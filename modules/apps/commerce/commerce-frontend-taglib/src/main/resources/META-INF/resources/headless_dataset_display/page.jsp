<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/headless_dataset_display/init.jsp" %>

<div class="table-root" id="<%= containerId %>">
	<span aria-hidden="true" class="loading-animation my-7"></span>
</div>

<aui:script require="commerce-frontend-js/components/dataset_display/entry as datasetDisplay">
	datasetDisplay.default('<%= containerId %>', '<%= containerId %>', {
		views: <%= jsonSerializer.serializeDeep(clayDataSetDisplayViewsContext) %>,
		filters: <%= jsonSerializer.serializeDeep(clayDataSetFiltersContext) %>,
		apiUrl: '<%= HtmlUtil.escapeJS(apiUrl) %>',
		bulkActions: <%= jsonSerializer.serializeDeep(bulkActions) %>,
		creationMenuItems: <%= jsonSerializer.serializeDeep(clayCreationMenu.getClayCreationMenuActionItems()) %>,
		currentUrl: '<%= currentURL %>',
		formId: '<%= HtmlUtil.escapeJS(formId) %>',
		id: '<%= id %>',
		itemsActions: <%= jsonSerializer.serializeDeep(clayHeadlessDataSetActionTemplates) %>,
		filters: <%= jsonSerializer.serializeDeep(clayDataSetFiltersContext) %>,

		<%
		if (Validator.isNotNull(nestedItemsKey)) {
		%>

			nestedItemsKey: '<%= HtmlUtil.escapeJS(nestedItemsKey) %>',

			<%
			}

			if (Validator.isNotNull(nestedItemsReferenceKey)) {
			%>

			nestedItemsReferenceKey:
				'<%= HtmlUtil.escapeJS(nestedItemsReferenceKey) %>',

		<%
		}
		%>

		showPagination: <%= showPagination %>,
		showManagementBar: <%= showManagementBar %>,
		showSearch: <%= showSearch %>,
		pagination: {
			deltas: <%= jsonSerializer.serializeDeep(paginationEntries) %>,
			initialDelta: <%= itemsPerPage %>,
			initialPageNumber: <%= pageNumber %>
		},
		portletId: '<%= portletDisplay.getRootPortletId() %>',
		namespace: '<%= namespace %>',
		portletURL: '<%= HtmlUtil.escapeJS(portletURL.toString()) %>',
		selectedItems: <%= jsonSerializer.serializeDeep(selectedItems) %>,
		selectedItemsKey: '<%= selectedItemsKey %>',
		selectionType: '<%= selectionType %>',
		spritemap: '<%= spritemap %>',
		style: '<%= style %>'
	});

	document.querySelectorAll('form').forEach(function(form) {
		form.setAttribute('data-senna-off', true);
	});
</aui:script>