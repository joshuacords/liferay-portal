<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
AccountsDisplayContext accountsDisplayContext = new AccountsDisplayContext(renderRequest, renderResponse, request);

ViewAccountsManagementToolbarDisplayContext viewAccountsManagementToolbarDisplayContext = new ViewAccountsManagementToolbarDisplayContext(liferayPortletRequest, liferayPortletResponse, request, accountsDisplayContext.getSearchContainer());
%>

<clay:navigation-bar
	inverted="<%= true %>"
	navigationItems="<%= viewAccountsManagementToolbarDisplayContext.getNavigationItems() %>"
/>

<liferay-ui:error exception="<%= RequiredAccountException.class %>" message="the-account-cannot-be-deleted-because-it-is-required-by-other-accounts" />

<clay:management-toolbar
	displayContext="<%= viewAccountsManagementToolbarDisplayContext %>"
/>

<div class="container-fluid-1280">
	<liferay-ui:search-container
		searchContainer="<%= accountsDisplayContext.getSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.osb.koroneiki.taproot.model.Account"
			escapedModel="<%= true %>"
			keyProperty="accountId"
			modelVar="koroneikiAccount"
		>
			<portlet:renderURL var="rowURL">
				<portlet:param name="mvcRenderCommandName" value="/accounts_admin/edit_account" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="accountId" value="<%= String.valueOf(koroneikiAccount.getAccountId()) %>" />
			</portlet:renderURL>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="name"
			>
				<span class="lfr-portal-tooltip" data-title="<liferay-ui:message key="account" />">
					<aui:icon cssClass="icon-monospaced" image="users" markupView="lexicon" />
				</span>

				<%= koroneikiAccount.getName() %>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="code"
				value="<%= koroneikiAccount.getCode() %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="description"
				value="<%= koroneikiAccount.getDescription() %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="status"
			>
				<span class="label label-sm label-<%= StringUtil.lowerCase(koroneikiAccount.getStatus()) %>"><%= StringUtil.lowerCase(koroneikiAccount.getStatus()) %></span>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-jsp
				align="right"
				path="/accounts_admin/account_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>