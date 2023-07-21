/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.web.internal.portlet.action;

import com.liferay.osb.koroneiki.taproot.constants.TaprootPortletKeys;
import com.liferay.osb.koroneiki.taproot.service.ContactTeamRoleService;
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
		"javax.portlet.name=" + TaprootPortletKeys.TEAMS_ADMIN,
		"mvc.command.name=/teams_admin/assign_team_contact_roles"
	},
	service = MVCActionCommand.class
)
public class AssignTeamContactRolesMVCActionCommand
	extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			long contactId = ParamUtil.getLong(actionRequest, "contactId");
			long teamId = ParamUtil.getLong(actionRequest, "teamId");
			long[] addContactRoleIds = ParamUtil.getLongValues(
				actionRequest, "addContactRoleIds");
			long[] deleteContactRoleIds = ParamUtil.getLongValues(
				actionRequest, "deleteContactRoleIds");

			for (long contactRoleId : addContactRoleIds) {
				_contactTeamRoleService.addContactTeamRole(
					contactId, teamId, contactRoleId);
			}

			for (long contactRoleId : deleteContactRoleIds) {
				_contactTeamRoleService.deleteContactTeamRole(
					contactId, teamId, contactRoleId);
			}

			sendRedirect(actionRequest, actionResponse);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw exception;
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AssignTeamContactRolesMVCActionCommand.class);

	@Reference
	private ContactTeamRoleService _contactTeamRoleService;

}