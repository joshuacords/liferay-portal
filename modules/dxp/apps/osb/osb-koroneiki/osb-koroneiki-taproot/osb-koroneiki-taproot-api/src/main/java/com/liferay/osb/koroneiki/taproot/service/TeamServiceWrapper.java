/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link TeamService}.
 *
 * @author Brian Wing Shun Chan
 * @see TeamService
 * @generated
 */
public class TeamServiceWrapper
	implements ServiceWrapper<TeamService>, TeamService {

	public TeamServiceWrapper(TeamService teamService) {
		_teamService = teamService;
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.Team addTeam(
			long accountId, String name)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teamService.addTeam(accountId, name);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.Team addTeam(
			String accountKey, String name)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teamService.addTeam(accountKey, name);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.Team deleteTeam(long teamId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teamService.deleteTeam(teamId);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.Team deleteTeam(
			String teamKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teamService.deleteTeam(teamKey);
	}

	@Override
	public java.util.List<com.liferay.osb.koroneiki.taproot.model.Team>
			getAccountAssignedTeams(String accountKey, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teamService.getAccountAssignedTeams(accountKey, start, end);
	}

	@Override
	public int getAccountAssignedTeamsCount(String accountKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teamService.getAccountAssignedTeamsCount(accountKey);
	}

	@Override
	public java.util.List<com.liferay.osb.koroneiki.taproot.model.Team>
			getAccountTeams(long accountId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teamService.getAccountTeams(accountId, start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.koroneiki.taproot.model.Team>
			getAccountTeams(String accountKey, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teamService.getAccountTeams(accountKey, start, end);
	}

	@Override
	public int getAccountTeamsCount(long accountId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teamService.getAccountTeamsCount(accountId);
	}

	@Override
	public int getAccountTeamsCount(String accountKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teamService.getAccountTeamsCount(accountKey);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _teamService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.Team getTeam(long teamId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teamService.getTeam(teamId);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.Team getTeam(String teamKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teamService.getTeam(teamKey);
	}

	@Override
	public java.util.List<com.liferay.osb.koroneiki.taproot.model.Team>
			getTeams(
				String domain, String entityName, String entityId, int start,
				int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teamService.getTeams(domain, entityName, entityId, start, end);
	}

	@Override
	public int getTeamsCount(String domain, String entityName, String entityId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teamService.getTeamsCount(domain, entityName, entityId);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.Team updateTeam(
			long teamId, String name)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teamService.updateTeam(teamId, name);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.Team updateTeam(
			String teamKey, String name)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teamService.updateTeam(teamKey, name);
	}

	@Override
	public TeamService getWrappedService() {
		return _teamService;
	}

	@Override
	public void setWrappedService(TeamService teamService) {
		_teamService = teamService;
	}

	private TeamService _teamService;

}