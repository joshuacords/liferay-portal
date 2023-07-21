<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
boolean active = GetterUtil.getBoolean(request.getAttribute("liferay-frontend:management-bar-button:active"));
String cssClass = (String)request.getAttribute("liferay-frontend:management-bar-button:cssClass");
Map<String, Object> data = (Map<String, Object>)request.getAttribute("liferay-frontend:management-bar-button:data");
boolean disabled = GetterUtil.getBoolean(request.getAttribute("liferay-frontend:management-bar-button:disabled"));
String href = (String)request.getAttribute("liferay-frontend:management-bar-button:href");
String icon = (String)request.getAttribute("liferay-frontend:management-bar-button:icon");
String iconCssClass = (String)request.getAttribute("liferay-frontend:management-bar-button:iconCssClass");
String id = (String)request.getAttribute("liferay-frontend:management-bar-button:id");
String label = (String)request.getAttribute("liferay-frontend:management-bar-button:label");

cssClass = "btn btn-default lfr-portal-tooltip " + cssClass;

if (active) {
	cssClass = "active " + cssClass;
}

if (disabled) {
	cssClass = "disabled " + cssClass;
}

if (Validator.isNotNull(icon)) {
	iconCssClass = StringPool.BLANK;
}

if (Validator.isNull(data.get("title"))) {
	data.put("title", LanguageUtil.get(request, label));
}

String labelCssClass = "sr-only";

if (Validator.isNull(icon) && Validator.isNull(iconCssClass)) {
	labelCssClass = StringPool.BLANK;
}
%>