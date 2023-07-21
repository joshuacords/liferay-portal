<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String forecastChartRootElementId = renderResponse.getNamespace() + "-forecast-chart";
CommerceContext commerceContext = (CommerceContext)request.getAttribute(CommerceWebKeys.COMMERCE_CONTEXT);

CommerceDashboardForecastDisplayContext commerceDashboardForecastDisplayContext = (CommerceDashboardForecastDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

String assetCategoryIdsString = commerceDashboardForecastDisplayContext.getAssetCategoryIds();

String categoryIds = "[]";

if (Validator.isNotNull(assetCategoryIdsString)) {
	categoryIds = jsonSerializer.serializeDeep(assetCategoryIdsString.split(StringPool.COMMA));
}

String accountIds = "[]";

CommerceAccount commerceAccount = commerceContext.getCommerceAccount();

if (commerceAccount != null) {
	accountIds = jsonSerializer.serializeDeep(new Long[] {commerceAccount.getCommerceAccountId()});
}
%>

<c:if test="<%= commerceDashboardForecastDisplayContext.hasViewPermission() %>">
	<div id="<%= forecastChartRootElementId %>">
		<span aria-hidden="true" class="loading-animation"></span>
	</div>

	<aui:script require="commerce-dashboard-web/js/forecast/index.es as chart">
	chart.default('<%= forecastChartRootElementId %>', {
		APIBaseUrl:
			'/o/headless-commerce-machine-learning/v1.0/accountCategoryForecasts/by-monthlyRevenue',
		accountIds: <%= accountIds %>,
		categoryIds: <%= categoryIds %>,
		noAccountErrorMessage: Liferay.Language.get('no-account-selected'),
		noDataErrorMessage: Liferay.Language.get('no-data-available'),
		portletId: '<%= portletDisplay.getId() %>'
	});
	</aui:script>
</c:if>