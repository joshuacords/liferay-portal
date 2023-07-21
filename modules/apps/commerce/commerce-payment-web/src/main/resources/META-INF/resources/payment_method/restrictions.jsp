<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommercePaymentMethodGroupRelsDisplayContext commercePaymentMethodGroupRelsDisplayContext = (CommercePaymentMethodGroupRelsDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

long commerceChannelId = commercePaymentMethodGroupRelsDisplayContext.getCommerceChannelId();
%>

<portlet:actionURL name="editCommercePaymentMethodAddressRestriction" var="editCommercePaymentMethodAddressRestrictionActionURL" />

<aui:form action="<%= editCommercePaymentMethodAddressRestrictionActionURL %>" method="post" name="fm">
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="commerceChannelId" type="hidden" value="<%= commerceChannelId %>" />

	<%
	Map<String, String> contextParams = new HashMap<>();

	contextParams.put("commerceChannelId", String.valueOf(commerceChannelId));
	%>

	<commerce-ui:dataset-display
		contextParams="<%= contextParams %>"
		dataProviderKey="<%= CommercePaymentRestrictionsPageClayTable.NAME %>"
		formId="fm"
		id="<%= CommercePaymentRestrictionsPageClayTable.NAME %>"
		itemsPerPage="<%= commercePaymentMethodGroupRelsDisplayContext.getCommerceCountriesCount() %>"
		namespace="<%= renderResponse.getNamespace() %>"
		pageNumber="<%= 1 %>"
		portletURL="<%= currentURLObj %>"
		selectedItemsKey="commerceCountryId"
		showPagination="<%= false %>"
	/>
</aui:form>