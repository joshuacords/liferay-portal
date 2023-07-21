<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
Map<String, Object> contextObjects = new HashMap<>();

CommerceOrderContentDisplayContext commerceOrderContentDisplayContext = (CommerceOrderContentDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

contextObjects.put("commerceOrderContentDisplayContext", commerceOrderContentDisplayContext);
%>

<liferay-ddm:template-renderer
	className="<%= CommerceOrderContentPortlet.class.getName() %>"
	contextObjects="<%= contextObjects %>"
	displayStyle="<%= commerceOrderContentDisplayContext.getDisplayStyle(CommercePortletKeys.COMMERCE_ORDER_CONTENT) %>"
	displayStyleGroupId="<%= commerceOrderContentDisplayContext.getDisplayStyleGroupId(CommercePortletKeys.COMMERCE_ORDER_CONTENT) %>"
	entries="<%= commerceOrderContentDisplayContext.getCommerceOrders() %>"
>
	<div class="container-fluid-1280" id="<portlet:namespace />ordersContainer">
		<div class="commerce-orders-container" id="<portlet:namespace />entriesContainer">
			<commerce-ui:dataset-display
				dataProviderKey="<%= CommerceOrderDataSetConstants.COMMERCE_DATA_SET_KEY_PLACED_ORDERS %>"
				id="<%= CommerceOrderDataSetConstants.COMMERCE_DATA_SET_KEY_PLACED_ORDERS %>"
				itemsPerPage="<%= 10 %>"
				namespace="<%= renderResponse.getNamespace() %>"
				pageNumber="<%= 1 %>"
				portletURL="<%= commerceOrderContentDisplayContext.getPortletURL() %>"
				style="stacked"
			/>
		</div>
	</div>
</liferay-ddm:template-renderer>