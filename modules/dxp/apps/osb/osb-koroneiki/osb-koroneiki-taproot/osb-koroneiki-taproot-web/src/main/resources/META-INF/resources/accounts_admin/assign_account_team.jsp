<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

Account koroneikiAccount = (Account)request.getAttribute(TaprootWebKeys.ACCOUNT);
%>

<portlet:actionURL name="/accounts_admin/assign_account_team" var="assignAccountTeamURL">
	<portlet:param name="accountId" value="<%= String.valueOf(koroneikiAccount.getAccountId()) %>" />
	<portlet:param name="redirect" value="<%= currentURL %>" />
</portlet:actionURL>

<aui:form action="<%= assignAccountTeamURL %>" cssClass="container-fluid-1280" method="post" name="fm">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="accountId" type="hidden" value="<%= koroneikiAccount.getAccountId() %>" />

	<aui:fieldset-group>
		<aui:fieldset>
			<aui:select label="team" name="teamId">
				<aui:option value="" />

				<%
				for (Team team : TeamLocalServiceUtil.getTeams(QueryUtil.ALL_POS, QueryUtil.ALL_POS)) {
				%>

					<aui:option label="<%= team.getName() %>" value="<%= team.getTeamId() %>" />

				<%
				}
				%>

			</aui:select>

			<aui:select label="role" name="teamRoleId">
				<aui:option value="" />

				<%
				for (TeamRole teamRole : TeamRoleLocalServiceUtil.getTeamRoles(com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.TeamRole.Type.ACCOUNT.toString(), QueryUtil.ALL_POS, QueryUtil.ALL_POS)) {
				%>

					<aui:option label="<%= teamRole.getName() %>" value="<%= teamRole.getTeamRoleId() %>" />

				<%
				}
				%>

			</aui:select>
		</aui:fieldset>
	</aui:fieldset-group>

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>