/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.web.internal.portlet.action;

import com.liferay.osb.koroneiki.taproot.constants.TaprootPortletKeys;
import com.liferay.osb.koroneiki.taproot.exception.NoSuchTeamException;
import com.liferay.osb.koroneiki.taproot.exception.TeamNameException;
import com.liferay.osb.koroneiki.taproot.service.TeamService;
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
		"javax.portlet.name=" + TaprootPortletKeys.TEAMS_ADMIN,
		"mvc.command.name=/teams_admin/edit_team"
	},
	service = MVCActionCommand.class
)
public class EditTeamMVCActionCommand extends BaseMVCActionCommand {

	protected void deleteTeam(ActionRequest actionRequest)
		throws PortalException {

		long teamId = ParamUtil.getLong(actionRequest, "teamId");

		_teamService.deleteTeam(teamId);
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		try {
			if (cmd.equals(Constants.DELETE)) {
				deleteTeam(actionRequest);
			}
			else {
				updateTeam(actionRequest);
			}

			sendRedirect(actionRequest, actionResponse);
		}
		catch (Exception exception) {
			if (exception instanceof NoSuchTeamException ||
				exception instanceof TeamNameException) {

				SessionErrors.add(actionRequest, exception.getClass());

				actionResponse.setRenderParameter(
					"mvcRenderCommandName", "/teams_admin/edit_team");
			}
			else {
				throw exception;
			}
		}
	}

	protected void updateTeam(ActionRequest actionRequest)
		throws PortalException {

		long teamId = ParamUtil.getLong(actionRequest, "teamId");

		String name = ParamUtil.getString(actionRequest, "name");

		if (teamId <= 0) {
			long accountId = ParamUtil.getLong(actionRequest, "accountId");

			_teamService.addTeam(accountId, name);
		}
		else {
			_teamService.updateTeam(teamId, name);
		}
	}

	@Reference
	private TeamService _teamService;

}