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
 * @author Dylan Rebelak
 */
@RunWith(Arquillian.class)
public class DLFileEntryIndexerLocalizedContentTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		fileEntrySearchFixture = new FileEntrySearchFixture(dlAppLocalService);

		fileEntrySearchFixture.setUp();

		_group = GroupTestUtil.addGroup();

		_indexer = IndexerRegistryUtil.getIndexer(DLFileEntry.class);

		UserTestUtil.setUser(TestPropsValues.getUser());

		CompanyThreadLocal.setCompanyId(TestPropsValues.getCompanyId());

		_addTestFiles();

		_userSearchFixture = new UserSearchFixture();

		_userSearchFixture.setUp();

		User user = _userSearchFixture.addUser(
			RandomTestUtil.randomString(), _group);

		_summaryFixture = new SummaryFixture<>(
			DLFileEntry.class, _group, LocaleUtil.US, user);
	}

	@Test
	public void testCase1() throws Exception {
		Locale searchLocale = LocaleUtil.JAPAN;

		List<Document> docs =
			_testCasesCounts("坂下", searchLocale, 1);

		String expectedTitle = "[[坂下]]";

		Document doc = docs.get(0);

		_assertHighlight(expectedTitle, "PDF A", searchLocale, doc);
	}

	@Test
	public void testCase2() throws Exception {
		Locale searchLocale = LocaleUtil.JAPAN;

		List<Document> docs =
			_testCasesCounts("下坂", searchLocale, 1);

		String expectedTitle = "[[下坂]]";

		Document doc = docs.get(0);

		_assertHighlight(expectedTitle, "PDF B", searchLocale, doc);
	}

	@Test
	public void testCase3() throws Exception {
		Locale searchLocale = LocaleUtil.CHINA;

		List<Document> docs =
			_testCasesCounts("欢迎", searchLocale, 1);

		String expectedTitle = "[[欢迎]]光临";

		Document doc = docs.get(0);

		_assertHighlight(expectedTitle, "PDF C", searchLocale, doc);
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

		//String expectedTitle = "[[下]] [[坂]]";
		String expectedTitle = "[[下]][[坂]]";

		Document doc = docs.get(0);

		_assertHighlight(expectedTitle, _test2Content, searchLocale, doc);

		//expectedTitle = "[[坂]] [[下]]";
		expectedTitle = "[[坂]][[下]]";

		doc = docs.get(1);

		_assertHighlight(expectedTitle, _test1Content, searchLocale, doc);
	}

	@Test
	public void testCase5_2() throws Exception {
		Locale searchLocale = LocaleUtil.CHINA;

		List<Document> docs =
			_testCasesCounts("坂下", searchLocale, 2);

		//String expectedTitle = "[[坂]] [[下]]";
		String expectedTitle = "[[坂]][[下]]";

		Document doc = docs.get(0);

		_assertHighlight(expectedTitle, "PDF A", searchLocale, doc);

		//expectedTitle = "[[下]] [[坂]]";
		expectedTitle = "[[下]][[坂]]";

		doc = docs.get(1);

		_assertHighlight(expectedTitle, "PDF B", searchLocale, doc);
	}

	@Test
	public void testCase6_1() throws Exception {
		Locale searchLocale = LocaleUtil.US;

		List<Document> docs =
			_testCasesCounts("迎欢", searchLocale, 1);

		//String expectedTitle = "[[欢]] [[迎]]光临";
		String expectedTitle = "[[欢]][[迎]]光临";

		Document doc = docs.get(0);

		_assertHighlight(expectedTitle, _test2Content, searchLocale, doc);
	}

	@Test
	public void testCase6_2() throws Exception {
		Locale searchLocale = LocaleUtil.JAPAN;

		List<Document> docs =
			_testCasesCounts("欢迎", searchLocale, 1);

		//String expectedTitle = "[[欢]] [迎]光临";
		String expectedTitle = "[[欢]][[迎]]光临";

		Document doc = docs.get(0);

		_assertHighlight(expectedTitle, "PDF C", searchLocale, doc);
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

		_assertHighlight(expectedTitle, "PDF D", searchLocale, doc);
	}

	private void _assertHighlight(
		String title, String content, Locale locale, Document document)
		throws Exception {

		String expectedTitle = StringUtil.replace(
			title, _highlightBrackets, _highlightTags);

		_summaryFixture.assertSummary(expectedTitle, content, locale, document);
	}


//	@Test
//	public void testJapaneseContent() throws Exception {
//		GroupTestUtil.updateDisplaySettings(
//			_group.getGroupId(), null, LocaleUtil.JAPAN);
//
//		addFileEntry("content_search.txt");
//
//		List<String> contentStrings = new ArrayList<>(
//			Collections.singletonList("content_ja_JP"));
//
//		String word1 = "新規";
//		String word2 = "作成";
//
//		Stream.of(
//			word1, word2
//		).forEach(
//			searchTerm -> {
//				Document document = _search(searchTerm, LocaleUtil.JAPAN);
//
//				assertLocalization(contentStrings, document);
//			}
//		);
//	}
//
//	@Test
//	public void testJapaneseContentFullWordOnly() throws Exception {
//		GroupTestUtil.updateDisplaySettings(
//			_group.getGroupId(), null, LocaleUtil.JAPAN);
//
//		addFileEntry("japanese_1.txt");
//		addFileEntry("japanese_2.txt");
//		addFileEntry("japanese_3.txt");
//
//		List<String> contentStrings = new ArrayList<>(
//			Collections.singletonList("content_ja_JP"));
//
//		String word1 = "新規";
//		String word2 = "作成";
//
//		Stream.of(
//			word1, word2
//		).forEach(
//			searchTerm -> {
//				Document document = _search(searchTerm, LocaleUtil.JAPAN);
//
//				assertLocalization(contentStrings, document);
//			}
//		);
//	}
//
//	@Test
//	public void testSiteLocale() throws Exception {
//		Group testGroup = GroupTestUtil.addGroup();
//
//		List<String> japaneseContentStrings = new ArrayList<>(
//			Collections.singletonList("content_ja_JP"));
//		List<String> englishContentStrings = new ArrayList<>(
//			Collections.singletonList("content_en_US"));
//
//		try {
//			GroupTestUtil.updateDisplaySettings(
//				_group.getGroupId(), null, LocaleUtil.JAPAN);
//			GroupTestUtil.updateDisplaySettings(
//				testGroup.getGroupId(), null, LocaleUtil.US);
//
//			addFileEntry("locale_ja.txt", _group.getGroupId());
//			addFileEntry("locale_en.txt", testGroup.getGroupId());
//
//			Document japenseDocument = _search(
//				"新規", LocaleUtil.JAPAN, _group.getGroupId());
//
//			assertLocalization(japaneseContentStrings, japenseDocument);
//
//			Document englishDocument = _search(
//				"Locale Test", LocaleUtil.ENGLISH, testGroup.getGroupId());
//
//			assertLocalization(englishContentStrings, englishDocument);
//		}
//		finally {
//			groupLocalService.deleteGroup(testGroup);
//		}
//	}

	protected FileEntry addFileEntry(String fileName) throws Exception {
		return addFileEntry(
			fileName, _group.getGroupId(), fileName, StringPool.BLANK);
	}

	protected FileEntry addFileEntry(String fileName, long groupId)
		throws Exception {

		return addFileEntry(fileName, groupId, fileName, StringPool.BLANK);
	}

	protected FileEntry addFileEntry(
			String fileName, long groupId, String title, String description)
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(groupId);

		File file = null;
		FileEntry fileEntry = null;

		try (InputStream inputStream =
				DLFileEntryIndexerLocalizedContentTest.class.
					getResourceAsStream("dependencies/" + fileName)) {

			String mimeType = MimeTypesUtil.getContentType(file, fileName);

			file = FileUtil.createTempFile(inputStream);

			fileEntry = DLAppLocalServiceUtil.addFileEntry(
				serviceContext.getUserId(), serviceContext.getScopeGroupId(),
				DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, fileName, mimeType,
				title, description, StringPool.BLANK, file, serviceContext);
		}
		finally {
			FileUtil.delete(file);
		}

		return fileEntry;
	}

	protected void assertLocalization(
		List<String> contentStrings, Document document) {

		List<String> fields = _getFieldValues("content", document);

		Assert.assertEquals(contentStrings.toString(), fields.toString());
	}

	@Inject
	protected DLAppLocalService dlAppLocalService;

	protected FileEntrySearchFixture fileEntrySearchFixture;

	@Inject
	protected GroupLocalService groupLocalService;

	@Inject
	protected IndexerRegistry indexerRegistry;

	private static List<String> _getFieldValues(
		String prefix, Document document) {

		List<String> filteredFields = new ArrayList<>();

		Map<String, Field> fields = document.getFields();

		for (String field : fields.keySet()) {
			if (field.contains(prefix)) {
				filteredFields.add(field);
			}
		}

		return filteredFields;
	}

	private void _addTestFiles() throws Exception {
		addFileEntry(
			"title_desc_test1.txt", _group.getGroupId(), "坂下", "PDF A");
		addFileEntry(
			"title_desc_test2.txt", _group.getGroupId(), "下坂", "PDF B");
		addFileEntry(
			"title_desc_test2.txt", _group.getGroupId(), "欢迎光临", "PDF C");
		addFileEntry(
			"title_desc_test2.txt", _group.getGroupId(), "hello world",
			"PDF D");
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
				DLFileEntryConstants.getClassName());

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
//			queryConfig.addHighlightFieldNames(fieldName);
//			queryConfig.addSelectedFieldNames(fieldName);

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

	private Indexer<DLFileEntry> _indexer;

	private SummaryFixture<DLFileEntry> _summaryFixture;
	private UserSearchFixture _userSearchFixture;

	private String _test1Content = "いろはにおえどちりぬるを";
	private String _test2Content = "行く川のながれは絶えずして、しかも本の水にあらず。";

	private final String[] _highlightTags = new String[] {"<liferay-hl>", "</liferay-hl>"};
	private final String[] _highlightBrackets = new String[] {"[[", "]]"};

}