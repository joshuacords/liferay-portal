/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.internal.security.permission.resource;

import com.liferay.osb.koroneiki.trunk.model.ProductEntry;
import com.liferay.osb.koroneiki.trunk.permission.ProductEntryPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true,
	property = "model.class.name=com.liferay.osb.koroneiki.trunk.model.ProductEntry",
	service = ModelResourcePermission.class
)
public class ProductEntryModelResourcePermission
	implements ModelResourcePermission<ProductEntry> {

	@Override
	public void check(
			PermissionChecker permissionChecker, long productEntryId,
			String actionId)
		throws PortalException {

		_productEntryPermission.check(
			permissionChecker, productEntryId, actionId);
	}

	@Override
	public void check(
			PermissionChecker permissionChecker, ProductEntry productEntry,
			String actionId)
		throws PortalException {

		_productEntryPermission.check(
			permissionChecker, productEntry, actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long productEntryId,
			String actionId)
		throws PortalException {

		return _productEntryPermission.contains(
			permissionChecker, productEntryId, actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, ProductEntry productEntry,
			String actionId)
		throws PortalException {

		return _productEntryPermission.contains(
			permissionChecker, productEntry, actionId);
	}

	@Override
	public String getModelName() {
		return ProductEntry.class.getName();
	}

	@Override
	public PortletResourcePermission getPortletResourcePermission() {
		return null;
	}

	@Reference
	private ProductEntryPermission _productEntryPermission;

}