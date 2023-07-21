<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
DisplayPageDisplayContext displayPageDisplayContext = new DisplayPageDisplayContext(renderRequest, renderResponse, request);
%>

<clay:navigation-bar
	inverted="<%= true %>"
	navigationItems="<%= layoutsAdminDisplayContext.getNavigationItems() %>"
/>

<liferay-ui:success key="displayPagePublished" message="the-display-page-template-was-published-succesfully" />

<%
DisplayPageManagementToolbarDisplayContext displayPageManagementToolbarDisplayContext = new DisplayPageManagementToolbarDisplayContext(liferayPortletRequest, liferayPortletResponse, request, displayPageDisplayContext);
%>

<clay:management-toolbar
	displayContext="<%= displayPageManagementToolbarDisplayContext %>"
/>

<portlet:actionURL name="/layout/delete_layout_page_template_entry" var="deleteDisplayPageURL">
	<portlet:param name="redirect" value="<%= currentURL %>" />
</portlet:actionURL>

<aui:form action="<%= deleteDisplayPageURL %>" cssClass="container-fluid-1280" name="fm">
	<liferay-ui:error key="<%= PortalException.class.getName() %>" message="one-or-more-entries-could-not-be-deleted" />
	<liferay-ui:error key="<%= RequiredLayoutPageTemplateEntryException.class.getName() %>" message="you-cannot-delete-asset-display-page-templates-that-are-used-by-one-or-more-assets" />

	<liferay-ui:search-container
		id="displayPages"
		searchContainer="<%= displayPageDisplayContext.getDisplayPagesSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.layout.page.template.model.LayoutPageTemplateEntry"
			keyProperty="layoutPageTemplateEntryId"
			modelVar="layoutPageTemplateEntry"
		>

			<%
			row.setCssClass("entry-card lfr-asset-item " + row.getCssClass());

			Map<String, Object> rowData = new HashMap<>();

			rowData.put("actions", displayPageManagementToolbarDisplayContext.getAvailableActions(layoutPageTemplateEntry));

			row.setData(rowData);
			%>

			<liferay-ui:search-container-column-text>
				<clay:vertical-card
					verticalCard="<%= new DisplayPageVerticalCard(layoutPageTemplateEntry, renderRequest, renderResponse, searchContainer.getRowChecker()) %>"
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
	componentId="<%= LayoutAdminWebKeys.DISPLAY_PAGE_DROPDOWN_DEFAULT_EVENT_HANDLER %>"
	module="js/DisplayPageDropdownDefaultEventHandler.es"
/>

<liferay-frontend:component
	componentId="<%= displayPageManagementToolbarDisplayContext.getDefaultEventHandler() %>"
	module="js/DisplayPageManagementToolbarDefaultEventHandler.es"
/>