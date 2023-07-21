<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/document_library/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

FileVersion fileVersion = null;

if (row != null) {
	fileVersion = (FileVersion)row.getObject();
}
else {
	fileVersion = (FileVersion)request.getAttribute("info_panel.jsp-fileVersion");
}

DLViewFileEntryHistoryDisplayContext dlViewFileEntryHistoryDisplayContext = dlDisplayContextProvider.getDLViewFileEntryHistoryDisplayContext(request, response, fileVersion);
%>

<liferay-ui:menu
	menu="<%= dlViewFileEntryHistoryDisplayContext.getMenu() %>"
/>