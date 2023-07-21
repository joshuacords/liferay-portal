<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
List<CPOptionCategory> cpOptionCategories = (List<CPOptionCategory>)request.getAttribute(CPWebKeys.CP_OPTION_CATEGORIES);

if (cpOptionCategories == null) {
	cpOptionCategories = Collections.emptyList();
}
%>

<c:choose>
	<c:when test="<%= cpOptionCategories.size() == 1 %>">

		<%
		CPOptionCategory cpOptionCategory = cpOptionCategories.get(0);

		request.setAttribute("info_panel.jsp-entry", cpOptionCategory);
		%>

		<div class="sidebar-header">
			<ul class="sidebar-header-actions">
				<li>
					<liferay-util:include page="/option_category_action.jsp" servletContext="<%= application %>" />
				</li>
			</ul>

			<h4><%= HtmlUtil.escape(cpOptionCategory.getTitle(locale)) %></h4>
		</div>

		<div class="sidebar-body">
			<h5><liferay-ui:message key="id" /></h5>

			<p>
				<%= HtmlUtil.escape(String.valueOf(cpOptionCategory.getCPOptionCategoryId())) %>
			</p>
		</div>
	</c:when>
	<c:otherwise>
		<div class="sidebar-header">
			<h4><liferay-ui:message arguments="<%= cpOptionCategories.size() %>" key="x-items-are-selected" /></h4>
		</div>
	</c:otherwise>
</c:choose>