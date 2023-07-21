<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<c:if test="<%= !SessionErrors.isEmpty(renderRequest) %>">
	<liferay-ui:error exception="<%= IllegalArgumentException.class %>">

		<%
		IllegalArgumentException iae = (IllegalArgumentException)errorException;
		%>

		<%= iae.getMessage() %>
	</liferay-ui:error>

	<liferay-ui:error exception="<%= Problem.ProblemException.class %>">

		<%
		Problem.ProblemException problemException = (Problem.ProblemException)errorException;
		%>

		<%= problemException.getMessage() %>
	</liferay-ui:error>
</c:if>