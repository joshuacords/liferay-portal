/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.js.loader.modules.extender.internal.npm;

import com.liferay.frontend.js.loader.modules.extender.npm.NPMRegistry;
import com.liferay.frontend.js.loader.modules.extender.npm.NPMResolver;
import com.liferay.portal.kernel.json.JSONFactory;

import java.net.URL;

import java.util.Hashtable;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceFactory;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Iván Zaera Avellón
 */
@Component(immediate = true, service = ServiceFactory.class)
public class NPMResolverServiceFactory implements ServiceFactory<NPMResolver> {

	@Activate
	public void activate(BundleContext bundleContext) {
		_serviceRegistration = bundleContext.registerService(
			NPMResolver.class, this, new Hashtable<String, Object>());
	}

	@Deactivate
	public void deactivate() {
		_serviceRegistration.unregister();
	}

	@Override
	public NPMResolver getService(
		Bundle bundle, ServiceRegistration<NPMResolver> serviceRegistration) {

		URL url = bundle.getEntry("META-INF/resources/package.json");

		if (url == null) {
			return new NullNPMResolverImpl(bundle);
		}

		return new NPMResolverImpl(bundle, _jsonFactory, _npmRegistry);
	}

	@Override
	public void ungetService(
		Bundle bundle, ServiceRegistration<NPMResolver> serviceRegistration,
		NPMResolver npmResolver) {
	}

	@Reference(
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	private volatile JSONFactory _jsonFactory;

	@Reference(
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	private volatile NPMRegistry _npmRegistry;

	private ServiceRegistration<NPMResolver> _serviceRegistration;

}