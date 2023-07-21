<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceTaxFixedRateAddressRelsDisplayContext commerceTaxFixedRateAddressRelsDisplayContext = (CommerceTaxFixedRateAddressRelsDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceTaxFixedRateAddressRel commerceTaxFixedRateAddressRel = commerceTaxFixedRateAddressRelsDisplayContext.getCommerceTaxFixedRateAddressRel();

long commerceCountryId = commerceTaxFixedRateAddressRelsDisplayContext.getCommerceCountryId();
long commerceRegionId = commerceTaxFixedRateAddressRelsDisplayContext.getCommerceRegionId();
%>

<portlet:actionURL name="editCommerceTaxFixedRateAddressRel" var="editCommerceTaxFixedRateAddressRelActionURL" />

<c:choose>
	<c:when test="<%= commerceTaxFixedRateAddressRel == null %>">
		<commerce-ui:modal-content
			title='<%= LanguageUtil.get(resourceBundle, "add-tax-rate-setting") %>'
		>
			<aui:form action="<%= editCommerceTaxFixedRateAddressRelActionURL %>" method="post" name="fm">
				<%@ include file="/edit_address_tax_fixed_rate.jspf" %>
			</aui:form>
		</commerce-ui:modal-content>
	</c:when>
	<c:otherwise>
		<commerce-ui:side-panel-content
			title='<%= LanguageUtil.get(resourceBundle, "edit-tax-rate-setting") %>'
		>
			<aui:form action="<%= editCommerceTaxFixedRateAddressRelActionURL %>" method="post" name="fm">
				<commerce-ui:panel>
					<%@ include file="/edit_address_tax_fixed_rate.jspf" %>
				</commerce-ui:panel>

				<aui:button-row>
					<aui:button cssClass="btn-lg" type="submit" />
				</aui:button-row>
			</aui:form>
		</commerce-ui:side-panel-content>
	</c:otherwise>
</c:choose>

<aui:script use="aui-base,liferay-dynamic-select">
	new Liferay.DynamicSelect([
		{
			select: '<portlet:namespace />commerceCountryId',
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
			selectId: 'commerceCountryId',
			selectSort: '<%= true %>',
			selectVal: '<%= commerceCountryId %>'
		},
		{
			select: '<portlet:namespace />commerceRegionId',
			selectData: function(callback, selectKey) {
				Liferay.Service(
					'/commerce.commerceregion/get-commerce-regions',
					{
						commerceCountryId: Number(selectKey),
						active: true
					},
					callback
				);
			},
			selectDesc: 'name',
			selectId: 'commerceRegionId',
			selectVal: '<%= commerceRegionId %>'
		}
	]);
</aui:script>