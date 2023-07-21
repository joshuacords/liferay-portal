/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.root.service.http;

import com.liferay.osb.koroneiki.root.service.AuditEntryServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * <code>AuditEntryServiceUtil</code> service
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
 * @see AuditEntryServiceSoap
 * @generated
 */
public class AuditEntryServiceHttp {

	public static com.liferay.osb.koroneiki.root.model.AuditEntry addAuditEntry(
			HttpPrincipal httpPrincipal, long classNameId, long classPK,
			long fieldClassNameId, long fieldClassPK, String action,
			String field, String oldLabel, String oldValue, String newLabel,
			String newValue, String description,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AuditEntryServiceUtil.class, "addAuditEntry",
				_addAuditEntryParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, classNameId, classPK, fieldClassNameId, fieldClassPK,
				action, field, oldLabel, oldValue, newLabel, newValue,
				description, serviceContext);

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

			return (com.liferay.osb.koroneiki.root.model.AuditEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<com.liferay.osb.koroneiki.root.model.AuditEntry> getAuditEntries(
				HttpPrincipal httpPrincipal, long classNameId, long classPK,
				int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.osb.koroneiki.root.model.AuditEntry> obc)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AuditEntryServiceUtil.class, "getAuditEntries",
				_getAuditEntriesParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, classNameId, classPK, start, end, obc);

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
				<com.liferay.osb.koroneiki.root.model.AuditEntry>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<com.liferay.osb.koroneiki.root.model.AuditEntry> getAuditEntries(
				HttpPrincipal httpPrincipal, long classNameId, long classPK,
				long fieldClassNameId, long fieldClassPK, int start, int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AuditEntryServiceUtil.class, "getAuditEntries",
				_getAuditEntriesParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, classNameId, classPK, fieldClassNameId, fieldClassPK,
				start, end);

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
				<com.liferay.osb.koroneiki.root.model.AuditEntry>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static int getAuditEntriesCount(
			HttpPrincipal httpPrincipal, long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AuditEntryServiceUtil.class, "getAuditEntriesCount",
				_getAuditEntriesCountParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, classNameId, classPK);

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

	public static int getAuditEntriesCount(
			HttpPrincipal httpPrincipal, long classNameId, long classPK,
			long fieldClassNameId, long fieldClassPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AuditEntryServiceUtil.class, "getAuditEntriesCount",
				_getAuditEntriesCountParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, classNameId, classPK, fieldClassNameId,
				fieldClassPK);

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

	public static com.liferay.osb.koroneiki.root.model.AuditEntry getAuditEntry(
			HttpPrincipal httpPrincipal, long auditEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AuditEntryServiceUtil.class, "getAuditEntry",
				_getAuditEntryParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, auditEntryId);

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

			return (com.liferay.osb.koroneiki.root.model.AuditEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.koroneiki.root.model.AuditEntry getAuditEntry(
			HttpPrincipal httpPrincipal, String auditEntryKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AuditEntryServiceUtil.class, "getAuditEntry",
				_getAuditEntryParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, auditEntryKey);

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

			return (com.liferay.osb.koroneiki.root.model.AuditEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		AuditEntryServiceHttp.class);

	private static final Class<?>[] _addAuditEntryParameterTypes0 =
		new Class[] {
			long.class, long.class, long.class, long.class, String.class,
			String.class, String.class, String.class, String.class,
			String.class, String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _getAuditEntriesParameterTypes1 =
		new Class[] {
			long.class, long.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getAuditEntriesParameterTypes2 =
		new Class[] {
			long.class, long.class, long.class, long.class, int.class, int.class
		};
	private static final Class<?>[] _getAuditEntriesCountParameterTypes3 =
		new Class[] {long.class, long.class};
	private static final Class<?>[] _getAuditEntriesCountParameterTypes4 =
		new Class[] {long.class, long.class, long.class, long.class};
	private static final Class<?>[] _getAuditEntryParameterTypes5 =
		new Class[] {long.class};
	private static final Class<?>[] _getAuditEntryParameterTypes6 =
		new Class[] {String.class};

}