/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.type.virtual.order.content.web.internal.security.resource.permission;

import com.liferay.commerce.product.type.virtual.order.model.CommerceVirtualOrderItem;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = CommerceVirtualOrderItemPermission.class)
public class CommerceVirtualOrderItemPermission {

	public static boolean contains(
			PermissionChecker permissionChecker,
			CommerceVirtualOrderItem commerceVirtualOrderItem, String actionId)
		throws PortalException {

		return _commerceVirtualOrderItemModelResourcePermission.contains(
			permissionChecker, commerceVirtualOrderItem, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker,
			long commerceVirtualOrderItemId, String actionId)
		throws PortalException {

		return _commerceVirtualOrderItemModelResourcePermission.contains(
			permissionChecker, commerceVirtualOrderItemId, actionId);
	}

	@Reference(
		target = "(model.class.name=com.liferay.commerce.product.type.virtual.order.model.CommerceVirtualOrderItem)",
		unbind = "-"
	)
	protected void setModelResourcePermission(
		ModelResourcePermission<CommerceVirtualOrderItem>
			modelResourcePermission) {

		_commerceVirtualOrderItemModelResourcePermission =
			modelResourcePermission;
	}

	private static ModelResourcePermission<CommerceVirtualOrderItem>
		_commerceVirtualOrderItemModelResourcePermission;

}