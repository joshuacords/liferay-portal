/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.license.service;

import com.liferay.osb.provisioning.license.model.CommonLicenseKey;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.InputStream;
import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for CommonLicenseKey. This utility wraps
 * <code>com.liferay.osb.provisioning.license.service.impl.CommonLicenseKeyLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see CommonLicenseKeyLocalService
 * @generated
 */
public class CommonLicenseKeyLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.provisioning.license.service.impl.CommonLicenseKeyLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

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
	public static CommonLicenseKey addCommonLicenseKey(
		CommonLicenseKey commonLicenseKey) {

		return getService().addCommonLicenseKey(commonLicenseKey);
	}

	public static CommonLicenseKey addCommonLicenseKey(
			long userId, String productGroup, String productEnvironment,
			String productVersion, java.util.Date startDate,
			java.util.Date endDate, String fileName, String fileContent)
		throws Exception {

		return getService().addCommonLicenseKey(
			userId, productGroup, productEnvironment, productVersion, startDate,
			endDate, fileName, fileContent);
	}

	/**
	 * Creates a new common license key with the primary key. Does not add the common license key to the database.
	 *
	 * @param commonLicenseKeyId the primary key for the new common license key
	 * @return the new common license key
	 */
	public static CommonLicenseKey createCommonLicenseKey(
		long commonLicenseKeyId) {

		return getService().createCommonLicenseKey(commonLicenseKeyId);
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
	public static CommonLicenseKey deleteCommonLicenseKey(
		CommonLicenseKey commonLicenseKey) {

		return getService().deleteCommonLicenseKey(commonLicenseKey);
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
	public static CommonLicenseKey deleteCommonLicenseKey(
			long commonLicenseKeyId)
		throws PortalException {

		return getService().deleteCommonLicenseKey(commonLicenseKeyId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.provisioning.license.model.impl.CommonLicenseKeyModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.provisioning.license.model.impl.CommonLicenseKeyModelImpl</code>.
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

	public static CommonLicenseKey fetchCommonLicenseKey(
		long commonLicenseKeyId) {

		return getService().fetchCommonLicenseKey(commonLicenseKeyId);
	}

	public static CommonLicenseKey fetchCommonLicenseKey(
		String productGroup, String productEnvironment, String productVersion,
		java.util.Date startDate, java.util.Date endDate) {

		return getService().fetchCommonLicenseKey(
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
	public static CommonLicenseKey fetchCommonLicenseKeyByUuidAndCompanyId(
		String uuid, long companyId) {

		return getService().fetchCommonLicenseKeyByUuidAndCompanyId(
			uuid, companyId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static byte[] getBytes(long commonLicenseKeyId)
		throws PortalException {

		return getService().getBytes(commonLicenseKeyId);
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
	public static List<CommonLicenseKey> getCommonLicenseKeies(
		int start, int end) {

		return getService().getCommonLicenseKeies(start, end);
	}

	/**
	 * Returns the number of common license keies.
	 *
	 * @return the number of common license keies
	 */
	public static int getCommonLicenseKeiesCount() {
		return getService().getCommonLicenseKeiesCount();
	}

	/**
	 * Returns the common license key with the primary key.
	 *
	 * @param commonLicenseKeyId the primary key of the common license key
	 * @return the common license key
	 * @throws PortalException if a common license key with the primary key could not be found
	 */
	public static CommonLicenseKey getCommonLicenseKey(long commonLicenseKeyId)
		throws PortalException {

		return getService().getCommonLicenseKey(commonLicenseKeyId);
	}

	/**
	 * Returns the common license key with the matching UUID and company.
	 *
	 * @param uuid the common license key's UUID
	 * @param companyId the primary key of the company
	 * @return the matching common license key
	 * @throws PortalException if a matching common license key could not be found
	 */
	public static CommonLicenseKey getCommonLicenseKeyByUuidAndCompanyId(
			String uuid, long companyId)
		throws PortalException {

		return getService().getCommonLicenseKeyByUuidAndCompanyId(
			uuid, companyId);
	}

	public static List<CommonLicenseKey> getCommonLicenseKeys(
		String productGroup, int start, int end) {

		return getService().getCommonLicenseKeys(productGroup, start, end);
	}

	public static int getCommonLicenseKeysCount(String productGroup) {
		return getService().getCommonLicenseKeysCount(productGroup);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	public static InputStream getInputStream(long commonLicenseKeyId)
		throws PortalException {

		return getService().getInputStream(commonLicenseKeyId);
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
	public static CommonLicenseKey updateCommonLicenseKey(
		CommonLicenseKey commonLicenseKey) {

		return getService().updateCommonLicenseKey(commonLicenseKey);
	}

	public static CommonLicenseKeyLocalService getService() {
		return _service;
	}

	public static void setService(CommonLicenseKeyLocalService service) {
		_service = service;
	}

	private static volatile CommonLicenseKeyLocalService _service;

}