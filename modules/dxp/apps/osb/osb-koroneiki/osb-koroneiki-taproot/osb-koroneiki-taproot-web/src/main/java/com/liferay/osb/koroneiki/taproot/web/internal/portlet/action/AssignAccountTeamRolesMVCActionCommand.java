/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.web.internal.portlet.action;

import com.liferay.osb.koroneiki.taproot.constants.TaprootPortletKeys;
import com.liferay.osb.koroneiki.taproot.service.TeamAccountRoleService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

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
	service = MVCActionCommand.class
)
public class AssignAccountTeamRolesMVCActionCommand
	extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			long teamId = ParamUtil.getLong(actionRequest, "teamId");
			long accountId = ParamUtil.getLong(actionRequest, "accountId");
			long[] addTeamRoleIds = ParamUtil.getLongValues(
				actionRequest, "addTeamRoleIds");
			long[] deleteTeamRoleIds = ParamUtil.getLongValues(
				actionRequest, "deleteTeamRoleIds");

			for (long teamRoleId : addTeamRoleIds) {
				_teamAccountRoleService.addTeamAccountRole(
					teamId, accountId, teamRoleId);
			}

			for (long teamRoleId : deleteTeamRoleIds) {
				_teamAccountRoleService.deleteTeamAccountRole(
					teamId, accountId, teamRoleId);
			}

			sendRedirect(actionRequest, actionResponse);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw exception;
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AssignAccountTeamRolesMVCActionCommand.class);

	@Reference
	private TeamAccountRoleService _teamAccountRoleService;

}