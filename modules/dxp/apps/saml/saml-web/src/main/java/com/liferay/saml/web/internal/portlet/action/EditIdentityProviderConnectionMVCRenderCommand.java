/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.saml.web.internal.portlet.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.saml.constants.SamlPortletKeys;
import com.liferay.saml.constants.SamlWebKeys;
import com.liferay.saml.persistence.model.SamlSpIdpConnection;
import com.liferay.saml.persistence.service.SamlSpIdpConnectionLocalService;
import com.liferay.saml.runtime.configuration.SamlProviderConfiguration;
import com.liferay.saml.runtime.configuration.SamlProviderConfigurationHelper;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Stian Sigvartsen
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + SamlPortletKeys.SAML_ADMIN,
		"mvc.command.name=/admin/edit_identity_provider_connection"
	},
	service = MVCRenderCommand.class
)
public class EditIdentityProviderConnectionMVCRenderCommand
	implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		long samlSpIdpConnectionId = ParamUtil.getLong(
			renderRequest, "samlSpIdpConnectionId");

		renderRequest.setAttribute(
			SamlProviderConfigurationHelper.class.getName(),
			_samlProviderConfigurationHelper);

		long clockSkew;

		if (samlSpIdpConnectionId > 0) {
			try {
				SamlSpIdpConnection samlSpIdpConnection =
					_samlSpIdpConnectionLocalService.getSamlSpIdpConnection(
						samlSpIdpConnectionId);

				clockSkew = ParamUtil.getLong(
					renderRequest, "clockSkew",
					samlSpIdpConnection.getClockSkew());

				renderRequest.setAttribute(
					SamlWebKeys.SAML_SP_IDP_CONNECTION, samlSpIdpConnection);
			}
			catch (PortalException portalException) {
				throw new PortletException(portalException);
			}
		}
		else {
			SamlProviderConfiguration samlProviderConfiguration =
				_samlProviderConfigurationHelper.getSamlProviderConfiguration();

			clockSkew = ParamUtil.getLong(
				renderRequest, "clockSkew",
				samlProviderConfiguration.clockSkew());
		}

		renderRequest.setAttribute(SamlWebKeys.SAML_CLOCK_SKEW, clockSkew);

		return "/admin/edit_identity_provider_connection.jsp";
	}

	@Reference
	private Portal _portal;

	@Reference
	private SamlProviderConfigurationHelper _samlProviderConfigurationHelper;

	@Reference
	private SamlSpIdpConnectionLocalService _samlSpIdpConnectionLocalService;

}