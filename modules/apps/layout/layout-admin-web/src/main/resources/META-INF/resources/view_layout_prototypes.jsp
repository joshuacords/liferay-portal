<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<clay:navigation-bar
	inverted="<%= true %>"
	navigationItems="<%= layoutsAdminDisplayContext.getNavigationItems() %>"
/>

<%
LayoutPrototypeManagementToolbarDisplayContext layoutPrototypeManagementToolbarDisplayContext = new LayoutPrototypeManagementToolbarDisplayContext(liferayPortletRequest, liferayPortletResponse, request, layoutPrototypeDisplayContext);
%>

<clay:management-toolbar
	displayContext="<%= layoutPrototypeManagementToolbarDisplayContext %>"
/>

<portlet:actionURL name="/layout_prototype/delete_layout_prototype" var="deleteLayoutPrototypesURL">
	<portlet:param name="redirect" value="<%= currentURL %>" />
</portlet:actionURL>

<aui:form action="<%= deleteLayoutPrototypesURL %>" cssClass="container-fluid-1280" name="fm">
	<liferay-ui:error embed="<%= false %>" exception="<%= RequiredLayoutPrototypeException.class %>" message="you-cannot-delete-page-templates-that-are-used-by-a-page" />

	<liferay-ui:search-container
		searchContainer="<%= layoutPrototypeDisplayContext.getSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.layout.page.template.model.LayoutPageTemplateEntry"
			cssClass="selectable"
			escapedModel="<%= true %>"
			keyProperty="layoutPrototypeId"
			modelVar="layoutPageTemplateEntry"
		>

			<%
			LayoutPrototype layoutPrototype = LayoutPrototypeServiceUtil.getLayoutPrototype(layoutPageTemplateEntry.getLayoutPrototypeId());

			row.setCssClass("entry-card lfr-asset-item");

			Map<String, Object> rowData = new HashMap<>();

			rowData.put("actions", layoutPrototypeManagementToolbarDisplayContext.getAvailableActions(layoutPrototype));

			row.setData(rowData);
			%>

			<liferay-ui:search-container-column-text>
				<clay:vertical-card
					verticalCard="<%= new LayoutPrototypeVerticalCard(layoutPrototype, renderRequest, renderResponse, searchContainer.getRowChecker()) %>"
				/>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			displayStyle="icon"
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</aui:form>

<liferay-frontend:component
	componentId="<%= LayoutAdminWebKeys.LAYOUT_PROTOTYPE_DROPDOWN_DEFAULT_EVENT_HANDLER %>"
	module="js/LayoutPrototypeDropdownDefaultEventHandler.es"
/>

<liferay-frontend:component
	componentId="<%= layoutPrototypeManagementToolbarDisplayContext.getDefaultEventHandler() %>"
	module="js/LayoutPrototypeManagementToolbarDefaultEventHandler.es"
/>