<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<clay:management-toolbar
	displayContext="<%= new AssetTagsSelectorManagementToolbarDisplayContext(liferayPortletRequest, liferayPortletResponse, request, assetTagsSelectorDisplayContext) %>"
/>

<div class="container-fluid-1280">
	<liferay-ui:search-container
		id="tags"
		searchContainer="<%= assetTagsSelectorDisplayContext.getTagsSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.asset.kernel.model.AssetTag"
			keyProperty="name"
			modelVar="tag"
		>
			<liferay-ui:search-container-column-text
				cssClass="content-column name-column title-column"
				name="name"
				truncate="<%= true %>"
				value="<%= tag.getName() %>"
			/>

			<liferay-ui:search-container-column-text
				cssClass="table-cell-expand"
				name="site"
				value="<%= HtmlUtil.escape(assetTagsSelectorDisplayContext.getAssetTagGroupName(tag, locale)) %>"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			displayStyle="list"
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>

<aui:script use="liferay-search-container">
	var searchContainer = Liferay.SearchContainer.get('<portlet:namespace />tags');

	var searchContainerData = searchContainer.getData(true);

	var selectedTagNames = <%= JSONFactoryUtil.serialize(assetTagsSelectorDisplayContext.getSelectedTagNames()) %>;

	selectedTagNames = selectedTagNames.filter(function(tag) {
		return searchContainerData.indexOf(tag) === -1;
	});

	searchContainer.on('rowToggled', function(event) {
		var items = '';

		var selectedItems = event.elements.allSelectedElements;

		if (selectedItems.size() > 0) {
			var selections = selectedTagNames.concat(selectedItems.attr('value'));

			var uniqueSelections = [];

			Array.prototype.forEach.call(selections, function(selection) {
				if (!uniqueSelections.includes(selection)) {
					uniqueSelections.push(selection);
				}
			});

			items = uniqueSelections.join(',');
		}

		Liferay.Util.getOpener().Liferay.fire(
			'<%= HtmlUtil.escapeJS(assetTagsSelectorDisplayContext.getEventName()) %>',
			{
				data: {
					items: items
				}
			}
		);
	});
</aui:script>