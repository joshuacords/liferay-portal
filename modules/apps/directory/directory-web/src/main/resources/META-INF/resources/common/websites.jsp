<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String className = (String)request.getAttribute("websites.className");
long classPK = (Long)request.getAttribute("websites.classPK");

List<Website> websites = Collections.emptyList();

if (classPK > 0) {
	websites = WebsiteServiceUtil.getWebsites(className, classPK);
}
%>

<c:if test="<%= !websites.isEmpty() %>">
	<h3 class="icon-file"><liferay-ui:message key="websites" /></h3>

	<ul class="property-list">

		<%
		for (Website website : websites) {
			website = website.toEscapedModel();
		%>

			<li class="<%= (website.isPrimary() && !websites.isEmpty()) ? "icon-star" : StringPool.BLANK %>">
				<a href="<%= website.getUrl() %>"><%= website.getUrl() %></a>

				<%= LanguageUtil.get(request, website.getType().getName()) %>
			</li>

		<%
		}
		%>

	</ul>
</c:if>