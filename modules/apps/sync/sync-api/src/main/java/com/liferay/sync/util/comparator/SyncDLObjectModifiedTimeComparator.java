/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.util.comparator;

import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.sync.model.SyncDLObject;

/**
 * @author Shinn Lok
 */
public class SyncDLObjectModifiedTimeComparator
	extends OrderByComparator<SyncDLObject> {

	public static final String ORDER_BY_ASC = "SyncDLObject.modifiedTime ASC";

	public static final String ORDER_BY_DESC = "SyncDLObject.modifiedTime DESC";

	public static final String[] ORDER_BY_FIELDS = {"modifiedTime"};

	public SyncDLObjectModifiedTimeComparator() {
		this(true);
	}

	public SyncDLObjectModifiedTimeComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(SyncDLObject syncDLObject1, SyncDLObject syncDLObject2) {
		long modifiedTime1 = syncDLObject1.getModifiedTime();
		long modifiedTime2 = syncDLObject2.getModifiedTime();

		int value = 0;

		if (modifiedTime1 < modifiedTime2) {
			value = -1;
		}
		else if (modifiedTime1 > modifiedTime2) {
			value = 1;
		}

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