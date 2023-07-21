<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
AssetDisplayPagesItemSelectorViewDisplayContext assetDisplayPagesItemSelectorViewDisplayContext = (AssetDisplayPagesItemSelectorViewDisplayContext)request.getAttribute(AssetDisplayPageItemSelectorWebKeys.ASSET_DISPLAY_PAGES_ITEM_SELECTOR_VIEW_DISPLAY_CONTEXT);
%>

<clay:management-toolbar
	displayContext="<%= new AssetDisplayPagesItemSelectorViewManagementToolbarDisplayContext(liferayPortletRequest, liferayPortletResponse, request, assetDisplayPagesItemSelectorViewDisplayContext) %>"
/>

<aui:form cssClass="container-fluid-1280" name="fm">
	<liferay-ui:search-container
		id="displayPages"
		searchContainer="<%= assetDisplayPagesItemSelectorViewDisplayContext.getAssetDisplayPageSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.layout.page.template.model.LayoutPageTemplateEntry"
			keyProperty="layoutPageTemplateEntryId"
			modelVar="layoutPageTemplateEntry"
		>

			<%
			row.setCssClass("entry-card form-check-card lfr-asset-item " + row.getCssClass());
			%>

			<liferay-ui:search-container-column-text>
				<clay:vertical-card
					verticalCard="<%= new LayoutPageTemplateEntryVerticalCard(layoutPageTemplateEntry, renderRequest) %>"
				/>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			displayStyle="icon"
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</aui:form>

<aui:script require="metal-dom/src/all/dom as dom">
	var selectFragmentEntryHandler = dom.delegate(
		document.querySelector('#<portlet:namespace />fm'),
		'click',
		'.layout-page-template-entry',
		function(event) {
			dom.removeClasses(
				document.querySelectorAll('.form-check-card.active'),
				'active'
			);
			dom.addClasses(
				dom.closest(event.delegateTarget, '.form-check-card'),
				'active'
			);

			Liferay.Util.getOpener().Liferay.fire(
				'<%= assetDisplayPagesItemSelectorViewDisplayContext.getItemSelectedEventName() %>',
				{
					data: event.delegateTarget.dataset
				}
			);
		}
	);

	function removeListener() {
		selectFragmentEntryHandler.removeListener();

		Liferay.detach('destroyPortlet', removeListener);
	}

	Liferay.on('destroyPortlet', removeListener);
</aui:script>