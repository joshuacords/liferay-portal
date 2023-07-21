/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.polls.util.comparator;

import com.liferay.polls.model.PollsQuestion;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Rafael Praxedes
 */
public class PollsQuestionTitleComparator
	extends OrderByComparator<PollsQuestion> {

	public static final String ORDER_BY_ASC = "PollsQuestion.title ASC";

	public static final String ORDER_BY_DESC = "PollsQuestion.title DESC";

	public static final String[] ORDER_BY_FIELDS = {"title"};

	public PollsQuestionTitleComparator() {
		this(false);
	}

	public PollsQuestionTitleComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(
		PollsQuestion pollsQuestion1, PollsQuestion pollsQuestion2) {

		String title1 = StringUtil.toLowerCase(pollsQuestion1.getTitle());
		String title2 = StringUtil.toLowerCase(pollsQuestion2.getTitle());

		int value = title1.compareTo(title2);

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