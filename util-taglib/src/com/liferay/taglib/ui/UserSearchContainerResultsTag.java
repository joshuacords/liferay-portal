/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.taglib.ui;

import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.taglib.util.IncludeTag;

import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;

/**
 * @author Pei-Jung Lan
 */
public class UserSearchContainerResultsTag<R> extends IncludeTag {

	@Override
	public int doStartTag() throws JspException {
		SearchContainerTag<R> searchContainerTag =
			(SearchContainerTag<R>)findAncestorWithClass(
				this, SearchContainerTag.class);

		if (searchContainerTag == null) {
			throw new JspTagException("Requires liferay-ui:search-container");
		}

		return super.doStartTag();
	}

	public LinkedHashMap<String, Object> getUserParams() {
		return _userParams;
	}

	public void setUserParams(LinkedHashMap<String, Object> userParams) {
		_userParams = userParams;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_searchContainer = null;
		_searchTerms = null;
		_userParams = null;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest httpServletRequest) {
		SearchContainerTag<R> searchContainerTag =
			(SearchContainerTag<R>)findAncestorWithClass(
				this, SearchContainerTag.class);

		_searchContainer = searchContainerTag.getSearchContainer();

		_searchTerms = _searchContainer.getSearchTerms();

		httpServletRequest.setAttribute(
			"liferay-ui:user-search-container-results:searchContainer",
			_searchContainer);

		httpServletRequest.setAttribute(
			"liferay-ui:user-search-container-results:searchTerms",
			_searchTerms);
		httpServletRequest.setAttribute(
			"liferay-ui:user-search-container-results:userParams", _userParams);
	}

	private static final String _PAGE =
		"/html/taglib/ui/user_search_container_results/page.jsp";

	private SearchContainer<R> _searchContainer;
	private DisplayTerms _searchTerms;
	private LinkedHashMap<String, Object> _userParams;

}