<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
int totalReviewableUADEntitiesCount = (int)request.getAttribute(UADWebKeys.TOTAL_UAD_ENTITIES_COUNT);
SearchContainer<UADApplicationSummaryDisplay> uadApplicationSummarySearchContainer = (SearchContainer<UADApplicationSummaryDisplay>)request.getAttribute(WebKeys.SEARCH_CONTAINER);

portletDisplay.setShowBackIcon(true);

LiferayPortletURL usersAdminURL = liferayPortletResponse.createLiferayPortletURL(UsersAdminPortletKeys.USERS_ADMIN, PortletRequest.RENDER_PHASE);

portletDisplay.setURLBack(usersAdminURL.toString());

renderResponse.setTitle(StringBundler.concat(selectedUser.getFullName(), " - ", LanguageUtil.get(request, "personal-data-erasure")));
%>

<liferay-util:include page="/uad_data_navigation_bar.jsp" servletContext="<%= application %>" />

<div class="container-fluid container-fluid-max-xl container-form-lg">
	<aui:form method="post" name="nonreviewableUADDataForm">
		<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
		<aui:input name="p_u_i_d" type="hidden" value="<%= String.valueOf(selectedUser.getUserId()) %>" />

		<div class="sheet sheet-lg">
			<div class="sheet-header">
				<h2 class="sheet-title"><liferay-ui:message key="auto-anonymize-data" /></h2>
			</div>

			<div class="sheet-section">
				<div class="text-muted"><liferay-ui:message key="auto-anonymize-data-that-does-not-require-review" /></div>
			</div>

			<div class="sheet-section">
				<h3 class="sheet-subtitle">
					<liferay-ui:message key="status-summary" />
				</h3>

				<div class="autofit-row autofit-row-center">
					<div class="autofit-col autofit-col-expand">
						<div class="autofit-section">
							<strong><liferay-ui:message key="remaining-items" />: </strong><%= totalReviewableUADEntitiesCount %>
						</div>
					</div>

					<div class="autofit-col">
						<portlet:actionURL name="/anonymize_nonreviewable_uad_data" var="anonymizeURL" />

						<aui:button cssClass="btn-sm" disabled="<%= totalReviewableUADEntitiesCount == 0 %>" onClick='<%= renderResponse.getNamespace() + "confirmAction('nonreviewableUADDataForm', '" + anonymizeURL.toString() + "', '" + UnicodeLanguageUtil.get(request, "are-you-sure-you-want-to-anonymize-the-users-personal-data") + "')" %>' primary="true" value="anonymize" />
					</div>
				</div>
			</div>

			<div class="sheet-section">
				<c:choose>
					<c:when test="<%= totalReviewableUADEntitiesCount == 0 %>">
						<liferay-ui:empty-result-message
							message="all-data-that-does-not-require-review-has-been-anonymized"
						/>
					</c:when>
					<c:otherwise>
						<h3 class="sheet-subtitle"><liferay-ui:message key="applications" /></h3>

						<liferay-ui:search-container
							compactEmptyResultsMessage="<%= true %>"
							searchContainer="<%= uadApplicationSummarySearchContainer %>"
						>
							<liferay-ui:search-container-row
								className="com.liferay.user.associated.data.web.internal.display.UADApplicationSummaryDisplay"
								escapedModel="<%= true %>"
								keyProperty="key"
								modelVar="uadApplicationSummaryDisplay"
							>
								<liferay-ui:search-container-column-text
									cssClass="table-cell-expand table-list-title"
									name="name"
									value="<%= UADLanguageUtil.getApplicationName(uadApplicationSummaryDisplay.getApplicationKey(), locale) %>"
								/>

								<liferay-ui:search-container-column-text
									cssClass="table-cell-expand"
									name="items"
									property="count"
								/>

								<liferay-ui:search-container-column-text
									cssClass="table-cell-expand"
									name="status"
								>
									<clay:label
										label='<%= uadApplicationSummaryDisplay.hasItems() ? StringUtil.toUpperCase(LanguageUtil.get(request, "pending"), locale) : StringUtil.toUpperCase(LanguageUtil.get(request, "done"), locale) %>'
										style='<%= uadApplicationSummaryDisplay.hasItems() ? "warning" : "success" %>'
									/>
								</liferay-ui:search-container-column-text>
							</liferay-ui:search-container-row>

							<liferay-ui:search-iterator
								markupView="lexicon"
								searchResultCssClass="show-quick-actions-on-hover table table-autofit"
							/>
						</liferay-ui:search-container>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</aui:form>
</div>

<%@ include file="/action/confirm_action_js.jspf" %>