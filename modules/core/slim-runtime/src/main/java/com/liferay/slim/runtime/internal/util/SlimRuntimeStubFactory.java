/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.slim.runtime.internal.util;

import com.liferay.portal.kernel.util.ProxyUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Raymond Augé
 */
public class SlimRuntimeStubFactory {

	public static Object createStub(Class<?> clazz) {
		return ProxyUtil.newProxyInstance(
			clazz.getClassLoader(), new Class<?>[] {clazz},
			new StubInvocationHandker(clazz));
	}

	public static class StubInvocationHandker implements InvocationHandler {

		public StubInvocationHandker(Class<?> clazz) {
			_clazz = clazz;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {

			throw new UnsupportedOperationException(_clazz.getName());
		}

		private final Class<?> _clazz;

	}

}