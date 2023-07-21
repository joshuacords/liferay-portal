<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
Team team = (Team)request.getAttribute(TaprootWebKeys.TEAM);

Contact koroneikiContact = (Contact)row.getObject();
%>

<c:if test="<%= !team.isSystem() %>">
	<liferay-ui:icon-menu
		direction="left-side"
		icon="<%= StringPool.BLANK %>"
		markupView="lexicon"
		message="<%= StringPool.BLANK %>"
		showWhenSingleIcon="<%= true %>"
	>
		<portlet:renderURL var="assignURL">
			<portlet:param name="mvcRenderCommandName" value="/teams_admin/assign_team_contact_roles" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="teamId" value="<%= String.valueOf(team.getTeamId()) %>" />
			<portlet:param name="contactId" value="<%= String.valueOf(koroneikiContact.getContactId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			message="assign-contact-roles"
			url="<%= assignURL %>"
		/>

		<portlet:actionURL name="/teams_admin/unassign_team_contact" var="deleteURL">
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="teamId" value="<%= String.valueOf(team.getTeamId()) %>" />
			<portlet:param name="contactId" value="<%= String.valueOf(koroneikiContact.getContactId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			confirmation="are-you-sure-you-want-to-unassign-this-contact"
			message="remove-membership"
			url="<%= deleteURL %>"
		/>
	</liferay-ui:icon-menu>
</c:if>