<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<liferay-util:include page="/common/view_account_search_header.jsp" servletContext="<%= application %>" />

<%
AccountSearchDisplayContext accountSearchDisplayContext = ProvisioningWebComponentProvider.getAccountSearchDisplayContext(renderRequest, renderResponse, request);

renderResponse.setTitle(StringBundler.concat(LanguageUtil.get(request, "accounts"), " - ", company.getName()));
%>

<div class="container-fluid home provisioning-accounts">
	<portlet:renderURL var="addAccountURL">
		<portlet:param name="mvcRenderCommandName" value="/accounts/add_account" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
	</portlet:renderURL>

	<div class="title-bar">
		<h3><liferay-ui:message key="accounts" /></h3>

		<c:if test="<%= accountSearchDisplayContext.hasManageAccountsPermission() %>">
			<clay:link
				ariaLabel='<%= LanguageUtil.get(request, "new-account") %>'
				elementClasses="btn btn-primary nav-btn nav-btn-monospaced"
				href="<%= addAccountURL %>"
				icon="plus"
				title='<%= LanguageUtil.get(request, "new-account") %>'
			/>
		</c:if>
	</div>

	<clay:management-toolbar
		displayContext="<%= ProvisioningWebComponentProvider.getViewAccountsManagementToolbarDisplayContext(liferayPortletRequest, liferayPortletResponse, request, accountSearchDisplayContext.getSearchContainer()) %>"
		elementClasses="full-width"
		searchInputName="accountSearchKeywords"
		showSearch="<%= false %>"
	/>

	<liferay-ui:search-container
		cssClass="table-hover"
		searchContainer="<%= accountSearchDisplayContext.getSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.osb.provisioning.web.internal.display.context.AccountDisplay"
			keyProperty="accountKey"
			modelVar="accountDisplay"
		>
			<portlet:renderURL var="rowURL">
				<portlet:param name="mvcRenderCommandName" value="/accounts/view_account" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="accountKey" value="<%= accountDisplay.getKey() %>" />
			</portlet:renderURL>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="name-code"
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
				name="partner"
				value="<%= HtmlUtil.escape(accountDisplay.getPartnerTeamName()) %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="region"
				value="<%= accountDisplay.getRegion() %>"
			/>

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
				name="state"
			>
				<span class="label <%= accountDisplay.getSubscriptionStateStyle() %>"><%= accountDisplay.getSubscriptionState() %></span>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>