/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.list.internal.upgrade.v3_1_0.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.asset.list.model.AssetListEntry;
import com.liferay.asset.list.model.AssetListEntrySegmentsEntryRel;
import com.liferay.asset.list.service.AssetListEntrySegmentsEntryRelLocalService;
import com.liferay.asset.list.test.util.AssetListTestUtil;
import com.liferay.portal.kernel.cache.MultiVMPool;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.UnicodePropertiesBuilder;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;
import com.liferay.portal.upgrade.test.util.UpgradeTestUtil;
import com.liferay.segments.constants.SegmentsEntryConstants;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Joshua Cords
 */
@RunWith(Arquillian.class)
public class AssetListEntrySegmentsEntryRelTypeSettingsUpgradeProcessTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();

		_assetListEntry = AssetListTestUtil.addAssetListEntry(
			_group.getGroupId());
	}

	@Test
	public void testUpgradeLegacyTypeSettings() throws Exception {
		AssetListEntrySegmentsEntryRel assetListEntrySegmentsEntryRel =
			AssetListTestUtil.addAssetListEntrySegmentsEntryRel(
				_group.getGroupId(), _assetListEntry,
				SegmentsEntryConstants.ID_DEFAULT,
				UnicodePropertiesBuilder.put(
					"anyAssetType", "true"
				).put(
					"queryAndOperator0", "true"
				).put(
					"queryAndOperator1", "false"
				).put(
					"queryAndOperator2", "true"
				).put(
					"queryContains0", "true"
				).put(
					"queryContains1", "false"
				).put(
					"queryContains2", "true"
				).put(
					"queryName0", "assetTags"
				).put(
					"queryName1", "assetCategories"
				).put(
					"queryName2", "keywords"
				).put(
					"queryValues0", "alpha,beta"
				).put(
					"queryValues1", "42"
				).put(
					"queryValues2", "hello,world"
				).buildString());

		_runUpgrade();

		UnicodeProperties unicodeProperties = _getTypeSettingsUnicodeProperties(
			assetListEntrySegmentsEntryRel);

		Assert.assertEquals(
			"true", unicodeProperties.getProperty("anyAssetType"));

		for (String key : unicodeProperties.keySet()) {
			Assert.assertFalse(key, key.startsWith("query"));
		}

		JSONArray filtersJSONArray = _jsonFactory.createJSONArray(
			unicodeProperties.getProperty("filters"));

		Assert.assertEquals(
			filtersJSONArray.toString(), 4, filtersJSONArray.length());

		JSONObject tagsJSONObject = filtersJSONArray.getJSONObject(0);

		Assert.assertEquals(
			"contains", tagsJSONObject.getString("operatorName"));
		Assert.assertEquals(
			"assetTags", tagsJSONObject.getString("propertyName"));
		Assert.assertEquals("all", tagsJSONObject.getString("quantifier"));

		JSONArray tagsValueJSONArray = tagsJSONObject.getJSONArray("value");

		Assert.assertEquals(
			tagsValueJSONArray.toString(), 2, tagsValueJSONArray.length());
		Assert.assertEquals(
			"alpha",
			tagsValueJSONArray.getJSONObject(
				0
			).getString(
				"value"
			));
		Assert.assertEquals(
			"beta",
			tagsValueJSONArray.getJSONObject(
				1
			).getString(
				"label"
			));

		JSONObject categoriesJSONObject = filtersJSONArray.getJSONObject(1);

		Assert.assertEquals(
			"not-contains", categoriesJSONObject.getString("operatorName"));
		Assert.assertEquals(
			"assetCategories", categoriesJSONObject.getString("propertyName"));
		Assert.assertEquals(
			"any", categoriesJSONObject.getString("quantifier"));

		JSONArray categoriesValueJSONArray = categoriesJSONObject.getJSONArray(
			"value");

		Assert.assertEquals(
			"42",
			categoriesValueJSONArray.getJSONObject(
				0
			).getString(
				"value"
			));

		for (int i = 2; i < 4; i++) {
			JSONObject keywordsJSONObject = filtersJSONArray.getJSONObject(i);

			Assert.assertEquals(
				"contains", keywordsJSONObject.getString("operatorName"));
			Assert.assertEquals(
				"keywords", keywordsJSONObject.getString("propertyName"));
			Assert.assertEquals(
				"all", keywordsJSONObject.getString("quantifier"));
		}

		Assert.assertEquals(
			"hello",
			filtersJSONArray.getJSONObject(
				2
			).getString(
				"value"
			));
		Assert.assertEquals(
			"world",
			filtersJSONArray.getJSONObject(
				3
			).getString(
				"value"
			));
	}

	@Test
	public void testUpgradePreservesAlreadyMigratedTypeSettings()
		throws Exception {

		String typeSettings = UnicodePropertiesBuilder.put(
			"anyAssetType", "true"
		).put(
			"filters",
			JSONUtil.putAll(
				JSONUtil.put(
					"operatorName", "contains"
				).put(
					"propertyName", "assetTags"
				).put(
					"quantifier", "any"
				).put(
					"value",
					JSONUtil.putAll(
						JSONUtil.put(
							"label", "alpha"
						).put(
							"value", "alpha"
						))
				)
			).toString()
		).buildString();

		AssetListEntrySegmentsEntryRel assetListEntrySegmentsEntryRel =
			AssetListTestUtil.addAssetListEntrySegmentsEntryRel(
				_group.getGroupId(), _assetListEntry,
				SegmentsEntryConstants.ID_DEFAULT, typeSettings);

		_runUpgrade();

		UnicodeProperties unicodeProperties = _getTypeSettingsUnicodeProperties(
			assetListEntrySegmentsEntryRel);

		Assert.assertEquals(
			UnicodePropertiesBuilder.fastLoad(
				typeSettings
			).build(),
			unicodeProperties);
	}

	private UnicodeProperties _getTypeSettingsUnicodeProperties(
		AssetListEntrySegmentsEntryRel assetListEntrySegmentsEntryRel) {

		assetListEntrySegmentsEntryRel =
			_assetListEntrySegmentsEntryRelLocalService.
				fetchAssetListEntrySegmentsEntryRel(
					assetListEntrySegmentsEntryRel.
						getAssetListEntrySegmentsEntryRelId());

		Assert.assertNotNull(assetListEntrySegmentsEntryRel);

		return UnicodePropertiesBuilder.fastLoad(
			assetListEntrySegmentsEntryRel.getTypeSettings()
		).build();
	}

	private void _runUpgrade() throws Exception {
		UpgradeProcess upgradeProcess = UpgradeTestUtil.getUpgradeStep(
			_upgradeStepRegistrator, _CLASS_NAME);

		upgradeProcess.upgrade();

		_entityCache.clearCache();
		_multiVMPool.clear();
	}

	private static final String _CLASS_NAME =
		"com.liferay.asset.list.internal.upgrade.v3_1_0." +
			"AssetListEntrySegmentsEntryRelTypeSettingsUpgradeProcess";

	@DeleteAfterTestRun
	private AssetListEntry _assetListEntry;

	@Inject
	private AssetListEntrySegmentsEntryRelLocalService
		_assetListEntrySegmentsEntryRelLocalService;

	@Inject
	private EntityCache _entityCache;

	@DeleteAfterTestRun
	private Group _group;

	@Inject
	private JSONFactory _jsonFactory;

	@Inject
	private MultiVMPool _multiVMPool;

	@Inject(
		filter = "(&(component.name=com.liferay.asset.list.internal.upgrade.registry.AssetListServiceUpgradeStepRegistrator))"
	)
	private UpgradeStepRegistrator _upgradeStepRegistrator;

}