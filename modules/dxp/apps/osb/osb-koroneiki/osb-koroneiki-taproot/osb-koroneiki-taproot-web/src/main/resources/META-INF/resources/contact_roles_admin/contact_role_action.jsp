<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

ContactRole contactRole = (ContactRole)row.getObject();
%>

<liferay-ui:icon-menu
	direction="left-side"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
	showWhenSingleIcon="<%= true %>"
>
	<portlet:renderURL var="editURL">
		<portlet:param name="mvcRenderCommandName" value="/contact_roles_admin/edit_contact_role" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="contactRoleId" value="<%= String.valueOf(contactRole.getContactRoleId()) %>" />
	</portlet:renderURL>

	<liferay-ui:icon
		message="edit"
		url="<%= editURL %>"
	/>

	<liferay-security:permissionsURL
		modelResource="<%= ContactRole.class.getName() %>"
		modelResourceDescription="<%= contactRole.getName() %>"
		resourcePrimKey="<%= String.valueOf(contactRole.getContactRoleId()) %>"
		var="permissionsURL"
		windowState="<%= LiferayWindowState.POP_UP.toString() %>"
	/>

	<liferay-ui:icon
		message="permissions"
		method="get"
		url="<%= permissionsURL %>"
		useDialog="<%= true %>"
	/>

	<c:if test="<%= !contactRole.isSystem() %>">
		<portlet:actionURL name="/contact_roles_admin/edit_contact_role" var="deleteURL">
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="contactRoleId" value="<%= String.valueOf(contactRole.getContactRoleId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			confirmation="are-you-sure-you-want-to-delete-this-contact-role"
			url="<%= deleteURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>