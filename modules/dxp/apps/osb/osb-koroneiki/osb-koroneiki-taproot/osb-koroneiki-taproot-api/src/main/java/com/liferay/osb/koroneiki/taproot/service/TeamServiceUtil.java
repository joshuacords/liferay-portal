/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.koroneiki.taproot.service;

import com.liferay.osb.koroneiki.taproot.model.Team;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

/**
 * Provides the remote service utility for Team. This utility wraps
 * <code>com.liferay.osb.koroneiki.taproot.service.impl.TeamServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see TeamService
 * @generated
 */
public class TeamServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.koroneiki.taproot.service.impl.TeamServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static Team addTeam(long accountId, String name)
		throws PortalException {

		return getService().addTeam(accountId, name);
	}

	public static Team addTeam(String accountKey, String name)
		throws PortalException {

		return getService().addTeam(accountKey, name);
	}

	public static Team deleteTeam(long teamId) throws PortalException {
		return getService().deleteTeam(teamId);
	}

	public static Team deleteTeam(String teamKey) throws PortalException {
		return getService().deleteTeam(teamKey);
	}

	public static List<Team> getAccountAssignedTeams(
			String accountKey, int start, int end)
		throws PortalException {

		return getService().getAccountAssignedTeams(accountKey, start, end);
	}

	public static int getAccountAssignedTeamsCount(String accountKey)
		throws PortalException {

		return getService().getAccountAssignedTeamsCount(accountKey);
	}

	public static List<Team> getAccountTeams(long accountId, int start, int end)
		throws PortalException {

		return getService().getAccountTeams(accountId, start, end);
	}

	public static List<Team> getAccountTeams(
			String accountKey, int start, int end)
		throws PortalException {

		return getService().getAccountTeams(accountKey, start, end);
	}

	public static int getAccountTeamsCount(long accountId)
		throws PortalException {

		return getService().getAccountTeamsCount(accountId);
	}

	public static int getAccountTeamsCount(String accountKey)
		throws PortalException {

		return getService().getAccountTeamsCount(accountKey);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static Team getTeam(long teamId) throws PortalException {
		return getService().getTeam(teamId);
	}

	public static Team getTeam(String teamKey) throws PortalException {
		return getService().getTeam(teamKey);
	}

	public static List<Team> getTeams(
			String domain, String entityName, String entityId, int start,
			int end)
		throws PortalException {

		return getService().getTeams(domain, entityName, entityId, start, end);
	}

	public static int getTeamsCount(
			String domain, String entityName, String entityId)
		throws PortalException {

		return getService().getTeamsCount(domain, entityName, entityId);
	}

	public static Team updateTeam(long teamId, String name)
		throws PortalException {

		return getService().updateTeam(teamId, name);
	}

	public static Team updateTeam(String teamKey, String name)
		throws PortalException {

		return getService().updateTeam(teamKey, name);
	}

	public static TeamService getService() {
		return _service;
	}

	public static void setService(TeamService service) {
		_service = service;
	}

	private static volatile TeamService _service;

}