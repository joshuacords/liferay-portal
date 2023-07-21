/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.delivery.resource.v1_0.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.headless.delivery.client.dto.v1_0.ContentElement;
import com.liferay.headless.delivery.client.pagination.Page;
import com.liferay.headless.delivery.client.pagination.Pagination;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.test.util.JournalTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Javier Gamarra
 */
@RunWith(Arquillian.class)
public class ContentElementResourceTest
	extends BaseContentElementResourceTestCase {

	@Override
	@Test
	public void testGetSiteContentElementsPageWithSortString()
		throws Exception {

		ContentElement contentElement1 = _toContentElement(
			JournalTestUtil.addArticle(
				testGroup.getGroupId(), "a" + RandomTestUtil.randomString(),
				RandomTestUtil.randomString()));
		ContentElement contentElement2 = _toContentElement(
			JournalTestUtil.addArticle(
				testGroup.getGroupId(), "b" + RandomTestUtil.randomString(),
				RandomTestUtil.randomString()));

		Page<ContentElement> ascPage =
			contentElementResource.getSiteContentElementsPage(
				testGroup.getGroupId(), null, null, null, Pagination.of(1, 2),
				"title:asc");

		assertEquals(
			Arrays.asList(contentElement1, contentElement2),
			(List<ContentElement>)ascPage.getItems());

		Page<ContentElement> descPage =
			contentElementResource.getSiteContentElementsPage(
				testGroup.getGroupId(), null, null, null, Pagination.of(1, 2),
				"title:desc");

		assertEquals(
			Arrays.asList(contentElement2, contentElement1),
			(List<ContentElement>)descPage.getItems());
	}

	@Override
	protected String[] getAdditionalAssertFieldNames() {
		return new String[] {"title"};
	}

	@Override
	protected String[] getIgnoredEntityFieldNames() {
		return new String[] {
			"contentType", "creatorId", "dateCreated", "dateModified"
		};
	}

	@Override
	protected ContentElement testGetSiteContentElementsPage_addContentElement(
			Long siteId, ContentElement contentElement)
		throws Exception {

		return _getContentElement(siteId);
	}

	@Override
	protected ContentElement testGraphQLContentElement_addContentElement()
		throws Exception {

		return _getContentElement(testGroup.getGroupId());
	}

	private ContentElement _getContentElement(long groupId) throws Exception {
		return _toContentElement(JournalTestUtil.addArticle(groupId, 0));
	}

	private ContentElement _toContentElement(JournalArticle journalArticle) {
		return new ContentElement() {
			{
				id = journalArticle.getId();
				title = journalArticle.getTitle();
			}
		};
	}

}