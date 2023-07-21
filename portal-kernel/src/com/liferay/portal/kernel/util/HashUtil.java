/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.util;

/**
 * @author     Shuyang Zhou
 * @deprecated As of Judson (7.1.x), replaced by {@link
 *             com.liferay.petra.lang.HashUtil}
 */
@Deprecated
public class HashUtil {

	public static int hash(int seed, boolean value) {
		return com.liferay.petra.lang.HashUtil.hash(seed, value);
	}

	public static int hash(int seed, int value) {
		return com.liferay.petra.lang.HashUtil.hash(seed, value);
	}

	public static int hash(int seed, long value) {
		return com.liferay.petra.lang.HashUtil.hash(seed, value);
	}

	public static int hash(int seed, Object value) {
		return com.liferay.petra.lang.HashUtil.hash(seed, value);
	}

}