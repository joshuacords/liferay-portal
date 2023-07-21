/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wiki.web.internal.item.selector.view.display.context;

import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.item.selector.ItemSelectorReturnTypeResolver;
import com.liferay.item.selector.ItemSelectorReturnTypeResolverHandler;
import com.liferay.item.selector.taglib.servlet.taglib.util.RepositoryEntryBrowserTagUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProviderUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.wiki.configuration.WikiFileUploadConfiguration;
import com.liferay.wiki.constants.WikiPortletKeys;
import com.liferay.wiki.item.selector.criterion.WikiAttachmentItemSelectorCriterion;
import com.liferay.wiki.model.WikiPage;
import com.liferay.wiki.service.WikiPageLocalServiceUtil;
import com.liferay.wiki.web.internal.item.selector.view.WikiAttachmentItemSelectorView;

import java.util.Locale;

import javax.portlet.ActionRequest;
import javax.portlet.PortletException;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Roberto Díaz
 */
public class WikiAttachmentItemSelectorViewDisplayContext {

	public WikiAttachmentItemSelectorViewDisplayContext(
		HttpServletRequest httpServletRequest, String itemSelectedEventName,
		ItemSelectorReturnTypeResolverHandler
			itemSelectorReturnTypeResolverHandler,
		PortletURL portletURL, boolean search,
		WikiAttachmentItemSelectorCriterion wikiAttachmentItemSelectorCriterion,
		WikiAttachmentItemSelectorView wikiAttachmentItemSelectorView) {

		_httpServletRequest = httpServletRequest;
		_itemSelectedEventName = itemSelectedEventName;
		_itemSelectorReturnTypeResolverHandler =
			itemSelectorReturnTypeResolverHandler;
		_portletURL = portletURL;
		_search = search;
		_wikiAttachmentItemSelectorCriterion =
			wikiAttachmentItemSelectorCriterion;
		_wikiAttachmentItemSelectorView = wikiAttachmentItemSelectorView;

		_portalPreferences = PortletPreferencesFactoryUtil.getPortalPreferences(
			_httpServletRequest);
	}

	public String getItemSelectedEventName() {
		return _itemSelectedEventName;
	}

	public ItemSelectorReturnTypeResolver getItemSelectorReturnTypeResolver() {
		return _itemSelectorReturnTypeResolverHandler.
			getItemSelectorReturnTypeResolver(
				_wikiAttachmentItemSelectorCriterion,
				_wikiAttachmentItemSelectorView, FileEntry.class);
	}

	public String[] getMimeTypes() throws ConfigurationException {
		String[] mimeTypes =
			_wikiAttachmentItemSelectorCriterion.getMimeTypes();

		if (mimeTypes != null) {
			return mimeTypes;
		}

		WikiFileUploadConfiguration wikiFileUploadConfiguration =
			_getWikiFileUploadsConfiguration();

		return wikiFileUploadConfiguration.attachmentMimeTypes();
	}

	public OrderByComparator<FileEntry> getOrderByComparator() {
		return DLUtil.getRepositoryModelOrderByComparator(
			RepositoryEntryBrowserTagUtil.getOrderByCol(
				_httpServletRequest, _portalPreferences),
			RepositoryEntryBrowserTagUtil.getOrderByType(
				_httpServletRequest, _portalPreferences));
	}

	public PortletURL getPortletURL(
			HttpServletRequest httpServletRequest,
			LiferayPortletResponse liferayPortletResponse)
		throws PortletException {

		PortletURL portletURL = PortletURLUtil.clone(
			_portletURL, liferayPortletResponse);

		portletURL.setParameter(
			"selectedTab",
			String.valueOf(getTitle(httpServletRequest.getLocale())));

		return portletURL;
	}

	public String getTitle(Locale locale) {
		return _wikiAttachmentItemSelectorView.getTitle(locale);
	}

	public PortletURL getUploadURL(
		LiferayPortletResponse liferayPortletResponse) {

		PortletURL portletURL = liferayPortletResponse.createActionURL(
			WikiPortletKeys.WIKI);

		portletURL.setParameter(
			ActionRequest.ACTION_NAME, "/wiki/upload_page_attachment");
		portletURL.setParameter(
			"mimeTypes", _wikiAttachmentItemSelectorCriterion.getMimeTypes());
		portletURL.setParameter(
			"resourcePrimKey",
			String.valueOf(
				_wikiAttachmentItemSelectorCriterion.getWikiPageResourceId()));

		return portletURL;
	}

	public WikiAttachmentItemSelectorCriterion
		getWikiAttachmentItemSelectorCriterion() {

		return _wikiAttachmentItemSelectorCriterion;
	}

	public long getWikiAttachmentMaxSize() throws ConfigurationException {
		WikiFileUploadConfiguration wikiFileUploadConfiguration =
			_getWikiFileUploadsConfiguration();

		return wikiFileUploadConfiguration.attachmentMaxSize();
	}

	public WikiPage getWikiPage() throws PortalException {
		return WikiPageLocalServiceUtil.getPage(
			_wikiAttachmentItemSelectorCriterion.getWikiPageResourceId());
	}

	public boolean isSearch() {
		return _search;
	}

	private WikiFileUploadConfiguration _getWikiFileUploadsConfiguration()
		throws ConfigurationException {

		if (_wikiFileUploadConfiguration == null) {
			_wikiFileUploadConfiguration =
				ConfigurationProviderUtil.getSystemConfiguration(
					WikiFileUploadConfiguration.class);
		}

		return _wikiFileUploadConfiguration;
	}

	private final HttpServletRequest _httpServletRequest;
	private final String _itemSelectedEventName;
	private final ItemSelectorReturnTypeResolverHandler
		_itemSelectorReturnTypeResolverHandler;
	private final PortalPreferences _portalPreferences;
	private final PortletURL _portletURL;
	private final boolean _search;
	private final WikiAttachmentItemSelectorCriterion
		_wikiAttachmentItemSelectorCriterion;
	private final WikiAttachmentItemSelectorView
		_wikiAttachmentItemSelectorView;
	private WikiFileUploadConfiguration _wikiFileUploadConfiguration;

}