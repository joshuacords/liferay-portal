/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.taglib.clay.servlet.taglib.model.table;

/**
 * @author Iván Zaera Avellón
 */
public enum SortingOrder {

	ASCENDING("asc"), DESCENDING("desc");

	public String getValue() {
		return _value;
	}

	private SortingOrder(String value) {
		_value = value;
	}

	private final String _value;

}