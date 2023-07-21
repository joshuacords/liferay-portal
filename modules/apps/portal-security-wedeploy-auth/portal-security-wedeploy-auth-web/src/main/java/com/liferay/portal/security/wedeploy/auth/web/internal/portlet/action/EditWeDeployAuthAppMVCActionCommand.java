/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.wedeploy.auth.web.internal.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.security.wedeploy.auth.model.WeDeployAuthApp;
import com.liferay.portal.security.wedeploy.auth.service.WeDeployAuthAppService;
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
		"javax.portlet.name=" + WeDeployAuthPortletKeys.WEDEPLOY_AUTH_ADMIN,
		"mvc.command.name=/wedeploy_auth_admin/edit_wedeploy_auth_app"
	},
	service = MVCActionCommand.class
)
public class EditWeDeployAuthAppMVCActionCommand extends BaseMVCActionCommand {

	@Override
	public void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		try {
			if (cmd.equals(Constants.ADD)) {
				addWeDeployAuthApp(actionRequest);
			}
			else if (cmd.equals(Constants.DELETE)) {
				deleteWeDeployAuthApp(actionRequest);
			}
		}
		catch (Exception exception) {
			SessionErrors.add(actionRequest, exception.getClass());
		}
	}

	protected void addWeDeployAuthApp(ActionRequest actionRequest)
		throws Exception {

		String name = ParamUtil.getString(actionRequest, "name");
		String redirectURI = ParamUtil.getString(actionRequest, "redirectURI");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			WeDeployAuthApp.class.getName(), actionRequest);

		_weDeployAuthAppService.addWeDeployAuthApp(
			name, redirectURI, serviceContext);
	}

	protected void deleteWeDeployAuthApp(ActionRequest actionRequest)
		throws Exception {

		long weDeployAuthAppId = ParamUtil.getLong(
			actionRequest, "weDeployAuthAppId");

		_weDeployAuthAppService.deleteWeDeployAuthApp(weDeployAuthAppId);
	}

	@Reference
	private WeDeployAuthAppService _weDeployAuthAppService;

}