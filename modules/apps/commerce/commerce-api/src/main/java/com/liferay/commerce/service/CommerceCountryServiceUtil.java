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
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;
import java.util.Map;

/**
 * Provides the remote service utility for CommerceCountry. This utility wraps
 * <code>com.liferay.commerce.service.impl.CommerceCountryServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Alessio Antonio Rendina
 * @see CommerceCountryService
 * @generated
 */
public class CommerceCountryServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.service.impl.CommerceCountryServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
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

	public static void deleteCommerceCountry(long commerceCountryId)
		throws PortalException {

		getService().deleteCommerceCountry(commerceCountryId);
	}

	public static CommerceCountry fetchCommerceCountry(
			long companyId, String twoLettersISOCode)
		throws com.liferay.portal.kernel.security.auth.PrincipalException {

		return getService().fetchCommerceCountry(companyId, twoLettersISOCode);
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

	public static List<CommerceCountry> getCommerceCountries(
		long companyId, boolean active) {

		return getService().getCommerceCountries(companyId, active);
	}

	public static List<CommerceCountry> getCommerceCountries(
			long companyId, boolean active, int start, int end,
			OrderByComparator<CommerceCountry> orderByComparator)
		throws PortalException {

		return getService().getCommerceCountries(
			companyId, active, start, end, orderByComparator);
	}

	public static List<CommerceCountry> getCommerceCountries(
			long companyId, int start, int end,
			OrderByComparator<CommerceCountry> orderByComparator)
		throws PortalException {

		return getService().getCommerceCountries(
			companyId, start, end, orderByComparator);
	}

	public static int getCommerceCountriesCount(long companyId)
		throws PortalException {

		return getService().getCommerceCountriesCount(companyId);
	}

	public static int getCommerceCountriesCount(long companyId, boolean active)
		throws PortalException {

		return getService().getCommerceCountriesCount(companyId, active);
	}

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
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
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
			long companyId, boolean all)
		throws PortalException {

		return getService().getWarehouseCommerceCountries(companyId, all);
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

	public static CommerceCountryService getService() {
		return _service;
	}

	public static void setService(CommerceCountryService service) {
		_service = service;
	}

	private static volatile CommerceCountryService _service;

}