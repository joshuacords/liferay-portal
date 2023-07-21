/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service.http;

import com.liferay.osb.koroneiki.taproot.service.ContactRoleServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * <code>ContactRoleServiceUtil</code> service
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
 * @see ContactRoleServiceSoap
 * @generated
 */
public class ContactRoleServiceHttp {

	public static com.liferay.osb.koroneiki.taproot.model.ContactRole
			addContactRole(
				HttpPrincipal httpPrincipal, String name, String description,
				String type)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				ContactRoleServiceUtil.class, "addContactRole",
				_addContactRoleParameterTypes0);

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

			return (com.liferay.osb.koroneiki.taproot.model.ContactRole)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.koroneiki.taproot.model.ContactRole
			deleteContactRole(HttpPrincipal httpPrincipal, long contactRoleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				ContactRoleServiceUtil.class, "deleteContactRole",
				_deleteContactRoleParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, contactRoleId);

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

			return (com.liferay.osb.koroneiki.taproot.model.ContactRole)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.koroneiki.taproot.model.ContactRole
			deleteContactRole(
				HttpPrincipal httpPrincipal, String contactRoleKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				ContactRoleServiceUtil.class, "deleteContactRole",
				_deleteContactRoleParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, contactRoleKey);

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

			return (com.liferay.osb.koroneiki.taproot.model.ContactRole)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<com.liferay.osb.koroneiki.taproot.model.ContactRole>
				getContactAccountContactRoles(
					HttpPrincipal httpPrincipal, long accountId, long contactId,
					String[] types, int start, int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				ContactRoleServiceUtil.class, "getContactAccountContactRoles",
				_getContactAccountContactRolesParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, accountId, contactId, types, start, end);

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
				<com.liferay.osb.koroneiki.taproot.model.ContactRole>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static int getContactAccountContactRolesCount(
			HttpPrincipal httpPrincipal, long accountId, long contactId,
			String[] types)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				ContactRoleServiceUtil.class,
				"getContactAccountContactRolesCount",
				_getContactAccountContactRolesCountParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, accountId, contactId, types);

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

	public static java.util.List
		<com.liferay.osb.koroneiki.taproot.model.ContactRole>
				getContactContactRoles(
					HttpPrincipal httpPrincipal, long contactId, int start,
					int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				ContactRoleServiceUtil.class, "getContactContactRoles",
				_getContactContactRolesParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, contactId, start, end);

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
				<com.liferay.osb.koroneiki.taproot.model.ContactRole>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.koroneiki.taproot.model.ContactRole
			getContactRole(HttpPrincipal httpPrincipal, long contactRoleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				ContactRoleServiceUtil.class, "getContactRole",
				_getContactRoleParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, contactRoleId);

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

			return (com.liferay.osb.koroneiki.taproot.model.ContactRole)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.koroneiki.taproot.model.ContactRole
			getContactRole(HttpPrincipal httpPrincipal, String contactRoleKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				ContactRoleServiceUtil.class, "getContactRole",
				_getContactRoleParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, contactRoleKey);

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

			return (com.liferay.osb.koroneiki.taproot.model.ContactRole)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.koroneiki.taproot.model.ContactRole
			getContactRole(
				HttpPrincipal httpPrincipal, String name, String type)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				ContactRoleServiceUtil.class, "getContactRole",
				_getContactRoleParameterTypes8);

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

			return (com.liferay.osb.koroneiki.taproot.model.ContactRole)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<com.liferay.osb.koroneiki.taproot.model.ContactRole>
				getContactTeamContactRoles(
					HttpPrincipal httpPrincipal, long teamId, long contactId,
					String[] types, int start, int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				ContactRoleServiceUtil.class, "getContactTeamContactRoles",
				_getContactTeamContactRolesParameterTypes9);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, teamId, contactId, types, start, end);

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
				<com.liferay.osb.koroneiki.taproot.model.ContactRole>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static int getContactTeamContactRolesCount(
			HttpPrincipal httpPrincipal, long teamId, long contactId,
			String[] types)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				ContactRoleServiceUtil.class, "getContactTeamContactRolesCount",
				_getContactTeamContactRolesCountParameterTypes10);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, teamId, contactId, types);

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

	public static com.liferay.osb.koroneiki.taproot.model.ContactRole
			updateContactRole(
				HttpPrincipal httpPrincipal, long contactRoleId, String name,
				String description)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				ContactRoleServiceUtil.class, "updateContactRole",
				_updateContactRoleParameterTypes11);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, contactRoleId, name, description);

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

			return (com.liferay.osb.koroneiki.taproot.model.ContactRole)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		ContactRoleServiceHttp.class);

	private static final Class<?>[] _addContactRoleParameterTypes0 =
		new Class[] {String.class, String.class, String.class};
	private static final Class<?>[] _deleteContactRoleParameterTypes1 =
		new Class[] {long.class};
	private static final Class<?>[] _deleteContactRoleParameterTypes2 =
		new Class[] {String.class};
	private static final Class<?>[]
		_getContactAccountContactRolesParameterTypes3 = new Class[] {
			long.class, long.class, String[].class, int.class, int.class
		};
	private static final Class<?>[]
		_getContactAccountContactRolesCountParameterTypes4 = new Class[] {
			long.class, long.class, String[].class
		};
	private static final Class<?>[] _getContactContactRolesParameterTypes5 =
		new Class[] {long.class, int.class, int.class};
	private static final Class<?>[] _getContactRoleParameterTypes6 =
		new Class[] {long.class};
	private static final Class<?>[] _getContactRoleParameterTypes7 =
		new Class[] {String.class};
	private static final Class<?>[] _getContactRoleParameterTypes8 =
		new Class[] {String.class, String.class};
	private static final Class<?>[] _getContactTeamContactRolesParameterTypes9 =
		new Class[] {
			long.class, long.class, String[].class, int.class, int.class
		};
	private static final Class<?>[]
		_getContactTeamContactRolesCountParameterTypes10 = new Class[] {
			long.class, long.class, String[].class
		};
	private static final Class<?>[] _updateContactRoleParameterTypes11 =
		new Class[] {long.class, String.class, String.class};

}