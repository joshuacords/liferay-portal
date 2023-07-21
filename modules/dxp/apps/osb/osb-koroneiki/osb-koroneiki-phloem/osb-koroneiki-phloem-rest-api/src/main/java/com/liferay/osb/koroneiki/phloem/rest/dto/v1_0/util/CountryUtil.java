/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.util;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Country;

/**
 * @author Yuanyuan Huang
 */
public class CountryUtil {

	public static Country toCountry(
			com.liferay.portal.kernel.model.Country country)
		throws Exception {

		return new Country() {
			{
				a2 = country.getA2();
				a3 = country.getA3();
				active = country.isActive();
				idd = country.getIdd();
				name = country.getName();
				zipRequired = country.isZipRequired();
			}
		};
	}

}