/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service.http;

import com.liferay.osb.koroneiki.taproot.service.TeamAccountRoleServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * <code>TeamAccountRoleServiceUtil</code> service
 * utility. The static methods of this class call the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a <code>java.util.List</code>,
 * that is translated to an array of
 * <code>com.liferay.osb.koroneiki.taproot.model.TeamAccountRoleSoap</code>. If the method in the
 * service utility returns a
 * <code>com.liferay.osb.koroneiki.taproot.model.TeamAccountRole</code>, that is translated to a
 * <code>com.liferay.osb.koroneiki.taproot.model.TeamAccountRoleSoap</code>. Methods that SOAP
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
 * @see TeamAccountRoleServiceHttp
 * @generated
 */
public class TeamAccountRoleServiceSoap {

	public static com.liferay.osb.koroneiki.taproot.model.TeamAccountRoleSoap
			addTeamAccountRole(long teamId, long accountId, long teamRoleId)
		throws RemoteException {

		try {
			com.liferay.osb.koroneiki.taproot.model.TeamAccountRole
				returnValue = TeamAccountRoleServiceUtil.addTeamAccountRole(
					teamId, accountId, teamRoleId);

			return com.liferay.osb.koroneiki.taproot.model.TeamAccountRoleSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.koroneiki.taproot.model.TeamAccountRoleSoap
			addTeamAccountRole(
				String teamKey, String accountKey, String teamRoleKey)
		throws RemoteException {

		try {
			com.liferay.osb.koroneiki.taproot.model.TeamAccountRole
				returnValue = TeamAccountRoleServiceUtil.addTeamAccountRole(
					teamKey, accountKey, teamRoleKey);

			return com.liferay.osb.koroneiki.taproot.model.TeamAccountRoleSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.koroneiki.taproot.model.TeamAccountRoleSoap
			deleteTeamAccountRole(long teamId, long accountId, long teamRoleId)
		throws RemoteException {

		try {
			com.liferay.osb.koroneiki.taproot.model.TeamAccountRole
				returnValue = TeamAccountRoleServiceUtil.deleteTeamAccountRole(
					teamId, accountId, teamRoleId);

			return com.liferay.osb.koroneiki.taproot.model.TeamAccountRoleSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.koroneiki.taproot.model.TeamAccountRoleSoap
			deleteTeamAccountRole(
				String teamKey, String accountKey, String teamRoleKey)
		throws RemoteException {

		try {
			com.liferay.osb.koroneiki.taproot.model.TeamAccountRole
				returnValue = TeamAccountRoleServiceUtil.deleteTeamAccountRole(
					teamKey, accountKey, teamRoleKey);

			return com.liferay.osb.koroneiki.taproot.model.TeamAccountRoleSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static void deleteTeamAccountRoles(long teamId, long accountId)
		throws RemoteException {

		try {
			TeamAccountRoleServiceUtil.deleteTeamAccountRoles(
				teamId, accountId);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		TeamAccountRoleServiceSoap.class);

}