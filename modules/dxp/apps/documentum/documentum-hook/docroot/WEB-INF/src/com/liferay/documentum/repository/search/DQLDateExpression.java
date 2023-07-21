/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.documentum.repository.search;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;

/**
 * @author Mika Koivisto
 */
public class DQLDateExpression extends DQLSimpleExpression {

	public DQLDateExpression(
		String field, Date value,
		DQLSimpleExpressionOperator dqlSimpleExpressionOperator) {

		super(field, _format(value), dqlSimpleExpressionOperator);
	}

	@Override
	public String toQueryFragment() {
		StringBundler sb = new StringBundler(9);

		sb.append(getField());
		sb.append(StringPool.SPACE);
		sb.append(getDQLSimpleExpressionOperator());
		sb.append(StringPool.SPACE);
		sb.append("DATE('");
		sb.append(getValue());
		sb.append("', '");
		sb.append(_DATE_FORMAT_PATTERN);
		sb.append("')");

		return sb.toString();
	}

	private static String _format(Date value) {
		DateFormat dateFormat = new SimpleDateFormat(_DATE_FORMAT_PATTERN);

		return dateFormat.format(value);
	}

	private static final String _DATE_FORMAT_PATTERN = "yyyyMMddHHmmss";

}