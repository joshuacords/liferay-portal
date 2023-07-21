<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<div class="authorize">
	<img src="<%= PortalUtil.getPathContext(request) %>/images/logo.svg" />

	<p>
		<liferay-ui:message key="liferay-marketplace-is-an-integral-part-of-the-liferay-platform-experience-for-all-users" />
	</p>

	<%
	Map<String, Object> data = new HashMap<String, Object>();

	data.put("senna-off", "true");
	%>

	<liferay-portlet:renderURL var="callbackURL" />

	<liferay-portlet:actionURL name="authorize" var="authorizeURL">
		<portlet:param name="callbackURL" value="<%= callbackURL %>" />
	</liferay-portlet:actionURL>

	<aui:button data="<%= data %>" onClick="<%= authorizeURL %>" value="sign-in" />
</div>