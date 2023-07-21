<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommercePriceListDisplayContext commercePriceListDisplayContext = (CommercePriceListDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

long commercePriceListId = commercePriceListDisplayContext.getCommercePriceListId();
%>

<c:if test="<%= commercePriceListDisplayContext.hasPermission(commercePriceListId, ActionKeys.UPDATE) %>">

	<%
	Map<String, String> contextParams = new HashMap<>();

	contextParams.put("commercePriceListId", String.valueOf(commercePriceListId));
	%>

	<div class="pt-4">
		<commerce-ui:dataset-display
			clayCreationMenu="<%= commercePriceListDisplayContext.getPriceModifiersClayCreationMenu() %>"
			contextParams="<%= contextParams %>"
			dataProviderKey="<%= CommercePricingDataSetConstants.COMMERCE_DATA_SET_KEY_PRICE_MODIFIERS %>"
			formId="fm"
			id="<%= CommercePricingDataSetConstants.COMMERCE_DATA_SET_KEY_PRICE_MODIFIERS %>"
			itemsPerPage="<%= 10 %>"
			namespace="<%= renderResponse.getNamespace() %>"
			pageNumber="<%= 1 %>"
			portletURL="<%= currentURLObj %>"
			style="stacked"
		/>
	</div>
</c:if>