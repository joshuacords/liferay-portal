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
ViewAccountDisplayContext viewAccountDisplayContext = ProvisioningWebComponentProvider.getViewAccountDisplayContext(renderRequest, renderResponse, request);

AccountDisplay accountDisplay = viewAccountDisplayContext.getAccountDisplay();
%>

<div class="account-header autofit-row provisioning-accounts">
	<svg class="autofit-col header-icon">
		<use xlink:href="#account-icon" />
	</svg>

	<div class="autofit-col autofit-col-expand">
		<liferay-ui:breadcrumb
			showCurrentGroup="<%= false %>"
			showGuestGroup="<%= false %>"
			showLayout="<%= true %>"
			showParentGroups="<%= false %>"
		/>

		<h3 class="account-name">
			<span class="account-code">
				<a href="<%= viewAccountDisplayContext.getAccountURL() %>"><%= HtmlUtil.escape(accountDisplay.getCode()) %></a>
			</span>

			<%= HtmlUtil.escape(accountDisplay.getName()) %>
		</h3>

		<ul class="header-details">
			<li>
				<div class="header-label">
					<liferay-ui:message key="state" />
				</div>

				<span class="label <%= accountDisplay.getSubscriptionStateStyle() %>"><%= accountDisplay.getSubscriptionState() %></span>
			</li>
			<li>
				<div class="header-label">
					<liferay-ui:message key="support-region" />
				</div>

				<%= accountDisplay.getRegion() %>
			</li>
			<li>
				<div class="header-label">
					<liferay-ui:message key="country" />
				</div>

				<%= accountDisplay.getPrimaryCountry() %>
			</li>
			<li>
				<div class="header-label">
					<liferay-ui:message key="sla" />
				</div>

				<%= HtmlUtil.escape(accountDisplay.getSLAName()) %>
			</li>
			<li>
				<div class="header-label">
					<liferay-ui:message key="tier" />
				</div>

				<%= accountDisplay.getTier() %>
			</li>
			<li>
				<div class="header-label">
					<liferay-ui:message key="ewsa" />
				</div>

				<%= accountDisplay.getEWSA() %>
			</li>
			<li>
				<div class="header-label">
					<liferay-ui:message key="support-seats" />
				</div>

				<%= accountDisplay.getSupportSeatContactUsage() %>
			</li>
			<li>
				<div class="header-label">
					<liferay-ui:message key="fls-partner" />
				</div>

				<c:choose>
					<c:when test="<%= Validator.isNotNull(accountDisplay.getFirstLineSupportTeamKey()) %>">
						<portlet:renderURL var="firstLineSupportTeamURL">
							<portlet:param name="mvcRenderCommandName" value="/accounts/view_team" />
							<portlet:param name="teamKey" value="<%= accountDisplay.getFirstLineSupportTeamKey() %>" />
						</portlet:renderURL>

						<a href="<%= firstLineSupportTeamURL %>">
							<%= HtmlUtil.escape(accountDisplay.getFirstLineSupportTeamName()) %>
						</a>
					</c:when>
					<c:otherwise>
						<%= HtmlUtil.escape(accountDisplay.getFirstLineSupportTeamName()) %>
					</c:otherwise>
				</c:choose>
			</li>
			<li>
				<div class="header-label">
					<liferay-ui:message key="primary-contact" />
				</div>

				<%= viewAccountDisplayContext.getPrimaryContactEmailAddress() %>
			</li>
		</ul>
	</div>

	<div class="header-buttons">
		<portlet:actionURL name="/accounts/sync_to_zendesk" var="syncToZendeskURL">
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="accountKey" value="<%= accountDisplay.getKey() %>" />
		</portlet:actionURL>

		<aui:form action="<%= syncToZendeskURL %>" method="post" name="fm1">
			<aui:button cssClass="btn-secondary btn-sm" href="<%= syncToZendeskURL %>" value="sync-to-zendesk" />
		</aui:form>

		<c:if test="<%= viewAccountDisplayContext.hasManageLicenseKeysPermission() %>">
			<div>
				<a class="btn btn-secondary btn-sm" href="<%= viewAccountDisplayContext.getGenerateLicenseURL() %>">
					<span class="lfr-btn-label"><liferay-ui:message key="generate-license" /></span>
				</a>
			</div>
		</c:if>
	</div>
</div>