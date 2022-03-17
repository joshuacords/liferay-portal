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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link LicenseKeyService}.
 *
 * @author Brian Wing Shun Chan
 * @see LicenseKeyService
 * @generated
 */
public class LicenseKeyServiceWrapper
	implements LicenseKeyService, ServiceWrapper<LicenseKeyService> {

	public LicenseKeyServiceWrapper(LicenseKeyService licenseKeyService) {
		_licenseKeyService = licenseKeyService;
	}

	@Override
	public com.liferay.osb.provisioning.license.model.LicenseKey addLicenseKey(
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

		return _licenseKeyService.addLicenseKey(
			userName, userUuid, licenseEntryId, productKey, accountKey,
			productPurchaseKey, accountName, productVersion, clusterId, name,
			owner, maxClusterNodes, maxServers, maxHttpSessions,
			maxConcurrentUsers, maxUsers, sizing, description, hostNames,
			ipAddresses, macAddresses, startDate, expirationDate, complimentary,
			active);
	}

	@Override
	public com.liferay.osb.provisioning.license.model.LicenseKey addLicenseKey(
			String userUuid, String assetReceiptLicenseUuid,
			String licenseEntryType, String productEntryName, String productId,
			int productVersion, String owner, long maxUsers, String description,
			String hostName, String ipAddresses, String macAddresses,
			String serverId, java.util.Date startDate,
			java.util.Date expirationDate)
		throws Exception {

		return _licenseKeyService.addLicenseKey(
			userUuid, assetReceiptLicenseUuid, licenseEntryType,
			productEntryName, productId, productVersion, owner, maxUsers,
			description, hostName, ipAddresses, macAddresses, serverId,
			startDate, expirationDate);
	}

	@Override
	public com.liferay.osb.provisioning.license.model.LicenseKey
			extendLicenseKey(
				long licenseKeyId, String productPurchaseKey,
				java.util.Date startDate, java.util.Date expirationDate)
		throws Exception {

		return _licenseKeyService.extendLicenseKey(
			licenseKeyId, productPurchaseKey, startDate, expirationDate);
	}

	@Override
	public java.util.List<com.liferay.osb.provisioning.license.model.LicenseKey>
			getAssetReceiptLicenseLicenseKeys(
				String assetReceiptLicenseUuid, boolean complimentary,
				boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _licenseKeyService.getAssetReceiptLicenseLicenseKeys(
			assetReceiptLicenseUuid, complimentary, active);
	}

	@Override
	public int getAssetReceiptLicenseLicenseKeysCount(
			String assetReceiptLicenseUuid, boolean complimentary,
			boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _licenseKeyService.getAssetReceiptLicenseLicenseKeysCount(
			assetReceiptLicenseUuid, complimentary, active);
	}

	@Override
	public com.liferay.osb.provisioning.license.model.LicenseKey getLicenseKey(
			long licenseKeyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _licenseKeyService.getLicenseKey(licenseKeyId);
	}

	@Override
	public com.liferay.osb.provisioning.license.model.LicenseKey getLicenseKey(
			String uuid)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _licenseKeyService.getLicenseKey(uuid);
	}

	@Override
	public java.util.List<com.liferay.osb.provisioning.license.model.LicenseKey>
			getLicenseKeys(String productId, String serverId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _licenseKeyService.getLicenseKeys(productId, serverId);
	}

	@Override
	public java.util.List<com.liferay.osb.provisioning.license.model.LicenseKey>
			getLicenseKeys(
				String assetReceiptLicenseUuid, String productId,
				String serverId, boolean active, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _licenseKeyService.getLicenseKeys(
			assetReceiptLicenseUuid, productId, serverId, active, start, end,
			obc);
	}

	@Override
	public java.util.List<com.liferay.osb.provisioning.license.model.LicenseKey>
			getLicenseKeysByName(
				String productName, String serverId, boolean active, int start,
				int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _licenseKeyService.getLicenseKeysByName(
			productName, serverId, active, start, end, obc);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _licenseKeyService.getOSGiServiceIdentifier();
	}

	@Override
	public boolean isActive(String serverId, String productId, String key)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _licenseKeyService.isActive(serverId, productId, key);
	}

	@Override
	public com.liferay.osb.provisioning.license.model.LicenseKey
			replaceLicenseKey(
				long licenseKeyId, java.util.Date startDate,
				java.util.Date expirationDate)
		throws Exception {

		return _licenseKeyService.replaceLicenseKey(
			licenseKeyId, startDate, expirationDate);
	}

	@Override
	public com.liferay.osb.provisioning.license.model.LicenseKey
			replaceLicenseKey(
				String uuid, java.util.Date startDate,
				java.util.Date expirationDate)
		throws Exception {

		return _licenseKeyService.replaceLicenseKey(
			uuid, startDate, expirationDate);
	}

	@Override
	public com.liferay.portal.kernel.search.Hits search(
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

		return _licenseKeyService.search(
			companyId, createUserUuid, createDateGT, createDateLT,
			modifiedUserUuid, modifiedDateGT, modifiedDateLT, accountKey,
			productPurchaseKey, accountName, startDateGT, startDateLT,
			licenseEntryIds, productKeys, productName, productId,
			productVersions, owner, description, hostName, ipAddress,
			macAddress, serverId, key, expirationDateGT, expirationDateLT,
			active, params, andSearch, start, end, sort);
	}

	@Override
	public java.util.List<com.liferay.osb.provisioning.license.model.LicenseKey>
			search(
				String createUserUuid, java.util.Date createDateGT,
				java.util.Date createDateLT, String modifiedUserUuid,
				java.util.Date modifiedDateGT, java.util.Date modifiedDateLT,
				String accountKey, String productPurchaseKey,
				String accountName, java.util.Date startDateGT,
				java.util.Date startDateLT, long[] licenseEntryIds,
				String[] productKeys, String productName, String productId,
				String[] productVersions, long[] clusterIds, String owner,
				String description, String hostName, String ipAddress,
				String macAddress, String serverId, String key,
				java.util.Date expirationDateGT,
				java.util.Date expirationDateLT,
				java.util.LinkedHashMap<String, Object> params,
				boolean andSearch, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator obc)
		throws Exception {

		return _licenseKeyService.search(
			createUserUuid, createDateGT, createDateLT, modifiedUserUuid,
			modifiedDateGT, modifiedDateLT, accountKey, productPurchaseKey,
			accountName, startDateGT, startDateLT, licenseEntryIds, productKeys,
			productName, productId, productVersions, clusterIds, owner,
			description, hostName, ipAddress, macAddress, serverId, key,
			expirationDateGT, expirationDateLT, params, andSearch, start, end,
			obc);
	}

	@Override
	public int searchCount(
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

		return _licenseKeyService.searchCount(
			createUserUuid, createDateGT, createDateLT, modifiedUserUuid,
			modifiedDateGT, modifiedDateLT, accountKey, productPurchaseKey,
			accountName, startDateGT, startDateLT, licenseEntryIds, productKeys,
			productName, productId, productVersions, clusterIds, owner,
			description, hostName, ipAddress, macAddress, serverId, key,
			expirationDateGT, expirationDateLT, params, andSearch);
	}

	@Override
	public com.liferay.osb.provisioning.license.model.LicenseKey
			updateLicenseKey(
				long licenseKeyId, String productPurchaseKey,
				boolean complimentary, boolean active)
		throws Exception {

		return _licenseKeyService.updateLicenseKey(
			licenseKeyId, productPurchaseKey, complimentary, active);
	}

	@Override
	public void updateLicenseKey(String userUuid, String uuid, boolean active)
		throws Exception {

		_licenseKeyService.updateLicenseKey(userUuid, uuid, active);
	}

	@Override
	public void updateLicenseKeys(
			String assetReceiptLicenseUuid, boolean active)
		throws Exception {

		_licenseKeyService.updateLicenseKeys(assetReceiptLicenseUuid, active);
	}

	@Override
	public LicenseKeyService getWrappedService() {
		return _licenseKeyService;
	}

	@Override
	public void setWrappedService(LicenseKeyService licenseKeyService) {
		_licenseKeyService = licenseKeyService;
	}

	private LicenseKeyService _licenseKeyService;

}