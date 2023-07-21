/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.internal.expando;

import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.ExpandoQueryContributor;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.search.internal.indexer.KeywordQueryContributorsHolder;
import com.liferay.portal.search.spi.model.query.contributor.KeywordQueryContributor;
import com.liferay.portal.search.spi.model.query.contributor.helper.KeywordQueryContributorHelper;

import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author André de Oliveira
 */
@Component(immediate = true, service = ExpandoQueryContributor.class)
public class BaseIndexerExpandoQueryContributor
	implements ExpandoQueryContributor {

	@Override
	public void contribute(
		String keywords, BooleanQuery booleanQuery, String[] classNames,
		SearchContext searchContext) {

		Stream<KeywordQueryContributor> stream =
			keywordQueryContributorsHolder.getAll();

		stream.forEach(
			keywordQueryContributor -> keywordQueryContributor.contribute(
				searchContext.getKeywords(), booleanQuery,
				new KeywordQueryContributorHelper() {

					@Override
					public String getClassName() {
						return null;
					}

					@Override
					public Stream<String> getSearchClassNamesStream() {
						return Stream.of(classNames);
					}

					@Override
					public SearchContext getSearchContext() {
						return searchContext;
					}

				}));
	}

	@Reference
	protected KeywordQueryContributorsHolder keywordQueryContributorsHolder;

}