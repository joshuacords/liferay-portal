/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.internal.security.permission.resource;

import com.liferay.osb.koroneiki.trunk.model.ProductConsumption;
import com.liferay.osb.koroneiki.trunk.permission.ProductConsumptionPermission;
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
	property = "model.class.name=com.liferay.osb.koroneiki.trunk.model.ProductConsumption",
	service = ModelResourcePermission.class
)
public class ProductConsumptionModelResourcePermission
	implements ModelResourcePermission<ProductConsumption> {

	@Override
	public void check(
			PermissionChecker permissionChecker, long productConsumptionId,
			String actionId)
		throws PortalException {

		_productConsumptionPermission.check(
			permissionChecker, productConsumptionId, actionId);
	}

	@Override
	public void check(
			PermissionChecker permissionChecker,
			ProductConsumption productConsumption, String actionId)
		throws PortalException {

		_productConsumptionPermission.check(
			permissionChecker, productConsumption, actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long productConsumptionId,
			String actionId)
		throws PortalException {

		return _productConsumptionPermission.contains(
			permissionChecker, productConsumptionId, actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker,
			ProductConsumption productConsumption, String actionId)
		throws PortalException {

		return _productConsumptionPermission.contains(
			permissionChecker, productConsumption, actionId);
	}

	@Override
	public String getModelName() {
		return ProductConsumption.class.getName();
	}

	@Override
	public PortletResourcePermission getPortletResourcePermission() {
		return null;
	}

	@Reference
	private ProductConsumptionPermission _productConsumptionPermission;

}