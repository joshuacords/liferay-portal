/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.saml.web.internal.portlet.action;

import com.liferay.portal.kernel.struts.StrutsAction;
import com.liferay.saml.runtime.configuration.SamlProviderConfigurationHelper;
import com.liferay.saml.runtime.servlet.profile.SingleLogoutProfile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Mika Koivisto
 */
@Component(
	immediate = true,
	property = {
		"path=/portal/saml/slo", "path=/portal/saml/slo_logout",
		"path=/portal/saml/slo_soap"
	},
	service = StrutsAction.class
)
public class SingleLogoutAction extends BaseSamlStrutsAction {

	@Override
	@Reference(unbind = "-")
	public void setSamlProviderConfigurationHelper(
		SamlProviderConfigurationHelper samlProviderConfigurationHelper) {

		super.setSamlProviderConfigurationHelper(
			samlProviderConfigurationHelper);
	}

	@Override
	protected String doExecute(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws Exception {

		String requestURI = httpServletRequest.getRequestURI();

		if (samlProviderConfigurationHelper.isRoleIdp() &&
			requestURI.endsWith("/slo_logout")) {

			_singleLogoutProfile.processIdpLogout(
				httpServletRequest, httpServletResponse);
		}
		else {
			_singleLogoutProfile.processSingleLogout(
				httpServletRequest, httpServletResponse);
		}

		return null;
	}

	@Reference
	private SingleLogoutProfile _singleLogoutProfile;

}