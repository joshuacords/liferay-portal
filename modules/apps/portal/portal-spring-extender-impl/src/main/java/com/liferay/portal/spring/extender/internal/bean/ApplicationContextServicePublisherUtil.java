/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.spring.extender.internal.bean;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.kernel.util.HashMapDictionary;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.util.PropsValues;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import org.springframework.beans.factory.BeanIsAbstractException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;

/**
 * @author Miguel Pastor
 * @author Preston Crary
 */
public class ApplicationContextServicePublisherUtil {

	public static List<ServiceRegistration<?>> registerContext(
		ConfigurableApplicationContext configurableApplicationContext,
		BundleContext bundleContext) {

		List<ServiceRegistration<?>> serviceRegistrations = new ArrayList<>();

		ConfigurableListableBeanFactory configurableListableBeanFactory =
			configurableApplicationContext.getBeanFactory();

		Iterator<String> iterator =
			configurableListableBeanFactory.getBeanNamesIterator();

		iterator.forEachRemaining(
			beanName -> {
				try {
					ServiceRegistration<?> serviceRegistration =
						_registerService(
							bundleContext, beanName,
							configurableApplicationContext.getBean(beanName));

					if (serviceRegistration != null) {
						serviceRegistrations.add(serviceRegistration);
					}
				}
				catch (BeanIsAbstractException beanIsAbstractException) {
				}
				catch (Exception exception) {
					_log.error(
						"Unable to register service " + beanName, exception);
				}
			});

		Bundle bundle = bundleContext.getBundle();

		Dictionary<String, Object> properties = new HashMapDictionary<>();

		properties.put(
			"org.springframework.context.service.name",
			bundle.getSymbolicName());

		ServiceRegistration<ApplicationContext> serviceRegistration =
			bundleContext.registerService(
				ApplicationContext.class, configurableApplicationContext,
				properties);

		serviceRegistrations.add(serviceRegistration);

		return serviceRegistrations;
	}

	public static void unregisterContext(
		List<ServiceRegistration<?>> serviceRegistrations) {

		if (serviceRegistrations != null) {
			for (ServiceRegistration<?> serviceReference :
					serviceRegistrations) {

				serviceReference.unregister();
			}

			serviceRegistrations.clear();
		}
	}

	private static ServiceRegistration<?> _registerService(
		BundleContext bundleContext, String beanName, Object bean) {

		Class<?> clazz = bean.getClass();

		if (ProxyUtil.isProxyClass(clazz)) {
			InvocationHandler invocationHandler =
				ProxyUtil.getInvocationHandler(bean);

			Class<?> invocationHandlerClass = invocationHandler.getClass();

			try {
				Method method = invocationHandlerClass.getMethod("getTarget");

				Object target = method.invoke(invocationHandler);

				clazz = target.getClass();
			}
			catch (ReflectiveOperationException reflectiveOperationException) {
			}
		}

		OSGiBeanProperties osgiBeanProperties = AnnotationUtils.findAnnotation(
			clazz, OSGiBeanProperties.class);

		Set<String> names = OSGiBeanProperties.Service.interfaceNames(
			bean, osgiBeanProperties,
			PropsValues.MODULE_FRAMEWORK_SERVICES_IGNORED_INTERFACES);

		if (names.isEmpty()) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Skipping registration because of an empty list of " +
						"interfaces");
			}

			return null;
		}

		Bundle bundle = bundleContext.getBundle();

		HashMapDictionary<String, Object> properties =
			new HashMapDictionary<>();

		properties.put("bean.id", beanName);
		properties.put("origin.bundle.symbolic.name", bundle.getSymbolicName());

		if (osgiBeanProperties != null) {
			properties.putAll(
				OSGiBeanProperties.Convert.toMap(osgiBeanProperties));
		}

		return bundleContext.registerService(
			names.toArray(new String[0]), bean, properties);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ApplicationContextServicePublisherUtil.class);

}