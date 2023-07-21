<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

EntitlementDefinition entitlementDefinition = (EntitlementDefinition)row.getObject();
%>

<liferay-ui:icon-menu
	direction="left-side"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
	showWhenSingleIcon="<%= true %>"
>
	<portlet:renderURL var="editURL">
		<portlet:param name="mvcRenderCommandName" value="/entitlement_definitions_admin/edit_entitlement_definition" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="entitlementDefinitionId" value="<%= String.valueOf(entitlementDefinition.getEntitlementDefinitionId()) %>" />
	</portlet:renderURL>

	<liferay-ui:icon
		message="edit"
		url="<%= editURL %>"
	/>

	<liferay-security:permissionsURL
		modelResource="<%= EntitlementDefinition.class.getName() %>"
		modelResourceDescription="<%= entitlementDefinition.getName() %>"
		resourcePrimKey="<%= String.valueOf(entitlementDefinition.getEntitlementDefinitionId()) %>"
		var="permissionsURL"
		windowState="<%= LiferayWindowState.POP_UP.toString() %>"
	/>

	<liferay-ui:icon
		message="permissions"
		method="get"
		url="<%= permissionsURL %>"
		useDialog="<%= true %>"
	/>

	<portlet:actionURL name="/entitlement_definitions_admin/edit_entitlement_definition" var="deleteURL">
		<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="entitlementDefinitionId" value="<%= String.valueOf(entitlementDefinition.getEntitlementDefinitionId()) %>" />
	</portlet:actionURL>

	<liferay-ui:icon-delete
		confirmation="are-you-sure-you-want-to-delete-this-entitlement-definition"
		url="<%= deleteURL %>"
	/>
</liferay-ui:icon-menu>