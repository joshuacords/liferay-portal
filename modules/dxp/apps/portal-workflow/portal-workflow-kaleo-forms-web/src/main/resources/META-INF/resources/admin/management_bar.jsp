<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/admin/init.jsp" %>

<clay:management-toolbar
	actionDropdownItems="<%= kaleoFormsAdminDisplayContext.getActionItemsDropdownItems() %>"
	clearResultsURL="<%= kaleoFormsAdminDisplayContext.getClearResultsURL() %>"
	componentId="kaleoFormsManagementToolbar"
	creationMenu="<%= kaleoFormsAdminDisplayContext.getCreationMenu() %>"
	filterDropdownItems="<%= kaleoFormsAdminDisplayContext.getFilterItemsDropdownItems() %>"
	itemsTotal="<%= kaleoFormsAdminDisplayContext.getTotalItems() %>"
	namespace="<%= renderResponse.getNamespace() %>"
	searchActionURL="<%= kaleoFormsAdminDisplayContext.getSearchActionURL() %>"
	searchContainerId="<%= kaleoFormsAdminDisplayContext.getSearchContainerId() %>"
	searchFormName="fm1"
	sortingOrder="<%= kaleoFormsAdminDisplayContext.getOrderByType() %>"
	sortingURL="<%= kaleoFormsAdminDisplayContext.getSortingURL() %>"
/>

<aui:script sandbox="<%= true %>">
	var deleteKaleoProcess = function() {
		if (
			confirm(
				'<%= UnicodeLanguageUtil.get(request, "are-you-sure-you-want-to-delete-this") %>'
			)
		) {
			var form = AUI.$(document.<portlet:namespace />fm);

			var searchContainer = AUI.$('#<portlet:namespace />kaleoProcess', form);

			form.attr('method', 'post');
			form.fm('kaleoProcessIds').val(
				Liferay.Util.listCheckedExcept(
					searchContainer,
					'<portlet:namespace />allRowIds'
				)
			);

			submitForm(
				form,
				'<portlet:actionURL name="deleteKaleoProcess"><portlet:param name="mvcPath" value="/admin/view.jsp" /><portlet:param name="redirect" value="<%= currentURL %>" /></portlet:actionURL>'
			);
		}
	};

	var ACTIONS = {
		deleteKaleoProcess: deleteKaleoProcess
	};

	Liferay.componentReady('kaleoFormsManagementToolbar').then(function(
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