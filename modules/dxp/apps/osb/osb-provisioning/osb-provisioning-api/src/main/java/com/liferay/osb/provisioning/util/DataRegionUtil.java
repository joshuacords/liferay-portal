/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.util;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Amos Fong
 */
public class DataRegionUtil {

	public static Account.DataRegion getDataRegion(
		Account.Region region, String country) {

		if (region == Account.Region.AUSTRALIA) {
			return Account.DataRegion.JAPAN;
		}
		else if (region == Account.Region.BRAZIL) {
			if (Validator.isNull(country) || country.equals("Brazil")) {
				return Account.DataRegion.BRAZIL;
			}

			return Account.DataRegion.UNITED_STATES;
		}
		else if (region == Account.Region.CHINA) {
			return Account.DataRegion.UNITED_STATES;
		}
		else if (region == Account.Region.GLOBAL) {
			return Account.DataRegion.HUNGARY;
		}
		else if (region == Account.Region.HUNGARY) {
			return Account.DataRegion.HUNGARY;
		}
		else if (region == Account.Region.INDIA) {
			return Account.DataRegion.UNITED_STATES;
		}
		else if (region == Account.Region.JAPAN) {
			return Account.DataRegion.JAPAN;
		}
		else if (region == Account.Region.SPAIN) {
			return Account.DataRegion.HUNGARY;
		}
		else if (region == Account.Region.UNITED_STATES) {
			return Account.DataRegion.UNITED_STATES;
		}

		return null;
	}

}