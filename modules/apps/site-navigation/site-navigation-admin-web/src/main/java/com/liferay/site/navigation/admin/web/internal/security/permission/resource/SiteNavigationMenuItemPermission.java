/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.site.navigation.admin.web.internal.security.permission.resource;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.site.navigation.model.SiteNavigationMenuItem;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Preston Crary
 */
@Component(immediate = true, service = {})
public class SiteNavigationMenuItemPermission {

	public static boolean contains(
			PermissionChecker permissionChecker, long siteNavigationMenuItemId,
			String actionId)
		throws PortalException {

		return _siteNavigationMenuItemModelResourcePermission.contains(
			permissionChecker, siteNavigationMenuItemId, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker,
			SiteNavigationMenuItem siteNavigationMenuItem, String actionId)
		throws PortalException {

		return _siteNavigationMenuItemModelResourcePermission.contains(
			permissionChecker, siteNavigationMenuItem, actionId);
	}

	@Reference(
		target = "(model.class.name=com.liferay.site.navigation.model.SiteNavigationMenuItem)",
		unbind = "-"
	)
	protected void setModelResourcePermission(
		ModelResourcePermission<SiteNavigationMenuItem>
			modelResourcePermission) {

		_siteNavigationMenuItemModelResourcePermission =
			modelResourcePermission;
	}

	private static ModelResourcePermission<SiteNavigationMenuItem>
		_siteNavigationMenuItemModelResourcePermission;

}