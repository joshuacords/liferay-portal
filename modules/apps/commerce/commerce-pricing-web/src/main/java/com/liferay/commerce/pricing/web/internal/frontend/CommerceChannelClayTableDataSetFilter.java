/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.pricing.web.internal.frontend;

import com.liferay.commerce.frontend.clay.data.set.ClayAutocompleteDataSetFilter;
import com.liferay.commerce.frontend.clay.data.set.ClayDataSetFilter;
import com.liferay.commerce.pricing.web.internal.frontend.constants.CommercePricingDataSetConstants;

import org.osgi.service.component.annotations.Component;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = {
		"commerce.data.set.display.name=" + CommercePricingDataSetConstants.COMMERCE_DATA_SET_KEY_DISCOUNTS,
		"commerce.data.set.display.name=" + CommercePricingDataSetConstants.COMMERCE_DATA_SET_KEY_PRICE_LISTS
	},
	service = ClayDataSetFilter.class
)
public class CommerceChannelClayTableDataSetFilter
	extends ClayAutocompleteDataSetFilter {

	@Override
	public String getApiURL() {
		return "/o/headless-commerce-admin-channel/v1.0/channels?sort=name:asc";
	}

	@Override
	public String getId() {
		return "channelId";
	}

	@Override
	public String getItemKey() {
		return "id";
	}

	@Override
	public String getItemLabel() {
		return "name";
	}

	@Override
	public String getLabel() {
		return "channel";
	}

	@Override
	public boolean isMultipleSelection() {
		return true;
	}

}