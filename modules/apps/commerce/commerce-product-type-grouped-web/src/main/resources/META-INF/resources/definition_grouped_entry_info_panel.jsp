<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
List<CPDefinitionGroupedEntry> cpDefinitionGroupedEntries = (List<CPDefinitionGroupedEntry>)request.getAttribute(GroupedCPTypeWebKeys.CP_DEFINITION_GROUPED_ENTRIES);

if (cpDefinitionGroupedEntries == null) {
	cpDefinitionGroupedEntries = Collections.emptyList();
}
%>

<c:choose>
	<c:when test="<%= cpDefinitionGroupedEntries.size() == 1 %>">

		<%
		CPDefinitionGroupedEntry cpDefinitionGroupedEntry = cpDefinitionGroupedEntries.get(0);

		CProduct cProduct = cpDefinitionGroupedEntry.getEntryCProduct();

		CPDefinition cProductCPDefinition = CPDefinitionLocalServiceUtil.getCPDefinition(cProduct.getPublishedCPDefinitionId());

		request.setAttribute("info_panel.jsp-entry", cpDefinitionGroupedEntry);
		%>

		<div class="sidebar-header">
			<ul class="sidebar-header-actions">
				<li>
					<liferay-util:include page="/definition_grouped_entry_action.jsp" servletContext="<%= application %>" />
				</li>
			</ul>

			<h4><%= HtmlUtil.escape(cProductCPDefinition.getName(themeDisplay.getLanguageId())) %></h4>
		</div>

		<div class="sidebar-body">
			<h5><liferay-ui:message key="id" /></h5>

			<p>
				<%= HtmlUtil.escape(String.valueOf(cpDefinitionGroupedEntry.getCPDefinitionGroupedEntryId())) %>
			</p>

			<h5><liferay-ui:message key="status" /></h5>
		</div>
	</c:when>
	<c:otherwise>
		<div class="sidebar-header">
			<h4><liferay-ui:message arguments="<%= cpDefinitionGroupedEntries.size() %>" key="x-items-are-selected" /></h4>
		</div>
	</c:otherwise>
</c:choose>