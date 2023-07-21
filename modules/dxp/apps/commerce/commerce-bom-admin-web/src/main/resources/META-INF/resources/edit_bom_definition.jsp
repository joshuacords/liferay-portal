<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceBOMAdminDisplayContext commerceBOMAdminDisplayContext = (CommerceBOMAdminDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);
%>

<clay:navigation-bar
	inverted="<%= true %>"
	navigationItems="<%= CPNavigationItemRegistryUtil.getNavigationItems(renderRequest) %>"
/>

<liferay-frontend:screen-navigation
	containerCssClass="col-md-10"
	key="<%= CommerceBOMDefinitionScreenNavigationConstants.SCREEN_NAVIGATION_KEY %>"
	modelBean="<%= commerceBOMAdminDisplayContext.getCommerceBOMDefinition() %>"
	navCssClass="col-md-2"
	portletURL="<%= currentURLObj %>"
/>