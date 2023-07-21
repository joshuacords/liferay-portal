/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.shipping.web.internal.model;

import com.liferay.commerce.frontend.model.RestrictionField;

import java.util.List;

/**
 * @author Alessio Antonio Rendina
 */
public class ShippingRestriction {

	public ShippingRestriction(
		long commerceCountryId, String country, List<RestrictionField> fields) {

		_commerceCountryId = commerceCountryId;
		_country = country;
		_fields = fields;
	}

	public long getCommerceCountryId() {
		return _commerceCountryId;
	}

	public String getCountry() {
		return _country;
	}

	public List<RestrictionField> getFields() {
		return _fields;
	}

	private final long _commerceCountryId;
	private final String _country;
	private final List<RestrictionField> _fields;

}