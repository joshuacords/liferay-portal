/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service.impl;

import com.liferay.osb.koroneiki.root.model.ExternalLink;
import com.liferay.osb.koroneiki.root.service.ExternalLinkLocalService;
import com.liferay.osb.koroneiki.taproot.constants.TaprootActionKeys;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.model.Team;
import com.liferay.osb.koroneiki.taproot.permission.AccountPermission;
import com.liferay.osb.koroneiki.taproot.permission.TeamPermission;
import com.liferay.osb.koroneiki.taproot.service.AccountLocalService;
import com.liferay.osb.koroneiki.taproot.service.base.TeamServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	property = {
		"json.web.service.context.name=koroneiki",
		"json.web.service.context.path=Team"
	},
	service = AopService.class
)
public class TeamServiceImpl extends TeamServiceBaseImpl {

	public Team addTeam(long accountId, String name) throws PortalException {
		_teamPermission.check(
			getPermissionChecker(), TaprootActionKeys.ADD_TEAM);

		return teamLocalService.addTeam(getUserId(), accountId, name, false);
	}

	public Team addTeam(String accountKey, String name) throws PortalException {
		Account account = _accountLocalService.getAccount(accountKey);

		return addTeam(account.getAccountId(), name);
	}

	public Team deleteTeam(long teamId) throws PortalException {
		Team team = teamLocalService.getTeam(teamId);

		if (team.isSystem()) {
			throw new PrincipalException();
		}

		_teamPermission.check(
			getPermissionChecker(), teamId, ActionKeys.DELETE);

		return teamLocalService.deleteTeam(teamId);
	}

	public Team deleteTeam(String teamKey) throws PortalException {
		Team team = teamLocalService.getTeam(teamKey);

		if (team.isSystem()) {
			throw new PrincipalException();
		}

		_teamPermission.check(getPermissionChecker(), team, ActionKeys.DELETE);

		return teamLocalService.deleteTeam(team.getTeamId());
	}

	public List<Team> getAccountAssignedTeams(
			String accountKey, int start, int end)
		throws PortalException {

		Account account = _accountLocalService.getAccount(accountKey);

		_accountPermission.check(
			getPermissionChecker(), account, ActionKeys.VIEW);

		return teamLocalService.getAccountAssignedTeams(
			account.getAccountId(), start, end);
	}

	public int getAccountAssignedTeamsCount(String accountKey)
		throws PortalException {

		Account account = _accountLocalService.getAccount(accountKey);

		_accountPermission.check(
			getPermissionChecker(), account, ActionKeys.VIEW);

		return teamLocalService.getAccountAssignedTeamsCount(
			account.getAccountId());
	}

	public List<Team> getAccountTeams(long accountId, int start, int end)
		throws PortalException {

		_accountPermission.check(
			getPermissionChecker(), accountId, ActionKeys.VIEW);

		return teamLocalService.getAccountTeams(accountId, start, end);
	}

	public List<Team> getAccountTeams(String accountKey, int start, int end)
		throws PortalException {

		Account account = _accountLocalService.getAccount(accountKey);

		_accountPermission.check(
			getPermissionChecker(), account, ActionKeys.VIEW);

		return teamLocalService.getAccountTeams(
			account.getAccountId(), start, end);
	}

	public int getAccountTeamsCount(long accountId) throws PortalException {
		_accountPermission.check(
			getPermissionChecker(), accountId, ActionKeys.VIEW);

		return teamLocalService.getAccountTeamsCount(accountId);
	}

	public int getAccountTeamsCount(String accountKey) throws PortalException {
		Account account = _accountLocalService.getAccount(accountKey);

		_accountPermission.check(
			getPermissionChecker(), account, ActionKeys.VIEW);

		return teamLocalService.getAccountTeamsCount(account.getAccountId());
	}

	public Team getTeam(long teamId) throws PortalException {
		_teamPermission.check(getPermissionChecker(), teamId, ActionKeys.VIEW);

		return teamLocalService.getTeam(teamId);
	}

	public Team getTeam(String teamKey) throws PortalException {
		Team team = teamLocalService.getTeam(teamKey);

		_teamPermission.check(getPermissionChecker(), team, ActionKeys.VIEW);

		return team;
	}

	public List<Team> getTeams(
			String domain, String entityName, String entityId, int start,
			int end)
		throws PortalException {

		List<Team> teams = new ArrayList<>();

		List<ExternalLink> externalLinks =
			_externalLinkLocalService.getExternalLinks(
				classNameLocalService.getClassNameId(Team.class), domain,
				entityName, entityId, start, end);

		for (ExternalLink externalLink : externalLinks) {
			teams.add(getTeam(externalLink.getClassPK()));
		}

		return teams;
	}

	public int getTeamsCount(String domain, String entityName, String entityId)
		throws PortalException {

		return _externalLinkLocalService.getExternalLinksCount(
			classNameLocalService.getClassNameId(Team.class), domain,
			entityName, entityId);
	}

	public Team updateTeam(long teamId, String name) throws PortalException {
		Team team = teamLocalService.getTeam(teamId);

		if (team.isSystem()) {
			throw new PrincipalException();
		}

		_teamPermission.check(
			getPermissionChecker(), teamId, ActionKeys.UPDATE);

		return teamLocalService.updateTeam(teamId, name);
	}

	public Team updateTeam(String teamKey, String name) throws PortalException {
		Team team = teamLocalService.getTeam(teamKey);

		if (team.isSystem()) {
			throw new PrincipalException();
		}

		_teamPermission.check(getPermissionChecker(), team, ActionKeys.UPDATE);

		return teamLocalService.updateTeam(team.getTeamId(), name);
	}

	@Reference
	private AccountLocalService _accountLocalService;

	@Reference
	private AccountPermission _accountPermission;

	@Reference
	private ExternalLinkLocalService _externalLinkLocalService;

	@Reference
	private TeamPermission _teamPermission;

}