/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wiki.display.context;

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.servlet.taglib.ui.Menu;
import com.liferay.wiki.model.WikiPage;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Iván Zaera
 * @author Roberto Díaz
 */
public class BaseWikiListPagesDisplayContext
	extends BaseWikiDisplayContext<WikiListPagesDisplayContext>
	implements WikiListPagesDisplayContext {

	public BaseWikiListPagesDisplayContext(
		UUID uuid, WikiListPagesDisplayContext parentDisplayContext,
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse) {

		super(
			uuid, parentDisplayContext, httpServletRequest,
			httpServletResponse);
	}

	@Override
	public String getEmptyResultsMessage() {
		return parentDisplayContext.getEmptyResultsMessage();
	}

	@Override
	public Menu getMenu(WikiPage wikiPage) throws PortalException {
		return parentDisplayContext.getMenu(wikiPage);
	}

	@Override
	public void populateResultsAndTotal(SearchContainer searchContainer)
		throws PortalException {

		parentDisplayContext.populateResultsAndTotal(searchContainer);
	}

}