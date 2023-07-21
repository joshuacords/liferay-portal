<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceTaxFixedRatesDisplayContext commerceTaxFixedRatesDisplayContext = (CommerceTaxFixedRatesDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

Map<String, String> contextParams = new HashMap<>();

contextParams.put("commerceChannelId", String.valueOf(commerceTaxFixedRatesDisplayContext.getCommerceChannelId()));
contextParams.put("commerceTaxMethodId", String.valueOf(commerceTaxFixedRatesDisplayContext.getCommerceTaxMethodId()));
%>

<commerce-ui:dataset-display
	clayCreationMenu="<%= commerceTaxFixedRatesDisplayContext.getClayCreationMenu() %>"
	contextParams="<%= contextParams %>"
	dataProviderKey="<%= CommerceTaxRateClayTable.NAME %>"
	id="<%= CommerceTaxRateClayTable.NAME %>"
	itemsPerPage="<%= 10 %>"
	namespace="<%= renderResponse.getNamespace() %>"
	pageNumber="<%= 1 %>"
	portletURL="<%= commerceTaxFixedRatesDisplayContext.getPortletURL() %>"
	showSearch="<%= false %>"
/>