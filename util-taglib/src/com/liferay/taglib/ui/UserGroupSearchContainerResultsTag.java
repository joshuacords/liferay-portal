/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.taglib.ui;

import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.taglib.util.IncludeTag;

import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Drew Brokke
 */
public class UserGroupSearchContainerResultsTag<R> extends IncludeTag {

	public DisplayTerms getSearchTerms() {
		return _searchTerms;
	}

	public LinkedHashMap<String, Object> getUserGroupParams() {
		return _userGroupParams;
	}

	public void setSearchTerms(DisplayTerms searchTerms) {
		_searchTerms = searchTerms;
	}

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public void setUseIndexer(boolean useIndexer) {
	}

	public void setUserGroupParams(
		LinkedHashMap<String, Object> userGroupParams) {

		_userGroupParams = userGroupParams;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_searchTerms = null;
		_userGroupParams = null;
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

		httpServletRequest.setAttribute(
			"liferay-ui:user-group-search-container-results:searchContainer",
			searchContainerTag.getSearchContainer());

		httpServletRequest.setAttribute(
			"liferay-ui:user-group-search-container-results:searchTerms",
			_searchTerms);
		httpServletRequest.setAttribute(
			"liferay-ui:user-group-search-container-results:userGroupParams",
			_userGroupParams);
	}

	private static final String _PAGE =
		"/html/taglib/ui/user_group_search_container_results/page.jsp";

	private DisplayTerms _searchTerms;
	private LinkedHashMap<String, Object> _userGroupParams;

}