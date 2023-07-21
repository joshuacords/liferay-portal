/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.price.list.model.impl;

import com.liferay.commerce.account.model.CommerceAccountGroup;
import com.liferay.commerce.account.service.CommerceAccountGroupLocalServiceUtil;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.service.CommercePriceListLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Alessio Antonio Rendina
 */
public class CommercePriceListCommerceAccountGroupRelImpl
	extends CommercePriceListCommerceAccountGroupRelBaseImpl {

	public CommercePriceListCommerceAccountGroupRelImpl() {
	}

	@Override
	public CommerceAccountGroup getCommerceAccountGroup()
		throws PortalException {

		return CommerceAccountGroupLocalServiceUtil.getCommerceAccountGroup(
			getCommerceAccountGroupId());
	}

	@Override
	public CommercePriceList getCommercePriceList() throws PortalException {
		return CommercePriceListLocalServiceUtil.getCommercePriceList(
			getCommercePriceListId());
	}

}