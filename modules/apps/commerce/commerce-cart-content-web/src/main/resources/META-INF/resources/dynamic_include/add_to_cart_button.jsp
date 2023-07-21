<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CPContentHelper cpContentHelper = (CPContentHelper)request.getAttribute(CPContentWebKeys.CP_CONTENT_HELPER);

CPCatalogEntry cpCatalogEntry = cpContentHelper.getCPCatalogEntry(request);
CPInstance cpInstance = cpContentHelper.getDefaultCPInstance(request);

long cpInstanceId = 0;

if (cpInstance != null) {
	cpInstanceId = cpInstance.getCPInstanceId();
}
%>

<liferay-commerce:quantity-input
	CPDefinitionId="<%= cpCatalogEntry.getCPDefinitionId() %>"
	useSelect="<%= true %>"
/>

<liferay-commerce-cart:add-to-cart
	CPDefinitionId="<%= cpCatalogEntry.getCPDefinitionId() %>"
	CPInstanceId="<%= cpInstanceId %>"
	elementClasses="btn-default btn-lg"
	productContentId='<%= renderResponse.getNamespace() + cpCatalogEntry.getCPDefinitionId() + "ProductContent" %>'
	taglibQuantityInputId='<%= renderResponse.getNamespace() + cpCatalogEntry.getCPDefinitionId() + "Quantity" %>'
/>