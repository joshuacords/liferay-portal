<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ViewAccountLiferayWorkersDisplayContext viewAccountLiferayWorkersDisplayContext = ProvisioningWebComponentProvider.getViewAccountLiferayWorkersDisplayContext(renderRequest, renderResponse, request);
%>

<div class="details-table table-striped">
	<liferay-ui:error exception="<%= NoSuchContactException.class %>" message="contact-does-not-exist" />

	<liferay-ui:search-container
		id="liferay-workers"
		searchContainer="<%= viewAccountLiferayWorkersDisplayContext.getSearchContainer() %>"
	>
		<clay:management-toolbar
			clearResultsURL="<%= viewAccountLiferayWorkersDisplayContext.getClearResultsURL() %>"
			creationMenu="<%= viewAccountLiferayWorkersDisplayContext.getCreationMenu() %>"
			elementClasses="full-width"
			filterDropdownItems="<%= viewAccountLiferayWorkersDisplayContext.getFilterDropdownItems() %>"
			filterLabelItems="<%= viewAccountLiferayWorkersDisplayContext.getFilterLabelItems() %>"
			itemsTotal="<%= searchContainer.getTotal() %>"
			searchActionURL="<%= viewAccountLiferayWorkersDisplayContext.getCurrentURL() %>"
			searchContainerId="liferay-workers"
			selectable="<%= false %>"
			showSearch="<%= true %>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.osb.provisioning.web.internal.display.context.ContactDisplay"
			modelVar="contactDisplay"
		>
			<liferay-portlet:renderURL portletName="<%= ProvisioningPortletKeys.USERS %>" var="rowURL">
				<portlet:param name="mvcRenderCommandName" value="/users/view_contact" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="contactEmailAddress" value="<%= contactDisplay.getEmailAddress() %>" />
			</liferay-portlet:renderURL>

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
				name="role"
			>
				<%= StringUtil.merge(contactDisplay.getContactRoleNames(), "<br />") %>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="user-status"
			>
				<span class="label <%= contactDisplay.getStatusStyle() %>"><%= contactDisplay.getStatus() %></span>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-jsp
				align="right"
				path="/accounts/account_worker_contact_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>