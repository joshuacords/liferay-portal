/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.inventory.internal.permission;

import com.liferay.commerce.inventory.constants.CommerceInventoryActionKeys;
import com.liferay.commerce.inventory.model.CommerceInventoryWarehouse;
import com.liferay.commerce.inventory.permission.CommerceInventoryWarehousePermission;
import com.liferay.commerce.inventory.service.CommerceInventoryWarehouseLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.permission.PortalPermission;
import com.liferay.portal.kernel.util.ArrayUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Luca Pellizzon
 */
@Component(
	immediate = true, service = CommerceInventoryWarehousePermission.class
)
public class CommerceInventoryWarehousePermissionImpl
	implements CommerceInventoryWarehousePermission {

	@Override
	public void check(
			PermissionChecker permissionChecker,
			CommerceInventoryWarehouse commerceInventoryWarehouse,
			String actionId)
		throws PortalException {

		if (!contains(
				permissionChecker, commerceInventoryWarehouse, actionId)) {

			throw new PrincipalException.MustHavePermission(
				permissionChecker, CommerceInventoryWarehouse.class.getName(),
				commerceInventoryWarehouse.getCommerceInventoryWarehouseId(),
				actionId);
		}
	}

	@Override
	public void check(
			PermissionChecker permissionChecker,
			long commerceInventoryWarehouseId, String actionId)
		throws PortalException {

		if (!contains(
				permissionChecker, commerceInventoryWarehouseId, actionId)) {

			throw new PrincipalException.MustHavePermission(
				permissionChecker, CommerceInventoryWarehouse.class.getName(),
				commerceInventoryWarehouseId, actionId);
		}
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker,
			CommerceInventoryWarehouse commerceInventoryWarehouse,
			String actionId)
		throws PortalException {

		if (contains(
				permissionChecker,
				commerceInventoryWarehouse.getCommerceInventoryWarehouseId(),
				actionId)) {

			return true;
		}

		return false;
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker,
			long commerceInventoryWarehouseId, String actionId)
		throws PortalException {

		CommerceInventoryWarehouse commerceInventoryWarehouse =
			_commerceInventoryWarehouseLocalService.
				getCommerceInventoryWarehouse(commerceInventoryWarehouseId);

		if (commerceInventoryWarehouse == null) {
			return false;
		}

		return _contains(
			permissionChecker, commerceInventoryWarehouse, actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long[] commerceAccountIds,
			String actionId)
		throws PortalException {

		if (ArrayUtil.isEmpty(commerceAccountIds)) {
			return false;
		}

		for (long commerceInventoryWarehouseId : commerceAccountIds) {
			if (!contains(
					permissionChecker, commerceInventoryWarehouseId,
					actionId)) {

				return false;
			}
		}

		return true;
	}

	private boolean _contains(
			PermissionChecker permissionChecker,
			CommerceInventoryWarehouse commerceInventoryWarehouse,
			String actionId)
		throws PortalException {

		if (permissionChecker.isCompanyAdmin(
				commerceInventoryWarehouse.getCompanyId()) ||
			permissionChecker.isOmniadmin()) {

			return true;
		}

		if (_portalPermission.contains(
				PermissionThreadLocal.getPermissionChecker(),
				CommerceInventoryActionKeys.MANAGE_INVENTORY)) {

			return true;
		}

		return permissionChecker.hasPermission(
			null, CommerceInventoryWarehouse.class.getName(),
			commerceInventoryWarehouse.getCommerceInventoryWarehouseId(),
			actionId);
	}

	@Reference
	private CommerceInventoryWarehouseLocalService
		_commerceInventoryWarehouseLocalService;

	@Reference
	private PortalPermission _portalPermission;

}