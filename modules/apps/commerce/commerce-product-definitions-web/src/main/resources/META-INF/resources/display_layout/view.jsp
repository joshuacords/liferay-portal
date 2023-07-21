<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CPDefinitionDisplayLayoutDisplayContext cpDefinitionDisplayLayoutDisplayContext = (CPDefinitionDisplayLayoutDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

Map<String, String> contextParams = new HashMap<>();

contextParams.put("commerceChannelId", String.valueOf(cpDefinitionDisplayLayoutDisplayContext.getCommerceChannelId()));
%>

<commerce-ui:dataset-display
	clayCreationMenu="<%= cpDefinitionDisplayLayoutDisplayContext.getClayCreationMenu() %>"
	contextParams="<%= contextParams %>"
	dataProviderKey="<%= CommerceProductDisplayPageClayTable.NAME %>"
	id="<%= CommerceProductDisplayPageClayTable.NAME %>"
	itemsPerPage="<%= 10 %>"
	namespace="<%= renderResponse.getNamespace() %>"
	pageNumber="<%= 1 %>"
	portletURL="<%= currentURLObj %>"
/>