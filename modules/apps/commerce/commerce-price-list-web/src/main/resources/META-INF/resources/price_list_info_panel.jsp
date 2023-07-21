<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
List<CommercePriceList> commercePriceLists = (List<CommercePriceList>)request.getAttribute(CommercePriceListWebKeys.COMMERCE_PRICE_LISTS);

if (commercePriceLists == null) {
	commercePriceLists = Collections.emptyList();
}
%>

<c:choose>
	<c:when test="<%= commercePriceLists.size() == 1 %>">

		<%
		CommercePriceList commercePriceList = commercePriceLists.get(0);

		request.setAttribute("info_panel.jsp-entry", commercePriceList);
		%>

		<div class="sidebar-header">
			<ul class="sidebar-header-actions">
				<li>
					<liferay-util:include page="/price_list_action.jsp" servletContext="<%= application %>" />
				</li>
			</ul>

			<h4><%= HtmlUtil.escape(commercePriceList.getName()) %></h4>
		</div>

		<div class="sidebar-body">
			<h5><liferay-ui:message key="id" /></h5>

			<p>
				<%= HtmlUtil.escape(String.valueOf(commercePriceList.getCommercePriceListId())) %>
			</p>

			<h5><liferay-ui:message key="status" /></h5>

			<p>
				<aui:workflow-status markupView="lexicon" showIcon="<%= false %>" showLabel="<%= false %>" status="<%= commercePriceList.getStatus() %>" />
			</p>
		</div>
	</c:when>
	<c:otherwise>
		<div class="sidebar-header">
			<h4><liferay-ui:message arguments="<%= commercePriceLists.size() %>" key="x-items-are-selected" /></h4>
		</div>
	</c:otherwise>
</c:choose>