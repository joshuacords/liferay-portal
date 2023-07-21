/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.license.internal.search.spi.model.query.contributor;

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
	property = "indexer.class.name=com.liferay.osb.provisioning.license.model.LicenseKey",
	service = KeywordQueryContributor.class
)
public class LicenseKeyKeywordQueryContributor
	implements KeywordQueryContributor {

	@Override
	public void contribute(
		String keywords, BooleanQuery booleanQuery,
		KeywordQueryContributorHelper keywordQueryContributorHelper) {

		SearchContext searchContext =
			keywordQueryContributorHelper.getSearchContext();

		queryHelper.addSearchTerm(
			booleanQuery, searchContext, "accountCode", false);
		queryHelper.addSearchTerm(
			booleanQuery, searchContext, "accountKey", false);
		queryHelper.addSearchTerm(
			booleanQuery, searchContext, "accountName", false);
		queryHelper.addSearchTerm(
			booleanQuery, searchContext, "description", false);
		queryHelper.addSearchTerm(
			booleanQuery, searchContext, "hostName", false);
		queryHelper.addSearchTerm(
			booleanQuery, searchContext, "ipAddresses", false);
		queryHelper.addSearchTerm(booleanQuery, searchContext, "key", false);
		queryHelper.addSearchTerm(
			booleanQuery, searchContext, "licenseEntryId", false);
		queryHelper.addSearchTerm(
			booleanQuery, searchContext, "macAddresses", false);
		queryHelper.addSearchTerm(
			booleanQuery, searchContext, "modifiedUserUuid", false);
		queryHelper.addSearchTerm(booleanQuery, searchContext, "owner", false);
		queryHelper.addSearchTerm(
			booleanQuery, searchContext, "productKey", false);
		queryHelper.addSearchTerm(
			booleanQuery, searchContext, "productName", false);
		queryHelper.addSearchTerm(
			booleanQuery, searchContext, "productPurchaseKey", false);
		queryHelper.addSearchTerm(
			booleanQuery, searchContext, "productVersion", false);
		queryHelper.addSearchTerm(
			booleanQuery, searchContext, "serverId", false);
		queryHelper.addSearchTerm(
			booleanQuery, searchContext, "userUuid", false);
	}

	@Reference
	protected QueryHelper queryHelper;

}