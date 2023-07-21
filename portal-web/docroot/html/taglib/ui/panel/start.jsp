<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/taglib/ui/panel/init.jsp" %>

<div class="panel panel-default <%= cssClass %>" id="<%= id %>">
	<div class="panel-heading <%= headerCssClass %>" data-persist-id="<%= persistState ? id : StringPool.BLANK %>">
		<div class="panel-toggle">
			<c:if test="<%= Validator.isNotNull(iconCssClass) %>">
				<i class="<%= iconCssClass %>"></i>
			</c:if>

			<span class="title-text">
				<liferay-ui:message key="<%= title %>" />
			</span>

			<c:if test="<%= Validator.isNotNull(helpMessage) %>">
				<liferay-ui:icon-help message="<%= helpMessage %>" />
			</c:if>
		</div>
	</div>

	<div class="<%= contentCssClass %>" id="<%= id %>Content">
		<div class="panel-body">