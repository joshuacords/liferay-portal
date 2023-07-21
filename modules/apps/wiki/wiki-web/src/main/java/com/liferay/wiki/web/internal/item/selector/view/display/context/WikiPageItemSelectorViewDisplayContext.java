/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wiki.web.internal.item.selector.view.display.context;

import com.liferay.item.selector.ItemSelectorReturnTypeResolverHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.wiki.item.selector.criterion.WikiPageItemSelectorCriterion;
import com.liferay.wiki.model.WikiNode;
import com.liferay.wiki.model.WikiPage;
import com.liferay.wiki.service.WikiNodeLocalService;
import com.liferay.wiki.web.internal.item.selector.resolver.WikiPageItemSelectorReturnTypeResolver;
import com.liferay.wiki.web.internal.item.selector.view.WikiPageItemSelectorView;

import java.util.Locale;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Roberto Díaz
 */
public class WikiPageItemSelectorViewDisplayContext {

	public WikiPageItemSelectorViewDisplayContext(
		WikiPageItemSelectorCriterion wikiPageItemSelectorCriterion,
		WikiPageItemSelectorView wikiAttachmentItemSelectorView,
		WikiNodeLocalService wikiNodeLocalService,
		ItemSelectorReturnTypeResolverHandler
			itemSelectorReturnTypeResolverHandler,
		String itemSelectedEventName, boolean search, PortletURL portletURL) {

		_wikiPageItemSelectorCriterion = wikiPageItemSelectorCriterion;
		_wikiNodeLocalService = wikiNodeLocalService;
		_itemSelectorReturnTypeResolverHandler =
			itemSelectorReturnTypeResolverHandler;
		_itemSelectedEventName = itemSelectedEventName;
		_search = search;
		_portletURL = portletURL;

		_wikiPageItemSelectorView = wikiAttachmentItemSelectorView;
	}

	public String getItemSelectedEventName() {
		return _itemSelectedEventName;
	}

	public WikiNode getNode() throws PortalException {
		return _wikiNodeLocalService.getNode(
			_wikiPageItemSelectorCriterion.getNodeId());
	}

	public PortletURL getPortletURL(
			HttpServletRequest httpServletRequest,
			LiferayPortletResponse liferayPortletResponse)
		throws PortletException {

		PortletURL portletURL = PortletURLUtil.clone(
			_portletURL, liferayPortletResponse);

		portletURL.setParameter(
			"selectedTab", getTitle(httpServletRequest.getLocale()));

		return portletURL;
	}

	public int getStatus() throws PortalException {
		return _wikiPageItemSelectorCriterion.getStatus();
	}

	public String getTitle(Locale locale) {
		return _wikiPageItemSelectorView.getTitle(locale);
	}

	public WikiPageItemSelectorCriterion getWikiPageItemSelectorCriterion() {
		return _wikiPageItemSelectorCriterion;
	}

	public WikiPageItemSelectorReturnTypeResolver
		getWikiPageItemSelectorReturnTypeResolver() {

		return (WikiPageItemSelectorReturnTypeResolver)
			_itemSelectorReturnTypeResolverHandler.
				getItemSelectorReturnTypeResolver(
					_wikiPageItemSelectorCriterion, _wikiPageItemSelectorView,
					WikiPage.class);
	}

	public boolean isSearch() {
		return _search;
	}

	private final String _itemSelectedEventName;
	private final ItemSelectorReturnTypeResolverHandler
		_itemSelectorReturnTypeResolverHandler;
	private final PortletURL _portletURL;
	private final boolean _search;
	private final WikiNodeLocalService _wikiNodeLocalService;
	private final WikiPageItemSelectorCriterion _wikiPageItemSelectorCriterion;
	private final WikiPageItemSelectorView _wikiPageItemSelectorView;

}