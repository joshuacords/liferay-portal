/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service.impl;

import com.liferay.osb.koroneiki.taproot.exception.TeamRoleTypeException;
import com.liferay.osb.koroneiki.taproot.model.Team;
import com.liferay.osb.koroneiki.taproot.model.TeamAccountRole;
import com.liferay.osb.koroneiki.taproot.model.TeamRole;
import com.liferay.osb.koroneiki.taproot.service.AccountLocalService;
import com.liferay.osb.koroneiki.taproot.service.TeamLocalService;
import com.liferay.osb.koroneiki.taproot.service.base.TeamAccountRoleLocalServiceBaseImpl;
import com.liferay.osb.koroneiki.taproot.service.persistence.TeamAccountRolePK;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	property = "model.class.name=com.liferay.osb.koroneiki.taproot.model.TeamAccountRole",
	service = AopService.class
)
public class TeamAccountRoleLocalServiceImpl
	extends TeamAccountRoleLocalServiceBaseImpl {

	public TeamAccountRole addTeamAccountRole(
			long teamId, long accountId, long teamRoleId)
		throws PortalException {

		validate(teamId, accountId, teamRoleId);

		TeamAccountRolePK teamAccountRolePK = new TeamAccountRolePK(
			teamId, accountId, teamRoleId);

		TeamAccountRole teamAccountRole =
			teamAccountRolePersistence.fetchByPrimaryKey(teamAccountRolePK);

		if (teamAccountRole == null) {
			teamAccountRole = teamAccountRolePersistence.create(
				teamAccountRolePK);

			teamAccountRole = teamAccountRolePersistence.update(
				teamAccountRole);

			_accountLocalService.reindex(accountId);

			Team team = _teamLocalService.reindex(teamId);

			_accountLocalService.reindex(team.getAccountId());
		}

		return teamAccountRole;
	}

	public TeamAccountRole deleteTeamAccountRole(
			long teamId, long accountId, long teamRoleId)
		throws PortalException {

		TeamAccountRolePK teamAccountRolePK = new TeamAccountRolePK(
			teamId, accountId, teamRoleId);

		TeamAccountRole teamAccountRole =
			teamAccountRolePersistence.fetchByPrimaryKey(teamAccountRolePK);

		if (teamAccountRole != null) {
			deleteTeamAccountRole(teamAccountRole);

			_accountLocalService.reindex(accountId);

			Team team = _teamLocalService.reindex(teamId);

			_accountLocalService.reindex(team.getAccountId());
		}

		return teamAccountRole;
	}

	public void deleteTeamAccountRoles(long teamId, long accountId)
		throws PortalException {

		teamAccountRolePersistence.removeByTI_AI(teamId, accountId);

		_accountLocalService.reindex(accountId);

		Team team = _teamLocalService.reindex(teamId);

		_accountLocalService.reindex(team.getAccountId());
	}

	public List<TeamAccountRole> getTeamAccountRoles(long teamId) {
		return teamAccountRolePersistence.findByTeamId(teamId);
	}

	public List<TeamAccountRole> getTeamAccountRolesByAccountId(
		long accountId) {

		return teamAccountRolePersistence.findByAccountId(accountId);
	}

	protected void validate(long teamId, long accountId, long teamRoleId)
		throws PortalException {

		teamPersistence.findByPrimaryKey(teamId);

		accountPersistence.findByPrimaryKey(accountId);

		TeamRole teamRole = teamRolePersistence.findByPrimaryKey(teamRoleId);

		String type = teamRole.getType();

		if (!type.equals(
				com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.TeamRole.Type.
					ACCOUNT.toString())) {

			throw new TeamRoleTypeException();
		}
	}

	@Reference
	private AccountLocalService _accountLocalService;

	@Reference
	private TeamLocalService _teamLocalService;

}