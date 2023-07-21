<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/panel_category/init.jsp" %>

<c:if test="<%= !panelApps.isEmpty() && showHeader %>">
	<a aria-expanded="<%= active %>" class="<%= headerActive ? "active" : "" %> collapse-icon collapse-icon-middle <%= active ? StringPool.BLANK : "collapsed" %> list-group-heading panel-header" data-qa-id="appGroup" data-toggle="collapse" href="#<%= id %>">
		<c:if test="<%= !panelCategory.includeHeader(request, PipingServletResponse.createPipingServletResponse(pageContext)) %>">
			<%= panelCategory.getLabel(themeDisplay.getLocale()) %>

			<c:if test="<%= notificationsCount > 0 %>">
				<span class="badge badge-danger panel-notifications-count pull-right">
					<span class="badge-item badge-item-expand" data-qa-id="notificationsCount"><%= notificationsCount %></span>
				</span>
			</c:if>
		</c:if>

		<aui:icon cssClass="collapse-icon-closed" image="angle-right" markupView="lexicon" />

		<aui:icon cssClass="collapse-icon-open" image="angle-down" markupView="lexicon" />
	</a>

	<div class="collapse <%= active ? "in" : StringPool.BLANK %>" id="<%= id %>">
		<div class="list-group-item">
</c:if>