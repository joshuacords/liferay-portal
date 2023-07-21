<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String notificationNavigationItem = ParamUtil.getString(request, "notificationNavigationItem", "view-all-notification-templates");

CommerceNotificationQueueEntriesDisplayContext commerceNotificationQueueEntriesDisplayContext = (CommerceNotificationQueueEntriesDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

PortletURL portletURL = commerceNotificationQueueEntriesDisplayContext.getPortletURL();

portletURL.setParameter("notificationNavigationItem", notificationNavigationItem);

Map<String, String> contextParams = new HashMap<>();

contextParams.put("commerceChannelId", String.valueOf(commerceNotificationQueueEntriesDisplayContext.getCommerceChannelId()));
%>

<commerce-ui:dataset-display
	clayCreationMenu="<%= commerceNotificationQueueEntriesDisplayContext.getNotificationTemplateClayCreationMenu() %>"
	contextParams="<%= contextParams %>"
	dataProviderKey="<%= CommerceNotificationTemplateClayTable.NAME %>"
	id="<%= CommerceNotificationTemplateClayTable.NAME %>"
	itemsPerPage="<%= 10 %>"
	namespace="<%= renderResponse.getNamespace() %>"
	pageNumber="<%= 1 %>"
	portletURL="<%= portletURL %>"
	showSearch="<%= false %>"
/>