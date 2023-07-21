<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
EntitlementDefinitionsDisplayContext entitlementDefinitionsDisplayContext = new EntitlementDefinitionsDisplayContext(renderRequest, renderResponse, request);

ViewEntitlementDefinitionsManagementToolbarDisplayContext viewEntitlementDefinitionsManagementToolbarDisplayContext = new ViewEntitlementDefinitionsManagementToolbarDisplayContext(liferayPortletRequest, liferayPortletResponse, request, entitlementDefinitionsDisplayContext.getSearchContainer());
%>

<clay:navigation-bar
	inverted="<%= true %>"
	navigationItems="<%= viewEntitlementDefinitionsManagementToolbarDisplayContext.getNavigationItems() %>"
/>

<clay:management-toolbar
	displayContext="<%= viewEntitlementDefinitionsManagementToolbarDisplayContext %>"
/>

<div class="container-fluid-1280">
	<liferay-ui:search-container
		searchContainer="<%= entitlementDefinitionsDisplayContext.getSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition"
			escapedModel="<%= true %>"
			keyProperty="entitlementDefinitionId"
			modelVar="entitlementDefinition"
		>
			<portlet:renderURL var="rowURL">
				<portlet:param name="mvcRenderCommandName" value="/entitlement_definitions_admin/edit_entitlement_definition" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="entitlementDefinitionId" value="<%= String.valueOf(entitlementDefinition.getEntitlementDefinitionId()) %>" />
			</portlet:renderURL>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="name"
				value="<%= entitlementDefinition.getName() %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="description"
				value="<%= entitlementDefinition.getDescription() %>"
			/>

			<liferay-ui:search-container-column-status
				href="<%= rowURL %>"
				name="status"
				status="<%= entitlementDefinition.getStatus() %>"
			/>

			<liferay-ui:search-container-column-jsp
				align="right"
				path="/entitlement_definitions_admin/entitlement_definition_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>