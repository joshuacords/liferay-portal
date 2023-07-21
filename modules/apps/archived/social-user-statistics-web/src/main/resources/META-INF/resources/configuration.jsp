<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
socialUserStatisticsPortletInstanceConfiguration = ConfigurationProviderUtil.getConfiguration(SocialUserStatisticsPortletInstanceConfiguration.class, new PortletInstanceSettingsLocator(themeDisplay.getLayout(), portletDisplay.getPortletResource()));

String[] displayActivityCounterNames = socialUserStatisticsPortletInstanceConfiguration.displayActivityCounterName();

int displayActivityCounterNameIndexCount = displayActivityCounterNames.length;

if (displayActivityCounterNameIndexCount == 0) {
	displayActivityCounterNameIndexCount = 1;
}

String[] displayActivityCounterNameIndexes = new String[displayActivityCounterNameIndexCount];
%>

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="<%= true %>" var="configurationRenderURL" />

<liferay-frontend:edit-form
	action="<%= configurationActionURL %>"
	method="post"
	name="fm"
>
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= configurationRenderURL %>" />

	<liferay-frontend:edit-form-body>
		<liferay-frontend:fieldset-group>
			<liferay-frontend:fieldset
				collapsible="<%= true %>"
				id="userStatisticsRankingsPanel"
				label="ranking"
			>
				<aui:input label="rank-by-contribution" name="preferences--rankByContribution--" type="checkbox" value="<%= socialUserStatisticsPortletInstanceConfiguration.rankByContribution() %>" />

				<aui:input label="rank-by-participation" name="preferences--rankByParticipation--" type="checkbox" value="<%= socialUserStatisticsPortletInstanceConfiguration.rankByParticipation() %>" />
			</liferay-frontend:fieldset>

			<liferay-frontend:fieldset
				collapsible="<%= true %>"
				id="userStatisticsSettingsPanel"
				label="settings"
			>
				<aui:input label="show-header-text" name="preferences--showHeaderText--" type="checkbox" value="<%= socialUserStatisticsPortletInstanceConfiguration.showHeaderText() %>" />

				<aui:input label="show-totals" name="preferences--showTotals--" type="checkbox" value="<%= socialUserStatisticsPortletInstanceConfiguration.showTotals() %>" />
			</liferay-frontend:fieldset>

			<liferay-frontend:fieldset
				collapsible="<%= true %>"
				id="userStatisticsDisplayActivityCounterNamesPanel"
				label="counters"
			>
				<div id="<portlet:namespace />displayActivityCounterNames">
					<aui:input label="display-additional-activity-counters" name="preferences--displayAdditionalActivityCounters--" type="checkbox" value="<%= socialUserStatisticsPortletInstanceConfiguration.displayAdditionalActivityCounters() %>" />

					<liferay-frontend:fieldset
						label=""
					>

						<%
						for (int i = 0; i < displayActivityCounterNameIndexCount; i++) {
							String index = String.valueOf(i);

							displayActivityCounterNameIndexes[i] = index;
						%>

							<div class="lfr-form-row">
								<div class="row-fields">
									<liferay-util:include page="/add_activity_counter.jsp" servletContext="<%= application %>">
										<liferay-util:param name="portletResource" value="<%= portletName %>" />
										<liferay-util:param name="index" value="<%= index %>" />
									</liferay-util:include>
								</div>
							</div>

						<%
						}
						%>

					</liferay-frontend:fieldset>
				</div>

				<aui:input name="displayActivityCounterNameIndexes" type="hidden" value="<%= StringUtil.merge(displayActivityCounterNameIndexes) %>" />

				<aui:script use="liferay-auto-fields">
					var autoFields = new Liferay.AutoFields({
						contentBox: '#<portlet:namespace />displayActivityCounterNames > fieldset',
						fieldIndexes: '<portlet:namespace />displayActivityCounterNameIndexes',
						namespace: '<portlet:namespace />',
						url:
							'<liferay-portlet:renderURL portletName="<%= SocialUserStatisticsPortletKeys.SOCIAL_USER_STATISTICS %>" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><liferay-portlet:param name="mvcPath" value="/add_activity_counter.jsp" /><liferay-portlet:param name="portletResource" value="<%= portletName %>" /></liferay-portlet:renderURL>',
						urlNamespace:
							'<%= "_" + SocialUserStatisticsPortletKeys.SOCIAL_USER_STATISTICS + "_" %>'
					}).render();
				</aui:script>
			</liferay-frontend:fieldset>
		</liferay-frontend:fieldset-group>
	</liferay-frontend:edit-form-body>

	<liferay-frontend:edit-form-footer>
		<aui:button type="submit" />

		<aui:button type="cancel" />
	</liferay-frontend:edit-form-footer>
</liferay-frontend:edit-form>