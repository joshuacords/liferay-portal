/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.polls.util.comparator;

import com.liferay.polls.model.PollsQuestion;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Rafael Praxedes
 */
public class PollsQuestionCreateDateComparator
	extends OrderByComparator<PollsQuestion> {

	public static final String ORDER_BY_ASC = "PollsQuestion.createDate ASC";

	public static final String ORDER_BY_DESC = "PollsQuestion.createDate DESC";

	public static final String[] ORDER_BY_FIELDS = {"createDate"};

	public PollsQuestionCreateDateComparator() {
		this(false);
	}

	public PollsQuestionCreateDateComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(
		PollsQuestion pollsQuestion1, PollsQuestion pollsQuestion2) {

		int value = DateUtil.compareTo(
			pollsQuestion1.getCreateDate(), pollsQuestion2.getCreateDate());

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