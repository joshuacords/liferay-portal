/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.documentum.repository.search;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

/**
 * @author Mika Koivisto
 */
public class DQLSimpleExpression implements DQLCriterion {

	public DQLSimpleExpression(
		String field, String value,
		DQLSimpleExpressionOperator dqlSimpleExpressionOperator) {

		_field = field;
		_value = value;
		_dqlSimpleExpressionOperator = dqlSimpleExpressionOperator;
	}

	public DQLSimpleExpressionOperator getDQLSimpleExpressionOperator() {
		return _dqlSimpleExpressionOperator;
	}

	public String getField() {
		return _field;
	}

	public String getValue() {
		return _value;
	}

	@Override
	public String toQueryFragment() {
		StringBundler sb = new StringBundler(7);

		sb.append(_field);
		sb.append(StringPool.SPACE);
		sb.append(_dqlSimpleExpressionOperator);
		sb.append(StringPool.SPACE);
		sb.append(StringPool.APOSTROPHE);
		sb.append(_value);
		sb.append(StringPool.APOSTROPHE);

		return sb.toString();
	}

	private final DQLSimpleExpressionOperator _dqlSimpleExpressionOperator;
	private final String _field;
	private final String _value;

}