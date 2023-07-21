<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
Map<String, Object> context = new HashMap<>();

context.put("color", portletConfigurationCSSPortletDisplayContext.getBackgroundColor());
context.put("id", renderResponse.getNamespace() + "backgroundColor");
context.put("label", LanguageUtil.get(request, "background-color"));
context.put("name", renderResponse.getNamespace() + "backgroundColor");
%>

<soy:component-renderer
	context="<%= context %>"
	module="js/ColorPickerInput.es"
	servletContext="<%= application %>"
	templateNamespace="com.liferay.portlet.configuration.css.web.ColorPickerInput.render"
/>