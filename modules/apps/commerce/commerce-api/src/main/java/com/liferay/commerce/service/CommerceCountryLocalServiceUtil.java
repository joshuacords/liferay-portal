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

import com.liferay.commerce.model.CommerceCountry;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

/**
 * Provides the local service utility for CommerceCountry. This utility wraps
 * <code>com.liferay.commerce.service.impl.CommerceCountryLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Alessio Antonio Rendina
 * @see CommerceCountryLocalService
 * @generated
 */
public class CommerceCountryLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.service.impl.CommerceCountryLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the commerce country to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceCountryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceCountry the commerce country
	 * @return the commerce country that was added
	 */
	public static CommerceCountry addCommerceCountry(
		CommerceCountry commerceCountry) {

		return getService().addCommerceCountry(commerceCountry);
	}

	public static CommerceCountry addCommerceCountry(
			Map<java.util.Locale, String> nameMap, boolean billingAllowed,
			boolean shippingAllowed, String twoLettersISOCode,
			String threeLettersISOCode, int numericISOCode,
			boolean subjectToVAT, double priority, boolean active,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addCommerceCountry(
			nameMap, billingAllowed, shippingAllowed, twoLettersISOCode,
			threeLettersISOCode, numericISOCode, subjectToVAT, priority, active,
			serviceContext);
	}

	/**
	 * Creates a new commerce country with the primary key. Does not add the commerce country to the database.
	 *
	 * @param commerceCountryId the primary key for the new commerce country
	 * @return the new commerce country
	 */
	public static CommerceCountry createCommerceCountry(
		long commerceCountryId) {

		return getService().createCommerceCountry(commerceCountryId);
	}

	public static void deleteCommerceCountries(long companyId)
		throws PortalException {

		getService().deleteCommerceCountries(companyId);
	}

	/**
	 * Deletes the commerce country from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceCountryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceCountry the commerce country
	 * @return the commerce country that was removed
	 * @throws PortalException
	 */
	public static CommerceCountry deleteCommerceCountry(
			CommerceCountry commerceCountry)
		throws PortalException {

		return getService().deleteCommerceCountry(commerceCountry);
	}

	/**
	 * Deletes the commerce country with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceCountryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceCountryId the primary key of the commerce country
	 * @return the commerce country that was removed
	 * @throws PortalException if a commerce country with the primary key could not be found
	 */
	public static CommerceCountry deleteCommerceCountry(long commerceCountryId)
		throws PortalException {

		return getService().deleteCommerceCountry(commerceCountryId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.model.impl.CommerceCountryModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.model.impl.CommerceCountryModelImpl</code>.
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

	public static CommerceCountry fetchCommerceCountry(long commerceCountryId) {
		return getService().fetchCommerceCountry(commerceCountryId);
	}

	public static CommerceCountry fetchCommerceCountry(
			long companyId, int numericISOCode)
		throws PortalException {

		return getService().fetchCommerceCountry(companyId, numericISOCode);
	}

	public static CommerceCountry fetchCommerceCountry(
		long companyId, String twoLettersISOCode) {

		return getService().fetchCommerceCountry(companyId, twoLettersISOCode);
	}

	/**
	 * Returns the commerce country with the matching UUID and company.
	 *
	 * @param uuid the commerce country's UUID
	 * @param companyId the primary key of the company
	 * @return the matching commerce country, or <code>null</code> if a matching commerce country could not be found
	 */
	public static CommerceCountry fetchCommerceCountryByUuidAndCompanyId(
		String uuid, long companyId) {

		return getService().fetchCommerceCountryByUuidAndCompanyId(
			uuid, companyId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static List<CommerceCountry> getBillingCommerceCountries(
		long companyId, boolean billingAllowed, boolean active) {

		return getService().getBillingCommerceCountries(
			companyId, billingAllowed, active);
	}

	public static List<CommerceCountry> getBillingCommerceCountriesByChannelId(
		long commerceChannelId, int start, int end) {

		return getService().getBillingCommerceCountriesByChannelId(
			commerceChannelId, start, end);
	}

	/**
	 * Returns a range of all the commerce countries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.model.impl.CommerceCountryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce countries
	 * @param end the upper bound of the range of commerce countries (not inclusive)
	 * @return the range of commerce countries
	 */
	public static List<CommerceCountry> getCommerceCountries(
		int start, int end) {

		return getService().getCommerceCountries(start, end);
	}

	public static List<CommerceCountry> getCommerceCountries(
		long companyId, boolean active) {

		return getService().getCommerceCountries(companyId, active);
	}

	public static List<CommerceCountry> getCommerceCountries(
		long companyId, boolean active, int start, int end,
		OrderByComparator<CommerceCountry> orderByComparator) {

		return getService().getCommerceCountries(
			companyId, active, start, end, orderByComparator);
	}

	public static List<CommerceCountry> getCommerceCountries(
		long companyId, int start, int end,
		OrderByComparator<CommerceCountry> orderByComparator) {

		return getService().getCommerceCountries(
			companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of commerce countries.
	 *
	 * @return the number of commerce countries
	 */
	public static int getCommerceCountriesCount() {
		return getService().getCommerceCountriesCount();
	}

	public static int getCommerceCountriesCount(long companyId) {
		return getService().getCommerceCountriesCount(companyId);
	}

	public static int getCommerceCountriesCount(
		long companyId, boolean active) {

		return getService().getCommerceCountriesCount(companyId, active);
	}

	/**
	 * Returns the commerce country with the primary key.
	 *
	 * @param commerceCountryId the primary key of the commerce country
	 * @return the commerce country
	 * @throws PortalException if a commerce country with the primary key could not be found
	 */
	public static CommerceCountry getCommerceCountry(long commerceCountryId)
		throws PortalException {

		return getService().getCommerceCountry(commerceCountryId);
	}

	public static CommerceCountry getCommerceCountry(
			long companyId, String twoLettersISOCode)
		throws PortalException {

		return getService().getCommerceCountry(companyId, twoLettersISOCode);
	}

	/**
	 * Returns the commerce country with the matching UUID and company.
	 *
	 * @param uuid the commerce country's UUID
	 * @param companyId the primary key of the company
	 * @return the matching commerce country
	 * @throws PortalException if a matching commerce country could not be found
	 */
	public static CommerceCountry getCommerceCountryByUuidAndCompanyId(
			String uuid, long companyId)
		throws PortalException {

		return getService().getCommerceCountryByUuidAndCompanyId(
			uuid, companyId);
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

	public static List<CommerceCountry> getShippingCommerceCountries(
		long companyId, boolean shippingAllowed, boolean active) {

		return getService().getShippingCommerceCountries(
			companyId, shippingAllowed, active);
	}

	public static List<CommerceCountry> getShippingCommerceCountriesByChannelId(
		long commerceChannelId, int start, int end) {

		return getService().getShippingCommerceCountriesByChannelId(
			commerceChannelId, start, end);
	}

	public static List<CommerceCountry> getWarehouseCommerceCountries(
		long companyId, boolean all) {

		return getService().getWarehouseCommerceCountries(companyId, all);
	}

	public static void importDefaultCountries(
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws Exception {

		getService().importDefaultCountries(serviceContext);
	}

	public static com.liferay.portal.kernel.search.BaseModelSearchResult
		<CommerceCountry> searchCommerceCountries(
				long companyId, Boolean active, String keywords, int start,
				int end, com.liferay.portal.kernel.search.Sort sort)
			throws PortalException {

		return getService().searchCommerceCountries(
			companyId, active, keywords, start, end, sort);
	}

	/**
	 * @deprecated As of Athanasius (7.3.x)
	 */
	@Deprecated
	public static com.liferay.portal.kernel.search.BaseModelSearchResult
		<CommerceCountry> searchCommerceCountries(
				com.liferay.portal.kernel.search.SearchContext searchContext)
			throws PortalException {

		return getService().searchCommerceCountries(searchContext);
	}

	public static CommerceCountry setActive(
			long commerceCountryId, boolean active)
		throws PortalException {

		return getService().setActive(commerceCountryId, active);
	}

	/**
	 * Updates the commerce country in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceCountryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceCountry the commerce country
	 * @return the commerce country that was updated
	 */
	public static CommerceCountry updateCommerceCountry(
		CommerceCountry commerceCountry) {

		return getService().updateCommerceCountry(commerceCountry);
	}

	public static CommerceCountry updateCommerceCountry(
			long commerceCountryId, Map<java.util.Locale, String> nameMap,
			boolean billingAllowed, boolean shippingAllowed,
			String twoLettersISOCode, String threeLettersISOCode,
			int numericISOCode, boolean subjectToVAT, double priority,
			boolean active,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().updateCommerceCountry(
			commerceCountryId, nameMap, billingAllowed, shippingAllowed,
			twoLettersISOCode, threeLettersISOCode, numericISOCode,
			subjectToVAT, priority, active, serviceContext);
	}

	public static CommerceCountry updateCommerceCountryChannelFilter(
			long commerceCountryId, boolean enable)
		throws PortalException {

		return getService().updateCommerceCountryChannelFilter(
			commerceCountryId, enable);
	}

	public static CommerceCountryLocalService getService() {
		return _service;
	}

	public static void setService(CommerceCountryLocalService service) {
		_service = service;
	}

	private static volatile CommerceCountryLocalService _service;

}