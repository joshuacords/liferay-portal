/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.remote.json.web.service.extender.internal;

import com.liferay.portal.kernel.bean.ClassLoaderBeanHandler;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceScannerStrategy;
import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.util.ProxyUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Miguel Pastor
 */
public class ServiceJSONWebServiceScannerStrategy
	implements JSONWebServiceScannerStrategy {

	@Override
	public MethodDescriptor[] scan(Object service) {
		Class<?> clazz = getTargetClass(service);

		Method[] methods = clazz.getMethods();

		List<MethodDescriptor> methodDescriptors = new ArrayList<>(
			methods.length);

		for (Method method : methods) {
			Class<?> declaringClass = method.getDeclaringClass();

			if (declaringClass != clazz) {
				continue;
			}

			methodDescriptors.add(new MethodDescriptor(method));
		}

		return methodDescriptors.toArray(new MethodDescriptor[0]);
	}

	/**
	 * @see com.liferay.portal.jsonwebservice.SpringJSONWebServiceScannerStrategy#getTargetClass(
	 *      Object)
	 */
	protected Class<?> getTargetClass(Object service) {
		while (ProxyUtil.isProxyClass(service.getClass())) {
			InvocationHandler invocationHandler =
				ProxyUtil.getInvocationHandler(service);

			if (invocationHandler instanceof ClassLoaderBeanHandler) {
				ClassLoaderBeanHandler classLoaderBeanHandler =
					(ClassLoaderBeanHandler)invocationHandler;

				Object bean = classLoaderBeanHandler.getBean();

				if (bean instanceof ServiceWrapper) {
					ServiceWrapper<?> serviceWrapper = (ServiceWrapper<?>)bean;

					service = serviceWrapper.getWrappedService();
				}
				else {
					service = bean;
				}
			}
			else {
				Class<?> invocationHandlerClass = invocationHandler.getClass();

				try {
					Method method = invocationHandlerClass.getMethod(
						"getTarget");

					service = method.invoke(invocationHandler);
				}
				catch (ReflectiveOperationException
							reflectiveOperationException) {
				}
			}
		}

		return service.getClass();
	}

}