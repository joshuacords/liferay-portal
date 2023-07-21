<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/document_library/init.jsp" %>

<ul class="list-group sidebar-list-group">

	<%
	FileEntry fileEntry = (FileEntry)request.getAttribute("info_panel.jsp-fileEntry");

	int status = WorkflowConstants.STATUS_APPROVED;

	if ((user.getUserId() == fileEntry.getUserId()) || permissionChecker.isContentReviewer(user.getCompanyId(), scopeGroupId)) {
		status = WorkflowConstants.STATUS_ANY;
	}

	List<FileVersion> fileVersions = fileEntry.getFileVersions(status);

	for (FileVersion fileVersion : fileVersions) {
		request.setAttribute("info_panel.jsp-fileVersion", fileVersion);
	%>

		<li class="list-group-item list-group-item-flex">
			<div class="autofit-col autofit-col-expand">
				<div class="list-group-title">
					<liferay-ui:message arguments="<%= fileVersion.getVersion() %>" key="version-x" />
				</div>

				<div class="list-group-subtitle">
					<liferay-ui:message arguments="<%= new Object[] {HtmlUtil.escape(fileVersion.getUserName()), dateFormatDateTime.format(fileVersion.getCreateDate())} %>" key="by-x-on-x" translateArguments="<%= false %>" />
				</div>

				<div class="list-group-subtext">
					<c:choose>
						<c:when test="<%= Validator.isNull(fileVersion.getChangeLog()) %>">
							<liferay-ui:message key="no-change-log" />
						</c:when>
						<c:otherwise>
							<%= HtmlUtil.escape(fileVersion.getChangeLog()) %>
						</c:otherwise>
					</c:choose>
				</div>
			</div>

			<div class="autofit-col">
				<liferay-util:include page="/document_library/file_entry_history_action.jsp" servletContext="<%= application %>" />
			</div>
		</li>

	<%
	}
	%>

</ul>