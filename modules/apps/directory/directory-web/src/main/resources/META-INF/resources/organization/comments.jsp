<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
long organizationId = ParamUtil.getLong(request, "organizationId");

Organization organization = OrganizationServiceUtil.fetchOrganization(organizationId);
%>

<c:if test="<%= Validator.isNotNull(organization.getComments()) %>">
	<h3 class="icon-comment"><liferay-ui:message key="comments" /></h3>

	<%= MBUtil.getBBCodeHTML(organization.getComments(), themeDisplay.getPathThemeImages()) %>
</c:if>