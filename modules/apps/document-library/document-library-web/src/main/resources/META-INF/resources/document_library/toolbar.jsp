<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/document_library/init.jsp" %>

<clay:management-toolbar
	actionDropdownItems="<%= dlAdminManagementToolbarDisplayContext.getActionDropdownItems() %>"
	clearResultsURL="<%= dlAdminManagementToolbarDisplayContext.getClearResultsURL() %>"
	componentId="<%= dlAdminManagementToolbarDisplayContext.getComponentId() %>"
	creationMenu="<%= dlAdminManagementToolbarDisplayContext.getCreationMenu() %>"
	defaultEventHandler='<%= renderResponse.getNamespace() + "DocumentLibrary" %>'
	disabled="<%= dlAdminManagementToolbarDisplayContext.isDisabled() %>"
	filterDropdownItems="<%= dlAdminManagementToolbarDisplayContext.getFilterDropdownItems() %>"
	filterLabelItems="<%= dlAdminManagementToolbarDisplayContext.getFilterLabelItems() %>"
	infoPanelId="infoPanelId"
	itemsTotal="<%= dlAdminManagementToolbarDisplayContext.getTotalItems() %>"
	searchActionURL="<%= String.valueOf(dlAdminManagementToolbarDisplayContext.getSearchURL()) %>"
	searchContainerId="entries"
	selectable="<%= dlAdminManagementToolbarDisplayContext.isSelectable() %>"
	showInfoButton="<%= true %>"
	showSearch="<%= dlAdminManagementToolbarDisplayContext.isShowSearch() %>"
	sortingOrder="<%= dlAdminManagementToolbarDisplayContext.getSortingOrder() %>"
	sortingURL="<%= String.valueOf(dlAdminManagementToolbarDisplayContext.getSortingURL()) %>"
	supportsBulkActions="<%= true %>"
	viewTypeItems="<%= dlAdminManagementToolbarDisplayContext.getViewTypes() %>"
/>