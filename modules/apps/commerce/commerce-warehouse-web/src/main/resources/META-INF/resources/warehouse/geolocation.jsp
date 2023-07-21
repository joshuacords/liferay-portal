<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceInventoryWarehousesDisplayContext commerceInventoryWarehousesDisplayContext = (CommerceInventoryWarehousesDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);
%>

<liferay-ui:error-marker
	key="<%= WebKeys.ERROR_SECTION %>"
	value="geolocation"
/>

<aui:model-context bean="<%= commerceInventoryWarehousesDisplayContext.getCommerceInventoryWarehouse() %>" model="<%= CommerceInventoryWarehouse.class %>" />

<aui:fieldset>
	<aui:input name="latitude" />

	<aui:input name="longitude" />
</aui:fieldset>