/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.similar.results.web.internal.contributor.wiki;

import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.portal.search.similar.results.web.internal.util.SearchStringUtil;
import com.liferay.portal.search.similar.results.web.internal.util.http.HttpHelper;
import com.liferay.portal.search.similar.results.web.spi.contributor.SimilarResultsContributor;
import com.liferay.portal.search.similar.results.web.spi.contributor.helper.RouteBuilder;
import com.liferay.portal.search.similar.results.web.spi.contributor.helper.RouteHelper;
import com.liferay.wiki.service.WikiNodeLocalService;
import com.liferay.wiki.service.WikiPageLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Wade Cao
 * @author André de Oliveira
 */
@Component(service = SimilarResultsContributor.class)
public class WikiSimilarResultsContributor
	extends BaseWikiSimilarResultsContributor {

	@Override
	public void detectRoute(
		RouteBuilder routeBuilder, RouteHelper routeHelper) {

		String[] parameters = _httpHelper.getFriendlyURLParameters(
			routeHelper.getURLString());

		SearchStringUtil.requireEquals("wiki", parameters[0]);

		routeBuilder.addAttribute(
			"nodeName", parameters[1]
		).addAttribute(
			"title", parameters[2]
		);
	}

	@Reference(unbind = "-")
	public void setAssetEntryLocalService(
		AssetEntryLocalService assetEntryLocalService) {

		super.setAssetEntryLocalService(assetEntryLocalService);
	}

	@Reference(unbind = "-")
	public void setHttpHelper(HttpHelper httpHelper) {
		_httpHelper = httpHelper;
	}

	@Reference(unbind = "-")
	public void setWikiNodeLocalService(
		WikiNodeLocalService wikiNodeLocalService) {

		super.setWikiNodeLocalService(wikiNodeLocalService);
	}

	@Reference(unbind = "-")
	public void setWikiPageLocalService(
		WikiPageLocalService wikiPageLocalService) {

		super.setWikiPageLocalService(wikiPageLocalService);
	}

	private HttpHelper _httpHelper;

}