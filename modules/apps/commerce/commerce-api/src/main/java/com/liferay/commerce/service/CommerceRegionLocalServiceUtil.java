/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.commerce.service;

import com.liferay.commerce.model.CommerceRegion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for CommerceRegion. This utility wraps
 * <code>com.liferay.commerce.service.impl.CommerceRegionLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Alessio Antonio Rendina
 * @see CommerceRegionLocalService
 * @generated
 */
public class CommerceRegionLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.service.impl.CommerceRegionLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the commerce region to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceRegionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceRegion the commerce region
	 * @return the commerce region that was added
	 */
	public static CommerceRegion addCommerceRegion(
		CommerceRegion commerceRegion) {

		return getService().addCommerceRegion(commerceRegion);
	}

	public static CommerceRegion addCommerceRegion(
			long commerceCountryId, String name, String code, double priority,
			boolean active,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addCommerceRegion(
			commerceCountryId, name, code, priority, active, serviceContext);
	}

	/**
	 * Creates a new commerce region with the primary key. Does not add the commerce region to the database.
	 *
	 * @param commerceRegionId the primary key for the new commerce region
	 * @return the new commerce region
	 */
	public static CommerceRegion createCommerceRegion(long commerceRegionId) {
		return getService().createCommerceRegion(commerceRegionId);
	}

	/**
	 * Deletes the commerce region from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceRegionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceRegion the commerce region
	 * @return the commerce region that was removed
	 * @throws PortalException
	 */
	public static CommerceRegion deleteCommerceRegion(
			CommerceRegion commerceRegion)
		throws PortalException {

		return getService().deleteCommerceRegion(commerceRegion);
	}

	/**
	 * Deletes the commerce region with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceRegionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceRegionId the primary key of the commerce region
	 * @return the commerce region that was removed
	 * @throws PortalException if a commerce region with the primary key could not be found
	 */
	public static CommerceRegion deleteCommerceRegion(long commerceRegionId)
		throws PortalException {

		return getService().deleteCommerceRegion(commerceRegionId);
	}

	public static void deleteCommerceRegions(long commerceCountryId)
		throws PortalException {

		getService().deleteCommerceRegions(commerceCountryId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.model.impl.CommerceRegionModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.model.impl.CommerceRegionModelImpl</code>.
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

	public static CommerceRegion fetchCommerceRegion(long commerceRegionId) {
		return getService().fetchCommerceRegion(commerceRegionId);
	}

	/**
	 * Returns the commerce region with the matching UUID and company.
	 *
	 * @param uuid the commerce region's UUID
	 * @param companyId the primary key of the company
	 * @return the matching commerce region, or <code>null</code> if a matching commerce region could not be found
	 */
	public static CommerceRegion fetchCommerceRegionByUuidAndCompanyId(
		String uuid, long companyId) {

		return getService().fetchCommerceRegionByUuidAndCompanyId(
			uuid, companyId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the commerce region with the primary key.
	 *
	 * @param commerceRegionId the primary key of the commerce region
	 * @return the commerce region
	 * @throws PortalException if a commerce region with the primary key could not be found
	 */
	public static CommerceRegion getCommerceRegion(long commerceRegionId)
		throws PortalException {

		return getService().getCommerceRegion(commerceRegionId);
	}

	public static CommerceRegion getCommerceRegion(
			long commerceCountryId, String code)
		throws PortalException {

		return getService().getCommerceRegion(commerceCountryId, code);
	}

	/**
	 * Returns the commerce region with the matching UUID and company.
	 *
	 * @param uuid the commerce region's UUID
	 * @param companyId the primary key of the company
	 * @return the matching commerce region
	 * @throws PortalException if a matching commerce region could not be found
	 */
	public static CommerceRegion getCommerceRegionByUuidAndCompanyId(
			String uuid, long companyId)
		throws PortalException {

		return getService().getCommerceRegionByUuidAndCompanyId(
			uuid, companyId);
	}

	/**
	 * Returns a range of all the commerce regions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.model.impl.CommerceRegionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce regions
	 * @param end the upper bound of the range of commerce regions (not inclusive)
	 * @return the range of commerce regions
	 */
	public static List<CommerceRegion> getCommerceRegions(int start, int end) {
		return getService().getCommerceRegions(start, end);
	}

	public static List<CommerceRegion> getCommerceRegions(
		long commerceCountryId, boolean active) {

		return getService().getCommerceRegions(commerceCountryId, active);
	}

	public static List<CommerceRegion> getCommerceRegions(
		long commerceCountryId, boolean active, int start, int end,
		OrderByComparator<CommerceRegion> orderByComparator) {

		return getService().getCommerceRegions(
			commerceCountryId, active, start, end, orderByComparator);
	}

	public static List<CommerceRegion> getCommerceRegions(
		long commerceCountryId, int start, int end,
		OrderByComparator<CommerceRegion> orderByComparator) {

		return getService().getCommerceRegions(
			commerceCountryId, start, end, orderByComparator);
	}

	public static List<CommerceRegion> getCommerceRegions(
			long companyId, String countryTwoLettersISOCode, boolean active)
		throws PortalException {

		return getService().getCommerceRegions(
			companyId, countryTwoLettersISOCode, active);
	}

	/**
	 * Returns the number of commerce regions.
	 *
	 * @return the number of commerce regions
	 */
	public static int getCommerceRegionsCount() {
		return getService().getCommerceRegionsCount();
	}

	public static int getCommerceRegionsCount(long commerceCountryId) {
		return getService().getCommerceRegionsCount(commerceCountryId);
	}

	public static int getCommerceRegionsCount(
		long commerceCountryId, boolean active) {

		return getService().getCommerceRegionsCount(commerceCountryId, active);
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

	public static CommerceRegion setActive(
			long commerceRegionId, boolean active)
		throws PortalException {

		return getService().setActive(commerceRegionId, active);
	}

	/**
	 * Updates the commerce region in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceRegionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceRegion the commerce region
	 * @return the commerce region that was updated
	 */
	public static CommerceRegion updateCommerceRegion(
		CommerceRegion commerceRegion) {

		return getService().updateCommerceRegion(commerceRegion);
	}

	public static CommerceRegion updateCommerceRegion(
			long commerceRegionId, String name, String code, double priority,
			boolean active,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().updateCommerceRegion(
			commerceRegionId, name, code, priority, active, serviceContext);
	}

	public static CommerceRegionLocalService getService() {
		return _service;
	}

	public static void setService(CommerceRegionLocalService service) {
		_service = service;
	}

	private static volatile CommerceRegionLocalService _service;

}