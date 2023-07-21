<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/taglib/ui/user_display/init.jsp" %>

<div class="display-style-<%= displayStyle %> taglib-user-display">

	<%
	if (Validator.isNull(url) && (userDisplay != null)) {
		url = userDisplay.getDisplayURL(themeDisplay);
	}
	%>

	<aui:a href="<%= url %>">
		<liferay-ui:user-portrait
			imageCssClass="<%= imageCssClass %>"
			user="<%= userDisplay %>"
			userName="<%= (userDisplay != null) ? userDisplay.getFullName() : userName %>"
		/>

		<c:if test="<%= showUserName %>">
			<span class="user-name">
				<%= (userDisplay != null) ? HtmlUtil.escape(userDisplay.getFullName()) : HtmlUtil.escape(userName) %>
			</span>
		</c:if>
	</aui:a>

	<c:if test="<%= showUserDetails %>">
		<div class="user-details">
	</c:if>