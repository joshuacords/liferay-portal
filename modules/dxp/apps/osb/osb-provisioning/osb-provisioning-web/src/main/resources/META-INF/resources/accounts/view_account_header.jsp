<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ViewAccountDisplayContext viewAccountDisplayContext = ProvisioningWebComponentProvider.getViewAccountDisplayContext(renderRequest, renderResponse, request);

AccountDisplay accountDisplay = viewAccountDisplayContext.getAccountDisplay();
%>

<liferay-ui:error key="syncInUse" message="sync-to-customer-portal-is-currently-in-use.-please-wait-and-try-again-later" />

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
		<c:if test="<%= accountDisplay.hasSubscription() || accountDisplay.isPartner() %>">
			<portlet:actionURL name="/accounts/sync_to_customer_portal" var="syncToCustomerPortalURL">
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="accountKey" value="<%= accountDisplay.getKey() %>" />
			</portlet:actionURL>

			<aui:form action="<%= syncToCustomerPortalURL %>" method="post" name="fm1">
				<aui:button cssClass="btn-secondary btn-sm" href="<%= syncToCustomerPortalURL %>" value="sync-to-customer-portal" />
			</aui:form>
		</c:if>

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