/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.util.comparator;

import com.liferay.commerce.model.CommerceCountry;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceCountryPriorityComparator
	extends OrderByComparator<CommerceCountry> {

	public static final String ORDER_BY_ASC = "CommerceCountry.priority ASC";

	public static final String ORDER_BY_DESC = "CommerceCountry.priority DESC";

	public static final String[] ORDER_BY_FIELDS = {"priority"};

	public CommerceCountryPriorityComparator() {
		this(false);
	}

	public CommerceCountryPriorityComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(
		CommerceCountry commerceCountry1, CommerceCountry commerceCountry2) {

		int value = Double.compare(
			commerceCountry1.getPriority(), commerceCountry2.getPriority());

		if (_ascending) {
			return value;
		}

		return Math.negateExact(value);
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