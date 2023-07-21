/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.util.comparator;

import com.liferay.asset.model.AssetEntryUsage;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Pavel Savinov
 */
public class AssetEntryUsageModifiedDateComparator
	extends OrderByComparator<AssetEntryUsage> {

	public static final String ORDER_BY_ASC =
		"AssetEntryUsage.modifiedDate ASC";

	public static final String ORDER_BY_DESC =
		"AssetEntryUsage.modifiedDate DESC";

	public static final String[] ORDER_BY_FIELDS = {"modifiedDate"};

	public AssetEntryUsageModifiedDateComparator() {
		this(true);
	}

	public AssetEntryUsageModifiedDateComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(
		AssetEntryUsage assetEntryUsage1, AssetEntryUsage assetEntryUsage2) {

		int value = DateUtil.compareTo(
			assetEntryUsage1.getModifiedDate(),
			assetEntryUsage2.getModifiedDate());

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