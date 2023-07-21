/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.messaging.proxy;

import com.liferay.portal.kernel.spring.aop.InvocationHandlerFactory;
import com.liferay.portal.kernel.util.ProxyUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Shuyang Zhou
 */
public class MessagingProxyInvocationHandler implements InvocationHandler {

	public static Object createProxy(BaseProxyBean baseProxyBean) {
		Class<?> beanClass = baseProxyBean.getClass();

		Thread currentThread = Thread.currentThread();

		return ProxyUtil.newProxyInstance(
			currentThread.getContextClassLoader(), beanClass.getInterfaces(),
			new MessagingProxyInvocationHandler(baseProxyBean));
	}

	public static InvocationHandlerFactory getInvocationHandlerFactory() {
		return _invocationHandlerFactory;
	}

	public MessagingProxyInvocationHandler(BaseProxyBean baseProxyBean) {
		_baseProxyBean = baseProxyBean;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
		throws Throwable {

		ProxyRequest proxyRequest = new ProxyRequest(method, args);

		if (proxyRequest.isSynchronous() ||
			ProxyModeThreadLocal.isForceSync()) {

			return _baseProxyBean.synchronousSend(proxyRequest);
		}

		_baseProxyBean.send(proxyRequest);

		return null;
	}

	private static final InvocationHandlerFactory _invocationHandlerFactory =
		new InvocationHandlerFactory() {

			@Override
			public InvocationHandler createInvocationHandler(Object obj) {
				return new MessagingProxyInvocationHandler((BaseProxyBean)obj);
			}

		};

	private final BaseProxyBean _baseProxyBean;

}