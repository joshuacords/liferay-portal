<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommercePricingClassPriceListDisplayContext commercePricingClassPriceListDisplayContext = (CommercePricingClassPriceListDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

boolean hasPermission = commercePricingClassPriceListDisplayContext.hasPermission();

CommercePricingClass commercePricingClass = commercePricingClassPriceListDisplayContext.getCommercePricingClass();

Map<String, String> contextParams = new HashMap<>();

contextParams.put("commercePricingClassId", String.valueOf(commercePricingClass.getCommercePricingClassId()));
%>

<c:if test="<%= hasPermission %>">
	<div class="col-12 pt-4">
		<commerce-ui:dataset-display
			contextParams="<%= contextParams %>"
			dataProviderKey="<%= CommercePricingDataSetConstants.COMMERCE_DATA_SET_KEY_PRICING_CLASSES_PRICE_LISTS %>"
			id="<%= CommercePricingDataSetConstants.COMMERCE_DATA_SET_KEY_PRICING_CLASSES_PRICE_LISTS %>"
			itemsPerPage="<%= 10 %>"
			namespace="<%= renderResponse.getNamespace() %>"
			pageNumber="<%= 1 %>"
			portletURL="<%= currentURLObj %>"
			style="stacked"
		/>
	</div>
</c:if>