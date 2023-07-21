<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/asset/init.jsp" %>

<%
CommerceOrder commerceOrder = (CommerceOrder)request.getAttribute(CommerceOrderConstants.COMMERCE_ORDER);
%>

<div class="container-fluid-1280">
	<h4><liferay-ui:message key="order-details" /></h4>

	<liferay-ui:search-container
		id="commerceOrderItems"
	>
		<liferay-ui:search-container-results
			results="<%= commerceOrder.getCommerceOrderItems() %>"
			resultsVar="commerceOrderItems"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.commerce.model.CommerceOrderItem"
			escapedModel="<%= true %>"
			keyProperty="commerceOrderItemId"
			modelVar="commerceOrderItem"
		>
			<liferay-ui:search-container-column-text
				cssClass="important table-cell-content"
				property="sku"
			/>

			<liferay-ui:search-container-column-text
				cssClass="table-cell-content"
				name="name"
				value="<%= HtmlUtil.escape(commerceOrderItem.getName(locale)) %>"
			/>

			<liferay-ui:search-container-column-text
				cssClass="table-cell-content"
				property="quantity"
			/>

			<%
			CommerceMoney finalPriceMoney = commerceOrderItem.getFinalPriceMoney();
			%>

			<liferay-ui:search-container-column-text
				cssClass="table-cell-content"
				name="price"
				value="<%= HtmlUtil.escape(finalPriceMoney.format(locale)) %>"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>