<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

PowwowServer powwowServer = (PowwowServer)row.getObject();
%>

<liferay-ui:icon-menu
	showExpanded="<%= row == null %>"
>
	<portlet:renderURL var="editURL">
		<portlet:param name="mvcPath" value="/admin/edit_server.jsp" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="powwowServerId" value="<%= String.valueOf(powwowServer.getPowwowServerId()) %>" />
	</portlet:renderURL>

	<liferay-ui:icon
		image="edit"
		url="<%= editURL %>"
	/>

	<portlet:actionURL name="deletePowwowServer" var="deleteURL">
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="powwowServerId" value="<%= String.valueOf(powwowServer.getPowwowServerId()) %>" />
	</portlet:actionURL>

	<liferay-ui:icon-delete
		url="<%= deleteURL %>"
	/>
</liferay-ui:icon-menu>