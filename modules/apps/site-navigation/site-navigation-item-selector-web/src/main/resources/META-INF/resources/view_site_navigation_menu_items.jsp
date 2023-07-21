<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
SiteNavigationMenuItemItemSelectorViewDisplayContext siteNavigationMenuItemItemSelectorViewDisplayContext = (SiteNavigationMenuItemItemSelectorViewDisplayContext)request.getAttribute(SiteNavigationItemSelectorWebKeys.SITE_NAVIGATION_MENU_ITEM_ITEM_SELECTOR_DISPLAY_CONTEXT);
%>

<c:choose>
	<c:when test="<%= siteNavigationMenuItemItemSelectorViewDisplayContext.isShowSelectSiteNavigationMenuItem() %>">

		<%
		Portlet portlet = PortletLocalServiceUtil.getPortletById(company.getCompanyId(), portletDisplay.getId());
		%>

		<liferay-util:html-top>
			<link href="<%= PortalUtil.getStaticResourceURL(request, PortalUtil.getPathProxy() + application.getContextPath() + "/css/main.css", portlet.getTimestamp()) %>" rel="stylesheet" type="text/css" />
		</liferay-util:html-top>

		<%
		Map<String, Object> context = new HashMap<>();

		context.put("itemSelectorSaveEvent", siteNavigationMenuItemItemSelectorViewDisplayContext.getItemSelectedEventName());
		context.put("namespace", liferayPortletResponse.getNamespace());
		context.put("nodes", siteNavigationMenuItemItemSelectorViewDisplayContext.getSiteNavigationMenuItemsJSONArray());
		context.put("pathThemeImages", themeDisplay.getPathThemeImages());
		%>

		<soy:component-renderer
			context="<%= context %>"
			module="js/SelectSiteNavigationMenuItem.es"
			templateNamespace="com.liferay.site.navigation.item.selector.web.SelectSiteNavigationMenuItem.render"
		/>
	</c:when>
	<c:otherwise>
		<liferay-frontend:empty-result-message
			elementType='<%= LanguageUtil.get(resourceBundle, "navigation-menu-items") %>'
		/>
	</c:otherwise>
</c:choose>