<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceAccountGroupAdminDisplayContext commerceAccountGroupAdminDisplayContext = (CommerceAccountGroupAdminDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceAccountGroup commerceAccountGroup = commerceAccountGroupAdminDisplayContext.getCommerceAccountGroup();

PortletURL portletURL = commerceAccountGroupAdminDisplayContext.getPortletURL();

portletURL.setParameter("mvcRenderCommandName", "editCommerceAccountGroup");

String title = LanguageUtil.get(request, "add-account-group");

if (commerceAccountGroup != null) {
	title = LanguageUtil.format(request, "edit-x", commerceAccountGroup.getName(), false);
}

renderResponse.setTitle(title);

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(backURL);
%>

<liferay-frontend:screen-navigation
	containerCssClass="col-md-10"
	context="<%= commerceAccountGroup %>"
	fullContainerCssClass="col-md-8 offset-md-2"
	key="<%= CommerceAccountGroupScreenNavigationConstants.SCREEN_NAVIGATION_KEY_COMMERCE_ACCOUNT_GROUP_GENERAL %>"
	navCssClass="col-md-2"
	portletURL="<%= currentURLObj %>"
/>