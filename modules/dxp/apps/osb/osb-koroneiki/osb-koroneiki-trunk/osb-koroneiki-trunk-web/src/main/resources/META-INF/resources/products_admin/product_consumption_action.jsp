<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

ProductConsumption productConsumption = (ProductConsumption)row.getObject();

ProductEntry productEntry = productConsumption.getProductEntry();
%>

<liferay-ui:icon-menu
	direction="left-side"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
	showWhenSingleIcon="<%= true %>"
>
	<portlet:renderURL var="editURL">
		<portlet:param name="mvcRenderCommandName" value="/products_admin/edit_product_consumption" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="productConsumptionId" value="<%= String.valueOf(productConsumption.getProductConsumptionId()) %>" />
	</portlet:renderURL>

	<liferay-ui:icon
		message="view"
		url="<%= editURL %>"
	/>

	<liferay-security:permissionsURL
		modelResource="<%= ProductConsumption.class.getName() %>"
		modelResourceDescription="<%= productEntry.getName() %>"
		resourcePrimKey="<%= String.valueOf(productConsumption.getProductConsumptionId()) %>"
		var="permissionsURL"
		windowState="<%= LiferayWindowState.POP_UP.toString() %>"
	/>

	<liferay-ui:icon
		message="permissions"
		method="get"
		url="<%= permissionsURL %>"
		useDialog="<%= true %>"
	/>

	<portlet:actionURL name="/products_admin/edit_product_consumption" var="deleteURL">
		<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="productConsumptionId" value="<%= String.valueOf(productConsumption.getProductConsumptionId()) %>" />
	</portlet:actionURL>

	<liferay-ui:icon-delete
		confirmation="are-you-sure-you-want-to-delete-this-consumption"
		url="<%= deleteURL %>"
	/>
</liferay-ui:icon-menu>