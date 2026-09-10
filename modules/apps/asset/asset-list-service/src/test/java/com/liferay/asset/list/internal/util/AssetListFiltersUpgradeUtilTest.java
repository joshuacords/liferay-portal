/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.list.internal.util;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.UnicodePropertiesBuilder;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Joshua Cords
 */
public class AssetListFiltersUpgradeUtilTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Test
	public void testToUpgradedTypeSettingsAppendsToExistingFilters()
		throws Exception {

		JSONObject existingJSONObject = JSONUtil.put(
			"operatorName", "eq"
		).put(
			"propertyName", "title"
		).put(
			"value", "hello"
		);

		UnicodeProperties unicodeProperties = _toUpgradedUnicodeProperties(
			UnicodePropertiesBuilder.put(
				"filters",
				JSONUtil.putAll(
					existingJSONObject
				).toString()
			).put(
				"queryAndOperator0", "false"
			).put(
				"queryContains0", "true"
			).put(
				"queryName0", "assetTags"
			).put(
				"queryValues0", "alpha"
			).buildString());

		JSONArray filtersJSONArray = _getFiltersJSONArray(unicodeProperties);

		Assert.assertEquals(
			filtersJSONArray.toString(), 2, filtersJSONArray.length());
		Assert.assertEquals(
			existingJSONObject.toString(),
			filtersJSONArray.getJSONObject(
				0
			).toString());
		Assert.assertEquals(
			"assetTags",
			filtersJSONArray.getJSONObject(
				1
			).getString(
				"propertyName"
			));
	}

	@Test
	public void testToUpgradedTypeSettingsCategoriesAll() throws Exception {
		_assertCategories("true", "true", "contains", "all");
	}

	@Test
	public void testToUpgradedTypeSettingsCategoriesAny() throws Exception {
		_assertCategories("true", "false", "contains", "any");
	}

	@Test
	public void testToUpgradedTypeSettingsCategoriesNotAll() throws Exception {
		_assertCategories("false", "true", "not-contains", "all");
	}

	@Test
	public void testToUpgradedTypeSettingsCategoriesNotAny() throws Exception {
		_assertCategories("false", "false", "not-contains", "any");
	}

	@Test
	public void testToUpgradedTypeSettingsKeywordsAll() throws Exception {
		_assertKeywords("true", "true", "contains", "all");
	}

	@Test
	public void testToUpgradedTypeSettingsKeywordsAny() throws Exception {
		_assertKeywords("true", "false", "contains", "any");
	}

	@Test
	public void testToUpgradedTypeSettingsKeywordsNotAll() throws Exception {
		_assertKeywords("false", "true", "not-contains", "all");
	}

	@Test
	public void testToUpgradedTypeSettingsKeywordsNotAny() throws Exception {
		_assertKeywords("false", "false", "not-contains", "any");
	}

	@Test
	public void testToUpgradedTypeSettingsLastRuleInBucketWins()
		throws Exception {

		UnicodeProperties unicodeProperties = _toUpgradedUnicodeProperties(
			UnicodePropertiesBuilder.put(
				"queryAndOperator0", "true"
			).put(
				"queryAndOperator1", "true"
			).put(
				"queryContains0", "true"
			).put(
				"queryContains1", "true"
			).put(
				"queryName0", "assetTags"
			).put(
				"queryName1", "assetTags"
			).put(
				"queryValues0", "alpha"
			).put(
				"queryValues1", "beta"
			).buildString());

		JSONArray filtersJSONArray = _getFiltersJSONArray(unicodeProperties);

		Assert.assertEquals(
			filtersJSONArray.toString(), 1, filtersJSONArray.length());

		JSONObject filterJSONObject = filtersJSONArray.getJSONObject(0);

		JSONArray valueJSONArray = filterJSONObject.getJSONArray("value");

		Assert.assertEquals(
			valueJSONArray.toString(), 1, valueJSONArray.length());
		Assert.assertEquals(
			"beta",
			valueJSONArray.getJSONObject(
				0
			).getString(
				"value"
			));
	}

	@Test
	public void testToUpgradedTypeSettingsMultipleKeywordsFanOut()
		throws Exception {

		UnicodeProperties unicodeProperties = _toUpgradedUnicodeProperties(
			UnicodePropertiesBuilder.put(
				"queryAndOperator0", "false"
			).put(
				"queryContains0", "true"
			).put(
				"queryName0", "keywords"
			).put(
				"queryValues0", "alpha,beta,gamma"
			).buildString());

		JSONArray filtersJSONArray = _getFiltersJSONArray(unicodeProperties);

		Assert.assertEquals(
			filtersJSONArray.toString(), 3, filtersJSONArray.length());

		String[] expectedValues = {"alpha", "beta", "gamma"};

		for (int i = 0; i < expectedValues.length; i++) {
			JSONObject filterJSONObject = filtersJSONArray.getJSONObject(i);

			Assert.assertEquals(
				"contains", filterJSONObject.getString("operatorName"));
			Assert.assertEquals(
				"keywords", filterJSONObject.getString("propertyName"));
			Assert.assertEquals(
				"any", filterJSONObject.getString("quantifier"));
			Assert.assertEquals(
				expectedValues[i], filterJSONObject.getString("value"));
		}
	}

	@Test
	public void testToUpgradedTypeSettingsRemovesLegacyKeys() throws Exception {
		UnicodeProperties unicodeProperties = _toUpgradedUnicodeProperties(
			UnicodePropertiesBuilder.put(
				"anyAssetType", "true"
			).put(
				"queryAndOperator0", "true"
			).put(
				"queryAndOperator1", "false"
			).put(
				"queryContains0", "true"
			).put(
				"queryContains1", "false"
			).put(
				"queryName0", "assetTags"
			).put(
				"queryName1", "assetCategories"
			).put(
				"queryValues0", "alpha"
			).put(
				"queryValues1", "42"
			).buildString());

		Assert.assertEquals(
			"true", unicodeProperties.getProperty("anyAssetType"));
		Assert.assertNotNull(unicodeProperties.getProperty("filters"));

		for (String key : unicodeProperties.keySet()) {
			Assert.assertFalse(key, key.startsWith("query"));
		}
	}

	@Test
	public void testToUpgradedTypeSettingsReturnsNullWithoutLegacyKeys() {
		Assert.assertNull(
			AssetListFiltersUpgradeUtil.toUpgradedTypeSettings(null));
		Assert.assertNull(
			AssetListFiltersUpgradeUtil.toUpgradedTypeSettings(""));
		Assert.assertNull(
			AssetListFiltersUpgradeUtil.toUpgradedTypeSettings(
				UnicodePropertiesBuilder.put(
					"anyAssetType", "true"
				).put(
					"filters",
					JSONUtil.putAll(
						JSONUtil.put("propertyName", "assetTags")
					).toString()
				).buildString()));
	}

	@Test
	public void testToUpgradedTypeSettingsTagsAll() throws Exception {
		_assertTags("assetTags", "true", "true", "contains", "all");
	}

	@Test
	public void testToUpgradedTypeSettingsTagsAny() throws Exception {
		_assertTags("assetTags", "true", "false", "contains", "any");
	}

	@Test
	public void testToUpgradedTypeSettingsTagsNotAll() throws Exception {
		_assertTags("assetTags", "false", "true", "not-contains", "all");
	}

	@Test
	public void testToUpgradedTypeSettingsTagsNotAny() throws Exception {
		_assertTags("assetTags", "false", "false", "not-contains", "any");
	}

	@Test
	public void testToUpgradedTypeSettingsUnknownQueryNameTreatedAsTags()
		throws Exception {

		_assertTags("somethingElse", "true", "true", "contains", "all");
	}

	private void _assertCategories(
			String queryContains, String queryAndOperator,
			String expectedOperatorName, String expectedQuantifier)
		throws Exception {

		JSONObject filterJSONObject = _getSingleFilterJSONObject(
			"assetCategories", queryContains, queryAndOperator, "11,22");

		Assert.assertEquals(
			expectedOperatorName, filterJSONObject.getString("operatorName"));
		Assert.assertEquals(
			"assetCategories", filterJSONObject.getString("propertyName"));
		Assert.assertEquals(
			expectedQuantifier, filterJSONObject.getString("quantifier"));

		JSONArray valueJSONArray = filterJSONObject.getJSONArray("value");

		Assert.assertEquals(
			valueJSONArray.toString(), 2, valueJSONArray.length());

		JSONObject valueJSONObject = valueJSONArray.getJSONObject(0);

		Assert.assertEquals("11", valueJSONObject.getString("value"));
		Assert.assertFalse(valueJSONObject.has("label"));

		valueJSONObject = valueJSONArray.getJSONObject(1);

		Assert.assertEquals("22", valueJSONObject.getString("value"));
	}

	private void _assertKeywords(
			String queryContains, String queryAndOperator,
			String expectedOperatorName, String expectedQuantifier)
		throws Exception {

		JSONObject filterJSONObject = _getSingleFilterJSONObject(
			"keywords", queryContains, queryAndOperator, "alpha");

		Assert.assertEquals(
			expectedOperatorName, filterJSONObject.getString("operatorName"));
		Assert.assertEquals(
			"keywords", filterJSONObject.getString("propertyName"));
		Assert.assertEquals(
			expectedQuantifier, filterJSONObject.getString("quantifier"));
		Assert.assertEquals("alpha", filterJSONObject.getString("value"));
	}

	private void _assertTags(
			String queryName, String queryContains, String queryAndOperator,
			String expectedOperatorName, String expectedQuantifier)
		throws Exception {

		JSONObject filterJSONObject = _getSingleFilterJSONObject(
			queryName, queryContains, queryAndOperator, "alpha,beta");

		Assert.assertEquals(
			expectedOperatorName, filterJSONObject.getString("operatorName"));
		Assert.assertEquals(
			"assetTags", filterJSONObject.getString("propertyName"));
		Assert.assertEquals(
			expectedQuantifier, filterJSONObject.getString("quantifier"));

		JSONArray valueJSONArray = filterJSONObject.getJSONArray("value");

		Assert.assertEquals(
			valueJSONArray.toString(), 2, valueJSONArray.length());

		JSONObject valueJSONObject = valueJSONArray.getJSONObject(0);

		Assert.assertEquals("alpha", valueJSONObject.getString("label"));
		Assert.assertEquals("alpha", valueJSONObject.getString("value"));

		valueJSONObject = valueJSONArray.getJSONObject(1);

		Assert.assertEquals("beta", valueJSONObject.getString("label"));
		Assert.assertEquals("beta", valueJSONObject.getString("value"));
	}

	private JSONArray _getFiltersJSONArray(UnicodeProperties unicodeProperties)
		throws Exception {

		String filtersJSON = unicodeProperties.getProperty("filters");

		Assert.assertNotNull(filtersJSON);

		return JSONFactoryUtil.createJSONArray(filtersJSON);
	}

	private JSONObject _getSingleFilterJSONObject(
			String queryName, String queryContains, String queryAndOperator,
			String queryValues)
		throws Exception {

		UnicodeProperties unicodeProperties = _toUpgradedUnicodeProperties(
			UnicodePropertiesBuilder.put(
				"queryAndOperator0", queryAndOperator
			).put(
				"queryContains0", queryContains
			).put(
				"queryName0", queryName
			).put(
				"queryValues0", queryValues
			).buildString());

		JSONArray filtersJSONArray = _getFiltersJSONArray(unicodeProperties);

		Assert.assertEquals(
			filtersJSONArray.toString(), 1, filtersJSONArray.length());

		return filtersJSONArray.getJSONObject(0);
	}

	private UnicodeProperties _toUpgradedUnicodeProperties(
		String typeSettings) {

		String upgradedTypeSettings =
			AssetListFiltersUpgradeUtil.toUpgradedTypeSettings(typeSettings);

		Assert.assertNotNull(upgradedTypeSettings);

		return UnicodePropertiesBuilder.fastLoad(
			upgradedTypeSettings
		).build();
	}

}