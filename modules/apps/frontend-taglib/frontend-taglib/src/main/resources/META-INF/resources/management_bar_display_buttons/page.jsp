<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/management_bar_display_buttons/init.jsp" %>

<%
String[] defaultViews = (String[])request.getAttribute("liferay-frontend:management-bar-display-buttons:defaultViews");
boolean disabled = GetterUtil.getBoolean(request.getAttribute("liferay-frontend:management-bar-display-buttons:disabled"));
String[] displayViews = (String[])request.getAttribute("liferay-frontend:management-bar-display-buttons:displayViews");
PortletURL portletURL = (PortletURL)request.getAttribute("liferay-frontend:management-bar-display-buttons:portletURL");
String selectedDisplayStyle = (String)request.getAttribute("liferay-frontend:management-bar-display-buttons:selectedDisplayStyle");

PortletURL displayStyleURL = PortletURLUtil.clone(portletURL, liferayPortletResponse);

for (String displayStyle : defaultViews) {
	String cssClass = StringPool.BLANK;

	if (displayStyle.equals("list")) {
		cssClass = "hidden-xs";
	}

	displayStyleURL.setParameter("displayStyle", displayStyle);

	String icon = "table2";

	if (displayStyle.equals("descriptive")) {
		icon = "list";
	}
	else if (displayStyle.equals("icon")) {
		icon = "cards2";
	}
%>

	<liferay-frontend:management-bar-button
		active="<%= displayStyle.equals(selectedDisplayStyle) %>"
		cssClass="<%= cssClass %>"
		disabled="<%= disabled || !ArrayUtil.contains(displayViews, displayStyle) %>"
		href='<%= (disabled || !ArrayUtil.contains(displayViews, displayStyle)) ? "javascript:;" : displayStyleURL.toString() %>'
		icon="<%= icon %>"
		label="<%= displayStyle %>"
	/>

<%
}
%>