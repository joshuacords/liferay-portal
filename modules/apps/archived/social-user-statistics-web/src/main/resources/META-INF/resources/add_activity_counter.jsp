<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String portletResource = ParamUtil.getString(request, "portletResource");

String portletResourceNamespace = PortalUtil.getPortletNamespace(portletResource);

int index = ParamUtil.getInteger(request, "index");

socialUserStatisticsPortletInstanceConfiguration = ConfigurationProviderUtil.getConfiguration(SocialUserStatisticsPortletInstanceConfiguration.class, new PortletInstanceSettingsLocator(themeDisplay.getLayout(), portletDisplay.getPortletResource()));

String displayActivityCounterName = "";

String[] displayActivityCounterNames = socialUserStatisticsPortletInstanceConfiguration.displayActivityCounterName();

if (index < displayActivityCounterNames.length) {
	displayActivityCounterName = displayActivityCounterNames[index];
}

List<String> activityCounterNames = SocialConfigurationUtil.getActivityCounterNames(SocialActivityCounterConstants.TYPE_ACTOR);

activityCounterNames.addAll(SocialConfigurationUtil.getActivityCounterNames(SocialActivityCounterConstants.TYPE_CREATOR));

activityCounterNames.add(SocialActivityCounterConstants.NAME_USER_ACHIEVEMENTS);

Collections.sort(activityCounterNames, new SocialActivityCounterNameComparator(locale));
%>

<div class="field-row query-row">
	<aui:select inlineField="<%= true %>" label="" name='<%= portletResourceNamespace + "displayActivityCounterName" + index %>' title="display-activity-counter-name" useNamespace="<%= false %>">

		<%
		for (String activityCounterName : activityCounterNames) {
			if (activityCounterName.equals(SocialActivityCounterConstants.NAME_CONTRIBUTION) || activityCounterName.equals(SocialActivityCounterConstants.NAME_PARTICIPATION)) {
				continue;
			}
		%>

			<aui:option label='<%= "user.statistics." + activityCounterName %>' selected="<%= activityCounterName.equals(displayActivityCounterName) %>" value="<%= activityCounterName %>" />

		<%
		}
		%>

	</aui:select>
</div>