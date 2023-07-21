<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<liferay-util:include page="/common/view_account_search_header.jsp" servletContext="<%= application %>" />

<%
ContactSearchDisplayContext contactSearchDisplayContext = ProvisioningWebComponentProvider.getContactSearchDisplayContext(renderRequest, renderResponse, request);
%>

<div class="title-bar">
	<h3><liferay-ui:message key="users" /></h3>
</div>

<div class="container-fluid home">
	<liferay-ui:search-container
		cssClass="table-hover"
		searchContainer="<%= contactSearchDisplayContext.getSearchContainer() %>"
	>
		<clay:management-toolbar
			clearResultsURL="<%= contactSearchDisplayContext.getClearResultsURL() %>"
			elementClasses="full-width"
			itemsTotal="<%= searchContainer.getTotal() %>"
			searchActionURL="<%= contactSearchDisplayContext.getCurrentURL() %>"
			searchContainerId="contacts"
			selectable="<%= false %>"
			showSearch="<%= true %>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.osb.provisioning.web.internal.display.context.ContactDisplay"
			keyProperty="contactKey"
			modelVar="contactDisplay"
		>
			<portlet:renderURL var="rowURL">
				<portlet:param name="mvcRenderCommandName" value="/users/view_contact" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="contactEmailAddress" value="<%= contactDisplay.getEmailAddress() %>" />
			</portlet:renderURL>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="name-email"
			>
				<%= HtmlUtil.escape(contactDisplay.getFullName()) %>

				<div class="secondary-information">
					<%= contactDisplay.getEmailAddress() %>
				</div>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="entitlements"
				value="<%= contactDisplay.getEntitlements() %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="no.-of-accounts"
				value="<%= contactDisplay.getAccountsCount() %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="status"
			>
				<span class="label <%= contactDisplay.getStatusStyle() %>"><%= contactDisplay.getStatus() %></span>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>