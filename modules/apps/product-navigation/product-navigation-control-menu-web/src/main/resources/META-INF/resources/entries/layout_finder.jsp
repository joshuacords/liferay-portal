<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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