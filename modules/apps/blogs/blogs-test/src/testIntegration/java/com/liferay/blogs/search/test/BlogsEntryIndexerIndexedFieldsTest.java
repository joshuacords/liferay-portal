/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.blogs.search.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.blogs.model.BlogsEntry;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchEngineHelper;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.rule.SynchronousDestinationTestRule;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.search.searcher.SearchRequestBuilderFactory;
import com.liferay.portal.search.searcher.Searcher;
import com.liferay.portal.search.test.util.FieldValuesAssert;
import com.liferay.portal.search.test.util.IndexedFieldsFixture;
import com.liferay.portal.search.test.util.IndexerFixture;
import com.liferay.portal.search.test.util.SearchTestRule;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;
import com.liferay.users.admin.test.util.search.UserSearchFixture;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Luan Maoski
 */
@RunWith(Arquillian.class)
public class BlogsEntryIndexerIndexedFieldsTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerMethodTestRule.INSTANCE,
			SynchronousDestinationTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		_setUpUserSearchFixture();

		_setUpBlogsEntryFixture();

		_setUpBlogsEntryIndexerFixture();

		_setUpIndexedFieldsFixture();
	}

	@Test
	public void testIndexedFields() throws Exception {
		Locale locale = LocaleUtil.JAPAN;

		String searchTerm = "新規";

		String title = "新規作成";

		blogsEntryFixture.updateDisplaySettings(locale);

		BlogsEntry blogsEntry = blogsEntryFixture.createBlogsEntry(title);

		assertFieldValues(_expectedFieldValues(blogsEntry), searchTerm, locale);
	}

	@Rule
	public SearchTestRule searchTestRule = new SearchTestRule();

	protected void assertFieldValues(
		Map<String, ?> map, String searchTerm, Locale locale) {

		FieldValuesAssert.assertFieldValues(
			map, name -> !name.equals("score"),
			searcher.search(
				searchRequestBuilderFactory.builder(
				).companyId(
					_group.getCompanyId()
				).fields(
					StringPool.STAR
				).groupIds(
					_group.getGroupId()
				).locale(
					locale
				).modelIndexerClasses(
					MODEL_INDEXER_CLASS
				).queryString(
					searchTerm
				).fetchSource(
					true
				).build()));
	}

	protected static final Class<?> MODEL_INDEXER_CLASS = BlogsEntry.class;

	protected BlogsEntryFixture blogsEntryFixture;
	protected IndexerFixture<BlogsEntry> blogsEntryIndexerFixture;
	protected IndexedFieldsFixture indexedFieldsFixture;

	@Inject
	protected ResourcePermissionLocalService resourcePermissionLocalService;

	@Inject
	protected SearchEngineHelper searchEngineHelper;

	@Inject
	protected Searcher searcher;

	@Inject
	protected SearchRequestBuilderFactory searchRequestBuilderFactory;

	protected UserSearchFixture userSearchFixture;

	private Map<String, String> _expectedFieldValues(BlogsEntry blogsEntry)
		throws Exception {

		Map<String, String> map = HashMapBuilder.put(
			Field.COMPANY_ID, String.valueOf(blogsEntry.getCompanyId())
		).put(
			Field.CONTENT, blogsEntry.getContent()
		).put(
			Field.DESCRIPTION, blogsEntry.getDescription()
		).put(
			Field.ENTRY_CLASS_NAME, BlogsEntry.class.getName()
		).put(
			Field.ENTRY_CLASS_PK, String.valueOf(blogsEntry.getEntryId())
		).put(
			Field.GROUP_ID, String.valueOf(blogsEntry.getGroupId())
		).put(
			Field.SCOPE_GROUP_ID, String.valueOf(blogsEntry.getGroupId())
		).put(
			Field.STAGING_GROUP, String.valueOf(_group.isStagingGroup())
		).put(
			Field.STATUS, String.valueOf(blogsEntry.getStatus())
		).put(
			Field.SUBTITLE, blogsEntry.getSubtitle()
		).put(
			Field.TITLE, blogsEntry.getTitle()
		).put(
			Field.USER_ID, String.valueOf(blogsEntry.getUserId())
		).put(
			Field.USER_NAME, StringUtil.lowerCase(blogsEntry.getUserName())
		).put(
			"localized_title", StringUtil.lowerCase(blogsEntry.getTitle())
		).put(
			"title_sortable", StringUtil.lowerCase(blogsEntry.getTitle())
		).put(
			"visible", "true"
		).build();

		indexedFieldsFixture.populateUID(
			BlogsEntry.class.getName(), blogsEntry.getEntryId(), map);
		indexedFieldsFixture.populatePriority("0.0", map);
		indexedFieldsFixture.populateViewCount(
			BlogsEntry.class, blogsEntry.getEntryId(), map);

		_populateContent(blogsEntry, map);
		_populateDates(blogsEntry, map);
		_populateRoles(blogsEntry, map);
		_populateTitle(blogsEntry, map);

		return map;
	}

	private void _populateContent(
		BlogsEntry blogsEntry, Map<String, String> map) {

		for (Locale locale :
				LanguageUtil.getAvailableLocales(blogsEntry.getGroupId())) {

			map.put(
				"content_" + LocaleUtil.toLanguageId(locale),
				HtmlUtil.extractText(blogsEntry.getContent()));
		}
	}

	private void _populateDates(
		BlogsEntry blogsEntry, Map<String, String> map) {

		indexedFieldsFixture.populateDate(
			Field.CREATE_DATE, blogsEntry.getCreateDate(), map);
		indexedFieldsFixture.populateDate(
			Field.DISPLAY_DATE, blogsEntry.getDisplayDate(), map);
		indexedFieldsFixture.populateDate(
			Field.MODIFIED_DATE, blogsEntry.getModifiedDate(), map);
		indexedFieldsFixture.populateDate(
			Field.PUBLISH_DATE, blogsEntry.getDisplayDate(), map);
		indexedFieldsFixture.populateExpirationDateWithForever(map);
	}

	private void _populateRoles(BlogsEntry blogsEntry, Map<String, String> map)
		throws Exception {

		indexedFieldsFixture.populateRoleIdFields(
			blogsEntry.getCompanyId(), BlogsEntry.class.getName(),
			blogsEntry.getEntryId(), blogsEntry.getGroupId(), null, map);
	}

	private void _populateTitle(
		BlogsEntry blogsEntry, Map<String, String> map) {

		for (Locale locale :
				LanguageUtil.getAvailableLocales(blogsEntry.getGroupId())) {

			String title = StringUtil.lowerCase(blogsEntry.getTitle());

			String languageId = LocaleUtil.toLanguageId(locale);

			String key = "title_" + languageId;

			map.put(key, title);
			map.put("localized_" + key, title);
			map.put("localized_" + key + "_sortable", title);
		}
	}

	private void _setUpBlogsEntryFixture() {
		blogsEntryFixture = new BlogsEntryFixture(_group);

		_blogsEntries = blogsEntryFixture.getBlogsEntries();
	}

	private void _setUpBlogsEntryIndexerFixture() {
		blogsEntryIndexerFixture = new IndexerFixture<>(BlogsEntry.class);
	}

	private void _setUpIndexedFieldsFixture() {
		indexedFieldsFixture = new IndexedFieldsFixture(
			resourcePermissionLocalService, searchEngineHelper);
	}

	private void _setUpUserSearchFixture() throws Exception {
		userSearchFixture = new UserSearchFixture();

		userSearchFixture.setUp();

		_group = userSearchFixture.addGroup();

		_groups = userSearchFixture.getGroups();

		_users = userSearchFixture.getUsers();
	}

	@DeleteAfterTestRun
	private List<BlogsEntry> _blogsEntries;

	private Group _group;

	@DeleteAfterTestRun
	private List<Group> _groups;

	@DeleteAfterTestRun
	private List<User> _users;

}