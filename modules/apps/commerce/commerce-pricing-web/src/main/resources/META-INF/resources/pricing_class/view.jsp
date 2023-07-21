<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommercePricingClassDisplayContext commercePricingClassDisplayContext = (CommercePricingClassDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

renderResponse.setTitle(LanguageUtil.get(request, "product-groups"));
%>

<clay:navigation-bar
	inverted="<%= true %>"
	navigationItems="<%= PricingNavigationItemRegistryUtil.getNavigationItems(renderRequest) %>"
/>

<div class="row">
	<div class="col-12">
		<commerce-ui:dataset-display
			clayCreationMenu="<%= commercePricingClassDisplayContext.getClayCreationMenu() %>"
			dataProviderKey="<%= CommercePricingDataSetConstants.COMMERCE_DATA_SET_KEY_PRICING_CLASSES %>"
			id="<%= CommercePricingDataSetConstants.COMMERCE_DATA_SET_KEY_PRICING_CLASSES %>"
			itemsPerPage="<%= 10 %>"
			namespace="<%= renderResponse.getNamespace() %>"
			pageNumber="<%= 1 %>"
			portletURL="<%= currentURLObj %>"
			style="fluid"
		/>
	</div>
</div>