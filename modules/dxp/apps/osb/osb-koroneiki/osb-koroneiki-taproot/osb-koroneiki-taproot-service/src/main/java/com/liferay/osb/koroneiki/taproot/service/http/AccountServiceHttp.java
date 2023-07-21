/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service.http;

import com.liferay.osb.koroneiki.taproot.service.AccountServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * <code>AccountServiceUtil</code> service
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
 * @see AccountServiceSoap
 * @generated
 */
public class AccountServiceHttp {

	public static com.liferay.osb.koroneiki.taproot.model.Account addAccount(
			HttpPrincipal httpPrincipal, long parentAccountId, String name,
			String code, String description, long logoId,
			String contactEmailAddress, String profileEmailAddress,
			String phoneNumber, String faxNumber, String website, String tier,
			String region, String dataRegion, String language, boolean internal,
			String status,
			java.util.List<com.liferay.osb.koroneiki.taproot.model.AccountField>
				accountFields)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AccountServiceUtil.class, "addAccount",
				_addAccountParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, parentAccountId, name, code, description, logoId,
				contactEmailAddress, profileEmailAddress, phoneNumber,
				faxNumber, website, tier, region, dataRegion, language,
				internal, status, accountFields);

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

			return (com.liferay.osb.koroneiki.taproot.model.Account)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.koroneiki.taproot.model.Account deleteAccount(
			HttpPrincipal httpPrincipal, long accountId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AccountServiceUtil.class, "deleteAccount",
				_deleteAccountParameterTypes1);

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

			return (com.liferay.osb.koroneiki.taproot.model.Account)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.koroneiki.taproot.model.Account deleteAccount(
			HttpPrincipal httpPrincipal, String accountKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AccountServiceUtil.class, "deleteAccount",
				_deleteAccountParameterTypes2);

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

			return (com.liferay.osb.koroneiki.taproot.model.Account)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.koroneiki.taproot.model.Account getAccount(
			HttpPrincipal httpPrincipal, long accountId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AccountServiceUtil.class, "getAccount",
				_getAccountParameterTypes3);

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

			return (com.liferay.osb.koroneiki.taproot.model.Account)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.koroneiki.taproot.model.Account getAccount(
			HttpPrincipal httpPrincipal, String accountKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AccountServiceUtil.class, "getAccount",
				_getAccountParameterTypes4);

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

			return (com.liferay.osb.koroneiki.taproot.model.Account)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<com.liferay.osb.koroneiki.taproot.model.Account> getAccounts(
				HttpPrincipal httpPrincipal, long parentAccountId, int start,
				int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AccountServiceUtil.class, "getAccounts",
				_getAccountsParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, parentAccountId, start, end);

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
				<com.liferay.osb.koroneiki.taproot.model.Account>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<com.liferay.osb.koroneiki.taproot.model.Account> getAccounts(
				HttpPrincipal httpPrincipal, String domain, String entityName,
				String entityId, int start, int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AccountServiceUtil.class, "getAccounts",
				_getAccountsParameterTypes6);

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
				<com.liferay.osb.koroneiki.taproot.model.Account>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static int getAccountsCount(
			HttpPrincipal httpPrincipal, long parentAccountId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AccountServiceUtil.class, "getAccountsCount",
				_getAccountsCountParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, parentAccountId);

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

	public static int getAccountsCount(
			HttpPrincipal httpPrincipal, String domain, String entityName,
			String entityId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AccountServiceUtil.class, "getAccountsCount",
				_getAccountsCountParameterTypes8);

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

	public static java.util.List
		<com.liferay.osb.koroneiki.taproot.model.Account> getContactAccounts(
				HttpPrincipal httpPrincipal, long contactId, int start, int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AccountServiceUtil.class, "getContactAccounts",
				_getContactAccountsParameterTypes9);

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
				<com.liferay.osb.koroneiki.taproot.model.Account>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static int getContactAccountsCount(
			HttpPrincipal httpPrincipal, long contactId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AccountServiceUtil.class, "getContactAccountsCount",
				_getContactAccountsCountParameterTypes10);

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

	public static java.util.List
		<com.liferay.osb.koroneiki.taproot.model.Account> getTeamAccounts(
				HttpPrincipal httpPrincipal, long teamId, int start, int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AccountServiceUtil.class, "getTeamAccounts",
				_getTeamAccountsParameterTypes11);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, teamId, start, end);

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
				<com.liferay.osb.koroneiki.taproot.model.Account>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static int getTeamAccountsCount(
			HttpPrincipal httpPrincipal, long teamId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AccountServiceUtil.class, "getTeamAccountsCount",
				_getTeamAccountsCountParameterTypes12);

			MethodHandler methodHandler = new MethodHandler(methodKey, teamId);

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

	public static com.liferay.osb.koroneiki.taproot.model.Account updateAccount(
			HttpPrincipal httpPrincipal, long accountId, long parentAccountId,
			String name, String code, String description, long logoId,
			String contactEmailAddress, String profileEmailAddress,
			String phoneNumber, String faxNumber, String website, String tier,
			String region, String dataRegion, String language, boolean internal,
			String status,
			java.util.List<com.liferay.osb.koroneiki.taproot.model.AccountField>
				accountFields)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AccountServiceUtil.class, "updateAccount",
				_updateAccountParameterTypes13);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, accountId, parentAccountId, name, code, description,
				logoId, contactEmailAddress, profileEmailAddress, phoneNumber,
				faxNumber, website, tier, region, dataRegion, language,
				internal, status, accountFields);

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

			return (com.liferay.osb.koroneiki.taproot.model.Account)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.koroneiki.taproot.model.Account updateAccount(
			HttpPrincipal httpPrincipal, String accountKey,
			long parentAccountId, String name, String code, String description,
			long logoId, String contactEmailAddress, String profileEmailAddress,
			String phoneNumber, String faxNumber, String website, String tier,
			String region, String dataRegion, String language, boolean internal,
			String status,
			java.util.List<com.liferay.osb.koroneiki.taproot.model.AccountField>
				accountFields)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AccountServiceUtil.class, "updateAccount",
				_updateAccountParameterTypes14);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, accountKey, parentAccountId, name, code, description,
				logoId, contactEmailAddress, profileEmailAddress, phoneNumber,
				faxNumber, website, tier, region, dataRegion, language,
				internal, status, accountFields);

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

			return (com.liferay.osb.koroneiki.taproot.model.Account)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(AccountServiceHttp.class);

	private static final Class<?>[] _addAccountParameterTypes0 = new Class[] {
		long.class, String.class, String.class, String.class, long.class,
		String.class, String.class, String.class, String.class, String.class,
		String.class, String.class, String.class, String.class, boolean.class,
		String.class, java.util.List.class
	};
	private static final Class<?>[] _deleteAccountParameterTypes1 =
		new Class[] {long.class};
	private static final Class<?>[] _deleteAccountParameterTypes2 =
		new Class[] {String.class};
	private static final Class<?>[] _getAccountParameterTypes3 = new Class[] {
		long.class
	};
	private static final Class<?>[] _getAccountParameterTypes4 = new Class[] {
		String.class
	};
	private static final Class<?>[] _getAccountsParameterTypes5 = new Class[] {
		long.class, int.class, int.class
	};
	private static final Class<?>[] _getAccountsParameterTypes6 = new Class[] {
		String.class, String.class, String.class, int.class, int.class
	};
	private static final Class<?>[] _getAccountsCountParameterTypes7 =
		new Class[] {long.class};
	private static final Class<?>[] _getAccountsCountParameterTypes8 =
		new Class[] {String.class, String.class, String.class};
	private static final Class<?>[] _getContactAccountsParameterTypes9 =
		new Class[] {long.class, int.class, int.class};
	private static final Class<?>[] _getContactAccountsCountParameterTypes10 =
		new Class[] {long.class};
	private static final Class<?>[] _getTeamAccountsParameterTypes11 =
		new Class[] {long.class, int.class, int.class};
	private static final Class<?>[] _getTeamAccountsCountParameterTypes12 =
		new Class[] {long.class};
	private static final Class<?>[] _updateAccountParameterTypes13 =
		new Class[] {
			long.class, long.class, String.class, String.class, String.class,
			long.class, String.class, String.class, String.class, String.class,
			String.class, String.class, String.class, String.class,
			String.class, boolean.class, String.class, java.util.List.class
		};
	private static final Class<?>[] _updateAccountParameterTypes14 =
		new Class[] {
			String.class, long.class, String.class, String.class, String.class,
			long.class, String.class, String.class, String.class, String.class,
			String.class, String.class, String.class, String.class,
			String.class, boolean.class, String.class, java.util.List.class
		};

}