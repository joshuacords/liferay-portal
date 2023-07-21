<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/panel_app/init.jsp" %>

<%
boolean active = GetterUtil.getBoolean(request.getAttribute("liferay-application-list:panel-app:active"));
int notificationsCount = GetterUtil.getInteger(request.getAttribute("liferay-application-list:panel-app:notificationsCount"));
String url = (String)request.getAttribute("liferay-application-list:panel-app:url");
%>

<c:if test="<%= Validator.isNotNull(url) %>">
	<li class="<%= active ? "active" : StringPool.BLANK %>" role="presentation">
		<aui:a ariaRole="menuitem" data='<%= (Map<String, Object>)request.getAttribute("liferay-application-list:panel-app:data") %>' href="<%= url %>" id='<%= (String)request.getAttribute("liferay-application-list:panel-app:id") %>'>
			<%= (String)request.getAttribute("liferay-application-list:panel-app:label") %>

			<c:if test="<%= notificationsCount > 0 %>">
				<span class="badge badge-danger pull-right">
					<span class="badge-item badge-item-expand"><%= notificationsCount %></span>
				</span>
			</c:if>
		</aui:a>
	</li>
</c:if>