<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String toolbarItem = ParamUtil.getString(request, "toolbar-item", "view-all");
%>

<div class="lfr-portlet-toolbar">
	<portlet:renderURL var="viewApplicationsURL">
		<portlet:param name="mvcPath" value="/admin/view.jsp" />
	</portlet:renderURL>

	<span class="lfr-toolbar-button view-button <%= toolbarItem.equals("view-all") ? "current" : StringPool.BLANK %>">
		<aui:a href="<%= viewApplicationsURL %>" label='<%= permissionChecker.isCompanyAdmin() ? "view-all" : "my-applications" %>' />
	</span>

	<c:if test="<%= OAuthPermission.contains(permissionChecker, OAuthActionKeys.ADD_APPLICATION) %>">
		<portlet:renderURL var="addApplicationURL">
			<portlet:param name="mvcPath" value="/admin/edit_application.jsp" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
		</portlet:renderURL>

		<span class="lfr-toolbar-button add-button <%= toolbarItem.equals("add") ? "current" : StringPool.BLANK %>">
			<aui:a href="<%= addApplicationURL %>" label="add" />
		</span>
	</c:if>
</div>