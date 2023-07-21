/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.microblogs.service.permission;

import com.liferay.microblogs.model.MicroblogsEntry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author     Jonathan Lee
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Component(immediate = true, service = {})
@Deprecated
public class MicroblogsEntryPermission {

	public static void check(
			PermissionChecker permissionChecker, long microblogsEntryId,
			String actionId)
		throws PortalException {

		_microblogsEntryModelResourcePermission.check(
			permissionChecker, microblogsEntryId, actionId);
	}

	public static void check(
			PermissionChecker permissionChecker,
			MicroblogsEntry microblogsEntry, String actionId)
		throws PortalException {

		_microblogsEntryModelResourcePermission.check(
			permissionChecker, microblogsEntry, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long microblogsEntryId,
			String actionId)
		throws PortalException {

		return _microblogsEntryModelResourcePermission.contains(
			permissionChecker, microblogsEntryId, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker,
			MicroblogsEntry microblogsEntry, String actionId)
		throws PortalException {

		return _microblogsEntryModelResourcePermission.contains(
			permissionChecker, microblogsEntry, actionId);
	}

	@Reference(
		target = "(model.class.name=com.liferay.microblogs.model.MicroblogsEntry)",
		unbind = "-"
	)
	protected void setModelResourcePermission(
		ModelResourcePermission<MicroblogsEntry> modelResourcePermission) {

		_microblogsEntryModelResourcePermission = modelResourcePermission;
	}

	private static ModelResourcePermission<MicroblogsEntry>
		_microblogsEntryModelResourcePermission;

}