/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service.http;

import com.liferay.osb.koroneiki.taproot.service.ContactAccountRoleServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * <code>ContactAccountRoleServiceUtil</code> service
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
 * @see ContactAccountRoleServiceSoap
 * @generated
 */
public class ContactAccountRoleServiceHttp {

	public static com.liferay.osb.koroneiki.taproot.model.ContactAccountRole
			addContactAccountRole(
				HttpPrincipal httpPrincipal, long contactId, long accountId,
				long contactRoleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				ContactAccountRoleServiceUtil.class, "addContactAccountRole",
				_addContactAccountRoleParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, contactId, accountId, contactRoleId);

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

			return (com.liferay.osb.koroneiki.taproot.model.ContactAccountRole)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.koroneiki.taproot.model.ContactAccountRole
			deleteContactAccountRole(
				HttpPrincipal httpPrincipal, long contactId, long accountId,
				long contactRoleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				ContactAccountRoleServiceUtil.class, "deleteContactAccountRole",
				_deleteContactAccountRoleParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, contactId, accountId, contactRoleId);

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

			return (com.liferay.osb.koroneiki.taproot.model.ContactAccountRole)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static void deleteContactAccountRoles(
			HttpPrincipal httpPrincipal, long contactId, long accountId,
			String contactRoleType)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				ContactAccountRoleServiceUtil.class,
				"deleteContactAccountRoles",
				_deleteContactAccountRolesParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, contactId, accountId, contactRoleType);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
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
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		ContactAccountRoleServiceHttp.class);

	private static final Class<?>[] _addContactAccountRoleParameterTypes0 =
		new Class[] {long.class, long.class, long.class};
	private static final Class<?>[] _deleteContactAccountRoleParameterTypes1 =
		new Class[] {long.class, long.class, long.class};
	private static final Class<?>[] _deleteContactAccountRolesParameterTypes2 =
		new Class[] {long.class, long.class, String.class};

}