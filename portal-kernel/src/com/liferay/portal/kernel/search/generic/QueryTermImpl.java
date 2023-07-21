/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.search.generic;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.search.QueryTerm;

/**
 * @author Michael C. Han
 */
public class QueryTermImpl implements QueryTerm {

	public QueryTermImpl(String field, String value) {
		_field = field;
		_value = value;
	}

	@Override
	public String getField() {
		return _field;
	}

	@Override
	public String getValue() {
		return _value;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(5);

		sb.append("{field=");
		sb.append(_field);
		sb.append(", value=");
		sb.append(_value);
		sb.append("}");

		return sb.toString();
	}

	private final String _field;
	private final String _value;

}