/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.util;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.CountryRegion;
import com.liferay.portal.kernel.model.Country;
import com.liferay.portal.kernel.model.Region;
import com.liferay.portal.kernel.service.CountryServiceUtil;

/**
 * @author Yuanyuan Huang
 */
public class CountryRegionUtil {

	public static CountryRegion toCountryRegion(Region region)
		throws Exception {

		return new CountryRegion() {
			{
				active = region.isActive();
				code = region.getRegionCode();

				Country country = CountryServiceUtil.getCountry(
					region.getCountryId());

				countryName = country.getName();

				name = region.getName();
			}
		};
	}

}