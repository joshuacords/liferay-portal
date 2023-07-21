<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

Account koroneikiAccount = (Account)request.getAttribute(TaprootWebKeys.ACCOUNT);
Address address = (Address)request.getAttribute(WebKeys.ADDRESS);

long addressId = ParamUtil.getLong(request, "addressId");

long countryId = 0L;
long regionId = 0L;

if (address != null) {
	countryId = address.getCountryId();
	regionId = address.getRegionId();
}

renderResponse.setTitle(koroneikiAccount.getName());
%>

<portlet:actionURL name="/accounts_admin/edit_address" var="editAddressURL" />

<aui:form action="<%= editAddressURL %>" cssClass="container-fluid-1280" method="post" name="fm">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="className" type="hidden" value="<%= Account.class.getName() %>" />
	<aui:input name="classPK" type="hidden" value="<%= String.valueOf(koroneikiAccount.getAccountId()) %>" />
	<aui:input name="addressId" type="hidden" value="<%= String.valueOf(addressId) %>" />

	<aui:model-context bean="<%= address %>" model="<%= Address.class %>" />

	<aui:fieldset-group>
		<aui:fieldset>
			<aui:input checked="<%= (address != null) ? address.isPrimary() : false %>" id="addressPrimary" label="primary" name="addressPrimary" type="checkbox" />

			<liferay-ui:error key="<%= NoSuchListTypeException.class.getName() + Account.class.getName() + ListTypeConstants.ADDRESS %>" message="please-select-a-type" />

			<aui:select label="type" listType='<%= "com.liferay.portal.kernel.model.Contact" + ListTypeConstants.ADDRESS %>' name="addressTypeId" />

			<liferay-ui:error exception="<%= AddressStreetException.class %>" message="please-enter-a-valid-street" />

			<aui:input fieldParam="addressStreet1" id="addressStreet1" name="street1" required="<%= true %>" />

			<aui:input fieldParam="addressStreet2" id="addressStreet2" name="street2" />

			<aui:input fieldParam="addressStreet3" id="addressStreet3" name="street3" />

			<liferay-ui:error exception="<%= AddressCityException.class %>" message="please-enter-a-valid-city" />

			<aui:input fieldParam="addressCity" id="addressCity" name="city" required="<%= true %>" />

			<liferay-ui:error exception="<%= NoSuchCountryException.class %>" message="please-select-a-country" />

			<aui:select label="country" name="addressCountryId" />

			<liferay-ui:error exception="<%= NoSuchRegionException.class %>" message="please-select-a-region" />

			<aui:select label="region" name="addressRegionId" />

			<liferay-ui:error exception="<%= AddressZipException.class %>" message="please-enter-a-valid-postal-code" />

			<div class="form-group">
				<label class="control-label" for="<portlet:namespace />addressZip">
					<liferay-ui:message key="postal-code" />

					<span hidden id="<portlet:namespace />addressZipRequiredWrapper">
						<aui:icon cssClass="reference-mark text-warning" image="asterisk" markupView="lexicon" />

						<span class="hide-accessible"><liferay-ui:message key="required" /></span>
					</span>
				</label>

				<aui:input fieldParam="addressZip" id="addressZip" label="" name="zip" />
			</div>

			<aui:input cssClass="mailing-ctrl" fieldParam="addressMailing" id="addressMailing" name="mailing" />
		</aui:fieldset>
	</aui:fieldset-group>

	<aui:button-row>
		<aui:button primary="<%= true %>" type="submit" />

		<aui:button href="<%= redirect %>" type="cancel" />
	</aui:button-row>

	<aui:script use="liferay-address,liferay-dynamic-select">
		new Liferay.DynamicSelect(
			[
				{
					select: '<portlet:namespace />addressCountryId',
					selectData: Liferay.Address.getCountries,
					selectDesc: 'nameCurrentValue',
					selectId: 'countryId',
					selectSort: '<%= true %>',
					selectVal: '<%= countryId %>'
				},
				{
					select: '<portlet:namespace />addressRegionId',
					selectData: Liferay.Address.getRegions,
					selectDesc: 'name',
					selectId: 'regionId',
					selectVal: '<%= regionId %>'
				}
			]
		);
	</aui:script>
</aui:form>

<aui:script use="liferay-form">
	const addressCountry = document.getElementById('<portlet:namespace />addressCountryId');

	function checkCountry(countryId) {
		Liferay.Service(
			'/country/get-country',
			{
				countryId: countryId
			},
			function(response, err) {
				if (err) {
					console.error(err);
				}
				else {
					updateAddressZipRequired(response.zipRequired);
				}
			}
		);
	}

	function handleSelectChange(event) {
		const value = Number(event.currentTarget.value);

		if (value > 0) {
			checkCountry(value);
		}
		else {
			updateAddressZipRequired(false);
		}
	}

	function updateAddressZipRequired(required) {
		const addressZipRequiredWrapper = document.getElementById('<portlet:namespace />addressZipRequiredWrapper');
		const formValidator = Liferay.Form.get('<portlet:namespace />fm').formValidator;

		const rules = formValidator._getAttr('rules');

		if (required) {
			addressZipRequiredWrapper.removeAttribute('hidden');
		}
		else {
			addressZipRequiredWrapper.setAttribute('hidden', true);
		}

		rules.<portlet:namespace />addressZip = {required: required};
	}

	if (addressCountry) {
		addressCountry.addEventListener('change', handleSelectChange);

		<c:if test="<%= countryId > 0 %>">
			checkCountry(<%= countryId %>);
		</c:if>
	}
</aui:script>