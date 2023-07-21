<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

ProductPurchase productPurchase = (ProductPurchase)row.getObject();

ProductEntry productEntry = productPurchase.getProductEntry();
%>

<liferay-ui:icon-menu
	direction="left-side"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
	showWhenSingleIcon="<%= true %>"
>
	<portlet:renderURL var="editURL">
		<portlet:param name="mvcRenderCommandName" value="/products_admin/edit_product_purchase" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="productPurchaseId" value="<%= String.valueOf(productPurchase.getProductPurchaseId()) %>" />
	</portlet:renderURL>

	<liferay-ui:icon
		message="edit"
		url="<%= editURL %>"
	/>

	<liferay-security:permissionsURL
		modelResource="<%= ProductPurchase.class.getName() %>"
		modelResourceDescription="<%= productEntry.getName() %>"
		resourcePrimKey="<%= String.valueOf(productPurchase.getProductPurchaseId()) %>"
		var="permissionsURL"
		windowState="<%= LiferayWindowState.POP_UP.toString() %>"
	/>

	<liferay-ui:icon
		message="permissions"
		method="get"
		url="<%= permissionsURL %>"
		useDialog="<%= true %>"
	/>

	<portlet:actionURL name="/products_admin/edit_product_purchase" var="deleteURL">
		<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="productPurchaseId" value="<%= String.valueOf(productPurchase.getProductPurchaseId()) %>" />
	</portlet:actionURL>

	<liferay-ui:icon-delete
		confirmation="are-you-sure-you-want-to-delete-this-purchase"
		url="<%= deleteURL %>"
	/>
</liferay-ui:icon-menu>