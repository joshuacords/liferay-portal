/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wiki.display.context;

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.servlet.taglib.ui.Menu;
import com.liferay.wiki.model.WikiPage;

/**
 * @author Iván Zaera
 * @author Roberto Díaz
 */
public interface WikiListPagesDisplayContext extends WikiDisplayContext {

	public String getEmptyResultsMessage();

	public Menu getMenu(WikiPage wikiPage) throws PortalException;

	public void populateResultsAndTotal(SearchContainer searchContainer)
		throws PortalException;

}