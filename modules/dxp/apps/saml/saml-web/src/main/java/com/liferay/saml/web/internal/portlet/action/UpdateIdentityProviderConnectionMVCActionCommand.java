/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.saml.web.internal.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.saml.constants.SamlPortletKeys;
import com.liferay.saml.persistence.model.SamlSpIdpConnection;
import com.liferay.saml.persistence.service.SamlSpIdpConnectionLocalService;

import java.io.InputStream;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + SamlPortletKeys.SAML_ADMIN,
		"mvc.command.name=/admin/updateIdentityProviderConnection"
	},
	service = MVCActionCommand.class
)
public class UpdateIdentityProviderConnectionMVCActionCommand
	extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UploadPortletRequest uploadPortletRequest =
			_portal.getUploadPortletRequest(actionRequest);

		long samlSpIdpConnectionId = ParamUtil.getLong(
			uploadPortletRequest, "samlSpIdpConnectionId");

		String samlIdpEntityId = ParamUtil.getString(
			uploadPortletRequest, "samlIdpEntityId");
		boolean assertionSignatureRequired = ParamUtil.getBoolean(
			uploadPortletRequest, "assertionSignatureRequired");
		long clockSkew = ParamUtil.getLong(uploadPortletRequest, "clockSkew");
		boolean enabled = ParamUtil.getBoolean(uploadPortletRequest, "enabled");
		boolean forceAuthn = ParamUtil.getBoolean(
			uploadPortletRequest, "forceAuthn");
		boolean ldapImportEnabled = ParamUtil.getBoolean(
			uploadPortletRequest, "ldapImportEnabled");
		String metadataUrl = ParamUtil.getString(
			uploadPortletRequest, "metadataUrl");
		InputStream metadataXmlInputStream =
			uploadPortletRequest.getFileAsStream("metadataXml");
		String name = ParamUtil.getString(uploadPortletRequest, "name");
		String nameIdFormat = ParamUtil.getString(
			uploadPortletRequest, "nameIdFormat");
		boolean signAuthnRequest = ParamUtil.getBoolean(
			uploadPortletRequest, "signAuthnRequest");
		String userAttributeMappings = ParamUtil.getString(
			uploadPortletRequest, "userAttributeMappings");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			SamlSpIdpConnection.class.getName(), actionRequest);

		if (samlSpIdpConnectionId <= 0) {
			_samlSpIdpConnectionLocalService.addSamlSpIdpConnection(
				samlIdpEntityId, assertionSignatureRequired, clockSkew, enabled,
				forceAuthn, ldapImportEnabled, metadataUrl,
				metadataXmlInputStream, name, nameIdFormat, signAuthnRequest,
				userAttributeMappings, serviceContext);
		}
		else {
			_samlSpIdpConnectionLocalService.updateSamlSpIdpConnection(
				samlSpIdpConnectionId, samlIdpEntityId,
				assertionSignatureRequired, clockSkew, enabled, forceAuthn,
				ldapImportEnabled, metadataUrl, metadataXmlInputStream, name,
				nameIdFormat, signAuthnRequest, userAttributeMappings,
				serviceContext);
		}
	}

	@Reference
	private Portal _portal;

	@Reference
	private SamlSpIdpConnectionLocalService _samlSpIdpConnectionLocalService;

}