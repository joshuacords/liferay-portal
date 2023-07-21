/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.checkout.web.internal.display.context;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.constants.CommerceCheckoutWebKeys;
import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.service.CommerceAddressService;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Andrea Di Giorgi
 * @author Alec Sloan
 */
public class ShippingAddressCheckoutStepDisplayContext
	extends BaseAddressCheckoutStepDisplayContext {

	public ShippingAddressCheckoutStepDisplayContext(
		CommerceAddressService commerceAddressService,
		HttpServletRequest httpServletRequest) {

		super(commerceAddressService, httpServletRequest);
	}

	@Override
	public List<CommerceAddress> getCommerceAddresses() throws PortalException {
		CommerceOrder commerceOrder = getCommerceOrder();

		return commerceAddressService.getShippingCommerceAddresses(
			commerceOrder.getCompanyId(), CommerceAccount.class.getName(),
			commerceOrder.getCommerceAccountId());
	}

	@Override
	public String getCommerceCountrySelectionColumnName() {
		return "shippingAllowed";
	}

	@Override
	public String getCommerceCountrySelectionMethodName() {
		return "get-shipping-commerce-countries";
	}

	@Override
	public long getDefaultCommerceAddressId() throws PortalException {
		CommerceOrder commerceOrder = getCommerceOrder();

		long shippingAddressId = commerceOrder.getShippingAddressId();

		if (shippingAddressId == 0) {
			CommerceAccount commerceAccount =
				commerceOrder.getCommerceAccount();

			if (commerceAccount != null) {
				shippingAddressId =
					commerceAccount.getDefaultShippingAddressId();
			}
		}

		return shippingAddressId;
	}

	@Override
	public String getParamName() {
		return CommerceCheckoutWebKeys.SHIPPING_ADDRESS_PARAM_NAME;
	}

	@Override
	public String getTitle() {
		return "shipping-address";
	}

}