<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceShipmentItemDisplayContext commerceShipmentItemDisplayContext = (CommerceShipmentItemDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceShipmentItem commerceShipmentItem = commerceShipmentItemDisplayContext.getCommerceShipmentItem();

CommerceOrderItem commerceOrderItem = commerceShipmentItemDisplayContext.getCommerceOrderItem();

portletDisplay.setShowBackIcon(true);

if (Validator.isNull(redirect)) {
	redirect = currentURL;
}

portletDisplay.setURLBack(redirect);
%>

<portlet:actionURL name="editCommerceShipmentItem" var="editCommerceShipmentItemActionURL" />

<commerce-ui:side-panel-content
	title='<%= LanguageUtil.format(request, "warehouse-availability-x", commerceOrderItem.getSku()) %>'
>
	<aui:form action="<%= editCommerceShipmentItemActionURL %>" method="post" name="fm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
		<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
		<aui:input name="commerceShipmentId" type="hidden" value="<%= commerceShipmentItem.getCommerceShipmentId() %>" />
		<aui:input name="commerceShipmentItemId" type="hidden" value="<%= commerceShipmentItem.getCommerceShipmentItemId() %>" />
		<aui:input name="commerceOrderItemId" type="hidden" value="<%= commerceOrderItem.getCommerceOrderItemId() %>" />

		<div class="row text-center">
			<div class="col-sm-6">
				<liferay-ui:message key="outstanding-quantity" />: <%= commerceOrderItem.getQuantity() - commerceOrderItem.getShippedQuantity() %>
			</div>

			<div class="col-sm-6">
				<liferay-ui:message key="quantity-in-shipment" />: <%= commerceShipmentItemDisplayContext.getToSendQuantity() %>
			</div>
		</div>

		<hr class="mt-0" />

		<%
		Map<String, String> contextParams = new HashMap<>();

		contextParams.put("commerceOrderItemId", String.valueOf(commerceOrderItem.getCommerceOrderItemId()));
		contextParams.put("commerceShipmentId", String.valueOf(commerceShipmentItem.getCommerceShipmentId()));
		contextParams.put("commerceShipmentItemId", String.valueOf(commerceShipmentItem.getCommerceShipmentItemId()));
		%>

		<commerce-ui:dataset-display
			contextParams="<%= contextParams %>"
			dataProviderKey="<%= CommerceShipmentDataSetConstants.COMMERCE_DATA_SET_KEY_INVENTORY_WAREHOUSE_ITEM %>"
			formId="fm"
			id="<%= CommerceShipmentDataSetConstants.COMMERCE_DATA_SET_KEY_INVENTORY_WAREHOUSE_ITEM %>"
			itemsPerPage="<%= 10 %>"
			namespace="<%= renderResponse.getNamespace() %>"
			pageNumber="<%= 1 %>"
			portletURL="<%= currentURLObj %>"
			showManagementBar="<%= false %>"
		/>

		<aui:button-row>
			<aui:button type="submit" value="save" />
		</aui:button-row>
	</aui:form>
</commerce-ui:side-panel-content>