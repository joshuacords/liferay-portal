/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.init.servlet.filter.internal;

import com.liferay.portal.kernel.util.HashMapDictionary;

import java.util.Dictionary;

import javax.servlet.Filter;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Matthew Tambara
 */
@Component(immediate = true, service = {})
public class InitFilterTracker {

	@Activate
	protected void activate(BundleContext bundleContext) {
		InitFilter initFilter = new InitFilter();

		Dictionary<String, Object> properties = new HashMapDictionary<>();

		properties.put("dispatcher", new String[] {"FORWARD", "REQUEST"});
		properties.put("servlet-context-name", "");
		properties.put("servlet-filter-name", "Init Filter");
		properties.put("url-pattern", "/*");

		_serviceRegistration = bundleContext.registerService(
			Filter.class, initFilter, properties);

		initFilter.setServiceRegistration(_serviceRegistration);
	}

	@Deactivate
	protected void deactivate() {
		try {
			_serviceRegistration.unregister();
		}
		catch (IllegalStateException illegalStateException) {
		}
	}

	private ServiceRegistration<Filter> _serviceRegistration;

}