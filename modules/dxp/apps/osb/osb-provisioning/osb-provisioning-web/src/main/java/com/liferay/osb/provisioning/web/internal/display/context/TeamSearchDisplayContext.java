/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.web.internal.display.context;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Team;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.TeamRole;
import com.liferay.osb.provisioning.koroneiki.constants.EntitlementConstants;
import com.liferay.osb.provisioning.koroneiki.constants.TeamRoleConstants;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.TeamRoleWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.TeamWebService;
import com.liferay.osb.provisioning.search.FilterQuery;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.vulcan.util.TransformUtil;

import java.util.Collections;
import java.util.List;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Kyle Bischof
 */
public class TeamSearchDisplayContext {

	public TeamSearchDisplayContext(
		RenderRequest renderRequest, RenderResponse renderResponse,
		HttpServletRequest httpServletRequest,
		AccountWebService accountWebService,
		TeamRoleWebService teamRoleWebService, TeamWebService teamWebService) {

		_renderRequest = renderRequest;
		_renderResponse = renderResponse;
		_httpServletRequest = httpServletRequest;
		_accountWebService = accountWebService;
		_teamRoleWebService = teamRoleWebService;
		_teamWebService = teamWebService;

		_currentURLObj = PortletURLUtil.getCurrent(
			_renderRequest, _renderResponse);
	}

	public String getClearResultsURL() {
		PortletURL portletURL = _renderResponse.createRenderURL();

		portletURL.setParameter(
			"mvcRenderCommandName", "/accounts/select_team");

		return portletURL.toString();
	}

	public SearchContainer getSearchContainer() throws Exception {
		String keywords = ParamUtil.getString(_renderRequest, "keywords");
		boolean partner = ParamUtil.getBoolean(_renderRequest, "partner");

		SearchContainer searchContainer = new SearchContainer(
			_renderRequest, _currentURLObj, Collections.emptyList(),
			"no-teams-were-found");

		FilterQuery filterQuery = new FilterQuery();

		if (partner) {
			filterQuery.addLambdaEquals(
				true, "accountEntitlements", EntitlementConstants.PARTNER);
		}

		List<Team> teams = _teamWebService.search(
			keywords, filterQuery, searchContainer.getCur(),
			searchContainer.getEnd() - searchContainer.getStart(), "name");

		searchContainer.setResults(
			TransformUtil.transform(
				teams,
				team -> new TeamDisplay(
					_renderRequest, _renderResponse, team,
					_getAssignedAccountsCount(
						team, TeamRoleConstants.NAME_FIRST_LINE_SUPPORT),
					_getAssignedAccountsCount(
						team, TeamRoleConstants.NAME_PARTNER))));

		int count = (int)_teamWebService.searchCount(keywords, filterQuery);

		searchContainer.setTotal(count);

		return searchContainer;
	}

	private long _getAssignedAccountsCount(Team team, String teamRoleName)
		throws Exception {

		TeamRole teamRole = _teamRoleWebService.getTeamRole(
			TeamRole.Type.ACCOUNT.toString(), teamRoleName);

		FilterQuery filterQuery = new FilterQuery();

		filterQuery.addLambdaEquals(
			true, "assignedTeamKeyTeamRoleKeys",
			team.getKey() + "_" + teamRole.getKey());

		return _accountWebService.searchCount(StringPool.BLANK, filterQuery);
	}

	private final AccountWebService _accountWebService;
	private final PortletURL _currentURLObj;
	private final HttpServletRequest _httpServletRequest;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;
	private final TeamRoleWebService _teamRoleWebService;
	private final TeamWebService _teamWebService;

}