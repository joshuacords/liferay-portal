/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.oauth.web.internal.action;

import com.liferay.oauth.constants.OAuthConstants;
import com.liferay.oauth.constants.OAuthPortletKeys;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.WindowStateFactory;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.struts.BaseStrutsAction;
import com.liferay.portal.kernel.struts.StrutsAction;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import javax.portlet.PortletMode;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.oauth.OAuth;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ivica Cardic
 */
@Component(
	immediate = true, property = "path=" + OAuthConstants.PUBLIC_PATH_AUTHORIZE,
	service = StrutsAction.class
)
public class OAuthAuthorizeAction extends BaseStrutsAction {

	@Override
	public String execute(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws Exception {

		if (!isSignedIn()) {
			return redirectToLogin(httpServletRequest, httpServletResponse);
		}

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		PortletURL portletURL = PortletURLFactoryUtil.create(
			httpServletRequest, OAuthPortletKeys.OAUTH_AUTHORIZE,
			themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);

		portletURL.setParameter("saveLastPath", "0");

		String oauthCallback = httpServletRequest.getParameter(
			OAuth.OAUTH_CALLBACK);

		if (Validator.isNotNull(oauthCallback)) {
			portletURL.setParameter(OAuth.OAUTH_CALLBACK, oauthCallback);
		}

		portletURL.setParameter(
			OAuth.OAUTH_TOKEN,
			httpServletRequest.getParameter(OAuth.OAUTH_TOKEN));
		portletURL.setPortletMode(PortletMode.VIEW);
		portletURL.setWindowState(getWindowState(httpServletRequest));

		String redirect = portletURL.toString();

		httpServletResponse.sendRedirect(redirect);

		return null;
	}

	protected WindowState getWindowState(
		HttpServletRequest httpServletRequest) {

		String windowStateString = ParamUtil.getString(
			httpServletRequest, "windowState");

		if (Validator.isNotNull(windowStateString)) {
			return WindowStateFactory.getWindowState(windowStateString);
		}

		return LiferayWindowState.POP_UP;
	}

	protected boolean isSignedIn() {
		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		if ((permissionChecker == null) || !permissionChecker.isSignedIn()) {
			return false;
		}

		return true;
	}

	protected String redirectToLogin(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		StringBundler sb = new StringBundler(4);

		sb.append(themeDisplay.getPathMain());
		sb.append("/portal/login?redirect=");
		sb.append(_http.encodeURL(httpServletRequest.getRequestURI()));

		String queryString = httpServletRequest.getQueryString();

		if (Validator.isNotNull(queryString)) {
			sb.append(_http.encodeURL(StringPool.QUESTION.concat(queryString)));
		}

		httpServletResponse.sendRedirect(sb.toString());

		return null;
	}

	@Reference
	private Http _http;

}