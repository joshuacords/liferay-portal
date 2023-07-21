/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.web.internal.portlet.action;

import com.liferay.osb.koroneiki.taproot.constants.TaprootPortletKeys;
import com.liferay.osb.koroneiki.taproot.constants.TaprootWebKeys;
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
		"mvc.command.name=/teams_admin/edit_team"
	},
	service = MVCRenderCommand.class
)
public class EditTeamMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		try {
			long teamId = ParamUtil.getLong(renderRequest, "teamId");

			if (teamId > 0) {
				renderRequest.setAttribute(
					TaprootWebKeys.TEAM, _teamLocalService.getTeam(teamId));
			}

			String tabs1 = ParamUtil.getString(renderRequest, "tabs1");

			if (tabs1.equals("contact-roles")) {
				return "/teams_admin/edit_team_contact_roles.jsp";
			}
			else if (tabs1.equals("external-links")) {
				return "/teams_admin/edit_team_external_links.jsp";
			}

			return "/teams_admin/edit_team.jsp";
		}
		catch (Exception exception) {
			SessionErrors.add(renderRequest, exception.getClass());

			return "/teams_admin/error.jsp";
		}
	}

	@Reference
	private TeamLocalService _teamLocalService;

}