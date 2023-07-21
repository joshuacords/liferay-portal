/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.sso.cas.internal;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.security.sso.SSO;
import com.liferay.portal.kernel.settings.CompanyServiceSettingsLocator;
import com.liferay.portal.security.sso.cas.configuration.CASConfiguration;
import com.liferay.portal.security.sso.cas.constants.CASConstants;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * Enables the CAS module to participate in significant portal session lifecycle
 * changes.
 *
 * @author Michael C. Han
 */
@Component(
	configurationPid = "com.liferay.portal.security.sso.cas.configuration.CASConfiguration",
	immediate = true, service = SSO.class
)
public class SSOImpl implements SSO {

	@Override
	public String getSessionExpirationRedirectUrl(long companyId) {
		CASConfiguration casConfiguration = _getCASConfiguration(companyId);

		if (casConfiguration.logoutOnSessionExpiration()) {
			return casConfiguration.logoutURL();
		}

		return null;
	}

	@Override
	public String getSignInURL(long companyId, String defaultSigninURL) {
		return defaultSigninURL;
	}

	@Override
	public boolean isLoginRedirectRequired(long companyId) {
		return isCASAuthEnabled(companyId);
	}

	@Override
	public boolean isRedirectRequired(long companyId) {
		return isCASAuthEnabled(companyId);
	}

	@Override
	public boolean isSessionRedirectOnExpire(long companyId) {
		CASConfiguration casConfiguration = _getCASConfiguration(companyId);

		return casConfiguration.logoutOnSessionExpiration();
	}

	protected boolean isCASAuthEnabled(long companyId) {
		CASConfiguration casConfiguration = _getCASConfiguration(companyId);

		return casConfiguration.enabled();
	}

	@Reference(unbind = "-")
	protected void setConfigurationProvider(
		ConfigurationProvider configurationProvider) {

		_configurationProvider = configurationProvider;
	}

	private CASConfiguration _getCASConfiguration(long companyId) {
		try {
			return _configurationProvider.getConfiguration(
				CASConfiguration.class,
				new CompanyServiceSettingsLocator(
					companyId, CASConstants.SERVICE_NAME));
		}
		catch (ConfigurationException configurationException) {
			_log.error(
				"Unable to get CAS configuration", configurationException);
		}

		return null;
	}

	private static final Log _log = LogFactoryUtil.getLog(SSOImpl.class);

	private ConfigurationProvider _configurationProvider;

}