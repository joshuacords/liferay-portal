/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.license.helper.constants;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Kyle Bischof
 */
public class LicenseSizing {

	public static final int FOUR = 4;

	public static final int ONE = 1;

	public static final String SIZING_FOUR = "Sizing 4";

	public static final String SIZING_ONE = "Sizing 1";

	public static final String SIZING_THREE = "Sizing 3";

	public static final String SIZING_TWO = "Sizing 2";

	public static final int THREE = 3;

	public static final int TWO = 2;

	public static String getLabel(int sizing) {
		if (sizing == ONE) {
			return SIZING_ONE;
		}
		else if (sizing == TWO) {
			return SIZING_TWO;
		}
		else if (sizing == THREE) {
			return SIZING_THREE;
		}
		else if (sizing == FOUR) {
			return SIZING_FOUR;
		}

		return StringPool.BLANK;
	}

	public static String getLabel(String sizing) {
		if (Validator.isNull(sizing)) {
			return sizing;
		}

		if (sizing.equals("sizing-1")) {
			return SIZING_ONE;
		}
		else if (sizing.equals("sizing-2")) {
			return SIZING_TWO;
		}
		else if (sizing.equals("sizing-3")) {
			return SIZING_THREE;
		}
		else if (sizing.equals("sizing-4")) {
			return SIZING_FOUR;
		}

		return sizing;
	}

	public static int getSizing(String sizing) {
		if (Validator.isNull(sizing)) {
			return 0;
		}

		if (sizing.equals(SIZING_ONE) || sizing.equals("sizing-1")) {
			return 1;
		}
		else if (sizing.equals(SIZING_TWO) || sizing.equals("sizing-2")) {
			return 2;
		}
		else if (sizing.equals(SIZING_THREE) || sizing.equals("sizing-3")) {
			return 3;
		}
		else if (sizing.equals(SIZING_FOUR) || sizing.equals("sizing-4")) {
			return 4;
		}

		return 0;
	}

}