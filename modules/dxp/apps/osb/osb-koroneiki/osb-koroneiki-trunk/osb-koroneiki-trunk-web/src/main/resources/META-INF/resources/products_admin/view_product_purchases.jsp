<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ProductPurchasesDisplayContext productPurchasesDisplayContext = new ProductPurchasesDisplayContext(renderRequest, renderResponse, request);

ViewProductPurchasesManagementToolbarDisplayContext viewProductPurchasesManagementToolbarDisplayContext = new ViewProductPurchasesManagementToolbarDisplayContext(liferayPortletRequest, liferayPortletResponse, request, productPurchasesDisplayContext.getSearchContainer());
%>

<clay:navigation-bar
	inverted="<%= true %>"
	navigationItems="<%= viewProductPurchasesManagementToolbarDisplayContext.getNavigationItems() %>"
/>

<clay:management-toolbar
	displayContext="<%= viewProductPurchasesManagementToolbarDisplayContext %>"
/>

<liferay-ui:error exception="<%= RequiredProductPurchaseException.MustNotDeleteProductPurchaseReferencedByProductConsumption.class %>" message="the-purchase-cannot-be-deleted-because-it-is-required-by-one-or-more-product-consumptions" />

<div class="container-fluid-1280">
	<liferay-ui:search-container
		searchContainer="<%= productPurchasesDisplayContext.getSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.osb.koroneiki.trunk.model.ProductPurchase"
			escapedModel="<%= true %>"
			keyProperty="productPurchaseId"
			modelVar="productPurchase"
		>

			<%
			Account koroneikiAccount = productPurchase.getAccount();
			ProductEntry productEntry = productPurchase.getProductEntry();
			%>

			<portlet:renderURL var="rowURL">
				<portlet:param name="mvcRenderCommandName" value="/products_admin/edit_product_purchase" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="productPurchaseId" value="<%= String.valueOf(productPurchase.getProductPurchaseId()) %>" />
			</portlet:renderURL>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="account"
			>
				<span class="lfr-portal-tooltip" data-title="<liferay-ui:message key="account" />">
					<aui:icon cssClass="icon-monospaced" image="users" markupView="lexicon" />
				</span>

				<%= HtmlUtil.escape(koroneikiAccount.getName()) %>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="product"
				value="<%= HtmlUtil.escape(productEntry.getName()) %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="start-date"
			>
				<c:if test="<%= productPurchase.getStartDate() != null %>">
					<%= mediumDateFormatDate.format(productPurchase.getStartDate()) %>
				</c:if>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="end-date"
			>
				<c:if test="<%= productPurchase.getEndDate() != null %>">
					<%= mediumDateFormatDate.format(productPurchase.getEndDate()) %>
				</c:if>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="quantity"
				value="<%= String.valueOf(productPurchase.getQuantity()) %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="status"
			>
				<span class="label label-sm label-<%= StringUtil.lowerCase(productPurchase.getStatusLabel()) %>"><%= StringUtil.lowerCase(productPurchase.getStatusLabel()) %></span>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-jsp
				align="right"
				path="/products_admin/product_purchase_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>