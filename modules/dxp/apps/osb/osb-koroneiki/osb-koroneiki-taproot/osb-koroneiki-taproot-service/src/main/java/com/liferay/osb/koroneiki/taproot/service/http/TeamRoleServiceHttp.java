/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service.http;

import com.liferay.osb.koroneiki.taproot.service.TeamRoleServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * <code>TeamRoleServiceUtil</code> service
 * utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * <code>HttpPrincipal</code> parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TeamRoleServiceSoap
 * @generated
 */
public class TeamRoleServiceHttp {

	public static com.liferay.osb.koroneiki.taproot.model.TeamRole addTeamRole(
			HttpPrincipal httpPrincipal, String name, String description,
			String type)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				TeamRoleServiceUtil.class, "addTeamRole",
				_addTeamRoleParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, name, description, type);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.osb.koroneiki.taproot.model.TeamRole)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.koroneiki.taproot.model.TeamRole
			deleteTeamRole(HttpPrincipal httpPrincipal, long teamRoleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				TeamRoleServiceUtil.class, "deleteTeamRole",
				_deleteTeamRoleParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, teamRoleId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.osb.koroneiki.taproot.model.TeamRole)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.koroneiki.taproot.model.TeamRole
			deleteTeamRole(HttpPrincipal httpPrincipal, String teamRoleKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				TeamRoleServiceUtil.class, "deleteTeamRole",
				_deleteTeamRoleParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, teamRoleKey);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.osb.koroneiki.taproot.model.TeamRole)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<com.liferay.osb.koroneiki.taproot.model.TeamRole>
				getTeamAccountTeamRoles(
					HttpPrincipal httpPrincipal, long accountId, long teamId,
					int start, int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				TeamRoleServiceUtil.class, "getTeamAccountTeamRoles",
				_getTeamAccountTeamRolesParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, accountId, teamId, start, end);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List
				<com.liferay.osb.koroneiki.taproot.model.TeamRole>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static int getTeamAccountTeamRolesCount(
			HttpPrincipal httpPrincipal, long accountId, long teamId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				TeamRoleServiceUtil.class, "getTeamAccountTeamRolesCount",
				_getTeamAccountTeamRolesCountParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, accountId, teamId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.koroneiki.taproot.model.TeamRole getTeamRole(
			HttpPrincipal httpPrincipal, long teamRoleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				TeamRoleServiceUtil.class, "getTeamRole",
				_getTeamRoleParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, teamRoleId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.osb.koroneiki.taproot.model.TeamRole)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.koroneiki.taproot.model.TeamRole getTeamRole(
			HttpPrincipal httpPrincipal, String teamRoleKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				TeamRoleServiceUtil.class, "getTeamRole",
				_getTeamRoleParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, teamRoleKey);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.osb.koroneiki.taproot.model.TeamRole)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.koroneiki.taproot.model.TeamRole getTeamRole(
			HttpPrincipal httpPrincipal, String name, String type)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				TeamRoleServiceUtil.class, "getTeamRole",
				_getTeamRoleParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, name, type);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.osb.koroneiki.taproot.model.TeamRole)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.koroneiki.taproot.model.TeamRole
			updateTeamRole(
				HttpPrincipal httpPrincipal, long teamRoleId, String name,
				String description)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				TeamRoleServiceUtil.class, "updateTeamRole",
				_updateTeamRoleParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, teamRoleId, name, description);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.osb.koroneiki.taproot.model.TeamRole)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(TeamRoleServiceHttp.class);

	private static final Class<?>[] _addTeamRoleParameterTypes0 = new Class[] {
		String.class, String.class, String.class
	};
	private static final Class<?>[] _deleteTeamRoleParameterTypes1 =
		new Class[] {long.class};
	private static final Class<?>[] _deleteTeamRoleParameterTypes2 =
		new Class[] {String.class};
	private static final Class<?>[] _getTeamAccountTeamRolesParameterTypes3 =
		new Class[] {long.class, long.class, int.class, int.class};
	private static final Class<?>[]
		_getTeamAccountTeamRolesCountParameterTypes4 = new Class[] {
			long.class, long.class
		};
	private static final Class<?>[] _getTeamRoleParameterTypes5 = new Class[] {
		long.class
	};
	private static final Class<?>[] _getTeamRoleParameterTypes6 = new Class[] {
		String.class
	};
	private static final Class<?>[] _getTeamRoleParameterTypes7 = new Class[] {
		String.class, String.class
	};
	private static final Class<?>[] _updateTeamRoleParameterTypes8 =
		new Class[] {long.class, String.class, String.class};

}