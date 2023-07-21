/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.scion.web.internal.portlet;

import com.liferay.osb.koroneiki.scion.constants.ScionPortletKeys;
import com.liferay.osb.koroneiki.scion.constants.ScionWebKeys;
import com.liferay.osb.koroneiki.scion.model.ServiceProducer;
import com.liferay.osb.koroneiki.scion.service.ServiceProducerLocalService;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.css-class-wrapper=koroneiki-authentication-token-manager-portlet",
		"com.liferay.portlet.display-category=category.koroneiki",
		"com.liferay.portlet.render-weight=0",
		"javax.portlet.display-name=Authentication Token Manager",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.template-path=/META-INF/resources/",
		"javax.portlet.name=" + ScionPortletKeys.AUTHENTICATION_TOKEN_MANAGER,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator,guest,power-user,user"
	},
	service = Portlet.class
)
public class AuthenticationTokenManagerPortlet extends MVCPortlet {

	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		ServiceProducer serviceProducer =
			_serviceProducerLocalService.fetchAuthorizedServiceProducer(
				themeDisplay.getUserId());

		if (serviceProducer != null) {
			renderRequest.setAttribute(
				ScionWebKeys.SERVICE_PRODUCER, serviceProducer);

			super.render(renderRequest, renderResponse);
		}
	}

	@Reference
	private ServiceProducerLocalService _serviceProducerLocalService;

}