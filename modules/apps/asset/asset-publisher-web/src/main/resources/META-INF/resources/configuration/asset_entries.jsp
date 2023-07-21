<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
PortletURL configurationRenderURL = (PortletURL)request.getAttribute("configuration.jsp-configurationRenderURL");
String eventName = "_" + HtmlUtil.escapeJS(assetPublisherDisplayContext.getPortletResource()) + "_selectAsset";

List<AssetEntry> assetEntries = assetPublisherHelper.getAssetEntries(renderRequest, portletPreferences, permissionChecker, assetPublisherDisplayContext.getGroupIds(), true, assetPublisherDisplayContext.isEnablePermissions(), true, AssetRendererFactory.TYPE_LATEST);

List<String> assetEntryIds = new ArrayList<String>();

for (AssetEntry assetEntry : assetEntries) {
	assetEntryIds.add(String.valueOf(assetEntry.getEntryId()));
}
%>

<liferay-ui:search-container
	compactEmptyResultsMessage="<%= true %>"
	emptyResultsMessage="none"
	iteratorURL="<%= configurationRenderURL %>"
	total="<%= assetEntries.size() %>"
>
	<liferay-ui:search-container-results
		results="<%= assetEntries.subList(searchContainer.getStart(), searchContainer.getResultEnd()) %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.asset.kernel.model.AssetEntry"
		escapedModel="<%= true %>"
		keyProperty="entryId"
		modelVar="assetEntry"
	>

		<%
		AssetRendererFactory<?> assetRendererFactory = AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(assetEntry.getClassName());

		AssetRenderer<?> assetRenderer = assetRendererFactory.getAssetRenderer(assetEntry.getClassPK(), AssetRendererFactory.TYPE_LATEST);
		%>

		<liferay-ui:search-container-column-text
			name="title"
			truncate="<%= true %>"
		>
			<%= HtmlUtil.escape(assetRenderer.getTitle(locale)) %>

			<c:if test="<%= !assetEntry.isVisible() %>">
				(<aui:workflow-status
					markupView="lexicon"
					showIcon="<%= false %>"
					showLabel="<%= false %>"
					status="<%= assetRenderer.getStatus() %>"
					statusMessage='<%= (assetRenderer.getStatus() == 0) ? "not-visible" : WorkflowConstants.getStatusLabel(assetRenderer.getStatus()) %>'
				/>)
			</c:if>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			name="type"
			value="<%= assetRendererFactory.getTypeName(locale) %>"
		/>

		<liferay-ui:search-container-column-date
			name="modified-date"
			value="<%= assetEntry.getModifiedDate() %>"
		/>

		<liferay-ui:search-container-column-jsp
			path="/configuration/asset_selection_action.jsp"
		/>

		<liferay-ui:search-container-column-jsp
			cssClass="entry-action-column"
			path="/configuration/asset_selection_order_action.jsp"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator
		markupView="lexicon"
		paginate="<%= total > SearchContainer.DEFAULT_DELTA %>"
	/>
</liferay-ui:search-container>

<c:if test='<%= SessionMessages.contains(renderRequest, "deletedMissingAssetEntries") %>'>
	<div class="alert alert-info">
		<liferay-ui:message key="the-selected-assets-have-been-removed-from-the-list-because-they-do-not-belong-in-the-scope-of-this-widget" />
	</div>
</c:if>

<%
long[] groupIds = assetPublisherDisplayContext.getGroupIds();

for (long groupId : groupIds) {
	Group group = GroupLocalServiceUtil.getGroup(groupId);
%>

	<liferay-ui:icon-menu
		cssClass="select-existing-selector"
		direction="right"
		message='<%= LanguageUtil.format(request, (groupIds.length == 1) ? "select" : "select-in-x", HtmlUtil.escape(group.getDescriptiveName(locale)), false) %>'
		showArrow="<%= false %>"
		showWhenSingleIcon="<%= true %>"
	>

		<%
		List<AssetRendererFactory<?>> assetRendererFactories = ListUtil.sort(AssetRendererFactoryRegistryUtil.getAssetRendererFactories(company.getCompanyId()), new AssetRendererFactoryTypeNameComparator(locale));

		for (AssetRendererFactory<?> curRendererFactory : assetRendererFactories) {
			long curGroupId = groupId;

			if (!curRendererFactory.isSelectable()) {
				continue;
			}

			PortletURL assetBrowserURL = PortletProviderUtil.getPortletURL(request, curRendererFactory.getClassName(), PortletProvider.Action.BROWSE);

			if (assetBrowserURL == null) {
				continue;
			}

			if (group.isStagingGroup() && !group.isStagedPortlet(curRendererFactory.getPortletId())) {
				curGroupId = group.getLiveGroupId();
			}

			assetBrowserURL.setParameter("selectedAssetEntryIds", StringUtil.merge(assetEntryIds, ","));
			assetBrowserURL.setParameter("groupId", String.valueOf(curGroupId));
			assetBrowserURL.setParameter("multipleSelection", String.valueOf(Boolean.TRUE));
			assetBrowserURL.setParameter("selectedGroupIds", String.valueOf(curGroupId));
			assetBrowserURL.setParameter("typeSelection", curRendererFactory.getClassName());
			assetBrowserURL.setParameter("showNonindexable", String.valueOf(Boolean.TRUE));
			assetBrowserURL.setParameter("showScheduled", String.valueOf(Boolean.TRUE));
			assetBrowserURL.setParameter("eventName", eventName);
			assetBrowserURL.setPortletMode(PortletMode.VIEW);
			assetBrowserURL.setWindowState(LiferayWindowState.POP_UP);

			Map<String, Object> data = HashMapBuilder.<String, Object>put(
				"groupid", String.valueOf(curGroupId)
			).build();
		%>

			<c:choose>
				<c:when test="<%= !curRendererFactory.isSupportsClassTypes() %>">

					<%
					data.put("href", assetBrowserURL.toString());

					String type = curRendererFactory.getTypeName(locale);

					data.put("destroyOnHide", true);
					data.put("title", LanguageUtil.format(request, "select-x", type, false));
					data.put("type", type);
					%>

					<liferay-ui:icon
						cssClass="asset-selector"
						data="<%= data %>"
						id="<%= curGroupId + FriendlyURLNormalizerUtil.normalize(type) %>"
						message="<%= HtmlUtil.escape(type) %>"
						url="javascript:;"
					/>
				</c:when>
				<c:otherwise>

					<%
					ClassTypeReader classTypeReader = curRendererFactory.getClassTypeReader();

					List<ClassType> assetAvailableClassTypes = classTypeReader.getAvailableClassTypes(PortalUtil.getCurrentAndAncestorSiteGroupIds(curGroupId), locale);

					for (ClassType assetAvailableClassType : assetAvailableClassTypes) {
						assetBrowserURL.setParameter("subtypeSelectionId", String.valueOf(assetAvailableClassType.getClassTypeId()));
						assetBrowserURL.setParameter("showNonindexable", String.valueOf(Boolean.TRUE));
						assetBrowserURL.setParameter("showScheduled", String.valueOf(Boolean.TRUE));

						data.put("href", assetBrowserURL.toString());

						String type = assetAvailableClassType.getName();

						data.put("destroyOnHide", true);
						data.put("title", LanguageUtil.format(request, "select-x", type, false));
						data.put("type", type);
					%>

						<liferay-ui:icon
							cssClass="asset-selector"
							data="<%= data %>"
							id="<%= curGroupId + FriendlyURLNormalizerUtil.normalize(type) %>"
							message="<%= HtmlUtil.escape(type) %>"
							url="javascript:;"
						/>

					<%
					}
					%>

				</c:otherwise>
			</c:choose>

		<%
		}
		%>

	</liferay-ui:icon-menu>

<%
}
%>

<script>
	function <portlet:namespace />moveSelectionDown(assetEntryOrder) {
		Liferay.Util.postForm(document.<portlet:namespace />fm, {
			data: {
				assetEntryOrder: assetEntryOrder,
				cmd: 'move-selection-down',
				redirect: '<%= HtmlUtil.escapeJS(currentURL) %>'
			}
		});
	}

	function <portlet:namespace />moveSelectionUp(assetEntryOrder) {
		Liferay.Util.postForm(document.<portlet:namespace />fm, {
			data: {
				assetEntryOrder: assetEntryOrder,
				cmd: 'move-selection-up',
				redirect: '<%= HtmlUtil.escapeJS(currentURL) %>'
			}
		});
	}
</script>

<aui:script require="metal-dom/src/dom as dom">
	AUI().use('liferay-item-selector-dialog', function(A) {
		function selectAssets(assetEntryList) {
			var assetClassName = '';
			var assetEntryIds = [];

			Array.prototype.forEach.call(assetEntryList, function(assetEntry) {
				assetEntryIds.push(assetEntry.entityid);

				assetClassName = assetEntry.assetclassname;
			});

			Liferay.Util.postForm(document.<portlet:namespace />fm, {
				data: {
					assetEntryIds: assetEntryIds.join(','),
					assetEntryType: assetClassName,
					cmd: 'add-selection',
					redirect: '<%= HtmlUtil.escapeJS(currentURL) %>'
				}
			});
		}

		var delegateHandler = dom.delegate(
			document.body,
			'click',
			'.asset-selector a',
			function(event) {
				event.preventDefault();

				var delegateTarget = event.delegateTarget;

				var itemSelectorDialog = new A.LiferayItemSelectorDialog({
					eventName: '<%= eventName %>',
					id: '<%= eventName %>' + delegateTarget.id,
					on: {
						selectedItemChange: function(event) {
							var selectedItems = event.newVal;

							if (selectedItems) {
								selectAssets(selectedItems);
							}
						}
					},
					title: delegateTarget.dataset.title,
					url: delegateTarget.dataset.href
				});

				itemSelectorDialog.open();
			}
		);

		function handleDestroyPortlet() {
			delegateHandler.removeListener();

			Liferay.detach('destroyPortlet', handleDestroyPortlet);
		}

		Liferay.on('destroyPortlet', handleDestroyPortlet);
	});
</aui:script>