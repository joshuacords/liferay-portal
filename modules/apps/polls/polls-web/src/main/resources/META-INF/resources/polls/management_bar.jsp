<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/polls/init.jsp" %>

<clay:management-toolbar
	clearResultsURL="<%= pollsDisplayContext.getClearResultsURL() %>"
	creationMenu="<%= pollsDisplayContext.getCreationMenu() %>"
	disabled="<%= pollsDisplayContext.isDisabledManagementBar() %>"
	filterDropdownItems="<%= pollsDisplayContext.getFilterItemsDropdownItems() %>"
	itemsTotal="<%= pollsDisplayContext.getTotalItems() %>"
	namespace="<%= renderResponse.getNamespace() %>"
	searchActionURL="<%= pollsDisplayContext.getSearchActionURL() %>"
	searchContainerId="<%= pollsDisplayContext.getSearchContainerId() %>"
	searchFormName="fm1"
	selectable="<%= false %>"
	sortingOrder="<%= pollsDisplayContext.getOrderByType() %>"
	sortingURL="<%= pollsDisplayContext.getSortingURL() %>"
/>