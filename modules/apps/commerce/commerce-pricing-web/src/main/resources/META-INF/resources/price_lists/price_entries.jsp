<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommercePriceEntryDisplayContext commercePriceEntryDisplayContext = (CommercePriceEntryDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommercePriceList commercePriceList = commercePriceEntryDisplayContext.getCommercePriceList();
long commercePriceListId = commercePriceEntryDisplayContext.getCommercePriceListId();

String datasetId = CommercePricingDataSetConstants.COMMERCE_DATA_SET_KEY_PRICE_LIST_ENTRIES;

if (CommercePriceListConstants.TYPE_PROMOTION.equals(commercePriceEntryDisplayContext.getCommercePriceListType(portletName))) {
	datasetId = CommercePricingDataSetConstants.COMMERCE_DATA_SET_KEY_PROMOTION_ENTRIES;
}
%>

<c:if test="<%= commercePriceEntryDisplayContext.hasPermission(commercePriceListId, ActionKeys.UPDATE) %>">
	<div class="pt-4 row">
		<div class="col-12">
			<div id="item-finder-root"></div>

			<aui:script require="commerce-frontend-js/components/item_finder/entry as itemFinder, commerce-frontend-js/utilities/slugify as slugify, commerce-frontend-js/utilities/eventsDefinitions as events, commerce-frontend-js/ServiceProvider/index as ServiceProvider">
				var CommercePriceEntriesResource = ServiceProvider.default.AdminPricingAPI(
					'v2'
				);

				var id = <%= commercePriceListId %>;
				var priceListExternalReferenceCode =
					'<%= HtmlUtil.escapeJS(commercePriceList.getExternalReferenceCode()) %>';

				function selectItem(sku) {
					var priceEntryData = {
						price: '0.0',
						priceListExternalReferenceCode: priceListExternalReferenceCode,
						priceListId: id,
						skuExternalReferenceCode: sku.externalReferenceCode,
						skuId: sku.id
					};

					return CommercePriceEntriesResource.addPriceEntry(id, priceEntryData)
						.then(function() {
							Liferay.fire(events.UPDATE_DATASET_DISPLAY, {
								id: '<%= datasetId %>'
							});
						})
						.catch(function(error) {
							return Promise.reject(error);
						});
				}

				function getSelectedItems() {
					return Promise.resolve([]);
				}

				itemFinder.default('itemFinder', 'item-finder-root', {
					apiUrl:
						'/o/headless-commerce-admin-catalog/v1.0/skus?filter=catalogId eq <%= commercePriceEntryDisplayContext.getCommerceCatalogId() %>',
					getSelectedItems: getSelectedItems,
					inputPlaceholder: '<%= LanguageUtil.get(request, "find-a-sku") %>',
					itemSelectedMessage: '<%= LanguageUtil.get(request, "sku-selected") %>',
					linkedDatasetsId: ['<%= datasetId %>'],
					itemCreation: false,
					itemsKey: 'id',
					onItemSelected: selectItem,
					pageSize: 10,
					panelHeaderLabel: '<%= LanguageUtil.get(request, "add-skus") %>',
					portletId: '<%= portletDisplay.getRootPortletId() %>',
					schema: [
						{
							fieldName: 'sku'
						},
						{
							fieldName: ['productName', 'LANG']
						}
					],
					spritemap: '<%= themeDisplay.getPathThemeImages() %>/lexicon/icons.svg',
					titleLabel: '<%= LanguageUtil.get(request, "add-existing-sku") %>'
				});
			</aui:script>
		</div>

		<div class="col-12">
			<commerce-ui:panel
				bodyClasses="p-0"
				title='<%= LanguageUtil.get(request, "entries") %>'
			>
				<commerce-ui:headless-dataset-display
					apiUrl="<%= commercePriceEntryDisplayContext.getPriceEntryApiUrl() %>"
					clayHeadlessDataSetActionTemplates="<%= commercePriceEntryDisplayContext.getClayHeadlessDataSetActionPriceEntriesTemplates() %>"
					formId="fm"
					id="<%= datasetId %>"
					itemsPerPage="<%= 10 %>"
					namespace="<%= renderResponse.getNamespace() %>"
					pageNumber="<%= 1 %>"
					portletURL="<%= currentURLObj %>"
				/>
			</commerce-ui:panel>
		</div>
	</div>
</c:if>