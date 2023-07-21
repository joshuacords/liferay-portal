<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
LayoutItemSelectorViewDisplayContext layoutItemSelectorViewDisplayContext = (LayoutItemSelectorViewDisplayContext)request.getAttribute(LayoutsItemSelectorWebKeys.LAYOUT_ITEM_SELECTOR_VIEW_DISPLAY_CONTEXT);
%>

<c:if test="<%= layoutItemSelectorViewDisplayContext.isShowBreadcrumb() %>">
	<liferay-site-navigation:breadcrumb
		breadcrumbEntries="<%= layoutItemSelectorViewDisplayContext.getPortletBreadcrumbEntries() %>"
	/>
</c:if>

<liferay-layout:select-layout
	checkDisplayPage="<%= layoutItemSelectorViewDisplayContext.isCheckDisplayPage() %>"
	enableCurrentPage="<%= layoutItemSelectorViewDisplayContext.isEnableCurrentPage() %>"
	followURLOnTitleClick="<%= layoutItemSelectorViewDisplayContext.isFollowURLOnTitleClick() %>"
	itemSelectorSaveEvent="<%= HtmlUtil.escapeJS(layoutItemSelectorViewDisplayContext.getItemSelectedEventName()) %>"
	multiSelection="<%= layoutItemSelectorViewDisplayContext.isMultiSelection() %>"
	namespace="<%= liferayPortletResponse.getNamespace() %>"
	pathThemeImages="<%= themeDisplay.getPathThemeImages() %>"
	privateLayout="<%= layoutItemSelectorViewDisplayContext.isPrivateLayout() %>"
	showHiddenLayouts="<%= layoutItemSelectorViewDisplayContext.isShowHiddenPages() %>"
/>