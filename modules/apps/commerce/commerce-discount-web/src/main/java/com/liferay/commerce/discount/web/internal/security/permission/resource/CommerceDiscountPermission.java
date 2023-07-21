/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.discount.web.internal.security.permission.resource;

import com.liferay.commerce.discount.model.CommerceDiscount;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = CommerceDiscountPermission.class)
public class CommerceDiscountPermission {

	public static boolean contains(
			PermissionChecker permissionChecker,
			CommerceDiscount commerceDiscount, String actionId)
		throws PortalException {

		return _commerceDiscountModelResourcePermission.contains(
			permissionChecker, commerceDiscount, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long commerceDiscountId,
			String actionId)
		throws PortalException {

		return _commerceDiscountModelResourcePermission.contains(
			permissionChecker, commerceDiscountId, actionId);
	}

	@Reference(
		target = "(model.class.name=com.liferay.commerce.discount.model.CommerceDiscount)",
		unbind = "-"
	)
	protected void setModelResourcePermission(
		ModelResourcePermission<CommerceDiscount> modelResourcePermission) {

		_commerceDiscountModelResourcePermission = modelResourcePermission;
	}

	private static ModelResourcePermission<CommerceDiscount>
		_commerceDiscountModelResourcePermission;

}