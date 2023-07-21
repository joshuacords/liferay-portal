<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/input_asset_links/init.jsp" %>

<liferay-ui:icon-menu
	cssClass="select-existing-selector"
	direction="right"
	id='<%= inputAssetLinksDisplayContext.getRandomNamespace() + "inputAssetLinks" %>'
	message="select"
	showArrow="<%= false %>"
	showWhenSingleIcon="<%= true %>"
>

	<%
	for (Map<String, Object> selectorEntry : inputAssetLinksDisplayContext.getSelectorEntries()) {
	%>

		<liferay-ui:icon
			cssClass="asset-selector"
			data='<%= (Map<String, Object>)selectorEntry.get("data") %>'
			id='<%= (String)selectorEntry.get("id") %>'
			message='<%= HtmlUtil.escape((String)selectorEntry.get("message")) %>'
			url="javascript:;"
		/>

	<%
	}
	%>

</liferay-ui:icon-menu>

<liferay-util:buffer
	var="removeLinkIcon"
>
	<liferay-ui:icon
		icon="times-circle"
		markupView="lexicon"
		message="remove"
	/>
</liferay-util:buffer>

<liferay-ui:search-container
	compactEmptyResultsMessage="<%= true %>"
	emptyResultsMessage="none"
	headerNames="title,null"
	total="<%= inputAssetLinksDisplayContext.getAssetLinksCount() %>"
>
	<liferay-ui:search-container-results
		results="<%= inputAssetLinksDisplayContext.getAssetLinks() %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.asset.kernel.model.AssetLink"
		keyProperty="entryId2"
		modelVar="assetLink"
	>

		<%
		AssetEntry assetLinkEntry = inputAssetLinksDisplayContext.getAssetLinkEntry(assetLink);
		%>

		<liferay-ui:search-container-column-text
			name="title"
		>
			<h4 class="list-group-title">
				<%= HtmlUtil.escape(assetLinkEntry.getTitle(locale)) %>
			</h4>

			<p class="list-group-subtitle">
				<%= inputAssetLinksDisplayContext.getAssetType(assetLinkEntry) %>
			</p>

			<p class="list-group-subtitle">
				<liferay-ui:message key="scope" />: <%= HtmlUtil.escape(inputAssetLinksDisplayContext.getGroupDescriptiveName(assetLinkEntry)) %>
			</p>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			cssClass="text-right"
		>
			<a class="modify-link" data-rowId="<%= assetLinkEntry.getEntryId() %>" href="javascript:;"><%= removeLinkIcon %></a>
		</liferay-ui:search-container-column-text>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator
		markupView="lexicon"
		paginate="<%= false %>"
		searchResultCssClass="table table-autofit table-heading-nowrap"
	/>
</liferay-ui:search-container>

<c:if test="<%= stagingGroupHelper.isLiveGroup(themeDisplay.getScopeGroupId()) %>">
	<span>
		<liferay-ui:message key="related-assets-for-staged-asset-types-can-be-managed-on-the-staging-site" />
	</span>
</c:if>

<aui:input name="assetLinkEntryIds" type="hidden" />

<aui:script use="aui-base,escape,liferay-item-selector-dialog,liferay-search-container">
	var assetSelectorHandle = A.getBody().delegate(
		'click',
		function(event) {
			event.preventDefault();

			var searchContainerName =
				'<portlet:namespace />assetLinksSearchContainer';

			var searchContainer = Liferay.SearchContainer.get(searchContainerName);

			var searchContainerData = searchContainer.getData();

			if (searchContainerData) {
				searchContainerData = searchContainerData.split(',');
			}
			else {
				searchContainerData = [];
			}

			var itemSelectorDialog = new A.LiferayItemSelectorDialog({
				eventName: '<%= inputAssetLinksDisplayContext.getEventName() %>',
				id:
					'<%= inputAssetLinksDisplayContext.getEventName() %>' +
					event.currentTarget.attr('id'),
				on: {
					selectedItemChange: function(event) {
						var assetEntryIds = event.newVal;

						if (assetEntryIds) {
							Array.prototype.forEach.call(assetEntryIds, function(
								assetEntry
							) {
								var entityId = assetEntry.entityid;

								if (searchContainerData.indexOf(entityId) == -1) {
									var entryLink =
										'<div class="text-right"><a class="modify-link" data-rowId="' +
										entityId +
										'" href="javascript:;"><%= UnicodeFormatter.toString(removeLinkIcon) %></a></div>';

									var entryHtml =
										'<h4 class="list-group-title">' +
										A.Escape.html(assetEntry.assettitle) +
										'</h4><p class="list-group-subtitle">' +
										A.Escape.html(assetEntry.assettype) +
										'</p><p class="list-group-subtitle">' +
										A.Escape.html(
											assetEntry.groupdescriptivename
										) +
										'</p>';

									searchContainer.addRow(
										[entryHtml, entryLink],
										entityId
									);

									searchContainer.updateDataStore();
								}
							});
						}
					}
				},
				selectedData: searchContainerData,
				'strings.add': '<liferay-ui:message key="done" />',
				title: event.currentTarget.attr('data-title'),
				url: event.currentTarget.attr('data-href')
			});

			itemSelectorDialog.open();
		},
		'.asset-selector a'
	);

	var clearAssetSelectorHandle = function(event) {
		if (event.portletId === '<%= portletDisplay.getId() %>') {
			assetSelectorHandle.detach();

			Liferay.detach('destroyPortlet', clearAssetSelectorHandle);
		}
	};

	Liferay.on('destroyPortlet', clearAssetSelectorHandle);
</aui:script>

<aui:script use="liferay-search-container">
	var searchContainer = Liferay.SearchContainer.get(
		'<portlet:namespace />assetLinksSearchContainer'
	);

	searchContainer.get('contentBox').delegate(
		'click',
		function(event) {
			var link = event.currentTarget;

			var tr = link.ancestor('tr');

			searchContainer.deleteRow(tr, link.getAttribute('data-rowId'));
		},
		'.modify-link'
	);
</aui:script>