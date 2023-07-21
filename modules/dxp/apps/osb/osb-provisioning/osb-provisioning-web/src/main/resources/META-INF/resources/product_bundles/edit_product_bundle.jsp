<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<liferay-util:include page="/common/view_account_search_header.jsp" servletContext="<%= application %>" />

<%
String redirect = ParamUtil.getString(request, "redirect");

ProductBundle productBundle = (ProductBundle)renderRequest.getAttribute(ProvisioningWebKeys.PRODUCT_BUNDLE);
List<Product> products = (List<Product>)renderRequest.getAttribute(ProvisioningWebKeys.PRODUCT_BUNDLE_PRODUCTS);

List<String> productKeys = new ArrayList<>();

if (products != null) {
	productKeys = TransformUtil.transform(products, product -> product.getKey());
}
%>

<div class="add-items provisioning-product-bundle">
	<liferay-ui:header
		backURL="<%= redirect %>"
		cssClass="add-items-header"
		title='<%= (productBundle != null) ? "edit-product-bundle" : "new-product-bundle" %>'
	/>

	<portlet:actionURL name="/product_bundles/edit_product_bundle" var="editProductBundleURL">
		<portlet:param name="mvcRenderCommandName" value="/product_bundles/edit_product_bundle" />
		<portlet:param name="redirect" value="<%= redirect %>" />
		<portlet:param name="productBundleId" value='<%= (productBundle != null) ? String.valueOf(productBundle.getProductBundleId()) : "" %>' />
	</portlet:actionURL>

	<portlet:renderURL var="assignProductsURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
		<portlet:param name="mvcRenderCommandName" value="/product_bundles/assign_products" />
	</portlet:renderURL>

	<aui:form action="<%= editProductBundleURL %>" cssClass="container-fluid container-fluid-max-xl" method="post" name="fm">
		<div class="add-items-sheet sheet sheet-lg">
			<aui:input name="productKeys" type="hidden" value="<%= StringUtil.merge(productKeys) %>" />

			<liferay-ui:error exception="<%= ProductBundleNameException.MustNotBeDuplicate.class %>">

				<%
				ProductBundleNameException.MustNotBeDuplicate productBundleNameException = (ProductBundleNameException.MustNotBeDuplicate)errorException;
				%>

				<%= productBundleNameException.getMessage() %>
			</liferay-ui:error>

			<liferay-ui:error key="<%= RequiredProductException.class.getName() %>" message="please-select-at-least-one-product" />

			<aui:input inlineLabel="left" name="name" value='<%= (productBundle != null) ? productBundle.getName() : "" %>'>
				<aui:validator name="required" />
			</aui:input>

			<div class="form-group form-inline input-text-wrapper">
				<label class="control-label"><liferay-ui:message key="products" /></label>

				<div class="field table-field">
					<div class="field-name" id="<portlet:namespace />productName">
						<c:if test="<%= products != null %>">
							<table class="table table-list">
								<thead>
									<tr>
										<th>
											<liferay-ui:message key="name" />
										</th>
										<th>
										</th>
									</tr>
								</thead>

								<tbody>

									<%
									for (Product product : products) {
									%>

										<tr id="<%= product.getKey() %>">
											<td>
												<%= HtmlUtil.escape(product.getName()) %>
											</td>
											<td class="text-right">
												<button class="btn" onclick="<portlet:namespace />removeProduct(event);" type="button">
													<svg class="lexicon-icon lexicon-icon-times-circle">
														<use xlink:href="#delete-icon" />
													</svg>
												</button>
											</td>
										</tr>

									<%
									}
									%>

								</tbody>
							</table>
						</c:if>
					</div>

					<aui:button onClick='<%= renderResponse.getNamespace() + "assignProducts();" %>' value="select" />
				</div>
			</div>

			<aui:button-row>
				<aui:button type="submit" />

				<aui:button href="<%= redirect %>" type="cancel" />
			</aui:button-row>
		</div>
	</aui:form>
</div>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />assignProducts',
		function() {
			var A = AUI();

			var productKeys = A.one('#<portlet:namespace />productKeys');
			var productName = A.one('#<portlet:namespace />productName');

			var assignProductsURL = Liferay.Util.PortletURL.createPortletURL(
				'<%= assignProductsURL.toString() %>',
				{
					productKeys: productKeys ? productKeys.val() : ''
				}
			);

			var itemSelectorDialog = new A.LiferayItemSelectorDialog({
				eventName: 'selectedItemChange',
				on: {
					selectedItemChange: function(event) {
						var selectionData = event.newVal;

						if (selectionData) {
							var selectedItems = selectionData.map(function(data) {
								return data.split('_');
							});

							var keys = [];

							var display =
								'<table class="table table-list"><thead><tr><th><liferay-ui:message key="name" /></th><th></th></tr></thead><tbody>';

							for (var i = 0; i < selectedItems.length; i++) {
								var selectItem = selectedItems[i];

								keys.push(selectItem[0]);

								display +=
									'<tr id="' +
									selectItem[0] +
									'"><td>' +
									selectItem[1] +
									'</td><td class="text-right"><button type="button" class="btn" onclick="<portlet:namespace />removeProduct(event);"><svg class="lexicon-icon lexicon-icon-times-circle"><use xlink:href="#delete-icon" /></svg></button></td></tr>';
							}

							display += '</tbody></table>';

							if (productKeys) {
								productKeys.val(keys.join(','));
							}

							if (productName) {
								productName.html(display);
							}
						}
					}
				},
				strings: {
					add: '<liferay-ui:message key="done" />',
					cancel: '<liferay-ui:message key="cancel" />'
				},
				title: '<liferay-ui:message key="select-products" />',
				url: assignProductsURL.toString()
			});

			itemSelectorDialog.open();
		},
		['aui-base', 'liferay-item-selector-dialog']
	);

	function <portlet:namespace />removeProduct(event) {
		var currentTarget = event.currentTarget;

		var productKeys = document.getElementById(
			'<portlet:namespace />productKeys'
		);
		var productName = document.getElementById(
			'<portlet:namespace />productName'
		);

		if (productKeys && productName) {
			var currentProductKeys = productKeys.value
				.split(',')
				.filter(function(key) {
					return key !== currentTarget.closest('tr').id;
				})
				.join(',');

			productKeys.value = currentProductKeys;

			if (!currentProductKeys) {
				productName.innerHTML = '';
			}
			else {
				currentTarget.closest('tr').remove();
			}
		}
	}
</aui:script>