/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.internal.permission;

import com.liferay.osb.koroneiki.root.permission.ModelPermission;
import com.liferay.osb.koroneiki.trunk.model.ProductConsumption;
import com.liferay.osb.koroneiki.trunk.permission.ProductConsumptionPermission;
import com.liferay.osb.koroneiki.trunk.service.ProductConsumptionLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.util.ArrayUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true,
	service = {ModelPermission.class, ProductConsumptionPermission.class}
)
public class ProductConsumptionPermissionImpl
	implements ModelPermission, ProductConsumptionPermission {

	@Override
	public void check(
			PermissionChecker permissionChecker, long productConsumptionId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, productConsumptionId, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, ProductConsumption.class.getName(),
				productConsumptionId, actionId);
		}
	}

	@Override
	public void check(
			PermissionChecker permissionChecker,
			ProductConsumption productConsumption, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, productConsumption, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, ProductConsumption.class.getName(),
				productConsumption.getProductConsumptionId(), actionId);
		}
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long productConsumptionId,
			String actionId)
		throws PortalException {

		if (contains(
				permissionChecker,
				_productConsumptionLocalService.getProductConsumption(
					productConsumptionId),
				actionId)) {

			return true;
		}

		return false;
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long[] productConsumptionIds,
			String actionId)
		throws PortalException {

		if (ArrayUtil.isEmpty(productConsumptionIds)) {
			return false;
		}

		for (long productConsumptionId : productConsumptionIds) {
			if (!contains(permissionChecker, productConsumptionId, actionId)) {
				return false;
			}
		}

		return true;
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker,
			ProductConsumption productConsumption, String actionId)
		throws PortalException {

		if (permissionChecker.hasOwnerPermission(
				productConsumption.getCompanyId(),
				ProductConsumption.class.getName(),
				productConsumption.getProductConsumptionId(),
				productConsumption.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			0, ProductConsumption.class.getName(),
			productConsumption.getProductConsumptionId(), actionId);
	}

	@Override
	public String getClassName() {
		return ProductConsumption.class.getName();
	}

	@Reference
	private ProductConsumptionLocalService _productConsumptionLocalService;

}