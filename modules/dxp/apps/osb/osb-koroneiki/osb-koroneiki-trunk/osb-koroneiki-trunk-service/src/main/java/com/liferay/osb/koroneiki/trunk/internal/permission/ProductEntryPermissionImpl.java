/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.internal.permission;

import com.liferay.osb.koroneiki.root.permission.ModelPermission;
import com.liferay.osb.koroneiki.trunk.model.ProductEntry;
import com.liferay.osb.koroneiki.trunk.permission.ProductEntryPermission;
import com.liferay.osb.koroneiki.trunk.service.ProductEntryLocalService;
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
	service = {ModelPermission.class, ProductEntryPermission.class}
)
public class ProductEntryPermissionImpl
	implements ModelPermission, ProductEntryPermission {

	public static final String RESOURCE_NAME_PRODUCTS =
		"com.liferay.osb.koroneiki.trunk.products";

	@Override
	public void check(
			PermissionChecker permissionChecker, long productEntryId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, productEntryId, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, ProductEntry.class.getName(), productEntryId,
				actionId);
		}
	}

	@Override
	public void check(
			PermissionChecker permissionChecker, ProductEntry productEntry,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, productEntry, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, ProductEntry.class.getName(),
				productEntry.getProductEntryId(), actionId);
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
			PermissionChecker permissionChecker, long productEntryId,
			String actionId)
		throws PortalException {

		if (contains(
				permissionChecker,
				_productEntryLocalService.getProductEntry(productEntryId),
				actionId)) {

			return true;
		}

		return false;
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long[] productEntryIds,
			String actionId)
		throws PortalException {

		if (ArrayUtil.isEmpty(productEntryIds)) {
			return false;
		}

		for (long productEntryId : productEntryIds) {
			if (!contains(permissionChecker, productEntryId, actionId)) {
				return false;
			}
		}

		return true;
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, ProductEntry productEntry,
			String actionId)
		throws PortalException {

		if (permissionChecker.hasOwnerPermission(
				productEntry.getCompanyId(), ProductEntry.class.getName(),
				productEntry.getProductEntryId(), productEntry.getUserId(),
				actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			0, ProductEntry.class.getName(), productEntry.getProductEntryId(),
			actionId);
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
		return ProductEntry.class.getName();
	}

	@Reference
	private ProductEntryLocalService _productEntryLocalService;

}