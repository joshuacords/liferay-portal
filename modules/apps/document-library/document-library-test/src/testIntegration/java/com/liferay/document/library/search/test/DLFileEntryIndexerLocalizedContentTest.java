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
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistry;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.SearchContextTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.io.File;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Stream;

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
	}

	@Test
	public void testCase1() throws Exception {
		_testCasesCounts("坂下", LocaleUtil.JAPAN, 1);
	}

	@Test
	public void testCase2() throws Exception {
		_testCasesCounts("下坂", LocaleUtil.JAPAN, 1);
	}

	@Test
	public void testCase3() throws Exception {
		_testCasesCounts("欢迎", LocaleUtil.CHINA, 1);
	}

	@Test
	public void testCase4() throws Exception {
		_testCasesCounts("迎欢", LocaleUtil.CHINA, 0);
	}

	@Test
	public void testCase5_1() throws Exception {
		_testCasesCounts("下坂", LocaleUtil.US, 2);
	}

	@Test
	public void testCase5_2() throws Exception {
		_testCasesCounts("坂下", LocaleUtil.CHINA, 2);
	}

	@Test
	public void testCase6_1() throws Exception {
		_testCasesCounts("迎欢", LocaleUtil.US, 1);
	}

	@Test
	public void testCase6_2() throws Exception {
		_testCasesCounts("欢迎", LocaleUtil.JAPAN, 1);
	}

	@Test
	public void testCase7_1() throws Exception {
		_testCasesCounts("hello world", LocaleUtil.US, 1);
	}

	@Test
	public void testCase7_2() throws Exception {
		_testCasesCounts("hello world", LocaleUtil.JAPAN, 1);
	}

	@Test
	public void testJapaneseContent() throws Exception {
		GroupTestUtil.updateDisplaySettings(
			_group.getGroupId(), null, LocaleUtil.JAPAN);

		addFileEntry("content_search.txt");

		List<String> contentStrings = new ArrayList<>(
			Collections.singletonList("content_ja_JP"));

		String word1 = "新規";
		String word2 = "作成";

		Stream.of(
			word1, word2
		).forEach(
			searchTerm -> {
				Document document = _search(searchTerm, LocaleUtil.JAPAN);

				assertLocalization(contentStrings, document);
			}
		);
	}

	@Test
	public void testJapaneseContentFullWordOnly() throws Exception {
		GroupTestUtil.updateDisplaySettings(
			_group.getGroupId(), null, LocaleUtil.JAPAN);

		addFileEntry("japanese_1.txt");
		addFileEntry("japanese_2.txt");
		addFileEntry("japanese_3.txt");

		List<String> contentStrings = new ArrayList<>(
			Collections.singletonList("content_ja_JP"));

		String word1 = "新規";
		String word2 = "作成";

		Stream.of(
			word1, word2
		).forEach(
			searchTerm -> {
				Document document = _search(searchTerm, LocaleUtil.JAPAN);

				assertLocalization(contentStrings, document);
			}
		);
	}

	@Test
	public void testSiteLocale() throws Exception {
		Group testGroup = GroupTestUtil.addGroup();

		List<String> japaneseContentStrings = new ArrayList<>(
			Collections.singletonList("content_ja_JP"));
		List<String> englishContentStrings = new ArrayList<>(
			Collections.singletonList("content_en_US"));

		try {
			GroupTestUtil.updateDisplaySettings(
				_group.getGroupId(), null, LocaleUtil.JAPAN);
			GroupTestUtil.updateDisplaySettings(
				testGroup.getGroupId(), null, LocaleUtil.US);

			addFileEntry("locale_ja.txt", _group.getGroupId());
			addFileEntry("locale_en.txt", testGroup.getGroupId());

			Document japenseDocument = _search(
				"新規", LocaleUtil.JAPAN, _group.getGroupId());

			assertLocalization(japaneseContentStrings, japenseDocument);

			Document englishDocument = _search(
				"Locale Test", LocaleUtil.ENGLISH, testGroup.getGroupId());

			assertLocalization(englishContentStrings, englishDocument);
		}
		finally {
			groupLocalService.deleteGroup(testGroup);
		}
	}

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

	private Indexer<DLFileEntry> _indexer;

}