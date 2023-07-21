<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/format_price/init.jsp" %>

<%
String formattedPrice = (String)request.getAttribute("liferay-commerce:format-price:formattedPrice");
%>

<c:if test="<%= Validator.isNotNull(formattedPrice) %>">
	<span class="product-price"><%= formattedPrice %></span>
</c:if>