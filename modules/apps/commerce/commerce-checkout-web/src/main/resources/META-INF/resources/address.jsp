<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceAddress commerceAddress = (CommerceAddress)request.getAttribute("address.jsp-commerceAddress");

commerceAddress = commerceAddress.toEscapedModel();
%>

<h4><%= HtmlUtil.escape(commerceAddress.getName()) %></h4>
<p><%= HtmlUtil.escape(commerceAddress.getStreet1()) %></p>

<c:if test="<%= Validator.isNotNull(commerceAddress.getStreet2()) %>">
	<p><%= HtmlUtil.escape(commerceAddress.getStreet2()) %></p>
</c:if>

<c:if test="<%= Validator.isNotNull(commerceAddress.getStreet3()) %>">
	<p><%= HtmlUtil.escape(commerceAddress.getStreet3()) %></p>
</c:if>

<p><%= HtmlUtil.escape(commerceAddress.getCity()) %></p>

<%
CommerceCountry commerceCountry = commerceAddress.getCommerceCountry();
%>

<c:if test="<%= commerceCountry != null %>">
	<p><%= HtmlUtil.escape(commerceCountry.getName(locale)) %></p>
</c:if>