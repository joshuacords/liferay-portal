<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/process_status/init.jsp" %>

<%
String clayClassPostfix = "info";

if (backgroundTaskStatus == BackgroundTaskConstants.STATUS_FAILED) {
	clayClassPostfix = "danger";
}
else if (backgroundTaskStatus == BackgroundTaskConstants.STATUS_IN_PROGRESS) {
	clayClassPostfix = "warning";
}
else if (backgroundTaskStatus == BackgroundTaskConstants.STATUS_SUCCESSFUL) {
	clayClassPostfix = "success";
}
%>

<span class="label label-<%= clayClassPostfix %> process-status background-task-status-<%= backgroundTaskStatusLabel %>" data-qa-id="processResult">
	<liferay-ui:message key="<%= backgroundTaskStatusLabel %>" />
</span>