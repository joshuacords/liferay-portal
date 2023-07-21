/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.license.util.comparator;

import com.liferay.osb.provisioning.license.model.LicenseKey;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Amos Fong
 */
public class LicenseKeyStartDateComparator extends OrderByComparator {

	public static final String ORDER_BY_ASC = "startDate ASC";

	public static final String ORDER_BY_DESC = "startDate DESC";

	public static final String[] ORDER_BY_FIELDS = {"startDate"};

	public LicenseKeyStartDateComparator() {
		this(false);
	}

	public LicenseKeyStartDateComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		LicenseKey licenseKey1 = (LicenseKey)obj1;
		LicenseKey licenseKey2 = (LicenseKey)obj2;

		int value = DateUtil.compareTo(
			licenseKey1.getStartDate(), licenseKey2.getStartDate());

		if (_ascending) {
			return value;
		}

		return -value;
	}

	@Override
	public String getOrderBy() {
		if (_ascending) {
			return ORDER_BY_ASC;
		}

		return ORDER_BY_DESC;
	}

	@Override
	public String[] getOrderByFields() {
		return ORDER_BY_FIELDS;
	}

	@Override
	public boolean isAscending() {
		return _ascending;
	}

	private boolean _ascending;

}