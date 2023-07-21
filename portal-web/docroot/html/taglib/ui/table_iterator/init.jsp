<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/taglib/init.jsp" %>

<%
List list = (List)request.getAttribute("liferay-ui:table-iterator:list");
int listPos = GetterUtil.getInteger((String)request.getAttribute("liferay-ui:table-iterator:listPos"));
int rowLength = GetterUtil.getInteger((String)request.getAttribute("liferay-ui:table-iterator:rowLength"));
String rowPadding = (String)request.getAttribute("liferay-ui:table-iterator:rowPadding");
String rowValign = (String)request.getAttribute("liferay-ui:table-iterator:rowValign");
String rowBreak = (String)request.getAttribute("liferay-ui:table-iterator:rowBreak");
String width = (String)request.getAttribute("liferay-ui:table-iterator:width");

// LEP-4752

if (rowLength == 0) {
	rowLength = 2;
}
%>