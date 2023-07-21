<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommercePriceListDisplayContext commercePriceListDisplayContext = (CommercePriceListDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommercePriceList commercePriceList = commercePriceListDisplayContext.getCommercePriceList();

PortletURL portletURL = commercePriceListDisplayContext.getPortletURL();

String title = LanguageUtil.get(request, "add-price-list");

if (commercePriceList != null) {
	title = commercePriceList.getName();
}

Map<String, Object> data = new HashMap<>();

data.put("direction-right", StringPool.TRUE);

PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, "price-lists"), priceListsURL, data);
PortalUtil.addPortletBreadcrumbEntry(request, title, portletURL.toString(), data);
PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, commercePriceListDisplayContext.getSelectedScreenNavigationCategoryKey()), StringPool.BLANK, data);

renderResponse.setTitle(LanguageUtil.get(request, "price-lists"));
%>

<%@ include file="/breadcrumb.jspf" %>

<liferay-frontend:screen-navigation
	containerCssClass="col-md-10"
	key="<%= CommercePriceListScreenNavigationConstants.SCREEN_NAVIGATION_KEY_COMMERCE_PRICE_LIST_GENERAL %>"
	modelBean="<%= commercePriceList %>"
	navCssClass="col-md-2"
	portletURL="<%= currentURLObj %>"
/>