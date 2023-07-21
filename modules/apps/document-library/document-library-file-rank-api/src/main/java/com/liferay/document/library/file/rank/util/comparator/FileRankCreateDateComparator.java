/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.file.rank.util.comparator;

import com.liferay.document.library.file.rank.model.DLFileRank;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Brian Wing Shun Chan
 */
public class FileRankCreateDateComparator
	extends OrderByComparator<DLFileRank> {

	public static final String ORDER_BY_ASC = "DLFileRank.createDate ASC";

	public static final String ORDER_BY_DESC = "DLFileRank.createDate DESC";

	public static final String[] ORDER_BY_FIELDS = {"createDate"};

	public FileRankCreateDateComparator() {
		this(false);
	}

	public FileRankCreateDateComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(DLFileRank dlFileRank1, DLFileRank dlFileRank2) {
		int value = DateUtil.compareTo(
			dlFileRank1.getCreateDate(), dlFileRank2.getCreateDate());

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