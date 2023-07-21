<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/import/init.jsp" %>

<%
long groupId = ParamUtil.getLong(request, "groupId");
boolean validate = ParamUtil.getBoolean(request, "validate", true);

String[] tempFileNames = LayoutServiceUtil.getTempFileNames(groupId, ExportImportHelper.TEMP_FOLDER_NAME);

portletDisplay.setShowBackIcon(true);

PortletURL importProcessesURL = PortalUtil.getControlPanelPortletURL(request, ExportImportPortletKeys.IMPORT, PortletRequest.RENDER_PHASE);

importProcessesURL.setParameter("mvcPath", "/import/view.jsp");

portletDisplay.setURLBack(importProcessesURL.toString());

renderResponse.setTitle(LanguageUtil.get(request, "new-import-process"));
%>

<div class="container-fluid-1280 container-view" id="<portlet:namespace />exportImportOptions">

	<%
	int incompleteBackgroundTaskCount = BackgroundTaskManagerUtil.getBackgroundTasksCount(groupId, BackgroundTaskExecutorNames.LAYOUT_IMPORT_BACKGROUND_TASK_EXECUTOR, false);
	%>

	<div class="<%= (incompleteBackgroundTaskCount == 0) ? "hide" : "in-progress" %>" id="<portlet:namespace />incompleteProcessMessage">
		<liferay-util:include page="/incomplete_processes_message.jsp" servletContext="<%= application %>">
			<liferay-util:param name="incompleteBackgroundTaskCount" value="<%= String.valueOf(incompleteBackgroundTaskCount) %>" />
		</liferay-util:include>
	</div>

	<c:choose>
		<c:when test="<%= (tempFileNames.length > 0) && !validate %>">
			<liferay-util:include page="/import/new_import/import_layouts_resources.jsp" servletContext="<%= application %>" />
		</c:when>
		<c:otherwise>
			<liferay-util:include page="/import/new_import/import_layouts_validation.jsp" servletContext="<%= application %>" />
		</c:otherwise>
	</c:choose>
</div>