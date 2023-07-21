/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.sso.google.internal.configuration.definition;

import com.liferay.portal.kernel.settings.definition.ConfigurationPidMapping;
import com.liferay.portal.security.sso.google.configuration.GoogleAuthorizationConfiguration;
import com.liferay.portal.security.sso.google.constants.GoogleConstants;

import org.osgi.service.component.annotations.Component;

/**
 * @author Stian Sigvartsen
 */
@Component(service = ConfigurationPidMapping.class)
public class GoogleAuthorizationCompanyServiceConfigurationPidMapping
	implements ConfigurationPidMapping {

	@Override
	public Class<?> getConfigurationBeanClass() {
		return GoogleAuthorizationConfiguration.class;
	}

	@Override
	public String getConfigurationPid() {
		return GoogleConstants.SERVICE_NAME;
	}

}