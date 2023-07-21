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

CommerceAccount commerceAccount = commerceShipment.getCommerceAccount();

portletDisplay.setShowBackIcon(true);

if (Validator.isNull(redirect)) {
	portletDisplay.setURLBack(String.valueOf(renderResponse.createRenderURL()));
}
else {
	portletDisplay.setURLBack(redirect);
}
%>

<liferay-ui:error embed="<%= false %>" exception="<%= CommerceShipmentStatusException.class %>" message="please-select-a-valid-warehouse-and-quantity-for-all-shipment-items" />
<liferay-ui:error embed="<%= false %>" exception="<%= CommerceShipmentItemQuantityException.class %>" message="please-add-at-least-one-item-to-the-shipment" />

<commerce-ui:header
	actions="<%= commerceShipmentDisplayContext.getHeaderActionModels() %>"
	bean="<%= commerceShipment %>"
	beanIdLabel="id"
	model="<%= CommerceShipment.class %>"
	thumbnailUrl="<%= commerceShipmentDisplayContext.getCommerceAccountThumbnailURL(commerceAccount, themeDisplay.getPathImage()) %>"
	title="<%= commerceAccount.getName() %>"
	wrapperCssClasses="side-panel-top-anchor"
/>

<div id="<portlet:namespace />editShipmentContainer">
	<liferay-frontend:screen-navigation
		fullContainerCssClass="col-12 pt-4"
		key="<%= CommerceShipmentScreenNavigationConstants.SCREEN_NAVIGATION_KEY_COMMERCE_SHIPMENT_GENERAL %>"
		modelBean="<%= commerceShipment %>"
		portletURL="<%= currentURLObj %>"
	/>
</div>