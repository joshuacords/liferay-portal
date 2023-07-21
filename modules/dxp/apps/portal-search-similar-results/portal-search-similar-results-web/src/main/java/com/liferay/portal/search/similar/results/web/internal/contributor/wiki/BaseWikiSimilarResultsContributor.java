/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.similar.results.web.internal.contributor.wiki;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.search.similar.results.web.spi.contributor.SimilarResultsContributor;
import com.liferay.portal.search.similar.results.web.spi.contributor.helper.CriteriaBuilder;
import com.liferay.portal.search.similar.results.web.spi.contributor.helper.CriteriaHelper;
import com.liferay.portal.search.similar.results.web.spi.contributor.helper.DestinationBuilder;
import com.liferay.portal.search.similar.results.web.spi.contributor.helper.DestinationHelper;
import com.liferay.portal.search.similar.results.web.spi.contributor.helper.RouteBuilder;
import com.liferay.portal.search.similar.results.web.spi.contributor.helper.RouteHelper;
import com.liferay.wiki.model.WikiNode;
import com.liferay.wiki.model.WikiPage;
import com.liferay.wiki.service.WikiNodeLocalService;
import com.liferay.wiki.service.WikiPageLocalService;

/**
 * @author Wade Cao
 * @author André de Oliveira
 */
public abstract class BaseWikiSimilarResultsContributor
	implements SimilarResultsContributor {

	@Override
	public abstract void detectRoute(
		RouteBuilder routeBuilder, RouteHelper routeHelper);

	@Override
	public void resolveCriteria(
		CriteriaBuilder criteriaBuilder, CriteriaHelper criteriaHelper) {

		long groupId = criteriaHelper.getGroupId();

		String nodeName = (String)criteriaHelper.getRouteParameter("nodeName");

		WikiNode wikiNode = _wikiNodeLocalService.fetchNode(groupId, nodeName);

		if (wikiNode == null) {
			return;
		}

		String title = (String)criteriaHelper.getRouteParameter("title");

		WikiPage wikiPage = _wikiPageLocalService.fetchPage(
			wikiNode.getNodeId(), title, 1.0);

		if (wikiPage == null) {
			return;
		}

		AssetEntry assetEntry = _assetEntryLocalService.fetchEntry(
			groupId, wikiPage.getUuid());

		if (assetEntry == null) {
			return;
		}

		criteriaBuilder.type(
			assetEntry.getClassName()
		).uid(
			Field.getUID(
				assetEntry.getClassName(),
				String.valueOf(assetEntry.getClassPK()))
		);
	}

	public void setAssetEntryLocalService(
		AssetEntryLocalService assetEntryLocalService) {

		_assetEntryLocalService = assetEntryLocalService;
	}

	public void setWikiNodeLocalService(
		WikiNodeLocalService wikiNodeLocalService) {

		_wikiNodeLocalService = wikiNodeLocalService;
	}

	public void setWikiPageLocalService(
		WikiPageLocalService wikiPageLocalService) {

		_wikiPageLocalService = wikiPageLocalService;
	}

	@Override
	public void writeDestination(
		DestinationBuilder destinationBuilder,
		DestinationHelper destinationHelper) {

		String className = destinationHelper.getClassName();

		if (className.equals(WikiPage.class.getName())) {
			String nodeName = (String)destinationHelper.getRouteParameter(
				"nodeName");
			String title = (String)destinationHelper.getRouteParameter("title");

			AssetRenderer<?> assetRenderer =
				destinationHelper.getAssetRenderer();

			WikiPage wikiPage = (WikiPage)assetRenderer.getAssetObject();

			WikiNode wikiNode = wikiPage.getNode();

			destinationBuilder.replace(
				nodeName, wikiNode.getName()
			).replace(
				title, wikiPage.getTitle()
			);
		}
	}

	private AssetEntryLocalService _assetEntryLocalService;
	private WikiNodeLocalService _wikiNodeLocalService;
	private WikiPageLocalService _wikiPageLocalService;

}