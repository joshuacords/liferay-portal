<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/init.jsp" %>

<%
Map<String, Object> context = new HashMap<>();

context.put("administrationPortletNamespace", PortalUtil.getPortletNamespace(LayoutAdminPortletKeys.GROUP_PAGES));

PortletURL administrationPortletURL = PortalUtil.getControlPanelPortletURL(request, LayoutAdminPortletKeys.GROUP_PAGES, PortletRequest.RENDER_PHASE);

administrationPortletURL.setParameter("redirect", themeDisplay.getURLCurrent());

context.put("administrationPortletURL", administrationPortletURL.toString());

LiferayPortletURL findLayoutsURL = PortletURLFactoryUtil.create(request, ProductNavigationControlMenuPortletKeys.PRODUCT_NAVIGATION_CONTROL_MENU, PortletRequest.RESOURCE_PHASE);

findLayoutsURL.setResourceID("/control_menu/find_layouts");

context.put("findLayoutsURL", findLayoutsURL.toString());

context.put("layouts", JSONFactoryUtil.createJSONArray());
context.put("namespace", PortalUtil.getPortletNamespace(ProductNavigationControlMenuPortletKeys.PRODUCT_NAVIGATION_CONTROL_MENU));
context.put("spritemap", themeDisplay.getPathThemeImages() + "/lexicon/icons.svg");
context.put("totalCount", 0);
%>

<soy:component-renderer
	componentId="layoutFinder"
	context="<%= context %>"
	module="js/LayoutFinder.es"
	templateNamespace="com.liferay.product.navigation.control.menu.web.LayoutFinder.render"
/>