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
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.search.experiences.rest.dto.v1_0.ElementInstance;
import com.liferay.search.experiences.rest.dto.v1_0.SXPElement;
import com.liferay.search.experiences.rest.dto.v1_0.util.ElementInstanceUtil;

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

					try {
						ElementInstance[] elementInstances = ElementInstanceUtil.toElementInstances(
							elementInstancesJSON);

						if((elementInstances == null) || !_containsCategoryElement(elementInstances)) {
							continue;
						}
					}
					catch (RuntimeException runtimeException) {
					}

					JSONArray elementInstancesJSONArray = _jsonFactory.createJSONArray(
						elementInstancesJSON);


					for (int i = 0; i < elementInstancesJSONArray.length(); i++) {
						JSONObject elementInstanceJSON = elementInstancesJSONArray.getJSONObject(i);

						JSONObject sxpElementJSON = elementInstanceJSON.getJSONObject("sxpElement");

						String elementExternalReferenceCode = sxpElementJSON.getString("externalReferenceCode");

						if (!ArrayUtil.contains(_ELEMENT_EXTERNAL_REFERENCE_CODES, elementExternalReferenceCode)) {
							continue;
						}

						if (elementInstanceJSON.has("configurationEntry")) {
							_upgradeConfigurationEntry(
								elementExternalReferenceCode,
								elementInstanceJSON.getJSONObject("configurationEntry"));
						}

						if (elementInstanceJSON.has("sxpElement")) {
							_upgradeSXPElement(
								elementExternalReferenceCode,
								elementInstanceJSON.getJSONObject("sxpElement"));
						}

						if (elementInstanceJSON.has("uiConfigurationValues")) {
							_upgradeUIConfigurationValues(
								elementExternalReferenceCode,
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

	private boolean _containsCategoryElement(ElementInstance[] elementInstances) {

		for (ElementInstance elementInstance : elementInstances) {
			SXPElement sxpElement = elementInstance.getSxpElement();

			String externalReferenceCode = sxpElement.getExternalReferenceCode();

			if (ArrayUtil.contains(_ELEMENT_EXTERNAL_REFERENCE_CODES, externalReferenceCode)) {
				return true;
			}
		}

		return false;
	}


	private void _upgradeUIConfigurationValues(String externalReferenceCode, JSONObject uiConfigurationValuesJSON) {

		try {
			if(externalReferenceCode.startsWith("HIDE_CONTENTS_IN_A_CATEGORY")) {
				_upgradeUIConfigurationValuesForHideElements(uiConfigurationValuesJSON);
			} else if(externalReferenceCode.startsWith("BOOST_CONTENTS_IN_A_CATEGORY_")) {
				_upgradeUIConfigurationValuesForBoostElements(uiConfigurationValuesJSON);
			} else if(externalReferenceCode.startsWith("BOOST_CONTENTS_IN_A_CATEGORY")) {
				_upgradeUIConfigurationValuesForBoostElement(uiConfigurationValuesJSON);
			}
		} catch (Exception exception) {
		}

	}

	private void _upgradeUIConfigurationValuesForBoostElement(JSONObject uiConfigurationValuesJSON) throws Exception {
		JSONArray assetCategoryIdsJSONArray = uiConfigurationValuesJSON.getJSONArray("asset_category_ids");

		JSONArray groupAssetCategoryExternalReferenceCodesJSONArray = _jsonFactory.createJSONArray();

		for(int i = 0; i < assetCategoryIdsJSONArray.length(); i++) {
			JSONObject assetCategoryIdJSON = assetCategoryIdsJSONArray.getJSONObject(i);

			long assetCategoryId = assetCategoryIdJSON.getLong("value");

			JSONObject groupAssetCategoryExternalReferenceCodesJSON = _jsonFactory.createJSONObject();

			groupAssetCategoryExternalReferenceCodesJSON.put("label", _getLabel(assetCategoryId));
			groupAssetCategoryExternalReferenceCodesJSON.put("value", _getExternalReferenceCode(assetCategoryId));

			groupAssetCategoryExternalReferenceCodesJSONArray.put(groupAssetCategoryExternalReferenceCodesJSON);

		}

		uiConfigurationValuesJSON.put("group_asset_category_external_reference_codes", groupAssetCategoryExternalReferenceCodesJSONArray);
		uiConfigurationValuesJSON.remove("asset_category_ids");
	}

	private void _upgradeUIConfigurationValuesForBoostElements(JSONObject uiConfigurationValuesJSON) throws Exception {
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

		try {
			if (externalReferenceCode.startsWith(
				"HIDE_CONTENTS_IN_A_CATEGORY")) {
				_upgradeSXPElementForHideElements(sxpElementJSON);
			}
			else if (externalReferenceCode.startsWith(
				"BOOST_CONTENTS_IN_A_CATEGORY")) {
				_upgradeSXPElementForBoostElements(sxpElementJSON);
			}
		} catch (Exception exception) {
		}

	}

	private void _upgradeSXPElementForBoostElements(JSONObject sxpElementJSON) {
		JSONObject elementDefinitionJSON = sxpElementJSON.getJSONObject("elementDefinition");

		_upgradeConfigurationForBoostElements(elementDefinitionJSON.getJSONObject("configuration"));
		_upgradeUIConfigurationForBoostElements(elementDefinitionJSON.getJSONObject("uiConfiguration"));
		
	}

	private void _upgradeSXPElementForHideElements(JSONObject sxpElementJSON) {
		JSONObject elementDefinitionJSON = sxpElementJSON.getJSONObject("elementDefinition");

		_upgradeConfigurationForHideElements(elementDefinitionJSON.getJSONObject("configuration"));
		_upgradeUIConfigurationForBoostElements(elementDefinitionJSON.getJSONObject("uiConfiguration"));

	}

	private void _upgradeConfigurationForHideElements(JSONObject configurationJSON) {
		try {
			JSONObject queryConfigurationJSON =
				configurationJSON.getJSONObject("queryConfiguration");

			JSONArray queryEntriesJSONArray =
				queryConfigurationJSON.getJSONArray("queryEntries");

			for (int i = 0; i < queryEntriesJSONArray.length(); i++) {
				JSONObject queryEntryJSON =
					queryEntriesJSONArray.getJSONObject(i);

				JSONArray clausesJSONArray =
					queryEntryJSON.getJSONArray("clauses");

				for (int j = 0; j < clausesJSONArray.length(); j++) {
					JSONObject clauseJSON = clausesJSONArray.getJSONObject(i);

					JSONObject queryJSON = clauseJSON.getJSONObject("query");

					JSONObject boolJSON = queryJSON.getJSONObject("bool");

					JSONArray mustNotJSONArray = boolJSON.getJSONArray("must_not");

					for (int k = 0; k < mustNotJSONArray.length(); k++) {
						JSONObject mustNotJSON = mustNotJSONArray.getJSONObject(k);

						if (!mustNotJSON.has("term")) {
							continue;
						}

						mustNotJSON.remove("term");
						JSONObject termsJSON = _jsonFactory.createJSONObject();
						termsJSON.put("groupAssetCategoryExternalReferenceCodes", "${configuration.group_asset_category_external_reference_codes}");

						mustNotJSON.put("terms", termsJSON);
						break;
					}
				}
			}
		} catch (Exception exception) {
		}
	}

	private void _upgradeUIConfigurationForBoostElements(JSONObject uiConfigurationJSON) {
		try {
			JSONArray fieldSetsJSONArray = uiConfigurationJSON.getJSONArray("fieldSets");

			for (int i = 0; i < fieldSetsJSONArray.length(); i++) {
				JSONObject fieldSetJSON = fieldSetsJSONArray.getJSONObject(i);

				JSONArray fieldsJSONArray = fieldSetJSON.getJSONArray("fields");

				for (int j = 0; j < fieldsJSONArray.length(); j++) {
					JSONObject fieldJSON = fieldsJSONArray.getJSONObject(i);

					String fieldName = fieldJSON.getString("name");

					if (!fieldName.startsWith("asset_category_id")) {
						continue;
					}

					fieldJSON.put("label", "asset-category-external-reference-codes");
					fieldJSON.put("name", "group_asset_category_external_reference_codes");
					fieldJSON.put("type", "multiselect");
					fieldJSON.remove("labelLocalized");

					break;
				}
			}
		} catch (Exception exception) {
		}
	}

	private void _upgradeConfigurationForBoostElements(JSONObject configurationJSON) {

		try {
			JSONObject queryConfigurationJSON =
				configurationJSON.getJSONObject("queryConfiguration");

			JSONArray queryEntriesJSONArray =
				queryConfigurationJSON.getJSONArray("queryEntries");

			for (int i = 0; i < queryEntriesJSONArray.length(); i++) {
				JSONObject queryEntryJSON =
					queryEntriesJSONArray.getJSONObject(i);

				JSONArray clausesJSONArray =
					queryEntryJSON.getJSONArray("clauses");

				for (int j = 0; j < clausesJSONArray.length(); j++) {
					JSONObject clauseJSON = clausesJSONArray.getJSONObject(i);

					JSONObject queryJSON = clauseJSON.getJSONObject("query");

					queryJSON.remove("term");

					JSONObject termsJSON = _jsonFactory.createJSONObject();

					termsJSON.put("boost", "${configuration.boost}");
					termsJSON.put(
						"groupAssetCategoryExternalReferenceCodes",
						"${configuration.group_asset_category_external_reference_codes}");

					queryJSON.put("terms", termsJSON);
				}
			}
		} catch (Exception exception) {
		}
	}

	private void _upgradeConfigurationEntry(String externalReferenceCode, JSONObject configurationEntryJSON) {

		try {
			if(externalReferenceCode.startsWith("HIDE_CONTENTS_IN_A_CATEGORY")) {
				_upgradeConfigurationEntryForHideElements(configurationEntryJSON);
			} else if(externalReferenceCode.startsWith("BOOST_CONTENTS_IN_A_CATEGORY_")) {
				_upgradeConfigurationEntryForBoostContentInACategoryElements(configurationEntryJSON);
			} else if(externalReferenceCode.equals("BOOST_CONTENTS_IN_A_CATEGORY")) {
				_upgradeConfigurationEntryForBoostContentInACategoryElement(configurationEntryJSON);
			}

		} catch (Exception exception) {
		}
	}

	private void _upgradeConfigurationEntryForBoostContentInACategoryElements(JSONObject configurationEntryJSON) throws Exception {
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

				JSONObject termJSON = queryJSON.getJSONObject("term");

				JSONObject assetCategoryIdsJSON = termJSON.getJSONObject("assetCategoryIds");

				JSONObject
					groupAssetCategoryExternalReferenceCodesJSON =
					_jsonFactory.createJSONObject();

				groupAssetCategoryExternalReferenceCodesJSON.put(
					"groupAssetCategoryExternalReferenceCodes",
					_translateIdsToExternalReferencesCodes(
						_extractAssetCategoryIds(termJSON)));

				groupAssetCategoryExternalReferenceCodesJSON.put("boost", assetCategoryIdsJSON.getDouble("boost"));

				queryJSON.remove("term");

				queryJSON.put(
					"terms",
					groupAssetCategoryExternalReferenceCodesJSON);
			}

		}
	}

	private void _upgradeConfigurationEntryForBoostContentInACategoryElement(JSONObject configurationEntryJSON) throws Exception {
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

				JSONObject termsJSON = queryJSON.getJSONObject("terms");

				JSONObject
					groupAssetCategoryExternalReferenceCodesJSON =
					_jsonFactory.createJSONObject();

				groupAssetCategoryExternalReferenceCodesJSON.put(
					"groupAssetCategoryExternalReferenceCodes",
					_translateIdsToExternalReferencesCodes(
						_extractAssetCategoryIds(termsJSON)));

				groupAssetCategoryExternalReferenceCodesJSON.put("boost", termsJSON.getDouble("boost"));

				queryJSON.put(
					"terms",
					groupAssetCategoryExternalReferenceCodesJSON);
			}

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

	private void _upgradeSXPElements() throws Exception {
		try (PreparedStatement preparedStatement =
				 AutoBatchPreparedStatementUtil.autoBatch(
					 connection,
					 "update SXPElement set elementDefinitionJSON = ? where " +
					 "externalReferenceCode = ?")) {

			for (String externalReferenceCode : _EXTERNAL_REFERENCE_CODES) {
				preparedStatement.setString(
					1, _getElementDefinitionJSON(externalReferenceCode));
				preparedStatement.setString(2, externalReferenceCode);

				preparedStatement.addBatch();
			}

			preparedStatement.executeBatch();
		}
	}

	private String _getElementDefinitionJSON(String externalReferenceCode) {
		return StringUtil.read(
			getClass(),
			"dependencies/" + StringUtil.toLowerCase(externalReferenceCode) +
				".json");
	}

	private static final String[] _EXTERNAL_REFERENCE_CODES = {
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