/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.util;

import java.lang.reflect.Method;

import java.util.Comparator;

/**
 * @author     Shuyang Zhou
 * @author     Brian Wing Shun Chan
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public class MethodComparator implements Comparator<Method> {

	@Override
	public int compare(Method method1, Method method2) {
		String name1 = method1.getName();
		String name2 = method2.getName();

		int value = name1.compareTo(name2);

		if (value != 0) {
			return value;
		}

		Class<?>[] parameterTypes1 = method1.getParameterTypes();
		Class<?>[] parameterTypes2 = method2.getParameterTypes();

		int index = 0;

		while ((index < parameterTypes1.length) &&
			   (index < parameterTypes2.length)) {

			Class<?> parameterType1 = parameterTypes1[index];
			Class<?> parameterType2 = parameterTypes2[index];

			String parameterTypeName1 = parameterType1.getName();
			String parameterTypeName2 = parameterType2.getName();

			value = parameterTypeName1.compareTo(parameterTypeName2);

			if (value != 0) {
				return value;
			}

			index++;
		}

		if (index < (parameterTypes1.length - 1)) {
			return -1;
		}

		return 1;
	}

}