<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

Organization organization = (Organization)row.getObject();

long organizationId = organization.getOrganizationId();
%>

<liferay-ui:icon-menu
	direction="left-side"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
	showWhenSingleIcon="<%= true %>"
>
	<portlet:renderURL var="viewUsersURL">
		<portlet:param name="mvcRenderCommandName" value="/directory/view" />
		<portlet:param name="tabs1" value="users" />
		<portlet:param name="viewUsersRedirect" value="<%= currentURL %>" />
		<portlet:param name="organizationId" value="<%= String.valueOf(organizationId) %>" />
	</portlet:renderURL>

	<liferay-ui:icon
		message="view-users"
		method="get"
		url="<%= viewUsersURL %>"
	/>

	<c:if test="<%= organization.hasSuborganizations() %>">
		<portlet:renderURL var="viewSuborganizationsURL">
			<portlet:param name="mvcRenderCommandName" value="/directory/view" />
			<portlet:param name="tabs1" value="organizations" />
			<portlet:param name="viewUsersRedirect" value="<%= currentURL %>" />
			<portlet:param name="parentOrganizationId" value="<%= String.valueOf(organizationId) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			message="view-suborganizations"
			method="get"
			url="<%= viewSuborganizationsURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>