<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ViewAppsManagerManagementToolbarDisplayContext viewAppsManagerManagementToolbarDisplayContext = new ViewAppsManagerManagementToolbarDisplayContext(liferayPortletRequest, liferayPortletResponse, request);

PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, "app-manager"), null);
%>

<portlet:renderURL var="viewURL" />

<clay:navigation-bar
	inverted="<%= true %>"
	navigationItems='<%= appManagerDisplayContext.getNavigationItems(viewURL, "apps") %>'
/>

<clay:management-toolbar
	clearResultsURL="<%= viewAppsManagerManagementToolbarDisplayContext.getClearResultsURL() %>"
	filterDropdownItems="<%= viewAppsManagerManagementToolbarDisplayContext.getFilterDropdownItems() %>"
	filterLabelItems="<%= viewAppsManagerManagementToolbarDisplayContext.getFilterLabelItems() %>"
	itemsTotal="<%= viewAppsManagerManagementToolbarDisplayContext.getItemsTotal() %>"
	searchActionURL="<%= viewAppsManagerManagementToolbarDisplayContext.getSearchActionURL() %>"
	searchContainerId="appDisplays"
	searchFormName="searchFm"
	selectable="<%= false %>"
	showSearch="<%= true %>"
	sortingOrder="<%= viewAppsManagerManagementToolbarDisplayContext.getSortingOrder() %>"
	sortingURL="<%= viewAppsManagerManagementToolbarDisplayContext.getSortingURL() %>"
/>

<div class="container-fluid container-fluid-max-xl">
	<liferay-ui:breadcrumb
		showCurrentGroup="<%= false %>"
		showGuestGroup="<%= false %>"
		showLayout="<%= false %>"
		showParentGroups="<%= false %>"
	/>

	<liferay-ui:search-container
		id="appDisplays"
		searchContainer="<%= viewAppsManagerManagementToolbarDisplayContext.getSearchContainer() %>"
		var="appDisplaySearch"
	>
		<liferay-ui:search-container-row
			className="com.liferay.marketplace.app.manager.web.internal.util.AppDisplay"
			modelVar="appDisplay"
		>
			<%@ include file="/app_display_columns.jspf" %>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			displayStyle="descriptive"
			markupView="lexicon"
			resultRowSplitter="<%= new MarketplaceAppManagerResultRowSplitter() %>"
		/>
	</liferay-ui:search-container>
</div>