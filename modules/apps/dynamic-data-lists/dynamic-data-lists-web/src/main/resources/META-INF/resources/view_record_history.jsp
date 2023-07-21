<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
DDLRecord record = (DDLRecord)request.getAttribute(DDLWebKeys.DYNAMIC_DATA_LISTS_RECORD);

DateSearchEntry dateSearchEntry = new DateSearchEntry();

List<DDLRecordVersion> recordVersions = DDLRecordVersionServiceUtil.getRecordVersions(record.getRecordId());

for (DDLRecordVersion recordVersion : recordVersions) {
	dateSearchEntry.setDate(recordVersion.getCreateDate());

	request.setAttribute(DDLWebKeys.DYNAMIC_DATA_LISTS_RECORD_VERSION, recordVersion);
%>

	<div>
		<ul class="sidebar-header-actions">
			<li>
				<liferay-util:include page="/record_version_action.jsp" servletContext="<%= application %>" />
			</li>
		</ul>

		<h4><liferay-ui:message arguments="<%= recordVersion.getVersion() %>" key="version-x" /></h4>

		<p>
			<small class="text-muted">
				<liferay-ui:message key="author" />: <%= HtmlUtil.escape(recordVersion.getUserName()) %>
			</small>
		</p>

		<p>
			<small class="text-muted">
				<liferay-ui:message key="create-date" />: <%= dateSearchEntry.getName(request) %>
			</small>
		</p>
	</div>

<%
}
%>