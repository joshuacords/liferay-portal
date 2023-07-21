<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommercePriceListDisplayContext commercePriceListDisplayContext = (CommercePriceListDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);
%>

<clay:navigation-bar
	inverted="<%= true %>"
	navigationItems="<%= PricingNavigationItemRegistryUtil.getNavigationItems(renderRequest) %>"
/>

<div class="pt-4">
	<aui:form action="<%= currentURLObj.toString() %>" cssClass="container-fluid-1280" method="post" name="fm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" />
		<aui:input name="redirect" type="hidden" value="<%= currentURLObj.toString() %>" />
		<aui:input name="deletePriceLists" type="hidden" />

		<commerce-ui:headless-dataset-display
			apiUrl="<%= commercePriceListDisplayContext.getPriceListsApiUrl(portletName) %>"
			clayCreationMenu="<%= commercePriceListDisplayContext.getClayCreationPriceListMenu() %>"
			clayHeadlessDataSetActionTemplates="<%= commercePriceListDisplayContext.getClayHeadlessDataSetActionPriceListTemplates() %>"
			formId="fm"
			id="<%= CommercePricingDataSetConstants.COMMERCE_DATA_SET_KEY_PRICE_LISTS %>"
			itemsPerPage="<%= 10 %>"
			namespace="<%= renderResponse.getNamespace() %>"
			pageNumber="<%= 1 %>"
			portletURL="<%= currentURLObj %>"
			style="stacked"
		/>
	</aui:form>
</div>