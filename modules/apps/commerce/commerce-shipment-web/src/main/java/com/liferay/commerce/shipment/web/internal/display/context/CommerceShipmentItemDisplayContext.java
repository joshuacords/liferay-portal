/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.shipment.web.internal.display.context;

import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.model.CommerceShipment;
import com.liferay.commerce.model.CommerceShipmentItem;
import com.liferay.commerce.service.CommerceOrderItemService;
import com.liferay.commerce.service.CommerceShipmentItemService;
import com.liferay.commerce.shipment.web.internal.portlet.action.ActionHelper;
import com.liferay.portal.kernel.exception.PortalException;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceShipmentItemDisplayContext
	extends BaseCommerceShipmentDisplayContext<CommerceShipmentItem> {

	public CommerceShipmentItemDisplayContext(
		ActionHelper actionHelper, HttpServletRequest httpServletRequest,
		CommerceOrderItemService commerceOrderItemService,
		CommerceShipmentItemService commerceShipmentItemService) {

		super(actionHelper, httpServletRequest);

		_commerceOrderItemService = commerceOrderItemService;
		_commerceShipmentItemService = commerceShipmentItemService;
	}

	public CommerceOrderItem getCommerceOrderItem() throws PortalException {
		CommerceShipmentItem commerceShipmentItem = getCommerceShipmentItem();

		if (commerceShipmentItem == null) {
			return null;
		}

		return _commerceOrderItemService.getCommerceOrderItem(
			commerceShipmentItem.getCommerceOrderItemId());
	}

	@Override
	public CommerceShipment getCommerceShipment() throws PortalException {
		CommerceShipmentItem commerceShipmentItem = getCommerceShipmentItem();

		if (commerceShipmentItem == null) {
			return null;
		}

		return commerceShipmentItem.getCommerceShipment();
	}

	public CommerceShipmentItem getCommerceShipmentItem()
		throws PortalException {

		if (_commerceShipmentItem != null) {
			return _commerceShipmentItem;
		}

		_commerceShipmentItem = actionHelper.getCommerceShipmentItem(
			cpRequestHelper.getRenderRequest());

		return _commerceShipmentItem;
	}

	@Override
	public PortletURL getPortletURL() throws PortalException {
		PortletURL portletURL = super.getPortletURL();

		portletURL.setParameter("mvcRenderCommandName", "editCommerceShipment");

		return portletURL;
	}

	public int getToSendQuantity() throws PortalException {
		CommerceOrderItem commerceOrderItem = getCommerceOrderItem();

		return _commerceShipmentItemService.
			getCommerceShipmentOrderItemsQuantity(
				getCommerceShipmentId(),
				commerceOrderItem.getCommerceOrderItemId());
	}

	private final CommerceOrderItemService _commerceOrderItemService;
	private CommerceShipmentItem _commerceShipmentItem;
	private final CommerceShipmentItemService _commerceShipmentItemService;

}