/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.oauth2.provider.shortcut.internal.instance.lifecycle;

import com.liferay.oauth2.provider.constants.ClientProfile;
import com.liferay.oauth2.provider.constants.GrantType;
import com.liferay.oauth2.provider.model.OAuth2Application;
import com.liferay.oauth2.provider.service.OAuth2ApplicationLocalService;
import com.liferay.oauth2.provider.service.OAuth2ApplicationScopeAliasesLocalService;
import com.liferay.oauth2.provider.service.OAuth2ScopeGrantLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.instance.lifecycle.BasePortalInstanceLifecycleListener;
import com.liferay.portal.instance.lifecycle.PortalInstanceLifecycleListener;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pavel Savinov
 */
@Component(
	immediate = true,
	property = {
		"applicationName=Fragment Renderer", "clientId=FragmentRenderer"
	},
	service = PortalInstanceLifecycleListener.class
)
public class FragmentRendererPortalInstanceLifecycleListener
	extends BasePortalInstanceLifecycleListener {

	@Override
	public void portalInstanceRegistered(Company company) throws Exception {
		OAuth2Application oAuth2Application =
			_oAuth2ApplicationLocalService.fetchOAuth2Application(
				company.getCompanyId(), _clientId);

		if (oAuth2Application != null) {
			return;
		}

		User user = _userLocalService.getDefaultUser(company.getCompanyId());

		_oAuth2ApplicationLocalService.addOAuth2Application(
			company.getCompanyId(), user.getUserId(), user.getScreenName(),
			new ArrayList<GrantType>() {
				{
					add(GrantType.REFRESH_TOKEN);
					add(GrantType.RESOURCE_OWNER_PASSWORD);
				}
			},
			user.getUserId(), _clientId, ClientProfile.NATIVE_APPLICATION.id(),
			StringPool.BLANK, null, null, null, 0, _applicationName, null,
			Collections.emptyList(),
			builder -> builder.forApplication(
				"liferay-json-web-services",
				"com.liferay.oauth2.provider.jsonws",
				scopeAssigner -> scopeAssigner.assignScope(
					"everything.read", "everything.write"
				).mapToScopeAlias(
					"liferay-json-web-services.everything.read",
					"liferay-json-web-services.everything.write"
				)),
			new ServiceContext());
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		_applicationName = GetterUtil.getString(
			properties.get("applicationName"));
		_clientId = GetterUtil.getString(properties.get("clientId"));
	}

	private String _applicationName = "Fragment Renderer";
	private String _clientId = "FragmentRenderer";

	@Reference
	private OAuth2ApplicationLocalService _oAuth2ApplicationLocalService;

	@Reference
	private OAuth2ApplicationScopeAliasesLocalService
		_oAuth2ApplicationScopeAliasesLocalService;

	@Reference
	private OAuth2ScopeGrantLocalService _oAuth2ScopeGrantLocalService;

	@Reference
	private UserLocalService _userLocalService;

}