/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.settings.authentication.cas.web.internal.portlet.action;

import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.sso.cas.constants.CASConstants;
import com.liferay.portal.settings.authentication.cas.web.internal.constants.PortalSettingsCASConstants;
import com.liferay.portal.settings.portlet.action.PortalSettingsFormContributor;
import com.liferay.portal.settings.portlet.action.PortalSettingsParameterUtil;

import java.util.Optional;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Tomas Polesovsky
 */
@Component(immediate = true, service = PortalSettingsFormContributor.class)
public class CASPortalSettingsFormContributor
	implements PortalSettingsFormContributor {

	@Override
	public Optional<String> getDeleteMVCActionCommandNameOptional() {
		return Optional.of("/portal_settings/cas_delete");
	}

	@Override
	public String getParameterNamespace() {
		return PortalSettingsCASConstants.FORM_PARAMETER_NAMESPACE;
	}

	@Override
	public Optional<String> getSaveMVCActionCommandNameOptional() {
		return Optional.of("/portal_settings/cas");
	}

	@Override
	public String getSettingsId() {
		return CASConstants.SERVICE_NAME;
	}

	@Override
	public void validateForm(
		ActionRequest actionRequest, ActionResponse actionResponse) {

		boolean casEnabled = PortalSettingsParameterUtil.getBoolean(
			actionRequest, this, "enabled");

		if (!casEnabled) {
			return;
		}

		String casLoginURL = PortalSettingsParameterUtil.getString(
			actionRequest, this, "loginURL");
		String casLogoutURL = PortalSettingsParameterUtil.getString(
			actionRequest, this, "logoutURL");
		String casServerName = PortalSettingsParameterUtil.getString(
			actionRequest, this, "serverName");
		String casServerURL = PortalSettingsParameterUtil.getString(
			actionRequest, this, "serverURL");
		String casServiceURL = PortalSettingsParameterUtil.getString(
			actionRequest, this, "serviceURL");
		String casNoSuchUserRedirectURL = PortalSettingsParameterUtil.getString(
			actionRequest, this, "noSuchUserRedirectURL");

		if (!Validator.isUrl(casLoginURL)) {
			SessionErrors.add(actionRequest, "casLoginURLInvalid");
		}

		if (!Validator.isUrl(casLogoutURL)) {
			SessionErrors.add(actionRequest, "casLogoutURLInvalid");
		}

		if (Validator.isNull(casServerName)) {
			SessionErrors.add(actionRequest, "casServerNameInvalid");
		}

		if (!Validator.isUrl(casServerURL)) {
			SessionErrors.add(actionRequest, "casServerURLInvalid");
		}

		if (Validator.isNotNull(casServiceURL) &&
			!Validator.isUrl(casServiceURL)) {

			SessionErrors.add(actionRequest, "casServiceURLInvalid");
		}

		if (Validator.isNotNull(casNoSuchUserRedirectURL) &&
			!Validator.isUrl(casNoSuchUserRedirectURL)) {

			SessionErrors.add(actionRequest, "casNoSuchUserURLInvalid");
		}
	}

}