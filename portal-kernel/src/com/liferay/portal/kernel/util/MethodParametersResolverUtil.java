/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.util;

import java.lang.reflect.Method;

/**
 * @author Igor Spasic
 */
public class MethodParametersResolverUtil {

	public static MethodParametersResolver getMethodParametersResolver() {
		return _methodParametersResolver;
	}

	public static MethodParameter[] resolveMethodParameters(Method method) {
		return getMethodParametersResolver().resolveMethodParameters(method);
	}

	public void setMethodParametersResolver(
		MethodParametersResolver methodParametersResolver) {

		_methodParametersResolver = methodParametersResolver;
	}

	private static MethodParametersResolver _methodParametersResolver;

}