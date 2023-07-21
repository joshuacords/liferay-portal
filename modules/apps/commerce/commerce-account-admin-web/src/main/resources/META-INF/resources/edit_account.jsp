<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceAccountAdminDisplayContext commerceAccountAdminDisplayContext = (CommerceAccountAdminDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceAccount commerceAccount = commerceAccountAdminDisplayContext.getCommerceAccount();

PortletURL portletURL = commerceAccountAdminDisplayContext.getPortletURL();

portletURL.setParameter("mvcRenderCommandName", "editCommerceAccount");

String title = LanguageUtil.get(request, "add-account");

if (commerceAccount != null) {
	title = LanguageUtil.format(request, "edit-x", commerceAccount.getName(), false);
}

renderResponse.setTitle(title);

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(backURL);
%>

<liferay-frontend:screen-navigation
	containerCssClass="col-md-10"
	context="<%= commerceAccount %>"
	fullContainerCssClass="col-md-8 offset-md-2"
	key="<%= CommerceAccountScreenNavigationConstants.SCREEN_NAVIGATION_KEY_COMMERCE_ACCOUNT_GENERAL %>"
	navCssClass="col-md-2"
	portletURL="<%= currentURLObj %>"
/>