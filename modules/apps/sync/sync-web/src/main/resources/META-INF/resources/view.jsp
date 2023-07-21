<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "settings");
%>

<clay:navigation-bar
	inverted="<%= true %>"
	navigationItems='<%=
		new JSPNavigationItemList(pageContext) {
			{
				add(
					navigationItem -> {
						navigationItem.setActive(tabs1.equals("settings"));
						navigationItem.setHref(renderResponse.createRenderURL(), "tabs1", "settings");
						navigationItem.setLabel(LanguageUtil.get(request, "settings"));
					});

				add(
					navigationItem -> {
						navigationItem.setActive(tabs1.equals("sites"));
						navigationItem.setHref(renderResponse.createRenderURL(), "tabs1", "sites");
						navigationItem.setLabel(LanguageUtil.get(request, "sites"));
					});

				add(
					navigationItem -> {
						navigationItem.setActive(tabs1.equals("devices"));
						navigationItem.setHref(renderResponse.createRenderURL(), "tabs1", "devices");
						navigationItem.setLabel(LanguageUtil.get(request, "devices"));
					});
			}
		}
	%>'
/>

<c:choose>
	<c:when test='<%= tabs1.equals("settings") %>'>
		<liferay-util:include page="/settings.jsp" servletContext="<%= application %>" />
	</c:when>
	<c:when test='<%= tabs1.equals("sites") %>'>
		<liferay-util:include page="/sites.jsp" servletContext="<%= application %>" />
	</c:when>
	<c:otherwise>
		<liferay-util:include page="/devices.jsp" servletContext="<%= application %>" />
	</c:otherwise>
</c:choose>