/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.admin.user.internal.dto.v1_0.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Country;
import com.liferay.portal.kernel.service.CountryServiceUtil;

import java.util.Optional;

/**
 * @author Drew Brokke
 */
public class ServiceBuilderCountryUtil {

	public static Country toServiceBuilderCountry(String addressCountry) {
		try {
			Country country = CountryServiceUtil.fetchCountryByA2(
				addressCountry);

			if (country != null) {
				return country;
			}

			country = CountryServiceUtil.fetchCountryByA3(addressCountry);

			if (country != null) {
				return country;
			}

			return CountryServiceUtil.getCountryByName(addressCountry);
		}
		catch (Exception exception) {
			if (_log.isWarnEnabled()) {
				_log.warn(exception, exception);
			}
		}

		return null;
	}

	public static long toServiceBuilderCountryId(String addressCountry) {
		return Optional.ofNullable(
			addressCountry
		).map(
			ServiceBuilderCountryUtil::toServiceBuilderCountry
		).map(
			Country::getCountryId
		).orElse(
			(long)0
		);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ServiceBuilderCountryUtil.class);

}