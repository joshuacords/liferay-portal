<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String eventName = ParamUtil.getString(request, "eventName", liferayPortletResponse.getNamespace() + "selectFolder");

Map<String, Object> context = new HashMap<>();

context.put("itemSelectorSaveEvent", eventName);
context.put("namespace", liferayPortletResponse.getNamespace());
context.put("nodes", journalDisplayContext.getFoldersJSONArray());
context.put("pathThemeImages", themeDisplay.getPathThemeImages());
%>

<soy:component-renderer
	context="<%= context %>"
	module="js/SelectFolder.es"
	templateNamespace="com.liferay.journal.web.SelectFolder.render"
/>