/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.remote.cors.internal.servlet.filter;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.util.HashMapDictionary;
import com.liferay.portal.remote.cors.configuration.PortalCORSConfiguration;
import com.liferay.portal.remote.cors.internal.CORSSupport;

import java.util.Dictionary;
import java.util.Map;

import javax.servlet.Filter;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Tomas Polesovsky
 */
@Component(
	configurationPid = "com.liferay.portal.remote.cors.configuration.PortalCORSConfiguration",
	configurationPolicy = ConfigurationPolicy.REQUIRE, immediate = true,
	service = {}
)
public class ConfigurablePortalCORSServletFilterPublisher {

	@Activate
	protected void activate(
		BundleContext bundleContext, Map<String, Object> properties) {

		PortalCORSConfiguration portalCORSConfiguration =
			ConfigurableUtil.createConfigurable(
				PortalCORSConfiguration.class, properties);

		if (!portalCORSConfiguration.enabled()) {
			return;
		}

		Map<String, String> corsHeaders = CORSSupport.buildCORSHeaders(
			portalCORSConfiguration.headers());

		CORSServletFilter corsServletFilter = new CORSServletFilter();

		corsServletFilter.setCORSHeaders(corsHeaders);

		Dictionary<String, Object> filterProperties = new HashMapDictionary<>();

		filterProperties.put("before-filter", "Upload Servlet Request Filter");
		filterProperties.put("dispatcher", new String[] {"FORWARD", "REQUEST"});
		filterProperties.put("servlet-context-name", "");
		filterProperties.put(
			"servlet-filter-name",
			"CORS Servlet Filter for " + portalCORSConfiguration.name());
		filterProperties.put(
			"url-pattern", portalCORSConfiguration.filterMappingURLPatterns());

		_serviceRegistration = bundleContext.registerService(
			Filter.class, corsServletFilter, filterProperties);
	}

	@Deactivate
	protected void deactivate() {
		if (_serviceRegistration != null) {
			_serviceRegistration.unregister();
		}

		_serviceRegistration = null;
	}

	private ServiceRegistration<Filter> _serviceRegistration;

}