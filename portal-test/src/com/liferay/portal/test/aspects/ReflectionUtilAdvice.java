/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.test.aspects;

import com.liferay.petra.reflect.ReflectionUtil;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * @author Shuyang Zhou
 */
@Aspect
public class ReflectionUtilAdvice {

	public static void setDeclaredFieldThrowable(
		Throwable declaredFieldThrowable) {

		_declaredFieldThrowable = declaredFieldThrowable;
	}

	public static void setDeclaredMethodThrowable(
			Throwable declaredMethodThrowable)
		throws ClassNotFoundException {

		Class.forName(
			ReflectionUtil.class.getName(), true,
			ReflectionUtil.class.getClassLoader());

		_declaredMethodThrowable = declaredMethodThrowable;
	}

	@Around(
		"execution(public static java.lang.reflect.Field " +
			"com.liferay.petra.reflect.ReflectionUtil." +
				"getDeclaredField(Class, String))"
	)
	public Object getDeclaredField(ProceedingJoinPoint proceedingJoinPoint)
		throws Throwable {

		if (_declaredFieldThrowable != null) {
			throw _declaredFieldThrowable;
		}

		return proceedingJoinPoint.proceed();
	}

	@Around(
		"execution(public static java.lang.reflect.Method " +
			"com.liferay.petra.reflect.ReflectionUtil." +
				"getDeclaredMethod(Class, String, Class...))"
	)
	public Object getDeclaredMethod(ProceedingJoinPoint proceedingJoinPoint)
		throws Throwable {

		if (_declaredMethodThrowable != null) {
			throw _declaredMethodThrowable;
		}

		return proceedingJoinPoint.proceed();
	}

	private static Throwable _declaredFieldThrowable;
	private static Throwable _declaredMethodThrowable;

}