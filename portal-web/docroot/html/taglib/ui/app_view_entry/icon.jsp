<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/taglib/ui/app_view_entry/init.jsp" %>

<div class="app-view-entry app-view-entry-taglib display-<%= HtmlUtil.escapeAttribute(displayStyle) %> entry-display-style <%= showCheckbox ? "selectable" : StringPool.BLANK %> <%= cssClass %>" <%= AUIUtil.buildData(data) %> data-draggable="<%= showCheckbox ? Boolean.TRUE.toString() : Boolean.FALSE.toString() %>" data-title="<%= HtmlUtil.escapeAttribute(shortTitle) %>">
	<c:if test="<%= showCheckbox %>">
		<aui:input cssClass="entry-selector overlay" id="<%= rowCheckerId %>" label="" name="<%= RowChecker.ROW_IDS + rowCheckerName %>" title='<%= LanguageUtil.format(request, "select-x", HtmlUtil.escapeAttribute(shortTitle)) %>' type="checkbox" value="<%= rowCheckerId %>" />
	</c:if>

	<%
	if (!folder) {
		request.removeAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
	}
	%>

	<liferay-util:include page="<%= actionJsp %>" servletContext="<%= actionJspServletContext %>" />

	<c:choose>
		<c:when test="<%= Validator.isNull(url) %>">
			<span class="entry-link" data-folder="<%= folder ? Boolean.TRUE.toString() : Boolean.FALSE.toString() %>" <%= folder ? "data-folder-id=\"" + rowCheckerId + "\"" : StringPool.BLANK %> title="<%= linkTitle %>">
		</c:when>
		<c:otherwise>
			<a class="entry-link" data-folder="<%= folder ? Boolean.TRUE.toString() : Boolean.FALSE.toString() %>" <%= folder ? "data-folder-id=\"" + rowCheckerId + "\"" : StringPool.BLANK %> href="<%= HtmlUtil.escapeAttribute(url) %>" title="<%= linkTitle %>">
		</c:otherwise>
	</c:choose>

	<c:if test="<%= Validator.isNotNull(iconCssClass) || Validator.isNotNull(thumbnailSrc) %>">
		<div class="entry-thumbnail" style="<%= thumbnailDivStyle %>">
			<c:choose>
				<c:when test="<%= Validator.isNotNull(thumbnailSrc) %>">
					<img alt="" src="<%= HtmlUtil.escapeAttribute(thumbnailSrc) %>" style="<%= thumbnailStyle %>" />
				</c:when>
				<c:when test="<%= Validator.isNotNull(iconCssClass) %>">
					<i class="<%= iconCssClass %>" style="<%= thumbnailStyle %>"></i>
				</c:when>
			</c:choose>

			<c:if test="<%= shortcut %>">
				<img alt="<liferay-ui:message escapeAttribute="<%= true %>" key="shortcut" />" class="shortcut-icon" src="<%= themeDisplay.getPathThemeImages() %>/file_system/large/overlay_link.png" />
			</c:if>

			<c:if test="<%= locked %>">
				<img alt="<liferay-ui:message escapeAttribute="<%= true %>" key="locked" />" class="locked-icon" src="<%= themeDisplay.getPathThemeImages() %>/file_system/large/overlay_lock.png" />
			</c:if>

			<c:if test="<%= !folder && (status != WorkflowConstants.STATUS_ANY) && (status != WorkflowConstants.STATUS_APPROVED) %>">
				<aui:workflow-status showIcon="<%= false %>" showLabel="<%= false %>" status="<%= status %>" />
			</c:if>
		</div>
	</c:if>

	<span class="entry-title">
		<span class="entry-title-text">
			<%= HtmlUtil.escape(shortTitle) %>
		</span>
		<span class="entry-result-icon"></span>
	</span>

	<c:choose>
		<c:when test="<%= Validator.isNull(url) %>">
			</span>
		</c:when>
		<c:otherwise>
			</a>
		</c:otherwise>
	</c:choose>
</div>