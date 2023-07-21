/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.asset.util.comparator;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Shuyang Zhou
 */
public class AssetCategoryLeftCategoryIdComparator
	extends OrderByComparator<AssetCategory> {

	public static final String ORDER_BY_ASC = "leftCategoryId ASC";

	public static final String ORDER_BY_DESC = "leftCategoryId DESC";

	public static final String[] ORDER_BY_FIELDS = {"leftCategoryId"};

	public AssetCategoryLeftCategoryIdComparator() {
		this(false);
	}

	public AssetCategoryLeftCategoryIdComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(
		AssetCategory assetCategory1, AssetCategory assetCategory2) {

		long leftCategoryId1 = assetCategory1.getLeftCategoryId();
		long leftCategoryId2 = assetCategory2.getLeftCategoryId();

		int value = 0;

		if (leftCategoryId1 < leftCategoryId2) {
			value = -1;
		}
		else if (leftCategoryId1 > leftCategoryId2) {
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