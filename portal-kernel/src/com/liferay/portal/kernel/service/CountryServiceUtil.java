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

package com.liferay.portal.kernel.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Country;

import java.util.List;

/**
 * Provides the remote service utility for Country. This utility wraps
 * <code>com.liferay.portal.service.impl.CountryServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see CountryService
 * @generated
 */
public class CountryServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.portal.service.impl.CountryServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static Country addCountry(
			String name, String a2, String a3, String number, String idd,
			boolean active)
		throws PortalException {

		return getService().addCountry(name, a2, a3, number, idd, active);
	}

	public static Country fetchCountry(long countryId) {
		return getService().fetchCountry(countryId);
	}

	public static Country fetchCountryByA2(String a2) {
		return getService().fetchCountryByA2(a2);
	}

	public static Country fetchCountryByA3(String a3) {
		return getService().fetchCountryByA3(a3);
	}

	public static List<Country> getCountries() {
		return getService().getCountries();
	}

	public static List<Country> getCountries(boolean active) {
		return getService().getCountries(active);
	}

	public static Country getCountry(long countryId) throws PortalException {
		return getService().getCountry(countryId);
	}

	public static Country getCountryByA2(String a2) throws PortalException {
		return getService().getCountryByA2(a2);
	}

	public static Country getCountryByA3(String a3) throws PortalException {
		return getService().getCountryByA3(a3);
	}

	public static Country getCountryByName(String name) throws PortalException {
		return getService().getCountryByName(name);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static CountryService getService() {
		return _service;
	}

	public static void setService(CountryService service) {
		_service = service;
	}

	private static volatile CountryService _service;

}