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
 * Provides a wrapper for {@link LicenseEntryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see LicenseEntryLocalService
 * @generated
 */
public class LicenseEntryLocalServiceWrapper
	implements LicenseEntryLocalService,
			   ServiceWrapper<LicenseEntryLocalService> {

	public LicenseEntryLocalServiceWrapper(
		LicenseEntryLocalService licenseEntryLocalService) {

		_licenseEntryLocalService = licenseEntryLocalService;
	}

	/**
	 * Adds the license entry to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LicenseEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param licenseEntry the license entry
	 * @return the license entry that was added
	 */
	@Override
	public com.liferay.osb.provisioning.license.model.LicenseEntry
		addLicenseEntry(
			com.liferay.osb.provisioning.license.model.LicenseEntry
				licenseEntry) {

		return _licenseEntryLocalService.addLicenseEntry(licenseEntry);
	}

	@Override
	public com.liferay.osb.provisioning.license.model.LicenseEntry
			addLicenseEntry(
				long userId, String productKey, String name, String type,
				String versionMin, String versionMax)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _licenseEntryLocalService.addLicenseEntry(
			userId, productKey, name, type, versionMin, versionMax);
	}

	/**
	 * Creates a new license entry with the primary key. Does not add the license entry to the database.
	 *
	 * @param licenseEntryId the primary key for the new license entry
	 * @return the new license entry
	 */
	@Override
	public com.liferay.osb.provisioning.license.model.LicenseEntry
		createLicenseEntry(long licenseEntryId) {

		return _licenseEntryLocalService.createLicenseEntry(licenseEntryId);
	}

	/**
	 * Deletes the license entry from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LicenseEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param licenseEntry the license entry
	 * @return the license entry that was removed
	 */
	@Override
	public com.liferay.osb.provisioning.license.model.LicenseEntry
		deleteLicenseEntry(
			com.liferay.osb.provisioning.license.model.LicenseEntry
				licenseEntry) {

		return _licenseEntryLocalService.deleteLicenseEntry(licenseEntry);
	}

	/**
	 * Deletes the license entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LicenseEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param licenseEntryId the primary key of the license entry
	 * @return the license entry that was removed
	 * @throws PortalException if a license entry with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.provisioning.license.model.LicenseEntry
			deleteLicenseEntry(long licenseEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _licenseEntryLocalService.deleteLicenseEntry(licenseEntryId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _licenseEntryLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _licenseEntryLocalService.dynamicQuery();
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

		return _licenseEntryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.provisioning.license.model.impl.LicenseEntryModelImpl</code>.
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

		return _licenseEntryLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.provisioning.license.model.impl.LicenseEntryModelImpl</code>.
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

		return _licenseEntryLocalService.dynamicQuery(
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

		return _licenseEntryLocalService.dynamicQueryCount(dynamicQuery);
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

		return _licenseEntryLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.provisioning.license.model.LicenseEntry
		fetchLicenseEntry(long licenseEntryId) {

		return _licenseEntryLocalService.fetchLicenseEntry(licenseEntryId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _licenseEntryLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _licenseEntryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns a range of all the license entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.provisioning.license.model.impl.LicenseEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @return the range of license entries
	 */
	@Override
	public java.util.List
		<com.liferay.osb.provisioning.license.model.LicenseEntry>
			getLicenseEntries(int start, int end) {

		return _licenseEntryLocalService.getLicenseEntries(start, end);
	}

	@Override
	public java.util.List
		<com.liferay.osb.provisioning.license.model.LicenseEntry>
			getLicenseEntries(String productKey) {

		return _licenseEntryLocalService.getLicenseEntries(productKey);
	}

	@Override
	public java.util.List
		<com.liferay.osb.provisioning.license.model.LicenseEntry>
			getLicenseEntriesByNameVersion(
				String name, String version, boolean supportedVersions) {

		return _licenseEntryLocalService.getLicenseEntriesByNameVersion(
			name, version, supportedVersions);
	}

	@Override
	public java.util.List
		<com.liferay.osb.provisioning.license.model.LicenseEntry>
			getLicenseEntriesByType(String type) {

		return _licenseEntryLocalService.getLicenseEntriesByType(type);
	}

	@Override
	public java.util.List
		<com.liferay.osb.provisioning.license.model.LicenseEntry>
				getLicenseEntriesByVersion(
					String productKey, String version,
					boolean supportedVersions)
			throws Exception {

		return _licenseEntryLocalService.getLicenseEntriesByVersion(
			productKey, version, supportedVersions);
	}

	/**
	 * Returns the number of license entries.
	 *
	 * @return the number of license entries
	 */
	@Override
	public int getLicenseEntriesCount() {
		return _licenseEntryLocalService.getLicenseEntriesCount();
	}

	/**
	 * Returns the license entry with the primary key.
	 *
	 * @param licenseEntryId the primary key of the license entry
	 * @return the license entry
	 * @throws PortalException if a license entry with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.provisioning.license.model.LicenseEntry
			getLicenseEntry(long licenseEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _licenseEntryLocalService.getLicenseEntry(licenseEntryId);
	}

	@Override
	public com.liferay.osb.provisioning.license.model.LicenseEntry
			getLicenseEntry(String productKey, String type)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _licenseEntryLocalService.getLicenseEntry(productKey, type);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _licenseEntryLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _licenseEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the license entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LicenseEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param licenseEntry the license entry
	 * @return the license entry that was updated
	 */
	@Override
	public com.liferay.osb.provisioning.license.model.LicenseEntry
		updateLicenseEntry(
			com.liferay.osb.provisioning.license.model.LicenseEntry
				licenseEntry) {

		return _licenseEntryLocalService.updateLicenseEntry(licenseEntry);
	}

	@Override
	public com.liferay.osb.provisioning.license.model.LicenseEntry
			updateLicenseEntry(
				long licenseEntryId, String productKey, String name,
				String type, String versionMin, String versionMax)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _licenseEntryLocalService.updateLicenseEntry(
			licenseEntryId, productKey, name, type, versionMin, versionMax);
	}

	@Override
	public LicenseEntryLocalService getWrappedService() {
		return _licenseEntryLocalService;
	}

	@Override
	public void setWrappedService(
		LicenseEntryLocalService licenseEntryLocalService) {

		_licenseEntryLocalService = licenseEntryLocalService;
	}

	private LicenseEntryLocalService _licenseEntryLocalService;

}