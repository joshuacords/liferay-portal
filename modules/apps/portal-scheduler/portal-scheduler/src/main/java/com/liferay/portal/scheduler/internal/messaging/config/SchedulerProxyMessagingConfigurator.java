/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.scheduler.internal.messaging.config;

import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.DestinationConfiguration;
import com.liferay.portal.kernel.messaging.DestinationFactory;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.MessageBus;
import com.liferay.portal.kernel.messaging.proxy.ProxyMessageListener;
import com.liferay.portal.kernel.util.HashMapDictionary;

import java.util.Dictionary;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Tina Tian
 */
@Component(
	enabled = false, immediate = true,
	service = SchedulerProxyMessagingConfigurator.class
)
public class SchedulerProxyMessagingConfigurator {

	@Activate
	protected void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;

		DestinationConfiguration destinationConfiguration =
			new DestinationConfiguration(
				DestinationConfiguration.DESTINATION_TYPE_PARALLEL,
				DestinationNames.SCHEDULER_ENGINE);

		Destination destination = _destinationFactory.createDestination(
			destinationConfiguration);

		destination.register(_proxyMessageListener);

		Dictionary<String, Object> properties = new HashMapDictionary<>();

		properties.put("destination.name", destination.getName());

		_destinationServiceRegistration = bundleContext.registerService(
			Destination.class, destination, properties);

		properties.put("destination.ready", true);

		_destinationReadyServiceRegistration = bundleContext.registerService(
			Object.class, new Object(), properties);
	}

	@Deactivate
	protected void deactivate() {
		_destinationReadyServiceRegistration.unregister();

		Destination destination = _bundleContext.getService(
			_destinationServiceRegistration.getReference());

		_destinationServiceRegistration.unregister();

		destination.destroy();
	}

	@Reference(unbind = "-")
	protected void setDestinationFactory(
		DestinationFactory destinationFactory) {

		_destinationFactory = destinationFactory;
	}

	@Reference(unbind = "-")
	protected void setMessageBus(MessageBus messageBus) {
	}

	@Reference(
		service = ProxyMessageListener.class,
		target = "(destination.name=" + DestinationNames.SCHEDULER_ENGINE + ")",
		unbind = "-"
	)
	protected void setProxyMessageListener(
		ProxyMessageListener proxyMessageListener) {

		_proxyMessageListener = proxyMessageListener;
	}

	private BundleContext _bundleContext;
	private DestinationFactory _destinationFactory;
	private ServiceRegistration<Object> _destinationReadyServiceRegistration;
	private ServiceRegistration<Destination> _destinationServiceRegistration;
	private ProxyMessageListener _proxyMessageListener;

}