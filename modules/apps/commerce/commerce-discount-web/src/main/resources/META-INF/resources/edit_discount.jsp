<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceDiscountDisplayContext commerceDiscountDisplayContext = (CommerceDiscountDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceDiscount commerceDiscount = commerceDiscountDisplayContext.getCommerceDiscount();

PortletURL portletURL = commerceDiscountDisplayContext.getPortletURL();

portletURL.setParameter("mvcRenderCommandName", "editCommerceDiscount");

String title = LanguageUtil.get(request, "add-discount");

if (commerceDiscount != null) {
	title = LanguageUtil.format(request, "edit-x", commerceDiscount.getTitle(), false);
}

Map<String, Object> data = new HashMap<>();

data.put("direction-right", StringPool.TRUE);

PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, "discounts"), String.valueOf(renderResponse.createRenderURL()), data);
PortalUtil.addPortletBreadcrumbEntry(request, title, StringPool.BLANK, data);
%>

<%@ include file="/breadcrumb.jspf" %>

<liferay-frontend:screen-navigation
	containerCssClass="col-md-10"
	context="<%= commerceDiscount %>"
	fullContainerCssClass="col-md-8 offset-md-2"
	key="<%= CommerceDiscountScreenNavigationConstants.SCREEN_NAVIGATION_KEY_COMMERCE_DISCOUNT_GENERAL %>"
	navCssClass="col-md-2"
	portletURL="<%= currentURLObj %>"
/>