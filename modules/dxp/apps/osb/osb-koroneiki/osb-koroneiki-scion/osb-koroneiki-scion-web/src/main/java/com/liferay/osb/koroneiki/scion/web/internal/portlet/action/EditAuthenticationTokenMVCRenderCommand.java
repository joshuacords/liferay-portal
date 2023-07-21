/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.scion.web.internal.portlet.action;

import com.liferay.osb.koroneiki.scion.constants.ScionPortletKeys;
import com.liferay.osb.koroneiki.scion.constants.ScionWebKeys;
import com.liferay.osb.koroneiki.scion.service.AuthenticationTokenLocalService;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PwdGenerator;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	property = {
		"javax.portlet.name=" + ScionPortletKeys.AUTHENTICATION_TOKEN_MANAGER,
		"mvc.command.name=/authentication_token_manager/edit_authentication_token"
	},
	service = MVCRenderCommand.class
)
public class EditAuthenticationTokenMVCRenderCommand
	implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		try {
			return doRender(renderRequest);
		}
		catch (Exception exception) {
			SessionErrors.add(renderRequest, exception.getClass());

			return "/authentication_token_manager/error.jsp";
		}
	}

	protected String doRender(RenderRequest renderRequest) throws Exception {
		long authenticationTokenId = ParamUtil.getLong(
			renderRequest, "authenticationTokenId");

		if (authenticationTokenId > 0) {
			renderRequest.setAttribute(
				ScionWebKeys.AUTHENTICATION_TOKEN,
				_authenticationTokenLocalService.getAuthenticationToken(
					authenticationTokenId));
		}
		else {
			String token = PwdGenerator.getPassword(
				64, PwdGenerator.KEY1, PwdGenerator.KEY2, PwdGenerator.KEY3);

			renderRequest.setAttribute(ScionWebKeys.TOKEN, token);
		}

		return "/authentication_token_manager/edit_authentication_token.jsp";
	}

	@Reference
	private AuthenticationTokenLocalService _authenticationTokenLocalService;

}