<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceDiscountDisplayContext commerceDiscountDisplayContext = (CommerceDiscountDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);
%>

<liferay-ui:error-marker
	key="<%= WebKeys.ERROR_SECTION %>"
	value="custom-fields"
/>

<aui:model-context bean="<%= commerceDiscountDisplayContext.getCommerceDiscount() %>" model="<%= CommerceDiscount.class %>" />

<liferay-expando:custom-attribute-list
	className="<%= CommerceDiscount.class.getName() %>"
	classPK="<%= commerceDiscountDisplayContext.getCommerceDiscountId() %>"
	editable="<%= true %>"
	label="<%= true %>"
/>