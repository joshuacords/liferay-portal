<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/admin/init.jsp" %>

<%
long formInstanceId = ParamUtil.getLong(request, renderResponse.getNamespace() + "formInstanceId");
%>

<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="exportFormInstance" var="exportFormInstanceURL">
	<portlet:param name="formInstanceId" value="<%= String.valueOf(formInstanceId) %>" />
</liferay-portlet:resourceURL>

<%
StringBundler sb = new StringBundler(5);

sb.append("javascript:");
sb.append(renderResponse.getNamespace());
sb.append("exportFormInstance('");
sb.append(exportFormInstanceURL);
sb.append("');");
%>

<liferay-ui:icon
	message="export"
	url="<%= sb.toString() %>"
/>