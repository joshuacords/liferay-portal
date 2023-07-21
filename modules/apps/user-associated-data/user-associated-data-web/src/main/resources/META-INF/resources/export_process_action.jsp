<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

BackgroundTask backgroundTask = (BackgroundTask)row.getObject();
%>

<liferay-ui:icon-menu
	direction="left-side"
	markupView="lexicon"
	showWhenSingleIcon="<%= true %>"
>
	<c:if test="<%= backgroundTask.isCompleted() && (backgroundTask.getAttachmentsFileEntriesCount() > 0) %>">

		<%
		FileEntry fileEntry = UADExportProcessUtil.getFileEntry(backgroundTask);

		Map<String, Object> data = new HashMap<String, Object>();

		data.put("senna-off", "true");

		StringBundler sb = new StringBundler(5);

		sb.append(LanguageUtil.get(request, "download"));
		sb.append(StringPool.SPACE);
		sb.append(StringPool.OPEN_PARENTHESIS);
		sb.append(LanguageUtil.formatStorageSize(fileEntry.getSize(), locale));
		sb.append(StringPool.CLOSE_PARENTHESIS);
		%>

		<liferay-ui:icon
			data="<%= data %>"
			label="<%= true %>"
			markupView="lexicon"
			message="<%= sb.toString() %>"
			method="get"
			url="<%= PortletFileRepositoryUtil.getDownloadPortletFileEntryURL(themeDisplay, fileEntry, StringPool.BLANK) %>"
		/>
	</c:if>

	<portlet:renderURL var="viewUADExportProcesses">
		<portlet:param name="mvcRenderCommandName" value="/view_uad_export_processes" />
		<portlet:param name="p_u_i_d" value="<%= String.valueOf(selectedUser.getUserId()) %>" />
	</portlet:renderURL>

	<portlet:actionURL name="/delete_uad_export_background_task" var="deleteBackgroundTaskURL">
		<portlet:param name="redirect" value="<%= viewUADExportProcesses.toString() %>" />
		<portlet:param name="backgroundTaskId" value="<%= String.valueOf(backgroundTask.getBackgroundTaskId()) %>" />
	</portlet:actionURL>

	<%
	Date completionDate = backgroundTask.getCompletionDate();
	%>

	<liferay-ui:icon-delete
		label="<%= true %>"
		message='<%= ((completionDate != null) && completionDate.before(new Date())) ? "delete" : "cancel" %>'
		url="<%= deleteBackgroundTaskURL.toString() %>"
	/>
</liferay-ui:icon-menu>