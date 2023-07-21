/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.util.comparator;

import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Eudaldo Alonso
 */
public class StructureNameComparator extends OrderByComparator<DDMStructure> {

	public static final String ORDER_BY_ASC = "DDMStructure.name ASC";

	public static final String ORDER_BY_DESC = "DDMStructure.name DESC";

	public static final String[] ORDER_BY_FIELDS = {"name"};

	public StructureNameComparator() {
		this(false);
	}

	public StructureNameComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(DDMStructure ddmStructure1, DDMStructure ddmStructure2) {
		String name1 = StringUtil.toLowerCase(ddmStructure1.getName());
		String name2 = StringUtil.toLowerCase(ddmStructure2.getName());

		int value = name1.compareTo(name2);

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