/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.inventory.engine;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;
import java.util.Map;

/**
 * @author Luca Pellizzon
 * @author Alessio Antonio Rendina
 */
@ProviderType
public interface CommerceInventoryEngine {

	public void consumeQuantity(
			long userId, long commerceInventoryWarehouseId, String sku,
			int quantity, long bookedQuantityId, Map<String, String> context)
		throws PortalException;

	public void decreaseStockQuantity(
			long userId, long commerceInventoryWarehouseId, String sku,
			int quantity)
		throws PortalException;

	public Map<String, Integer> getStockQuantities(
			long companyId, long channelGroupId, List<String> skus)
		throws PortalException;

	public int getStockQuantity(long companyId, long channelGroupId, String sku)
		throws PortalException;

	public int getStockQuantity(long companyId, String sku)
		throws PortalException;

	public boolean hasStockQuantity(long companyId, String sku, int quantity);

	public void increaseStockQuantity(
			long userId, long commerceInventoryWarehouseId, String sku,
			int quantity)
		throws PortalException;

}