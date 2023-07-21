/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.blogs.search.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.blogs.model.BlogsEntry;
import com.liferay.blogs.service.BlogsEntryLocalServiceUtil;
import com.liferay.blogs.test.util.BlogsTestUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.search.test.util.BaseSearchTestCase;
import com.liferay.portal.service.test.ServiceTestUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Eudaldo Alonso
 */
@RunWith(Arquillian.class)
public class BlogsEntrySearchTest extends BaseSearchTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	@Override
	public void setUp() throws Exception {
		ServiceTestUtil.setUser(TestPropsValues.getUser());

		super.setUp();
	}

	@Ignore
	@Override
	@Test
	public void testLocalizedSearch() {
	}

	@Ignore
	@Override
	@Test
	public void testParentBaseModelUserPermissions() {
	}

	@Ignore
	@Override
	@Test
	public void testSearchAttachments() {
	}

	@Ignore
	@Override
	@Test
	public void testSearchByDDMStructureField() {
	}

	@Ignore
	@Override
	@Test
	public void testSearchByKeywordsInsideParentBaseModel() {
	}

	@Ignore
	@Override
	@Test
	public void testSearchExpireAllVersions() {
	}

	@Ignore
	@Override
	@Test
	public void testSearchExpireLatestVersion() {
	}

	@Ignore
	@Override
	@Test
	public void testSearchMyEntries() {
	}

	@Ignore
	@Override
	@Test
	public void testSearchRecentEntries() {
	}

	@Ignore
	@Override
	@Test
	public void testSearchStatus() {
	}

	@Ignore
	@Override
	@Test
	public void testSearchVersions() {
	}

	@Ignore
	@Override
	@Test
	public void testSearchWithinDDMStructure() {
	}

	@Override
	protected BaseModel<?> addBaseModelWithWorkflow(
			BaseModel<?> parentBaseModel, boolean approved, String keywords,
			ServiceContext serviceContext)
		throws Exception {

		return BlogsTestUtil.addEntryWithWorkflow(
			TestPropsValues.getUserId(), keywords, approved, serviceContext);
	}

	@Override
	protected void deleteBaseModel(long primaryKey) throws Exception {
		BlogsEntryLocalServiceUtil.deleteEntry(primaryKey);
	}

	@Override
	protected Class<?> getBaseModelClass() {
		return BlogsEntry.class;
	}

	@Override
	protected String getSearchKeywords() {
		return "Title";
	}

	@Override
	protected void moveBaseModelToTrash(long primaryKey) throws Exception {
		BlogsEntryLocalServiceUtil.moveEntryToTrash(
			TestPropsValues.getUserId(), primaryKey);
	}

	@Override
	protected BaseModel<?> updateBaseModel(
			BaseModel<?> baseModel, String keywords,
			ServiceContext serviceContext)
		throws Exception {

		BlogsEntry entry = (BlogsEntry)baseModel;

		return BlogsEntryLocalServiceUtil.updateEntry(
			serviceContext.getUserId(), entry.getEntryId(), keywords,
			entry.getContent(), serviceContext);
	}

}