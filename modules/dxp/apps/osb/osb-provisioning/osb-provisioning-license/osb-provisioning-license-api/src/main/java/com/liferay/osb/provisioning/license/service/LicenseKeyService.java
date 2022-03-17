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
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the remote service interface for LicenseKey. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see LicenseKeyServiceUtil
 * @generated
 */
@AccessControlled
@JSONWebService
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface LicenseKeyService extends BaseService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.liferay.osb.provisioning.license.service.impl.LicenseKeyServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the license key remote service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link LicenseKeyServiceUtil} if injection and service tracking are not available.
	 */
	public LicenseKey addLicenseKey(
			String userName, String userUuid, long licenseEntryId,
			String productKey, String accountKey, String productPurchaseKey,
			String accountName, String productVersion, long clusterId,
			String name, String owner, int maxClusterNodes, int maxServers,
			int maxHttpSessions, int maxConcurrentUsers, int maxUsers,
			String sizing, String description, String[] hostNames,
			String[] ipAddresses, String[] macAddresses, Date startDate,
			Date expirationDate, boolean complimentary, boolean active)
		throws Exception;

	@JSONWebService
	public LicenseKey addLicenseKey(
			String userUuid, String assetReceiptLicenseUuid,
			String licenseEntryType, String productEntryName, String productId,
			int productVersion, String owner, long maxUsers, String description,
			String hostName, String ipAddresses, String macAddresses,
			String serverId, Date startDate, Date expirationDate)
		throws Exception;

	public LicenseKey extendLicenseKey(
			long licenseKeyId, String productPurchaseKey, Date startDate,
			Date expirationDate)
		throws Exception;

	@JSONWebService
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LicenseKey> getAssetReceiptLicenseLicenseKeys(
			String assetReceiptLicenseUuid, boolean complimentary,
			boolean active)
		throws PortalException;

	@JSONWebService
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getAssetReceiptLicenseLicenseKeysCount(
			String assetReceiptLicenseUuid, boolean complimentary,
			boolean active)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LicenseKey getLicenseKey(long licenseKeyId) throws PortalException;

	@JSONWebService
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LicenseKey getLicenseKey(String uuid) throws PortalException;

	@JSONWebService
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LicenseKey> getLicenseKeys(String productId, String serverId)
		throws PortalException;

	@JSONWebService
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LicenseKey> getLicenseKeys(
			String assetReceiptLicenseUuid, String productId, String serverId,
			boolean active, int start, int end, OrderByComparator obc)
		throws PortalException;

	@JSONWebService
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LicenseKey> getLicenseKeysByName(
			String productName, String serverId, boolean active, int start,
			int end, OrderByComparator obc)
		throws PortalException;

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	@JSONWebService
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isActive(String serverId, String productId, String key)
		throws PortalException;

	public LicenseKey replaceLicenseKey(
			long licenseKeyId, Date startDate, Date expirationDate)
		throws Exception;

	@JSONWebService
	public LicenseKey replaceLicenseKey(
			String uuid, Date startDate, Date expirationDate)
		throws Exception;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
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
		throws Exception;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
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
		throws Exception;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
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
		throws Exception;

	public LicenseKey updateLicenseKey(
			long licenseKeyId, String productPurchaseKey, boolean complimentary,
			boolean active)
		throws Exception;

	@JSONWebService
	public void updateLicenseKey(String userUuid, String uuid, boolean active)
		throws Exception;

	@JSONWebService
	public void updateLicenseKeys(
			String assetReceiptLicenseUuid, boolean active)
		throws Exception;

}