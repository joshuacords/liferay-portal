/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.servlet;

import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.model.PortletApp;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceReference;
import com.liferay.registry.ServiceTracker;
import com.liferay.registry.ServiceTrackerCustomizer;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;

/**
 * @author Michael Bradford
 */
public class PortletResourcesUtil {

	public static ServletContext getPathServletContext(String path) {
		for (ServletContext servletContext :
				_portletResourcesUtil._servletContexts.values()) {

			if (path.startsWith(servletContext.getContextPath())) {
				return servletContext;
			}
		}

		return null;
	}

	public static URL getResource(ServletContext servletContext, String path) {
		if (servletContext == null) {
			return null;
		}

		path = PortalWebResourcesUtil.stripContextPath(servletContext, path);

		try {
			URL url = servletContext.getResource(path);

			if (url != null) {
				return url;
			}
		}
		catch (MalformedURLException malformedURLException) {
		}

		return null;
	}

	public static URL getResource(String path) {
		ServletContext servletContext = getPathServletContext(path);

		if (servletContext != null) {
			return getResource(servletContext, path);
		}

		return null;
	}

	private PortletResourcesUtil() {
		Registry registry = RegistryUtil.getRegistry();

		_serviceTracker = registry.trackServices(
			Portlet.class, new PortletResourcesServiceTrackerCustomizer());

		_serviceTracker.open();
	}

	private static final PortletResourcesUtil _portletResourcesUtil =
		new PortletResourcesUtil();

	private final ServiceTracker<Portlet, Portlet> _serviceTracker;
	private final Map<ServiceReference<Portlet>, ServletContext>
		_servletContexts = new ConcurrentHashMap<>();

	private class PortletResourcesServiceTrackerCustomizer
		implements ServiceTrackerCustomizer<Portlet, Portlet> {

		@Override
		public Portlet addingService(
			ServiceReference<Portlet> serviceReference) {

			Registry registry = RegistryUtil.getRegistry();

			Portlet portlet = registry.getService(serviceReference);

			PortletApp portletApp = portlet.getPortletApp();

			if (portletApp.isWARFile()) {
				_servletContexts.put(
					serviceReference, portletApp.getServletContext());
			}

			return portlet;
		}

		@Override
		public void modifiedService(
			ServiceReference<Portlet> serviceReference, Portlet portlet) {
		}

		@Override
		public void removedService(
			ServiceReference<Portlet> serviceReference, Portlet portlet) {

			Registry registry = RegistryUtil.getRegistry();

			registry.ungetService(serviceReference);

			_servletContexts.remove(serviceReference);
		}

	}

}