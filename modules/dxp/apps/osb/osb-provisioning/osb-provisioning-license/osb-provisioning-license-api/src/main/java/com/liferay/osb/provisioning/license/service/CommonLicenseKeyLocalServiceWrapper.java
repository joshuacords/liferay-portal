/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.license.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommonLicenseKeyLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see CommonLicenseKeyLocalService
 * @generated
 */
public class CommonLicenseKeyLocalServiceWrapper
	implements CommonLicenseKeyLocalService,
			   ServiceWrapper<CommonLicenseKeyLocalService> {

	public CommonLicenseKeyLocalServiceWrapper(
		CommonLicenseKeyLocalService commonLicenseKeyLocalService) {

		_commonLicenseKeyLocalService = commonLicenseKeyLocalService;
	}

	/**
	 * Adds the common license key to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommonLicenseKeyLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commonLicenseKey the common license key
	 * @return the common license key that was added
	 */
	@Override
	public com.liferay.osb.provisioning.license.model.CommonLicenseKey
		addCommonLicenseKey(
			com.liferay.osb.provisioning.license.model.CommonLicenseKey
				commonLicenseKey) {

		return _commonLicenseKeyLocalService.addCommonLicenseKey(
			commonLicenseKey);
	}

	@Override
	public com.liferay.osb.provisioning.license.model.CommonLicenseKey
			addCommonLicenseKey(
				long userId, String productGroup, String productEnvironment,
				String productVersion, java.util.Date startDate,
				java.util.Date endDate, String fileName, String fileContent)
		throws Exception {

		return _commonLicenseKeyLocalService.addCommonLicenseKey(
			userId, productGroup, productEnvironment, productVersion, startDate,
			endDate, fileName, fileContent);
	}

	/**
	 * Creates a new common license key with the primary key. Does not add the common license key to the database.
	 *
	 * @param commonLicenseKeyId the primary key for the new common license key
	 * @return the new common license key
	 */
	@Override
	public com.liferay.osb.provisioning.license.model.CommonLicenseKey
		createCommonLicenseKey(long commonLicenseKeyId) {

		return _commonLicenseKeyLocalService.createCommonLicenseKey(
			commonLicenseKeyId);
	}

	/**
	 * Deletes the common license key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommonLicenseKeyLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commonLicenseKey the common license key
	 * @return the common license key that was removed
	 */
	@Override
	public com.liferay.osb.provisioning.license.model.CommonLicenseKey
		deleteCommonLicenseKey(
			com.liferay.osb.provisioning.license.model.CommonLicenseKey
				commonLicenseKey) {

		return _commonLicenseKeyLocalService.deleteCommonLicenseKey(
			commonLicenseKey);
	}

	/**
	 * Deletes the common license key with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommonLicenseKeyLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commonLicenseKeyId the primary key of the common license key
	 * @return the common license key that was removed
	 * @throws PortalException if a common license key with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.provisioning.license.model.CommonLicenseKey
			deleteCommonLicenseKey(long commonLicenseKeyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commonLicenseKeyLocalService.deleteCommonLicenseKey(
			commonLicenseKeyId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commonLicenseKeyLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _commonLicenseKeyLocalService.dynamicQuery();
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

		return _commonLicenseKeyLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.provisioning.license.model.impl.CommonLicenseKeyModelImpl</code>.
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

		return _commonLicenseKeyLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.provisioning.license.model.impl.CommonLicenseKeyModelImpl</code>.
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

		return _commonLicenseKeyLocalService.dynamicQuery(
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

		return _commonLicenseKeyLocalService.dynamicQueryCount(dynamicQuery);
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

		return _commonLicenseKeyLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.provisioning.license.model.CommonLicenseKey
		fetchCommonLicenseKey(long commonLicenseKeyId) {

		return _commonLicenseKeyLocalService.fetchCommonLicenseKey(
			commonLicenseKeyId);
	}

	@Override
	public com.liferay.osb.provisioning.license.model.CommonLicenseKey
		fetchCommonLicenseKey(
			String productGroup, String productEnvironment,
			String productVersion, java.util.Date startDate,
			java.util.Date endDate) {

		return _commonLicenseKeyLocalService.fetchCommonLicenseKey(
			productGroup, productEnvironment, productVersion, startDate,
			endDate);
	}

	/**
	 * Returns the common license key with the matching UUID and company.
	 *
	 * @param uuid the common license key's UUID
	 * @param companyId the primary key of the company
	 * @return the matching common license key, or <code>null</code> if a matching common license key could not be found
	 */
	@Override
	public com.liferay.osb.provisioning.license.model.CommonLicenseKey
		fetchCommonLicenseKeyByUuidAndCompanyId(String uuid, long companyId) {

		return _commonLicenseKeyLocalService.
			fetchCommonLicenseKeyByUuidAndCompanyId(uuid, companyId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _commonLicenseKeyLocalService.getActionableDynamicQuery();
	}

	@Override
	public byte[] getBytes(long commonLicenseKeyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commonLicenseKeyLocalService.getBytes(commonLicenseKeyId);
	}

	/**
	 * Returns a range of all the common license keies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.provisioning.license.model.impl.CommonLicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of common license keies
	 * @param end the upper bound of the range of common license keies (not inclusive)
	 * @return the range of common license keies
	 */
	@Override
	public java.util.List
		<com.liferay.osb.provisioning.license.model.CommonLicenseKey>
			getCommonLicenseKeies(int start, int end) {

		return _commonLicenseKeyLocalService.getCommonLicenseKeies(start, end);
	}

	/**
	 * Returns the number of common license keies.
	 *
	 * @return the number of common license keies
	 */
	@Override
	public int getCommonLicenseKeiesCount() {
		return _commonLicenseKeyLocalService.getCommonLicenseKeiesCount();
	}

	/**
	 * Returns the common license key with the primary key.
	 *
	 * @param commonLicenseKeyId the primary key of the common license key
	 * @return the common license key
	 * @throws PortalException if a common license key with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.provisioning.license.model.CommonLicenseKey
			getCommonLicenseKey(long commonLicenseKeyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commonLicenseKeyLocalService.getCommonLicenseKey(
			commonLicenseKeyId);
	}

	/**
	 * Returns the common license key with the matching UUID and company.
	 *
	 * @param uuid the common license key's UUID
	 * @param companyId the primary key of the company
	 * @return the matching common license key
	 * @throws PortalException if a matching common license key could not be found
	 */
	@Override
	public com.liferay.osb.provisioning.license.model.CommonLicenseKey
			getCommonLicenseKeyByUuidAndCompanyId(String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commonLicenseKeyLocalService.
			getCommonLicenseKeyByUuidAndCompanyId(uuid, companyId);
	}

	@Override
	public java.util.List
		<com.liferay.osb.provisioning.license.model.CommonLicenseKey>
			getCommonLicenseKeys(String productGroup, int start, int end) {

		return _commonLicenseKeyLocalService.getCommonLicenseKeys(
			productGroup, start, end);
	}

	@Override
	public int getCommonLicenseKeysCount(String productGroup) {
		return _commonLicenseKeyLocalService.getCommonLicenseKeysCount(
			productGroup);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _commonLicenseKeyLocalService.
			getIndexableActionableDynamicQuery();
	}

	@Override
	public java.io.InputStream getInputStream(long commonLicenseKeyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commonLicenseKeyLocalService.getInputStream(commonLicenseKeyId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _commonLicenseKeyLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commonLicenseKeyLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the common license key in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommonLicenseKeyLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commonLicenseKey the common license key
	 * @return the common license key that was updated
	 */
	@Override
	public com.liferay.osb.provisioning.license.model.CommonLicenseKey
		updateCommonLicenseKey(
			com.liferay.osb.provisioning.license.model.CommonLicenseKey
				commonLicenseKey) {

		return _commonLicenseKeyLocalService.updateCommonLicenseKey(
			commonLicenseKey);
	}

	@Override
	public CommonLicenseKeyLocalService getWrappedService() {
		return _commonLicenseKeyLocalService;
	}

	@Override
	public void setWrappedService(
		CommonLicenseKeyLocalService commonLicenseKeyLocalService) {

		_commonLicenseKeyLocalService = commonLicenseKeyLocalService;
	}

	private CommonLicenseKeyLocalService _commonLicenseKeyLocalService;

}