/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.rules.engine.wiring.internal;

import com.liferay.portal.kernel.messaging.proxy.MessagingProxyInvocationHandler;
import com.liferay.portal.kernel.messaging.proxy.ProxyMessageListener;
import com.liferay.portal.kernel.messaging.sender.SynchronousMessageSender;
import com.liferay.portal.kernel.spring.aop.InvocationHandlerFactory;
import com.liferay.portal.kernel.util.HashMapDictionary;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.rules.engine.RulesEngine;
import com.liferay.portal.rules.engine.RulesEngineConstants;

import java.lang.reflect.InvocationHandler;

import java.util.Dictionary;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(
	enabled = false, immediate = true,
	service = RulesEngineProxyBeanConfigurator.class
)
public class RulesEngineProxyBeanConfigurator {

	@Activate
	protected void activate(BundleContext bundleContext) {
		RulesEngineProxyBean rulesEngineProxyBean = new RulesEngineProxyBean();

		rulesEngineProxyBean.setDestinationName(
			RulesEngineConstants.DESTINATION_NAME);
		rulesEngineProxyBean.setSynchronousDestinationName(
			RulesEngineConstants.DESTINATION_NAME);
		rulesEngineProxyBean.setSynchronousMessageSenderMode(
			SynchronousMessageSender.Mode.DIRECT);

		InvocationHandlerFactory invocationHandlerFactory =
			MessagingProxyInvocationHandler.getInvocationHandlerFactory();

		InvocationHandler invocationHandler =
			invocationHandlerFactory.createInvocationHandler(
				rulesEngineProxyBean);

		Class<?> beanClass = rulesEngineProxyBean.getClass();

		RulesEngine rulesEngine = (RulesEngine)ProxyUtil.newProxyInstance(
			beanClass.getClassLoader(), beanClass.getInterfaces(),
			invocationHandler);

		Dictionary<String, Object> dictionary = new HashMapDictionary<>();

		dictionary.put("rules.engine.proxy", Boolean.TRUE);

		_serviceRegistration = bundleContext.registerService(
			RulesEngine.class, rulesEngine, dictionary);
	}

	@Deactivate
	protected void deactivate() {
		if (_serviceRegistration != null) {
			_serviceRegistration.unregister();
		}
	}

	@Reference(
		service = ProxyMessageListener.class,
		target = "(destination.name=" + RulesEngineConstants.DESTINATION_NAME + ")",
		unbind = "-"
	)
	protected void setProxyMessageListener(
		ProxyMessageListener proxyMessageListener) {
	}

	private ServiceRegistration<RulesEngine> _serviceRegistration;

}