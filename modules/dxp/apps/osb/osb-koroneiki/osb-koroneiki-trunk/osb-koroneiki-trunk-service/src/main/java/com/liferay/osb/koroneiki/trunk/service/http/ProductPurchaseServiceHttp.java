/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.service.http;

import com.liferay.osb.koroneiki.trunk.service.ProductPurchaseServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * <code>ProductPurchaseServiceUtil</code> service
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
 * @see ProductPurchaseServiceSoap
 * @generated
 */
public class ProductPurchaseServiceHttp {

	public static com.liferay.osb.koroneiki.trunk.model.ProductPurchase
			addProductPurchase(
				HttpPrincipal httpPrincipal, long accountId,
				long productEntryId, java.util.Date startDate,
				java.util.Date endDate, java.util.Date originalEndDate,
				int quantity, int status,
				java.util.List
					<com.liferay.osb.koroneiki.trunk.model.ProductField>
						productFields)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				ProductPurchaseServiceUtil.class, "addProductPurchase",
				_addProductPurchaseParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, accountId, productEntryId, startDate, endDate,
				originalEndDate, quantity, status, productFields);

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

			return (com.liferay.osb.koroneiki.trunk.model.ProductPurchase)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.koroneiki.trunk.model.ProductPurchase
			addProductPurchase(
				HttpPrincipal httpPrincipal, String accountKey,
				String productEntryKey, java.util.Date startDate,
				java.util.Date endDate, java.util.Date originalEndDate,
				int quantity, int status,
				java.util.List
					<com.liferay.osb.koroneiki.trunk.model.ProductField>
						productFields)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				ProductPurchaseServiceUtil.class, "addProductPurchase",
				_addProductPurchaseParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, accountKey, productEntryKey, startDate, endDate,
				originalEndDate, quantity, status, productFields);

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

			return (com.liferay.osb.koroneiki.trunk.model.ProductPurchase)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.koroneiki.trunk.model.ProductPurchase
			deleteProductPurchase(
				HttpPrincipal httpPrincipal, long productPurchaseId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				ProductPurchaseServiceUtil.class, "deleteProductPurchase",
				_deleteProductPurchaseParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, productPurchaseId);

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

			return (com.liferay.osb.koroneiki.trunk.model.ProductPurchase)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.koroneiki.trunk.model.ProductPurchase
			deleteProductPurchase(
				HttpPrincipal httpPrincipal, String productPurchaseKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				ProductPurchaseServiceUtil.class, "deleteProductPurchase",
				_deleteProductPurchaseParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, productPurchaseKey);

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

			return (com.liferay.osb.koroneiki.trunk.model.ProductPurchase)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<com.liferay.osb.koroneiki.trunk.model.ProductPurchase>
				getAccountProductEntryProductPurchases(
					HttpPrincipal httpPrincipal, long accountId,
					long productEntryId, int start, int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				ProductPurchaseServiceUtil.class,
				"getAccountProductEntryProductPurchases",
				_getAccountProductEntryProductPurchasesParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, accountId, productEntryId, start, end);

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
				<com.liferay.osb.koroneiki.trunk.model.ProductPurchase>)
					returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<com.liferay.osb.koroneiki.trunk.model.ProductPurchase>
				getAccountProductPurchases(
					HttpPrincipal httpPrincipal, long accountId, int start,
					int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				ProductPurchaseServiceUtil.class, "getAccountProductPurchases",
				_getAccountProductPurchasesParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, accountId, start, end);

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
				<com.liferay.osb.koroneiki.trunk.model.ProductPurchase>)
					returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<com.liferay.osb.koroneiki.trunk.model.ProductPurchase>
				getAccountProductPurchases(
					HttpPrincipal httpPrincipal, String accountKey, int start,
					int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				ProductPurchaseServiceUtil.class, "getAccountProductPurchases",
				_getAccountProductPurchasesParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, accountKey, start, end);

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
				<com.liferay.osb.koroneiki.trunk.model.ProductPurchase>)
					returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static int getAccountProductPurchasesCount(
			HttpPrincipal httpPrincipal, long accountId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				ProductPurchaseServiceUtil.class,
				"getAccountProductPurchasesCount",
				_getAccountProductPurchasesCountParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, accountId);

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

	public static int getAccountProductPurchasesCount(
			HttpPrincipal httpPrincipal, String accountKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				ProductPurchaseServiceUtil.class,
				"getAccountProductPurchasesCount",
				_getAccountProductPurchasesCountParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, accountKey);

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
		<com.liferay.osb.koroneiki.trunk.model.ProductPurchase>
				getContactProductPurchases(
					HttpPrincipal httpPrincipal, long contactId, int start,
					int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				ProductPurchaseServiceUtil.class, "getContactProductPurchases",
				_getContactProductPurchasesParameterTypes9);

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
				<com.liferay.osb.koroneiki.trunk.model.ProductPurchase>)
					returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static int getContactProductPurchasesCount(
			HttpPrincipal httpPrincipal, long contactId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				ProductPurchaseServiceUtil.class,
				"getContactProductPurchasesCount",
				_getContactProductPurchasesCountParameterTypes10);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, contactId);

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

	public static com.liferay.osb.koroneiki.trunk.model.ProductPurchase
			getProductPurchase(
				HttpPrincipal httpPrincipal, long productPurchaseId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				ProductPurchaseServiceUtil.class, "getProductPurchase",
				_getProductPurchaseParameterTypes11);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, productPurchaseId);

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

			return (com.liferay.osb.koroneiki.trunk.model.ProductPurchase)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.koroneiki.trunk.model.ProductPurchase
			getProductPurchase(
				HttpPrincipal httpPrincipal, String productPurchaseKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				ProductPurchaseServiceUtil.class, "getProductPurchase",
				_getProductPurchaseParameterTypes12);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, productPurchaseKey);

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

			return (com.liferay.osb.koroneiki.trunk.model.ProductPurchase)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<com.liferay.osb.koroneiki.trunk.model.ProductPurchase>
				getProductPurchases(
					HttpPrincipal httpPrincipal, String domain,
					String entityName, String entityId, int start, int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				ProductPurchaseServiceUtil.class, "getProductPurchases",
				_getProductPurchasesParameterTypes13);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, domain, entityName, entityId, start, end);

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
				<com.liferay.osb.koroneiki.trunk.model.ProductPurchase>)
					returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static int getProductPurchasesCount(
			HttpPrincipal httpPrincipal, String domain, String entityName,
			String entityId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				ProductPurchaseServiceUtil.class, "getProductPurchasesCount",
				_getProductPurchasesCountParameterTypes14);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, domain, entityName, entityId);

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

	public static com.liferay.osb.koroneiki.trunk.model.ProductPurchase
			updateProductPurchase(
				HttpPrincipal httpPrincipal, long productPurchaseId,
				java.util.Date startDate, java.util.Date endDate,
				java.util.Date originalEndDate, int quantity, int status,
				java.util.List
					<com.liferay.osb.koroneiki.trunk.model.ProductField>
						productFields)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				ProductPurchaseServiceUtil.class, "updateProductPurchase",
				_updateProductPurchaseParameterTypes15);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, productPurchaseId, startDate, endDate,
				originalEndDate, quantity, status, productFields);

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

			return (com.liferay.osb.koroneiki.trunk.model.ProductPurchase)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		ProductPurchaseServiceHttp.class);

	private static final Class<?>[] _addProductPurchaseParameterTypes0 =
		new Class[] {
			long.class, long.class, java.util.Date.class, java.util.Date.class,
			java.util.Date.class, int.class, int.class, java.util.List.class
		};
	private static final Class<?>[] _addProductPurchaseParameterTypes1 =
		new Class[] {
			String.class, String.class, java.util.Date.class,
			java.util.Date.class, java.util.Date.class, int.class, int.class,
			java.util.List.class
		};
	private static final Class<?>[] _deleteProductPurchaseParameterTypes2 =
		new Class[] {long.class};
	private static final Class<?>[] _deleteProductPurchaseParameterTypes3 =
		new Class[] {String.class};
	private static final Class<?>[]
		_getAccountProductEntryProductPurchasesParameterTypes4 = new Class[] {
			long.class, long.class, int.class, int.class
		};
	private static final Class<?>[] _getAccountProductPurchasesParameterTypes5 =
		new Class[] {long.class, int.class, int.class};
	private static final Class<?>[] _getAccountProductPurchasesParameterTypes6 =
		new Class[] {String.class, int.class, int.class};
	private static final Class<?>[]
		_getAccountProductPurchasesCountParameterTypes7 = new Class[] {
			long.class
		};
	private static final Class<?>[]
		_getAccountProductPurchasesCountParameterTypes8 = new Class[] {
			String.class
		};
	private static final Class<?>[] _getContactProductPurchasesParameterTypes9 =
		new Class[] {long.class, int.class, int.class};
	private static final Class<?>[]
		_getContactProductPurchasesCountParameterTypes10 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getProductPurchaseParameterTypes11 =
		new Class[] {long.class};
	private static final Class<?>[] _getProductPurchaseParameterTypes12 =
		new Class[] {String.class};
	private static final Class<?>[] _getProductPurchasesParameterTypes13 =
		new Class[] {
			String.class, String.class, String.class, int.class, int.class
		};
	private static final Class<?>[] _getProductPurchasesCountParameterTypes14 =
		new Class[] {String.class, String.class, String.class};
	private static final Class<?>[] _updateProductPurchaseParameterTypes15 =
		new Class[] {
			long.class, java.util.Date.class, java.util.Date.class,
			java.util.Date.class, int.class, int.class, java.util.List.class
		};

}