/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.order.web.internal.security.permission.resource;

import com.liferay.commerce.model.CommerceOrder;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 */
@Component(immediate = true, service = CommerceOrderPermission.class)
public class CommerceOrderPermission {

	public static boolean contains(
			PermissionChecker permissionChecker, CommerceOrder commerceOrder,
			String actionId)
		throws PortalException {

		return _commerceOrderModelResourcePermission.contains(
			permissionChecker, commerceOrder, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long commerceOrderId,
			String actionId)
		throws PortalException {

		return _commerceOrderModelResourcePermission.contains(
			permissionChecker, commerceOrderId, actionId);
	}

	@Reference(
		target = "(model.class.name=com.liferay.commerce.model.CommerceOrder)",
		unbind = "-"
	)
	protected void setModelResourcePermission(
		ModelResourcePermission<CommerceOrder> modelResourcePermission) {

		_commerceOrderModelResourcePermission = modelResourcePermission;
	}

	private static ModelResourcePermission<CommerceOrder>
		_commerceOrderModelResourcePermission;

}