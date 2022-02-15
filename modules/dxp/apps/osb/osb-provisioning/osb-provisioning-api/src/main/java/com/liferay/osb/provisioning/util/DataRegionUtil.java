/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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