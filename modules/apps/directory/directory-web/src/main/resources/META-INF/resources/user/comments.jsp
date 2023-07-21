<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
User selUser = (User)request.getAttribute("user.selUser");
%>

<c:if test="<%= Validator.isNotNull(selUser.getComments()) %>">
	<h3 class="icon-comment"><liferay-ui:message key="comments" /></h3>

	<%= MBUtil.getBBCodeHTML(selUser.getComments(), themeDisplay.getPathThemeImages()) %>
</c:if>