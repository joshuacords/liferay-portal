/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.search.SearchContainer;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author     Shinn Lok
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public class SearchContainerReference {

	public SearchContainerReference(
		HttpServletRequest httpServletRequest, String namespace) {

		_namespace = namespace;

		httpServletRequest.setAttribute(
			WebKeys.SEARCH_CONTAINER_REFERENCE, this);
	}

	public String getId(HttpServletRequest httpServletRequest) {
		return getId(httpServletRequest, SearchContainer.DEFAULT_VAR);
	}

	public String getId(HttpServletRequest httpServletRequest, String var) {
		if (_searchContainers == null) {
			return StringPool.BLANK;
		}

		SearchContainer<?> searchContainer = _searchContainers.get(var);

		if (searchContainer == null) {
			return StringPool.BLANK;
		}

		return searchContainer.getId(httpServletRequest, _namespace);
	}

	public void register(SearchContainer<?> searchContainer) {
		register(SearchContainer.DEFAULT_VAR, searchContainer);
	}

	public void register(String var, SearchContainer<?> searchContainer) {
		if (_searchContainers == null) {
			_searchContainers = new HashMap<>();
		}

		_searchContainers.put(var, searchContainer);
	}

	private final String _namespace;
	private Map<String, SearchContainer<?>> _searchContainers;

}