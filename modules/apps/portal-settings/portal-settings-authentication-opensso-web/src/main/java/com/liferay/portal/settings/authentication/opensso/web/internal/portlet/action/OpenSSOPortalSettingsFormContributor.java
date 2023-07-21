/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.settings.authentication.opensso.web.internal.portlet.action;

import com.liferay.portal.security.sso.opensso.constants.OpenSSOConstants;
import com.liferay.portal.settings.authentication.opensso.web.internal.constants.PortalSettingsOpenSSOConstants;
import com.liferay.portal.settings.portlet.action.PortalSettingsFormContributor;

import java.util.Optional;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;

/**
 * @author Philip Jones
 */
@Component(immediate = true, service = PortalSettingsFormContributor.class)
public class OpenSSOPortalSettingsFormContributor
	implements PortalSettingsFormContributor {

	@Override
	public Optional<String> getDeleteMVCActionCommandNameOptional() {
		return Optional.of("/portal_settings/opensso_delete");
	}

	@Override
	public String getParameterNamespace() {
		return PortalSettingsOpenSSOConstants.FORM_PARAMETER_NAMESPACE;
	}

	@Override
	public Optional<String> getSaveMVCActionCommandNameOptional() {
		return Optional.of("/portal_settings/opensso");
	}

	@Override
	public String getSettingsId() {
		return OpenSSOConstants.SERVICE_NAME;
	}

	@Override
	public void validateForm(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortletException {
	}

}