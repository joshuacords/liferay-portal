<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
long userGroupId = ParamUtil.getLong(request, "userGroupId");

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

User user2 = (User)row.getObject();
%>

<portlet:actionURL name="editUserGroupAssignments" var="deleteUserGroupUsersURL">
	<portlet:param name="redirect" value="<%= currentURL %>" />
	<portlet:param name="userGroupId" value="<%= String.valueOf(userGroupId) %>" />
	<portlet:param name="removeUserIds" value="<%= String.valueOf(user2.getUserId()) %>" />
</portlet:actionURL>

<liferay-ui:icon
	icon="minus-circle"
	linkCssClass="table-action-link"
	markupView="lexicon"
	message="remove"
	url="<%= deleteUserGroupUsersURL %>"
/>