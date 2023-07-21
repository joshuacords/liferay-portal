<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "devices");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("tabs1", tabs1);
%>

<aui:nav-bar markupView="lexicon">
	<aui:nav cssClass="navbar-nav">

		<%
		PortletURL devicesURL = renderResponse.createRenderURL();

		devicesURL.setParameter("tabs1", "devices");
		%>

		<aui:nav-item href="<%= devicesURL.toString() %>" label="devices" selected='<%= tabs1.equals("devices") %>' />

		<%
		PortletURL testURL = renderResponse.createRenderURL();

		testURL.setParameter("tabs1", "test");
		%>

		<aui:nav-item href="<%= testURL.toString() %>" label="test" selected='<%= tabs1.equals("test") %>' />
	</aui:nav>
</aui:nav-bar>

<div class="container-fluid-1280">
	<c:choose>
		<c:when test='<%= tabs1.equals("test") %>'>
			<%@ include file="/test.jspf" %>
		</c:when>
		<c:otherwise>
			<%@ include file="/devices.jspf" %>
		</c:otherwise>
	</c:choose>
</div>