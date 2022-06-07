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
 * Provides a wrapper for {@link LicenseKeyLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see LicenseKeyLocalService
 * @generated
 */
public class LicenseKeyLocalServiceWrapper
	implements LicenseKeyLocalService, ServiceWrapper<LicenseKeyLocalService> {

	public LicenseKeyLocalServiceWrapper(
		LicenseKeyLocalService licenseKeyLocalService) {

		_licenseKeyLocalService = licenseKeyLocalService;
	}

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
	@Override
	public com.liferay.osb.provisioning.license.model.LicenseKey addLicenseKey(
		com.liferay.osb.provisioning.license.model.LicenseKey licenseKey) {

		return _licenseKeyLocalService.addLicenseKey(licenseKey);
	}

	@Override
	public com.liferay.osb.provisioning.license.model.LicenseKey addLicenseKey(
			String userName, String userUuid,
			com.liferay.osb.provisioning.license.model.LicenseEntry
				licenseEntry,
			com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product
				product,
			String accountKey, String productPurchaseKey, String accountName,
			String productVersion, long clusterId, String name, String owner,
			int maxClusterNodes, int maxServers, int maxHttpSessions,
			int maxConcurrentUsers, int maxUsers, String sizing,
			String description, String[] hostNames, String[] ipAddresses,
			String[] macAddresses, java.util.Date startDate,
			java.util.Date expirationDate, String additionalInfo,
			boolean complimentary, boolean active)
		throws Exception {

		return _licenseKeyLocalService.addLicenseKey(
			userName, userUuid, licenseEntry, product, accountKey,
			productPurchaseKey, accountName, productVersion, clusterId, name,
			owner, maxClusterNodes, maxServers, maxHttpSessions,
			maxConcurrentUsers, maxUsers, sizing, description, hostNames,
			ipAddresses, macAddresses, startDate, expirationDate,
			additionalInfo, complimentary, active);
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

		return _licenseKeyLocalService.addLicenseKey(
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
			String licenseEntryType, String productName, String productId,
			String productVersion, String owner, long maxUsers,
			String description, String hostName, String ipAddresses,
			String macAddresses, String serverId, java.util.Date startDate,
			java.util.Date expirationDate)
		throws Exception {

		return _licenseKeyLocalService.addLicenseKey(
			userUuid, assetReceiptLicenseUuid, licenseEntryType, productName,
			productId, productVersion, owner, maxUsers, description, hostName,
			ipAddresses, macAddresses, serverId, startDate, expirationDate);
	}

	@Override
	public com.liferay.osb.provisioning.license.model.LicenseKey addLicenseKey(
			String userName, String userUuid, String licenseEntryType,
			String productKey, String accountKey, String productPurchaseKey,
			String productVersion, String name, String owner,
			int maxClusterNodes, String sizing, String description,
			String hostName, String ipAddresses, String macAddresses,
			java.util.Date startDate, java.util.Date expirationDate,
			boolean complimentary, boolean active)
		throws Exception {

		return _licenseKeyLocalService.addLicenseKey(
			userName, userUuid, licenseEntryType, productKey, accountKey,
			productPurchaseKey, productVersion, name, owner, maxClusterNodes,
			sizing, description, hostName, ipAddresses, macAddresses, startDate,
			expirationDate, complimentary, active);
	}

	/**
	 * Creates a new license key with the primary key. Does not add the license key to the database.
	 *
	 * @param licenseKeyId the primary key for the new license key
	 * @return the new license key
	 */
	@Override
	public com.liferay.osb.provisioning.license.model.LicenseKey
		createLicenseKey(long licenseKeyId) {

		return _licenseKeyLocalService.createLicenseKey(licenseKeyId);
	}

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
	@Override
	public com.liferay.osb.provisioning.license.model.LicenseKey
		deleteLicenseKey(
			com.liferay.osb.provisioning.license.model.LicenseKey licenseKey) {

		return _licenseKeyLocalService.deleteLicenseKey(licenseKey);
	}

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
	@Override
	public com.liferay.osb.provisioning.license.model.LicenseKey
			deleteLicenseKey(long licenseKeyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _licenseKeyLocalService.deleteLicenseKey(licenseKeyId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _licenseKeyLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _licenseKeyLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _licenseKeyLocalService.dynamicQuery(dynamicQuery);
	}

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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _licenseKeyLocalService.dynamicQuery(dynamicQuery, start, end);
	}

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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _licenseKeyLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _licenseKeyLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _licenseKeyLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.provisioning.license.model.LicenseKey
			extendLicenseKey(
				String userName, String userUuid, long licenseKeyId,
				String productPurchaseKey, java.util.Date startDate,
				java.util.Date expirationDate)
		throws Exception {

		return _licenseKeyLocalService.extendLicenseKey(
			userName, userUuid, licenseKeyId, productPurchaseKey, startDate,
			expirationDate);
	}

	@Override
	public com.liferay.osb.provisioning.license.model.LicenseKey
		fetchLicenseKey(long licenseKeyId) {

		return _licenseKeyLocalService.fetchLicenseKey(licenseKeyId);
	}

	/**
	 * Returns the license key with the matching UUID and company.
	 *
	 * @param uuid the license key's UUID
	 * @param companyId the primary key of the company
	 * @return the matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public com.liferay.osb.provisioning.license.model.LicenseKey
		fetchLicenseKeyByUuidAndCompanyId(String uuid, long companyId) {

		return _licenseKeyLocalService.fetchLicenseKeyByUuidAndCompanyId(
			uuid, companyId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _licenseKeyLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List<com.liferay.osb.provisioning.license.model.LicenseKey>
		getAssetReceiptLicenseLicenseKeys(
			String assetReceiptLicenseUuid, boolean complimentary,
			boolean active) {

		return _licenseKeyLocalService.getAssetReceiptLicenseLicenseKeys(
			assetReceiptLicenseUuid, complimentary, active);
	}

	@Override
	public int getAssetReceiptLicenseLicenseKeysCount(
		String assetReceiptLicenseUuid, boolean complimentary, boolean active) {

		return _licenseKeyLocalService.getAssetReceiptLicenseLicenseKeysCount(
			assetReceiptLicenseUuid, complimentary, active);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _licenseKeyLocalService.getExportActionableDynamicQuery(
			portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _licenseKeyLocalService.getIndexableActionableDynamicQuery();
	}

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
	@Override
	public java.util.List<com.liferay.osb.provisioning.license.model.LicenseKey>
		getLicenseKeies(int start, int end) {

		return _licenseKeyLocalService.getLicenseKeies(start, end);
	}

	/**
	 * Returns the number of license keies.
	 *
	 * @return the number of license keies
	 */
	@Override
	public int getLicenseKeiesCount() {
		return _licenseKeyLocalService.getLicenseKeiesCount();
	}

	/**
	 * Returns the license key with the primary key.
	 *
	 * @param licenseKeyId the primary key of the license key
	 * @return the license key
	 * @throws PortalException if a license key with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.provisioning.license.model.LicenseKey getLicenseKey(
			long licenseKeyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _licenseKeyLocalService.getLicenseKey(licenseKeyId);
	}

	@Override
	public com.liferay.osb.provisioning.license.model.LicenseKey
			getLicenseKeyByUuid(String uuid)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _licenseKeyLocalService.getLicenseKeyByUuid(uuid);
	}

	/**
	 * Returns the license key with the matching UUID and company.
	 *
	 * @param uuid the license key's UUID
	 * @param companyId the primary key of the company
	 * @return the matching license key
	 * @throws PortalException if a matching license key could not be found
	 */
	@Override
	public com.liferay.osb.provisioning.license.model.LicenseKey
			getLicenseKeyByUuidAndCompanyId(String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _licenseKeyLocalService.getLicenseKeyByUuidAndCompanyId(
			uuid, companyId);
	}

	@Override
	public java.util.List<com.liferay.osb.provisioning.license.model.LicenseKey>
		getLicenseKeys(String productId, String serverId) {

		return _licenseKeyLocalService.getLicenseKeys(productId, serverId);
	}

	@Override
	public java.util.List<com.liferay.osb.provisioning.license.model.LicenseKey>
		getLicenseKeys(
			String assetReceiptLicenseUuid, String productId, String serverId,
			boolean active, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator obc) {

		return _licenseKeyLocalService.getLicenseKeys(
			assetReceiptLicenseUuid, productId, serverId, active, start, end,
			obc);
	}

	@Override
	public java.util.List<com.liferay.osb.provisioning.license.model.LicenseKey>
		getLicenseKeysByName(
			String productName, String serverId, boolean active, int start,
			int end, com.liferay.portal.kernel.util.OrderByComparator obc) {

		return _licenseKeyLocalService.getLicenseKeysByName(
			productName, serverId, active, start, end, obc);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _licenseKeyLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _licenseKeyLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public com.liferay.osb.provisioning.license.model.LicenseKey reindex(
			long licenseKeyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _licenseKeyLocalService.reindex(licenseKeyId);
	}

	@Override
	public com.liferay.osb.provisioning.license.model.LicenseKey
			replaceLicenseKey(
				String userName, String userUuid, long licenseKeyId,
				java.util.Date startDate, java.util.Date expirationDate)
		throws Exception {

		return _licenseKeyLocalService.replaceLicenseKey(
			userName, userUuid, licenseKeyId, startDate, expirationDate);
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

		return _licenseKeyLocalService.search(
			companyId, createUserUuid, createDateGT, createDateLT,
			modifiedUserUuid, modifiedDateGT, modifiedDateLT, accountKey,
			productPurchaseKey, accountName, startDateGT, startDateLT,
			licenseEntryIds, productKeys, productName, productId,
			productVersions, owner, description, hostName, ipAddress,
			macAddress, serverId, key, expirationDateGT, expirationDateLT,
			active, params, andSearch, start, end, sort);
	}

	@Override
	public com.liferay.portal.kernel.search.Hits search(
			long companyId, String keywords, int start, int end,
			com.liferay.portal.kernel.search.Sort sort)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _licenseKeyLocalService.search(
			companyId, keywords, start, end, sort);
	}

	@Override
	public java.util.List<com.liferay.osb.provisioning.license.model.LicenseKey>
		search(
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
			int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator obc) {

		return _licenseKeyLocalService.search(
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
			boolean andSearch)
		throws Exception {

		return _licenseKeyLocalService.searchCount(
			companyId, createUserUuid, createDateGT, createDateLT,
			modifiedUserUuid, modifiedDateGT, modifiedDateLT, accountKey,
			productPurchaseKey, accountName, startDateGT, startDateLT,
			licenseEntryIds, productKeys, productName, productId,
			productVersions, owner, description, hostName, ipAddress,
			macAddress, serverId, key, expirationDateGT, expirationDateLT,
			active, params, andSearch);
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
		java.util.LinkedHashMap<String, Object> params, boolean andSearch) {

		return _licenseKeyLocalService.searchCount(
			createUserUuid, createDateGT, createDateLT, modifiedUserUuid,
			modifiedDateGT, modifiedDateLT, accountKey, productPurchaseKey,
			accountName, startDateGT, startDateLT, licenseEntryIds, productKeys,
			productName, productId, productVersions, clusterIds, owner,
			description, hostName, ipAddress, macAddress, serverId, key,
			expirationDateGT, expirationDateLT, params, andSearch);
	}

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
	@Override
	public com.liferay.osb.provisioning.license.model.LicenseKey
		updateLicenseKey(
			com.liferay.osb.provisioning.license.model.LicenseKey licenseKey) {

		return _licenseKeyLocalService.updateLicenseKey(licenseKey);
	}

	@Override
	public com.liferay.osb.provisioning.license.model.LicenseKey
			updateLicenseKey(
				String userName, String userUuid, long licenseKeyId,
				boolean active)
		throws Exception {

		return _licenseKeyLocalService.updateLicenseKey(
			userName, userUuid, licenseKeyId, active);
	}

	@Override
	public com.liferay.osb.provisioning.license.model.LicenseKey
			updateLicenseKey(
				String userName, String userUuid, long licenseKeyId,
				String productPurchaseKey, boolean complimentary,
				boolean active)
		throws Exception {

		return _licenseKeyLocalService.updateLicenseKey(
			userName, userUuid, licenseKeyId, productPurchaseKey, complimentary,
			active);
	}

	@Override
	public LicenseKeyLocalService getWrappedService() {
		return _licenseKeyLocalService;
	}

	@Override
	public void setWrappedService(
		LicenseKeyLocalService licenseKeyLocalService) {

		_licenseKeyLocalService = licenseKeyLocalService;
	}

	private LicenseKeyLocalService _licenseKeyLocalService;

}