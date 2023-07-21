/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wiki.display.context;

import com.liferay.portal.kernel.display.context.DisplayContextFactory;
import com.liferay.wiki.model.WikiNode;
import com.liferay.wiki.model.WikiPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Iván Zaera
 */
public interface WikiDisplayContextFactory extends DisplayContextFactory {

	public WikiEditPageDisplayContext getWikiEditPageDisplayContext(
		WikiEditPageDisplayContext parentWikiEditPageDisplayContext,
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse, WikiPage wikiPage);

	public WikiListPagesDisplayContext getWikiListPagesDisplayContext(
		WikiListPagesDisplayContext parentWikiListPagesDisplayContext,
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse, WikiNode wikiNode);

	public WikiNodeInfoPanelDisplayContext getWikiNodeInfoPanelDisplayContext(
		WikiNodeInfoPanelDisplayContext parentWikiNodeInfoPanelDisplayContext,
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse);

	public WikiPageInfoPanelDisplayContext getWikiPageInfoPanelDisplayContext(
		WikiPageInfoPanelDisplayContext parentWikiPageInfoPanelDisplayContext,
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse);

	public WikiViewPageDisplayContext getWikiViewPageDisplayContext(
		WikiViewPageDisplayContext parentWikiViewPageDisplayContext,
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse, WikiPage wikiPage);

}