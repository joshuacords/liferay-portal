/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.sso.openid.internal;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.openid.OpenId;
import com.liferay.portal.kernel.settings.CompanyServiceSettingsLocator;
import com.liferay.portal.security.sso.openid.configuration.OpenIdConfiguration;
import com.liferay.portal.security.sso.openid.constants.OpenIdConstants;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * Serves as the core implementation of the OpenID protocol.
 *
 * <p>
 * This class is utilized by many other classes via {@link
 * com.liferay.portal.util.OpenIdUtil} which exposes all of its methods
 * statically.
 * </p>
 *
 * @author Michael C. Han
 */
@Component(
	configurationPid = "com.liferay.portal.security.sso.openid.configuration.OpenIdConfiguration",
	immediate = true, service = OpenId.class
)
public class OpenIdImpl implements OpenId {

	@Override
	public boolean isEnabled(long companyId) {
		try {
			OpenIdConfiguration openIdConfiguration =
				_configurationProvider.getConfiguration(
					OpenIdConfiguration.class,
					new CompanyServiceSettingsLocator(
						companyId, OpenIdConstants.SERVICE_NAME));

			return openIdConfiguration.enabled();
		}
		catch (ConfigurationException configurationException) {
			_log.error(
				"Unable to get OpenId configuration", configurationException);
		}

		return false;
	}

	@Reference(unbind = "-")
	protected void setConfigurationProvider(
		ConfigurationProvider configurationProvider) {

		_configurationProvider = configurationProvider;
	}

	private static final Log _log = LogFactoryUtil.getLog(OpenIdImpl.class);

	private ConfigurationProvider _configurationProvider;

}