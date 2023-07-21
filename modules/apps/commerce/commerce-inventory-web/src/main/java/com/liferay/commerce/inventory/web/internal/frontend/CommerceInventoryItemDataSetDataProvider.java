/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.inventory.web.internal.frontend;

import com.liferay.commerce.frontend.CommerceDataSetDataProvider;
import com.liferay.commerce.frontend.Filter;
import com.liferay.commerce.frontend.Pagination;
import com.liferay.commerce.inventory.constants.CommerceInventoryActionKeys;
import com.liferay.commerce.inventory.model.CIWarehouseItem;
import com.liferay.commerce.inventory.service.CommerceInventoryWarehouseItemLocalService;
import com.liferay.commerce.inventory.web.internal.model.InventoryItem;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.permission.PortalPermissionUtil;
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
	property = "commerce.data.provider.key=" + CommerceInventoryDataSetConstants.COMMERCE_DATA_SET_KEY_INVENTORY_ITEMS,
	service = CommerceDataSetDataProvider.class
)
public class CommerceInventoryItemDataSetDataProvider
	implements CommerceDataSetDataProvider<InventoryItem> {

	@Override
	public int countItems(HttpServletRequest httpServletRequest, Filter filter)
		throws PortalException {

		PortalPermissionUtil.check(
			PermissionThreadLocal.getPermissionChecker(),
			CommerceInventoryActionKeys.MANAGE_INVENTORY);

		return _commerceInventoryWarehouseItemLocalService.
			countItemsByCompanyId(
				_portal.getCompanyId(httpServletRequest), filter.getKeywords());
	}

	@Override
	public List<InventoryItem> getItems(
			HttpServletRequest httpServletRequest, Filter filter,
			Pagination pagination, Sort sort)
		throws PortalException {

		PortalPermissionUtil.check(
			PermissionThreadLocal.getPermissionChecker(),
			CommerceInventoryActionKeys.MANAGE_INVENTORY);

		List<InventoryItem> inventoryItems = new ArrayList<>();

		List<CIWarehouseItem> ciWarehouseItems =
			_commerceInventoryWarehouseItemLocalService.getItemsByCompanyId(
				_portal.getCompanyId(httpServletRequest), filter.getKeywords(),
				pagination.getStartPosition(), pagination.getEndPosition());

		for (CIWarehouseItem ciWarehouseItem : ciWarehouseItems) {
			inventoryItems.add(
				new InventoryItem(
					ciWarehouseItem.getSkuCode(),
					ciWarehouseItem.getStockQuantity(),
					ciWarehouseItem.getBookedQuantity(),
					ciWarehouseItem.getReplenishmentQuantity()));
		}

		return inventoryItems;
	}

	@Reference
	private CommerceInventoryWarehouseItemLocalService
		_commerceInventoryWarehouseItemLocalService;

	@Reference
	private Portal _portal;

}