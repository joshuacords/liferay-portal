/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.model.impl;

import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.model.CommerceCountry;
import com.liferay.commerce.model.CommerceRegion;
import com.liferay.commerce.service.CommerceCountryLocalServiceUtil;
import com.liferay.commerce.service.CommerceRegionLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.Objects;

/**
 * @author Andrea Di Giorgi
 */
public class CommerceAddressImpl extends CommerceAddressBaseImpl {

	public CommerceAddressImpl() {
	}

	@Override
	public CommerceCountry fetchCommerceCountry() {
		return CommerceCountryLocalServiceUtil.fetchCommerceCountry(
			getCommerceCountryId());
	}

	@Override
	public CommerceCountry getCommerceCountry() throws PortalException {
		return CommerceCountryLocalServiceUtil.getCommerceCountry(
			getCommerceCountryId());
	}

	@Override
	public CommerceRegion getCommerceRegion() throws PortalException {
		long commerceRegionId = getCommerceRegionId();

		if (commerceRegionId > 0) {
			return CommerceRegionLocalServiceUtil.getCommerceRegion(
				commerceRegionId);
		}

		return null;
	}

	@Override
	public boolean isGeolocated() {
		if ((getLatitude() == 0) && (getLongitude() == 0)) {
			return false;
		}

		return true;
	}

	@Override
	public boolean isSameAddress(CommerceAddress commerceAddress) {
		if (Objects.equals(getName(), commerceAddress.getName()) &&
			Objects.equals(getStreet1(), commerceAddress.getStreet1()) &&
			Objects.equals(getStreet2(), commerceAddress.getStreet2()) &&
			Objects.equals(getStreet3(), commerceAddress.getStreet3()) &&
			Objects.equals(getCity(), commerceAddress.getCity()) &&
			Objects.equals(getZip(), commerceAddress.getZip()) &&
			(getCommerceRegionId() == commerceAddress.getCommerceRegionId()) &&
			(getCommerceCountryId() ==
				commerceAddress.getCommerceCountryId()) &&
			Objects.equals(
				getPhoneNumber(), commerceAddress.getPhoneNumber())) {

			return true;
		}

		return false;
	}

}