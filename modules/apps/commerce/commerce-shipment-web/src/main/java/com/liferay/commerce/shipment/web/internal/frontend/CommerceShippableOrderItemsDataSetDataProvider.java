/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.shipment.web.internal.frontend;

import com.liferay.commerce.constants.CommerceOrderConstants;
import com.liferay.commerce.constants.CommerceShipmentDataSetConstants;
import com.liferay.commerce.frontend.CommerceDataSetDataProvider;
import com.liferay.commerce.frontend.Filter;
import com.liferay.commerce.frontend.Pagination;
import com.liferay.commerce.frontend.model.Icon;
import com.liferay.commerce.frontend.model.OrderItem;
import com.liferay.commerce.inventory.engine.CommerceInventoryEngine;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.model.CommerceShipment;
import com.liferay.commerce.model.CommerceShipmentItem;
import com.liferay.commerce.service.CommerceOrderItemService;
import com.liferay.commerce.service.CommerceShipmentItemService;
import com.liferay.commerce.service.CommerceShipmentService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.util.ParamUtil;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alec Sloan
 */
@Component(
	immediate = true,
	property = "commerce.data.provider.key=" + CommerceShipmentDataSetConstants.COMMERCE_DATA_SET_KEY_SHIPPABLE_ORDER_ITEMS,
	service = CommerceDataSetDataProvider.class
)
public class CommerceShippableOrderItemsDataSetDataProvider
	implements CommerceDataSetDataProvider<OrderItem> {

	@Override
	public int countItems(HttpServletRequest httpServletRequest, Filter filter)
		throws PortalException {

		long commerceShipmentId = ParamUtil.getLong(
			httpServletRequest, "commerceShipmentId");

		CommerceShipment commerceShipment =
			_commerceShipmentService.getCommerceShipment(commerceShipmentId);

		return _commerceOrderItemService.getCommerceOrderItemsCount(
			commerceShipment.getGroupId(),
			commerceShipment.getCommerceAccountId(), orderStatuses);
	}

	@Override
	public List<OrderItem> getItems(
			HttpServletRequest httpServletRequest, Filter filter,
			Pagination pagination, Sort sort)
		throws PortalException {

		List<OrderItem> orderItems = new ArrayList<>();

		long commerceShipmentId = ParamUtil.getLong(
			httpServletRequest, "commerceShipmentId");

		CommerceShipment commerceShipment =
			_commerceShipmentService.getCommerceShipment(commerceShipmentId);

		List<CommerceOrderItem> commerceOrderItems =
			_commerceOrderItemService.getCommerceOrderItems(
				commerceShipment.getGroupId(),
				commerceShipment.getCommerceAccountId(), orderStatuses,
				pagination.getStartPosition(), pagination.getEndPosition());

		for (CommerceOrderItem commerceOrderItem : commerceOrderItems) {
			CommerceOrder commerceOrder = commerceOrderItem.getCommerceOrder();

			String iconName = _getAddressMatchIcon(
				commerceShipment, commerceOrder);

			Icon icon = null;

			if (iconName != null) {
				icon = new Icon(iconName);
			}

			CommerceShipmentItem commerceShipmentItem =
				_commerceShipmentItemService.fetchCommerceShipmentItem(
					commerceShipmentId,
					commerceOrderItem.getCommerceOrderItemId(), 0);

			if (commerceShipmentItem == null) {
				orderItems.add(
					new OrderItem(
						_commerceInventoryEngine.getStockQuantity(
							commerceOrderItem.getCompanyId(),
							commerceOrderItem.getGroupId(),
							commerceOrderItem.getSku()),
						icon, commerceOrderItem.getCommerceOrderId(),
						commerceOrderItem.getCommerceOrderItemId(),
						commerceOrderItem.getQuantity() -
							commerceOrderItem.getShippedQuantity(),
						commerceOrderItem.getSku()));
			}
		}

		return orderItems;
	}

	protected int[] orderStatuses = {
		CommerceOrderConstants.ORDER_STATUS_PROCESSING,
		CommerceOrderConstants.ORDER_STATUS_PARTIALLY_SHIPPED
	};

	private String _getAddressMatchIcon(
		CommerceShipment commerceShipment, CommerceOrder commerceOrder) {

		if (commerceShipment.getCommerceAddressId() ==
				commerceOrder.getShippingAddressId()) {

			return "check";
		}

		return null;
	}

	@Reference
	private CommerceInventoryEngine _commerceInventoryEngine;

	@Reference
	private CommerceOrderItemService _commerceOrderItemService;

	@Reference
	private CommerceShipmentItemService _commerceShipmentItemService;

	@Reference
	private CommerceShipmentService _commerceShipmentService;

}