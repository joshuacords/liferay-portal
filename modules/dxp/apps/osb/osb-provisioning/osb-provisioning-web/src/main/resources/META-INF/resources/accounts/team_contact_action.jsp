<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

ContactDisplay contactDisplay = (ContactDisplay)row.getObject();

ViewTeamDisplayContext viewTeamDisplayContext = ProvisioningWebComponentProvider.getViewTeamDisplayContext(renderRequest, renderResponse, request);

TeamDisplay teamDisplay = viewTeamDisplayContext.getTeamDisplay();
%>

<liferay-ui:icon-menu
	direction="right"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
>
	<c:if test="<%= !teamDisplay.isSystem() && viewTeamDisplayContext.hasManageAccountsPermission() %>">
		<portlet:actionURL name="/accounts/edit_team" var="unassignURL">
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="teamKey" value="<%= teamDisplay.getKey() %>" />
			<portlet:param name="deleteEmailAddresses" value="<%= contactDisplay.getEmailAddress() %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			confirmation="are-you-sure-you-want-to-unassign-this-contact"
			label="<%= false %>"
			message="unassign"
			showIcon="<%= true %>"
			url="<%= unassignURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>