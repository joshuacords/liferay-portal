/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.site.teams.web.internal.display.context;

import com.liferay.frontend.taglib.clay.servlet.taglib.util.NavigationItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.NavigationItemList;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Team;
import com.liferay.portal.kernel.service.TeamLocalServiceUtil;
import com.liferay.portal.kernel.util.ParamUtil;

import java.util.List;
import java.util.Objects;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eudaldo Alonso
 */
public class EditSiteTeamAssignmentsDisplayContext {

	public EditSiteTeamAssignmentsDisplayContext(
		RenderRequest renderRequest, RenderResponse renderResponse,
		HttpServletRequest httpServletRequest) {

		this.renderRequest = renderRequest;
		this.renderResponse = renderResponse;
		request = httpServletRequest;
	}

	public PortletURL getEditTeamAssignmentsURL() {
		PortletURL portletURL = renderResponse.createRenderURL();

		portletURL.setParameter("mvcPath", "/edit_team_assignments.jsp");
		portletURL.setParameter("tabs1", getTabs1());
		portletURL.setParameter("teamId", String.valueOf(getTeamId()));

		return portletURL;
	}

	public List<NavigationItem> getNavigationItems() {
		return new NavigationItemList() {
			{
				add(
					navigationItem -> {
						navigationItem.setActive(
							Objects.equals(getTabs1(), "users"));
						navigationItem.setHref(
							getEditTeamAssignmentsURL(), "tabs1", "users");
						navigationItem.setLabel(
							LanguageUtil.get(request, "users"));
					});

				add(
					navigationItem -> {
						navigationItem.setActive(
							Objects.equals(getTabs1(), "user-groups"));
						navigationItem.setHref(
							getEditTeamAssignmentsURL(), "tabs1",
							"user-groups");
						navigationItem.setLabel(
							LanguageUtil.get(request, "user-groups"));
					});
			}
		};
	}

	public String getTabs1() {
		if (_tabs1 != null) {
			return _tabs1;
		}

		_tabs1 = ParamUtil.getString(request, "tabs1", "users");

		return _tabs1;
	}

	public Team getTeam() {
		if (_team != null) {
			return _team;
		}

		_team = TeamLocalServiceUtil.fetchTeam(getTeamId());

		return _team;
	}

	public long getTeamId() {
		if (_teamId != null) {
			return _teamId;
		}

		_teamId = ParamUtil.getLong(request, "teamId");

		return _teamId;
	}

	public String getTeamName() {
		if (_teamName != null) {
			return _teamName;
		}

		Team team = getTeam();

		_teamName = team.getName();

		return _teamName;
	}

	protected final RenderRequest renderRequest;
	protected final RenderResponse renderResponse;
	protected final HttpServletRequest request;

	private String _tabs1;
	private Team _team;
	private Long _teamId;
	private String _teamName;

}