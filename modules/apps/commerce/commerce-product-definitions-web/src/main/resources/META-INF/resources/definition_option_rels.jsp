<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CPDefinitionOptionRelDisplayContext cpDefinitionOptionRelDisplayContext = (CPDefinitionOptionRelDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CPDefinition cpDefinition = cpDefinitionOptionRelDisplayContext.getCPDefinition();
%>

<c:if test="<%= CommerceCatalogPermission.contains(permissionChecker, cpDefinitionOptionRelDisplayContext.getCPDefinition(), ActionKeys.VIEW) %>">
	<div class="pt-4" id="<portlet:namespace />productOptionRelsContainer">
		<div id="item-finder-root"></div>

		<aui:script require="commerce-frontend-js/components/item_finder/entry as itemFinder, commerce-frontend-js/utilities/slugify as slugify, commerce-frontend-js/utilities/eventsDefinitions as events">
			var headers = new Headers({
				Accept: 'application/json',
				'Content-Type': 'application/json',
				'x-csrf-token': Liferay.authToken
			});

			var productId = <%= cpDefinition.getCProductId() %>;

			function selectItem(option) {
				return fetch(
					'/o/headless-commerce-admin-catalog/v1.0/products/' +
						productId +
						'/productOptions/',
					{
						body: JSON.stringify([
							{
								facetable: option.facetable,
								fieldType: option.fieldType,
								key: option.key,
								name: option.name,
								optionId: option.id,
								required: option.required,
								skuContributor: option.skuContributor,
								productOptionValues: []
							}
						]),
						credentials: 'include',
						headers: headers,
						method: 'POST'
					}
				)
					.then(function(response) {
						if (response.ok) {
							return response.json();
						}

						return response.json().then(function(data) {
							return Promise.reject(data.errorDescription);
						});
					})
					.then(function(e) {
						Liferay.fire(events.UPDATE_DATASET_DISPLAY, {
							id:
								'<%= CommerceProductDataSetConstants.COMMERCE_DATA_SET_KEY_PRODUCT_OPTIONS %>'
						});
						return null;
					});
			}

			function addNewItem(name) {
				var nameDefinition = {};

				nameDefinition[themeDisplay.getLanguageId()] = name;

				if (themeDisplay.getLanguageId() !== themeDisplay.getDefaultLanguageId()) {
					nameDefinition[themeDisplay.getDefaultLanguageId()] = name;
				}

				return fetch('/o/headless-commerce-admin-catalog/v1.0/options', {
					body: JSON.stringify({
						fieldType: 'select',
						key: slugify.default(name),
						name: nameDefinition
					}),
					credentials: 'include',
					headers: headers,
					method: 'POST'
				})
					.then(function(response) {
						if (response.ok) {
							return response.json();
						}

						return response.json().then(function(data) {
							return Promise.reject(data.errorDescription);
						});
					})
					.then(selectItem);
			}

			function getSelectedItems() {
				return Promise.resolve([]);
			}

			itemFinder.default('itemFinder', 'item-finder-root', {
				apiUrl: '/o/headless-commerce-admin-catalog/v1.0/options',
				createNewItemLabel: '<%= LanguageUtil.get(request, "create-new") %>',
				getSelectedItems: getSelectedItems,
				inputPlaceholder:
					'<%= LanguageUtil.get(request, "find-or-create-an-option") %>',
				itemSelectedMessage: '<%= LanguageUtil.get(request, "option-selected") %>',
				itemsKey: 'id',
				linkedDatasetsId: [
					'<%= CommerceProductDataSetConstants.COMMERCE_DATA_SET_KEY_PRODUCT_OPTIONS %>'
				],
				multiSelectableEntries: true,
				onItemCreated: addNewItem,
				onItemSelected: selectItem,
				pageSize: 10,
				panelHeaderLabel: '<%= LanguageUtil.get(request, "add-options") %>',
				portletId: '<%= portletDisplay.getRootPortletId() %>',
				schema: [
					{
						fieldName: ['name', 'LANG']
					}
				],
				spritemap: '<%= themeDisplay.getPathThemeImages() %>/lexicon/icons.svg',
				titleLabel: '<%= LanguageUtil.get(request, "add-existing-option") %>'
			});
		</aui:script>

		<commerce-ui:panel
			bodyClasses="p-0"
			elementClasses="mt-4"
			title='<%= LanguageUtil.get(request, "options") %>'
		>

			<%
			Map<String, String> contextParams = new HashMap<>();

			contextParams.put("cpDefinitionId", String.valueOf(cpDefinitionOptionRelDisplayContext.getCPDefinitionId()));
			%>

			<commerce-ui:dataset-display
				contextParams="<%= contextParams %>"
				dataProviderKey="<%= CommerceProductDataSetConstants.COMMERCE_DATA_SET_KEY_PRODUCT_OPTIONS %>"
				id="<%= CommerceProductDataSetConstants.COMMERCE_DATA_SET_KEY_PRODUCT_OPTIONS %>"
				itemsPerPage="<%= 10 %>"
				namespace="<%= renderResponse.getNamespace() %>"
				pageNumber="<%= 1 %>"
				portletURL="<%= currentURLObj %>"
			/>
		</commerce-ui:panel>
	</div>
</c:if>