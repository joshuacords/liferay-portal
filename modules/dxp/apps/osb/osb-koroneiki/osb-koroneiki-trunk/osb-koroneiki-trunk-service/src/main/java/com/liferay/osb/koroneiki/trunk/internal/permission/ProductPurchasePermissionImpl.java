/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.internal.permission;

import com.liferay.osb.koroneiki.root.permission.ModelPermission;
import com.liferay.osb.koroneiki.trunk.model.ProductPurchase;
import com.liferay.osb.koroneiki.trunk.permission.ProductPurchasePermission;
import com.liferay.osb.koroneiki.trunk.service.ProductPurchaseLocalService;
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
	service = {ModelPermission.class, ProductPurchasePermission.class}
)
public class ProductPurchasePermissionImpl
	implements ModelPermission, ProductPurchasePermission {

	public static final String RESOURCE_NAME_PRODUCTS =
		"com.liferay.osb.koroneiki.trunk.products";

	@Override
	public void check(
			PermissionChecker permissionChecker, long productPurchaseId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, productPurchaseId, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, ProductPurchase.class.getName(),
				productPurchaseId, actionId);
		}
	}

	@Override
	public void check(
			PermissionChecker permissionChecker,
			ProductPurchase productPurchase, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, productPurchase, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, ProductPurchase.class.getName(),
				productPurchase.getProductPurchaseId(), actionId);
		}
	}

	@Override
	public void check(PermissionChecker permissionChecker, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, RESOURCE_NAME_PRODUCTS, 0, actionId);
		}
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long productPurchaseId,
			String actionId)
		throws PortalException {

		if (contains(
				permissionChecker,
				_productPurchaseLocalService.getProductPurchase(
					productPurchaseId),
				actionId)) {

			return true;
		}

		return false;
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long[] productPurchaseIds,
			String actionId)
		throws PortalException {

		if (ArrayUtil.isEmpty(productPurchaseIds)) {
			return false;
		}

		for (long productPurchaseId : productPurchaseIds) {
			if (!contains(permissionChecker, productPurchaseId, actionId)) {
				return false;
			}
		}

		return true;
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker,
			ProductPurchase productPurchase, String actionId)
		throws PortalException {

		if (permissionChecker.hasOwnerPermission(
				productPurchase.getCompanyId(), ProductPurchase.class.getName(),
				productPurchase.getProductPurchaseId(),
				productPurchase.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			0, ProductPurchase.class.getName(),
			productPurchase.getProductPurchaseId(), actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, String actionId)
		throws PortalException {

		return permissionChecker.hasPermission(
			0, RESOURCE_NAME_PRODUCTS, RESOURCE_NAME_PRODUCTS, actionId);
	}

	@Override
	public String getClassName() {
		return ProductPurchase.class.getName();
	}

	@Reference
	private ProductPurchaseLocalService _productPurchaseLocalService;

}