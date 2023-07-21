/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.internal.search;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.IndexerPostProcessor;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;

/**
 * @author Jesse Yeh
 */
@Component(
	immediate = true,
	property = "indexer.class.name=com.liferay.journal.model.JournalArticle",
	service = IndexerPostProcessor.class
)
public class JournalArticleIndexerPostProcessor
	implements IndexerPostProcessor {

	@Override
	public void postProcessContextBooleanFilter(
		BooleanFilter booleanFilter, SearchContext searchContext) {
	}

	@Override
	public void postProcessDocument(Document document, Object object) {
	}

	@Override
	public void postProcessFullQuery(
		BooleanQuery fullQuery, SearchContext searchContext) {
	}

	@Override
	public void postProcessSearchQuery(
		BooleanQuery searchQuery, BooleanFilter booleanFilter,
		SearchContext searchContext) {
	}

	@Override
	public void postProcessSummary(
		Summary summary, Document document, Locale locale, String snippet) {

		summary.setContent(
			HtmlUtil.unescape(
				StringUtil.replace(
					summary.getContent(), "<br />", StringPool.NEW_LINE)));
	}

}