<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ItemSelectorViewDisplayContext itemSelectorViewDisplayContext = (ItemSelectorViewDisplayContext)request.getAttribute(AssetPublisherWebKeys.ITEM_SELECTOR_DISPLAY_CONTEXT);

GroupSearch groupSearch = itemSelectorViewDisplayContext.getGroupSearch();
%>

<liferay-site:site-browser
	displayStyle="<%= itemSelectorViewDisplayContext.getDisplayStyle() %>"
	eventName="<%= itemSelectorViewDisplayContext.getItemSelectedEventName() %>"
	groups="<%= groupSearch.getResults() %>"
	groupsCount="<%= groupSearch.getTotal() %>"
	portletURL="<%= itemSelectorViewDisplayContext.getPortletURL() %>"
	selectedGroupIds="<%= itemSelectorViewDisplayContext.getSelectedGroupIds() %>"
	showSearch="<%= itemSelectorViewDisplayContext.isShowSearch() %>"
/>