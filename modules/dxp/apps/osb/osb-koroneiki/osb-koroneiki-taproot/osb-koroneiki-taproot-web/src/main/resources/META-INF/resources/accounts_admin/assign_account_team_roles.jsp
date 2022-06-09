<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */
--%>

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

Account koroneikiAccount = (Account)request.getAttribute(TaprootWebKeys.ACCOUNT);
Team team = (Team)request.getAttribute(TaprootWebKeys.TEAM);

renderResponse.setTitle(koroneikiAccount.getName());
%>

<portlet:actionURL name="/accounts_admin/assign_account_team_roles" var="assignAccountTeamRolesURL" />

<div class="main-content-body">
	<aui:form action="<%= assignAccountTeamRolesURL %>" cssClass="container-fluid-1280" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "submitForm();" %>'>
		<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
		<aui:input name="accountId" type="hidden" value="<%= koroneikiAccount.getAccountId() %>" />
		<aui:input name="teamId" type="hidden" value="<%= team.getTeamId() %>" />
		<aui:input name="addTeamRoleIds" type="hidden" />
		<aui:input name="deleteTeamRoleIds" type="hidden" />

		<h2><liferay-ui:message arguments="<%= team.getName() %>" key="assign-team-roles-for-x" /></h2>

		<%
		List<TeamRole> teamRoles = TeamRoleLocalServiceUtil.getTeamAccountTeamRoles(koroneikiAccount.getAccountId(), team.getTeamId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		%>

		<liferay-ui:search-container
			emptyResultsMessage="no-team-roles-were-found"
			headerNames="name,description"
			iteratorURL="<%= currentURLObj %>"
			total="<%= TeamRoleLocalServiceUtil.getTeamRolesCount(com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.TeamRole.Type.ACCOUNT.toString()) %>"
		>
			<liferay-ui:search-container-results
				results="<%= TeamRoleLocalServiceUtil.getTeamRoles(com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.TeamRole.Type.ACCOUNT.toString(), searchContainer.getStart(), searchContainer.getEnd()) %>"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.osb.koroneiki.taproot.model.TeamRole"
				escapedModel="<%= true %>"
				keyProperty="teamRoleId"
				modelVar="teamRole"
			>
				<liferay-ui:search-container-column-text>
					<aui:input checked="<%= teamRoles.contains(teamRole) %>" label="" name="teamRoleIds" type="checkbox" value="<%= teamRole.getTeamRoleId() %>" />
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					name="name"
					value="<%= teamRole.getName() %>"
				/>

				<liferay-ui:search-container-column-text
					name="description"
					value="<%= teamRole.getDescription() %>"
				/>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator
				markupView="lexicon"
			/>
		</liferay-ui:search-container>

		<aui:button-row>
			<aui:button type="submit" />

			<aui:button href="<%= redirect %>" type="cancel" />
		</aui:button-row>
	</aui:form>
</div>

<aui:script>
	function <portlet:namespace />submitForm() {
		var form = document.getElementById('<portlet:namespace />fm');

		var addTeamRoleIdsInput = form.querySelector('#<portlet:namespace />addTeamRoleIds');
		var deleteTeamRoleIdsInput = form.querySelector('#<portlet:namespace />deleteTeamRoleIds');

		addTeamRoleIdsInput.setAttribute('value', Liferay.Util.listCheckedExcept(form));
		deleteTeamRoleIdsInput.setAttribute('value', Liferay.Util.listUncheckedExcept(form));

		form.submit();
	}
</aui:script>