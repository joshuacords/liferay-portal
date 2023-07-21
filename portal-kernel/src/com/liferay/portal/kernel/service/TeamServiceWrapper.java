/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.service;

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
	public com.liferay.portal.kernel.model.Team addTeam(
			long groupId, java.lang.String name, java.lang.String description,
			ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teamService.addTeam(groupId, name, description, serviceContext);
	}

	@Override
	public void deleteTeam(long teamId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_teamService.deleteTeam(teamId);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Team> getGroupTeams(
			long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teamService.getGroupTeams(groupId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _teamService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.Team getTeam(long teamId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teamService.getTeam(teamId);
	}

	@Override
	public com.liferay.portal.kernel.model.Team getTeam(
			long groupId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teamService.getTeam(groupId, name);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Team> getUserTeams(
			long userId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teamService.getUserTeams(userId);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Team> getUserTeams(
			long userId, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teamService.getUserTeams(userId, groupId);
	}

	@Override
	public boolean hasUserTeam(long userId, long teamId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teamService.hasUserTeam(userId, teamId);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Team> search(
		long groupId, java.lang.String name, java.lang.String description,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<com.liferay.portal.kernel.model.Team> obc) {

		return _teamService.search(
			groupId, name, description, params, start, end, obc);
	}

	@Override
	public int searchCount(
		long groupId, java.lang.String name, java.lang.String description,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params) {

		return _teamService.searchCount(groupId, name, description, params);
	}

	@Override
	public com.liferay.portal.kernel.model.Team updateTeam(
			long teamId, java.lang.String name, java.lang.String description)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teamService.updateTeam(teamId, name, description);
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