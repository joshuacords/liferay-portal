/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.site.navigation.admin.web.internal.security.permission.resource;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.site.navigation.model.SiteNavigationMenu;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Preston Crary
 */
@Component(immediate = true, service = {})
public class SiteNavigationMenuPermission {

	public static boolean contains(
			PermissionChecker permissionChecker, long siteNavigationMenuId,
			String actionId)
		throws PortalException {

		return _siteNavigationMenuModelResourcePermission.contains(
			permissionChecker, siteNavigationMenuId, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker,
			SiteNavigationMenu siteNavigationMenu, String actionId)
		throws PortalException {

		return _siteNavigationMenuModelResourcePermission.contains(
			permissionChecker, siteNavigationMenu, actionId);
	}

	@Reference(
		target = "(model.class.name=com.liferay.site.navigation.model.SiteNavigationMenu)",
		unbind = "-"
	)
	protected void setModelResourcePermission(
		ModelResourcePermission<SiteNavigationMenu> modelResourcePermission) {

		_siteNavigationMenuModelResourcePermission = modelResourcePermission;
	}

	private static ModelResourcePermission<SiteNavigationMenu>
		_siteNavigationMenuModelResourcePermission;

}