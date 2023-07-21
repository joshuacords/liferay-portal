<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String portletDescription = (String)request.getAttribute(ProductNavigationControlMenuWebKeys.PORTLET_DESCRIPTION);
String portletTitle = (String)request.getAttribute(ProductNavigationControlMenuWebKeys.PORTLET_TITLE);
%>

<li class="control-menu-nav-item control-menu-nav-item-content">
	<span class="control-menu-level-1-heading truncate-text" data-qa-id="headerTitle"><%= HtmlUtil.escape(portletTitle) %></span>

	<c:if test="<%= Validator.isNotNull(portletDescription) %>">
		<liferay-ui:icon-help message="<%= portletDescription %>" />
	</c:if>
</li>