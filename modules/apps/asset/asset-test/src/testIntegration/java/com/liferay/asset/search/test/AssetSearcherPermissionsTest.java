/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.search.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.asset.kernel.service.persistence.AssetEntryQuery;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.service.test.ServiceTestUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portlet.asset.util.AssetSearcher;

import java.util.stream.Stream;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

/**
 * @author André de Oliveira
 */
@RunWith(Arquillian.class)
public class AssetSearcherPermissionsTest {

	@ClassRule
	@Rule
	public static final TestRule testRule = new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();
	}

	@Test
	public void testGuestUser() throws Exception {
		setGuestUser();

		AssetEntryQuery assetEntryQuery = getAssetEntryQuery();

		assetEntryQuery.setClassNameIds(
			getClassNameIds("com.liferay.blogs.kernel.model.BlogsEntry"));

		search(assetEntryQuery, getSearchContext());
	}

	@Test
	public void testGuestUserBorrowedPermissions() throws Exception {
		setGuestUser();

		AssetEntryQuery assetEntryQuery = getAssetEntryQuery();

		assetEntryQuery.setClassNameIds(
			getClassNameIds(
				"com.liferay.calendar.model.CalendarBooking",
				"com.liferay.dynamic.data.lists.model.DDLRecord"));

		search(assetEntryQuery, getSearchContext());
	}

	protected AssetEntryQuery getAssetEntryQuery() {
		AssetEntryQuery assetEntryQuery = new AssetEntryQuery();

		assetEntryQuery.setGroupIds(new long[] {_group.getGroupId()});

		return assetEntryQuery;
	}

	protected long[] getClassNameIds(String... classNames) {
		return Stream.of(
			classNames
		).mapToLong(
			PortalUtil::getClassNameId
		).toArray();
	}

	protected SearchContext getSearchContext() {
		SearchContext searchContext = new SearchContext();

		searchContext.setCompanyId(_group.getCompanyId());
		searchContext.setGroupIds(new long[] {_group.getGroupId()});

		return searchContext;
	}

	protected void search(
			AssetEntryQuery assetEntryQuery, SearchContext searchContext)
		throws Exception {

		AssetSearcher assetSearcher = new AssetSearcher();

		assetSearcher.setAssetEntryQuery(assetEntryQuery);

		assetSearcher.search(searchContext);
	}

	protected void setGuestUser() throws Exception {
		ServiceTestUtil.setUser(
			_userLocalService.getDefaultUser(_group.getCompanyId()));
	}

	@Inject
	private static UserLocalService _userLocalService;

	@DeleteAfterTestRun
	private Group _group;

}