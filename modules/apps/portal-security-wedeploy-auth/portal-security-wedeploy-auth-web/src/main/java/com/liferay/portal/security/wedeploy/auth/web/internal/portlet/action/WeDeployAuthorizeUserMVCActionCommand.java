/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.wedeploy.auth.web.internal.portlet.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.JSONPortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.security.wedeploy.auth.model.WeDeployAuthApp;
import com.liferay.portal.security.wedeploy.auth.model.WeDeployAuthToken;
import com.liferay.portal.security.wedeploy.auth.service.WeDeployAuthTokenLocalService;
import com.liferay.portal.security.wedeploy.auth.web.internal.constants.WeDeployAuthPortletKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Supritha Sundaram
 */
@Component(
	property = {
		"javax.portlet.name=" + WeDeployAuthPortletKeys.WEDEPLOY_AUTH,
		"mvc.command.name=/wedeploy_auth/authorize_user"
	},
	service = MVCActionCommand.class
)
public class WeDeployAuthorizeUserMVCActionCommand
	extends BaseMVCActionCommand {

	@Override
	public void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		String redirectURI = ParamUtil.getString(actionRequest, "redirectURI");

		try {
			if (cmd.equals("allow")) {
				redirectURI = _http.addParameter(
					redirectURI, "code",
					getWeDeployAuthToken(actionRequest, themeDisplay));
			}
			else if (cmd.equals("deny")) {
				JSONObject jsonObject = JSONUtil.put("error", "access_denied");

				JSONPortletResponseUtil.writeJSON(
					actionRequest, actionResponse, jsonObject);
			}
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			JSONObject jsonObject = JSONUtil.put(
				"error_message",
				LanguageUtil.get(
					themeDisplay.getLocale(),
					"an-error-occurred-while-processing-the-requested-" +
						"resource"));

			JSONPortletResponseUtil.writeJSON(
				actionRequest, actionResponse, jsonObject);
		}

		sendRedirect(actionRequest, actionResponse, redirectURI);
	}

	protected String getWeDeployAuthToken(
			ActionRequest actionRequest, ThemeDisplay themeDisplay)
		throws PortalException {

		String redirectURI = ParamUtil.getString(actionRequest, "redirectURI");
		String clientId = ParamUtil.getString(actionRequest, "clientId");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			WeDeployAuthApp.class.getName(), actionRequest);

		WeDeployAuthToken weDeployAuthRequestToken =
			_weDeployAuthTokenLocalService.addAuthorizationWeDeployAuthToken(
				themeDisplay.getUserId(), redirectURI, clientId,
				serviceContext);

		return weDeployAuthRequestToken.getToken();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		WeDeployAuthorizeUserMVCActionCommand.class);

	@Reference
	private Http _http;

	@Reference
	private WeDeployAuthTokenLocalService _weDeployAuthTokenLocalService;

}