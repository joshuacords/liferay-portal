/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.price.list.web.internal.portlet;

import com.liferay.commerce.price.list.constants.CommercePriceListPortletKeys;
import com.liferay.portal.kernel.portlet.BasePortletProvider;
import com.liferay.portal.kernel.portlet.ManagePortletProvider;

import org.osgi.service.component.annotations.Component;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = "model.class.name=com.liferay.commerce.price.list.model.CommercePriceList",
	service = ManagePortletProvider.class
)
public class CommercePriceListPortletProvider
	extends BasePortletProvider implements ManagePortletProvider {

	@Override
	public String getPortletName() {
		return CommercePriceListPortletKeys.COMMERCE_PRICE_LIST;
	}

}