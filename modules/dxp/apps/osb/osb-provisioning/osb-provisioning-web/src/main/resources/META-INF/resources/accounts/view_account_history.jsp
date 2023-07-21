<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ViewAccountDisplayContext viewAccountDisplayContext = ProvisioningWebComponentProvider.getViewAccountDisplayContext(renderRequest, renderResponse, request);

long auditSetId = 0;
String currentDate = StringPool.BLANK;
%>

<c:choose>
	<c:when test="<%= viewAccountDisplayContext.getAuditEntryDisplaysCount() < 1 %>">
		<ul class="instructions list-group">
			<li class="list-group-header">
				<p class="list-group-header-title">
					<liferay-ui:message key="account-history" />
				</p>
			</li>
			<li class="list-group-item list-group-item-flex">
				<div className="list-group-text text-muted">
					<liferay-ui:message key="there-is-no-account-history-yet" />
				</div>
			</li>
		</ul>
	</c:when>
	<c:otherwise>
		<liferay-ui:search-container
			deltaConfigurable="<%= false %>"
			id="auditEntriesContainer"
			searchContainer="<%= viewAccountDisplayContext.getAuditEntryDisplaysSearchContainer() %>"
			var="auditEntriesSearchContainer"
		>
			<liferay-ui:search-container-row
				className="com.liferay.osb.provisioning.web.internal.display.context.AuditEntryDisplay"
				keyProperty="auditEntryKey"
				modelVar="auditEntryDisplay"
			>
				<c:if test="<%= !currentDate.equals(auditEntryDisplay.getDateCreated()) %>">
					<div class="list-group-header">
						<span class="list-group-header-title"><%= auditEntryDisplay.getDateCreated() %></span>
					</div>
				</c:if>

				<c:if test="<%= auditSetId != auditEntryDisplay.getAuditSetId() %>">
					<aui:row cssClass="detail-title">
						<aui:col cssClass="description" width="<%= 80 %>">
							<c:if test="<%= Validator.isNotNull(auditEntryDisplay.getAgentPortraitURL()) %>">
								<span class="sticker sticker-circle sticker-secondary sticker-sm">
									<span class="sticker-overlay">
										<img alt="<%= LanguageUtil.get(request, "agent-avatar") %>" class="sticker-img" src="<%= auditEntryDisplay.getAgentPortraitURL() %>" />
									</span>
								</span>
							</c:if>

							<%= HtmlUtil.escape(auditEntryDisplay.getAgentName()) %> > <%= HtmlUtil.escape(auditEntryDisplay.getSummary()) %>

							<c:if test="<%= Validator.isNotNull(auditEntryDisplay.getDescription()) %>">
								> <%= HtmlUtil.escape(auditEntryDisplay.getDescription()) %>
							</c:if>
						</aui:col>

						<aui:col cssClass="timestamp" width="<%= 20 %>">
							<%= auditEntryDisplay.getTimeCreated() %>
						</aui:col>
					</aui:row>

					<aui:row cssClass="detail-label">
						<aui:col width="<%= 20 %>">
							<span class="list-group-header-title"><liferay-ui:message key="field" /></span>
						</aui:col>

						<aui:col width="<%= 40 %>">
							<span class="list-group-header-title"><liferay-ui:message key="original-value" /></span>
						</aui:col>

						<aui:col width="<%= 40 %>">
							<span class="list-group-header-title"><liferay-ui:message key="new-value" /></span>
						</aui:col>
					</aui:row>
				</c:if>

				<aui:row>
					<aui:col cssClass="col-field" width="<%= 20 %>">
						<%= auditEntryDisplay.getField() %>
					</aui:col>

					<aui:col width="<%= 40 %>">
						<%= HtmlUtil.escape(auditEntryDisplay.getOldValue()) %>
					</aui:col>

					<aui:col width="<%= 40 %>">
						<%= HtmlUtil.escape(auditEntryDisplay.getNewValue()) %>
					</aui:col>
				</aui:row>

				<%
				auditSetId = auditEntryDisplay.getAuditSetId();
				currentDate = auditEntryDisplay.getDateCreated();
				%>

			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator
				markupView="lexicon"
			/>
		</liferay-ui:search-container>
	</c:otherwise>
</c:choose>