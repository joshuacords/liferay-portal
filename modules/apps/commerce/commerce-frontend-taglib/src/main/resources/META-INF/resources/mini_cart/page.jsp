<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/mini_cart/init.jsp" %>

<div class="cart-root" id="<%= miniCartId %>"></div>

<aui:script require="commerce-frontend-js/components/mini_cart/entry as Cart">
	var initialProps = {
		cartActionURLs: {
			checkoutURL: '<%= HtmlUtil.escapeJS(checkoutURL) %>',
			orderDetailURL: '<%= HtmlUtil.escapeJS(orderDetailURL) %>'
		},
		displayDiscountLevels: <%= displayDiscountLevels %>,
		displayTotalItemsQuantity: <%= displayTotalItemsQuantity %>,
		orderId: <%= orderId %>,
		spritemap: '<%= HtmlUtil.escapeJS(spritemap) %>',
		toggleable: <%= toggleable %>
	};

	<%
	if (!cartViews.isEmpty()) {
	%>

		initialProps.cartViews = {};

		<%
		for (Map.Entry<String, String> cartView : cartViews.entrySet()) {
		%>

			initialProps.cartViews['<%= cartView.getKey() %>'] = {
				contentRendererModuleUrl: '<%= cartView.getValue() %>'
			};

			<%
				}
			}

			if (!labels.isEmpty()) {
				for (Map.Entry<String, String> label : labels.entrySet()) {
			%>

			initialProps.labels['<%= label.getKey() %>'] = '<%= label.getValue() %>';

	<%
		}
	}
	%>

	Cart.default('<%= miniCartId %>', '<%= miniCartId %>', initialProps);
</aui:script>