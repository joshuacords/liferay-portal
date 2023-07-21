<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceShippingFixedOptionsDisplayContext commerceShippingFixedOptionsDisplayContext = (CommerceShippingFixedOptionsDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

Map<String, String> contextParams = new HashMap<>();

contextParams.put("commerceShippingMethodId", String.valueOf(commerceShippingFixedOptionsDisplayContext.getCommerceShippingMethodId()));
%>

<commerce-ui:dataset-display
	clayCreationMenu="<%= commerceShippingFixedOptionsDisplayContext.getClayCreationMenu() %>"
	contextParams="<%= contextParams %>"
	dataProviderKey="<%= CommerceShippingFixedOptionClayTable.NAME %>"
	id="<%= CommerceShippingFixedOptionClayTable.NAME %>"
	itemsPerPage="<%= 10 %>"
	namespace="<%= renderResponse.getNamespace() %>"
	pageNumber="<%= 1 %>"
	portletURL="<%= commerceShippingFixedOptionsDisplayContext.getPortletURL() %>"
	showManagementBar="<%= true %>"
/>