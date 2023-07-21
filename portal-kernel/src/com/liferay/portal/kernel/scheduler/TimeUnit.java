/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.scheduler;

/**
 * @author Brian Wing Shun Chan
 */
public enum TimeUnit {

	DAY("day"), HOUR("hour"), MILLISECOND("millisecond"), MINUTE("minute"),
	MONTH("month"), SECOND("second"), WEEK("week"), YEAR("year");

	public String getValue() {
		return _value;
	}

	@Override
	public String toString() {
		return _value;
	}

	private TimeUnit(String value) {
		_value = value;
	}

	private final String _value;

}