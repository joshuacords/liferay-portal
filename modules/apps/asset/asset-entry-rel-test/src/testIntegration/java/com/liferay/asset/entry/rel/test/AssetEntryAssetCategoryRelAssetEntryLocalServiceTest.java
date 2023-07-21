/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.entry.rel.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.asset.test.util.AssetTestUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Samuel Trong Tran
 */
@RunWith(Arquillian.class)
public class AssetEntryAssetCategoryRelAssetEntryLocalServiceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerMethodTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();
		_user = UserTestUtil.addUser();

		_assetVocabulary = AssetTestUtil.addVocabulary(_group.getGroupId());

		for (int i = 0; i < _assetCategoryIds.length; i++) {
			_assetCategoryIds[i] = _addAssetCategory(_group, _assetVocabulary);
		}
	}

	@Test
	public void testUserCategoryIds() throws Exception {
		_assertSize(1, ArrayUtil.subset(_assetCategoryIds, 0, 1));

		_assertSize(2, _assetCategoryIds);

		_assertSize(2, null);

		_assertSize(0, new long[0]);
	}

	@Inject
	protected static AssetEntryLocalService assetEntryLocalService;

	private long _addAssetCategory(Group group, AssetVocabulary assetVocabulary)
		throws Exception {

		AssetCategory assetCategory = AssetTestUtil.addCategory(
			group.getGroupId(), assetVocabulary.getVocabularyId());

		_assetCategories.add(assetCategory);

		return assetCategory.getCategoryId();
	}

	private void _assertSize(int expectedSize, long[] assetCategoryIds)
		throws Exception {

		assetEntryLocalService.updateEntry(
			_user.getUserId(), _group.getGroupId(), _user.getCreateDate(),
			_user.getModifiedDate(), User.class.getName(), _user.getUserId(),
			_user.getUuid(), 0, assetCategoryIds, null, true, false, null, null,
			null, null, null, _user.getFullName(), null, null, null, null, 0, 0,
			null);

		List<AssetCategory> assetCategories =
			AssetCategoryServiceUtil.getCategories(
				User.class.getName(), _user.getUserId());

		Assert.assertEquals(
			assetCategories.toString(), expectedSize, assetCategories.size());
	}

	@DeleteAfterTestRun
	private final List<AssetCategory> _assetCategories = new ArrayList<>();

	private final long[] _assetCategoryIds = new long[2];

	@DeleteAfterTestRun
	private AssetVocabulary _assetVocabulary;

	@DeleteAfterTestRun
	private Group _group;

	@DeleteAfterTestRun
	private User _user;

}