<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceInventoryWarehouseItemSelectorViewDisplayContext commerceInventoryWarehouseItemSelectorViewDisplayContext = (CommerceInventoryWarehouseItemSelectorViewDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

long commerceCountryId = commerceInventoryWarehouseItemSelectorViewDisplayContext.getCommerceCountryId();
String itemSelectedEventName = commerceInventoryWarehouseItemSelectorViewDisplayContext.getItemSelectedEventName();
List<ManagementBarFilterItem> managementBarFilterItems = commerceInventoryWarehouseItemSelectorViewDisplayContext.getManagementBarFilterItems();

String managementBarFilterValue = null;

for (ManagementBarFilterItem managementBarFilterItem : managementBarFilterItems) {
	if (commerceCountryId == Long.valueOf(managementBarFilterItem.getId())) {
		managementBarFilterValue = managementBarFilterItem.getLabel();

		break;
	}
}
%>

<liferay-frontend:management-bar
	includeCheckBox="<%= true %>"
	searchContainerId="commerceInventoryWarehouses"
>
	<liferay-frontend:management-bar-filters>
		<liferay-frontend:management-bar-filter
			label="country"
			managementBarFilterItems="<%= managementBarFilterItems %>"
			value="<%= managementBarFilterValue %>"
		/>

		<liferay-frontend:management-bar-sort
			orderByCol="<%= commerceInventoryWarehouseItemSelectorViewDisplayContext.getOrderByCol() %>"
			orderByType="<%= commerceInventoryWarehouseItemSelectorViewDisplayContext.getOrderByType() %>"
			orderColumns='<%= new String[] {"city", "name"} %>'
			portletURL="<%= commerceInventoryWarehouseItemSelectorViewDisplayContext.getPortletURL() %>"
		/>
	</liferay-frontend:management-bar-filters>

	<liferay-frontend:management-bar-buttons>
		<liferay-frontend:management-bar-display-buttons
			displayViews='<%= new String[] {"list"} %>'
			portletURL="<%= renderResponse.createRenderURL() %>"
			selectedDisplayStyle="list"
		/>
	</liferay-frontend:management-bar-buttons>
</liferay-frontend:management-bar>

<div class="container-fluid-1280" id="<portlet:namespace />commerceInventoryWarehouseSelectorWrapper">
	<liferay-ui:search-container
		id="commerceInventoryWarehouses"
		searchContainer="<%= commerceInventoryWarehouseItemSelectorViewDisplayContext.getSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.commerce.inventory.model.CommerceInventoryWarehouse"
			keyProperty="commerceInventoryWarehouseId"
			modelVar="commerceInventoryWarehouse"
		>
			<liferay-ui:search-container-column-text
				cssClass="table-cell-content"
				property="name"
			/>

			<liferay-ui:search-container-column-text
				cssClass="table-cell-content"
				property="city"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>

<aui:script use="liferay-search-container">
	var commerceInventoryWarehouseSelectorWrapper = A.one(
		'#<portlet:namespace />commerceInventoryWarehouseSelectorWrapper'
	);

	var searchContainer = Liferay.SearchContainer.get(
		'<portlet:namespace />commerceInventoryWarehouses'
	);

	searchContainer.on('rowToggled', function(event) {
		Liferay.Util.getOpener().Liferay.fire(
			'<%= HtmlUtil.escapeJS(itemSelectedEventName) %>',
			{
				data: Liferay.Util.listCheckedExcept(
					commerceInventoryWarehouseSelectorWrapper,
					'<portlet:namespace />allRowIds'
				)
			}
		);
	});
</aui:script>