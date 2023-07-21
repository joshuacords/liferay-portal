/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.adaptive.media.web.internal.messaging;

import com.liferay.adaptive.media.web.internal.configuration.AMConfiguration;
import com.liferay.adaptive.media.web.internal.constants.AMDestinationNames;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.DestinationConfiguration;
import com.liferay.portal.kernel.messaging.DestinationFactory;
import com.liferay.portal.kernel.util.HashMapDictionary;

import java.util.Dictionary;
import java.util.Map;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Adolfo Pérez
 */
@Component(
	configurationPid = "com.liferay.adaptive.media.web.internal.configuration.AMConfiguration",
	immediate = true, service = {}
)
public class AMMessagingConfigurator {

	@Activate
	public void activate(
		BundleContext bundleContext, Map<String, Object> properties) {

		_amConfiguration = ConfigurableUtil.createConfigurable(
			AMConfiguration.class, properties);

		DestinationConfiguration destinationConfiguration =
			new DestinationConfiguration(
				DestinationConfiguration.DESTINATION_TYPE_PARALLEL,
				AMDestinationNames.ADAPTIVE_MEDIA_PROCESSOR);

		destinationConfiguration.setWorkersCoreSize(
			_amConfiguration.workersCoreSize());
		destinationConfiguration.setWorkersMaxSize(
			_amConfiguration.workersMaxSize());

		Destination destination = _destinationFactory.createDestination(
			destinationConfiguration);

		Dictionary<String, Object> dictionary = new HashMapDictionary<>();

		dictionary.put("destination.name", destination.getName());

		_serviceRegistration = bundleContext.registerService(
			Destination.class, destination, dictionary);
	}

	@Deactivate
	public void deactivate() {
		if (_serviceRegistration != null) {
			_serviceRegistration.unregister();
		}
	}

	@Modified
	public void modified(
		BundleContext bundleContext, Map<String, Object> properties) {

		deactivate();

		activate(bundleContext, properties);
	}

	private volatile AMConfiguration _amConfiguration;

	@Reference
	private DestinationFactory _destinationFactory;

	private ServiceRegistration<Destination> _serviceRegistration;

}