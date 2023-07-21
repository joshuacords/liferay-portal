/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link TeamRoleService}.
 *
 * @author Brian Wing Shun Chan
 * @see TeamRoleService
 * @generated
 */
public class TeamRoleServiceWrapper
	implements ServiceWrapper<TeamRoleService>, TeamRoleService {

	public TeamRoleServiceWrapper(TeamRoleService teamRoleService) {
		_teamRoleService = teamRoleService;
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.TeamRole addTeamRole(
			String name, String description, String type)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teamRoleService.addTeamRole(name, description, type);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.TeamRole deleteTeamRole(
			long teamRoleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teamRoleService.deleteTeamRole(teamRoleId);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.TeamRole deleteTeamRole(
			String teamRoleKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teamRoleService.deleteTeamRole(teamRoleKey);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _teamRoleService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.osb.koroneiki.taproot.model.TeamRole>
			getTeamAccountTeamRoles(
				long accountId, long teamId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teamRoleService.getTeamAccountTeamRoles(
			accountId, teamId, start, end);
	}

	@Override
	public int getTeamAccountTeamRolesCount(long accountId, long teamId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teamRoleService.getTeamAccountTeamRolesCount(accountId, teamId);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.TeamRole getTeamRole(
			long teamRoleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teamRoleService.getTeamRole(teamRoleId);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.TeamRole getTeamRole(
			String teamRoleKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teamRoleService.getTeamRole(teamRoleKey);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.TeamRole getTeamRole(
			String name, String type)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teamRoleService.getTeamRole(name, type);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.TeamRole updateTeamRole(
			long teamRoleId, String name, String description)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teamRoleService.updateTeamRole(teamRoleId, name, description);
	}

	@Override
	public TeamRoleService getWrappedService() {
		return _teamRoleService;
	}

	@Override
	public void setWrappedService(TeamRoleService teamRoleService) {
		_teamRoleService = teamRoleService;
	}

	private TeamRoleService _teamRoleService;

}