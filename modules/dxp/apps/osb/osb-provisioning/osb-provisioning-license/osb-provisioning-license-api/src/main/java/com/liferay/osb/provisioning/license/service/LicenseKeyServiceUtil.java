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

package com.liferay.osb.provisioning.license.service;

import com.liferay.osb.provisioning.license.model.LicenseKey;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

/**
 * Provides the remote service utility for LicenseKey. This utility wraps
 * <code>com.liferay.osb.provisioning.license.service.impl.LicenseKeyServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see LicenseKeyService
 * @generated
 */
public class LicenseKeyServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.provisioning.license.service.impl.LicenseKeyServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static LicenseKey addLicenseKey(
			String userName, String userUuid, long licenseEntryId,
			String productKey, String accountKey, String productPurchaseKey,
			String accountName, String productVersion, long clusterId,
			String name, String owner, int maxClusterNodes, int maxServers,
			int maxHttpSessions, int maxConcurrentUsers, int maxUsers,
			String sizing, String description, String[] hostNames,
			String[] ipAddresses, String[] macAddresses,
			java.util.Date startDate, java.util.Date expirationDate,
			boolean complimentary, boolean active)
		throws Exception {

		return getService().addLicenseKey(
			userName, userUuid, licenseEntryId, productKey, accountKey,
			productPurchaseKey, accountName, productVersion, clusterId, name,
			owner, maxClusterNodes, maxServers, maxHttpSessions,
			maxConcurrentUsers, maxUsers, sizing, description, hostNames,
			ipAddresses, macAddresses, startDate, expirationDate, complimentary,
			active);
	}

	public static LicenseKey addLicenseKey(
			String userUuid, String assetReceiptLicenseUuid,
			String licenseEntryType, String productEntryName, String productId,
			int productVersion, String owner, long maxUsers, String description,
			String hostName, String ipAddresses, String macAddresses,
			String serverId, java.util.Date startDate,
			java.util.Date expirationDate)
		throws Exception {

		return getService().addLicenseKey(
			userUuid, assetReceiptLicenseUuid, licenseEntryType,
			productEntryName, productId, productVersion, owner, maxUsers,
			description, hostName, ipAddresses, macAddresses, serverId,
			startDate, expirationDate);
	}

	public static LicenseKey extendLicenseKey(
			long licenseKeyId, String productPurchaseKey,
			java.util.Date startDate, java.util.Date expirationDate)
		throws Exception {

		return getService().extendLicenseKey(
			licenseKeyId, productPurchaseKey, startDate, expirationDate);
	}

	public static List<LicenseKey> getAssetReceiptLicenseLicenseKeys(
			String assetReceiptLicenseUuid, boolean complimentary,
			boolean active)
		throws PortalException {

		return getService().getAssetReceiptLicenseLicenseKeys(
			assetReceiptLicenseUuid, complimentary, active);
	}

	public static int getAssetReceiptLicenseLicenseKeysCount(
			String assetReceiptLicenseUuid, boolean complimentary,
			boolean active)
		throws PortalException {

		return getService().getAssetReceiptLicenseLicenseKeysCount(
			assetReceiptLicenseUuid, complimentary, active);
	}

	public static LicenseKey getLicenseKey(long licenseKeyId)
		throws PortalException {

		return getService().getLicenseKey(licenseKeyId);
	}

	public static LicenseKey getLicenseKey(String uuid) throws PortalException {
		return getService().getLicenseKey(uuid);
	}

	public static List<LicenseKey> getLicenseKeys(
			String productId, String serverId)
		throws PortalException {

		return getService().getLicenseKeys(productId, serverId);
	}

	public static List<LicenseKey> getLicenseKeys(
			String assetReceiptLicenseUuid, String productId, String serverId,
			boolean active, int start, int end, OrderByComparator obc)
		throws PortalException {

		return getService().getLicenseKeys(
			assetReceiptLicenseUuid, productId, serverId, active, start, end,
			obc);
	}

	public static List<LicenseKey> getLicenseKeysByName(
			String productName, String serverId, boolean active, int start,
			int end, OrderByComparator obc)
		throws PortalException {

		return getService().getLicenseKeysByName(
			productName, serverId, active, start, end, obc);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static boolean isActive(
			String serverId, String productId, String key)
		throws PortalException {

		return getService().isActive(serverId, productId, key);
	}

	public static LicenseKey replaceLicenseKey(
			long licenseKeyId, java.util.Date startDate,
			java.util.Date expirationDate)
		throws Exception {

		return getService().replaceLicenseKey(
			licenseKeyId, startDate, expirationDate);
	}

	public static LicenseKey replaceLicenseKey(
			String uuid, java.util.Date startDate,
			java.util.Date expirationDate)
		throws Exception {

		return getService().replaceLicenseKey(uuid, startDate, expirationDate);
	}

	public static com.liferay.portal.kernel.search.Hits search(
			long companyId, String createUserUuid, java.util.Date createDateGT,
			java.util.Date createDateLT, String modifiedUserUuid,
			java.util.Date modifiedDateGT, java.util.Date modifiedDateLT,
			String accountKey, String productPurchaseKey, String accountName,
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

		return getService().search(
			companyId, createUserUuid, createDateGT, createDateLT,
			modifiedUserUuid, modifiedDateGT, modifiedDateLT, accountKey,
			productPurchaseKey, accountName, startDateGT, startDateLT,
			licenseEntryIds, productKeys, productName, productId,
			productVersions, owner, description, hostName, ipAddress,
			macAddress, serverId, key, expirationDateGT, expirationDateLT,
			active, params, andSearch, start, end, sort);
	}

	public static List<LicenseKey> search(
			String createUserUuid, java.util.Date createDateGT,
			java.util.Date createDateLT, String modifiedUserUuid,
			java.util.Date modifiedDateGT, java.util.Date modifiedDateLT,
			String accountKey, String productPurchaseKey, String accountName,
			java.util.Date startDateGT, java.util.Date startDateLT,
			long[] licenseEntryIds, String[] productKeys, String productName,
			String productId, String[] productVersions, long[] clusterIds,
			String owner, String description, String hostName, String ipAddress,
			String macAddress, String serverId, String key,
			java.util.Date expirationDateGT, java.util.Date expirationDateLT,
			java.util.LinkedHashMap<String, Object> params, boolean andSearch,
			int start, int end, OrderByComparator obc)
		throws Exception {

		return getService().search(
			createUserUuid, createDateGT, createDateLT, modifiedUserUuid,
			modifiedDateGT, modifiedDateLT, accountKey, productPurchaseKey,
			accountName, startDateGT, startDateLT, licenseEntryIds, productKeys,
			productName, productId, productVersions, clusterIds, owner,
			description, hostName, ipAddress, macAddress, serverId, key,
			expirationDateGT, expirationDateLT, params, andSearch, start, end,
			obc);
	}

	public static int searchCount(
			String createUserUuid, java.util.Date createDateGT,
			java.util.Date createDateLT, String modifiedUserUuid,
			java.util.Date modifiedDateGT, java.util.Date modifiedDateLT,
			String accountKey, String productPurchaseKey, String accountName,
			java.util.Date startDateGT, java.util.Date startDateLT,
			long[] licenseEntryIds, String[] productKeys, String productName,
			String productId, String[] productVersions, long[] clusterIds,
			String owner, String description, String hostName, String ipAddress,
			String macAddress, String serverId, String key,
			java.util.Date expirationDateGT, java.util.Date expirationDateLT,
			java.util.LinkedHashMap<String, Object> params, boolean andSearch)
		throws Exception {

		return getService().searchCount(
			createUserUuid, createDateGT, createDateLT, modifiedUserUuid,
			modifiedDateGT, modifiedDateLT, accountKey, productPurchaseKey,
			accountName, startDateGT, startDateLT, licenseEntryIds, productKeys,
			productName, productId, productVersions, clusterIds, owner,
			description, hostName, ipAddress, macAddress, serverId, key,
			expirationDateGT, expirationDateLT, params, andSearch);
	}

	public static LicenseKey updateLicenseKey(
			long licenseKeyId, String productPurchaseKey, boolean complimentary,
			boolean active)
		throws Exception {

		return getService().updateLicenseKey(
			licenseKeyId, productPurchaseKey, complimentary, active);
	}

	public static void updateLicenseKey(
			String userUuid, String uuid, boolean active)
		throws Exception {

		getService().updateLicenseKey(userUuid, uuid, active);
	}

	public static void updateLicenseKeys(
			String assetReceiptLicenseUuid, boolean active)
		throws Exception {

		getService().updateLicenseKeys(assetReceiptLicenseUuid, active);
	}

	public static LicenseKeyService getService() {
		return _service;
	}

	private static volatile LicenseKeyService _service;

}