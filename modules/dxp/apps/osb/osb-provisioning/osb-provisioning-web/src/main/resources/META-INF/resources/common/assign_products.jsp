<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String accountKey = ParamUtil.getString(renderRequest, "accountKey");

String clearResultsURL = StringPool.BLANK;
String searchActionURL = StringPool.BLANK;
SearchContainer searchContainer = null;

if (Validator.isNotNull(accountKey)) {
	AssignProductPurchaseProductsDisplayContext assignProductPurchaseProductsDisplayContext = ProvisioningWebComponentProvider.getAssignProductPurchaseProductsDisplayContext(renderRequest, renderResponse, request);

	clearResultsURL = assignProductPurchaseProductsDisplayContext.getClearResultsURL();
	searchActionURL = assignProductPurchaseProductsDisplayContext.getSearchActionURL();
	searchContainer = assignProductPurchaseProductsDisplayContext.getSearchContainer();
}
else {
	AssignProductBundleProductsDisplayContext assignProductBundleProductsDisplayContext = ProvisioningWebComponentProvider.getAssignProductBundleProductsDisplayContext(renderRequest, renderResponse, request);

	clearResultsURL = assignProductBundleProductsDisplayContext.getClearResultsURL();
	searchActionURL = assignProductBundleProductsDisplayContext.getSearchActionURL();
	searchContainer = assignProductBundleProductsDisplayContext.getSearchContainer();
}
%>

<clay:management-toolbar
	clearResultsURL="<%= clearResultsURL %>"
	itemsTotal="<%= searchContainer.getTotal() %>"
	searchActionURL="<%= searchActionURL %>"
	searchContainerId="assignProducts"
	selectable="<%= true %>"
	showSearch="<%= true %>"
/>

<div class="container-fluid container-fluid-max-xl">
	<liferay-ui:search-container
		id="assignProducts"
		searchContainer="<%= searchContainer %>"
		var="productsSearchContainer"
	>
		<liferay-ui:search-container-row
			className="Object"
			modelVar="result"
		>

			<%
			String name = StringPool.BLANK;

			if (result instanceof ProductBundle) {
				ProductBundle productBundle = (ProductBundle)result;

				name = productBundle.getName();
			}
			else {
				ProductDisplay productDisplay = (ProductDisplay)result;

				name = productDisplay.getName();
			}
			%>

			<liferay-ui:search-container-column-text
				cssClass="table-cell-expand"
				name='<%= Validator.isNotNull(accountKey) ? StringPool.BLANK : "products" %>'
				value="<%= HtmlUtil.escape(name) %>"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
			resultRowSplitter="<%= Validator.isNotNull(accountKey) ? new ProductResultRowSplitter() : null %>"
		/>
	</liferay-ui:search-container>
</div>

<aui:script use="liferay-search-container">
	var searchContainer = Liferay.SearchContainer.get(
		'<portlet:namespace />assignProducts'
	);

	if (searchContainer) {
		searchContainer.on('rowToggled', function(event) {
			var selectedItems = event.elements.allSelectedElements;

			if (selectedItems && selectedItems.size()) {
				var data = selectedItems.attr('value');

				Liferay.Util.getOpener().Liferay.fire('selectedItemChange', {
					data: data
				});
			}
		});
	}
</aui:script>