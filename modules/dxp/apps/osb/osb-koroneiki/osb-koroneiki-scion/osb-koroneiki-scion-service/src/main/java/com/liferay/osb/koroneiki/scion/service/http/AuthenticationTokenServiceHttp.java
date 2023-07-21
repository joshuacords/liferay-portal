/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.scion.service.http;

import com.liferay.osb.koroneiki.scion.service.AuthenticationTokenServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * <code>AuthenticationTokenServiceUtil</code> service
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
 * @see AuthenticationTokenServiceSoap
 * @generated
 */
public class AuthenticationTokenServiceHttp {

	public static com.liferay.osb.koroneiki.scion.model.AuthenticationToken
			addAuthenticationToken(
				HttpPrincipal httpPrincipal, long serviceProducerId,
				String name, String token)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AuthenticationTokenServiceUtil.class, "addAuthenticationToken",
				_addAuthenticationTokenParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, serviceProducerId, name, token);

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

			return (com.liferay.osb.koroneiki.scion.model.AuthenticationToken)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.koroneiki.scion.model.AuthenticationToken
			deleteAuthenticationToken(
				HttpPrincipal httpPrincipal, long authenticationTokenId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AuthenticationTokenServiceUtil.class,
				"deleteAuthenticationToken",
				_deleteAuthenticationTokenParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, authenticationTokenId);

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

			return (com.liferay.osb.koroneiki.scion.model.AuthenticationToken)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.koroneiki.scion.model.AuthenticationToken
			updateAuthenticationToken(
				HttpPrincipal httpPrincipal, long authenticationTokenId,
				String name)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AuthenticationTokenServiceUtil.class,
				"updateAuthenticationToken",
				_updateAuthenticationTokenParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, authenticationTokenId, name);

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

			return (com.liferay.osb.koroneiki.scion.model.AuthenticationToken)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.koroneiki.scion.model.AuthenticationToken
			updateStatus(
				HttpPrincipal httpPrincipal, long authenticationTokenId,
				int status)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AuthenticationTokenServiceUtil.class, "updateStatus",
				_updateStatusParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, authenticationTokenId, status);

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

			return (com.liferay.osb.koroneiki.scion.model.AuthenticationToken)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		AuthenticationTokenServiceHttp.class);

	private static final Class<?>[] _addAuthenticationTokenParameterTypes0 =
		new Class[] {long.class, String.class, String.class};
	private static final Class<?>[] _deleteAuthenticationTokenParameterTypes1 =
		new Class[] {long.class};
	private static final Class<?>[] _updateAuthenticationTokenParameterTypes2 =
		new Class[] {long.class, String.class};
	private static final Class<?>[] _updateStatusParameterTypes3 = new Class[] {
		long.class, int.class
	};

}