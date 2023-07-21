/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.service;

/**
 * Provides a wrapper for {@link CountryService}.
 *
 * @author Brian Wing Shun Chan
 * @see CountryService
 * @generated
 */
public class CountryServiceWrapper
	implements CountryService, ServiceWrapper<CountryService> {

	public CountryServiceWrapper(CountryService countryService) {
		_countryService = countryService;
	}

	@Override
	public com.liferay.portal.kernel.model.Country addCountry(
			java.lang.String name, java.lang.String a2, java.lang.String a3,
			java.lang.String number, java.lang.String idd, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _countryService.addCountry(name, a2, a3, number, idd, active);
	}

	@Override
	public com.liferay.portal.kernel.model.Country fetchCountry(
		long countryId) {

		return _countryService.fetchCountry(countryId);
	}

	@Override
	public com.liferay.portal.kernel.model.Country fetchCountryByA2(
		java.lang.String a2) {

		return _countryService.fetchCountryByA2(a2);
	}

	@Override
	public com.liferay.portal.kernel.model.Country fetchCountryByA3(
		java.lang.String a3) {

		return _countryService.fetchCountryByA3(a3);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Country>
		getCountries() {

		return _countryService.getCountries();
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Country> getCountries(
		boolean active) {

		return _countryService.getCountries(active);
	}

	@Override
	public com.liferay.portal.kernel.model.Country getCountry(long countryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _countryService.getCountry(countryId);
	}

	@Override
	public com.liferay.portal.kernel.model.Country getCountryByA2(
			java.lang.String a2)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _countryService.getCountryByA2(a2);
	}

	@Override
	public com.liferay.portal.kernel.model.Country getCountryByA3(
			java.lang.String a3)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _countryService.getCountryByA3(a3);
	}

	@Override
	public com.liferay.portal.kernel.model.Country getCountryByName(
			java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _countryService.getCountryByName(name);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _countryService.getOSGiServiceIdentifier();
	}

	@Override
	public CountryService getWrappedService() {
		return _countryService;
	}

	@Override
	public void setWrappedService(CountryService countryService) {
		_countryService = countryService;
	}

	private CountryService _countryService;

}