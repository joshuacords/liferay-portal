/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.order.engine;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.order.status.CommerceOrderStatus;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

/**
 * @author Alec Sloan
 */
@ProviderType
public interface CommerceOrderEngine {

	public CommerceOrder checkCommerceOrderShipmentStatus(
			CommerceOrder commerceOrder)
		throws PortalException;

	public CommerceOrder checkoutCommerceOrder(
			CommerceOrder commerceOrder, long userId)
		throws PortalException;

	public CommerceOrderStatus getCurrentCommerceOrderStatus(
		CommerceOrder commerceOrder);

	public List<CommerceOrderStatus> getNextCommerceOrderStatuses(
			CommerceOrder commerceOrder)
		throws PortalException;

	public CommerceOrder transitionCommerceOrder(
			CommerceOrder commerceOrder, int orderStatus, long userId)
		throws PortalException;

}