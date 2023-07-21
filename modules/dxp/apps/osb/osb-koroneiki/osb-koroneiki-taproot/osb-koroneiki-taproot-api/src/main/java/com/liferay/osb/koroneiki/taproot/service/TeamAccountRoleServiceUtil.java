/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service;

import com.liferay.osb.koroneiki.taproot.model.TeamAccountRole;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * Provides the remote service utility for TeamAccountRole. This utility wraps
 * <code>com.liferay.osb.koroneiki.taproot.service.impl.TeamAccountRoleServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see TeamAccountRoleService
 * @generated
 */
public class TeamAccountRoleServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.koroneiki.taproot.service.impl.TeamAccountRoleServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static TeamAccountRole addTeamAccountRole(
			long teamId, long accountId, long teamRoleId)
		throws PortalException {

		return getService().addTeamAccountRole(teamId, accountId, teamRoleId);
	}

	public static TeamAccountRole addTeamAccountRole(
			String teamKey, String accountKey, String teamRoleKey)
		throws PortalException {

		return getService().addTeamAccountRole(
			teamKey, accountKey, teamRoleKey);
	}

	public static TeamAccountRole deleteTeamAccountRole(
			long teamId, long accountId, long teamRoleId)
		throws PortalException {

		return getService().deleteTeamAccountRole(
			teamId, accountId, teamRoleId);
	}

	public static TeamAccountRole deleteTeamAccountRole(
			String teamKey, String accountKey, String teamRoleKey)
		throws PortalException {

		return getService().deleteTeamAccountRole(
			teamKey, accountKey, teamRoleKey);
	}

	public static void deleteTeamAccountRoles(long teamId, long accountId)
		throws PortalException {

		getService().deleteTeamAccountRoles(teamId, accountId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static TeamAccountRoleService getService() {
		return _service;
	}

	public static void setService(TeamAccountRoleService service) {
		_service = service;
	}

	private static volatile TeamAccountRoleService _service;

}