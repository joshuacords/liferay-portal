/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.sso.google.internal.verify;

import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.settings.SettingsFactory;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.security.sso.google.constants.GoogleAuthorizationConfigurationKeys;
import com.liferay.portal.security.sso.google.constants.GoogleConstants;
import com.liferay.portal.security.sso.google.constants.LegacyGoogleLoginPropsKeys;
import com.liferay.portal.verify.BaseCompanySettingsVerifyProcess;
import com.liferay.portal.verify.VerifyProcess;

import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Stian Sigvartsen
 */
@Component(
	immediate = true,
	property = "verify.process.name=com.liferay.portal.security.sso.google",
	service = VerifyProcess.class
)
public class GoogleLoginCompanySettingsVerifyProcess
	extends BaseCompanySettingsVerifyProcess {

	@Override
	protected CompanyLocalService getCompanyLocalService() {
		return _companyLocalService;
	}

	@Override
	protected Set<String> getLegacyPropertyKeys() {
		return SetUtil.fromArray(LegacyGoogleLoginPropsKeys.GOOGLE_LOGIN_KEYS);
	}

	@Override
	protected String[][] getRenamePropertyKeysArray() {
		return new String[][] {
			{
				LegacyGoogleLoginPropsKeys.AUTH_ENABLED,
				GoogleAuthorizationConfigurationKeys.AUTH_ENABLED
			},
			{
				LegacyGoogleLoginPropsKeys.CLIENT_ID,
				GoogleAuthorizationConfigurationKeys.CLIENT_ID
			},
			{
				LegacyGoogleLoginPropsKeys.CLIENT_SECRET,
				GoogleAuthorizationConfigurationKeys.CLIENT_SECRET
			}
		};
	}

	@Override
	protected SettingsFactory getSettingsFactory() {
		return _settingsFactory;
	}

	@Override
	protected String getSettingsId() {
		return GoogleConstants.SERVICE_NAME;
	}

	@Reference
	private CompanyLocalService _companyLocalService;

	@Reference
	private SettingsFactory _settingsFactory;

}