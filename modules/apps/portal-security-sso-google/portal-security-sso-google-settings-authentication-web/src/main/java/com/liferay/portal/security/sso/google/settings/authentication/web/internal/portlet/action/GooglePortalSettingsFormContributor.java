/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.sso.google.settings.authentication.web.internal.portlet.action;

import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.sso.google.constants.GoogleConstants;
import com.liferay.portal.security.sso.google.settings.authentication.web.internal.constants.PortalSettingsGoogleConstants;
import com.liferay.portal.settings.portlet.action.PortalSettingsFormContributor;
import com.liferay.portal.settings.portlet.action.PortalSettingsParameterUtil;

import java.util.Optional;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;

/**
 * @author Stian Sigvartsen
 */
@Component(immediate = true, service = PortalSettingsFormContributor.class)
public class GooglePortalSettingsFormContributor
	implements PortalSettingsFormContributor {

	@Override
	public Optional<String> getDeleteMVCActionCommandNameOptional() {
		return Optional.of("/portal_settings/google_delete");
	}

	@Override
	public String getParameterNamespace() {
		return PortalSettingsGoogleConstants.FORM_PARAMETER_NAMESPACE;
	}

	@Override
	public Optional<String> getSaveMVCActionCommandNameOptional() {
		return Optional.of("/portal_settings/google");
	}

	@Override
	public String getSettingsId() {
		return GoogleConstants.SERVICE_NAME;
	}

	@Override
	public void validateForm(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortletException {

		boolean googleEnabled = PortalSettingsParameterUtil.getBoolean(
			actionRequest, this, "enabled");

		if (!googleEnabled) {
			return;
		}

		String googleClientId = PortalSettingsParameterUtil.getString(
			actionRequest, this, "clientId");
		String googleClientSecret = PortalSettingsParameterUtil.getString(
			actionRequest, this, "clientSecret");

		if (Validator.isNull(googleClientId)) {
			SessionErrors.add(actionRequest, "googleClientIdInvalid");
		}

		if (Validator.isNull(googleClientSecret)) {
			SessionErrors.add(actionRequest, "googleClientSecretInvalid");
		}
	}

}