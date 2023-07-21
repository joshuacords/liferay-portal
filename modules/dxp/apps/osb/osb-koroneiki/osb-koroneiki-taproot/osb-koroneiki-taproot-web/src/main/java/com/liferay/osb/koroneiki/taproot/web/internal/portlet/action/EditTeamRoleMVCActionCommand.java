/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.web.internal.portlet.action;

import com.liferay.osb.koroneiki.taproot.constants.TaprootPortletKeys;
import com.liferay.osb.koroneiki.taproot.exception.NoSuchTeamRoleException;
import com.liferay.osb.koroneiki.taproot.exception.TeamRoleNameException;
import com.liferay.osb.koroneiki.taproot.exception.TeamRoleTypeException;
import com.liferay.osb.koroneiki.taproot.service.TeamRoleService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Constants;
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
		"javax.portlet.name=" + TaprootPortletKeys.TEAM_ROLES_ADMIN,
		"mvc.command.name=/team_roles_admin/edit_team_role"
	},
	service = MVCActionCommand.class
)
public class EditTeamRoleMVCActionCommand extends BaseMVCActionCommand {

	protected void deleteTeamRole(ActionRequest actionRequest)
		throws PortalException {

		long teamRoleId = ParamUtil.getLong(actionRequest, "teamRoleId");

		_teamRoleService.deleteTeamRole(teamRoleId);
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		try {
			if (cmd.equals(Constants.DELETE)) {
				deleteTeamRole(actionRequest);
			}
			else {
				updateTeamRole(actionRequest);
			}

			sendRedirect(actionRequest, actionResponse);
		}
		catch (Exception exception) {
			if (exception instanceof NoSuchTeamRoleException ||
				exception instanceof TeamRoleNameException ||
				exception instanceof TeamRoleTypeException) {

				SessionErrors.add(actionRequest, exception.getClass());

				actionResponse.setRenderParameter(
					"mvcRenderCommandName", "/team_roles_admin/edit_team_role");
			}
			else {
				throw exception;
			}
		}
	}

	protected void updateTeamRole(ActionRequest actionRequest)
		throws PortalException {

		long teamRoleId = ParamUtil.getLong(actionRequest, "teamRoleId");

		String name = ParamUtil.getString(actionRequest, "name");
		String description = ParamUtil.getString(actionRequest, "description");

		if (teamRoleId <= 0) {
			String type = ParamUtil.getString(actionRequest, "type");

			_teamRoleService.addTeamRole(name, description, type);
		}
		else {
			_teamRoleService.updateTeamRole(teamRoleId, name, description);
		}
	}

	@Reference
	private TeamRoleService _teamRoleService;

}