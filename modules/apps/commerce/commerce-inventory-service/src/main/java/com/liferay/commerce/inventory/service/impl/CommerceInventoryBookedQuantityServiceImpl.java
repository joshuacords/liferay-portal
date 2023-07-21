/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.inventory.service.impl;

import com.liferay.commerce.inventory.constants.CommerceInventoryActionKeys;
import com.liferay.commerce.inventory.model.CommerceInventoryBookedQuantity;
import com.liferay.commerce.inventory.service.base.CommerceInventoryBookedQuantityServiceBaseImpl;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.permission.PortalPermissionUtil;

import java.util.List;

/**
 * @author Luca Pellizzon
 * @author Alessio Antonio Rendina
 */
public class CommerceInventoryBookedQuantityServiceImpl
	extends CommerceInventoryBookedQuantityServiceBaseImpl {

	@Override
	public List<CommerceInventoryBookedQuantity>
			getCommerceInventoryBookedQuantities(
				long companyId, String sku, int start, int end)
		throws PrincipalException {

		PortalPermissionUtil.check(
			getPermissionChecker(),
			CommerceInventoryActionKeys.MANAGE_INVENTORY);

		return commerceInventoryBookedQuantityLocalService.
			getCommerceInventoryBookedQuantities(companyId, sku, start, end);
	}

	@Override
	public int getCommerceInventoryBookedQuantitiesCount(
			long companyId, String sku)
		throws PrincipalException {

		PortalPermissionUtil.check(
			getPermissionChecker(),
			CommerceInventoryActionKeys.MANAGE_INVENTORY);

		return commerceInventoryBookedQuantityLocalService.
			getCommerceInventoryBookedQuantitiesCount(companyId, sku);
	}

}