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

CommerceAddress billingAddress = null;

CommerceOrder commerceOrder = commerceOrderEditDisplayContext.getCommerceOrder();

if ((commerceOrder != null) && Validator.isNull(cmd)) {
	billingAddress = commerceOrder.getBillingAddress();
}

long commerceCountryId = BeanParamUtil.getLong(billingAddress, request, "commerceCountryId");
long commerceRegionId = BeanParamUtil.getLong(billingAddress, request, "commerceRegionId");
%>

<portlet:actionURL name="editCommerceOrder" var="editCommerceOrderBillingAddressActionURL" />

<commerce-ui:modal-content
	title='<%= (billingAddress == null) ? LanguageUtil.get(request, "add-billing-address") : LanguageUtil.get(request, "edit-billing-address") %>'
>
	<aui:form action="<%= editCommerceOrderBillingAddressActionURL %>" method="post" name="fm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" value='<%= (billingAddress == null) ? "addBillingAddress" : "updateBillingAddress" %>' />
		<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
		<aui:input name="commerceOrderId" type="hidden" value="<%= commerceOrder.getCommerceOrderId() %>" />

		<aui:model-context bean="<%= billingAddress %>" model="<%= CommerceAddress.class %>" />

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
					callback(
						list.concat({
							commerceCountryId: '0',
							nameCurrentValue:
								'- <liferay-ui:message key="select-country" />'
						})
					);
				}

				Liferay.Service(
					'/commerce.commercecountry/get-billing-commerce-countries',
					{
						active: true,
						billingAllowed: true,
						companyId: <%= company.getCompanyId() %>
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