/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.internal.security.permission.resource;

import com.liferay.osb.koroneiki.trunk.model.ProductPurchase;
import com.liferay.osb.koroneiki.trunk.permission.ProductPurchasePermission;
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
	property = "model.class.name=com.liferay.osb.koroneiki.trunk.model.ProductPurchase",
	service = ModelResourcePermission.class
)
public class ProductPurchaseModelResourcePermission
	implements ModelResourcePermission<ProductPurchase> {

	@Override
	public void check(
			PermissionChecker permissionChecker, long productPurchaseId,
			String actionId)
		throws PortalException {

		_productPurchasePermission.check(
			permissionChecker, productPurchaseId, actionId);
	}

	@Override
	public void check(
			PermissionChecker permissionChecker,
			ProductPurchase productPurchase, String actionId)
		throws PortalException {

		_productPurchasePermission.check(
			permissionChecker, productPurchase, actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long productPurchaseId,
			String actionId)
		throws PortalException {

		return _productPurchasePermission.contains(
			permissionChecker, productPurchaseId, actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker,
			ProductPurchase productPurchase, String actionId)
		throws PortalException {

		return _productPurchasePermission.contains(
			permissionChecker, productPurchase, actionId);
	}

	@Override
	public String getModelName() {
		return ProductPurchase.class.getName();
	}

	@Override
	public PortletResourcePermission getPortletResourcePermission() {
		return null;
	}

	@Reference
	private ProductPurchasePermission _productPurchasePermission;

}