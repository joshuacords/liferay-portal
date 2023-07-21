<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
PortletURL portletURL = renderResponse.createRenderURL();

SearchContainer searchContainer = new SearchContainer(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, 5, portletURL, null, null);

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

Map<String, SocialActivityCounter> activityCounters = (Map<String, SocialActivityCounter>)row.getObject();

SocialActivityCounter contributionActivityCounter = activityCounters.get(SocialActivityCounterConstants.NAME_CONTRIBUTION);

if (contributionActivityCounter == null) {
	contributionActivityCounter = new SocialActivityCounterImpl();

	contributionActivityCounter.setName(SocialActivityCounterConstants.NAME_CONTRIBUTION);
}

if (!contributionActivityCounter.isActivePeriod(SocialActivityCounterConstants.PERIOD_LENGTH_SYSTEM)) {
	contributionActivityCounter.setCurrentValue(0);
}

SocialActivityCounter participationActivityCounter = activityCounters.get(SocialActivityCounterConstants.NAME_PARTICIPATION);

if (participationActivityCounter == null) {
	participationActivityCounter = new SocialActivityCounterImpl();

	participationActivityCounter.setName(SocialActivityCounterConstants.NAME_PARTICIPATION);
}

if (!participationActivityCounter.isActivePeriod(SocialActivityCounterConstants.PERIOD_LENGTH_SYSTEM)) {
	participationActivityCounter.setCurrentValue(0);
}

activityCounters.remove(SocialActivityCounterConstants.NAME_CONTRIBUTION);
activityCounters.remove(SocialActivityCounterConstants.NAME_PARTICIPATION);
%>

<liferay-ui:user-display
	userId="<%= GetterUtil.getLong(row.getPrimaryKey()) %>"
	userName=""
>
	<c:if test="<%= userDisplay != null %>">
		<div class="user-rank">
			<span class="statistics-label"><liferay-ui:message key="rank" />:</span> <%= searchContainer.getStart() + row.getPos() + 1 %>
		</div>

		<div class="contribution-score">
			<span class="statistics-label"><liferay-ui:message key="contribution-score" />:</span> <%= contributionActivityCounter.getCurrentValue() %>

			<c:if test="<%= socialUserStatisticsPortletInstanceConfiguration.showTotals() %>">
				<span>(<liferay-ui:message key="total" />: <%= contributionActivityCounter.getTotalValue() %>)</span>
			</c:if>
		</div>

		<div class="participation-score">
			<span class="statistics-label"><liferay-ui:message key="participation-score" />:</span> <%= participationActivityCounter.getCurrentValue() %>

			<c:if test="<%= socialUserStatisticsPortletInstanceConfiguration.showTotals() %>">
				<span>(<liferay-ui:message key="total" />: <%= participationActivityCounter.getTotalValue() %>)</span>
			</c:if>
		</div>
	</c:if>
</liferay-ui:user-display>

<c:if test="<%= socialUserStatisticsPortletInstanceConfiguration.displayAdditionalActivityCounters() %>">
	<div class="separator"><!-- --></div>

	<%
	for (SocialActivityCounter activityCounter : activityCounters.values()) {
		if (!activityCounter.isActivePeriod(SocialActivityCounterConstants.PERIOD_LENGTH_SYSTEM)) {
			activityCounter.setCurrentValue(0);
		}
	%>

		<div class="social-counter-<%= activityCounter.getName() %>">
			<span class="statistics-label"><liferay-ui:message key='<%= "user.statistics." + activityCounter.getName() %>' />:</span> <%= activityCounter.getCurrentValue() %>

			<c:if test="<%= socialUserStatisticsPortletInstanceConfiguration.showTotals() %>">
				<span>(<liferay-ui:message key="total" />: <%= activityCounter.getTotalValue() %>)</span>
			</c:if>
		</div>

	<%
	}
	%>

</c:if>