<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/taglib/ui/app_view_entry/init.jsp" %>

<div class="app-view-entry app-view-entry-taglib display-<%= HtmlUtil.escapeAttribute(displayStyle) %> entry-display-style <%= cssClass %>" <%= AUIUtil.buildData(data) %>>
	<liferay-ui:icon
		cssClass='<%= showCheckbox ? "pull-left app-view-entry app-view-entry-taglib entry-display-style selectable" : "pull-left app-view-entry app-view-entry-taglib entry-display-style" %>'
		data="<%= data %>"
		iconCssClass="<%= iconCssClass %>"
		label="<%= true %>"
		linkCssClass="entry-link"
		localizeMessage="<%= false %>"
		message="<%= HtmlUtil.escape(title) %>"
		method="get"
		src="<%= HtmlUtil.escapeAttribute(thumbnailSrc) %>"
		url="<%= url %>"
	/>

	<c:if test="<%= locked %>">
		<i class="icon-lock pull-right"></i>
	</c:if>

	<c:if test="<%= !folder && (status != WorkflowConstants.STATUS_ANY) && (status != WorkflowConstants.STATUS_APPROVED) %>">
		<aui:workflow-status showIcon="<%= false %>" showLabel="<%= false %>" status="<%= status %>" />
	</c:if>
</div>