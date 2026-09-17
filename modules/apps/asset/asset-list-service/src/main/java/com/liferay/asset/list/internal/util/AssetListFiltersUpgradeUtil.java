/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.list.internal.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.UnicodePropertiesBuilder;
import com.liferay.portal.kernel.util.Validator;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Converts the legacy indexed query rules stored in an {@code
 * AssetListEntrySegmentsEntryRel} type settings blob ({@code queryName&lt;i&gt;},
 * {@code queryValues&lt;i&gt;}, {@code queryContains&lt;i&gt;} and {@code
 * queryAndOperator&lt;i&gt;}) into the {@code filters} JSON array read by {@link
 * AssetListFiltersUtil}.
 *
 * <p>
 * The converter moves names and IDs verbatim and performs no lookups, so it
 * has no OSGi dependencies and is shared by the schema upgrade step and the
 * export/import content processor.
 * </p>
 *
 * @author Joshua Cords
 */
public class AssetListFiltersUpgradeUtil {

	/**
	 * Returns the type settings with the legacy query rules replaced by the
	 * equivalent {@code filters} rows, or <code>null</code> if the type
	 * settings hold no legacy query rules and nothing needs to be rewritten.
	 *
	 * @param  typeSettings the type settings to upgrade
	 * @return the upgraded type settings, or <code>null</code> if there is
	 *         nothing to upgrade
	 */
	public static String toUpgradedTypeSettings(String typeSettings) {
		if (Validator.isNull(typeSettings)) {
			return null;
		}

		UnicodeProperties unicodeProperties = UnicodePropertiesBuilder.fastLoad(
			typeSettings
		).build();

		if (!_hasLegacyQueryRules(unicodeProperties)) {
			return null;
		}

		Map<String, JSONObject> bucketsMap = new LinkedHashMap<>();

		for (int i = 0; true; i++) {
			String[] queryValues = StringUtil.split(
				unicodeProperties.getProperty("queryValues" + i, null));

			if (ArrayUtil.isEmpty(queryValues)) {
				break;
			}

			boolean queryContains = GetterUtil.getBoolean(
				unicodeProperties.getProperty(
					"queryContains" + i, StringPool.BLANK));
			boolean queryAndOperator = GetterUtil.getBoolean(
				unicodeProperties.getProperty(
					"queryAndOperator" + i, StringPool.BLANK));

			String queryName = unicodeProperties.getProperty(
				"queryName" + i, StringPool.BLANK);

			String propertyName = _toPropertyName(queryName);

			String bucketKey = StringUtil.merge(
				new String[] {
					propertyName, String.valueOf(queryContains),
					String.valueOf(queryAndOperator)
				},
				StringPool.POUND);

			bucketsMap.put(
				bucketKey,
				JSONUtil.put(
					"operatorName", _toOperatorName(queryContains)
				).put(
					"propertyName", propertyName
				).put(
					"quantifier", _toQuantifier(queryAndOperator)
				).put(
					"value", _toValue(propertyName, queryValues)
				));
		}

		JSONArray filtersJSONArray = _getFiltersJSONArray(unicodeProperties);

		for (JSONObject bucketJSONObject : bucketsMap.values()) {
			if (Objects.equals(
					bucketJSONObject.getString("propertyName"), "keywords")) {

				JSONArray valueJSONArray = bucketJSONObject.getJSONArray(
					"value");

				for (int j = 0; j < valueJSONArray.length(); j++) {
					filtersJSONArray.put(
						JSONUtil.put(
							"operatorName",
							bucketJSONObject.getString("operatorName")
						).put(
							"propertyName", "keywords"
						).put(
							"quantifier",
							bucketJSONObject.getString("quantifier")
						).put(
							"value", valueJSONArray.getString(j)
						));
				}

				continue;
			}

			filtersJSONArray.put(bucketJSONObject);
		}

		unicodeProperties.setProperty("filters", filtersJSONArray.toString());

		_removeLegacyQueryRules(unicodeProperties);

		return unicodeProperties.toString();
	}

	private static JSONArray _getFiltersJSONArray(
		UnicodeProperties unicodeProperties) {

		String filtersJSON = unicodeProperties.getProperty("filters");

		if (Validator.isNull(filtersJSON)) {
			return JSONFactoryUtil.createJSONArray();
		}

		try {
			return JSONFactoryUtil.createJSONArray(filtersJSON);
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception);
			}

			return JSONFactoryUtil.createJSONArray();
		}
	}

	private static boolean _hasLegacyQueryRules(
		UnicodeProperties unicodeProperties) {

		for (String key : unicodeProperties.keySet()) {
			if (StringUtil.startsWith(key, "queryName")) {
				return true;
			}
		}

		return false;
	}

	private static void _removeLegacyQueryRules(
		UnicodeProperties unicodeProperties) {

		// The runtime stops reading at the first empty queryValues<i>, so keys
		// past that point were never applied. Remove those too, so the presence
		// of queryName<i> stays a reliable "needs upgrade" signal.

		for (String key :
				unicodeProperties.keySet(
				).toArray(
					new String[0]
				)) {

			if (StringUtil.startsWith(key, "queryAndOperator") ||
				StringUtil.startsWith(key, "queryContains") ||
				StringUtil.startsWith(key, "queryName") ||
				StringUtil.startsWith(key, "queryValues")) {

				unicodeProperties.remove(key);
			}
		}
	}

	private static String _toOperatorName(boolean queryContains) {
		if (queryContains) {
			return "contains";
		}

		return "not-contains";
	}

	private static String _toPropertyName(String queryName) {
		if (Objects.equals(queryName, "assetCategories") ||
			Objects.equals(queryName, "keywords")) {

			return queryName;
		}

		return "assetTags";
	}

	private static String _toQuantifier(boolean queryAndOperator) {
		if (queryAndOperator) {
			return "all";
		}

		return "any";
	}

	private static JSONArray _toValue(
		String propertyName, String[] queryValues) {

		JSONArray valueJSONArray = JSONFactoryUtil.createJSONArray();

		for (String queryValue : queryValues) {
			if (Objects.equals(propertyName, "assetCategories")) {
				valueJSONArray.put(JSONUtil.put("value", queryValue));
			}
			else if (Objects.equals(propertyName, "keywords")) {
				valueJSONArray.put(queryValue);
			}
			else {
				valueJSONArray.put(
					JSONUtil.put(
						"label", queryValue
					).put(
						"value", queryValue
					));
			}
		}

		return valueJSONArray;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AssetListFiltersUpgradeUtil.class);

}