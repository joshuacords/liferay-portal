<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceSubscriptionEntryDisplayContext commerceSubscriptionEntryDisplayContext = (CommerceSubscriptionEntryDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

boolean hasManageCommerceSubscriptionEntryPermission = commerceSubscriptionEntryDisplayContext.hasManageCommerceSubscriptionEntryPermission();

java.util.Map<String, String> contextParams = new java.util.HashMap<>();

contextParams.put("companyId", String.valueOf(themeDisplay.getCompanyId()));
%>

<div class="row">
	<div class="col-12 mb-4">
		<c:if test="<%= hasManageCommerceSubscriptionEntryPermission %>">
			<commerce-ui:dataset-display
				contextParams="<%= contextParams %>"
				dataProviderKey="<%= CommerceSubscriptionDataSetConstants.COMMERCE_DATA_SET_KEY_SUBSCRIPTION_ENTRIES %>"
				id="<%= CommerceSubscriptionDataSetConstants.COMMERCE_DATA_SET_KEY_SUBSCRIPTION_ENTRIES %>"
				itemsPerPage="<%= 10 %>"
				namespace="<%= renderResponse.getNamespace() %>"
				pageNumber="<%= 1 %>"
				portletURL="<%= commerceSubscriptionEntryDisplayContext.getPortletURL() %>"
				style="fluid"
			/>
		</c:if>
	</div>
</div>