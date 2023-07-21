/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link TeamAccountRoleService}.
 *
 * @author Brian Wing Shun Chan
 * @see TeamAccountRoleService
 * @generated
 */
public class TeamAccountRoleServiceWrapper
	implements ServiceWrapper<TeamAccountRoleService>, TeamAccountRoleService {

	public TeamAccountRoleServiceWrapper(
		TeamAccountRoleService teamAccountRoleService) {

		_teamAccountRoleService = teamAccountRoleService;
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.TeamAccountRole
			addTeamAccountRole(long teamId, long accountId, long teamRoleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teamAccountRoleService.addTeamAccountRole(
			teamId, accountId, teamRoleId);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.TeamAccountRole
			addTeamAccountRole(
				String teamKey, String accountKey, String teamRoleKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teamAccountRoleService.addTeamAccountRole(
			teamKey, accountKey, teamRoleKey);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.TeamAccountRole
			deleteTeamAccountRole(long teamId, long accountId, long teamRoleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teamAccountRoleService.deleteTeamAccountRole(
			teamId, accountId, teamRoleId);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.TeamAccountRole
			deleteTeamAccountRole(
				String teamKey, String accountKey, String teamRoleKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _teamAccountRoleService.deleteTeamAccountRole(
			teamKey, accountKey, teamRoleKey);
	}

	@Override
	public void deleteTeamAccountRoles(long teamId, long accountId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_teamAccountRoleService.deleteTeamAccountRoles(teamId, accountId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _teamAccountRoleService.getOSGiServiceIdentifier();
	}

	@Override
	public TeamAccountRoleService getWrappedService() {
		return _teamAccountRoleService;
	}

	@Override
	public void setWrappedService(
		TeamAccountRoleService teamAccountRoleService) {

		_teamAccountRoleService = teamAccountRoleService;
	}

	private TeamAccountRoleService _teamAccountRoleService;

}