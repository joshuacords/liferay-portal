/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.sso.ntlm.internal.verify;

import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.settings.SettingsFactory;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.security.sso.ntlm.constants.LegacyNtlmPropsKeys;
import com.liferay.portal.security.sso.ntlm.constants.NtlmConfigurationKeys;
import com.liferay.portal.security.sso.ntlm.constants.NtlmConstants;
import com.liferay.portal.verify.BaseCompanySettingsVerifyProcess;
import com.liferay.portal.verify.VerifyProcess;

import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brian Greenwald
 */
@Component(
	immediate = true,
	property = "verify.process.name=com.liferay.portal.security.sso.ntlm",
	service = VerifyProcess.class
)
public class NtlmCompanySettingsVerifyProcess
	extends BaseCompanySettingsVerifyProcess {

	@Override
	protected CompanyLocalService getCompanyLocalService() {
		return _companyLocalService;
	}

	@Override
	protected Set<String> getLegacyPropertyKeys() {
		return SetUtil.fromArray(LegacyNtlmPropsKeys.NTLM_AUTH_KEYS);
	}

	@Override
	protected String[][] getRenamePropertyKeysArray() {
		return new String[][] {
			{
				LegacyNtlmPropsKeys.NTLM_AUTH_DOMAIN,
				NtlmConfigurationKeys.AUTH_DOMAIN
			},
			{
				LegacyNtlmPropsKeys.NTLM_AUTH_DOMAIN_CONTROLLER,
				NtlmConfigurationKeys.AUTH_DOMAIN_CONTROLLER
			},
			{
				LegacyNtlmPropsKeys.NTLM_AUTH_DOMAIN_CONTROLLER_NAME,
				NtlmConfigurationKeys.AUTH_DOMAIN_CONTROLLER_NAME
			},
			{
				LegacyNtlmPropsKeys.NTLM_AUTH_ENABLED,
				NtlmConfigurationKeys.AUTH_ENABLED
			},
			{
				LegacyNtlmPropsKeys.NTLM_AUTH_NEGOTIATE_FLAGS,
				NtlmConfigurationKeys.AUTH_NEGOTIATE_FLAGS
			},
			{
				LegacyNtlmPropsKeys.NTLM_AUTH_SERVICE_ACCOUNT,
				NtlmConfigurationKeys.AUTH_SERVICE_ACCOUNT
			},
			{
				LegacyNtlmPropsKeys.NTLM_AUTH_SERVICE_PASSWORD,
				NtlmConfigurationKeys.AUTH_SERVICE_PASSWORD
			}
		};
	}

	@Override
	protected SettingsFactory getSettingsFactory() {
		return _settingsFactory;
	}

	@Override
	protected String getSettingsId() {
		return NtlmConstants.SERVICE_NAME;
	}

	@Reference(unbind = "-")
	protected void setCompanyLocalService(
		CompanyLocalService companyLocalService) {

		_companyLocalService = companyLocalService;
	}

	@Reference(unbind = "-")
	protected void setSettingsFactory(SettingsFactory settingsFactory) {
		_settingsFactory = settingsFactory;
	}

	private CompanyLocalService _companyLocalService;
	private SettingsFactory _settingsFactory;

}