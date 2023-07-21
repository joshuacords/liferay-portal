<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
long siteNavigationMenuItemId = GetterUtil.getLong(request.getAttribute("edit_site_navigation_menu.jsp-siteNavigationMenuItemId"));
%>

<portlet:actionURL copyCurrentRenderParameters="<%= false %>" name="/navigation_menu/delete_site_navigation_menu_item" var="deleteURL">
	<portlet:param name="redirect" value="<%= currentURL %>" />
	<portlet:param name="siteNavigationMenuItemId" value="<%= String.valueOf(siteNavigationMenuItemId) %>" />
</portlet:actionURL>

<liferay-ui:icon
	icon="times-circle"
	linkCssClass="icon-monospaced site-navigation-menu-item__remove-icon text-default"
	markupView="lexicon"
	url="<%= deleteURL %>"
/>