/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.powwow.admin.portlet;

import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.powwow.model.PowwowServer;
import com.liferay.powwow.service.PowwowServerLocalServiceUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * @author Shinn Lok
 */
public class AdminPortlet extends MVCPortlet {

	public void deletePowwowServer(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long powwowServerId = ParamUtil.getLong(
			actionRequest, "powwowServerId");

		PowwowServerLocalServiceUtil.deletePowwowServer(powwowServerId);
	}

	public void updatePowwowServer(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long powwowServerId = ParamUtil.getLong(
			actionRequest, "powwowServerId");

		String name = ParamUtil.getString(actionRequest, "name");
		String providerType = ParamUtil.getString(
			actionRequest, "providerType");
		String url = ParamUtil.getString(actionRequest, "url");
		String apiKey = ParamUtil.getString(actionRequest, "apiKey");
		String secret = ParamUtil.getString(actionRequest, "secret");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			PowwowServer.class.getName(), actionRequest);

		if (powwowServerId <= 0) {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			PowwowServerLocalServiceUtil.addPowwowServer(
				themeDisplay.getUserId(), name, providerType, url, apiKey,
				secret, serviceContext);
		}
		else {
			PowwowServerLocalServiceUtil.updatePowwowServer(
				powwowServerId, name, providerType, url, apiKey, secret,
				serviceContext);
		}
	}

}