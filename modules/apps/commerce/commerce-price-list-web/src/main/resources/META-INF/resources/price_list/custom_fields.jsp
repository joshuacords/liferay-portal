<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommercePriceList commercePriceList = (CommercePriceList)request.getAttribute(CommercePriceListWebKeys.COMMERCE_PRICE_LIST);
%>

<liferay-ui:error-marker
	key="<%= WebKeys.ERROR_SECTION %>"
	value="custom-fields"
/>

<aui:model-context bean="<%= commercePriceList %>" model="<%= CommercePriceList.class %>" />

<liferay-expando:custom-attribute-list
	className="<%= CommercePriceList.class.getName() %>"
	classPK="<%= (commercePriceList != null) ? commercePriceList.getCommercePriceListId() : 0 %>"
	editable="<%= true %>"
	label="<%= true %>"
/>