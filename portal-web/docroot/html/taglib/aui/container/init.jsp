<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

@generated
--%>

<%@ include file="/html/taglib/taglib-init.jsp" %>

<%
java.lang.String cssClass = GetterUtil.getString((java.lang.String)request.getAttribute("aui:container:cssClass"));
boolean fluid = GetterUtil.getBoolean(String.valueOf(request.getAttribute("aui:container:fluid")), true);
java.lang.String id = GetterUtil.getString((java.lang.String)request.getAttribute("aui:container:id"));
Map<String, Object> dynamicAttributes = (Map<String, Object>)request.getAttribute("aui:container:dynamicAttributes");
%>

<%@ include file="/html/taglib/aui/container/init-ext.jspf" %>