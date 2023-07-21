<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
TeamRolesDisplayContext teamRolesDisplayContext = new TeamRolesDisplayContext(renderRequest, renderResponse, request);

ViewTeamRolesManagementToolbarDisplayContext viewTeamRolesManagementToolbarDisplayContext = new ViewTeamRolesManagementToolbarDisplayContext(liferayPortletRequest, liferayPortletResponse, request, teamRolesDisplayContext.getSearchContainer());
%>

<clay:navigation-bar
	inverted="<%= true %>"
	navigationItems="<%= viewTeamRolesManagementToolbarDisplayContext.getNavigationItems() %>"
/>

<clay:management-toolbar
	displayContext="<%= viewTeamRolesManagementToolbarDisplayContext %>"
/>

<div class="container-fluid-1280">
	<liferay-ui:search-container
		searchContainer="<%= teamRolesDisplayContext.getSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.osb.koroneiki.taproot.model.TeamRole"
			escapedModel="<%= true %>"
			keyProperty="teamRoleId"
			modelVar="teamRole"
		>
			<portlet:renderURL var="rowURL">
				<portlet:param name="mvcRenderCommandName" value="/team_roles_admin/edit_team_role" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="teamRoleId" value="<%= String.valueOf(teamRole.getTeamRoleId()) %>" />
			</portlet:renderURL>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="name"
				value="<%= teamRole.getName() %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="type"
				value="<%= teamRole.getType() %>"
			/>

			<liferay-ui:search-container-column-jsp
				align="right"
				path="/team_roles_admin/team_role_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>