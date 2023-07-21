/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.oauth.web.internal.portlet;

import com.liferay.oauth.constants.OAuthPortletKeys;
import com.liferay.oauth.service.OAuthUserService;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Igor Beslic
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.add-default-resource=true",
		"com.liferay.portlet.css-class-wrapper=oauth-portlet oauth-portlet-authorizations",
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.use-default-template=true",
		"javax.portlet.display-name=OAuth Authorizations",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.template-path=/authorizations/",
		"javax.portlet.init-param.view-template=/authorizations/view.jsp",
		"javax.portlet.name=" + OAuthPortletKeys.OAUTH_AUTHORIZATIONS,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class AuthorizationsPortlet extends MVCPortlet {

	public void deleteOAuthUser(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long oAuthApplicationId = ParamUtil.getLong(
			actionRequest, "oAuthApplicationId");

		_oAuthUserService.deleteOAuthUser(oAuthApplicationId);
	}

	@Reference(unbind = "-")
	protected void setOAuthUserService(OAuthUserService oAuthUserService) {
		_oAuthUserService = oAuthUserService;
	}

	private OAuthUserService _oAuthUserService;

}