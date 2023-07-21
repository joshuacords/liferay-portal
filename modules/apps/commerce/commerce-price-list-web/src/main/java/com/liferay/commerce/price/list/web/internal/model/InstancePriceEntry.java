/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.price.list.web.internal.model;

/**
 * @author Alessio Antonio Rendina
 */
public class InstancePriceEntry {

	public InstancePriceEntry(
		long priceEntryId, String name, String unitPrice, String createDate) {

		_priceEntryId = priceEntryId;
		_name = name;
		_unitPrice = unitPrice;
		_createDate = createDate;
	}

	public String getCreateDate() {
		return _createDate;
	}

	public String getName() {
		return _name;
	}

	public long getPriceEntryId() {
		return _priceEntryId;
	}

	public String getUnitPrice() {
		return _unitPrice;
	}

	private final String _createDate;
	private final String _name;
	private final long _priceEntryId;
	private final String _unitPrice;

}