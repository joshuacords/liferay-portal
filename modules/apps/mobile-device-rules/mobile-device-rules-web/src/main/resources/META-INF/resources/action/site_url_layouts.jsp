<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/action/init.jsp" %>

<%
long actionGroupId = 0;

if (action != null) {
	actionGroupId = GetterUtil.getLong(typeSettingsProperties.getProperty("groupId"));
}
else {
	actionGroupId = ParamUtil.getLong(request, "actionGroupId");
}

long actionPlid = 0;

if (action != null) {
	actionPlid = GetterUtil.getLong(typeSettingsProperties.getProperty("plid"));
}
else {
	actionPlid = ParamUtil.getLong(request, "actionPlid");
}
%>

<aui:input name="actionPlid" type="hidden" value="<%= actionPlid %>" />

<aui:select label="page" name="plid" required="<%= true %>">
	<aui:option disabled="<%= true %>" label="select-a-page" selected="<%= actionPlid == 0 %>" value="" />

	<%
	List<Layout> publicLayouts = LayoutServiceUtil.getLayouts(actionGroupId, false);
	%>

	<c:if test="<%= !publicLayouts.isEmpty() %>">
		<aui:option disabled="<%= true %>" label="public-pages" value="0" />

		<%
		for (Layout publicLayout : publicLayouts) {
		%>

			<aui:option label="<%= HtmlUtil.escape(publicLayout.getName(locale)) %>" selected="<%= publicLayout.getPlid() == actionPlid %>" value="<%= publicLayout.getPlid() %>" />

		<%
		}
		%>

	</c:if>

	<%
	List<Layout> privateLayouts = LayoutServiceUtil.getLayouts(actionGroupId, true);
	%>

	<c:if test="<%= !privateLayouts.isEmpty() %>">
		<aui:option disabled="<%= true %>" label="private-pages" value="0" />

		<%
		for (Layout privateLayout : privateLayouts) {
		%>

			<aui:option label="<%= HtmlUtil.escape(privateLayout.getName(locale)) %>" selected="<%= privateLayout.getPlid() == actionPlid %>" value="<%= privateLayout.getPlid() %>" />

		<%
		}
		%>

	</c:if>

	<c:if test="<%= publicLayouts.isEmpty() && privateLayouts.isEmpty() %>">
		<aui:option disabled="<%= true %>" label="no-available-pages" value="0" />
	</c:if>
</aui:select>