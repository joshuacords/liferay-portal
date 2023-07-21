/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.permission;

import com.liferay.osb.koroneiki.taproot.model.TeamRole;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

/**
 * @author Kyle Bischof
 */
public interface TeamRolePermission {

	public void check(
			PermissionChecker permissionChecker, long teamRoleId,
			String actionId)
		throws PortalException;

	public void check(PermissionChecker permissionChecker, String actionId)
		throws PortalException;

	public void check(
			PermissionChecker permissionChecker, TeamRole teamRole,
			String actionId)
		throws PortalException;

	public boolean contains(
			PermissionChecker permissionChecker, long teamRoleId,
			String actionId)
		throws PortalException;

	public boolean contains(
			PermissionChecker permissionChecker, long[] teamRoleIds,
			String actionId)
		throws PortalException;

	public boolean contains(
			PermissionChecker permissionChecker, String actionId)
		throws PortalException;

	public boolean contains(
			PermissionChecker permissionChecker, TeamRole teamRole,
			String actionId)
		throws PortalException;

}