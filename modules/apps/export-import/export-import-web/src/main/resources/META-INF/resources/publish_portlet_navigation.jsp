<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String tabs3 = ParamUtil.getString(request, "tabs3", "new-publication-process");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcRenderCommandName", "publishPortlet");
portletURL.setParameter("portletResource", portletResource);
%>

<c:if test="<%= (themeDisplay.getURLPublishToLive() != null) || layout.isTypeControlPanel() %>">
	<aui:nav-bar cssClass="navbar-collapse-absolute" markupView="lexicon">
		<aui:nav cssClass="navbar-nav">

			<%
			portletURL.setParameter("tabs3", "new-publication-process");
			%>

			<aui:nav-item href="<%= portletURL.toString() %>" label="new-publication-process" selected='<%= tabs3.equals("new-publication-process") %>' />

			<%
			Group scopeGroup = themeDisplay.getScopeGroup();
			%>

			<c:if test="<%= !scopeGroup.isStagedRemotely() %>">

				<%
				portletURL.setParameter("tabs3", "copy-from-live");
				%>

				<aui:nav-item href="<%= portletURL.toString() %>" label="copy-from-live" selected='<%= tabs3.equals("copy-from-live") %>' />
			</c:if>

			<%
			portletURL.setParameter("tabs3", "current-and-previous");
			%>

			<aui:nav-item href="<%= portletURL.toString() %>" label="current-and-previous" selected='<%= tabs3.equals("current-and-previous") %>' />
		</aui:nav>
	</aui:nav-bar>
</c:if>