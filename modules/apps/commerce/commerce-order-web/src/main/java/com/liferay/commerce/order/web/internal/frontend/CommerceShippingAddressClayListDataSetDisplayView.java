/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.order.web.internal.frontend;

import com.liferay.commerce.frontend.clay.data.set.ClayDataSetDisplayView;
import com.liferay.commerce.frontend.clay.list.ClayListDataSetDisplayView;

import org.osgi.service.component.annotations.Component;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = "commerce.data.set.display.name=" + CommerceOrderDataSetConstants.COMMERCE_DATA_SET_KEY_SHIPPING_ADDRESSES,
	service = {ClayDataSetDisplayView.class, ClayListDataSetDisplayView.class}
)
public class CommerceShippingAddressClayListDataSetDisplayView
	extends ClayListDataSetDisplayView {

	@Override
	public String getDescription() {
		return "description";
	}

	@Override
	public String getTitle() {
		return "title";
	}

}