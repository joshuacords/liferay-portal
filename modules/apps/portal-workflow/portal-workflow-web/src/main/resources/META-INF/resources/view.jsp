<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<liferay-ui:success key='<%= portletName + "requestProcessed" %>' message="your-request-completed-successfully" />

<liferay-util:include page="/navigation.jsp" servletContext="<%= application %>">
	<liferay-util:param name="searchPage" value="<%= selectedWorkflowPortletTab.getSearchJspPath() %>" />

	<%
	PortletURL searchURL = selectedWorkflowPortletTab.getSearchURL(renderRequest, renderResponse);
	%>

	<liferay-util:param name="searchURL" value="<%= searchURL.toString() %>" />
</liferay-util:include>

<%
selectedWorkflowPortletTab.include(request, PipingServletResponse.createPipingServletResponse(pageContext), null);
%>