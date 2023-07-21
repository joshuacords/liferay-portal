/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.bookmarks.service.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.bookmarks.model.BookmarksEntry;
import com.liferay.bookmarks.service.BookmarksEntryLocalServiceUtil;
import com.liferay.bookmarks.test.util.BookmarksTestUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.service.test.ServiceTestUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author     Michael C. Han
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
@RunWith(Arquillian.class)
public class BookmarksEntryLocalServiceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		ServiceTestUtil.setUser(TestPropsValues.getUser());

		_group = GroupTestUtil.addGroup();
	}

	@Test
	public void testGetNoAssetFolders() throws Exception {
		List<BookmarksEntry> initialBookmarksEntries =
			BookmarksEntryLocalServiceUtil.getNoAssetEntries();

		BookmarksEntry entry = BookmarksTestUtil.addEntry(
			_group.getGroupId(), true);

		AssetEntry assetEntry = AssetEntryLocalServiceUtil.fetchEntry(
			BookmarksEntry.class.getName(), entry.getEntryId());

		Assert.assertNotNull(assetEntry);

		AssetEntryLocalServiceUtil.deleteAssetEntry(assetEntry);

		List<BookmarksEntry> actualBookmarksEntries =
			BookmarksEntryLocalServiceUtil.getNoAssetEntries();

		Assert.assertEquals(
			actualBookmarksEntries.toString(),
			initialBookmarksEntries.size() + 1, actualBookmarksEntries.size());

		Assert.assertTrue(
			actualBookmarksEntries.toString(),
			actualBookmarksEntries.contains(entry));
	}

	@DeleteAfterTestRun
	private Group _group;

}