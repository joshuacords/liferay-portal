/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.util;

import java.lang.reflect.Method;

/**
 * @author     Michael C. Han
 * @author     Shuyang Zhou
 * @deprecated As of Judson (7.1.x), replaced by {@link MethodKey}
 */
@Deprecated
public class MethodCache {

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             MethodKey#resetCache()}
	 */
	@Deprecated
	public static void reset() {
		MethodKey.resetCache();
	}

	/**
	 * @see MethodKey
	 */
	protected static Method get(MethodKey methodKey)
		throws NoSuchMethodException {

		return methodKey.getMethod();
	}

}