<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceDiscountDisplayContext commerceDiscountDisplayContext = (CommerceDiscountDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

PortletURL portletURL = commerceDiscountDisplayContext.getPortletURL();
%>

<clay:navigation-bar
	inverted="<%= true %>"
	navigationItems="<%= PricingNavigationItemRegistryUtil.getNavigationItems(renderRequest) %>"
/>

<div class="pt-4">
	<aui:form action="<%= portletURL.toString() %>" cssClass="container-fluid-1280" method="post" name="fm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" />
		<aui:input name="redirect" type="hidden" value="<%= portletURL.toString() %>" />
		<aui:input name="deleteDiscounts" type="hidden" />

		<commerce-ui:headless-dataset-display
			apiUrl="/o/headless-commerce-admin-pricing/v2.0/discounts"
			clayCreationMenu="<%= commerceDiscountDisplayContext.getClayCreationDiscountMenu() %>"
			clayHeadlessDataSetActionTemplates="<%= commerceDiscountDisplayContext.getClayHeadlessDataSetActionDiscountTemplates() %>"
			formId="fm"
			id="<%= CommercePricingDataSetConstants.COMMERCE_DATA_SET_KEY_DISCOUNTS %>"
			itemsPerPage="<%= 10 %>"
			namespace="<%= renderResponse.getNamespace() %>"
			pageNumber="<%= 1 %>"
			portletURL="<%= portletURL %>"
			style="stacked"
		/>
	</aui:form>
</div>