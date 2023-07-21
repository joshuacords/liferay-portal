/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.powwow.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.powwow.service.PowwowParticipantServiceUtil;

/**
 * Provides the HTTP utility for the
 * <code>PowwowParticipantServiceUtil</code> service
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
 * @author Shinn Lok
 * @see PowwowParticipantServiceSoap
 * @generated
 */
public class PowwowParticipantServiceHttp {

	public static com.liferay.powwow.model.PowwowParticipant
			deletePowwowParticipant(
				HttpPrincipal httpPrincipal,
				com.liferay.powwow.model.PowwowParticipant powwowParticipant)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				PowwowParticipantServiceUtil.class, "deletePowwowParticipant",
				_deletePowwowParticipantParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, powwowParticipant);

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

			return (com.liferay.powwow.model.PowwowParticipant)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List<com.liferay.powwow.model.PowwowParticipant>
			getPowwowParticipants(
				HttpPrincipal httpPrincipal, long powwowMeetingId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				PowwowParticipantServiceUtil.class, "getPowwowParticipants",
				_getPowwowParticipantsParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, powwowMeetingId);

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

			return (java.util.List<com.liferay.powwow.model.PowwowParticipant>)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static int getPowwowParticipantsCount(
			HttpPrincipal httpPrincipal, long powwowMeetingId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				PowwowParticipantServiceUtil.class,
				"getPowwowParticipantsCount",
				_getPowwowParticipantsCountParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, powwowMeetingId);

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

	public static com.liferay.powwow.model.PowwowParticipant
			updatePowwowParticipant(
				HttpPrincipal httpPrincipal, long powwowParticipantId,
				long powwowMeetingId, String name, long participantUserId,
				String emailAddress, int type, int status,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				PowwowParticipantServiceUtil.class, "updatePowwowParticipant",
				_updatePowwowParticipantParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, powwowParticipantId, powwowMeetingId, name,
				participantUserId, emailAddress, type, status, serviceContext);

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

			return (com.liferay.powwow.model.PowwowParticipant)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		PowwowParticipantServiceHttp.class);

	private static final Class<?>[] _deletePowwowParticipantParameterTypes0 =
		new Class[] {com.liferay.powwow.model.PowwowParticipant.class};
	private static final Class<?>[] _getPowwowParticipantsParameterTypes1 =
		new Class[] {long.class};
	private static final Class<?>[] _getPowwowParticipantsCountParameterTypes2 =
		new Class[] {long.class};
	private static final Class<?>[] _updatePowwowParticipantParameterTypes3 =
		new Class[] {
			long.class, long.class, String.class, long.class, String.class,
			int.class, int.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};

}