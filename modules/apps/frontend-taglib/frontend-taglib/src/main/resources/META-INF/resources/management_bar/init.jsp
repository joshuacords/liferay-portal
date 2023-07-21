<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String actionButtons = (String)request.getAttribute("liferay-frontend:management-bar:actionButtons");
String buttons = (String)request.getAttribute("liferay-frontend:management-bar:buttons");
boolean disabled = GetterUtil.getBoolean(request.getAttribute("liferay-frontend:management-bar:disabled"));
String filters = (String)request.getAttribute("liferay-frontend:management-bar:filters");
boolean includeCheckBox = GetterUtil.getBoolean(request.getAttribute("liferay-frontend:management-bar:includeCheckBox"));
String searchContainerId = (String)request.getAttribute("liferay-frontend:management-bar:searchContainerId");
%>