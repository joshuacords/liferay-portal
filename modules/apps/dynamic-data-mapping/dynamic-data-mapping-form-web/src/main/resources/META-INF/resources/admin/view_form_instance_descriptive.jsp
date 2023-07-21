<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/admin/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

DDMFormInstance ddmFormInstance = (DDMFormInstance)row.getObject();

DateSearchEntry dateSearchEntry = new DateSearchEntry();

dateSearchEntry.setDate(ddmFormInstance.getModifiedDate());
%>

<div class="clamp-container">
	<h2 class="h5 truncate-text">
		<aui:a cssClass="form-instance-name" href="<%= (String)request.getAttribute(WebKeys.SEARCH_ENTRY_HREF) %>">
			<%= HtmlUtil.escape(ddmFormInstance.getName(locale)) %>
		</aui:a>
	</h2>

	<span class="text-default">
		<div class="form-instance-description truncate-text">
			<%= HtmlUtil.escape(ddmFormInstance.getDescription(locale)) %>
		</div>
	</span>
	<span class="text-default">
		<span class="form-instance-id">
			<liferay-ui:message key="id" />: <%= ddmFormInstance.getFormInstanceId() %>
		</span>
		<span class="form-instance-modified-date">
			<liferay-ui:message key="modified-date" />: <%= dateSearchEntry.getName(request) %>
		</span>
	</span>
</div>