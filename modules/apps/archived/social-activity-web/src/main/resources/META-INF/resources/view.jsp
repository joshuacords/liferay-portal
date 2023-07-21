<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<div class="container-fluid-1280">
	<div class="card main-content-card">
		<div class="card-row card-row-padded">

			<%
			Map<String, Boolean> activitySettingsMap = (Map<String, Boolean>)request.getAttribute(SocialActivityWebKeys.SOCIAL_ACTIVITY_SETTINGS_MAP);
			%>

			<liferay-ui:error-principal />

			<portlet:actionURL name="/social_activity/edit_activity_settings" var="editURL" />

			<aui:form action="<%= editURL %>" cssClass="update-socialactivity-form" method="post" name="fm">
				<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
				<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
				<aui:input id="settingsJSON" name="settingsJSON" type="hidden" />

				<h4>
					<liferay-ui:message key="enable-social-activity-for" />:
				</h4>

				<aui:row cssClass="social-activity social-activity-settings" id="settings">
					<aui:col cssClass="social-activity-items" width="<%= 20 %>">

						<%
						for (Map.Entry<String, Boolean> entry : activitySettingsMap.entrySet()) {
							String className = entry.getKey();

							String localizedClassName = ResourceActionsUtil.getModelResource(locale, className);
						%>

							<h4 class="social-activity-item" data-modelName="<%= className %>" title="<%= localizedClassName %>">
								<div class="social-activity-item-content">
									<aui:input disabled="<%= !SocialActivityPermissionUtil.contains(permissionChecker, themeDisplay.getSiteGroupId(), ActionKeys.CONFIGURATION) %>" inlineField="<%= true %>" label="" name='<%= className + ".enabled" %>' title="enabled" type="checkbox" value="<%= entry.getValue() %>" />

									<a class="settings-label" href="javascript:;"><%= localizedClassName %></a>
								</div>
							</h4>

						<%
						}
						%>

					</aui:col>

					<aui:col cssClass="social-activity-details" width="<%= 80 %>" />
				</aui:row>

				<%
				List<String> activityDefinitionLanguageKeys = new ArrayList<String>();

				for (String modelName : activitySettingsMap.keySet()) {
					List<SocialActivityDefinition> activityDefinitions = SocialConfigurationUtil.getActivityDefinitions(modelName);

					for (SocialActivityDefinition activityDefinition : activityDefinitions) {
						activityDefinitionLanguageKeys.add("'" + modelName + "." + activityDefinition.getLanguageKey() + "': \"" + activityDefinition.getName(locale) + "\"");
					}
				}
				%>

				<aui:script use="liferay-social-activity-admin">
					new Liferay.Portlet.SocialActivity.Admin({
						activityDefinitionLanguageKeys: {
							<%= StringUtil.merge(activityDefinitionLanguageKeys) %>
						},
						counterSettings: {

							<%
							SocialActivityGroupServiceConfiguration socialActivityGroupServiceConfiguration = ConfigurationProviderUtil.getConfiguration(SocialActivityGroupServiceConfiguration.class, new CompanyServiceSettingsLocator(company.getCompanyId(), "com.liferay.social.activity.configuration.SocialActivityGroupServiceConfiguration"));
							%>

							contributionIncrements: [
								<%= StringUtil.merge(socialActivityGroupServiceConfiguration.contributionIncrements()) %>
							],
							contributionLimitValues: [
								<%= StringUtil.merge(socialActivityGroupServiceConfiguration.contributionLimitValues()) %>
							],
							participationIncrements: [
								<%= StringUtil.merge(socialActivityGroupServiceConfiguration.participationIncrements()) %>
							],
							participationLimitValues: [
								<%= StringUtil.merge(socialActivityGroupServiceConfiguration.participationLimitValues()) %>
							]
						},
						namespace: '<portlet:namespace />',
						portletId: '<%= portletDisplay.getId() %>'
					});
				</aui:script>
			</aui:form>
		</div>
	</div>
</div>