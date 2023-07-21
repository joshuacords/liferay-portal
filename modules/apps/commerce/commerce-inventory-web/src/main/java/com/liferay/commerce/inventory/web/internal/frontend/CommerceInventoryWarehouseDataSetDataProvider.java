/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.inventory.web.internal.frontend;

import com.liferay.commerce.frontend.CommerceDataSetDataProvider;
import com.liferay.commerce.frontend.Filter;
import com.liferay.commerce.frontend.Pagination;
import com.liferay.commerce.inventory.model.CommerceInventoryWarehouse;
import com.liferay.commerce.inventory.model.CommerceInventoryWarehouseItem;
import com.liferay.commerce.inventory.service.CommerceInventoryReplenishmentItemService;
import com.liferay.commerce.inventory.service.CommerceInventoryWarehouseItemService;
import com.liferay.commerce.inventory.web.internal.model.Warehouse;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Luca Pellizzon
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = "commerce.data.provider.key=" + CommerceInventoryDataSetConstants.COMMERCE_DATA_SET_KEY_INVENTORY_WAREHOUSES,
	service = CommerceDataSetDataProvider.class
)
public class CommerceInventoryWarehouseDataSetDataProvider
	implements CommerceDataSetDataProvider<Warehouse> {

	@Override
	public int countItems(HttpServletRequest httpServletRequest, Filter filter)
		throws PortalException {

		String sku = ParamUtil.getString(httpServletRequest, "sku");

		return _commerceInventoryWarehouseItemService.
			getCommerceInventoryWarehouseItemsCount(
				_portal.getCompanyId(httpServletRequest), sku);
	}

	@Override
	public List<Warehouse> getItems(
			HttpServletRequest httpServletRequest, Filter filter,
			Pagination pagination, Sort sort)
		throws PortalException {

		List<Warehouse> warehouses = new ArrayList<>();

		String sku = ParamUtil.getString(httpServletRequest, "sku");

		List<CommerceInventoryWarehouseItem> commerceInventoryWarehouseItems =
			_commerceInventoryWarehouseItemService.
				getCommerceInventoryWarehouseItems(
					_portal.getCompanyId(httpServletRequest), sku,
					pagination.getStartPosition(), pagination.getEndPosition());

		for (CommerceInventoryWarehouseItem commerceInventoryWarehouseItem :
				commerceInventoryWarehouseItems) {

			CommerceInventoryWarehouse commerceInventoryWarehouse =
				commerceInventoryWarehouseItem.getCommerceInventoryWarehouse();

			warehouses.add(
				new Warehouse(
					commerceInventoryWarehouseItem.
						getCommerceInventoryWarehouseItemId(),
					commerceInventoryWarehouse.getName(),
					commerceInventoryWarehouseItem.getQuantity(),
					commerceInventoryWarehouseItem.getReservedQuantity(),
					_commerceInventoryReplenishmentItemService.
						getCommerceInventoryReplenishmentItemsCount(
							commerceInventoryWarehouse.
								getCommerceInventoryWarehouseId(),
							sku)));
		}

		return warehouses;
	}

	@Reference
	private CommerceInventoryReplenishmentItemService
		_commerceInventoryReplenishmentItemService;

	@Reference
	private CommerceInventoryWarehouseItemService
		_commerceInventoryWarehouseItemService;

	@Reference
	private Portal _portal;

}