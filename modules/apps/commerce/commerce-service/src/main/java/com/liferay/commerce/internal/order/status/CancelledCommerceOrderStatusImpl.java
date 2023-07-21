/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.internal.order.status;

import com.liferay.commerce.constants.CommerceOrderConstants;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.order.status.CommerceOrderStatus;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Alec Sloan
 */
@Component(
	immediate = true,
	property = {
		"commerce.order.status.key=" + CancelledCommerceOrderStatusImpl.KEY,
		"commerce.order.status.priority:Integer=" + CancelledCommerceOrderStatusImpl.PRIORITY
	},
	service = CommerceOrderStatus.class
)
public class CancelledCommerceOrderStatusImpl implements CommerceOrderStatus {

	public static final int KEY = CommerceOrderConstants.ORDER_STATUS_CANCELLED;

	public static final int PRIORITY = -1;

	@Override
	public CommerceOrder doTransition(CommerceOrder commerceOrder, long userId)
		throws PortalException {

		commerceOrder.setOrderStatus(KEY);

		return _commerceOrderService.updateCommerceOrder(commerceOrder);
	}

	public int getKey() {
		return KEY;
	}

	public String getLabel(Locale locale) {
		return LanguageUtil.get(
			locale, CommerceOrderConstants.getOrderStatusLabel(KEY));
	}

	public int getPriority() {
		return PRIORITY;
	}

	@Reference(
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	private volatile CommerceOrderService _commerceOrderService;

}