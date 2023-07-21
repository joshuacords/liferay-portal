<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/taglib/init.jsp" %>

<%
String align = (String)request.getAttribute("liferay-ui:toggle-area:align");
boolean defaultShowContent = GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:toggle-area:defaultShowContent"));
String hideImage = (String)request.getAttribute("liferay-ui:toggle-area:hideImage");
String hideMessage = (String)request.getAttribute("liferay-ui:toggle-area:hideMessage");
String id = (String)request.getAttribute("liferay-ui:toggle-area:id");
String showImage = (String)request.getAttribute("liferay-ui:toggle-area:showImage");
String showMessage = (String)request.getAttribute("liferay-ui:toggle-area:showMessage");
String stateVar = (String)request.getAttribute("liferay-ui:toggle-area:stateVar");
%>