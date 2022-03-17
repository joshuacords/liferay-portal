/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.provisioning.license.service.http;

import com.liferay.osb.provisioning.license.service.LicenseKeyServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * <code>LicenseKeyServiceUtil</code> service
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
 * @see LicenseKeyServiceSoap
 * @generated
 */
public class LicenseKeyServiceHttp {

	public static com.liferay.osb.provisioning.license.model.LicenseKey
			addLicenseKey(
				HttpPrincipal httpPrincipal, String userName, String userUuid,
				long licenseEntryId, String productKey, String accountKey,
				String productPurchaseKey, String accountName,
				String productVersion, long clusterId, String name,
				String owner, int maxClusterNodes, int maxServers,
				int maxHttpSessions, int maxConcurrentUsers, int maxUsers,
				String sizing, String description, String[] hostNames,
				String[] ipAddresses, String[] macAddresses,
				java.util.Date startDate, java.util.Date expirationDate,
				boolean complimentary, boolean active)
		throws Exception {

		try {
			MethodKey methodKey = new MethodKey(
				LicenseKeyServiceUtil.class, "addLicenseKey",
				_addLicenseKeyParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, userName, userUuid, licenseEntryId, productKey,
				accountKey, productPurchaseKey, accountName, productVersion,
				clusterId, name, owner, maxClusterNodes, maxServers,
				maxHttpSessions, maxConcurrentUsers, maxUsers, sizing,
				description, hostNames, ipAddresses, macAddresses, startDate,
				expirationDate, complimentary, active);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof Exception) {
					throw (Exception)exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.osb.provisioning.license.model.LicenseKey)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.provisioning.license.model.LicenseKey
			addLicenseKey(
				HttpPrincipal httpPrincipal, String userUuid,
				String assetReceiptLicenseUuid, String licenseEntryType,
				String productEntryName, String productId, int productVersion,
				String owner, long maxUsers, String description,
				String hostName, String ipAddresses, String macAddresses,
				String serverId, java.util.Date startDate,
				java.util.Date expirationDate)
		throws Exception {

		try {
			MethodKey methodKey = new MethodKey(
				LicenseKeyServiceUtil.class, "addLicenseKey",
				_addLicenseKeyParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, userUuid, assetReceiptLicenseUuid, licenseEntryType,
				productEntryName, productId, productVersion, owner, maxUsers,
				description, hostName, ipAddresses, macAddresses, serverId,
				startDate, expirationDate);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof Exception) {
					throw (Exception)exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.osb.provisioning.license.model.LicenseKey)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.provisioning.license.model.LicenseKey
			extendLicenseKey(
				HttpPrincipal httpPrincipal, long licenseKeyId,
				String productPurchaseKey, java.util.Date startDate,
				java.util.Date expirationDate)
		throws Exception {

		try {
			MethodKey methodKey = new MethodKey(
				LicenseKeyServiceUtil.class, "extendLicenseKey",
				_extendLicenseKeyParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, licenseKeyId, productPurchaseKey, startDate,
				expirationDate);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof Exception) {
					throw (Exception)exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.osb.provisioning.license.model.LicenseKey)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<com.liferay.osb.provisioning.license.model.LicenseKey>
				getAssetReceiptLicenseLicenseKeys(
					HttpPrincipal httpPrincipal, String assetReceiptLicenseUuid,
					boolean complimentary, boolean active)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				LicenseKeyServiceUtil.class,
				"getAssetReceiptLicenseLicenseKeys",
				_getAssetReceiptLicenseLicenseKeysParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, assetReceiptLicenseUuid, complimentary, active);

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
				<com.liferay.osb.provisioning.license.model.LicenseKey>)
					returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static int getAssetReceiptLicenseLicenseKeysCount(
			HttpPrincipal httpPrincipal, String assetReceiptLicenseUuid,
			boolean complimentary, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				LicenseKeyServiceUtil.class,
				"getAssetReceiptLicenseLicenseKeysCount",
				_getAssetReceiptLicenseLicenseKeysCountParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, assetReceiptLicenseUuid, complimentary, active);

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

	public static com.liferay.osb.provisioning.license.model.LicenseKey
			getLicenseKey(HttpPrincipal httpPrincipal, long licenseKeyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				LicenseKeyServiceUtil.class, "getLicenseKey",
				_getLicenseKeyParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, licenseKeyId);

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

			return (com.liferay.osb.provisioning.license.model.LicenseKey)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.provisioning.license.model.LicenseKey
			getLicenseKey(HttpPrincipal httpPrincipal, String uuid)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				LicenseKeyServiceUtil.class, "getLicenseKey",
				_getLicenseKeyParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(methodKey, uuid);

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

			return (com.liferay.osb.provisioning.license.model.LicenseKey)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<com.liferay.osb.provisioning.license.model.LicenseKey> getLicenseKeys(
				HttpPrincipal httpPrincipal, String productId, String serverId)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				LicenseKeyServiceUtil.class, "getLicenseKeys",
				_getLicenseKeysParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, productId, serverId);

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
				<com.liferay.osb.provisioning.license.model.LicenseKey>)
					returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<com.liferay.osb.provisioning.license.model.LicenseKey> getLicenseKeys(
				HttpPrincipal httpPrincipal, String assetReceiptLicenseUuid,
				String productId, String serverId, boolean active, int start,
				int end, com.liferay.portal.kernel.util.OrderByComparator obc)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				LicenseKeyServiceUtil.class, "getLicenseKeys",
				_getLicenseKeysParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, assetReceiptLicenseUuid, productId, serverId, active,
				start, end, obc);

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
				<com.liferay.osb.provisioning.license.model.LicenseKey>)
					returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<com.liferay.osb.provisioning.license.model.LicenseKey>
				getLicenseKeysByName(
					HttpPrincipal httpPrincipal, String productName,
					String serverId, boolean active, int start, int end,
					com.liferay.portal.kernel.util.OrderByComparator obc)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				LicenseKeyServiceUtil.class, "getLicenseKeysByName",
				_getLicenseKeysByNameParameterTypes9);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, productName, serverId, active, start, end, obc);

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
				<com.liferay.osb.provisioning.license.model.LicenseKey>)
					returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static boolean isActive(
			HttpPrincipal httpPrincipal, String serverId, String productId,
			String key)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				LicenseKeyServiceUtil.class, "isActive",
				_isActiveParameterTypes10);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, serverId, productId, key);

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

			return ((Boolean)returnObj).booleanValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.provisioning.license.model.LicenseKey
			replaceLicenseKey(
				HttpPrincipal httpPrincipal, long licenseKeyId,
				java.util.Date startDate, java.util.Date expirationDate)
		throws Exception {

		try {
			MethodKey methodKey = new MethodKey(
				LicenseKeyServiceUtil.class, "replaceLicenseKey",
				_replaceLicenseKeyParameterTypes11);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, licenseKeyId, startDate, expirationDate);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof Exception) {
					throw (Exception)exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.osb.provisioning.license.model.LicenseKey)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.provisioning.license.model.LicenseKey
			replaceLicenseKey(
				HttpPrincipal httpPrincipal, String uuid,
				java.util.Date startDate, java.util.Date expirationDate)
		throws Exception {

		try {
			MethodKey methodKey = new MethodKey(
				LicenseKeyServiceUtil.class, "replaceLicenseKey",
				_replaceLicenseKeyParameterTypes12);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, uuid, startDate, expirationDate);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof Exception) {
					throw (Exception)exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.osb.provisioning.license.model.LicenseKey)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.search.Hits search(
			HttpPrincipal httpPrincipal, long companyId, String createUserUuid,
			java.util.Date createDateGT, java.util.Date createDateLT,
			String modifiedUserUuid, java.util.Date modifiedDateGT,
			java.util.Date modifiedDateLT, String accountKey,
			String productPurchaseKey, String accountName,
			java.util.Date startDateGT, java.util.Date startDateLT,
			Long[] licenseEntryIds, String[] productKeys, String productName,
			String productId, String[] productVersions, String owner,
			String description, String hostName, String ipAddress,
			String macAddress, String serverId, String key,
			java.util.Date expirationDateGT, java.util.Date expirationDateLT,
			Boolean active, java.util.LinkedHashMap<String, Object> params,
			boolean andSearch, int start, int end,
			com.liferay.portal.kernel.search.Sort sort)
		throws Exception {

		try {
			MethodKey methodKey = new MethodKey(
				LicenseKeyServiceUtil.class, "search", _searchParameterTypes13);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId, createUserUuid, createDateGT,
				createDateLT, modifiedUserUuid, modifiedDateGT, modifiedDateLT,
				accountKey, productPurchaseKey, accountName, startDateGT,
				startDateLT, licenseEntryIds, productKeys, productName,
				productId, productVersions, owner, description, hostName,
				ipAddress, macAddress, serverId, key, expirationDateGT,
				expirationDateLT, active, params, andSearch, start, end, sort);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof Exception) {
					throw (Exception)exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.search.Hits)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<com.liferay.osb.provisioning.license.model.LicenseKey> search(
				HttpPrincipal httpPrincipal, String createUserUuid,
				java.util.Date createDateGT, java.util.Date createDateLT,
				String modifiedUserUuid, java.util.Date modifiedDateGT,
				java.util.Date modifiedDateLT, String accountKey,
				String productPurchaseKey, String accountName,
				java.util.Date startDateGT, java.util.Date startDateLT,
				long[] licenseEntryIds, String[] productKeys,
				String productName, String productId, String[] productVersions,
				long[] clusterIds, String owner, String description,
				String hostName, String ipAddress, String macAddress,
				String serverId, String key, java.util.Date expirationDateGT,
				java.util.Date expirationDateLT,
				java.util.LinkedHashMap<String, Object> params,
				boolean andSearch, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator obc)
			throws Exception {

		try {
			MethodKey methodKey = new MethodKey(
				LicenseKeyServiceUtil.class, "search", _searchParameterTypes14);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, createUserUuid, createDateGT, createDateLT,
				modifiedUserUuid, modifiedDateGT, modifiedDateLT, accountKey,
				productPurchaseKey, accountName, startDateGT, startDateLT,
				licenseEntryIds, productKeys, productName, productId,
				productVersions, clusterIds, owner, description, hostName,
				ipAddress, macAddress, serverId, key, expirationDateGT,
				expirationDateLT, params, andSearch, start, end, obc);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof Exception) {
					throw (Exception)exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List
				<com.liferay.osb.provisioning.license.model.LicenseKey>)
					returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static int searchCount(
			HttpPrincipal httpPrincipal, String createUserUuid,
			java.util.Date createDateGT, java.util.Date createDateLT,
			String modifiedUserUuid, java.util.Date modifiedDateGT,
			java.util.Date modifiedDateLT, String accountKey,
			String productPurchaseKey, String accountName,
			java.util.Date startDateGT, java.util.Date startDateLT,
			long[] licenseEntryIds, String[] productKeys, String productName,
			String productId, String[] productVersions, long[] clusterIds,
			String owner, String description, String hostName, String ipAddress,
			String macAddress, String serverId, String key,
			java.util.Date expirationDateGT, java.util.Date expirationDateLT,
			java.util.LinkedHashMap<String, Object> params, boolean andSearch)
		throws Exception {

		try {
			MethodKey methodKey = new MethodKey(
				LicenseKeyServiceUtil.class, "searchCount",
				_searchCountParameterTypes15);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, createUserUuid, createDateGT, createDateLT,
				modifiedUserUuid, modifiedDateGT, modifiedDateLT, accountKey,
				productPurchaseKey, accountName, startDateGT, startDateLT,
				licenseEntryIds, productKeys, productName, productId,
				productVersions, clusterIds, owner, description, hostName,
				ipAddress, macAddress, serverId, key, expirationDateGT,
				expirationDateLT, params, andSearch);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof Exception) {
					throw (Exception)exception;
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

	public static com.liferay.osb.provisioning.license.model.LicenseKey
			updateLicenseKey(
				HttpPrincipal httpPrincipal, long licenseKeyId,
				String productPurchaseKey, boolean complimentary,
				boolean active)
		throws Exception {

		try {
			MethodKey methodKey = new MethodKey(
				LicenseKeyServiceUtil.class, "updateLicenseKey",
				_updateLicenseKeyParameterTypes16);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, licenseKeyId, productPurchaseKey, complimentary,
				active);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof Exception) {
					throw (Exception)exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.osb.provisioning.license.model.LicenseKey)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static void updateLicenseKey(
			HttpPrincipal httpPrincipal, String userUuid, String uuid,
			boolean active)
		throws Exception {

		try {
			MethodKey methodKey = new MethodKey(
				LicenseKeyServiceUtil.class, "updateLicenseKey",
				_updateLicenseKeyParameterTypes17);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, userUuid, uuid, active);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof Exception) {
					throw (Exception)exception;
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

	public static void updateLicenseKeys(
			HttpPrincipal httpPrincipal, String assetReceiptLicenseUuid,
			boolean active)
		throws Exception {

		try {
			MethodKey methodKey = new MethodKey(
				LicenseKeyServiceUtil.class, "updateLicenseKeys",
				_updateLicenseKeysParameterTypes18);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, assetReceiptLicenseUuid, active);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof Exception) {
					throw (Exception)exception;
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
		LicenseKeyServiceHttp.class);

	private static final Class<?>[] _addLicenseKeyParameterTypes0 =
		new Class[] {
			String.class, String.class, long.class, String.class, String.class,
			String.class, String.class, String.class, long.class, String.class,
			String.class, int.class, int.class, int.class, int.class, int.class,
			String.class, String.class, String[].class, String[].class,
			String[].class, java.util.Date.class, java.util.Date.class,
			boolean.class, boolean.class
		};
	private static final Class<?>[] _addLicenseKeyParameterTypes1 =
		new Class[] {
			String.class, String.class, String.class, String.class,
			String.class, int.class, String.class, long.class, String.class,
			String.class, String.class, String.class, String.class,
			java.util.Date.class, java.util.Date.class
		};
	private static final Class<?>[] _extendLicenseKeyParameterTypes2 =
		new Class[] {
			long.class, String.class, java.util.Date.class, java.util.Date.class
		};
	private static final Class<?>[]
		_getAssetReceiptLicenseLicenseKeysParameterTypes3 = new Class[] {
			String.class, boolean.class, boolean.class
		};
	private static final Class<?>[]
		_getAssetReceiptLicenseLicenseKeysCountParameterTypes4 = new Class[] {
			String.class, boolean.class, boolean.class
		};
	private static final Class<?>[] _getLicenseKeyParameterTypes5 =
		new Class[] {long.class};
	private static final Class<?>[] _getLicenseKeyParameterTypes6 =
		new Class[] {String.class};
	private static final Class<?>[] _getLicenseKeysParameterTypes7 =
		new Class[] {String.class, String.class};
	private static final Class<?>[] _getLicenseKeysParameterTypes8 =
		new Class[] {
			String.class, String.class, String.class, boolean.class, int.class,
			int.class, com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getLicenseKeysByNameParameterTypes9 =
		new Class[] {
			String.class, String.class, boolean.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _isActiveParameterTypes10 = new Class[] {
		String.class, String.class, String.class
	};
	private static final Class<?>[] _replaceLicenseKeyParameterTypes11 =
		new Class[] {long.class, java.util.Date.class, java.util.Date.class};
	private static final Class<?>[] _replaceLicenseKeyParameterTypes12 =
		new Class[] {String.class, java.util.Date.class, java.util.Date.class};
	private static final Class<?>[] _searchParameterTypes13 = new Class[] {
		long.class, String.class, java.util.Date.class, java.util.Date.class,
		String.class, java.util.Date.class, java.util.Date.class, String.class,
		String.class, String.class, java.util.Date.class, java.util.Date.class,
		Long[].class, String[].class, String.class, String.class,
		String[].class, String.class, String.class, String.class, String.class,
		String.class, String.class, String.class, java.util.Date.class,
		java.util.Date.class, Boolean.class, java.util.LinkedHashMap.class,
		boolean.class, int.class, int.class,
		com.liferay.portal.kernel.search.Sort.class
	};
	private static final Class<?>[] _searchParameterTypes14 = new Class[] {
		String.class, java.util.Date.class, java.util.Date.class, String.class,
		java.util.Date.class, java.util.Date.class, String.class, String.class,
		String.class, java.util.Date.class, java.util.Date.class, long[].class,
		String[].class, String.class, String.class, String[].class,
		long[].class, String.class, String.class, String.class, String.class,
		String.class, String.class, String.class, java.util.Date.class,
		java.util.Date.class, java.util.LinkedHashMap.class, boolean.class,
		int.class, int.class,
		com.liferay.portal.kernel.util.OrderByComparator.class
	};
	private static final Class<?>[] _searchCountParameterTypes15 = new Class[] {
		String.class, java.util.Date.class, java.util.Date.class, String.class,
		java.util.Date.class, java.util.Date.class, String.class, String.class,
		String.class, java.util.Date.class, java.util.Date.class, long[].class,
		String[].class, String.class, String.class, String[].class,
		long[].class, String.class, String.class, String.class, String.class,
		String.class, String.class, String.class, java.util.Date.class,
		java.util.Date.class, java.util.LinkedHashMap.class, boolean.class
	};
	private static final Class<?>[] _updateLicenseKeyParameterTypes16 =
		new Class[] {long.class, String.class, boolean.class, boolean.class};
	private static final Class<?>[] _updateLicenseKeyParameterTypes17 =
		new Class[] {String.class, String.class, boolean.class};
	private static final Class<?>[] _updateLicenseKeysParameterTypes18 =
		new Class[] {String.class, boolean.class};

}