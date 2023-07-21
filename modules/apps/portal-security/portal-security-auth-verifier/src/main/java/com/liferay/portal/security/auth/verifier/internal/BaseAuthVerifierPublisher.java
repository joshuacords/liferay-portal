/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.auth.verifier.internal;

import com.liferay.portal.kernel.security.auth.verifier.AuthVerifier;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.security.auth.AuthVerifierPipeline;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Map;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

/**
 * @author Tomas Polesovsky
 */
public abstract class BaseAuthVerifierPublisher {

	protected void activate(
		BundleContext bundleContext, Map<String, Object> properties) {

		Boolean enabled = GetterUtil.getBoolean(properties.get("enabled"));

		if ((enabled == null) || !enabled) {
			return;
		}

		AuthVerifier authVerifier = getAuthVerifierInstance();

		Class<?> clazz = authVerifier.getClass();

		String authVerifierPropertyName =
			AuthVerifierPipeline.getAuthVerifierPropertyName(clazz.getName());

		Dictionary<String, Object> authVerifierProperties = new Hashtable<>();

		for (Map.Entry<String, Object> entry : properties.entrySet()) {
			String key = translateKey(authVerifierPropertyName, entry.getKey());

			authVerifierProperties.put(key, entry.getValue());
		}

		_authVerifierRegistration = bundleContext.registerService(
			AuthVerifier.class, authVerifier, authVerifierProperties);
	}

	protected void deactivate() {
		if (_authVerifierRegistration != null) {
			_authVerifierRegistration.unregister();

			_authVerifierRegistration = null;
		}
	}

	protected abstract AuthVerifier getAuthVerifierInstance();

	protected void modified(
		BundleContext bundleContext, Map<String, Object> properties) {

		deactivate();

		activate(bundleContext, properties);
	}

	protected String translateKey(String authVerifierPropertyName, String key) {
		if (key.equals("hostsAllowed")) {
			key = "hosts.allowed";
		}
		else if (key.equals("urlsExcludes")) {
			key = "urls.excludes";
		}
		else if (key.equals("urlsIncludes")) {
			key = "urls.includes";
		}

		return authVerifierPropertyName + key;
	}

	private ServiceRegistration<AuthVerifier> _authVerifierRegistration;

}