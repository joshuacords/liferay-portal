/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.service;

/**
 * Provides a wrapper for {@link AddressService}.
 *
 * @author Brian Wing Shun Chan
 * @see AddressService
 * @generated
 */
public class AddressServiceWrapper
	implements AddressService, ServiceWrapper<AddressService> {

	public AddressServiceWrapper(AddressService addressService) {
		_addressService = addressService;
	}

	@Override
	public com.liferay.portal.kernel.model.Address addAddress(
			java.lang.String className, long classPK, java.lang.String street1,
			java.lang.String street2, java.lang.String street3,
			java.lang.String city, java.lang.String zip, long regionId,
			long countryId, long typeId, boolean mailing, boolean primary,
			ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _addressService.addAddress(
			className, classPK, street1, street2, street3, city, zip, regionId,
			countryId, typeId, mailing, primary, serviceContext);
	}

	@Override
	public void deleteAddress(long addressId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_addressService.deleteAddress(addressId);
	}

	@Override
	public com.liferay.portal.kernel.model.Address getAddress(long addressId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _addressService.getAddress(addressId);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Address> getAddresses(
			java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _addressService.getAddresses(className, classPK);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _addressService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.Address updateAddress(
			long addressId, java.lang.String street1, java.lang.String street2,
			java.lang.String street3, java.lang.String city,
			java.lang.String zip, long regionId, long countryId, long typeId,
			boolean mailing, boolean primary)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _addressService.updateAddress(
			addressId, street1, street2, street3, city, zip, regionId,
			countryId, typeId, mailing, primary);
	}

	@Override
	public AddressService getWrappedService() {
		return _addressService;
	}

	@Override
	public void setWrappedService(AddressService addressService) {
		_addressService = addressService;
	}

	private AddressService _addressService;

}