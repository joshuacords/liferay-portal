/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service;

import com.liferay.osb.koroneiki.taproot.model.TeamRole;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

/**
 * Provides the remote service utility for TeamRole. This utility wraps
 * <code>com.liferay.osb.koroneiki.taproot.service.impl.TeamRoleServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see TeamRoleService
 * @generated
 */
public class TeamRoleServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.koroneiki.taproot.service.impl.TeamRoleServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static TeamRole addTeamRole(
			String name, String description, String type)
		throws PortalException {

		return getService().addTeamRole(name, description, type);
	}

	public static TeamRole deleteTeamRole(long teamRoleId)
		throws PortalException {

		return getService().deleteTeamRole(teamRoleId);
	}

	public static TeamRole deleteTeamRole(String teamRoleKey)
		throws PortalException {

		return getService().deleteTeamRole(teamRoleKey);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static List<TeamRole> getTeamAccountTeamRoles(
			long accountId, long teamId, int start, int end)
		throws PortalException {

		return getService().getTeamAccountTeamRoles(
			accountId, teamId, start, end);
	}

	public static int getTeamAccountTeamRolesCount(long accountId, long teamId)
		throws PortalException {

		return getService().getTeamAccountTeamRolesCount(accountId, teamId);
	}

	public static TeamRole getTeamRole(long teamRoleId) throws PortalException {
		return getService().getTeamRole(teamRoleId);
	}

	public static TeamRole getTeamRole(String teamRoleKey)
		throws PortalException {

		return getService().getTeamRole(teamRoleKey);
	}

	public static TeamRole getTeamRole(String name, String type)
		throws PortalException {

		return getService().getTeamRole(name, type);
	}

	public static TeamRole updateTeamRole(
			long teamRoleId, String name, String description)
		throws PortalException {

		return getService().updateTeamRole(teamRoleId, name, description);
	}

	public static TeamRoleService getService() {
		return _service;
	}

	public static void setService(TeamRoleService service) {
		_service = service;
	}

	private static volatile TeamRoleService _service;

}