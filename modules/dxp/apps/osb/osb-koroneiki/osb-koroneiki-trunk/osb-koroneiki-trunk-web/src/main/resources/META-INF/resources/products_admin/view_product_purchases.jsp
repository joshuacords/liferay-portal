<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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