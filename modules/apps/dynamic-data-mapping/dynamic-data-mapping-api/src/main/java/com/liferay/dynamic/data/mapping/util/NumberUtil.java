/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.util;

import com.liferay.petra.string.StringPool;

/**
 * @author Marcos Martins
 */
public class NumberUtil {

	public static int getDecimalSeparatorIndex(String value) {
		int index = value.indexOf(StringPool.PERIOD);

		if (index == -1) {
			index = value.indexOf(StringPool.COMMA);
		}

		return index;
	}

	public static boolean hasDecimalSeparator(String value) {
		if (getDecimalSeparatorIndex(value) == -1) {
			return false;
		}

		return true;
	}

}