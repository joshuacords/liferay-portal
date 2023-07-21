/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.web.internal.portlet.action;

import com.liferay.osb.koroneiki.taproot.constants.TaprootPortletKeys;
import com.liferay.osb.koroneiki.taproot.constants.TaprootWebKeys;
import com.liferay.osb.koroneiki.taproot.service.AccountLocalService;
import com.liferay.osb.koroneiki.taproot.service.TeamLocalService;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	property = {
		"javax.portlet.name=" + TaprootPortletKeys.ACCOUNTS_ADMIN,
		"mvc.command.name=/accounts_admin/assign_account_team_roles"
	},
	service = MVCRenderCommand.class
)
public class AssignAccountTeamRolesMVCRenderCommand
	implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		try {
			long accountId = ParamUtil.getLong(renderRequest, "accountId");
			long teamId = ParamUtil.getLong(renderRequest, "teamId");

			renderRequest.setAttribute(
				TaprootWebKeys.ACCOUNT,
				_accountLocalService.getAccount(accountId));

			renderRequest.setAttribute(
				TaprootWebKeys.TEAM, _teamLocalService.getTeam(teamId));

			return "/accounts_admin/assign_account_team_roles.jsp";
		}
		catch (Exception exception) {
			SessionErrors.add(renderRequest, exception.getClass());

			return "/accounts_admin/error.jsp";
		}
	}

	@Reference
	private AccountLocalService _accountLocalService;

	@Reference
	private TeamLocalService _teamLocalService;

}