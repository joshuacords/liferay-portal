/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service.impl;

import com.liferay.osb.koroneiki.taproot.constants.TaprootActionKeys;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.model.Team;
import com.liferay.osb.koroneiki.taproot.model.TeamAccountRole;
import com.liferay.osb.koroneiki.taproot.model.TeamRole;
import com.liferay.osb.koroneiki.taproot.permission.AccountPermission;
import com.liferay.osb.koroneiki.taproot.permission.TeamPermission;
import com.liferay.osb.koroneiki.taproot.permission.TeamRolePermission;
import com.liferay.osb.koroneiki.taproot.service.AccountLocalService;
import com.liferay.osb.koroneiki.taproot.service.TeamLocalService;
import com.liferay.osb.koroneiki.taproot.service.TeamRoleLocalService;
import com.liferay.osb.koroneiki.taproot.service.base.TeamAccountRoleServiceBaseImpl;
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
		"json.web.service.context.path=TeamAccountRole"
	},
	service = AopService.class
)
public class TeamAccountRoleServiceImpl extends TeamAccountRoleServiceBaseImpl {

	public TeamAccountRole addTeamAccountRole(
			long teamId, long accountId, long teamRoleId)
		throws PortalException {

		_teamPermission.check(getPermissionChecker(), teamId, ActionKeys.VIEW);

		_accountPermission.check(
			getPermissionChecker(), accountId, TaprootActionKeys.ASSIGN_TEAM);

		_teamRolePermission.check(
			getPermissionChecker(), teamRoleId, TaprootActionKeys.ASSIGN_TEAM);

		return teamAccountRoleLocalService.addTeamAccountRole(
			teamId, accountId, teamRoleId);
	}

	public TeamAccountRole addTeamAccountRole(
			String teamKey, String accountKey, String teamRoleKey)
		throws PortalException {

		Team team = _teamLocalService.getTeam(teamKey);
		Account account = _accountLocalService.getAccount(accountKey);
		TeamRole teamRole = _teamRoleLocalService.getTeamRole(teamRoleKey);

		_teamPermission.check(getPermissionChecker(), team, ActionKeys.VIEW);

		_accountPermission.check(
			getPermissionChecker(), account, TaprootActionKeys.ASSIGN_TEAM);

		_teamRolePermission.check(
			getPermissionChecker(), teamRole, TaprootActionKeys.ASSIGN_TEAM);

		return teamAccountRoleLocalService.addTeamAccountRole(
			team.getTeamId(), account.getAccountId(), teamRole.getTeamRoleId());
	}

	public TeamAccountRole deleteTeamAccountRole(
			long teamId, long accountId, long teamRoleId)
		throws PortalException {

		_teamPermission.check(getPermissionChecker(), teamId, ActionKeys.VIEW);

		_accountPermission.check(
			getPermissionChecker(), accountId, TaprootActionKeys.ASSIGN_TEAM);

		_teamRolePermission.check(
			getPermissionChecker(), teamRoleId, TaprootActionKeys.ASSIGN_TEAM);

		return teamAccountRoleLocalService.deleteTeamAccountRole(
			teamId, accountId, teamRoleId);
	}

	public TeamAccountRole deleteTeamAccountRole(
			String teamKey, String accountKey, String teamRoleKey)
		throws PortalException {

		Team team = _teamLocalService.getTeam(teamKey);
		Account account = _accountLocalService.getAccount(accountKey);
		TeamRole teamRole = _teamRoleLocalService.getTeamRole(teamRoleKey);

		_teamPermission.check(getPermissionChecker(), team, ActionKeys.VIEW);

		_accountPermission.check(
			getPermissionChecker(), account, TaprootActionKeys.ASSIGN_TEAM);

		_teamRolePermission.check(
			getPermissionChecker(), teamRole, TaprootActionKeys.ASSIGN_TEAM);

		return teamAccountRoleLocalService.deleteTeamAccountRole(
			team.getTeamId(), account.getAccountId(), teamRole.getTeamRoleId());
	}

	public void deleteTeamAccountRoles(long teamId, long accountId)
		throws PortalException {

		_teamPermission.check(getPermissionChecker(), teamId, ActionKeys.VIEW);

		_accountPermission.check(
			getPermissionChecker(), accountId, TaprootActionKeys.ASSIGN_TEAM);

		List<TeamAccountRole> teamAccountRoles =
			teamAccountRolePersistence.findByTI_AI(teamId, accountId);

		for (TeamAccountRole teamAccountRole : teamAccountRoles) {
			_teamRolePermission.check(
				getPermissionChecker(), teamAccountRole.getTeamRoleId(),
				TaprootActionKeys.ASSIGN_TEAM);
		}

		teamAccountRoleLocalService.deleteTeamAccountRoles(teamId, accountId);
	}

	@Reference
	private AccountLocalService _accountLocalService;

	@Reference
	private AccountPermission _accountPermission;

	@Reference
	private TeamLocalService _teamLocalService;

	@Reference
	private TeamPermission _teamPermission;

	@Reference
	private TeamRoleLocalService _teamRoleLocalService;

	@Reference
	private TeamRolePermission _teamRolePermission;

}