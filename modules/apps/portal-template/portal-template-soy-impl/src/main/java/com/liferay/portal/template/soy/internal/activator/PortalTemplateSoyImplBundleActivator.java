/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.template.soy.internal.activator;

import com.liferay.portal.template.soy.util.SoyContextFactory;
import com.liferay.portal.template.soy.util.SoyContextFactoryUtil;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

/**
 * @author Máté Thurzó
 */
public class PortalTemplateSoyImplBundleActivator implements BundleActivator {

	@Override
	public void start(BundleContext bundleContext) throws Exception {
		_serviceTracker =
			new ServiceTracker<SoyContextFactory, SoyContextFactory>(
				bundleContext, SoyContextFactory.class.getName(), null) {

				@Override
				public SoyContextFactory addingService(
					ServiceReference<SoyContextFactory> serviceReference) {

					SoyContextFactory soyContextFactory =
						bundleContext.getService(serviceReference);

					SoyContextFactoryUtil.setSoyContextFactory(
						soyContextFactory);

					return soyContextFactory;
				}

			};

		_serviceTracker.open();
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		_serviceTracker.close();
	}

	private ServiceTracker<SoyContextFactory, SoyContextFactory>
		_serviceTracker;

}