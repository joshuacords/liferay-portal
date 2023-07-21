<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
List<CommercePriceEntry> commercePriceEntries = (List<CommercePriceEntry>)request.getAttribute(CommercePriceListWebKeys.COMMERCE_PRICE_ENTRIES);

if (commercePriceEntries == null) {
	commercePriceEntries = Collections.emptyList();
}
%>

<c:choose>
	<c:when test="<%= commercePriceEntries.size() == 1 %>">

		<%
		CommercePriceEntry commercePriceEntry = commercePriceEntries.get(0);

		request.setAttribute("info_panel.jsp-entry", commercePriceEntry);

		CPInstance cpInstance = commercePriceEntry.getCPInstance();

		CPDefinition cpDefinition = cpInstance.getCPDefinition();
		%>

		<div class="sidebar-header">
			<ul class="sidebar-header-actions">
				<li>
					<liferay-util:include page="/price_entry_action.jsp" servletContext="<%= application %>" />
				</li>
			</ul>

			<h4><%= HtmlUtil.escape(cpDefinition.getName(languageId)) %></h4>
		</div>

		<div class="sidebar-body">
			<h5><liferay-ui:message key="id" /></h5>

			<p>
				<%= HtmlUtil.escape(String.valueOf(commercePriceEntry.getCommercePriceEntryId())) %>
			</p>

			<h5><liferay-ui:message key="name" /></h5>

			<p>
				<%= HtmlUtil.escape(cpDefinition.getName(languageId)) %>
			</p>

			<h5><liferay-ui:message key="sku" /></h5>

			<p>
				<%= HtmlUtil.escape(cpInstance.getSku()) %>
			</p>
		</div>
	</c:when>
	<c:otherwise>
		<div class="sidebar-header">
			<h4><liferay-ui:message arguments="<%= commercePriceEntries.size() %>" key="x-items-are-selected" /></h4>
		</div>
	</c:otherwise>
</c:choose>