/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.model.impl;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.service.CommerceAccountLocalServiceUtil;
import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.model.CommerceShippingMethod;
import com.liferay.commerce.service.CommerceAddressLocalServiceUtil;
import com.liferay.commerce.service.CommerceShippingMethodLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceShipmentImpl extends CommerceShipmentBaseImpl {

	public CommerceShipmentImpl() {
	}

	@Override
	public CommerceAddress fetchCommerceAddress() {
		return CommerceAddressLocalServiceUtil.fetchCommerceAddress(
			getCommerceAddressId());
	}

	@Override
	public CommerceShippingMethod fetchCommerceShippingMethod() {
		return CommerceShippingMethodLocalServiceUtil.
			fetchCommerceShippingMethod(getCommerceShippingMethodId());
	}

	@Override
	public CommerceAccount getCommerceAccount() throws PortalException {
		return CommerceAccountLocalServiceUtil.getCommerceAccount(
			getCommerceAccountId());
	}

	@Override
	public String getCommerceAccountName() throws PortalException {
		CommerceAccount commerceAccount = getCommerceAccount();

		return commerceAccount.getName();
	}

	@Override
	public CommerceShippingMethod getCommerceShippingMethod()
		throws PortalException {

		long commerceShippingMethodId = getCommerceShippingMethodId();

		if (commerceShippingMethodId > 0) {
			return CommerceShippingMethodLocalServiceUtil.
				getCommerceShippingMethod(commerceShippingMethodId);
		}

		return null;
	}

}