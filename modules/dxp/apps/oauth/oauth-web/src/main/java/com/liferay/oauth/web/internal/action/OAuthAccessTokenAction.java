/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.oauth.web.internal.action;

import com.liferay.oauth.constants.OAuthConstants;
import com.liferay.oauth.util.OAuthAccessor;
import com.liferay.oauth.util.OAuthAccessorConstants;
import com.liferay.oauth.util.OAuthMessage;
import com.liferay.oauth.util.OAuthUtil;
import com.liferay.oauth.util.WebServerUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.struts.BaseStrutsAction;
import com.liferay.portal.kernel.struts.StrutsAction;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.oauth.OAuth;
import net.oauth.OAuthProblemException;

import org.osgi.service.component.annotations.Component;

/**
 * @author Ivica Cardic
 */
@Component(
	immediate = true,
	property = "path=" + OAuthConstants.PUBLIC_PATH_ACCESS_TOKEN,
	service = StrutsAction.class
)
public class OAuthAccessTokenAction extends BaseStrutsAction {

	@Override
	public String execute(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws Exception {

		try {
			OAuthMessage oAuthMessage = OAuthUtil.getOAuthMessage(
				httpServletRequest,
				WebServerUtil.getWebServerURL(
					httpServletRequest.getRequestURL()));

			OAuthAccessor oAuthAccessor = OAuthUtil.getOAuthAccessor(
				oAuthMessage);

			OAuthUtil.validateOAuthMessage(oAuthMessage, oAuthAccessor);

			boolean authorized = GetterUtil.getBoolean(
				oAuthAccessor.getProperty(OAuthAccessorConstants.AUTHORIZED));

			if (!authorized) {
				throw new OAuthProblemException(
					OAuth.Problems.ADDITIONAL_AUTHORIZATION_REQUIRED);
			}

			long userId = (Long)oAuthAccessor.getProperty(
				OAuthAccessorConstants.USER_ID);

			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				httpServletRequest);

			OAuthUtil.generateAccessToken(
				oAuthAccessor, userId, serviceContext);

			httpServletResponse.setContentType(ContentTypes.TEXT_PLAIN);

			OutputStream outputStream = httpServletResponse.getOutputStream();

			OAuthUtil.formEncode(
				oAuthAccessor.getAccessToken(), oAuthAccessor.getTokenSecret(),
				outputStream);

			outputStream.close();
		}
		catch (Exception exception) {
			OAuthUtil.handleException(
				httpServletRequest, httpServletResponse, exception, false);
		}

		return null;
	}

}