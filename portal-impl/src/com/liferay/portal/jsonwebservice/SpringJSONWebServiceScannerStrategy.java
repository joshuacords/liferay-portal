/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.jsonwebservice;

import com.liferay.portal.kernel.bean.ClassLoaderBeanHandler;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceScannerStrategy;
import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.spring.aop.AopInvocationHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Miguel Pastor
 */
public class SpringJSONWebServiceScannerStrategy
	implements JSONWebServiceScannerStrategy {

	@Override
	public MethodDescriptor[] scan(Object service) {
		Class<?> clazz = getTargetClass(service);

		Method[] methods = clazz.getMethods();

		List<MethodDescriptor> methodDescriptors = new ArrayList<>(
			methods.length);

		for (Method method : methods) {
			Class<?> declaringClass = method.getDeclaringClass();

			if ((declaringClass != clazz) || !isInterfaceMethod(method)) {
				continue;
			}

			methodDescriptors.add(new MethodDescriptor(method));
		}

		return methodDescriptors.toArray(new MethodDescriptor[0]);
	}

	/**
	 * @see com.liferay.portal.remote.json.web.service.extender.internal.ServiceJSONWebServiceScannerStrategy#getTargetClass(
	 *      Object)
	 */
	protected Class<?> getTargetClass(Object service) {
		while (ProxyUtil.isProxyClass(service.getClass())) {
			InvocationHandler invocationHandler =
				ProxyUtil.getInvocationHandler(service);

			if (invocationHandler instanceof AopInvocationHandler) {
				AopInvocationHandler aopInvocationHandler =
					(AopInvocationHandler)invocationHandler;

				service = aopInvocationHandler.getTarget();
			}
			else if (invocationHandler instanceof ClassLoaderBeanHandler) {
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
		}

		return service.getClass();
	}

	protected boolean isInterfaceMethod(Method method) {
		Class<?> declaringClass = method.getDeclaringClass();

		if (declaringClass.isInterface()) {
			return true;
		}

		Queue<Class<?>> queue = new LinkedList<>(
			Arrays.asList(declaringClass.getInterfaces()));

		Class<?> superClass = declaringClass.getSuperclass();

		if (superClass != null) {
			queue.add(superClass);
		}

		Class<?> clazz = null;

		while ((clazz = queue.poll()) != null) {
			if (clazz.isInterface()) {
				try {
					clazz.getMethod(
						method.getName(), method.getParameterTypes());

					return true;
				}
				catch (ReflectiveOperationException
							reflectiveOperationException) {
				}
			}
			else {
				Collections.addAll(queue, clazz.getInterfaces());

				superClass = clazz.getSuperclass();

				if (superClass != null) {
					queue.add(superClass);
				}
			}
		}

		return false;
	}

}