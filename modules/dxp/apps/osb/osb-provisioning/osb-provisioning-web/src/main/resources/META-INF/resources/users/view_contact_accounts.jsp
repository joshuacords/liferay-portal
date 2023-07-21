<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ViewContactDisplayContext viewContactDisplayContext = ProvisioningWebComponentProvider.getViewContactDisplayContext(renderRequest, renderResponse, request);
%>

<div class="details-table table-striped">
	<h3 class="panel-title">
		<liferay-ui:message key="contact" />
	</h3>

	<liferay-ui:search-container
		id="contacts"
		searchContainer='<%= viewContactDisplayContext.getContactAccountsSearchContainer("customer") %>'
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
				name="region"
			>
				<%= accountDisplay.getRegion() %>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="roles"
			>
				<%= StringUtil.merge(viewContactDisplayContext.getCustomerContactRoleNames(accountDisplay.getKey()), "<br />") %>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="teams"
			>

				<%
				List<TeamDisplay> teamDisplays = viewContactDisplayContext.getContactAccountTeamDisplays(accountDisplay.getKey());

				for (int i = 0; i < teamDisplays.size(); i++) {
					TeamDisplay teamDisplay = teamDisplays.get(i);
				%>

					<liferay-portlet:renderURL portletName="<%= ProvisioningPortletKeys.ACCOUNTS %>" var="teamURL">
						<portlet:param name="mvcRenderCommandName" value="/accounts/view_team" />
						<portlet:param name="redirect" value="<%= currentURL %>" />
						<portlet:param name="teamKey" value="<%= teamDisplay.getKey() %>" />
					</liferay-portlet:renderURL>

					<a href="<%= teamURL %>"><%= HtmlUtil.escape(teamDisplay.getName()) %></a>

					<%= ((i + 1) < teamDisplays.size()) ? "<br />" : "" %>

				<%
				}
				%>

			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="account-state"
			>
				<span class="label <%= accountDisplay.getSubscriptionStateStyle() %>"><%= accountDisplay.getSubscriptionState() %></span>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>

<div class="details-table table-striped">
	<h3 class="panel-title">
		<liferay-ui:message key="liferay-workers" />
	</h3>

	<liferay-ui:search-container
		id="contacts"
		searchContainer='<%= viewContactDisplayContext.getContactAccountsSearchContainer("worker") %>'
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
				name="region"
			>
				<%= accountDisplay.getRegion() %>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="roles"
			>
				<%= StringUtil.merge(viewContactDisplayContext.getWorkerContactRoleNames(accountDisplay.getKey()), "<br />") %>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="account-state"
			>
				<span class="label <%= accountDisplay.getSubscriptionStateStyle() %>"><%= accountDisplay.getSubscriptionState() %></span>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>