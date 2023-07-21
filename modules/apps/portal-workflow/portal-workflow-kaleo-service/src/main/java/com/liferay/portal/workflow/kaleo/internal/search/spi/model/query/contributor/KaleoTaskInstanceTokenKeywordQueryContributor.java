/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.internal.search.spi.model.query.contributor;

import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.query.QueryHelper;
import com.liferay.portal.search.spi.model.query.contributor.KeywordQueryContributor;
import com.liferay.portal.search.spi.model.query.contributor.helper.KeywordQueryContributorHelper;
import com.liferay.portal.workflow.kaleo.internal.search.KaleoTaskInstanceTokenField;
import com.liferay.portal.workflow.kaleo.service.persistence.KaleoTaskInstanceTokenQuery;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Rafael Praxedes
 */
@Component(
	immediate = true,
	property = "indexer.class.name=com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken",
	service = KeywordQueryContributor.class
)
public class KaleoTaskInstanceTokenKeywordQueryContributor
	implements KeywordQueryContributor {

	@Override
	public void contribute(
		String keywords, BooleanQuery booleanQuery,
		KeywordQueryContributorHelper keywordQueryContributorHelper) {

		KaleoTaskInstanceTokenQuery kaleoTaskInstanceTokenQuery =
			getKaleoTaskInstanceTokenQuery(keywordQueryContributorHelper);

		if (kaleoTaskInstanceTokenQuery == null) {
			return;
		}

		appendAssetTitleTerm(
			booleanQuery, kaleoTaskInstanceTokenQuery.getAssetTitle(),
			keywordQueryContributorHelper);
		appendAssetTypeTerm(
			booleanQuery, kaleoTaskInstanceTokenQuery.getAssetTypes(),
			keywordQueryContributorHelper);
		appendTaskNameTerm(
			booleanQuery, kaleoTaskInstanceTokenQuery.getTaskName(),
			keywordQueryContributorHelper);
	}

	protected void appendAssetTitleTerm(
		BooleanQuery booleanQuery, String assetTitle,
		KeywordQueryContributorHelper keywordQueryContributorHelper) {

		if (Validator.isNull(assetTitle)) {
			return;
		}

		SearchContext searchContext =
			keywordQueryContributorHelper.getSearchContext();

		String assetTitleLocalizedName = LocalizationUtil.getLocalizedName(
			KaleoTaskInstanceTokenField.ASSET_TITLE,
			searchContext.getLanguageId());

		searchContext.setAttribute(assetTitleLocalizedName, assetTitle);

		queryHelper.addSearchLocalizedTerm(
			booleanQuery, searchContext,
			KaleoTaskInstanceTokenField.ASSET_TITLE, false);
	}

	protected void appendAssetTypeTerm(
		BooleanQuery booleanQuery, String[] assetTypes,
		KeywordQueryContributorHelper keywordQueryContributorHelper) {

		if (ArrayUtil.isEmpty(assetTypes)) {
			return;
		}

		SearchContext searchContext =
			keywordQueryContributorHelper.getSearchContext();

		searchContext.setAttribute(
			KaleoTaskInstanceTokenField.CLASS_NAME, assetTypes);

		queryHelper.addSearchTerm(
			booleanQuery, searchContext, KaleoTaskInstanceTokenField.CLASS_NAME,
			false);
	}

	protected void appendTaskNameTerm(
		BooleanQuery booleanQuery, String taskName,
		KeywordQueryContributorHelper keywordQueryContributorHelper) {

		if (Validator.isNull(taskName)) {
			return;
		}

		SearchContext searchContext =
			keywordQueryContributorHelper.getSearchContext();

		searchContext.setAttribute(
			KaleoTaskInstanceTokenField.TASK_NAME, taskName);

		queryHelper.addSearchTerm(
			booleanQuery, searchContext, KaleoTaskInstanceTokenField.TASK_NAME,
			false);
	}

	protected KaleoTaskInstanceTokenQuery getKaleoTaskInstanceTokenQuery(
		KeywordQueryContributorHelper keywordQueryContributorHelper) {

		SearchContext searchContext =
			keywordQueryContributorHelper.getSearchContext();

		return (KaleoTaskInstanceTokenQuery)searchContext.getAttribute(
			"kaleoTaskInstanceTokenQuery");
	}

	@Reference
	protected QueryHelper queryHelper;

}