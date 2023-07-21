<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<liferay-util:include page="/common/view_account_search_header.jsp" servletContext="<%= application %>" />

<%
ViewSubscriptionDisplayContext viewSubscriptionDisplayContext = ProvisioningWebComponentProvider.getViewSubscriptionDisplayContext(renderRequest, renderResponse, request);

String productPurchaseKey = ParamUtil.getString(request, "productPurchaseKey");

ProductPurchaseViewDisplay productPurchaseViewDisplay = viewSubscriptionDisplayContext.getProductPurchaseViewDisplay();
%>

<div class="info-container">
	<div class="info">
		<clay:icon
			symbol="exclamation-circle"
		/>

		<liferay-ui:message key="date-and-time-displayed-in-utc-all-end-dates-are-exclusive" />
	</div>
</div>

<div class="details-table table-striped">
	<liferay-ui:search-container
		searchContainer="<%= viewSubscriptionDisplayContext.getSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.osb.provisioning.web.internal.display.context.ProductPurchaseDisplay"
			modelVar="productPurchaseDisplay"
		>

			<%
			if (productPurchaseKey.equals(productPurchaseDisplay.getKey())) {
				row.setCssClass("highlight-row");
			}
			%>

			<liferay-ui:search-container-column-text
				name="start-end-date"
				value="<%= productPurchaseDisplay.getSupportLife() %>"
			/>

			<liferay-ui:search-container-column-text
				name="grace-period"
				value="<%= productPurchaseDisplay.getGracePeriod() %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= productPurchaseViewDisplay.getProvisionedCountURL() %>"
				name="provisioned"
				value="<%= productPurchaseDisplay.getProvisionedCount() %>"
			/>

			<liferay-ui:search-container-column-text
				name="purchased"
				value="<%= productPurchaseDisplay.getQuantity() %>"
			/>

			<liferay-ui:search-container-column-text
				name="salesforce-opportunity-key"
			>
				<a href="<%= productPurchaseDisplay.getSalesforceOpportunityURL() %>"><%= productPurchaseDisplay.getSalesforceOpportunityKey() %></a>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="instance-size"
				value="<%= productPurchaseDisplay.getSizing() %>"
			/>

			<liferay-ui:search-container-column-text
				name="state"
			>
				<span class="label <%= productPurchaseDisplay.getStateStyle() %>"><%= productPurchaseDisplay.getState() %></span>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-jsp
				path="/accounts/product_purchase_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>