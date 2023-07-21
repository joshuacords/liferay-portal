/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.documentum.repository.search;

/**
 * @author Mika Koivisto
 */
public enum DQLSimpleExpressionOperator {

	EQ("="), GE(">="), GT(">"), LE("<="), LIKE("LIKE"), LT("<"), NE("!=");

	@Override
	public String toString() {
		return _value;
	}

	private DQLSimpleExpressionOperator(String value) {
		_value = value;
	}

	private final String _value;

}