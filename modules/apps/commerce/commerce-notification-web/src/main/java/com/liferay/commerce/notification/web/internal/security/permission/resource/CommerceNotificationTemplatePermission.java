/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.notification.web.internal.security.permission.resource;

import com.liferay.commerce.notification.model.CommerceNotificationTemplate;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true, service = CommerceNotificationTemplatePermission.class
)
public class CommerceNotificationTemplatePermission {

	public static boolean contains(
			PermissionChecker permissionChecker,
			CommerceNotificationTemplate commerceNotificationTemplate,
			String actionId)
		throws PortalException {

		return _commerceNotificationTemplateModelResourcePermission.contains(
			permissionChecker, commerceNotificationTemplate, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long commerceDiscountId,
			String actionId)
		throws PortalException {

		return _commerceNotificationTemplateModelResourcePermission.contains(
			permissionChecker, commerceDiscountId, actionId);
	}

	@Reference(
		target = "(model.class.name=com.liferay.commerce.notification.model.CommerceNotificationTemplate)",
		unbind = "-"
	)
	protected void setModelResourcePermission(
		ModelResourcePermission<CommerceNotificationTemplate>
			modelResourcePermission) {

		_commerceNotificationTemplateModelResourcePermission =
			modelResourcePermission;
	}

	private static ModelResourcePermission<CommerceNotificationTemplate>
		_commerceNotificationTemplateModelResourcePermission;

}