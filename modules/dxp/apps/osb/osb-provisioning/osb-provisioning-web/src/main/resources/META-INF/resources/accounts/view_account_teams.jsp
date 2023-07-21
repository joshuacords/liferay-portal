<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ViewAccountTeamsDisplayContext viewAccountTeamsDisplayContext = ProvisioningWebComponentProvider.getViewAccountTeamsDisplayContext(renderRequest, renderResponse, request);
%>

<div class="details-table table-striped">
	<liferay-ui:error exception="<%= Problem.ProblemException.class %>">

		<%
		Problem.ProblemException problemException = (Problem.ProblemException)errorException;
		%>

		<%= problemException.getMessage() %>
	</liferay-ui:error>

	<liferay-ui:search-container
		id="teams"
		searchContainer="<%= viewAccountTeamsDisplayContext.getSearchContainer() %>"
	>
		<clay:management-toolbar
			clearResultsURL="<%= viewAccountTeamsDisplayContext.getClearResultsURL() %>"
			creationMenu="<%= viewAccountTeamsDisplayContext.getCreationMenu() %>"
			elementClasses="full-width"
			itemsTotal="<%= searchContainer.getTotal() %>"
			searchActionURL="<%= viewAccountTeamsDisplayContext.getCurrentURL() %>"
			searchContainerId="teams"
			selectable="<%= false %>"
			showSearch="<%= true %>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.osb.provisioning.web.internal.display.context.TeamDisplay"
			modelVar="teamDisplay"
		>
			<portlet:renderURL var="rowURL">
				<portlet:param name="mvcRenderCommandName" value="/accounts/view_team" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="teamKey" value="<%= teamDisplay.getKey() %>" />
			</portlet:renderURL>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="name-users"
			>
				<%= HtmlUtil.escape(teamDisplay.getName()) %>

				<div class="secondary-information">

					<%
					List<String> contactNames = teamDisplay.getContactNames();

					for (int i = 0; i < contactNames.size(); i++) {
						String contactName = contactNames.get(i);
					%>

						<%= HtmlUtil.escape(contactName) %><%= ((i + 1) < contactNames.size()) ? ", " : "" %>

					<%
					}
					%>

				</div>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="partner-reseller-si-accounts"
				value="<%= teamDisplay.getPartnerAssignedAccountsCount() %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="first-line-support-accounts"
				value="<%= teamDisplay.getFLSAssignedAccountsCount() %>"
			/>

			<liferay-ui:search-container-column-jsp
				align="right"
				path="/accounts/team_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>