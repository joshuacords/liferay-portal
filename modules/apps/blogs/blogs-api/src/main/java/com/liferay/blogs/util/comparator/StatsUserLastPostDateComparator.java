/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.blogs.util.comparator;

import com.liferay.blogs.model.BlogsStatsUser;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Brian Wing Shun Chan
 */
public class StatsUserLastPostDateComparator
	extends OrderByComparator<BlogsStatsUser> {

	public static final String ORDER_BY_ASC = "BlogsStatsUser.lastPostDate ASC";

	public static final String ORDER_BY_DESC =
		"BlogsStatsUser.lastPostDate DESC";

	public static final String[] ORDER_BY_FIELDS = {"lastPostDate"};

	public StatsUserLastPostDateComparator() {
		this(false);
	}

	public StatsUserLastPostDateComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(BlogsStatsUser statsUser1, BlogsStatsUser statsUser2) {
		int value = DateUtil.compareTo(
			statsUser1.getLastPostDate(), statsUser2.getLastPostDate());

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