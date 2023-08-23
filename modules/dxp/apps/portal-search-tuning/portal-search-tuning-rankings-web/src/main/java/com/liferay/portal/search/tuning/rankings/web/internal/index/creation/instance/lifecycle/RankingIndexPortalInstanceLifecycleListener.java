/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.tuning.rankings.web.internal.index.creation.instance.lifecycle;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.search.capabilities.SearchCapabilities;
import com.liferay.portal.search.spi.model.index.contributor.IndexContributor;
import com.liferay.portal.search.tuning.rankings.web.internal.index.RankingIndexCreator;
import com.liferay.portal.search.tuning.rankings.web.internal.index.RankingIndexReader;
import com.liferay.portal.search.tuning.rankings.web.internal.index.name.RankingIndexName;
import com.liferay.portal.search.tuning.rankings.web.internal.index.name.RankingIndexNameBuilder;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Petteri Karttunen
 */
@Component(service = IndexContributor.class)
public class RankingIndexPortalInstanceLifecycleListener
	implements IndexContributor {

	@Override
	public void onAfterCreate(long companyId, String companyIndexName) {
		if (!_searchCapabilities.isResultRankingsSupported()) {
			return;
		}

		if (companyId == CompanyConstants.SYSTEM) {
			return;
		}

		RankingIndexName rankingIndexName =
			_rankingIndexNameBuilder.getRankingIndexName(companyId);

		if (_rankingIndexReader.isExists(rankingIndexName)) {
			return;
		}

		_rankingIndexCreator.create(rankingIndexName);
	}

//	@Override
//	public void portalInstanceUnregistered(Company company) throws Exception {
//		if (!_searchCapabilities.isResultRankingsSupported()) {
//			return;
//		}
//
//		RankingIndexName rankingIndexName =
//			_rankingIndexNameBuilder.getRankingIndexName(
//				company.getCompanyId());
//
//		if (!_rankingIndexReader.isExists(rankingIndexName)) {
//			return;
//		}
//
//		_rankingIndexCreator.delete(rankingIndexName);
//	}

	protected static final String RANKINGS_INDEX_NAME_SUFFIX =
		"search-tuning-rankings";

	@Reference
	private RankingIndexCreator _rankingIndexCreator;

	@Reference
	private RankingIndexNameBuilder _rankingIndexNameBuilder;

	@Reference
	private RankingIndexReader _rankingIndexReader;

	@Reference
	private SearchCapabilities _searchCapabilities;

}