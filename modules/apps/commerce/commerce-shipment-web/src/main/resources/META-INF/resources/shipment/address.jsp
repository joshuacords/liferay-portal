<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceShipmentDisplayContext commerceShipmentDisplayContext = (CommerceShipmentDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceShipment commerceShipment = commerceShipmentDisplayContext.getCommerceShipment();

CommerceAddress shippingAddress = commerceShipmentDisplayContext.getShippingAddress();
%>

<portlet:actionURL name="editCommerceShipment" var="editCommerceShipmentURL" />

<commerce-ui:modal-content
	title='<%= LanguageUtil.format(request, "edit-x", "shipping-address") %>'
>
	<aui:form action="<%= editCommerceShipmentURL %>" cssClass="container-fluid-1280 p-0" method="post" name="fm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" value="address" />
		<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
		<aui:input name="commerceShipmentId" type="hidden" value="<%= commerceShipment.getCommerceShipmentId() %>" />

		<aui:model-context bean="<%= shippingAddress %>" model="<%= CommerceAddress.class %>" />

		<aui:input name="name" />

		<aui:input name="street1" />

		<aui:input name="street2" />

		<aui:input name="street3" />

		<aui:input name="city" />

		<aui:input label="postal-code" name="zip" />

		<aui:select label="country" name="commerceCountryId" showEmptyOption="<%= true %>">

			<%
			List<CommerceCountry> commerceCountries = commerceShipmentDisplayContext.getCommerceCountries();

			for (CommerceCountry commerceCountry : commerceCountries) {
			%>

				<aui:option label="<%= commerceCountry.getName(LanguageUtil.getLanguageId(locale)) %>" selected="<%= shippingAddress.getCommerceCountryId() == commerceCountry.getCommerceCountryId() %>" value="<%= commerceCountry.getCommerceCountryId() %>" />

			<%
			}
			%>

		</aui:select>

		<aui:select label="region" name="commerceRegionId" showEmptyOption="<%= true %>">

			<%
			List<CommerceRegion> commerceRegions = commerceShipmentDisplayContext.getCommerceRegions(shippingAddress.getCommerceCountryId());

			for (CommerceRegion commerceRegion : commerceRegions) {
			%>

				<aui:option label="<%= commerceRegion.getName() %>" selected="<%= shippingAddress.getCommerceRegionId() == commerceRegion.getCommerceRegionId() %>" value="<%= shippingAddress.getCommerceRegionId() %>" />

			<%
			}
			%>

		</aui:select>

		<aui:input name="phoneNumber" />
	</aui:form>
</commerce-ui:modal-content>

<aui:script use="aui-base,liferay-dynamic-select">
	new Liferay.DynamicSelect([
		{
			select: '<portlet:namespace />commerceCountryId',
			selectData: function(callback) {
				Liferay.Service(
					'/commerce.commercecountry/get-shipping-commerce-countries',
					{
						companyId: <%= company.getCompanyId() %>,
						end: -1,
						start: -1
					},
					callback
				);
			},
			selectDesc: 'nameCurrentValue',
			selectId: 'commerceCountryId',
			selectSort: '<%= true %>',
			selectVal: '<%= shippingAddress.getCommerceCountryId() %>'
		},
		{
			select: '<portlet:namespace />commerceRegionId',
			selectData: function(callback, selectKey) {
				Liferay.Service(
					'/commerce.commerceregion/get-commerce-regions',
					{
						active: true,
						commerceCountryId: Number(selectKey)
					},
					callback
				);
			},
			selectDesc: 'name',
			selectId: 'commerceRegionId',
			selectVal: '<%= shippingAddress.getCommerceRegionId() %>'
		}
	]);
</aui:script>