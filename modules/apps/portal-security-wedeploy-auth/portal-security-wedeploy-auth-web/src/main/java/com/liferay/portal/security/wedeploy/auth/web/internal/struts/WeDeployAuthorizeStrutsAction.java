/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.wedeploy.auth.web.internal.struts;

import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.struts.StrutsAction;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.security.wedeploy.auth.web.internal.constants.WeDeployAuthPortletKeys;

import javax.portlet.PortletMode;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Supritha Sundaram
 */
@Component(
	immediate = true, property = "path=/portal/wedeploy/authorize",
	service = StrutsAction.class
)
public class WeDeployAuthorizeStrutsAction implements StrutsAction {

	@Override
	public String execute(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws Exception {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		if (!themeDisplay.isSignedIn()) {
			sendLoginRedirect(
				httpServletRequest, httpServletResponse,
				themeDisplay.getPlid());

			return null;
		}

		String redirectURI = ParamUtil.getString(
			httpServletRequest, "redirect_uri");
		String clientId = ParamUtil.getString(httpServletRequest, "client_id");

		PortletURL portletURL = PortletURLFactoryUtil.create(
			httpServletRequest, WeDeployAuthPortletKeys.WEDEPLOY_AUTH,
			themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);

		portletURL.setParameter("redirectURI", redirectURI);
		portletURL.setParameter("clientId", clientId);
		portletURL.setParameter("saveLastPath", Boolean.FALSE.toString());
		portletURL.setPortletMode(PortletMode.VIEW);

		httpServletResponse.sendRedirect(portletURL.toString());

		return null;
	}

	protected void sendLoginRedirect(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, long plid)
		throws Exception {

		PortletURL portletURL = PortletURLFactoryUtil.create(
			httpServletRequest, PortletKeys.LOGIN, plid,
			PortletRequest.RENDER_PHASE);

		portletURL.setParameter("saveLastPath", Boolean.FALSE.toString());
		portletURL.setParameter("mvcRenderCommandName", "/login/login");
		portletURL.setPortletMode(PortletMode.VIEW);
		portletURL.setWindowState(LiferayWindowState.MAXIMIZED);

		httpServletResponse.sendRedirect(portletURL.toString());
	}

}