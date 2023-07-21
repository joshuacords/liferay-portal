<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ProductEntriesDisplayContext productEntriesDisplayContext = new ProductEntriesDisplayContext(renderRequest, renderResponse, request);

ViewProductEntriesManagementToolbarDisplayContext viewProductEntriesManagementToolbarDisplayContext = new ViewProductEntriesManagementToolbarDisplayContext(liferayPortletRequest, liferayPortletResponse, request, productEntriesDisplayContext.getSearchContainer());
%>

<clay:navigation-bar
	inverted="<%= true %>"
	navigationItems="<%= viewProductEntriesManagementToolbarDisplayContext.getNavigationItems() %>"
/>

<clay:management-toolbar
	displayContext="<%= viewProductEntriesManagementToolbarDisplayContext %>"
/>

<liferay-ui:error exception="<%= RequiredProductEntryException.MustNotDeleteProductEntryReferencedByProductConsumption.class %>" message="the-product-cannot-be-deleted-because-it-is-required-by-one-or-more-product-consumptions" />
<liferay-ui:error exception="<%= RequiredProductEntryException.MustNotDeleteProductEntryReferencedByProductPurchase.class %>" message="the-product-cannot-be-deleted-because-it-is-required-by-one-or-more-product-purchases" />

<div class="container-fluid-1280">
	<liferay-ui:search-container
		searchContainer="<%= productEntriesDisplayContext.getSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.osb.koroneiki.trunk.model.ProductEntry"
			escapedModel="<%= true %>"
			keyProperty="productEntryId"
			modelVar="productEntry"
		>
			<portlet:renderURL var="rowURL">
				<portlet:param name="mvcRenderCommandName" value="/products_admin/edit_product_entry" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="productEntryId" value="<%= String.valueOf(productEntry.getProductEntryId()) %>" />
			</portlet:renderURL>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="name"
				value="<%= productEntry.getName() %>"
			/>

			<liferay-ui:search-container-column-jsp
				align="right"
				path="/products_admin/product_entry_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>