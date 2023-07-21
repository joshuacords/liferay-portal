<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceOrderEditDisplayContext commerceOrderEditDisplayContext = (CommerceOrderEditDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceOrder commerceOrder = commerceOrderEditDisplayContext.getCommerceOrder();
%>

<portlet:actionURL name="editCommerceOrder" var="editCommerceOrderBillingAddressActionURL" />

<commerce-ui:modal-content
	contentCssClasses="p-0"
	title='<%= LanguageUtil.get(request, "billing-address") %>'
>
	<aui:form action="<%= editCommerceOrderBillingAddressActionURL %>" method="post" name="fm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" value="selectBillingAddress" />
		<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
		<aui:input name="commerceOrderId" type="hidden" value="<%= commerceOrder.getCommerceOrderId() %>" />

		<%
		Map<String, String> contextParams = new HashMap<>();

		contextParams.put("commerceOrderId", String.valueOf(commerceOrder.getCommerceOrderId()));
		%>

		<commerce-ui:dataset-display
			clayCreationMenu='<%= commerceOrderEditDisplayContext.getCommerceAddressClayCreationMenu("editCommerceOrderBillingAddress") %>'
			contextParams="<%= contextParams %>"
			dataProviderKey="<%= CommerceOrderDataSetConstants.COMMERCE_DATA_SET_KEY_BILLING_ADDRESSES %>"
			formId="fm"
			id="<%= CommerceOrderDataSetConstants.COMMERCE_DATA_SET_KEY_BILLING_ADDRESSES %>"
			itemsPerPage="<%= 10 %>"
			namespace="<%= renderResponse.getNamespace() %>"
			pageNumber="<%= 1 %>"
			portletURL="<%= currentURLObj %>"
			selectedItems="<%= Collections.singletonList(String.valueOf(commerceOrder.getBillingAddressId())) %>"
			selectedItemsKey="addressId"
			selectionType="single"
		/>
	</aui:form>
</commerce-ui:modal-content>