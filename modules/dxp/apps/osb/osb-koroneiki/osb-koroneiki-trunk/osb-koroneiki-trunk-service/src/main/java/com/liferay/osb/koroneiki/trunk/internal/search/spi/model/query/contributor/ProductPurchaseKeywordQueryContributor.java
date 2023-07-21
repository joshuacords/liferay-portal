/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.internal.search.spi.model.query.contributor;

import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.search.query.QueryHelper;
import com.liferay.portal.search.spi.model.query.contributor.KeywordQueryContributor;
import com.liferay.portal.search.spi.model.query.contributor.helper.KeywordQueryContributorHelper;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true,
	property = "indexer.class.name=com.liferay.osb.koroneiki.trunk.model.ProductPurchase",
	service = KeywordQueryContributor.class
)
public class ProductPurchaseKeywordQueryContributor
	implements KeywordQueryContributor {

	@Override
	public void contribute(
		String keywords, BooleanQuery booleanQuery,
		KeywordQueryContributorHelper keywordQueryContributorHelper) {

		SearchContext searchContext =
			keywordQueryContributorHelper.getSearchContext();

		queryHelper.addSearchTerm(
			booleanQuery, searchContext, "accountKey", false);
		queryHelper.addSearchTerm(
			booleanQuery, searchContext, "externalLinkDomains", false);
		queryHelper.addSearchTerm(
			booleanQuery, searchContext, "externalLinkEntityIds", false);
		queryHelper.addSearchTerm(
			booleanQuery, searchContext, "externalLinkEntityNames", false);
		queryHelper.addSearchTerm(booleanQuery, searchContext, "name", false);
		queryHelper.addSearchTerm(
			booleanQuery, searchContext, "productEntryKey", false);
	}

	@Reference
	protected QueryHelper queryHelper;

}