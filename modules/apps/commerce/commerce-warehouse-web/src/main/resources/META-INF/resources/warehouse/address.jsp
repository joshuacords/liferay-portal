<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceInventoryWarehousesDisplayContext commerceInventoryWarehousesDisplayContext = (CommerceInventoryWarehousesDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceInventoryWarehouse commerceInventoryWarehouse = commerceInventoryWarehousesDisplayContext.getCommerceInventoryWarehouse();

String countryTwoLettersISOCode = BeanParamUtil.getString(commerceInventoryWarehouse, request, "countryTwoLettersISOCode");
String commerceRegionCode = BeanParamUtil.getString(commerceInventoryWarehouse, request, "commerceRegionCode");
%>

<liferay-ui:error-marker
	key="<%= WebKeys.ERROR_SECTION %>"
	value="address"
/>

<aui:model-context bean="<%= commerceInventoryWarehouse %>" model="<%= CommerceInventoryWarehouse.class %>" />

<aui:fieldset>
	<div class="col-md-6">
		<aui:input name="street1" />

		<aui:input name="street2" />

		<aui:input name="street3" />

		<aui:select label="country" name="countryTwoLettersISOCode" />

		<aui:select label="region" name="commerceRegionCode" />
	</div>

	<div class="col-md-6">
		<aui:input label="postal-code" name="zip" />

		<aui:input name="city" />
	</div>
</aui:fieldset>

<aui:script use="liferay-dynamic-select">
	new Liferay.DynamicSelect([
		{
			select: '<portlet:namespace />countryTwoLettersISOCode',
			selectData: function(callback) {
				Liferay.Service(
					'/commerce.commercecountry/get-commerce-countries',
					{
						companyId: <%= company.getCompanyId() %>,
						active: true
					},
					callback
				);
			},
			selectDesc: 'nameCurrentValue',
			selectId: 'twoLettersISOCode',
			selectSort: '<%= true %>',
			selectVal: '<%= HtmlUtil.escape(countryTwoLettersISOCode) %>'
		},
		{
			select: '<portlet:namespace />commerceRegionCode',
			selectData: function(callback, selectKey) {
				Liferay.Service(
					'/commerce.commerceregion/get-commerce-regions',
					{
						companyId: <%= company.getCompanyId() %>,
						countryTwoLettersISOCode: selectKey,
						active: true
					},
					callback
				);
			},
			selectDesc: 'name',
			selectId: 'code',
			selectVal: '<%= HtmlUtil.escape(commerceRegionCode) %>'
		}
	]);
</aui:script>