<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/admin/init.jsp" %>

<clay:management-toolbar
	actionDropdownItems="<%= ddmFormAdminDisplayContext.getActionItemsDropdownItems() %>"
	clearResultsURL="<%= ddmFormAdminDisplayContext.getClearResultsURL() %>"
	componentId="ddmFormManagementToolbar"
	creationMenu="<%= ddmFormAdminDisplayContext.getCreationMenu() %>"
	disabled="<%= ddmFormAdminDisplayContext.isDisabledManagementBar() %>"
	filterDropdownItems="<%= ddmFormAdminDisplayContext.getFilterItemsDropdownItems() %>"
	itemsTotal="<%= ddmFormAdminDisplayContext.getTotalItems() %>"
	namespace="<%= renderResponse.getNamespace() %>"
	searchActionURL="<%= ddmFormAdminDisplayContext.getSearchActionURL() %>"
	searchContainerId="<%= ddmFormAdminDisplayContext.getSearchContainerId() %>"
	searchFormName="fm1"
	sortingOrder="<%= ddmFormAdminDisplayContext.getOrderByType() %>"
	sortingURL="<%= ddmFormAdminDisplayContext.getSortingURL() %>"
	viewTypeItems="<%= ddmFormAdminDisplayContext.getViewTypesItems() %>"
/>

<aui:script sandbox="<%= true %>">
	var deleteFormInstances = function() {
		if (
			confirm(
				'<%= UnicodeLanguageUtil.get(request, "are-you-sure-you-want-to-delete-this") %>'
			)
		) {
			var searchContainer = document.getElementById(
				'<portlet:namespace /><%= ddmFormAdminDisplayContext.getSearchContainerId() %>'
			);

			if (searchContainer) {
				Liferay.Util.postForm(
					document.<portlet:namespace />searchContainerForm,
					{
						data: {
							deleteFormInstanceIds: Liferay.Util.listCheckedExcept(
								searchContainer,
								'<portlet:namespace />allRowIds'
							)
						},

						<portlet:actionURL name="deleteFormInstance" var="deleteFormInstanceURL">
							<portlet:param name="mvcPath" value="/admin/view.jsp" />
							<portlet:param name="redirect" value="<%= currentURL %>" />
						</portlet:actionURL>

						url: '<%= deleteFormInstanceURL %>'
					}
				);
			}
		}
	};

	var deleteStructures = function() {
		if (
			confirm(
				'<%= UnicodeLanguageUtil.get(request, "are-you-sure-you-want-to-delete-this") %>'
			)
		) {
			var searchContainer = document.getElementById(
				'<portlet:namespace /><%= ddmFormAdminDisplayContext.getSearchContainerId() %>'
			);

			if (searchContainer) {
				Liferay.Util.postForm(
					document.<portlet:namespace />searchContainerForm,
					{
						data: {
							deleteStructureIds: Liferay.Util.listCheckedExcept(
								searchContainer,
								'<portlet:namespace />allRowIds'
							)
						},

						<portlet:actionURL name="deleteStructure" var="deleteStructureURL">
							<portlet:param name="mvcPath" value="/admin/view.jsp" />
							<portlet:param name="currentTab" value="element-set" />
							<portlet:param name="redirect" value="<%= currentURL %>" />
						</portlet:actionURL>

						url: '<%= deleteStructureURL %>'
					}
				);
			}
		}
	};

	var ACTIONS = {
		deleteFormInstances: deleteFormInstances,
		deleteStructures: deleteStructures
	};

	Liferay.componentReady('ddmFormManagementToolbar').then(function(
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