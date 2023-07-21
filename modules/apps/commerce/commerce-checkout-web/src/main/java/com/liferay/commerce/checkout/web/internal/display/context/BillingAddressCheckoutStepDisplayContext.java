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
 */
public class BillingAddressCheckoutStepDisplayContext
	extends BaseAddressCheckoutStepDisplayContext {

	public BillingAddressCheckoutStepDisplayContext(
		CommerceAddressService commerceAddressService,
		HttpServletRequest httpServletRequest) {

		super(commerceAddressService, httpServletRequest);
	}

	@Override
	public List<CommerceAddress> getCommerceAddresses() throws PortalException {
		CommerceOrder commerceOrder = getCommerceOrder();

		return commerceAddressService.getBillingCommerceAddresses(
			commerceOrder.getCompanyId(), CommerceAccount.class.getName(),
			commerceOrder.getCommerceAccountId());
	}

	@Override
	public String getCommerceCountrySelectionColumnName() {
		return "billingAllowed";
	}

	@Override
	public String getCommerceCountrySelectionMethodName() {
		return "get-billing-commerce-countries";
	}

	@Override
	public long getDefaultCommerceAddressId() throws PortalException {
		CommerceOrder commerceOrder = getCommerceOrder();

		long billingAddressId = commerceOrder.getBillingAddressId();

		if (billingAddressId == 0) {
			CommerceAccount commerceAccount =
				commerceOrder.getCommerceAccount();

			CommerceAddress commerceAddress =
				commerceAddressService.fetchCommerceAddress(
					commerceAccount.getDefaultBillingAddressId());

			if (commerceAddress != null) {
				billingAddressId = commerceAddress.getCommerceAddressId();
			}
		}

		return billingAddressId;
	}

	@Override
	public String getParamName() {
		return CommerceCheckoutWebKeys.BILLING_ADDRESS_PARAM_NAME;
	}

	@Override
	public String getTitle() {
		return "billing-address";
	}

}