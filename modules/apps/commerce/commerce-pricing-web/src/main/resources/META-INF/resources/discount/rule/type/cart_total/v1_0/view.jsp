<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CartTotalCommerceDiscountRuleDisplayContext cartTotalCommerceDiscountRuleDisplayContext = (CartTotalCommerceDiscountRuleDisplayContext)request.getAttribute("view.jsp-cartTotalCommerceDiscountRuleDisplayContext");
%>

<aui:input label="cart-total-minimum-amount" name="typeSettings" required="<%= true %>" suffix="<%= HtmlUtil.escape(cartTotalCommerceDiscountRuleDisplayContext.getDefaultCommerceCurrencyCode()) %>" type="text" value="<%= cartTotalCommerceDiscountRuleDisplayContext.getTypeSettings() %>">
	<aui:validator name="number" />
</aui:input>