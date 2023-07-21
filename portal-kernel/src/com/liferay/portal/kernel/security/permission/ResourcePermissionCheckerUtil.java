/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.security.permission;

import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.registry.collections.ServiceTrackerCollections;
import com.liferay.registry.collections.ServiceTrackerMap;

/**
 * @author Roberto Díaz
 */
public class ResourcePermissionCheckerUtil {

	public static Boolean containsResourcePermission(
		PermissionChecker permissionChecker, String className, long classPK,
		String actionId) {

		PortletResourcePermission portletResourcePermission =
			_portletPermissions.getService(className);

		if (portletResourcePermission != null) {
			return portletResourcePermission.contains(
				permissionChecker, classPK, actionId);
		}

		ResourcePermissionChecker resourcePermissionChecker =
			_resourcePermissionCheckers.getService(className);

		if (resourcePermissionChecker == null) {
			return null;
		}

		return resourcePermissionChecker.checkResource(
			permissionChecker, classPK, actionId);
	}

	private static final ServiceTrackerMap<String, PortletResourcePermission>
		_portletPermissions = ServiceTrackerCollections.openSingleValueMap(
			PortletResourcePermission.class, "resource.name");
	private static final ServiceTrackerMap<String, ResourcePermissionChecker>
		_resourcePermissionCheckers =
			ServiceTrackerCollections.openSingleValueMap(
				ResourcePermissionChecker.class, "resource.name");

}