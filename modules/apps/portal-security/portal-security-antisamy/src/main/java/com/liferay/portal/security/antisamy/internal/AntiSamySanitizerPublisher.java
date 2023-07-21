/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.antisamy.internal;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.sanitizer.Sanitizer;
import com.liferay.portal.security.antisamy.configuration.AntiSamyClassNameConfiguration;
import com.liferay.portal.security.antisamy.configuration.AntiSamyConfiguration;

import java.net.URL;

import java.util.Dictionary;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedServiceFactory;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;

/**
 * @author Tomas Polesovsky
 */
@Component(
	configurationPid = "com.liferay.portal.security.antisamy.configuration.AntiSamyConfiguration",
	immediate = true,
	property = Constants.SERVICE_PID + "=com.liferay.portal.security.antisamy.configuration.AntiSamyClassNameConfiguration",
	service = ManagedServiceFactory.class
)
public class AntiSamySanitizerPublisher implements ManagedServiceFactory {

	@Override
	public void deleted(String pid) {
		String className = _classNames.get(pid);

		_antiSamySanitizerImpl.removePolicy(className);
	}

	@Override
	public String getName() {
		return AntiSamySanitizerPublisher.class.getName();
	}

	@Override
	public void updated(String pid, Dictionary<String, ?> properties)
		throws ConfigurationException {

		AntiSamyClassNameConfiguration antiSamyClassNameConfiguration =
			ConfigurableUtil.createConfigurable(
				AntiSamyClassNameConfiguration.class, properties);

		String className = antiSamyClassNameConfiguration.className();

		Bundle bundle = FrameworkUtil.getBundle(
			AntiSamyClassNameConfiguration.class);

		URL url = bundle.getResource(
			antiSamyClassNameConfiguration.configurationFileURL());

		if (url == null) {
			throw new IllegalStateException(
				"Unable to find " +
					antiSamyClassNameConfiguration.configurationFileURL());
		}

		_antiSamySanitizerImpl.addPolicy(className, url);
		_classNames.put(pid, className);
	}

	@Activate
	protected void activate(
		BundleContext bundleContext, Map<String, Object> properties) {

		AntiSamyConfiguration antiSamyConfiguration =
			ConfigurableUtil.createConfigurable(
				AntiSamyConfiguration.class, properties);

		if (!antiSamyConfiguration.enabled()) {
			return;
		}

		Bundle bundle = bundleContext.getBundle();

		URL url = bundle.getResource(
			antiSamyConfiguration.configurationFileURL());

		if (url == null) {
			throw new IllegalStateException(
				"Unable to find " +
					antiSamyConfiguration.configurationFileURL());
		}

		_antiSamySanitizerImpl = new AntiSamySanitizerImpl(
			antiSamyConfiguration.blacklist(), url,
			antiSamyConfiguration.whitelist());

		_sanitizerServiceRegistration = bundleContext.registerService(
			Sanitizer.class, _antiSamySanitizerImpl, null);
	}

	@Deactivate
	protected void deactivate() {
		if (_sanitizerServiceRegistration != null) {
			_sanitizerServiceRegistration.unregister();

			_sanitizerServiceRegistration = null;
		}
	}

	@Modified
	protected void modified(
		BundleContext bundleContext, Map<String, Object> properties) {

		deactivate();

		activate(bundleContext, properties);
	}

	private AntiSamySanitizerImpl _antiSamySanitizerImpl;
	private final Map<String, String> _classNames = new ConcurrentHashMap<>();
	private ServiceRegistration<Sanitizer> _sanitizerServiceRegistration;

}