/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mobile.device.rules.util.comparator;

import com.liferay.mobile.device.rules.model.MDRAction;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Jürgen Kappler
 */
public class ActionCreateDateComparator extends OrderByComparator<MDRAction> {

	public static final String ORDER_BY_ASC = "MDRAction.createDate ASC";

	public static final String ORDER_BY_DESC = "MDRAction.createDate DESC";

	public static final String[] ORDER_BY_FIELDS = {"createDate"};

	public ActionCreateDateComparator() {
		this(true);
	}

	public ActionCreateDateComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(MDRAction mdrAction1, MDRAction mdrAction2) {
		int value = DateUtil.compareTo(
			mdrAction1.getCreateDate(), mdrAction2.getCreateDate());

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

	private final boolean _ascending;

}