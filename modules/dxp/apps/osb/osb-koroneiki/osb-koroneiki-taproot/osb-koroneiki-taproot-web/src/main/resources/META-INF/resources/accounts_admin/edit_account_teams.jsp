<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
Account koroneikiAccount = (Account)request.getAttribute(TaprootWebKeys.ACCOUNT);

renderResponse.setTitle(koroneikiAccount.getName());
%>

<liferay-util:include page="/accounts_admin/edit_account_tabs.jsp" servletContext="<%= application %>" />

<div class="container-fluid-1280">
	<liferay-ui:search-container
		emptyResultsMessage="no-teams-were-found"
		headerNames="name,type"
		iteratorURL="<%= currentURLObj %>"
		total="<%= TeamLocalServiceUtil.getAccountTeamsCount(koroneikiAccount.getAccountId()) %>"
	>
		<liferay-ui:search-container-results
			results="<%= TeamLocalServiceUtil.getAccountTeams(koroneikiAccount.getAccountId(), searchContainer.getStart(), searchContainer.getEnd()) %>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.osb.koroneiki.taproot.model.Team"
			escapedModel="<%= true %>"
			keyProperty="teamId"
			modelVar="team"
		>
			<liferay-portlet:renderURL portletName="<%= TaprootPortletKeys.TEAMS_ADMIN %>" var="rowURL">
				<portlet:param name="mvcRenderCommandName" value="/teams_admin/edit_team" />
				<portlet:param name="teamId" value="<%= String.valueOf(team.getTeamId()) %>" />
			</liferay-portlet:renderURL>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="name"
				value="<%= team.getName() %>"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>