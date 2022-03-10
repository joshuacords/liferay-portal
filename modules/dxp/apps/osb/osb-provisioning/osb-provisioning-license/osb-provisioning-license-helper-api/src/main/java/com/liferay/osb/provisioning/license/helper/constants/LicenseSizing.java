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

}