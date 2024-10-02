/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.search.experiences.internal.upgrade.v3_1_4;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.search.experiences.rest.dto.v1_0.ElementInstance;
import com.liferay.search.experiences.rest.dto.v1_0.SXPBlueprint;
import com.liferay.search.experiences.rest.dto.v1_0.util.ElementInstanceUtil;
import com.liferay.search.experiences.rest.dto.v1_0.util.SXPBlueprintUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Pattern;

/**
 * @author Joshua Cords
 */
public class SXPBlueprintAndSXPElementUpgradeProcess extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		_upgradeSXPBlueprints();
		_upgradeSXPElements();
	}

	public SXPBlueprintAndSXPElementUpgradeProcess(
		AssetCategoryLocalService assetCategoryLocalService,
		GroupLocalService groupLocalService,
		JSONFactory jsonFactory) {

		_assetCategoryLocalService = assetCategoryLocalService;
		_groupLocalService = groupLocalService;
		_jsonFactory = jsonFactory;
	}

	private AssetCategoryLocalService _assetCategoryLocalService;
	private GroupLocalService _groupLocalService;
	private JSONFactory _jsonFactory;

	private void _upgradeSXPBlueprints() throws Exception {
		try (PreparedStatement preparedStatement1 = connection.prepareStatement(
			"select sxpBlueprintId, elementInstancesJSON from SXPBlueprint");
			 PreparedStatement preparedStatement2 =
				 AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					 connection,
					 "update SXPBlueprint set elementInstancesJSON = ? where " +
					 "sxpBlueprintId = ?")) {

			try (ResultSet resultSet = preparedStatement1.executeQuery()) {
				while (resultSet.next()) {
					String elementInstancesJSON = resultSet.getString(
						"elementInstancesJSON");

//					SXPBlueprint sxpBlueprint = SXPBlueprintUtil.toSXPBlueprint(elementInstancesJSON);

					try {
						ElementInstance[] elementInstances = ElementInstanceUtil.toElementInstances(
							elementInstancesJSON);

//						if((elementInstances.length == 0) || !_containsCategoryElement(elementInstances)) {
//							continue;
//						}
					}
					catch (RuntimeException runtimeException) {
						if (_log.isWarnEnabled()) {
							_log.warn(
								StringBundler.concat(
									"Search experiences blueprint with ID ",
									resultSet.getLong("sxpBlueprintId"),
									" contains corrupted element instances ",
									"JSON"),
								runtimeException);
						}
					}

					JSONArray elementInstancesJSONArray = _jsonFactory.createJSONArray(
						elementInstancesJSON);


					for (int i = 0; i < elementInstancesJSONArray.length(); i++) {
						JSONObject elementInstanceJSON = elementInstancesJSONArray.getJSONObject(i);

						JSONObject sxpElementJSON = elementInstanceJSON.getJSONObject("sxpElement");

						String elemnentExternalReferenceCode = sxpElementJSON.getString("externalReferenceCode");

						if (elementInstanceJSON.has("configurationEntry")) {
							_upgradeConfigurationEntry(
								elemnentExternalReferenceCode,
								elementInstanceJSON.getJSONObject("configurationEntry"));
						}

						if (elementInstanceJSON.has("sxpElement")) {
							_upgradeSXPElement(
								elemnentExternalReferenceCode,
								elementInstanceJSON.getJSONObject("sxpElement"));
						}

						if (elementInstanceJSON.has("uiConfigurationValues")) {
							_upgradeUIConfigurationValues(
								elemnentExternalReferenceCode,
								elementInstanceJSON.getJSONObject("uiConfigurationValues"));
						}
					}

					preparedStatement2.setString(
						1, elementInstancesJSONArray.toJSONString());

					preparedStatement2.setLong(
						2, resultSet.getLong("sxpBlueprintId"));

					preparedStatement2.addBatch();
				}

				preparedStatement2.executeBatch();
			}
		}
	}

	private void _upgradeUIConfigurationValues(String externalReferenceCode, JSONObject uiConfigurationValuesJSON) {

		try {
			if(externalReferenceCode.startsWith("HIDE_CONTENTS_IN_A_CATEGORY")) {
				_upgradeUIConfigurationValuesForHideElements(uiConfigurationValuesJSON);
			}

		} catch (Exception exception) {
		}

	}

	private void _upgradeUIConfigurationValuesForHideElements(JSONObject uiConfigurationValuesJSON) throws Exception {
		JSONObject assetCategoryIdsJSON = uiConfigurationValuesJSON.getJSONObject("asset_category_id");

		long assetCategoryId = assetCategoryIdsJSON.getLong("value");

		JSONObject groupAssetCategoryExternalReferenceCodesJSON = _jsonFactory.createJSONObject();

		groupAssetCategoryExternalReferenceCodesJSON.put("label", _getLabel(assetCategoryId));
		groupAssetCategoryExternalReferenceCodesJSON.put("value", _getExternalReferenceCode(assetCategoryId));

		JSONArray groupAssetCategoryExternalReferenceCodesJSONArray = _jsonFactory.createJSONArray();
		groupAssetCategoryExternalReferenceCodesJSONArray.put(groupAssetCategoryExternalReferenceCodesJSON);

		uiConfigurationValuesJSON.put("group_asset_category_external_reference_codes", groupAssetCategoryExternalReferenceCodesJSONArray);
		uiConfigurationValuesJSON.remove("asset_category_id");
	}

	private String _getLabel(long assetCategoryId) throws Exception {
		try {
			AssetCategory assetCategory =
				_assetCategoryLocalService.getAssetCategory(assetCategoryId);

			return StringBundler.concat(assetCategory.getName(), " (ERC: ", assetCategory.getExternalReferenceCode(), ")");
		} catch (Exception exception) {
			_log.error("Unable to find assetCategory associated with " + assetCategoryId);

			throw exception;
		}
	}

	private String _getExternalReferenceCode(long assetCategoryId) throws PortalException {
		try {
			AssetCategory assetCategory =
				_assetCategoryLocalService.getAssetCategory(assetCategoryId);

			Group group = _groupLocalService.getGroup(assetCategory.getGroupId());

			return group.getExternalReferenceCode() + "&&" + assetCategory.getExternalReferenceCode();
		} catch (PortalException portalException) {
			_log.error("Unable to find assetCategory with id " + assetCategoryId);
			throw portalException;
		}
	}

	private void _upgradeSXPElement(String externalReferenceCode, JSONObject sxpElementJSON) throws Exception {

		if(externalReferenceCode.startsWith("HIDE_CONTENTS_IN_A_CATEGORY")) {
			_upgradeSXPElementForHideElements(sxpElementJSON);
		}


	}

	private void _upgradeSXPElementForHideElements(JSONObject sxpElementJSON) throws Exception {
		String elementDefinition = sxpElementJSON.getString("elementDefinition");

		for (int i = 0; i < _HIDE_CONTENTS_IN_A_CATEGORY_OLD.length; i++) {
			elementDefinition = StringUtil.replace(elementDefinition,
				_HIDE_CONTENTS_IN_A_CATEGORY_OLD[i],
				_HIDE_CONTENTS_IN_A_CATEGORY_NEW[i]);
		}

		sxpElementJSON.remove("elementDefinition");
		sxpElementJSON.put("elementDefinition", _jsonFactory.createJSONObject(elementDefinition));
	}

	private static String[] _HIDE_CONTENTS_IN_A_CATEGORY_OLD = new String[]{
		"{\"term\":{\"assetCategoryIds\":{\"value\":\"${configuration.asset_category_id}\"}}}",
		"\"asset-category-name-id\"", "\"asset_category_id\"", "\"number\""
	};

	private static String[] _HIDE_CONTENTS_IN_A_CATEGORY_NEW = new String[]{
		"{\"terms\":{\"groupAssetCategoryExternalReferenceCodes\":\"${configuration.group_asset_category_external_reference_codes}\"}}",
		"\"asset-category-external-reference-codes\"",
		"\"group_asset_category_external_reference_codes\"",
		"\"multiselect\""
	};

	private void _upgradeConfigurationEntry(String externalReferenceCode, JSONObject configurationEntryJSON) {

		try {
			if(externalReferenceCode.startsWith("HIDE_CONTENTS_IN_A_CATEGORY")) {
				_upgradeConfigurationEntryForHideElements(configurationEntryJSON);
			}

		} catch (Exception exception) {
		}
	}

	private void _upgradeConfigurationEntryForHideElements(JSONObject configurationEntryJSON) throws Exception {
		JSONObject queryConfigurationEntryJSON =
			configurationEntryJSON.getJSONObject("queryConfiguration");

		JSONArray queryEntriesJSONArray =
			queryConfigurationEntryJSON.getJSONArray("queryEntries");

		for (int i = 0; i < queryEntriesJSONArray.length(); i++) {
			JSONObject queryEntryJSON =
				queryEntriesJSONArray.getJSONObject(i);

			JSONArray clausesJSONArray =
				queryEntryJSON.getJSONArray("clauses");

			for (int k = 0; k < clausesJSONArray.length(); k++) {
				JSONObject clauseJSON = clausesJSONArray.getJSONObject(i);

				JSONObject queryJSON = clauseJSON.getJSONObject("query");

				JSONObject boolJSON = queryJSON.getJSONObject("bool");

				JSONArray mustNotJSONArray =
					boolJSON.getJSONArray("must_not");

				for (int j = 0; j < mustNotJSONArray.length(); j++) {
					JSONObject mustNotJSON =
						mustNotJSONArray.getJSONObject(j);

					JSONObject termJSON = mustNotJSON.getJSONObject("term");

					long[] assetCategoryIds =
						_extractAssetCategoryIds(termJSON);

					mustNotJSON.remove("term");

					JSONObject
						groupAssetCategoryExternalReferenceCodesJSON =
						_jsonFactory.createJSONObject();

					groupAssetCategoryExternalReferenceCodesJSON.put(
						"groupAssetCategoryExternalReferenceCodes",
						_translateIdsToExternalReferencesCodes(
							assetCategoryIds));

					mustNotJSON.put(
						"terms",
						groupAssetCategoryExternalReferenceCodesJSON);
				}
			}

		}
	}

	private JSONArray _translateIdsToExternalReferencesCodes(long[] assetCategoryIds) throws PortalException {

		JSONArray jsonArray = _jsonFactory.createJSONArray();

		for (int i = 0; i < assetCategoryIds.length; i++) {
			jsonArray.put(_getExternalReferenceCode(assetCategoryIds[i]));
		}

		return jsonArray;
	}

	private long[] _extractAssetCategoryIds(JSONObject termJSON) {
		long[] assetCategoryIds;

		JSONArray assetCategoryJSONArray = termJSON.getJSONArray("assetCategoryIds");

		if (assetCategoryJSONArray == null) {
			JSONObject valueJSON = termJSON.getJSONObject("assetCategoryIds");

			assetCategoryIds = new long[1];
			assetCategoryIds[0] = valueJSON.getLong("value");
		} else {
			assetCategoryIds = new long[assetCategoryJSONArray.length()];

			for (int k = 0; k < assetCategoryJSONArray.length(); k++) {
				assetCategoryIds[k] = assetCategoryJSONArray.getLong(k);
			}
		}

		return assetCategoryIds;
	}

	private JSONObject _upgradeCategoryIdToCategoryExternalReferenceCode(
		JSONObject sxpElementJSONObject) throws Exception {

		String elementDefinitionJSON = sxpElementJSONObject.getString(
			"elementDefinition");

		JSONArray clausesArray = sxpElementJSONObject.getJSONObject("elementDefinition").getJSONObject("configuration").getJSONObject("queryConfiguration").getJSONArray("queryEntries").getJSONObject(0).getJSONArray("clauses");

		for (int i = 0; i < clausesArray.length(); i++) {
			JSONObject jsonObject = clausesArray.getJSONObject(i);

			JSONObject termsObject = jsonObject.getJSONObject("query").getJSONObject("terms");

			if (termsObject == null) {
				continue;
			}

			termsObject.put("groupAssetCategoryExternalReferenceCodes", "${configuration.group_asset_category_external_reference_codes}");
			termsObject.remove("assetCategoryIds");
		}

//		for (int i = 0; i < clausesArray.length(); i++) {
//			JSONObject jsonObject = clausesArray.getJSONObject(i);
//
//			jsonObject.getJSONObject("query").getJSONObject("terms").put("groupAssetCategoryExternalReferenceCodes", "${configuration.group_asset_category_external_reference_codes}");
//
//			long[] assetCategoryIds;
//
//			if (jsonObject.has("assetCategoryIds")) {
//				JSONArray assetCategoriesArray = jsonObject.getJSONArray("assetCategoryIds");
//
//				assetCategoryIds = new long[assetCategoriesArray.length()];
//
//				for (int j = 0; j < assetCategoriesArray.length(); j++) {
//					assetCategoryIds[j] = assetCategoriesArray.getLong(j);
//				}
//
//			}
//		}

//		elementDefinitionJSON = StringUtil.replace(
//			elementDefinitionJSON, "\"defaultValue\":[]",
//			_defaultValues.get(externalReferenceCode));

		return _jsonFactory.createJSONObject(elementDefinitionJSON);
	}

	private void _upgradeSXPElements() throws Exception {
		try (PreparedStatement preparedStatement =
				 AutoBatchPreparedStatementUtil.autoBatch(
					 connection,
					 "update SXPElement set elementDefinitionJSON = ? where " +
					 "externalReferenceCode = ?")) {

			for (String elementExternalReferenceCode :
				_ELEMENT_EXTERNAL_REFERENCE_CODES) {

				preparedStatement.setString(
					1, _getElementDefinitionJSON(elementExternalReferenceCode));

				preparedStatement.setString(2, elementExternalReferenceCode);

				preparedStatement.addBatch();
			}

			preparedStatement.executeBatch();
		}
	}

	private String _getElementDefinitionJSON(
		String elementExternalReferenceCode) {

		return StringUtil.read(
			getClass(),
			"dependencies/" +
				StringUtil.toLowerCase(elementExternalReferenceCode) + ".json");
	}

	private static final String[] _ELEMENT_EXTERNAL_REFERENCE_CODES = {
		"BOOST_CONTENTS_IN_A_CATEGORY",
		"BOOST_CONTENTS_IN_A_CATEGORY_BY_KEYWORD_MATCH",
		"BOOST_CONTENTS_IN_A_CATEGORY_FOR_A_PERIOD_OF_TIME",
		"BOOST_CONTENTS_IN_A_CATEGORY_FOR_GUEST_USERS",
		"BOOST_CONTENTS_IN_A_CATEGORY_FOR_NEW_USER_ACCOUNTS",
		"BOOST_CONTENTS_IN_A_CATEGORY_FOR_THE_TIME_OF_DAY",
		"BOOST_CONTENTS_IN_A_CATEGORY_FOR_USER_SEGMENTS",
		"HIDE_CONTENTS_IN_A_CATEGORY",
		"HIDE_CONTENTS_IN_A_CATEGORY_FOR_GUEST_USERS"
	};

	private static final Log _log = LogFactoryUtil.getLog(
		SXPBlueprintAndSXPElementUpgradeProcess.class);

	private static final Pattern _pattern = Pattern.compile(
		"Ljava\\.lang\\.Object;@\\w{8}");

}