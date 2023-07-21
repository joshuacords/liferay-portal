<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
boolean branchingEnabled = GetterUtil.getBoolean((String)renderRequest.getAttribute(StagingProcessesWebKeys.BRANCHING_ENABLED));
LayoutRevision layoutRevision = (LayoutRevision)renderRequest.getAttribute(WebKeys.LAYOUT_REVISION);
List<LayoutSetBranch> layoutSetBranches = (List<LayoutSetBranch>)renderRequest.getAttribute(StagingProcessesWebKeys.LAYOUT_SET_BRANCHES);
liveGroup = (Group)renderRequest.getAttribute(StagingProcessesWebKeys.LIVE_GROUP);
Layout liveLayout = (Layout)renderRequest.getAttribute(StagingProcessesWebKeys.LIVE_LAYOUT);
String liveURL = (String)renderRequest.getAttribute(StagingProcessesWebKeys.LIVE_URL);
String remoteSiteURL = (String)renderRequest.getAttribute(StagingProcessesWebKeys.REMOTE_SITE_URL);
String remoteURL = (String)renderRequest.getAttribute(StagingProcessesWebKeys.REMOTE_URL);
stagingGroup = (Group)renderRequest.getAttribute(StagingProcessesWebKeys.STAGING_GROUP);
String stagingURL = (String)renderRequest.getAttribute(StagingProcessesWebKeys.STAGING_URL);

if (liveLayout != null) {
	request.setAttribute("view.jsp-typeSettingsProperties", liveLayout.getTypeSettingsProperties());
}

boolean layoutTypeContent = false;

if ((layout != null) && Objects.equals(layout.getType(), LayoutConstants.TYPE_CONTENT)) {
	layoutTypeContent = true;
}
%>

<c:if test="<%= themeDisplay.isShowStagingIcon() %>">
	<c:if test="<%= liveGroup != null %>">
		<ul class="control-menu-nav">
			<c:if test="<%= !stagingBarDisplayContext.isDraftLayout() %>">
				<li class="control-menu-nav-item dropdown staging-options-toggle visible-xs">
					<a class="control-menu-icon dropdown-toggle" data-toggle="dropdown" href="javascript:;" value="staging">
						<span class="control-menu-icon-label">
							<c:choose>
								<c:when test="<%= group.isStagingGroup() || group.isStagedRemotely() %>">
									<c:if test="<%= stagingGroup != null %>">
										<liferay-ui:message key="staging" />
									</c:if>
								</c:when>
								<c:otherwise>
									<liferay-ui:message key="live" />
								</c:otherwise>
							</c:choose>
						</span>

						<aui:icon image="caret-double-l" markupView="lexicon" />
					</a>

					<ul class="dropdown-menu">
						<li>
							<a href="#" id="viewPageStagingOptions">
								<liferay-ui:message key="view-page-staging-options" />
							</a>
						</li>

						<c:if test="<%= !group.isStagingGroup() && !group.isStagedRemotely() && (stagingGroup != null) %>">
							<li>
								<a href="<%= HtmlUtil.escape(stagingURL) %>">
									<liferay-ui:message key="go-to-staging" />
								</a>
							</li>
						</c:if>

						<c:if test="<%= group.isStagingGroup() %>">
							<c:choose>
								<c:when test="<%= group.isStagedRemotely() %>">
									<li>
										<a href="<%= HtmlUtil.escape(remoteURL) %>">
											<liferay-ui:message key="go-to-remote-live" />
										</a>
									</li>
								</c:when>
								<c:when test="<%= group.isStagingGroup() && Validator.isNotNull(liveURL) %>">
									<li>
										<a href="<%= HtmlUtil.escape(liveURL) %>">
											<liferay-ui:message key="go-to-live" />
										</a>
									</li>
								</c:when>
							</c:choose>
						</c:if>
					</ul>
				</li>

				<c:choose>
					<c:when test="<%= group.isStagingGroup() || group.isStagedRemotely() %>">
						<c:if test="<%= stagingGroup != null %>">
							<li class="active control-menu-link control-menu-nav-item hidden-xs staging-link">
								<a class="control-menu-icon" id="stagingLink" value="staging">
									<liferay-ui:message key="staging" />
								</a>
							</li>
						</c:if>
					</c:when>
					<c:otherwise>
						<li class="control-menu-link control-menu-nav-item hidden-xs staging-link">
							<a class="control-menu-icon" href="<%= (layoutSetBranches != null) ? null : stagingURL %>" value="staging">
								<liferay-ui:message key="staging" />
							</a>
						</li>
					</c:otherwise>
				</c:choose>

				<c:choose>
					<c:when test="<%= group.isStagedRemotely() %>">
						<li class="control-menu-link control-menu-nav-item hidden-xs live-link">
							<c:choose>
								<c:when test="<%= !remoteSiteURL.isEmpty() %>">
									<a class="control-menu-icon" href="<%= HtmlUtil.escape(remoteSiteURL) %>" value="go-to-remote-live">
										<aui:icon image="home" label="go-to-remote-live" markupView="lexicon" />
									</a>
								</c:when>
								<c:when test="<%= SessionErrors.contains(renderRequest, AuthException.class) %>">
									<a class="control-menu-icon" value="go-to-remote-live">
										<aui:icon image="home" label="go-to-remote-live" markupView="lexicon" />
									</a>

									<liferay-ui:icon
										icon="exclamation-full"
										markupView="lexicon"
										message="an-error-occurred-while-authenticating-user"
										toolTip="<%= true %>"
									/>
								</c:when>
								<c:when test="<%= SessionErrors.contains(renderRequest, RemoteExportException.class) %>">
									<a class="control-menu-icon" value="go-to-remote-live">
										<aui:icon image="home" label="go-to-remote-live" markupView="lexicon" />
									</a>

									<liferay-ui:icon
										icon="exclamation-full"
										markupView="lexicon"
										message="the-connection-to-the-remote-live-site-cannot-be-established-due-to-a-network-problem"
										toolTip="<%= true %>"
									/>
								</c:when>
								<c:otherwise>
									<a class="control-menu-icon" value="go-to-remote-live">
										<aui:icon image="home" label="go-to-remote-live" markupView="lexicon" />
									</a>

									<liferay-ui:icon
										icon="exclamation-full"
										markupView="lexicon"
										message="an-unexpected-error-occurred"
										toolTip="<%= true %>"
									/>
								</c:otherwise>
							</c:choose>
						</li>
					</c:when>
					<c:when test="<%= group.isStagingGroup() %>">
						<c:if test="<%= Validator.isNotNull(liveURL) %>">
							<li class="control-menu-link control-menu-nav-item hidden-xs live-link">
								<a class="control-menu-icon" href="<%= HtmlUtil.escape(liveURL) %>" value="live">
									<liferay-ui:message key="live" />
								</a>
							</li>
						</c:if>
					</c:when>
					<c:otherwise>
						<li class="active control-menu-link control-menu-nav-item hidden-xs live-link">
							<a class="control-menu-icon taglib-icon" id="liveLink" value="live">
								<liferay-ui:message key="live" />
							</a>
						</li>
					</c:otherwise>
				</c:choose>
			</c:if>

			<c:if test="<%= !layout.isSystem() || layout.isTypeControlPanel() || !Objects.equals(layout.getFriendlyURL(), PropsValues.CONTROL_PANEL_LAYOUT_FRIENDLY_URL) %>">
				<li class="control-menu-nav-item staging-bar">
					<div class="control-menu-level-2">
						<div class="container-fluid container-fluid-max-xl">
							<div class="control-menu-level-2-heading visible-xs">
								<liferay-ui:message key="staging-options" />

								<button aria-label="<%= LanguageUtil.get(request, "close") %>" class="close" id="closeStagingOptions" type="button">
									<aui:icon image="times" markupView="lexicon" />
								</button>
							</div>

							<%
							String cssClass = "control-menu-level-2-nav control-menu-nav staging-bar-level-2-nav";

							if (stagingBarDisplayContext.isDraftLayout()) {
								cssClass = cssClass + " justify-content-end";
							}
							%>

							<ul class="<%= cssClass %>">
								<c:choose>
									<c:when test="<%= group.isStagingGroup() || group.isStagedRemotely() %>">
										<c:if test="<%= stagingGroup != null %>">
											<liferay-ui:error exception="<%= AuthException.class %>">
												<liferay-ui:message arguments="<%= user.getScreenName() %>" key="an-error-occurred-while-authenticating-user-x-on-the-remote-server" />
											</liferay-ui:error>

											<liferay-ui:error exception="<%= Exception.class %>" message="an-unexpected-error-occurred" />

											<c:choose>
												<c:when test="<%= branchingEnabled %>">
													<li class="control-menu-nav-item staging-bar-level-2-nav-item">
														<div class="staging-bar-flex-column">
															<liferay-util:include page="/view_layout_set_branch_details.jsp" servletContext="<%= application %>" />
														</div>

														<div class="staging-bar-flex-column">
															<c:if test="<%= !layoutRevision.isIncomplete() && !Objects.equals(layout.getType(), LayoutConstants.TYPE_CONTENT) %>">
																<liferay-util:include page="/view_layout_branch_details.jsp" servletContext="<%= application %>" />
															</c:if>
														</div>
													</li>

													<c:if test="<%= !layoutTypeContent %>">
														<li class="control-menu-nav-item staging-bar-level-2-nav-item" id="<portlet:namespace />layoutRevisionStatus">
															<aui:model-context bean="<%= layoutRevision %>" model="<%= LayoutRevision.class %>" />

															<liferay-util:include page="/view_layout_revision_status.jsp" servletContext="<%= application %>" />
														</li>
													</c:if>

													<li class="control-menu-nav-item nav-item-flex-end staging-bar-level-2-nav-item staging-layout-revision-details" id="<portlet:namespace />layoutRevisionDetails">
														<aui:model-context bean="<%= layoutRevision %>" model="<%= LayoutRevision.class %>" />

														<liferay-util:include page="/view_layout_revision_details.jsp" servletContext="<%= application %>" />
													</li>
												</c:when>
												<c:otherwise>
													<c:choose>
														<c:when test="<%= !stagingBarDisplayContext.isDraftLayout() %>">
															<liferay-staging:menu
																cssClass="publish-link"
																onlyActions="<%= true %>"
															/>

															<li>
																<c:choose>
																	<c:when test="<%= liveLayout == null %>">
																		<span class="last-publication-branch">
																			<liferay-ui:message arguments='<%= "<strong>" + HtmlUtil.escape(layout.getName(locale)) + "</strong>" %>' key="page-x-has-not-been-published-to-live-yet" translateArguments="<%= false %>" />
																		</span>
																	</c:when>
																	<c:otherwise>
																		<liferay-util:include page="/last_publication_date_message.jsp" servletContext="<%= application %>" />
																	</c:otherwise>
																</c:choose>
															</li>
														</c:when>
														<c:otherwise>
															<li class="control-menu-nav-item">
																<aui:button cssClass="btn btn-primary btn-sm mr-2" disabled="<%= !stagingBarDisplayContext.isStatusDraft() %>" href="<%= stagingBarDisplayContext.getApproveDraftURL() %>" value="approve-draft" />
															</li>

															<c:choose>
																<c:when test="<%= stagingBarDisplayContext.isDraftLayout() && stagingBarDisplayContext.isStatusDraft() %>">
																	<c:if test="<%= group.isCompany() && GroupPermissionUtil.contains(permissionChecker, group, ActionKeys.PUBLISH_STAGING) %>">
																		<li class="control-menu-nav-item">
																			<aui:button cssClass="btn btn-primary btn-sm" disabled="<%= true %>" value="publish-to-live" />
																		</li>
																	</c:if>
																</c:when>
																<c:otherwise>
																	<liferay-staging:menu
																		cssClass="publish-link"
																		onlyActions="<%= true %>"
																	/>
																</c:otherwise>
															</c:choose>
														</c:otherwise>
													</c:choose>
												</c:otherwise>
											</c:choose>
										</c:if>
									</c:when>
									<c:otherwise>
										<li class="control-menu-nav-item staging-message">
											<div class="alert alert-warning hide warning-content" id="<portlet:namespace />warningMessage">
												<liferay-ui:message key="an-inital-staging-publication-is-in-progress" />
											</div>

											<liferay-util:include page="/last_publication_date_message.jsp" servletContext="<%= application %>" />
										</li>
									</c:otherwise>
								</c:choose>
							</ul>
						</div>
					</div>
				</li>
			</c:if>
		</ul>
	</c:if>

	<c:if test="<%= !branchingEnabled %>">
		<aui:script use="liferay-staging">
			Liferay.StagingBar.init({
				namespace: '<portlet:namespace />',
				portletId: '<%= portletDisplay.getId() %>'
			});
		</aui:script>
	</c:if>

	<aui:script use="aui-base">
		var viewPageStagingOptions = document.getElementById('viewPageStagingOptions');

		var controlMenuLevelTwo = document.querySelector('.control-menu-level-2');

		if (controlMenuLevelTwo && viewPageStagingOptions) {
			viewPageStagingOptions.addEventListener('click', function(event) {
				event.preventDefault();

				controlMenuLevelTwo.classList.add('open');
			});
		}

		var closeStagingOptions = document.getElementById('closeStagingOptions');

		if (closeStagingOptions && controlMenuLevelTwo) {
			closeStagingOptions.addEventListener('click', function(event) {
				event.preventDefault();

				controlMenuLevelTwo.classList.remove('open');
			});
		}

		var stagingLink = document.getElementById('<portlet:namespace />stagingLink');
		var warningMessage = document.getElementById(
			'<portlet:namespace />warningMessage'
		);

		var checkBackgroundTasks = function() {
			Liferay.Service(
				'/backgroundtask.backgroundtask/get-background-tasks-count',
				{
					completed: false,
					groupId: '<%= liveGroup.getGroupId() %>',
					taskExecutorClassName:
						'<%= BackgroundTaskExecutorNames.LAYOUT_STAGING_BACKGROUND_TASK_EXECUTOR %>'
				},
				function(obj) {
					var incomplete = obj > 0;

					if (incomplete) {
						if (stagingLink) {
							stagingLink.classList.add('hide');
						}

						if (warningMessage) {
							warningMessage.classList.remove('hide');
						}

						setTimeout(checkBackgroundTasks, 5000);
					}
					else {
						if (stagingLink) {
							stagingLink.classList.remove('hide');
						}

						if (warningMessage) {
							warningMessage.classList.add('hide');
						}
					}
				}
			);
		};

		checkBackgroundTasks();
	</aui:script>
</c:if>