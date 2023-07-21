<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "products");
%>

<c:choose>
	<c:when test='<%= tabs1.equals("consumption") %>'>
		<liferay-util:include page="/products_admin/view_product_consumptions.jsp" servletContext="<%= application %>" />
	</c:when>
	<c:when test='<%= tabs1.equals("purchases") %>'>
		<liferay-util:include page="/products_admin/view_product_purchases.jsp" servletContext="<%= application %>" />
	</c:when>
	<c:otherwise>
		<liferay-util:include page="/products_admin/view_product_entries.jsp" servletContext="<%= application %>" />
	</c:otherwise>
</c:choose>