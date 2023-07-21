/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.text.localizer.taglib.internal.address.util;

import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.model.Region;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.util.Validator;

import java.util.Optional;

/**
 * @author Pei-Jung Lan
 * @author Drew Brokke
 */
public class AddressUtil {

	public static Optional<String> getCountryNameOptional(Address address) {
		return Optional.ofNullable(
			address
		).map(
			Address::getCountry
		).filter(
			country -> {
				if (country.getCountryId() > 0) {
					return true;
				}

				return false;
			}
		).map(
			country -> Optional.ofNullable(
				ServiceContextThreadLocal.getServiceContext()
			).map(
				serviceContext -> country.getName(serviceContext.getLocale())
			).orElseGet(
				country::getName
			)
		).filter(
			Validator::isNotNull
		);
	}

	public static Optional<String> getRegionNameOptional(Address address) {
		return Optional.ofNullable(
			address
		).map(
			Address::getRegion
		).filter(
			region -> {
				if (region.getRegionId() > 0) {
					return true;
				}

				return false;
			}
		).map(
			Region::getName
		).filter(
			Validator::isNotNull
		);
	}

}