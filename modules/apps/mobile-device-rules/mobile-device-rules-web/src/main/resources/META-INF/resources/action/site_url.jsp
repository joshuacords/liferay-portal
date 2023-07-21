<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/action/init.jsp" %>

<%
boolean ajax = GetterUtil.getBoolean(request.getParameter("ajax"));

long actionGroupId = GetterUtil.getLong(typeSettingsProperties.getProperty("groupId"));
%>

<aui:select label="site" name="groupId" onChange='<%= liferayPortletResponse.getNamespace() + "changeDisplay();" %>' required="<%= true %>">
	<aui:option disabled="<%= true %>" label="select-a-site" selected="<%= actionGroupId == 0 %>" value="" />

	<%
	int count = 0;

	for (Group group : GroupServiceUtil.getUserSitesGroups()) {
	%>

		<c:if test="<%= !group.isUser() && !group.isControlPanel() %>">

			<%
			count++;
			%>

			<aui:option label="<%= HtmlUtil.escape(group.getDescriptiveName(locale)) %>" selected="<%= group.getGroupId() == actionGroupId %>" value="<%= group.getGroupId() %>" />
		</c:if>

	<%
	}
	%>

	<c:if test="<%= count == 0 %>">
		<aui:option disabled="<%= true %>" label="no-available-sites" value="0" />
	</c:if>
</aui:select>

<div id="<portlet:namespace />layouts">
	<liferay-util:include page="/action/site_url_layouts.jsp" servletContext="<%= application %>" />
</div>

<c:if test="<%= ajax %>">
	<aui:script use="liferay-form">
		var form = Liferay.Form.get('<portlet:namespace />fm');

		if (form) {
			var rules = form.formValidator.get('rules');

			var groupIdFieldName = '<portlet:namespace />groupId';

			if (!(groupIdFieldName in rules)) {
				rules[groupIdFieldName] = {
					custom: false,
					required: true
				};
			}

			var plidFieldName = '<portlet:namespace />plid';

			if (!(plidFieldName in rules)) {
				rules[plidFieldName] = {
					custom: false,
					required: true
				};
			}
		}
	</aui:script>
</c:if>