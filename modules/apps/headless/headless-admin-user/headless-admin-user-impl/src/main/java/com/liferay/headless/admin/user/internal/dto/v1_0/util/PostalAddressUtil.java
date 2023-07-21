/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.admin.user.internal.dto.v1_0.util;

import com.liferay.headless.admin.user.dto.v1_0.PostalAddress;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.model.Country;
import com.liferay.portal.kernel.model.ListType;
import com.liferay.portal.kernel.model.Region;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Javier Gamarra
 */
public class PostalAddressUtil {

	public static PostalAddress toPostalAddress(
		boolean acceptAllLanguages, Address address, long companyId,
		Locale locale) {

		ListType listType = address.getType();

		return new PostalAddress() {
			{
				addressLocality = address.getCity();
				addressType = listType.getName();
				id = address.getAddressId();
				postalCode = address.getZip();
				primary = address.isPrimary();
				streetAddressLine1 = address.getStreet1();
				streetAddressLine2 = address.getStreet2();
				streetAddressLine3 = address.getStreet3();

				setAddressCountry(
					() -> {
						if (address.getCountryId() <= 0) {
							return null;
						}

						Country country = address.getCountry();

						return country.getName(locale);
					});
				setAddressCountry_i18n(
					() -> {
						if (!acceptAllLanguages) {
							return null;
						}

						Set<Locale> locales =
							LanguageUtil.getCompanyAvailableLocales(companyId);

						Stream<Locale> localesStream = locales.stream();

						Country country = address.getCountry();

						return localesStream.collect(
							Collectors.toMap(
								LocaleUtil::toBCP47LanguageId,
								country::getName));
					});
				setAddressRegion(
					() -> {
						if (address.getRegionId() <= 0) {
							return null;
						}

						Region region = address.getRegion();

						return region.getName();
					});
			}
		};
	}

}