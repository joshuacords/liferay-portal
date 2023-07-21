/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.internal.security.permission.resource;

import com.liferay.osb.koroneiki.taproot.model.TeamRole;
import com.liferay.osb.koroneiki.taproot.permission.TeamRolePermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true,
	property = "model.class.name=com.liferay.osb.koroneiki.taproot.model.TeamRole",
	service = ModelResourcePermission.class
)
public class TeamRoleModelResourcePermission
	implements ModelResourcePermission<TeamRole> {

	@Override
	public void check(
			PermissionChecker permissionChecker, long teamRoleId,
			String actionId)
		throws PortalException {

		_teamRolePermission.check(permissionChecker, teamRoleId, actionId);
	}

	@Override
	public void check(
			PermissionChecker permissionChecker, TeamRole teamRole,
			String actionId)
		throws PortalException {

		_teamRolePermission.check(permissionChecker, teamRole, actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long teamRoleId,
			String actionId)
		throws PortalException {

		return _teamRolePermission.contains(
			permissionChecker, teamRoleId, actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, TeamRole teamRole,
			String actionId)
		throws PortalException {

		return _teamRolePermission.contains(
			permissionChecker, teamRole, actionId);
	}

	@Override
	public String getModelName() {
		return TeamRole.class.getName();
	}

	@Override
	public PortletResourcePermission getPortletResourcePermission() {
		return null;
	}

	@Reference
	private TeamRolePermission _teamRolePermission;

}