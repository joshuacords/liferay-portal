/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service.impl;

import com.liferay.osb.koroneiki.taproot.constants.TaprootActionKeys;
import com.liferay.osb.koroneiki.taproot.model.TeamRole;
import com.liferay.osb.koroneiki.taproot.permission.AccountPermission;
import com.liferay.osb.koroneiki.taproot.permission.TeamPermission;
import com.liferay.osb.koroneiki.taproot.permission.TeamRolePermission;
import com.liferay.osb.koroneiki.taproot.service.base.TeamRoleServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	property = {
		"json.web.service.context.name=koroneiki",
		"json.web.service.context.path=TeamRole"
	},
	service = AopService.class
)
public class TeamRoleServiceImpl extends TeamRoleServiceBaseImpl {

	public TeamRole addTeamRole(String name, String description, String type)
		throws PortalException {

		_teamRolePermission.check(
			getPermissionChecker(), TaprootActionKeys.ADD_TEAM_ROLE);

		return teamRoleLocalService.addTeamRole(
			getUserId(), name, description, type);
	}

	public TeamRole deleteTeamRole(long teamRoleId) throws PortalException {
		_teamRolePermission.check(
			getPermissionChecker(), teamRoleId, ActionKeys.DELETE);

		return teamRoleLocalService.deleteTeamRole(teamRoleId);
	}

	public TeamRole deleteTeamRole(String teamRoleKey) throws PortalException {
		TeamRole teamRole = teamRoleLocalService.getTeamRole(teamRoleKey);

		_teamRolePermission.check(
			getPermissionChecker(), teamRole, ActionKeys.DELETE);

		return teamRoleLocalService.deleteTeamRole(teamRole);
	}

	public List<TeamRole> getTeamAccountTeamRoles(
			long accountId, long teamId, int start, int end)
		throws PortalException {

		_accountPermission.check(
			getPermissionChecker(), accountId, ActionKeys.VIEW);

		_teamPermission.check(getPermissionChecker(), teamId, ActionKeys.VIEW);

		return teamRoleLocalService.getTeamAccountTeamRoles(
			accountId, teamId, start, end);
	}

	public int getTeamAccountTeamRolesCount(long accountId, long teamId)
		throws PortalException {

		_accountPermission.check(
			getPermissionChecker(), accountId, ActionKeys.VIEW);

		_teamPermission.check(getPermissionChecker(), teamId, ActionKeys.VIEW);

		return teamRoleLocalService.getTeamAccountTeamRolesCount(
			accountId, teamId);
	}

	public TeamRole getTeamRole(long teamRoleId) throws PortalException {
		_teamRolePermission.check(
			getPermissionChecker(), teamRoleId, ActionKeys.VIEW);

		return teamRoleLocalService.getTeamRole(teamRoleId);
	}

	public TeamRole getTeamRole(String teamRoleKey) throws PortalException {
		TeamRole teamRole = teamRoleLocalService.getTeamRole(teamRoleKey);

		_teamRolePermission.check(
			getPermissionChecker(), teamRole, ActionKeys.VIEW);

		return teamRole;
	}

	public TeamRole getTeamRole(String name, String type)
		throws PortalException {

		TeamRole teamRole = teamRoleLocalService.getTeamRole(name, type);

		_teamRolePermission.check(
			getPermissionChecker(), teamRole, ActionKeys.VIEW);

		return teamRole;
	}

	public TeamRole updateTeamRole(
			long teamRoleId, String name, String description)
		throws PortalException {

		_teamRolePermission.check(
			getPermissionChecker(), teamRoleId, ActionKeys.UPDATE);

		return teamRoleLocalService.updateTeamRole(
			getUserId(), teamRoleId, name, description);
	}

	@Reference
	private AccountPermission _accountPermission;

	@Reference
	private TeamPermission _teamPermission;

	@Reference
	private TeamRolePermission _teamRolePermission;

}