/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wiki.web.internal.display.context;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.wiki.display.context.WikiPageInfoPanelDisplayContext;
import com.liferay.wiki.model.WikiNode;
import com.liferay.wiki.model.WikiPage;
import com.liferay.wiki.service.WikiPageLocalServiceUtil;
import com.liferay.wiki.web.internal.display.context.util.WikiPageInfoPanelRequestHelper;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Adolfo Pérez
 */
public class DefaultWikiPageInfoPanelDisplayContext
	implements WikiPageInfoPanelDisplayContext {

	public DefaultWikiPageInfoPanelDisplayContext(
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse) {

		_wikiPageInfoPanelRequestHelper = new WikiPageInfoPanelRequestHelper(
			httpServletRequest);
	}

	@Override
	public WikiPage getFirstPage() {
		List<WikiPage> pages = _wikiPageInfoPanelRequestHelper.getPages();

		if (pages.isEmpty()) {
			return _wikiPageInfoPanelRequestHelper.getPage();
		}

		return pages.get(0);
	}

	@Override
	public String getPageRSSURL(WikiPage page) {
		ThemeDisplay themeDisplay =
			_wikiPageInfoPanelRequestHelper.getThemeDisplay();

		StringBundler sb = new StringBundler(5);

		sb.append(themeDisplay.getPathMain());
		sb.append("/wiki/rss?nodeId=");
		sb.append(page.getNodeId());
		sb.append("&title=");
		sb.append(page.getTitle());

		return sb.toString();
	}

	@Override
	public int getPagesCount() {
		WikiNode node = _wikiPageInfoPanelRequestHelper.getCurrentNode();

		return WikiPageLocalServiceUtil.getPagesCount(node.getNodeId(), true);
	}

	@Override
	public int getSelectedPagesCount() {
		List<?> items = getSelectedPages();

		return items.size();
	}

	@Override
	public UUID getUuid() {
		return _UUID;
	}

	@Override
	public boolean isMultiplePageSelection() {
		List<?> items = getSelectedPages();

		if (items.size() > 1) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isShowSidebarHeader() {
		return _wikiPageInfoPanelRequestHelper.isShowSidebarHeader();
	}

	@Override
	public boolean isSinglePageSelection() {
		List<WikiPage> pages = _wikiPageInfoPanelRequestHelper.getPages();

		if (pages.size() == 1) {
			return true;
		}

		WikiPage page = _wikiPageInfoPanelRequestHelper.getPage();

		if (page != null) {
			return true;
		}

		return false;
	}

	protected List<WikiPage> getSelectedPages() {
		return _wikiPageInfoPanelRequestHelper.getPages();
	}

	private static final UUID _UUID = UUID.fromString(
		"7099F1F8-ED73-47D8-9CDC-ED292BF7779F");

	private final WikiPageInfoPanelRequestHelper
		_wikiPageInfoPanelRequestHelper;

}