/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.spi.model.query.contributor.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.blogs.model.BlogsEntry;
import com.liferay.blogs.service.BlogsEntryLocalService;
import com.liferay.blogs.test.util.search.BlogsEntryBlueprint.BlogsEntryBlueprintBuilder;
import com.liferay.blogs.test.util.search.BlogsEntrySearchFixture;
import com.liferay.petra.function.UnsafeSupplierValue;
import com.liferay.petra.lang.SafeCloseable;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.preview.PreviewableResolverUtil;
import com.liferay.portal.search.searcher.SearchRequestBuilderFactory;
import com.liferay.portal.search.searcher.SearchResponse;
import com.liferay.portal.search.searcher.Searcher;
import com.liferay.portal.search.test.rule.SearchTestRule;
import com.liferay.portal.search.test.util.DocumentsAssert;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.users.admin.test.util.search.GroupBlueprint;
import com.liferay.users.admin.test.util.search.GroupSearchFixture;

import java.io.Serializable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Joshua Cords
 */
@RunWith(Arquillian.class)
public class WorkflowStatusModelPreFilterContributorTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		BlogsEntrySearchFixture blogsEntrySearchFixture =
			new BlogsEntrySearchFixture(_blogsEntryLocalService);
		GroupSearchFixture groupSearchFixture = new GroupSearchFixture();

		_blogsEntries = blogsEntrySearchFixture.getBlogsEntries();
		_blogsEntrySearchFixture = blogsEntrySearchFixture;
		_group = groupSearchFixture.addGroup(new GroupBlueprint());
		_groups = groupSearchFixture.getGroups();
	}

	@Test
	public void testContribute() throws Exception {
		BlogsEntry liveBlogsEntry = _addBlogsEntry("Gamma Live");

		BlogsEntry draftBlogsEntry = _addBlogsEntry("Gamma Draft");

		_blogsEntryLocalService.updateStatus(
			TestPropsValues.getUserId(), draftBlogsEntry.getEntryId(),
			WorkflowConstants.STATUS_DRAFT,
			ServiceContextTestUtil.getServiceContext(_group.getGroupId()),
			new HashMap<>());

		_assertSearch("[Gamma Live]", null);
		_assertSearch(
			"[Gamma Draft]",
			HashMapBuilder.<Serializable, Object>put(
				liveBlogsEntry.getEntryId(), draftBlogsEntry.getEntryId()
			).build());
		_assertSearch(
			"[Gamma Draft]",
			HashMapBuilder.<Serializable, Object>put(
				liveBlogsEntry.getEntryId(),
				new UnsafeSupplierValue<>(() -> draftBlogsEntry)
			).build());
	}

	@Rule
	public SearchTestRule searchTestRule = new SearchTestRule();

	private BlogsEntry _addBlogsEntry(String title) throws Exception {
		return _blogsEntrySearchFixture.addBlogsEntry(
			BlogsEntryBlueprintBuilder.builder(
			).content(
				RandomTestUtil.randomString()
			).groupId(
				_group.getGroupId()
			).title(
				title
			).userId(
				TestPropsValues.getUserId()
			).build());
	}

	private void _assertSearch(
		String expected, Map<Serializable, Object> previewValues) {

		Long previewId = null;

		if (previewValues != null) {
			previewId = PreviewableResolverUtil.addPreviewableMap(
				HashMapBuilder.<Class<?>, Map<Serializable, Object>>put(
					BlogsEntry.class, previewValues
				).build());
		}

		try (SafeCloseable safeCloseable =
				PreviewableResolverUtil.setPreviewIdWithSafeCloseable(
					previewId)) {

			SearchResponse searchResponse = _searcher.search(
				_searchRequestBuilderFactory.builder(
				).companyId(
					_group.getCompanyId()
				).fields(
					StringPool.STAR
				).groupIds(
					_group.getGroupId()
				).modelIndexerClasses(
					BlogsEntry.class
				).queryString(
					"gamma"
				).build());

			DocumentsAssert.assertValuesIgnoreRelevance(
				searchResponse.getRequestString(),
				searchResponse.getDocuments(), _TITLE_EN_US, expected);
		}
		finally {
			if (previewId != null) {
				PreviewableResolverUtil.removePreviewableMap(previewId);
			}
		}
	}

	private static final String _TITLE_EN_US = StringBundler.concat(
		Field.TITLE, StringPool.UNDERLINE, LocaleUtil.US);

	@DeleteAfterTestRun
	private List<BlogsEntry> _blogsEntries;

	@Inject
	private BlogsEntryLocalService _blogsEntryLocalService;

	private BlogsEntrySearchFixture _blogsEntrySearchFixture;
	private Group _group;

	@DeleteAfterTestRun
	private List<Group> _groups;

	@Inject
	private SearchRequestBuilderFactory _searchRequestBuilderFactory;

	@Inject
	private Searcher _searcher;

}