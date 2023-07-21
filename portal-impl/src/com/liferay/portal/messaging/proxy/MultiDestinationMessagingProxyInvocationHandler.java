/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.messaging.proxy;

import com.liferay.portal.kernel.messaging.proxy.BaseMultiDestinationProxyBean;
import com.liferay.portal.kernel.messaging.proxy.ProxyModeThreadLocal;
import com.liferay.portal.kernel.messaging.proxy.ProxyRequest;
import com.liferay.portal.kernel.spring.aop.InvocationHandlerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Shuyang Zhou
 */
public class MultiDestinationMessagingProxyInvocationHandler
	implements InvocationHandler {

	public static InvocationHandlerFactory getInvocationHandlerFactory() {
		return _invocationHandlerFactory;
	}

	public MultiDestinationMessagingProxyInvocationHandler(
		BaseMultiDestinationProxyBean baseMultiDestinationProxyBean) {

		_baseMultiDestinationProxyBean = baseMultiDestinationProxyBean;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
		throws Throwable {

		if (_objectMethods.contains(method)) {
			return method.invoke(_baseMultiDestinationProxyBean, args);
		}

		ProxyRequest proxyRequest = new ProxyRequest(method, args);

		if (proxyRequest.isSynchronous() ||
			ProxyModeThreadLocal.isForceSync()) {

			return _baseMultiDestinationProxyBean.synchronousSend(proxyRequest);
		}

		_baseMultiDestinationProxyBean.send(proxyRequest);

		return null;
	}

	private static final InvocationHandlerFactory _invocationHandlerFactory =
		new InvocationHandlerFactory() {

			@Override
			public InvocationHandler createInvocationHandler(Object obj) {
				return new MultiDestinationMessagingProxyInvocationHandler(
					(BaseMultiDestinationProxyBean)obj);
			}

		};

	private static final Set<Method> _objectMethods = new HashSet<>(
		Arrays.asList(Object.class.getDeclaredMethods()));

	private final BaseMultiDestinationProxyBean _baseMultiDestinationProxyBean;

}