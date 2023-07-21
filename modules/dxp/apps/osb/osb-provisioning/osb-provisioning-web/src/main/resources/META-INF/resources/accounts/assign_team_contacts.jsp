<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
AssignTeamContactsDisplayContext assignTeamContactsDisplayContext = ProvisioningWebComponentProvider.getAssignTeamContactsDisplayContext(renderRequest, renderResponse, request);

SearchContainer searchContainer = assignTeamContactsDisplayContext.getSearchContainer();
%>

<div class="container-fluid container-fluid-max-xl">
	<clay:management-toolbar
		clearResultsURL="<%= assignTeamContactsDisplayContext.getClearResultsURL() %>"
		filterDropdownItems="<%= assignTeamContactsDisplayContext.getFilterCustomerRoleDropdownItems() %>"
		filterLabelItems="<%= assignTeamContactsDisplayContext.getFilterCustomerRoleLabelItems() %>"
		itemsTotal="<%= searchContainer.getTotal() %>"
		searchActionURL="<%= assignTeamContactsDisplayContext.getCurrentURL() %>"
		searchContainerId="assignContacts"
		searchFormName="searchFm"
		showSearch="<%= true %>"
	/>

	<liferay-ui:search-container
		id="assignContacts"
		searchContainer="<%= searchContainer %>"
		var="contactsSearchContainer"
	>
		<liferay-ui:search-container-row
			className="com.liferay.osb.provisioning.web.internal.display.context.ContactDisplay"
			keyProperty="emailAddress"
			modelVar="contactDisplay"
		>
			<liferay-ui:search-container-column-text
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

<aui:script use="liferay-search-container">
	var searchContainer = Liferay.SearchContainer.get(
		'<portlet:namespace />assignContacts'
	);

	searchContainer.on('rowToggled', function(event) {
		var selectedItems = event.elements.allSelectedElements;

		var data = '';

		if (selectedItems && selectedItems.size()) {
			data = selectedItems.attr('value').join(',');
		}

		Liferay.Util.getOpener().Liferay.fire(
			'<portlet:namespace />assignContacts',
			{
				data: data
			}
		);
	});
</aui:script>