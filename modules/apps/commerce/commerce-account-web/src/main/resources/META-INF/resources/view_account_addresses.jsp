<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceAccountDisplayContext commerceAccountDisplayContext = (CommerceAccountDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceAccount commerceAccount = commerceAccountDisplayContext.getCurrentCommerceAccount();

Map<String, String> contextParams = new HashMap<>();

contextParams.put("commerceAccountId", String.valueOf(commerceAccount.getCommerceAccountId()));

PortletURL portletURL = currentURLObj;

portletURL.setParameter(PortletQName.PUBLIC_RENDER_PARAMETER_NAMESPACE + "backURL", backURL);
%>

<portlet:actionURL name="editCommerceAddress" var="editCommerceAddressActionURL" />

<commerce-ui:dataset-display
	contextParams="<%= contextParams %>"
	dataProviderKey="<%= CommerceAccountAddressClayDataSetDataSetDisplayView.NAME %>"
	id="<%= CommerceAccountAddressClayDataSetDataSetDisplayView.NAME %>"
	itemsPerPage="<%= 10 %>"
	namespace="<%= renderResponse.getNamespace() %>"
	pageNumber="<%= 1 %>"
	portletURL="<%= commerceAccountDisplayContext.getPortletURL() %>"
	style="stacked"
/>

<c:if test="<%= commerceAccountDisplayContext.hasCommerceAccountModelPermissions(commerceAccount, ActionKeys.UPDATE) %>">
	<div class="commerce-cta is-visible">
		<aui:button cssClass="btn-lg btn-primary js-add-address" onClick='<%= renderResponse.getNamespace() + "openAddressModal();" %>' value="add-address" />
	</div>

	<commerce-ui:add-address-modal
		componentId="addressModal"
	/>

	<portlet:actionURL name="editCommerceAddress" var="editCommerceAddressActionURL" />

	<aui:form action="<%= editCommerceAddressActionURL %>" method="post" name="addressFm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.ADD %>" />
		<aui:input name="redirect" type="hidden" value="<%= portletURL %>" />
		<aui:input name="commerceAddressId" type="hidden" value="" />
		<aui:input name="commerceAccountId" type="hidden" value="<%= commerceAccount.getCommerceAccountId() %>" />
		<aui:input name="name" type="hidden" />
		<aui:input name="street1" type="hidden" />
		<aui:input name="description" type="hidden" />
		<aui:input name="city" type="hidden" />
		<aui:input name="zip" type="hidden" />
		<aui:input name="commerceCountryId" type="hidden" />
		<aui:input name="commerceRegionId" type="hidden" />
		<aui:input name="phoneNumber" type="hidden" />
		<aui:input name="addressType" type="hidden" />
	</aui:form>

	<aui:script>
		Liferay.provide(window, '<portlet:namespace />openAddressModal', function(evt) {
			var addressModal = Liferay.component('addressModal');
			addressModal.resetForm();
			addressModal.open();
		});

		Liferay.provide(window, 'editCommerceAddress', function(id) {
			var addressModal = Liferay.component('addressModal');
			addressModal.fetchExistingAddress(id);
			addressModal.open();
		});

		Liferay.provide(window, 'deleteCommerceAddress', function(id) {
			document.querySelector('#<portlet:namespace /><%= Constants.CMD %>').value =
				'<%= Constants.DELETE %>';
			document.querySelector(
				'#<portlet:namespace />commerceAddressId'
			).value = id;

			submitForm(document.<portlet:namespace />addressFm);
		});

		Liferay.componentReady('addressModal').then(function(addressModal) {
			addressModal.on('addressModalSave', function(formData) {
				document.querySelector('#<portlet:namespace />name').value =
					formData.referent;
				document.querySelector('#<portlet:namespace />street1').value =
					formData.address;
				document.querySelector('#<portlet:namespace />city').value =
					formData.city;
				document.querySelector('#<portlet:namespace />zip').value =
					formData.zipCode;
				document.querySelector(
					'#<portlet:namespace />commerceCountryId'
				).value = formData.country;
				document.querySelector('#<portlet:namespace />commerceRegionId').value =
					formData.region;
				document.querySelector('#<portlet:namespace />phoneNumber').value =
					formData.telephone;
				document.querySelector('#<portlet:namespace />addressType').value =
					formData.addressType;

				if (formData.id) {
					document.querySelector(
						'#<portlet:namespace />commerceAddressId'
					).value = formData.id;
					document.querySelector(
						'#<portlet:namespace /><%= Constants.CMD %>'
					).value = '<%= Constants.UPDATE %>';
				}

				addressModal.close();

				submitForm(document.<portlet:namespace />addressFm);
			});
		});
	</aui:script>
</c:if>