<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceOrderListDisplayContext commerceOrderListDisplayContext = (CommerceOrderListDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);
%>

<commerce-ui:headless-dataset-display
	apiUrl="/o/headless-commerce-admin-order/v1.0/orders?nestedFields=account,channel"
	clayHeadlessDataSetActionTemplates="<%= commerceOrderListDisplayContext.getClayHeadlessDataSetActionTemplates() %>"
	formId="fm"
	id="<%= CommerceOrderDataSetConstants.COMMERCE_DATA_SET_KEY_ALL_ORDERS %>"
	itemsPerPage="<%= 20 %>"
	namespace="<%= renderResponse.getNamespace() %>"
	pageNumber="<%= 1 %>"
	portletURL="<%= commerceOrderListDisplayContext.getPortletURL() %>"
	style="fluid"
/>