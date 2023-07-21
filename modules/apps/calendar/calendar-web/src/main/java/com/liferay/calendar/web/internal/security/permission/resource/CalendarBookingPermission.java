/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.calendar.web.internal.security.permission.resource;

import com.liferay.calendar.model.CalendarBooking;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jonathan McCann
 */
@Component(immediate = true, service = {})
public class CalendarBookingPermission {

	public static boolean contains(
			PermissionChecker permissionChecker,
			CalendarBooking calendarBooking, String actionId)
		throws PortalException {

		return _calendarBookingeModelResourcePermission.contains(
			permissionChecker, calendarBooking, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long calendarBookingId,
			String actionId)
		throws PortalException {

		return _calendarBookingeModelResourcePermission.contains(
			permissionChecker, calendarBookingId, actionId);
	}

	@Reference(
		target = "(model.class.name=com.liferay.calendar.model.CalendarBooking)",
		unbind = "-"
	)
	protected void setModelPermissionChecker(
		ModelResourcePermission<CalendarBooking> modelResourcePermission) {

		_calendarBookingeModelResourcePermission = modelResourcePermission;
	}

	private static ModelResourcePermission<CalendarBooking>
		_calendarBookingeModelResourcePermission;

}