/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.model.version;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.Map;

/**
 * @author     Preston Crary
 * @deprecated As of Mueller (7.2.x), with no direct replacement
 */
@Deprecated
public class VersionedModelInvocationHandler implements InvocationHandler {

	public VersionedModelInvocationHandler(
		VersionModel<?> versionModel, Map<Method, Method> methods) {

		_versionModel = versionModel;
		_methods = methods;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] arguments)
		throws Throwable {

		Method mappedMethod = _methods.get(method);

		if (mappedMethod == null) {
			String methodName = method.getName();

			if (methodName.equals("getHeadId")) {
				return -_versionModel.getVersionedModelId();
			}

			if (methodName.equals("isHead")) {
				return true;
			}

			throw new UnsupportedOperationException(methodName);
		}

		try {
			return mappedMethod.invoke(_versionModel, arguments);
		}
		catch (InvocationTargetException invocationTargetException) {
			throw invocationTargetException.getTargetException();
		}
	}

	private final Map<Method, Method> _methods;
	private final VersionModel<?> _versionModel;

}