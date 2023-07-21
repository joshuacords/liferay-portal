<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ViewTeamDisplayContext viewTeamDisplayContext = ProvisioningWebComponentProvider.getViewTeamDisplayContext(renderRequest, renderResponse, request);

PortletURL searchURL = viewTeamDisplayContext.getPortletURL();
%>

<div class="management-bar management-bar-light navbar navbar-expand-md">
	<div class="container-fluid">
		<div class="navbar-form navbar-form-autofit navbar-overlay navbar-overlay-sm-down">
			<div class="container-fluid">
				<aui:form action="<%= searchURL %>" method="get" name="fm">
					<liferay-portlet:renderURLParams portletURL="<%= searchURL %>" />

					<div class="input-group search-input">
						<aui:input cssClass="input-group-inset-after" label="" name="keywords" placeholder="search-for" wrapperCssClass="input-group-item" />

						<span class="input-group-inset-item input-group-inset-item-after">
							<button aria-label="search" class="btn btn-unstyled" type="submit">
								<liferay-ui:icon
									icon="search"
									markupView="lexicon"
								/>
							</button>
						</span>
					</div>
				</aui:form>
			</div>
		</div>
	</div>
</div>

<liferay-ui:search-container
	id="accounts"
	searchContainer="<%= viewTeamDisplayContext.getFLSAssignedAccountsSearchContainer() %>"
>
	<liferay-ui:search-container-row
		className="com.liferay.osb.provisioning.web.internal.display.context.AccountDisplay"
		modelVar="accountDisplay"
	>
		<liferay-portlet:renderURL portletName="<%= ProvisioningPortletKeys.ACCOUNTS %>" var="rowURL">
			<portlet:param name="mvcRenderCommandName" value="/accounts/view_account" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="accountKey" value="<%= accountDisplay.getKey() %>" />
		</liferay-portlet:renderURL>

		<liferay-ui:search-container-column-text
			href="<%= rowURL %>"
			name="account-name-code"
		>
			<%= HtmlUtil.escape(accountDisplay.getName()) %>

			<div class="secondary-information">
				<%= HtmlUtil.escape(accountDisplay.getCode()) %>
			</div>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			href="<%= rowURL %>"
			name="support-end-date"
			value="<%= accountDisplay.getSupportEndDate() %>"
		/>

		<liferay-ui:search-container-column-text
			href="<%= rowURL %>"
			name="region"
		>
			<%= accountDisplay.getRegion() %>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			href="<%= rowURL %>"
			name="sla-tier"
		>
			<%= HtmlUtil.escape(accountDisplay.getSLAName()) %>

			<div class="secondary-information">
				<%= accountDisplay.getTier() %>
			</div>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			href="<%= rowURL %>"
			name="account-state"
		>
			<span class="label <%= accountDisplay.getSubscriptionStateStyle() %>"><%= accountDisplay.getSubscriptionState() %></span>
		</liferay-ui:search-container-column-text>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator
		markupView="lexicon"
	/>
</liferay-ui:search-container>