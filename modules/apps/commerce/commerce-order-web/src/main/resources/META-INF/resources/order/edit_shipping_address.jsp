<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String cmd = ParamUtil.getString(request, Constants.CMD);

CommerceOrderEditDisplayContext commerceOrderEditDisplayContext = (CommerceOrderEditDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceAddress shippingAddress = null;

CommerceOrder commerceOrder = commerceOrderEditDisplayContext.getCommerceOrder();

if ((commerceOrder != null) && Validator.isNull(cmd)) {
	shippingAddress = commerceOrder.getShippingAddress();
}

long commerceCountryId = BeanParamUtil.getLong(shippingAddress, request, "commerceCountryId");
long commerceRegionId = BeanParamUtil.getLong(shippingAddress, request, "commerceRegionId");
%>

<portlet:actionURL name="editCommerceOrder" var="editCommerceOrderShippingAddressActionURL" />

<commerce-ui:modal-content
	title='<%= (shippingAddress == null) ? LanguageUtil.get(request, "add-shipping-address") : LanguageUtil.get(request, "edit-shipping-address") %>'
>
	<aui:form action="<%= editCommerceOrderShippingAddressActionURL %>" method="post" name="fm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" value='<%= (shippingAddress == null) ? "addShippingAddress" : "updateShippingAddress" %>' />
		<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
		<aui:input name="commerceOrderId" type="hidden" value="<%= commerceOrder.getCommerceOrderId() %>" />

		<aui:model-context bean="<%= shippingAddress %>" model="<%= CommerceAddress.class %>" />

		<aui:input name="name" wrapperCssClass="form-group-item" />

		<aui:input name="phoneNumber" wrapperCssClass="form-group-item" />

		<aui:input name="street1" wrapperCssClass="form-group-item" />

		<aui:input name="street2" wrapperCssClass="form-group-item" />

		<aui:input name="street3" wrapperCssClass="form-group-item" />

		<aui:select label="country" name="commerceCountryId" wrapperCssClass="form-group-item" />

		<aui:input name="zip" wrapperCssClass="form-group-item" />

		<aui:input name="city" wrapperCssClass="form-group-item" />

		<aui:select label="region" name="commerceRegionId" wrapperCssClass="form-group-item" />
	</aui:form>
</commerce-ui:modal-content>

<aui:script use="liferay-dynamic-select">
	new Liferay.DynamicSelect([
		{
			select: '<portlet:namespace />commerceCountryId',
			selectData: function(callback) {
				function injectCountryPlaceholder(list) {
					list.unshift({
						commerceCountryId: '0',
						nameCurrentValue:
							'- <liferay-ui:message key="select-country" />'
					});

					callback(list);
				}

				Liferay.Service(
					'/commerce.commercecountry/get-shipping-commerce-countries',
					{
						active: true,
						companyId: <%= company.getCompanyId() %>,
						shippingAllowed: true
					},
					injectCountryPlaceholder
				);
			},
			selectDesc: 'nameCurrentValue',
			selectId: 'commerceCountryId',
			selectNullable: <%= false %>,
			selectSort: '<%= true %>',
			selectVal: '<%= commerceCountryId %>'
		},
		{
			select: '<portlet:namespace />commerceRegionId',
			selectData: function(callback, selectKey) {
				function injectRegionPlaceholder(list) {
					list.unshift({
						commerceRegionId: '0',
						name: '- <liferay-ui:message key="select-region" />'
					});

					callback(list);
				}

				Liferay.Service(
					'/commerce.commerceregion/get-commerce-regions',
					{
						active: true,
						commerceCountryId: Number(selectKey)
					},
					injectRegionPlaceholder
				);
			},
			selectDesc: 'name',
			selectId: 'commerceRegionId',
			selectNullable: <%= false %>,
			selectVal: '<%= commerceRegionId %>'
		}
	]);
</aui:script>