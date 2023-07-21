/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.zendesk.web.service.internal.search;

import com.liferay.osb.provisioning.zendesk.web.service.search.Query;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Amos Fong
 */
public class QueryImpl implements Query {

	public void addCriterion(String criterion) {
		_criteria.add(criterion);
	}

	public void addParameter(String key, String value) {
		_parameters.put(key, value);
	}

	public int getPage() {
		return _page;
	}

	public Map<String, String> getParameters() {
		Map<String, String> parameters = new HashMap<>();

		if (!_sideloads.isEmpty()) {
			parameters.put("include", StringUtil.merge(_sideloads));
		}

		if (_page > 0) {
			parameters.put("page", String.valueOf(_page));
		}

		if (!_parameters.isEmpty()) {
			parameters.putAll(_parameters);
		}

		if (!_criteria.isEmpty()) {
			parameters.put("query", getQuery());
		}

		if (Validator.isNotNull(_sortBy)) {
			parameters.put("sort_by", _sortBy);

			if (Validator.isNotNull(_sortOrder)) {
				parameters.put("sort_order", _sortOrder);
			}
		}

		return parameters;
	}

	public void setPage(int page) {
		_page = page;
	}

	public void setSortBy(String sortBy) {
		_sortBy = sortBy;
	}

	public void setSortOrder(boolean asc) {
		if (asc) {
			_sortOrder = "asc";
		}
		else {
			_sortOrder = "desc";
		}
	}

	protected String getQuery() {
		StringBundler sb = new StringBundler();

		for (String criterion : _criteria) {
			if (sb.index() != 0) {
				sb.append(StringPool.SPACE);
			}

			sb.append(criterion);
		}

		return sb.toString();
	}

	private final Set<String> _criteria = new HashSet<>();
	private int _page;
	private final Map<String, String> _parameters = new HashMap<>();
	private final Set<String> _sideloads = new HashSet<>();
	private String _sortBy;
	private String _sortOrder;

}