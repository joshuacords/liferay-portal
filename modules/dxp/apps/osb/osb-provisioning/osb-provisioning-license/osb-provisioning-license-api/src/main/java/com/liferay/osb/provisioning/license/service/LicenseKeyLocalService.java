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

import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.provisioning.license.model.LicenseEntry;
import com.liferay.osb.provisioning.license.model.LicenseKey;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the local service interface for LicenseKey. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see LicenseKeyLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface LicenseKeyLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.liferay.osb.provisioning.license.service.impl.LicenseKeyLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the license key local service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link LicenseKeyLocalServiceUtil} if injection and service tracking are not available.
	 */

	/**
	 * Adds the license key to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LicenseKeyLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param licenseKey the license key
	 * @return the license key that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	public LicenseKey addLicenseKey(LicenseKey licenseKey);

	public LicenseKey addLicenseKey(
			String userName, String userUuid, LicenseEntry licenseEntry,
			Product product, String accountKey, String productPurchaseKey,
			String accountName, String productVersion, long clusterId,
			String name, String owner, int maxClusterNodes, int maxServers,
			int maxHttpSessions, int maxConcurrentUsers, int maxUsers,
			String sizing, String description, String[] hostNames,
			String[] ipAddresses, String[] macAddresses, Date startDate,
			Date expirationDate, String additionalInfo, boolean complimentary,
			boolean active)
		throws Exception;

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

	public LicenseKey addLicenseKey(
			String userUuid, String assetReceiptLicenseUuid,
			String licenseEntryType, String productName, String productId,
			String productVersion, String owner, long maxUsers,
			String description, String hostName, String ipAddresses,
			String macAddresses, String serverId, Date startDate,
			Date expirationDate)
		throws Exception;

	public LicenseKey addLicenseKey(
			String userName, String userUuid, String licenseEntryType,
			String productKey, String accountKey, String productPurchaseKey,
			String productVersion, String name, String owner,
			int maxClusterNodes, String sizing, String description,
			String hostName, String ipAddresses, String macAddresses,
			Date startDate, Date expirationDate, boolean complimentary,
			boolean active)
		throws Exception;

	/**
	 * Creates a new license key with the primary key. Does not add the license key to the database.
	 *
	 * @param licenseKeyId the primary key for the new license key
	 * @return the new license key
	 */
	@Transactional(enabled = false)
	public LicenseKey createLicenseKey(long licenseKeyId);

	/**
	 * Deletes the license key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LicenseKeyLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param licenseKey the license key
	 * @return the license key that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	public LicenseKey deleteLicenseKey(LicenseKey licenseKey);

	/**
	 * Deletes the license key with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LicenseKeyLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param licenseKeyId the primary key of the license key
	 * @return the license key that was removed
	 * @throws PortalException if a license key with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	public LicenseKey deleteLicenseKey(long licenseKeyId)
		throws PortalException;

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DynamicQuery dynamicQuery();

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.provisioning.license.model.impl.LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end);

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.provisioning.license.model.impl.LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection);

	public LicenseKey extendLicenseKey(
			String userName, String userUuid, long licenseKeyId,
			String productPurchaseKey, Date startDate, Date expirationDate)
		throws Exception;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LicenseKey fetchLicenseKey(long licenseKeyId);

	/**
	 * Returns the license key with the matching UUID and company.
	 *
	 * @param uuid the license key's UUID
	 * @param companyId the primary key of the company
	 * @return the matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LicenseKey fetchLicenseKeyByUuidAndCompanyId(
		String uuid, long companyId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LicenseKey> getAssetReceiptLicenseLicenseKeys(
		String assetReceiptLicenseUuid, boolean complimentary, boolean active);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getAssetReceiptLicenseLicenseKeysCount(
		String assetReceiptLicenseUuid, boolean complimentary, boolean active);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	 * Returns a range of all the license keies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.provisioning.license.model.impl.LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of license keies
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LicenseKey> getLicenseKeies(int start, int end);

	/**
	 * Returns the number of license keies.
	 *
	 * @return the number of license keies
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getLicenseKeiesCount();

	/**
	 * Returns the license key with the primary key.
	 *
	 * @param licenseKeyId the primary key of the license key
	 * @return the license key
	 * @throws PortalException if a license key with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LicenseKey getLicenseKey(long licenseKeyId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LicenseKey getLicenseKeyByUuid(String uuid) throws PortalException;

	/**
	 * Returns the license key with the matching UUID and company.
	 *
	 * @param uuid the license key's UUID
	 * @param companyId the primary key of the company
	 * @return the matching license key
	 * @throws PortalException if a matching license key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LicenseKey getLicenseKeyByUuidAndCompanyId(
			String uuid, long companyId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LicenseKey> getLicenseKeys(String productId, String serverId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LicenseKey> getLicenseKeys(
		String assetReceiptLicenseUuid, String productId, String serverId,
		boolean active, int start, int end, OrderByComparator obc);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LicenseKey> getLicenseKeysByName(
		String productName, String serverId, boolean active, int start, int end,
		OrderByComparator obc);

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	/**
	 * @throws PortalException
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	@Indexable(type = IndexableType.REINDEX)
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LicenseKey reindex(long licenseKeyId) throws PortalException;

	public LicenseKey replaceLicenseKey(
			String userName, String userUuid, long licenseKeyId, Date startDate,
			Date expirationDate)
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
	public Hits search(
			long companyId, String keywords, int start, int end, Sort sort)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LicenseKey> search(
		String createUserUuid, Date createDateGT, Date createDateLT,
		String modifiedUserUuid, Date modifiedDateGT, Date modifiedDateLT,
		String accountKey, String productPurchaseKey, String accountName,
		Date startDateGT, Date startDateLT, long[] licenseEntryIds,
		String[] productKeys, String productName, String productId,
		String[] productVersions, long[] clusterIds, String owner,
		String description, String hostName, String ipAddress,
		String macAddress, String serverId, String key, Date expirationDateGT,
		Date expirationDateLT, LinkedHashMap<String, Object> params,
		boolean andSearch, int start, int end, OrderByComparator obc);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int searchCount(
			long companyId, String createUserUuid, Date createDateGT,
			Date createDateLT, String modifiedUserUuid, Date modifiedDateGT,
			Date modifiedDateLT, String accountKey, String productPurchaseKey,
			String accountName, Date startDateGT, Date startDateLT,
			Long[] licenseEntryIds, String[] productKeys, String productName,
			String productId, String[] productVersions, String owner,
			String description, String hostName, String ipAddress,
			String macAddress, String serverId, String key,
			Date expirationDateGT, Date expirationDateLT, Boolean active,
			LinkedHashMap<String, Object> params, boolean andSearch)
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
		String macAddress, String serverId, String key, Date expirationDateGT,
		Date expirationDateLT, LinkedHashMap<String, Object> params,
		boolean andSearch);

	/**
	 * Updates the license key in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LicenseKeyLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param licenseKey the license key
	 * @return the license key that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	public LicenseKey updateLicenseKey(LicenseKey licenseKey);

	public LicenseKey updateLicenseKey(
			String userName, String userUuid, long licenseKeyId, boolean active)
		throws Exception;

	public LicenseKey updateLicenseKey(
			String userName, String userUuid, long licenseKeyId,
			String productPurchaseKey, boolean complimentary, boolean active)
		throws Exception;

}