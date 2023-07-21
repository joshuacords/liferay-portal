/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.bean.portlet.cdi.extension.internal;

import com.liferay.petra.string.StringBundler;

import java.lang.reflect.Method;

import javax.enterprise.inject.spi.BeanManager;

/**
 * @author Neil Griffin
 */
public class BeanMethodFactory {

	public BeanMethodFactory(
		Class<?> clazz, Method method, MethodType methodType) {

		_clazz = clazz;
		_method = method;
		_methodType = methodType;
	}

	public BeanMethod create(BeanManager beanManager) {
		return new BeanMethod(
			beanManager, beanManager.resolve(beanManager.getBeans(_clazz)),
			_method, _methodType);
	}

	public String[] getPortletNames() {
		return _methodType.getPortletNames(_method);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{clazz=");
		sb.append(_clazz);
		sb.append(", method=");
		sb.append(_method);
		sb.append(", methodType=");
		sb.append(_methodType);
		sb.append("}");

		return sb.toString();
	}

	private final Class<?> _clazz;
	private final Method _method;
	private final MethodType _methodType;

}