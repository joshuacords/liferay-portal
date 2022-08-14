<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */
--%>

<%@ include file="/init.jsp" %>

<%
SegmentsExperimentDisplayContext segmentsExperimentDisplayContext = (SegmentsExperimentDisplayContext)request.getAttribute(SegmentsExperimentWebKeys.SEGMENTS_EXPERIMENT_DISPLAY_CONTEXT);
%>

<c:choose>
	<c:when test="<%= SegmentsExperimentUtil.isAnalyticsEnabled(themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId()) %>">
		<div id="<%= renderResponse.getNamespace() %>-segments-experiment-root"></div>

		<aui:script require='<%= npmResolvedPackageName + "/js/index.es as segmentsExperimentsApp" %>'>
			segmentsExperimentsApp.default(
				'<%= renderResponse.getNamespace() + "-segments-experiment-root" %>',
				{
					context: {
						assetsPath:
							'<%= segmentsExperimentDisplayContext.getAssetsPath() %>',
						contentPageEditorNamespace:
							'<%= segmentsExperimentDisplayContext.getContentPageEditorPortletNamespace() %>',
						endpoints: {
							calculateSegmentsExperimentEstimatedDurationURL:
								'<%= segmentsExperimentDisplayContext.getCalculateSegmentsExperimentEstimatedDurationURL() %>',
							createSegmentsExperimentURL:
								'<%= segmentsExperimentDisplayContext.getCreateSegmentsExperimentURL() %>',
							createSegmentsVariantURL:
								'<%= segmentsExperimentDisplayContext.getCreateSegmentsVariantURL() %>',
							deleteSegmentsExperimentURL:
								'<%= segmentsExperimentDisplayContext.getDeleteSegmentsExperimentURL() %>',
							deleteSegmentsVariantURL:
								'<%= segmentsExperimentDisplayContext.getDeleteSegmentsVariantURL() %>',
							editSegmentsExperimentStatusURL:
								'<%= segmentsExperimentDisplayContext.getEditSegmentsExperimentStatusURL() %>',
							editSegmentsExperimentURL:
								'<%= segmentsExperimentDisplayContext.getEditSegmentsExperimentURL() %>',
							editSegmentsVariantLayoutURL:
								'<%= segmentsExperimentDisplayContext.getEditSegmentsVariantLayoutURL() %>',
							editSegmentsVariantURL:
								'<%= segmentsExperimentDisplayContext.getEditSegmentsVariantURL() %>',
							runSegmentsExperimentURL:
								'<%= segmentsExperimentDisplayContext.getRunSegmentsExperimenttURL() %>'
						},
						namespace: '<portlet:namespace />',
						page: {
							classPK: '<%= themeDisplay.getPlid() %>',
							classNameId:
								'<%= PortalUtil.getClassNameId(Layout.class.getName()) %>',
							type: '<%= layout.getType() %>'
						}
					},
					props: {
						historySegmentsExperiments: <%= segmentsExperimentDisplayContext.getHistorySegmentsExperimentsJSONArray(locale) %>,
						initialSegmentsVariants: <%= segmentsExperimentDisplayContext.getSegmentsExperimentRelsJSONArray(locale) %>,
						segmentsExperiences: <%= segmentsExperimentDisplayContext.getSegmentsExperiencesJSONArray(locale) %>,
						segmentsExperiment: <%= segmentsExperimentDisplayContext.getSegmentsExperimentJSONObject(locale) %>,
						segmentsExperimentGoals: <%= segmentsExperimentDisplayContext.getSegmentsExperimentGoalsJSONArray(locale) %>,
						selectedSegmentsExperienceId:
							'<%= segmentsExperimentDisplayContext.getSelectedSegmentsExperienceId() %>',
						winnerSegmentsVariantId:
							'<%= segmentsExperimentDisplayContext.getWinnerSegmentsExperienceId() %>'
					}
				}
			);
		</aui:script>
	</c:when>
	<c:otherwise>
		<div class="p-3 pt-5 text-center">
			<liferay-ui:icon
				alt="connect-to-analytics-cloud"
				src='<%= PortalUtil.getPathContext(request) + "/assets/ac-icon.svg" %>'
			/>

			<h4 class="mt-3"><liferay-ui:message key="connect-to-analytics-cloud" /></h4>

			<p><liferay-ui:message key="connect-to-analytics-cloud-help" /></p>

			<c:choose>
				<c:when test="<%= SegmentsExperimentUtil.isAnalyticsEnabled(themeDisplay.getCompanyId()) %>">
					<liferay-ui:icon
						label="<%= true %>"
						linkCssClass="btn btn-primary btn-sm mb-4"
						markupView="lexicon"
						message="open-analytics-cloud"
						target="_blank"
						url="<%= segmentsExperimentDisplayContext.getLiferayAnalyticsURL(themeDisplay.getCompanyId()) %>"
					/>
				</c:when>
				<c:otherwise>
					<liferay-ui:icon
						label="<%= true %>"
						linkCssClass="btn btn-primary btn-sm mb-4"
						markupView="lexicon"
						message="start-free-trial"
						target="_blank"
						url="<%= SegmentsExperimentUtil.ANALYTICS_CLOUD_TRIAL_URL %>"
					/>
				</c:otherwise>
			</c:choose>

			<portlet:actionURL name="/hide_segments_experiment_panel" var="hideSegmentsExperimentPanelURL">
				<portlet:param name="redirect" value="<%= themeDisplay.getLayoutFriendlyURL(layout) %>" />
			</portlet:actionURL>

			<liferay-ui:icon
				label="<%= true %>"
				linkCssClass="btn btn-secondary btn-sm mb-4"
				markupView="lexicon"
				message="hide-ab-test-panel"
				url="<%= hideSegmentsExperimentPanelURL %>"
			/>
		</div>
	</c:otherwise>
</c:choose>