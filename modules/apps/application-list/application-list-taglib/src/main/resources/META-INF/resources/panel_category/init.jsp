<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
boolean active = GetterUtil.getBoolean(request.getAttribute("liferay-application-list:panel-category:active"));
boolean headerActive = GetterUtil.getBoolean(request.getAttribute("liferay-application-list:panel-category:headerActive"));
String id = (String)request.getAttribute("liferay-application-list:panel-category:id");
int notificationsCount = GetterUtil.getInteger(request.getAttribute("liferay-application-list:panel-category:notificationsCount"));
List<PanelApp> panelApps = (List<PanelApp>)request.getAttribute("liferay-application-list:panel-category:panelApps");
PanelCategory panelCategory = (PanelCategory)request.getAttribute("liferay-application-list:panel-category:panelCategory");
boolean persistState = GetterUtil.getBoolean(request.getAttribute("liferay-application-list:panel-category:persistState"));
boolean showBody = GetterUtil.getBoolean(request.getAttribute("liferay-application-list:panel-category:showBody"));
boolean showHeader = GetterUtil.getBoolean(request.getAttribute("liferay-application-list:panel-category:showHeader"));
%>