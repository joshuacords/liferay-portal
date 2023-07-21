/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.remote.axis.extender.internal;

import com.liferay.osgi.util.BundleUtil;
import com.liferay.portal.kernel.bean.ClassLoaderBeanHandler;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.AggregateClassLoader;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.servlet.filters.authverifier.AuthVerifierFilter;
import com.liferay.util.axis.AxisServlet;

import java.net.URL;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.Filter;
import javax.servlet.Servlet;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.ServiceRegistration;
import org.osgi.framework.wiring.BundleWiring;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.http.context.ServletContextHelper;
import org.osgi.service.http.whiteboard.HttpWhiteboardConstants;
import org.osgi.util.tracker.BundleTracker;
import org.osgi.util.tracker.BundleTrackerCustomizer;

/**
 * @author Carlos Sierra Andrés
 */
@Component(immediate = true, service = {})
public class AxisExtender {

	@Activate
	protected void activate(ComponentContext componentContext) {
		_bundleTracker = new BundleTracker<>(
			componentContext.getBundleContext(), Bundle.ACTIVE,
			new BundleRegistrationInfoBundleTrackerCustomizer());

		_bundleTracker.open();
	}

	@Deactivate
	protected void deactivate() {
		_bundleTracker.close();
	}

	private static final Log _log = LogFactoryUtil.getLog(AxisExtender.class);

	private BundleTracker<BundleRegistrationInfo> _bundleTracker;

	private static class BundleRegistrationInfo {

		public BundleRegistrationInfo(
			ServiceRegistration<Filter> authVerifierFilterServiceRegistration,
			ServiceRegistration<Servlet> axisServletServiceRegistration,
			ServiceRegistration<ServletContextHelper>
				bundleServletContextServiceRegistration) {

			_authVerifierFilterServiceRegistration =
				authVerifierFilterServiceRegistration;
			_axisServletServiceRegistration = axisServletServiceRegistration;

			_bundleServletContextHelperServiceRegistration =
				bundleServletContextServiceRegistration;
		}

		public ServiceRegistration<Filter>
			getAuthVerifierFilterServiceRegistration() {

			return _authVerifierFilterServiceRegistration;
		}

		public ServiceRegistration<Servlet>
			getAxisServletServiceRegistration() {

			return _axisServletServiceRegistration;
		}

		public ServiceRegistration<ServletContextHelper>
			getBundleServletContextHelperServiceRegistration() {

			return _bundleServletContextHelperServiceRegistration;
		}

		private final ServiceRegistration<Filter>
			_authVerifierFilterServiceRegistration;
		private final ServiceRegistration<Servlet>
			_axisServletServiceRegistration;
		private final ServiceRegistration<ServletContextHelper>
			_bundleServletContextHelperServiceRegistration;

	}

	private class BundleRegistrationInfoBundleTrackerCustomizer
		implements BundleTrackerCustomizer<BundleRegistrationInfo> {

		@Override
		public BundleRegistrationInfo addingBundle(
			final Bundle bundle, BundleEvent bundleEvent) {

			Enumeration<URL> enumeration = bundle.findEntries(
				"/WEB-INF", "server-config.wsdd", false);

			if ((enumeration == null) || !enumeration.hasMoreElements()) {
				return null;
			}

			BundleContext bundleContext = bundle.getBundleContext();

			Dictionary<String, Object> properties = new Hashtable<>();

			properties.put(
				HttpWhiteboardConstants.HTTP_WHITEBOARD_CONTEXT_NAME,
				"liferay.axis." + bundle.getSymbolicName());
			properties.put(
				HttpWhiteboardConstants.HTTP_WHITEBOARD_CONTEXT_PATH,
				"/" + bundle.getSymbolicName());

			ServiceRegistration<ServletContextHelper>
				bundleServletContextHelperServiceRegistration =
					bundleContext.registerService(
						ServletContextHelper.class,
						new ServletContextHelper(bundle) {

							@Override
							public URL getResource(String name) {
								if (name.startsWith("/")) {
									name = name.substring(1);
								}

								return BundleUtil.
									getResourceInBundleOrFragments(
										bundle, name);
							}

						},
						properties);

			properties = new Hashtable<>();

			properties.put(
				HttpWhiteboardConstants.HTTP_WHITEBOARD_CONTEXT_SELECT,
				"liferay.axis." + bundle.getSymbolicName());
			properties.put(
				HttpWhiteboardConstants.HTTP_WHITEBOARD_FILTER_NAME,
				AuthVerifierFilter.class.getName());
			properties.put(
				HttpWhiteboardConstants.HTTP_WHITEBOARD_FILTER_PATTERN,
				"/api/axis/*");
			properties.put(
				HttpWhiteboardConstants.
					HTTP_WHITEBOARD_FILTER_INIT_PARAM_PREFIX +
						"portal_property_prefix",
				"axis.servlet.");

			ServiceRegistration<Filter> authVerifierFilterServiceRegistration =
				bundleContext.registerService(
					Filter.class, new AuthVerifierFilter(), properties);

			properties = new Hashtable<>();

			properties.put(
				HttpWhiteboardConstants.HTTP_WHITEBOARD_CONTEXT_SELECT,
				"liferay.axis." + bundle.getSymbolicName());
			properties.put(
				HttpWhiteboardConstants.HTTP_WHITEBOARD_SERVLET_NAME,
				AxisServlet.class.getName());
			properties.put(
				HttpWhiteboardConstants.HTTP_WHITEBOARD_SERVLET_PATTERN,
				"/api/axis/*");
			properties.put("servlet.init.axis.servicesPath", "/api/axis/");
			properties.put("servlet.init.httpMethods", "GET,POST,HEAD");

			Bundle bundleContextBundle = bundleContext.getBundle();

			BundleWiring bundleContextBundleBundleWiring =
				bundleContextBundle.adapt(BundleWiring.class);

			BundleWiring bundleWiring = bundle.adapt(BundleWiring.class);

			ClassLoader aggregateClassLoader =
				AggregateClassLoader.getAggregateClassLoader(
					bundleContextBundleBundleWiring.getClassLoader(),
					bundleWiring.getClassLoader());

			Servlet servlet = (Servlet)ProxyUtil.newProxyInstance(
				aggregateClassLoader, new Class<?>[] {Servlet.class},
				new ClassLoaderBeanHandler(
					new AxisServlet(), aggregateClassLoader));

			ServiceRegistration<Servlet> axisServletServiceRegistration =
				bundleContext.registerService(
					Servlet.class, servlet, properties);

			return new BundleRegistrationInfo(
				authVerifierFilterServiceRegistration,
				axisServletServiceRegistration,
				bundleServletContextHelperServiceRegistration);
		}

		@Override
		public void modifiedBundle(
			Bundle bundle, BundleEvent bundleEvent,
			BundleRegistrationInfo bundleRegistrationInfo) {
		}

		@Override
		public void removedBundle(
			Bundle bundle, BundleEvent bundleEvent,
			BundleRegistrationInfo bundleRegistrationInfo) {

			ServiceRegistration<Servlet> axisServletServiceRegistration =
				bundleRegistrationInfo.getAxisServletServiceRegistration();

			try {
				axisServletServiceRegistration.unregister();
			}
			catch (Exception exception) {
				_log.error(exception, exception);
			}

			ServiceRegistration<Filter> authVerifierFilterServiceRegistration =
				bundleRegistrationInfo.
					getAuthVerifierFilterServiceRegistration();

			try {
				authVerifierFilterServiceRegistration.unregister();
			}
			catch (Exception exception) {
				_log.error(exception, exception);
			}

			ServiceRegistration<ServletContextHelper>
				bundleServletContextHelperServiceRegistration =
					bundleRegistrationInfo.
						getBundleServletContextHelperServiceRegistration();

			try {
				bundleServletContextHelperServiceRegistration.unregister();
			}
			catch (Exception exception) {
				_log.error(exception, exception);
			}
		}

	}

}