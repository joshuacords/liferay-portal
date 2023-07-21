/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.saml.opensaml.integration.internal.binding;

import com.liferay.saml.opensaml.integration.SamlBinding;
import com.liferay.saml.opensaml.integration.internal.velocity.VelocityEngineFactory;

import java.util.Hashtable;
import java.util.Map;

import net.shibboleth.utilities.java.support.xml.ParserPool;

import org.apache.http.client.HttpClient;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Carlos Sierra Andrés
 */
@Component(immediate = true, service = HttpBindingsRegistrator.class)
public class HttpBindingsRegistrator {

	@Activate
	protected void activate(
		BundleContext bundleContext, Map<String, Object> propertiesMap) {

		Hashtable<String, Object> properties = new Hashtable<>(propertiesMap);

		HttpPostBinding httpPostBinding = new HttpPostBinding(
			_parserPool, _velocityEngineFactory.getVelocityEngine());

		HttpRedirectBinding httpRedirectBinding = new HttpRedirectBinding(
			_parserPool);

		HttpSoap11Binding httpSoap11Binding = new HttpSoap11Binding(
			_parserPool, _httpClient);

		_samlHttpPostBindingServiceRegistration = bundleContext.registerService(
			SamlBinding.class, httpPostBinding, properties);

		_samlHttpRedirectBindingServiceRegistration =
			bundleContext.registerService(
				SamlBinding.class, httpRedirectBinding, properties);

		_samlHttpSoap11BindingServiceRegistration =
			bundleContext.registerService(
				SamlBinding.class, httpSoap11Binding, properties);
	}

	@Deactivate
	protected void deactivate() {
		_samlHttpPostBindingServiceRegistration.unregister();

		_samlHttpRedirectBindingServiceRegistration.unregister();

		_samlHttpSoap11BindingServiceRegistration.unregister();
	}

	@Reference
	private HttpClient _httpClient;

	@Reference
	private ParserPool _parserPool;

	private ServiceRegistration<SamlBinding>
		_samlHttpPostBindingServiceRegistration;
	private ServiceRegistration<SamlBinding>
		_samlHttpRedirectBindingServiceRegistration;
	private ServiceRegistration<SamlBinding>
		_samlHttpSoap11BindingServiceRegistration;

	@Reference
	private VelocityEngineFactory _velocityEngineFactory;

}