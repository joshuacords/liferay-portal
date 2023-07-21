<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/taglib/ui/breadcrumb/init.jsp" %>

<nav aria-label="<%= HtmlUtil.escapeAttribute(portletDisplay.getTitle()) %>" id="<portlet:namespace />breadcrumbs-defaultScreen">
	<c:if test="<%= !breadcrumbEntries.isEmpty() %>">

		<%
		String renderedDDMTemplate = StringPool.BLANK;

		DDMTemplate portletDisplayDDMTemplate = PortletDisplayTemplateManagerUtil.getDDMTemplate(displayStyleGroupId, PortalUtil.getClassNameId(BreadcrumbEntry.class), displayStyle, true);

		if (portletDisplayDDMTemplate != null) {
			renderedDDMTemplate = PortletDisplayTemplateManagerUtil.renderDDMTemplate(request, response, portletDisplayDDMTemplate.getTemplateId(), breadcrumbEntries, new HashMap<String, Object>());
		}
		%>

		<%= renderedDDMTemplate %>
	</c:if>
</nav>