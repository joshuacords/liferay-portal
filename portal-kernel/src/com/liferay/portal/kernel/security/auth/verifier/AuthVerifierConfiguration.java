/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.security.auth.verifier;

import java.util.Properties;

/**
 * @author Tomas Polesovsky
 */
public class AuthVerifierConfiguration {

	public AuthVerifier getAuthVerifier() {
		return _authVerifier;
	}

	public String getAuthVerifierClassName() {
		return _authVerifierClassName;
	}

	public Properties getProperties() {
		return _properties;
	}

	public void setAuthVerifier(AuthVerifier authVerifier) {
		_authVerifier = authVerifier;
	}

	public void setAuthVerifierClassName(String authVerifierClassName) {
		_authVerifierClassName = authVerifierClassName;
	}

	public void setProperties(Properties properties) {
		_properties = properties;
	}

	private AuthVerifier _authVerifier;
	private String _authVerifierClassName;
	private Properties _properties;

}