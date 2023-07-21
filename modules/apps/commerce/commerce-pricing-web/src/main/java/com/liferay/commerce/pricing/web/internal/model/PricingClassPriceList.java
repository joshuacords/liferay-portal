/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.pricing.web.internal.model;

import com.liferay.commerce.frontend.model.LabelField;

/**
 * @author Riccardo Alberti
 */
public class PricingClassPriceList {

	public PricingClassPriceList(
		long commercePriceListId, String name, String catalogName,
		String createDate, LabelField status, String active) {

		_commercePriceListId = commercePriceListId;
		_name = name;
		_catalogName = catalogName;
		_createDate = createDate;
		_status = status;
		_active = active;
	}

	public String getActive() {
		return _active;
	}

	public String getCatalogName() {
		return _catalogName;
	}

	public long getCommercePriceListId() {
		return _commercePriceListId;
	}

	public String getCreateDate() {
		return _createDate;
	}

	public String getName() {
		return _name;
	}

	public LabelField getStatus() {
		return _status;
	}

	private final String _active;
	private final String _catalogName;
	private final long _commercePriceListId;
	private final String _createDate;
	private final String _name;
	private final LabelField _status;

}