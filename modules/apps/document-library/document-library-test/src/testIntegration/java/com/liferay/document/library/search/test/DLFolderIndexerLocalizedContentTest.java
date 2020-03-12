/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.document.library.search.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFileEntryConstants;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.test.util.search.FileEntrySearchFixture;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistry;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.highlight.HighlightUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.SearchContextTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.search.localization.SearchLocalizationHelper;
import com.liferay.portal.search.test.util.SummaryFixture;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.io.File;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Stream;

import com.liferay.users.admin.test.util.search.UserSearchFixture;
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
public class DLFolderIndexerLocalizedContentTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
//		fileEntrySearchFixture = new FileEntrySearchFixture(dlAppLocalService);
//
//		fileEntrySearchFixture.setUp();

		_group = GroupTestUtil.addGroup();

		_indexer = IndexerRegistryUtil.getIndexer(DLFolder.class);

		UserTestUtil.setUser(TestPropsValues.getUser());

		CompanyThreadLocal.setCompanyId(TestPropsValues.getCompanyId());

		_addTestFolders();

		_userSearchFixture = new UserSearchFixture();

		_userSearchFixture.setUp();

		User user = _userSearchFixture.addUser(
			RandomTestUtil.randomString(), _group);

		_summaryFixture = new SummaryFixture<>(
			DLFolder.class, _group, LocaleUtil.US, user);
	}

	@Test
	public void testCase1() throws Exception {
		Locale searchLocale = LocaleUtil.JAPAN;

		List<Document> docs =
			_testCasesCounts("坂下", searchLocale, 1);

		String expectedTitle = "[[坂下]]";

		Document doc = docs.get(0);

		_assertHighlight(expectedTitle, "Folder A", searchLocale, doc);
	}

	@Test
	public void testCase2() throws Exception {
		Locale searchLocale = LocaleUtil.JAPAN;

		List<Document> docs =
			_testCasesCounts("下坂", searchLocale, 1);

		String expectedTitle = "[[下坂]]";

		Document doc = docs.get(0);

		_assertHighlight(expectedTitle, "Folder B", searchLocale, doc);
	}

	@Test
	public void testCase3() throws Exception {
		Locale searchLocale = LocaleUtil.CHINA;

		List<Document> docs =
			_testCasesCounts("欢迎", searchLocale, 1);

		String expectedTitle = "[[欢迎]]光临";

		Document doc = docs.get(0);

		_assertHighlight(expectedTitle, "Folder C", searchLocale, doc);
	}

	@Test
	public void testCase4() throws Exception {
		_testCasesCounts("迎欢", LocaleUtil.CHINA, 0);
	}

	@Test
	public void testCase5_1() throws Exception {
		Locale searchLocale = LocaleUtil.US;

		List<Document> docs =
			_testCasesCounts("下坂", searchLocale, 2);

		String expectedTitle = "[[下]][[坂]]";

		Document doc = docs.get(0);

		_assertHighlight(expectedTitle, _test2Content, searchLocale, doc);

		expectedTitle = "[[坂]][[下]]";

		doc = docs.get(1);

		_assertHighlight(expectedTitle, _test1Content, searchLocale, doc);
	}

	@Test
	public void testCase5_2() throws Exception {
		Locale searchLocale = LocaleUtil.CHINA;

		List<Document> docs =
			_testCasesCounts("坂下", searchLocale, 1);

		String expectedTitle = "[[坂下]]";

		Document doc = docs.get(0);

		_assertHighlight(expectedTitle, "Folder A", searchLocale, doc);
	}

	@Test
	public void testCase6_1() throws Exception {
		Locale searchLocale = LocaleUtil.US;

		List<Document> docs =
			_testCasesCounts("迎欢", searchLocale, 1);

		String expectedTitle = "[[欢]][[迎]]光临";

		Document doc = docs.get(0);

		_assertHighlight(expectedTitle, _test2Content, searchLocale, doc);
	}

	@Test
	public void testCase6_2() throws Exception {
		Locale searchLocale = LocaleUtil.JAPAN;

		List<Document> docs =
			_testCasesCounts("欢迎", searchLocale, 1);

		String expectedTitle = "[[欢]][[迎]]光临";

		Document doc = docs.get(0);

		_assertHighlight(expectedTitle, "Folder C", searchLocale, doc);
	}

	@Test
	public void testCase7_1() throws Exception {
		Locale searchLocale = LocaleUtil.US;

		List<Document> docs =
			_testCasesCounts("hello world", searchLocale, 1);

		String expectedTitle = "[[hello]] [[world]]";

		Document doc = docs.get(0);

		_assertHighlight(expectedTitle, _test2Content, searchLocale, doc);
	}

	@Test
	public void testCase7_2() throws Exception {
		Locale searchLocale = LocaleUtil.JAPAN;

		List<Document> docs =
			_testCasesCounts("hello world", searchLocale, 1);

		String expectedTitle = "[[hello]] [[world]]";

		Document doc = docs.get(0);

		_assertHighlight(expectedTitle, "Folder D", searchLocale, doc);
	}

	private void _assertHighlight(
		String title, String content, Locale locale, Document document)
		throws Exception {

		String expectedTitle = StringUtil.replace(
			title, _highlightBrackets, _highlightTags);

		_summaryFixture.assertSummary(expectedTitle, content, locale, document);
	}


	protected void addFolder(long groupId, String title, String description)
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(groupId);

		DLAppLocalServiceUtil.addFolder(
			serviceContext.getUserId(), serviceContext.getScopeGroupId(),
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, title, description,
			serviceContext
		);
	}

//	@Inject
//	protected DLAppLocalService dlAppLocalService;

//	protected FileEntrySearchFixture fileEntrySearchFixture;

//	@Inject
//	protected GroupLocalService groupLocalService;

	@Inject
	protected IndexerRegistry indexerRegistry;

	private void _addTestFolders() throws Exception {
		addFolder(_group.getGroupId(), "坂下", "Folder A");
		addFolder(_group.getGroupId(), "下坂", "Folder B");
		addFolder(_group.getGroupId(), "欢迎光临", "Folder C");
		addFolder(_group.getGroupId(), "hello world","Folder D");
	}

	private SearchContext _getSearchContext(
		String searchTerm, Locale locale, long groupId)
		throws Exception {

		SearchContext searchContext = SearchContextTestUtil.getSearchContext(
			groupId);

		searchContext.setKeywords(searchTerm);
		searchContext.setLocale(locale);

		QueryConfig queryConfig = searchContext.getQueryConfig();

		queryConfig.setSelectedFieldNames(StringPool.STAR);

		return searchContext;
	}

	private Document _getSingleDocument(String searchTerm, Hits hits) {
		List<Document> documents = hits.toList();

		if (documents.size() == 1) {
			return documents.get(0);
		}

		throw new AssertionError(searchTerm + "->" + documents);
	}

	private Document _search(String searchTerm, Locale locale) {
		return _search(searchTerm, locale, _group.getGroupId());
	}

	private Document _search(String searchTerm, Locale locale, long groupId) {
		try {
			SearchContext searchContext = _getSearchContext(
				searchTerm, locale, groupId);

			Indexer indexer = indexerRegistry.getIndexer(
				DLFolderConstants.getClassName());

			Hits hits = indexer.search(searchContext);

			return _getSingleDocument(searchTerm, hits);
		}
		catch (RuntimeException runtimeException) {
			throw runtimeException;
		}
		catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	private List<Document> _testCasesCounts(
		String searchTerm, Locale searchLocale, int expected)
		throws Exception {

		GroupTestUtil.updateDisplaySettings(
			_group.getGroupId(), null, searchLocale);

		try {
			SearchContext searchContext = _getSearchContext(
				searchTerm, searchLocale, _group.getGroupId());

			QueryConfig queryConfig = searchContext.getQueryConfig();

			queryConfig.setHighlightEnabled(true);

			String[] localizedFieldNames =
				searchLocalizationHelper.getLocalizedFieldNames(
					new String[] {Field.CONTENT, Field.DESCRIPTION, Field.TITLE},
					searchContext);

			queryConfig.addHighlightFieldNames(localizedFieldNames);

			Hits hits = _indexer.search(searchContext);

			List<Document> documents = hits.toList();

			Assert.assertEquals(
				documents.toString(), expected, documents.size());

			return documents;
		}
		catch (RuntimeException runtimeException) {
			throw runtimeException;
		}
		catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	@DeleteAfterTestRun
	private Group _group;

	@Inject
	protected SearchLocalizationHelper searchLocalizationHelper;

	private Indexer<DLFolder> _indexer;

	private SummaryFixture<DLFolder> _summaryFixture;
	private UserSearchFixture _userSearchFixture;

	private String _test1Content = "いろはにおえどちりぬるを";
	private String _test2Content = "行く川のながれは絶えずして、しかも本の水にあらず。";

	private final String[] _highlightTags = new String[] {"<liferay-hl>", "</liferay-hl>"};
	private final String[] _highlightBrackets = new String[] {"[[", "]]"};

}