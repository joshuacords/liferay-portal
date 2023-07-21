<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
PortletURL portletURL = ddmDataProviderDisplayContext.getPortletURL();
%>

<clay:management-toolbar
	actionDropdownItems="<%= ddmDataProviderDisplayContext.getActionItemsDropdownItems() %>"
	clearResultsURL="<%= ddmDataProviderDisplayContext.getClearResultsURL() %>"
	componentId="ddmDataProviderManagementToolbar"
	creationMenu="<%= ddmDataProviderDisplayContext.getCreationMenu() %>"
	disabled="<%= ddmDataProviderDisplayContext.isDisabledManagementBar() %>"
	filterDropdownItems="<%= ddmDataProviderDisplayContext.getFilterItemsDropdownItems() %>"
	itemsTotal="<%= ddmDataProviderDisplayContext.getTotalItems() %>"
	namespace="<%= renderResponse.getNamespace() %>"
	searchActionURL="<%= portletURL.toString() %>"
	searchContainerId="<%= ddmDataProviderDisplayContext.getSearchContainerId() %>"
	searchFormName="fm1"
	sortingOrder="<%= ddmDataProviderDisplayContext.getOrderByType() %>"
	sortingURL="<%= ddmDataProviderDisplayContext.getSortingURL() %>"
	viewTypeItems="<%= ddmDataProviderDisplayContext.getViewTypesItems() %>"
/>

<aui:script>
	var deleteDataProviderInstances = function() {
		if (
			confirm(
				'<%= UnicodeLanguageUtil.get(request, "are-you-sure-you-want-to-delete-this") %>'
			)
		) {
			var searchContainer = document.getElementById(
				'<portlet:namespace />dataProviderInstance'
			);

			if (searchContainer) {
				Liferay.Util.postForm(
					document.<portlet:namespace />searchContainerForm,
					{
						data: {
							deleteDataProviderInstanceIds: Liferay.Util.listCheckedExcept(
								searchContainer,
								'<portlet:namespace />allRowIds'
							)
						},

						<portlet:actionURL name="deleteDataProvider" var="deleteDataProviderURL">
							<portlet:param name="mvcPath" value="/view.jsp" />
							<portlet:param name="redirect" value="<%= currentURL %>" />
						</portlet:actionURL>

						url: '<%= deleteDataProviderURL %>'
					}
				);
			}
		}
	};

	var ACTIONS = {
		deleteDataProviderInstances: deleteDataProviderInstances
	};

	Liferay.componentReady('ddmDataProviderManagementToolbar').then(function(
		managementToolbar
	) {
		managementToolbar.on(['actionItemClicked'], function(event) {
			var itemData = event.data.item.data;

			if (itemData && itemData.action && ACTIONS[itemData.action]) {
				ACTIONS[itemData.action]();
			}
		});
	});
</aui:script>