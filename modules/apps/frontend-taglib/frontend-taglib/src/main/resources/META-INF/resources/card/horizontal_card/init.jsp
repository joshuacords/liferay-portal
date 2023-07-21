<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/card/init.jsp" %>

<%
String colHTML = (String)request.getAttribute("liferay-frontend:card:colHTML");
Map<String, Object> linkData = (Map<String, Object>)request.getAttribute("liferay-frontend:card:linkData");
String text = (String)request.getAttribute("liferay-frontend:card:text");
%>