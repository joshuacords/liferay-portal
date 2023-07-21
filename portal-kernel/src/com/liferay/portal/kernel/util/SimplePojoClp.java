/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.util;

import com.liferay.portal.kernel.bean.BeanPropertiesUtil;

/**
 * <p>
 * A class loader proxy able to serialize simple POJOs between two class
 * loaders. It only works for simple POJOs following the Java Beans semantics.
 * The local and remote classes do not have to match or even be derived from
 * each other as long as their properties match. Any bean properties that the
 * source bean exposes but the target bean does not will silently be ignored.
 * </p>
 *
 * @author Micha Kiener
 * @author Brian Wing Shun Chan
 * @deprecated As of Cavanaugh (7.4.x), with no direct replacement
 */
@Deprecated
public class SimplePojoClp<T> {

	public SimplePojoClp(
			Class<? extends T> localImplementationClass,
			ClassLoader remoteClassLoader)
		throws ClassNotFoundException {

		this(
			localImplementationClass, remoteClassLoader,
			localImplementationClass.getName());
	}

	public SimplePojoClp(
			Class<? extends T> localImplementationClass,
			ClassLoader remoteClassLoader, String remoteImplementationClassName)
		throws ClassNotFoundException {

		_localImplementationClass = localImplementationClass;

		_remoteClassLoader = remoteClassLoader;

		_remoteImplementationClass = _remoteClassLoader.loadClass(
			remoteImplementationClassName);
	}

	public T createLocalObject(Object remoteInstance)
		throws IllegalAccessException, InstantiationException {

		T localInstance = _localImplementationClass.newInstance();

		BeanPropertiesUtil.copyProperties(
			remoteInstance, localInstance, _localImplementationClass);

		return localInstance;
	}

	public Object createRemoteObject(T localInstance)
		throws IllegalAccessException, InstantiationException {

		Object remoteInstance = _remoteImplementationClass.newInstance();

		BeanPropertiesUtil.copyProperties(
			localInstance, remoteInstance, _remoteImplementationClass);

		return remoteInstance;
	}

	private final Class<? extends T> _localImplementationClass;
	private final ClassLoader _remoteClassLoader;
	private final Class<?> _remoteImplementationClass;

}