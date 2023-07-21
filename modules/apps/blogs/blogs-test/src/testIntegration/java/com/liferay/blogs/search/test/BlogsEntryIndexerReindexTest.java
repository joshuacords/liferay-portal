/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.blogs.search.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.blogs.model.BlogsEntry;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.rule.SynchronousDestinationTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.search.test.util.IndexerFixture;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;
import com.liferay.users.admin.test.util.search.UserSearchFixture;

import java.util.List;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Igor Fabiano Nazar
 * @author Luan Maoski
 */
@RunWith(Arquillian.class)
public class BlogsEntryIndexerReindexTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerMethodTestRule.INSTANCE,
			SynchronousDestinationTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		setUpUserSearchFixture();

		setUpBlogsEntryFixture();

		setUpBlogsEntryIndexerFixture();
	}

	@Test
	public void testReindexing() throws Exception {
		BlogsEntry blogsEntry = blogsEntryFixture.createBlogsEntry(
			RandomTestUtil.randomString());

		String searchTerm = blogsEntry.getTitle();

		blogsEntryIndexerFixture.searchOnlyOne(searchTerm);

		Document document = blogsEntryIndexerFixture.searchOnlyOne(searchTerm);

		blogsEntryIndexerFixture.deleteDocument(document);

		blogsEntryIndexerFixture.searchNoOne(searchTerm);

		blogsEntryIndexerFixture.reindex(blogsEntry.getCompanyId());

		blogsEntryIndexerFixture.searchOnlyOne(searchTerm);
	}

	protected void setUpBlogsEntryFixture() throws Exception {
		blogsEntryFixture = new BlogsEntryFixture(_group);

		_blogsEntries = blogsEntryFixture.getBlogsEntries();
	}

	protected void setUpBlogsEntryIndexerFixture() {
		blogsEntryIndexerFixture = new IndexerFixture<>(BlogsEntry.class);
	}

	protected void setUpUserSearchFixture() throws Exception {
		userSearchFixture = new UserSearchFixture();

		userSearchFixture.setUp();

		_group = userSearchFixture.addGroup();

		_groups = userSearchFixture.getGroups();

		_users = userSearchFixture.getUsers();
	}

	protected BlogsEntryFixture blogsEntryFixture;
	protected IndexerFixture<BlogsEntry> blogsEntryIndexerFixture;
	protected UserSearchFixture userSearchFixture;

	@DeleteAfterTestRun
	private List<BlogsEntry> _blogsEntries;

	private Group _group;

	@DeleteAfterTestRun
	private List<Group> _groups;

	@DeleteAfterTestRun
	private List<User> _users;

}