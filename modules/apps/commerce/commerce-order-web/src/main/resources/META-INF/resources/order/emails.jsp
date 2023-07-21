<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceOrderEditDisplayContext commerceOrderEditDisplayContext = (CommerceOrderEditDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);
%>

<commerce-ui:panel
	bodyClasses="p-0"
	title='<%= LanguageUtil.get(request, "emails") %>'
>

	<%
	Map<String, String> contextParams = new HashMap<>();

	contextParams.put("commerceOrderId", String.valueOf(commerceOrderEditDisplayContext.getCommerceOrderId()));
	%>

	<commerce-ui:dataset-display
		contextParams="<%= contextParams %>"
		dataProviderKey="<%= CommerceOrderDataSetConstants.COMMERCE_DATA_SET_KEY_NOTIFICATIONS %>"
		id="<%= CommerceOrderDataSetConstants.COMMERCE_DATA_SET_KEY_NOTIFICATIONS %>"
		itemsPerPage="<%= 10 %>"
		namespace="<%= renderResponse.getNamespace() %>"
		pageNumber="<%= 1 %>"
		portletURL="<%= commerceOrderEditDisplayContext.getCommerceNotificationQueueEntriesPortletURL() %>"
		showManagementBar="<%= false %>"
	/>
</commerce-ui:panel>