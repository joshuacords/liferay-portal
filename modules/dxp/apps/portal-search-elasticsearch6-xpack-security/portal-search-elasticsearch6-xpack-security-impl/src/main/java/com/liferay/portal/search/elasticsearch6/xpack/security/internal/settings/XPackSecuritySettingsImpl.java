/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch6.xpack.security.internal.settings;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.search.elasticsearch6.settings.XPackSecuritySettings;
import com.liferay.portal.search.elasticsearch6.xpack.security.internal.configuration.XPackSecurityConfiguration;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

/**
 * @author Bryan Engler
 */
@Component(
	configurationPid = "com.liferay.portal.search.elasticsearch6.xpack.security.internal.configuration.XPackSecurityConfiguration",
	immediate = true, property = "operation.mode=REMOTE",
	service = XPackSecuritySettings.class
)
public class XPackSecuritySettingsImpl implements XPackSecuritySettings {

	@Override
	public boolean requiresXPackSecurity() {
		if (xPackSecurityConfiguration.requiresAuthentication() ||
			xPackSecurityConfiguration.transportSSLEnabled()) {

			return true;
		}

		return false;
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		xPackSecurityConfiguration = ConfigurableUtil.createConfigurable(
			XPackSecurityConfiguration.class, properties);
	}

	protected volatile XPackSecurityConfiguration xPackSecurityConfiguration;

}