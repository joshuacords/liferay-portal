<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

long siteNavigationMenuItemId = ParamUtil.getLong(request, "siteNavigationMenuItemId");

SiteNavigationMenuItem siteNavigationMenuItem = SiteNavigationMenuItemLocalServiceUtil.getSiteNavigationMenuItem(siteNavigationMenuItemId);

SiteNavigationMenuItemType siteNavigationMenuItemType = siteNavigationMenuItemTypeRegistry.getSiteNavigationMenuItemType(siteNavigationMenuItem.getType());
%>

<portlet:actionURL name="/navigation_menu/edit_site_navigation_menu_item" var="editSiteNavigationMenuItemURL" />

<aui:form action="<%= editSiteNavigationMenuItemURL %>">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="siteNavigationMenuId" type="hidden" value="<%= siteNavigationMenuItem.getSiteNavigationMenuId() %>" />
	<aui:input name="siteNavigationMenuItemId" type="hidden" value="<%= siteNavigationMenuItem.getSiteNavigationMenuItemId() %>" />
	<aui:input name="parentSiteNavigationMenuItemId" type="hidden" value="<%= siteNavigationMenuItem.getParentSiteNavigationMenuItemId() %>" />

	<%
	siteNavigationMenuItemType.renderEditPage(request, PipingServletResponse.createPipingServletResponse(pageContext), siteNavigationMenuItem);
	%>

	<aui:button-row>
		<aui:button cssClass="btn-block" type="submit" />
	</aui:button-row>
</aui:form>