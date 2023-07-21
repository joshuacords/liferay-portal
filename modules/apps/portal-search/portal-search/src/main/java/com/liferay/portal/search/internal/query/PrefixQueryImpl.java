/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.internal.query;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.search.query.PrefixQuery;
import com.liferay.portal.search.query.QueryVisitor;

/**
 * @author Michael C. Han
 */
public class PrefixQueryImpl extends BaseQueryImpl implements PrefixQuery {

	public PrefixQueryImpl(String field, String prefix) {
		_field = field;
		_prefix = prefix;
	}

	@Override
	public <T> T accept(QueryVisitor<T> queryVisitor) {
		return queryVisitor.visit(this);
	}

	public String getField() {
		return _field;
	}

	public String getPrefix() {
		return _prefix;
	}

	public String getRewrite() {
		return _rewrite;
	}

	public void setRewrite(String rewrite) {
		_rewrite = rewrite;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{(");
		sb.append(_field);
		sb.append("=");
		sb.append(_prefix);
		sb.append(", _rewrite=");
		sb.append(_rewrite);
		sb.append("), ");
		sb.append(super.toString());
		sb.append("}");

		return sb.toString();
	}

	private static final long serialVersionUID = 1L;

	private final String _field;
	private final String _prefix;
	private String _rewrite;

}