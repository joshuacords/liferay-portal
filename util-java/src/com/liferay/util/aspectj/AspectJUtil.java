/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.util.aspectj;

import com.liferay.portal.kernel.util.ServerDetector;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * @author Brian Wing Shun Chan
 */
public class AspectJUtil {

	public static Method getMethod(MethodSignature methodSignature)
		throws NoSuchMethodException {

		Method method = null;

		if (ServerDetector.isWebSphere()) {
			Class<?> declaringType = methodSignature.getDeclaringType();

			method = declaringType.getMethod(
				methodSignature.getName(), methodSignature.getParameterTypes());
		}
		else {
			method = methodSignature.getMethod();
		}

		return method;
	}

	public static Method getMethod(ProceedingJoinPoint proceedingJoinPoint)
		throws NoSuchMethodException {

		MethodSignature methodSignature =
			(MethodSignature)proceedingJoinPoint.getSignature();

		return getMethod(methodSignature);
	}

}