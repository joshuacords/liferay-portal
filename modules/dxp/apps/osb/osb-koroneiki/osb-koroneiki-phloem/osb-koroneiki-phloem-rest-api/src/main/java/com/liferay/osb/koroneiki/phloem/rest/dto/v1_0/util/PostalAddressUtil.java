/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.util;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.PostalAddress;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.model.Country;
import com.liferay.portal.kernel.model.ListType;
import com.liferay.portal.kernel.model.Region;
import com.liferay.portal.kernel.util.LocaleUtil;

/**
 * @author Kyle Bischof
 */
public class PostalAddressUtil {

	public static PostalAddress toPostalAddress(Address address) {
		ListType listType = address.getType();

		return new PostalAddress() {
			{
				addressLocality = address.getCity();
				addressType = listType.getName();
				id = address.getAddressId();
				mailing = address.isMailing();
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

						return country.getName(LocaleUtil.US);
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