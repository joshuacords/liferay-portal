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

package com.liferay.osb.provisioning.license.service.impl;

import com.liferay.osb.provisioning.constants.ProvisioningActionKeys;
import com.liferay.osb.provisioning.license.model.LicenseKey;
import com.liferay.osb.provisioning.license.permission.LicenseKeyPermission;
import com.liferay.osb.provisioning.license.service.base.LicenseKeyServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.instances.service.PortalInstancesLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	property = {
		"json.web.service.context.name=provisioning",
		"json.web.service.context.path=LicenseKey"
	},
	service = AopService.class
)
public class LicenseKeyServiceImpl extends LicenseKeyServiceBaseImpl {

	public LicenseKey addLicenseKey(
			String userName, String userUuid, long licenseEntryId,
			String productKey, String accountKey, String productPurchaseKey,
			String accountName, String productVersion, long clusterId,
			String name, String owner, int maxClusterNodes, int maxServers,
			int maxHttpSessions, int maxConcurrentUsers, int maxUsers,
			String sizing, String description, String[] hostNames,
			String[] ipAddresses, String[] macAddresses, Date startDate,
			Date expirationDate, boolean complimentary, boolean active)
		throws Exception {

		_licenseKeyPermission.check(
			getPermissionChecker(), ProvisioningActionKeys.MANAGE_LICENSE_KEYS);

		return licenseKeyLocalService.addLicenseKey(
			userName, userUuid, licenseEntryId, productKey, accountKey,
			productPurchaseKey, accountName, productVersion, clusterId, name,
			owner, maxClusterNodes, maxServers, maxHttpSessions,
			maxConcurrentUsers, maxUsers, sizing, description, hostNames,
			ipAddresses, macAddresses, startDate, expirationDate, complimentary,
			true);
	}

	@JSONWebService
	public LicenseKey addLicenseKey(
			String userUuid, String assetReceiptLicenseUuid,
			String licenseEntryType, String productEntryName, String productId,
			int productVersion, String owner, long maxUsers, String description,
			String hostName, String ipAddresses, String macAddresses,
			String serverId, Date startDate, Date expirationDate)
		throws Exception {

		validateJSONWebServicePermissions();

		return licenseKeyLocalService.addLicenseKey(
			userUuid, assetReceiptLicenseUuid, licenseEntryType,
			productEntryName, productId, String.valueOf(productVersion), owner,
			maxUsers, description, hostName, ipAddresses, macAddresses,
			serverId, startDate, expirationDate);
	}

	public LicenseKey extendLicenseKey(
			long licenseKeyId, String productPurchaseKey, Date startDate,
			Date expirationDate)
		throws Exception {

		_licenseKeyPermission.check(
			getPermissionChecker(), ProvisioningActionKeys.MANAGE_LICENSE_KEYS);

		User user = getUser();

		return licenseKeyLocalService.extendLicenseKey(
			user.getFullName(), user.getUuid(), licenseKeyId,
			productPurchaseKey, startDate, expirationDate);
	}

	@JSONWebService
	public List<LicenseKey> getAssetReceiptLicenseLicenseKeys(
			String assetReceiptLicenseUuid, boolean complimentary,
			boolean active)
		throws PortalException {

		validateJSONWebServicePermissions();

		return licenseKeyLocalService.getAssetReceiptLicenseLicenseKeys(
			assetReceiptLicenseUuid, complimentary, active);
	}

	@JSONWebService
	public int getAssetReceiptLicenseLicenseKeysCount(
			String assetReceiptLicenseUuid, boolean complimentary,
			boolean active)
		throws PortalException {

		validateJSONWebServicePermissions();

		return licenseKeyLocalService.getAssetReceiptLicenseLicenseKeysCount(
			assetReceiptLicenseUuid, complimentary, active);
	}

	public LicenseKey getLicenseKey(long licenseKeyId) throws PortalException {
		_licenseKeyPermission.check(
			getPermissionChecker(), ProvisioningActionKeys.VIEW);

		return licenseKeyLocalService.getLicenseKey(licenseKeyId);
	}

	@JSONWebService
	public LicenseKey getLicenseKey(String uuid) throws PortalException {
		validateJSONWebServicePermissions();

		return licenseKeyLocalService.getLicenseKeyByUuid(uuid);
	}

	@JSONWebService
	public List<LicenseKey> getLicenseKeys(String productId, String serverId)
		throws PortalException {

		validateJSONWebServicePermissions();

		return licenseKeyLocalService.getLicenseKeys(productId, serverId);
	}

	@JSONWebService
	public List<LicenseKey> getLicenseKeys(
			String assetReceiptLicenseUuid, String productId, String serverId,
			boolean active, int start, int end, OrderByComparator obc)
		throws PortalException {

		validateJSONWebServicePermissions();

		return licenseKeyLocalService.getLicenseKeys(
			assetReceiptLicenseUuid, productId, serverId, active, start, end,
			obc);
	}

	@JSONWebService
	public List<LicenseKey> getLicenseKeysByName(
			String productName, String serverId, boolean active, int start,
			int end, OrderByComparator obc)
		throws PortalException {

		validateJSONWebServicePermissions();

		return licenseKeyLocalService.getLicenseKeysByName(
			productName, serverId, active, start, end, obc);
	}

	@JSONWebService
	public boolean isActive(String serverId, String productId, String key)
		throws PortalException {

		validateJSONWebServicePermissions();

		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		params.put("active", true);

		int activeLicensesCount = licenseKeyLocalService.searchCount(
			null, null, null, null, null, null, null, null, null, null, null,
			new long[0], new String[0], null, productId, new String[0],
			new long[0], null, null, null, null, null, serverId, key, null,
			null, params, true);

		if (activeLicensesCount > 0) {
			return true;
		}

		return false;
	}

	public LicenseKey replaceLicenseKey(
			long licenseKeyId, Date startDate, Date expirationDate)
		throws Exception {

		_licenseKeyPermission.check(
			getPermissionChecker(), ProvisioningActionKeys.MANAGE_LICENSE_KEYS);

		User user = getUser();

		return licenseKeyLocalService.replaceLicenseKey(
			user.getFullName(), user.getUserUuid(), licenseKeyId, startDate,
			expirationDate);
	}

	@JSONWebService
	public LicenseKey replaceLicenseKey(
			String uuid, Date startDate, Date expirationDate)
		throws Exception {

		validateJSONWebServicePermissions();

		LicenseKey licenseKey = licenseKeyLocalService.getLicenseKeyByUuid(
			uuid);

		return licenseKeyLocalService.replaceLicenseKey(
			licenseKey.getUserName(), licenseKey.getUserUuid(),
			licenseKey.getLicenseKeyId(), startDate, expirationDate);
	}

	public Hits search(
			long companyId, String createUserUuid, Date createDateGT,
			Date createDateLT, String modifiedUserUuid, Date modifiedDateGT,
			Date modifiedDateLT, String accountKey, String productPurchaseKey,
			String accountName, Date startDateGT, Date startDateLT,
			Long[] licenseEntryIds, String[] productKeys, String productName,
			String productId, String[] productVersions, String owner,
			String description, String hostName, String ipAddress,
			String macAddress, String serverId, String key,
			Date expirationDateGT, Date expirationDateLT, Boolean active,
			LinkedHashMap<String, Object> params, boolean andSearch, int start,
			int end, Sort sort)
		throws Exception {

		_licenseKeyPermission.check(
			getPermissionChecker(), ProvisioningActionKeys.VIEW);

		return licenseKeyLocalService.search(
			companyId, createUserUuid, createDateGT, createDateLT,
			modifiedUserUuid, modifiedDateGT, modifiedDateLT, accountKey,
			productPurchaseKey, accountName, startDateGT, startDateLT,
			licenseEntryIds, productKeys, productName, productId,
			productVersions, owner, description, hostName, ipAddress,
			macAddress, serverId, key, expirationDateGT, expirationDateLT,
			active, params, andSearch, start, end, sort);
	}

	public List<LicenseKey> search(
			String createUserUuid, Date createDateGT, Date createDateLT,
			String modifiedUserUuid, Date modifiedDateGT, Date modifiedDateLT,
			String accountKey, String productPurchaseKey, String accountName,
			Date startDateGT, Date startDateLT, long[] licenseEntryIds,
			String[] productKeys, String productName, String productId,
			String[] productVersions, long[] clusterIds, String owner,
			String description, String hostName, String ipAddress,
			String macAddress, String serverId, String key,
			Date expirationDateGT, Date expirationDateLT,
			LinkedHashMap<String, Object> params, boolean andSearch, int start,
			int end, OrderByComparator obc)
		throws Exception {

		_licenseKeyPermission.check(
			getPermissionChecker(), ProvisioningActionKeys.VIEW);

		return licenseKeyLocalService.search(
			createUserUuid, createDateGT, createDateLT, modifiedUserUuid,
			modifiedDateGT, modifiedDateLT, accountKey, productPurchaseKey,
			accountName, startDateGT, startDateLT, licenseEntryIds, productKeys,
			productName, productId, productVersions, clusterIds, owner,
			description, hostName, ipAddress, macAddress, serverId, key,
			expirationDateGT, expirationDateLT, params, andSearch, start, end,
			obc);
	}

	public int searchCount(
			String createUserUuid, Date createDateGT, Date createDateLT,
			String modifiedUserUuid, Date modifiedDateGT, Date modifiedDateLT,
			String accountKey, String productPurchaseKey, String accountName,
			Date startDateGT, Date startDateLT, long[] licenseEntryIds,
			String[] productKeys, String productName, String productId,
			String[] productVersions, long[] clusterIds, String owner,
			String description, String hostName, String ipAddress,
			String macAddress, String serverId, String key,
			Date expirationDateGT, Date expirationDateLT,
			LinkedHashMap<String, Object> params, boolean andSearch)
		throws Exception {

		_licenseKeyPermission.check(
			getPermissionChecker(), ProvisioningActionKeys.VIEW);

		return licenseKeyLocalService.searchCount(
			createUserUuid, createDateGT, createDateLT, modifiedUserUuid,
			modifiedDateGT, modifiedDateLT, accountKey, productPurchaseKey,
			accountName, startDateGT, startDateLT, licenseEntryIds, productKeys,
			productName, productId, productVersions, clusterIds, owner,
			description, hostName, ipAddress, macAddress, serverId, key,
			expirationDateGT, expirationDateLT, params, andSearch);
	}

	public LicenseKey updateLicenseKey(
			long licenseKeyId, String productPurchaseKey, boolean complimentary,
			boolean active)
		throws Exception {

		_licenseKeyPermission.check(
			getPermissionChecker(), ProvisioningActionKeys.MANAGE_LICENSE_KEYS);

		User user = getUser();

		return licenseKeyLocalService.updateLicenseKey(
			user.getFullName(), user.getUuid(), licenseKeyId,
			productPurchaseKey, complimentary, active);
	}

	@JSONWebService
	public void updateLicenseKey(String userUuid, String uuid, boolean active)
		throws Exception {

		validateJSONWebServicePermissions();

		LicenseKey licenseKey = licenseKeyLocalService.getLicenseKeyByUuid(
			uuid);

		licenseKeyLocalService.updateLicenseKey(
			StringPool.BLANK, userUuid, licenseKey.getLicenseKeyId(), active);
	}

	@JSONWebService
	public void updateLicenseKeys(
			String assetReceiptLicenseUuid, boolean active)
		throws Exception {

		validateJSONWebServicePermissions();

		List<LicenseKey> licenseKeys = licenseKeyPersistence.findByARLU_A(
			assetReceiptLicenseUuid, !active);

		for (LicenseKey licenseKey : licenseKeys) {
			licenseKeyLocalService.updateLicenseKey(
				licenseKey.getModifiedUserName(),
				licenseKey.getModifiedUserUuid(), licenseKey.getLicenseKeyId(),
				active);
		}
	}

	protected void validateJSONWebServicePermissions() throws PortalException {
		PermissionChecker permissionChecker = getPermissionChecker();

		if (!permissionChecker.isCompanyAdmin()) {
			throw new PrincipalException();
		}
	}

	@Reference
	private LicenseKeyPermission _licenseKeyPermission;

	@Reference
	private PortalInstancesLocalService _portalInstancesLocalService;

}