/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.calendar.web.internal.security.permission.resource;

import com.liferay.calendar.model.CalendarResource;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Preston Crary
 */
@Component(immediate = true, service = {})
public class CalendarResourcePermission {

	public static boolean contains(
			PermissionChecker permissionChecker,
			CalendarResource calendarResource, String actionId)
		throws PortalException {

		return _calendarResourceModelResourcePermission.contains(
			permissionChecker, calendarResource, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long calendarResourceId,
			String actionId)
		throws PortalException {

		return _calendarResourceModelResourcePermission.contains(
			permissionChecker, calendarResourceId, actionId);
	}

	@Reference(
		target = "(model.class.name=com.liferay.calendar.model.CalendarResource)",
		unbind = "-"
	)
	protected void setModelPermissionChecker(
		ModelResourcePermission<CalendarResource> modelResourcePermission) {

		_calendarResourceModelResourcePermission = modelResourcePermission;
	}

	private static ModelResourcePermission<CalendarResource>
		_calendarResourceModelResourcePermission;

}