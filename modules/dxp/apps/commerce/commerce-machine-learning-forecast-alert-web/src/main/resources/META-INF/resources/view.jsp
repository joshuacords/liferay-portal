<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceMLForecastAlertEntryListDisplayContext commerceMLForecastAlertEntryListDisplayContext = (CommerceMLForecastAlertEntryListDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);
%>

<div class="container-fluid-1280" id="<portlet:namespace />commerceMLForecastAlertEntryWrapper">
	<liferay-ui:error key="principalException" message="you-do-not-have-permission-to-update-forecast-alert-statuses" />

	<c:choose>
		<c:when test="<%= commerceMLForecastAlertEntryListDisplayContext.hasViewPermission() %>">
			<liferay-ui:search-container
				cssClass="table-nowrap"
				id="commerceMLForecastAlertEntries"
				searchContainer="<%= commerceMLForecastAlertEntryListDisplayContext.getSearchContainer() %>"
			>
				<liferay-ui:search-container-row
					className="com.liferay.commerce.machine.learning.forecast.alert.model.CommerceMLForecastAlertEntry"
					keyProperty="commerceMLForecastAlertEntryId"
					modelVar="commerceMLForecastAlertEntry"
				>

					<%
					CommerceAccount commerceAccount = commerceMLForecastAlertEntryListDisplayContext.getCommerceAccount(commerceMLForecastAlertEntry.getCommerceAccountId());

					long logoId = commerceAccount.getLogoId();
					%>

					<liferay-ui:search-container-column-image
						colspan="<%= 1 %>"
						name="logo"
						src='<%= themeDisplay.getPathImage() + "/organization_logo?img_id=" + logoId + "&t=" + WebServerServletTokenUtil.getToken(logoId) %>'
					/>

					<liferay-ui:search-container-column-text
						cssClass="important table-cell-content"
						name="name"
						value="<%= HtmlUtil.escape(commerceAccount.getName()) %>"
					/>

					<liferay-ui:search-container-column-text
						cssClass="table-cell-content"
						property="forecast"
					/>

					<liferay-ui:search-container-column-text
						cssClass="table-cell-content"
						property="actual"
					/>

					<liferay-ui:search-container-column-text
						cssClass="table-cell-content"
						property="relativeChange"
					/>

					<liferay-ui:search-container-column-jsp
						align="center"
						cssClass="entry-action-column"
						name="status"
						path="/forecast_alert_action.jsp"
					/>
				</liferay-ui:search-container-row>

				<liferay-ui:search-iterator
					markupView="lexicon"
				/>
			</liferay-ui:search-container>
		</c:when>
		<c:otherwise>
			<liferay-ui:message key="you-do-not-have-permission-to-view-forecast-alerts" />
		</c:otherwise>
	</c:choose>
</div>