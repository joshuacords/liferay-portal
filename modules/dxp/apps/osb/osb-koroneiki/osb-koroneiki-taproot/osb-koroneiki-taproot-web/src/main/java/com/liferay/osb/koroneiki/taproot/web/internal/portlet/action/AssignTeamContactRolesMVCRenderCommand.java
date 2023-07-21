/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.web.internal.portlet.action;

import com.liferay.osb.koroneiki.taproot.constants.TaprootPortletKeys;
import com.liferay.osb.koroneiki.taproot.constants.TaprootWebKeys;
import com.liferay.osb.koroneiki.taproot.service.ContactLocalService;
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
		"javax.portlet.name=" + TaprootPortletKeys.TEAMS_ADMIN,
		"mvc.command.name=/teams_admin/assign_team_contact_roles"
	},
	service = MVCRenderCommand.class
)
public class AssignTeamContactRolesMVCRenderCommand
	implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		try {
			long teamId = ParamUtil.getLong(renderRequest, "teamId");
			long contactId = ParamUtil.getLong(renderRequest, "contactId");

			renderRequest.setAttribute(
				TaprootWebKeys.TEAM, _teamLocalService.getTeam(teamId));

			renderRequest.setAttribute(
				TaprootWebKeys.CONTACT,
				_contactLocalService.getContact(contactId));

			return "/teams_admin/assign_team_contact_roles.jsp";
		}
		catch (Exception exception) {
			SessionErrors.add(renderRequest, exception.getClass());

			return "/teams_admin/error.jsp";
		}
	}

	@Reference
	private ContactLocalService _contactLocalService;

	@Reference
	private TeamLocalService _teamLocalService;

}