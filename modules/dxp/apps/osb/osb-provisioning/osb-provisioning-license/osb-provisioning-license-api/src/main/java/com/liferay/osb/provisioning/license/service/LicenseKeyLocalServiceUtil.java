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
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for LicenseKey. This utility wraps
 * <code>com.liferay.osb.provisioning.license.service.impl.LicenseKeyLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see LicenseKeyLocalService
 * @generated
 */
public class LicenseKeyLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.provisioning.license.service.impl.LicenseKeyLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
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
	public static LicenseKey addLicenseKey(LicenseKey licenseKey) {
		return getService().addLicenseKey(licenseKey);
	}

	public static LicenseKey addLicenseKey(
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

		return getService().addLicenseKey(
			userName, userUuid, licenseEntry, product, accountKey,
			productPurchaseKey, accountName, productVersion, clusterId, name,
			owner, maxClusterNodes, maxServers, maxHttpSessions,
			maxConcurrentUsers, maxUsers, sizing, description, hostNames,
			ipAddresses, macAddresses, startDate, expirationDate,
			additionalInfo, complimentary, active);
	}

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
			String licenseEntryType, String productName, String productId,
			String productVersion, String owner, long maxUsers,
			String description, String hostName, String ipAddresses,
			String macAddresses, String serverId, java.util.Date startDate,
			java.util.Date expirationDate)
		throws Exception {

		return getService().addLicenseKey(
			userUuid, assetReceiptLicenseUuid, licenseEntryType, productName,
			productId, productVersion, owner, maxUsers, description, hostName,
			ipAddresses, macAddresses, serverId, startDate, expirationDate);
	}

	public static LicenseKey addLicenseKey(
			String userName, String userUuid, String licenseEntryType,
			String productKey, String accountKey, String productPurchaseKey,
			String productVersion, String name, String owner,
			int maxClusterNodes, String sizing, String description,
			String hostName, String ipAddresses, String macAddresses,
			java.util.Date startDate, java.util.Date expirationDate,
			boolean complimentary, boolean active)
		throws Exception {

		return getService().addLicenseKey(
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
	public static LicenseKey createLicenseKey(long licenseKeyId) {
		return getService().createLicenseKey(licenseKeyId);
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
	public static LicenseKey deleteLicenseKey(LicenseKey licenseKey) {
		return getService().deleteLicenseKey(licenseKey);
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
	public static LicenseKey deleteLicenseKey(long licenseKeyId)
		throws PortalException {

		return getService().deleteLicenseKey(licenseKeyId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
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
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static LicenseKey extendLicenseKey(
			String userName, String userUuid, long licenseKeyId,
			String productPurchaseKey, java.util.Date startDate,
			java.util.Date expirationDate)
		throws Exception {

		return getService().extendLicenseKey(
			userName, userUuid, licenseKeyId, productPurchaseKey, startDate,
			expirationDate);
	}

	public static LicenseKey fetchLicenseKey(long licenseKeyId) {
		return getService().fetchLicenseKey(licenseKeyId);
	}

	/**
	 * Returns the license key with the matching UUID and company.
	 *
	 * @param uuid the license key's UUID
	 * @param companyId the primary key of the company
	 * @return the matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public static LicenseKey fetchLicenseKeyByUuidAndCompanyId(
		String uuid, long companyId) {

		return getService().fetchLicenseKeyByUuidAndCompanyId(uuid, companyId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static List<LicenseKey> getAssetReceiptLicenseLicenseKeys(
		String assetReceiptLicenseUuid, boolean complimentary, boolean active) {

		return getService().getAssetReceiptLicenseLicenseKeys(
			assetReceiptLicenseUuid, complimentary, active);
	}

	public static int getAssetReceiptLicenseLicenseKeysCount(
		String assetReceiptLicenseUuid, boolean complimentary, boolean active) {

		return getService().getAssetReceiptLicenseLicenseKeysCount(
			assetReceiptLicenseUuid, complimentary, active);
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
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
	public static List<LicenseKey> getLicenseKeies(int start, int end) {
		return getService().getLicenseKeies(start, end);
	}

	/**
	 * Returns the number of license keies.
	 *
	 * @return the number of license keies
	 */
	public static int getLicenseKeiesCount() {
		return getService().getLicenseKeiesCount();
	}

	/**
	 * Returns the license key with the primary key.
	 *
	 * @param licenseKeyId the primary key of the license key
	 * @return the license key
	 * @throws PortalException if a license key with the primary key could not be found
	 */
	public static LicenseKey getLicenseKey(long licenseKeyId)
		throws PortalException {

		return getService().getLicenseKey(licenseKeyId);
	}

	public static LicenseKey getLicenseKeyByUuid(String uuid)
		throws PortalException {

		return getService().getLicenseKeyByUuid(uuid);
	}

	/**
	 * Returns the license key with the matching UUID and company.
	 *
	 * @param uuid the license key's UUID
	 * @param companyId the primary key of the company
	 * @return the matching license key
	 * @throws PortalException if a matching license key could not be found
	 */
	public static LicenseKey getLicenseKeyByUuidAndCompanyId(
			String uuid, long companyId)
		throws PortalException {

		return getService().getLicenseKeyByUuidAndCompanyId(uuid, companyId);
	}

	public static List<LicenseKey> getLicenseKeys(
		String productId, String serverId) {

		return getService().getLicenseKeys(productId, serverId);
	}

	public static List<LicenseKey> getLicenseKeys(
		String assetReceiptLicenseUuid, String productId, String serverId,
		boolean active, int start, int end, OrderByComparator obc) {

		return getService().getLicenseKeys(
			assetReceiptLicenseUuid, productId, serverId, active, start, end,
			obc);
	}

	public static List<LicenseKey> getLicenseKeysByName(
		String productName, String serverId, boolean active, int start, int end,
		OrderByComparator obc) {

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

	/**
	 * @throws PortalException
	 */
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	public static LicenseKey reindex(long licenseKeyId) throws PortalException {
		return getService().reindex(licenseKeyId);
	}

	public static LicenseKey replaceLicenseKey(
			String userName, String userUuid, long licenseKeyId,
			java.util.Date startDate, java.util.Date expirationDate)
		throws Exception {

		return getService().replaceLicenseKey(
			userName, userUuid, licenseKeyId, startDate, expirationDate);
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

	public static com.liferay.portal.kernel.search.Hits search(
			long companyId, String keywords, int start, int end,
			com.liferay.portal.kernel.search.Sort sort)
		throws PortalException {

		return getService().search(companyId, keywords, start, end, sort);
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
		int start, int end, OrderByComparator obc) {

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

		return getService().searchCount(
			companyId, createUserUuid, createDateGT, createDateLT,
			modifiedUserUuid, modifiedDateGT, modifiedDateLT, accountKey,
			productPurchaseKey, accountName, startDateGT, startDateLT,
			licenseEntryIds, productKeys, productName, productId,
			productVersions, owner, description, hostName, ipAddress,
			macAddress, serverId, key, expirationDateGT, expirationDateLT,
			active, params, andSearch);
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
		java.util.LinkedHashMap<String, Object> params, boolean andSearch) {

		return getService().searchCount(
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
	public static LicenseKey updateLicenseKey(LicenseKey licenseKey) {
		return getService().updateLicenseKey(licenseKey);
	}

	public static LicenseKey updateLicenseKey(
			String userName, String userUuid, long licenseKeyId, boolean active)
		throws Exception {

		return getService().updateLicenseKey(
			userName, userUuid, licenseKeyId, active);
	}

	public static LicenseKey updateLicenseKey(
			String userName, String userUuid, long licenseKeyId,
			String productPurchaseKey, boolean complimentary, boolean active)
		throws Exception {

		return getService().updateLicenseKey(
			userName, userUuid, licenseKeyId, productPurchaseKey, complimentary,
			active);
	}

	public static LicenseKeyLocalService getService() {
		return _service;
	}

	private static volatile LicenseKeyLocalService _service;

}