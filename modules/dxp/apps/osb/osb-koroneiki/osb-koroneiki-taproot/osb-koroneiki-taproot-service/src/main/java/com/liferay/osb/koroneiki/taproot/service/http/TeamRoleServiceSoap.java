/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service.http;

import com.liferay.osb.koroneiki.taproot.service.TeamRoleServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * <code>TeamRoleServiceUtil</code> service
 * utility. The static methods of this class call the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a <code>java.util.List</code>,
 * that is translated to an array of
 * <code>com.liferay.osb.koroneiki.taproot.model.TeamRoleSoap</code>. If the method in the
 * service utility returns a
 * <code>com.liferay.osb.koroneiki.taproot.model.TeamRole</code>, that is translated to a
 * <code>com.liferay.osb.koroneiki.taproot.model.TeamRoleSoap</code>. Methods that SOAP
 * cannot safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TeamRoleServiceHttp
 * @generated
 */
public class TeamRoleServiceSoap {

	public static com.liferay.osb.koroneiki.taproot.model.TeamRoleSoap
			addTeamRole(String name, String description, String type)
		throws RemoteException {

		try {
			com.liferay.osb.koroneiki.taproot.model.TeamRole returnValue =
				TeamRoleServiceUtil.addTeamRole(name, description, type);

			return com.liferay.osb.koroneiki.taproot.model.TeamRoleSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.koroneiki.taproot.model.TeamRoleSoap
			deleteTeamRole(long teamRoleId)
		throws RemoteException {

		try {
			com.liferay.osb.koroneiki.taproot.model.TeamRole returnValue =
				TeamRoleServiceUtil.deleteTeamRole(teamRoleId);

			return com.liferay.osb.koroneiki.taproot.model.TeamRoleSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.koroneiki.taproot.model.TeamRoleSoap
			deleteTeamRole(String teamRoleKey)
		throws RemoteException {

		try {
			com.liferay.osb.koroneiki.taproot.model.TeamRole returnValue =
				TeamRoleServiceUtil.deleteTeamRole(teamRoleKey);

			return com.liferay.osb.koroneiki.taproot.model.TeamRoleSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.koroneiki.taproot.model.TeamRoleSoap[]
			getTeamAccountTeamRoles(
				long accountId, long teamId, int start, int end)
		throws RemoteException {

		try {
			java.util.List<com.liferay.osb.koroneiki.taproot.model.TeamRole>
				returnValue = TeamRoleServiceUtil.getTeamAccountTeamRoles(
					accountId, teamId, start, end);

			return com.liferay.osb.koroneiki.taproot.model.TeamRoleSoap.
				toSoapModels(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static int getTeamAccountTeamRolesCount(long accountId, long teamId)
		throws RemoteException {

		try {
			int returnValue = TeamRoleServiceUtil.getTeamAccountTeamRolesCount(
				accountId, teamId);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.koroneiki.taproot.model.TeamRoleSoap
			getTeamRole(long teamRoleId)
		throws RemoteException {

		try {
			com.liferay.osb.koroneiki.taproot.model.TeamRole returnValue =
				TeamRoleServiceUtil.getTeamRole(teamRoleId);

			return com.liferay.osb.koroneiki.taproot.model.TeamRoleSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.koroneiki.taproot.model.TeamRoleSoap
			getTeamRole(String teamRoleKey)
		throws RemoteException {

		try {
			com.liferay.osb.koroneiki.taproot.model.TeamRole returnValue =
				TeamRoleServiceUtil.getTeamRole(teamRoleKey);

			return com.liferay.osb.koroneiki.taproot.model.TeamRoleSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.koroneiki.taproot.model.TeamRoleSoap
			getTeamRole(String name, String type)
		throws RemoteException {

		try {
			com.liferay.osb.koroneiki.taproot.model.TeamRole returnValue =
				TeamRoleServiceUtil.getTeamRole(name, type);

			return com.liferay.osb.koroneiki.taproot.model.TeamRoleSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.koroneiki.taproot.model.TeamRoleSoap
			updateTeamRole(long teamRoleId, String name, String description)
		throws RemoteException {

		try {
			com.liferay.osb.koroneiki.taproot.model.TeamRole returnValue =
				TeamRoleServiceUtil.updateTeamRole(
					teamRoleId, name, description);

			return com.liferay.osb.koroneiki.taproot.model.TeamRoleSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(TeamRoleServiceSoap.class);

}