<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ViewAccountRelatedAccountsDisplayContext viewAccountRelatedAccountsDisplayContext = ProvisioningWebComponentProvider.getViewAccountRelatedAccountsDisplayContext(renderRequest, renderResponse, request);

PortletURL portletURL = viewAccountRelatedAccountsDisplayContext.getPortletURL();
%>

<div class="details-table table-striped">
	<liferay-util:include page="/common/tabs.jsp" servletContext="<%= application %>">
		<liferay-util:param name="names" value="<%= viewAccountRelatedAccountsDisplayContext.getTabsNames() %>" />
		<liferay-util:param name="param" value="tabs2" />
		<liferay-util:param name="url" value="<%= portletURL.toString() %>" />
		<liferay-util:param name="values" value="<%= viewAccountRelatedAccountsDisplayContext.getTabsValues() %>" />
	</liferay-util:include>

	<liferay-ui:search-container
		id="related-accounts"
		searchContainer="<%= viewAccountRelatedAccountsDisplayContext.getSearchContainer() %>"
	>
		<clay:management-toolbar
			clearResultsURL="<%= viewAccountRelatedAccountsDisplayContext.getClearResultsURL() %>"
			elementClasses="full-width"
			itemsTotal="<%= searchContainer.getTotal() %>"
			searchActionURL="<%= viewAccountRelatedAccountsDisplayContext.getCurrentURL() %>"
			searchContainerId="related-accounts"
			selectable="<%= false %>"
			showSearch="<%= true %>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.osb.provisioning.web.internal.display.context.AccountDisplay"
			keyProperty="accountKey"
			modelVar="curAccountDisplay"
		>
			<portlet:renderURL var="rowURL">
				<portlet:param name="mvcRenderCommandName" value="/accounts/view_account" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="accountKey" value="<%= curAccountDisplay.getKey() %>" />
			</portlet:renderURL>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="name-code"
			>
				<%= HtmlUtil.escape(curAccountDisplay.getName()) %>

				<div class="secondary-information">
					<%= HtmlUtil.escape(curAccountDisplay.getCode()) %>
				</div>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="support-end-date"
				value="<%= curAccountDisplay.getSupportEndDate() %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="partner"
				value="<%= HtmlUtil.escape(curAccountDisplay.getPartnerTeamName()) %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="region"
				value="<%= curAccountDisplay.getRegion() %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="sla-tier"
			>
				<%= HtmlUtil.escape(curAccountDisplay.getSLAName()) %>

				<div class="secondary-information">
					<%= curAccountDisplay.getTier() %>
				</div>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="state"
			>
				<span class="label <%= curAccountDisplay.getSubscriptionStateStyle() %>"><%= curAccountDisplay.getSubscriptionState() %></span>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
			paginate="<%= false %>"
			resultRowSplitter="<%= viewAccountRelatedAccountsDisplayContext.getAccountResultRowSplitter() %>"
		/>
	</liferay-ui:search-container>
</div>