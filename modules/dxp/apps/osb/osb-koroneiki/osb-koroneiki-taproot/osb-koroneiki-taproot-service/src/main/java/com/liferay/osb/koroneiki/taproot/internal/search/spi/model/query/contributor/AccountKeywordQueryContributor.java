/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.internal.search.spi.model.query.contributor;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.generic.WildcardQueryImpl;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
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
	property = "indexer.class.name=com.liferay.osb.koroneiki.taproot.model.Account",
	service = KeywordQueryContributor.class
)
public class AccountKeywordQueryContributor implements KeywordQueryContributor {

	@Override
	public void contribute(
		String keywords, BooleanQuery booleanQuery,
		KeywordQueryContributorHelper keywordQueryContributorHelper) {

		SearchContext searchContext =
			keywordQueryContributorHelper.getSearchContext();

		queryHelper.addSearchTerm(booleanQuery, searchContext, "code", false);
		queryHelper.addSearchTerm(
			booleanQuery, searchContext, "contactEmailAddress", false);
		queryHelper.addSearchTerm(
			booleanQuery, searchContext, "contactUuids", false);
		queryHelper.addSearchTerm(
			booleanQuery, searchContext, "description", false);
		queryHelper.addSearchTerm(
			booleanQuery, searchContext, "externalLinkDomains", false);
		queryHelper.addSearchTerm(
			booleanQuery, searchContext, "externalLinkEntityIds", false);
		queryHelper.addSearchTerm(
			booleanQuery, searchContext, "externalLinkEntityNames", false);
		queryHelper.addSearchTerm(booleanQuery, searchContext, "name", false);
		queryHelper.addSearchTerm(booleanQuery, searchContext, "notes", false);
		queryHelper.addSearchTerm(
			booleanQuery, searchContext, "phoneNumber", false);
		queryHelper.addSearchTerm(
			booleanQuery, searchContext, "productEntryKeys", false);
		queryHelper.addSearchTerm(
			booleanQuery, searchContext, "profileEmailAddress", false);
		queryHelper.addSearchTerm(
			booleanQuery, searchContext, "website", false);

		if (Validator.isNotNull(keywords)) {
			QueryConfig queryConfig = searchContext.getQueryConfig();

			queryConfig.setScoreEnabled(true);

			_addSearchTerm(booleanQuery, "code", keywords);
			_addSearchTerm(booleanQuery, "name", keywords);
			_addWildcardQuery(booleanQuery, "code", keywords);
			_addWildcardQuery(booleanQuery, "externalLinkEntityIds", keywords);
			_addWildcardQuery(booleanQuery, "name", keywords);
			_addWildcardQuery(
				booleanQuery, "productPurchaseExternalLinkEntityIds", keywords);
		}
	}

	@Reference
	protected QueryHelper queryHelper;

	private void _addSearchTerm(
		BooleanQuery searchQuery, String field, String value) {

		try {
			Query query = searchQuery.addTerm(field, value);

			query.setBoost(4.0F);
		}
		catch (ParseException parseException) {
			throw new SystemException(parseException);
		}
	}

	private void _addWildcardQuery(
		BooleanQuery searchQuery, String field, String value) {

		WildcardQueryImpl wildcardQueryImpl = new WildcardQueryImpl(
			field,
			StringBundler.concat(
				StringPool.STAR, StringUtil.toLowerCase(value),
				StringPool.STAR));

		wildcardQueryImpl.setBoost(3.0F);

		try {
			searchQuery.add(wildcardQueryImpl, BooleanClauseOccur.SHOULD);
		}
		catch (ParseException parseException) {
			throw new SystemException(parseException);
		}
	}

}