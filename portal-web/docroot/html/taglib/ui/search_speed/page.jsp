<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/taglib/init.jsp" %>

<%
SearchContainer searchContainer = (SearchContainer)request.getAttribute("liferay-ui:search:searchContainer");
Hits hits = (Hits)request.getAttribute("liferay-ui:search:hits");

searchContainer.setTotal(hits.getLength());

NumberFormat doubleFormat = NumberFormat.getInstance(locale);

doubleFormat.setMaximumFractionDigits(2);

NumberFormat integerFormat = NumberFormat.getInstance(locale);

integerFormat.setMaximumFractionDigits(0);
%>

<liferay-ui:message arguments='<%= new Object[] {"<strong>" + ((searchContainer.getResultEnd() > 0) ? searchContainer.getStart() + 1 : 0)+ "</strong> - <strong>" + searchContainer.getResultEnd() + "</strong>", "<strong>" + integerFormat.format(searchContainer.getTotal()) + "</strong>"} %>' key="results-of" translateArguments="<%= false %>" />

<liferay-ui:message arguments='<%= new Object[] {"<strong>" + doubleFormat.format(hits.getSearchTime()) + "</strong>"} %>' key="search-took-x-seconds" translateArguments="<%= false %>" />