<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceInventoryWarehouseItemsDisplayContext commerceInventoryWarehouseItemsDisplayContext = (CommerceInventoryWarehouseItemsDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

CommerceInventoryWarehouseItem commerceInventoryWarehouseItem = (CommerceInventoryWarehouseItem)row.getObject();
%>

<liferay-ui:icon-menu
	direction="left-side"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
	showWhenSingleIcon="<%= true %>"
>
	<c:if test="<%= commerceInventoryWarehouseItemsDisplayContext.hasManageCommerceInventoryWarehousePermission() %>">
		<portlet:renderURL var="editURL">
			<portlet:param name="mvcRenderCommandName" value="editCommerceInventoryWarehouseItem" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="commerceInventoryWarehouseItemId" value="<%= String.valueOf(commerceInventoryWarehouseItem.getCommerceInventoryWarehouseItemId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			message="edit"
			url="<%= editURL %>"
		/>

		<portlet:actionURL name="editCommerceInventoryWarehouseItem" var="deleteURL">
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="commerceInventoryWarehouseItemId" value="<%= String.valueOf(commerceInventoryWarehouseItem.getCommerceInventoryWarehouseItemId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			url="<%= deleteURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>