/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.documentlibrary.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.BaseResourcePermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.kernel.util.ServiceProxyFactory;
import com.liferay.portlet.documentlibrary.constants.DLConstants;

/**
 * @author     Jorge Ferrer
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
@OSGiBeanProperties(property = "resource.name=" + DLConstants.RESOURCE_NAME)
public class DLPermission extends BaseResourcePermissionChecker {

	public static final String RESOURCE_NAME = DLConstants.RESOURCE_NAME;

	public static void check(
			PermissionChecker permissionChecker, long groupId, String actionId)
		throws PortalException {

		_portletResourcePermission.check(permissionChecker, groupId, actionId);
	}

	public static boolean contains(
		PermissionChecker permissionChecker, long classPK, String actionId) {

		return _portletResourcePermission.contains(
			permissionChecker, classPK, actionId);
	}

	@Override
	public Boolean checkResource(
		PermissionChecker permissionChecker, long classPK, String actionId) {

		return _portletResourcePermission.contains(
			permissionChecker, classPK, actionId);
	}

	private static volatile PortletResourcePermission
		_portletResourcePermission =
			ServiceProxyFactory.newServiceTrackedInstance(
				PortletResourcePermission.class, DLPermission.class,
				"_portletResourcePermission",
				"(resource.name=" + DLConstants.RESOURCE_NAME + ")", true);

}