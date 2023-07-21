<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ItemSelectorURLViewDisplayContext itemSelectorURLViewDisplayContext = (ItemSelectorURLViewDisplayContext)request.getAttribute(ItemSelectorURLView.ITEM_SELECTOR_URL_VIEW_DISPLAY_CONTEXT);
%>

<div class="lfr-form-content">
	<div class="sheet sheet-lg">
		<div class="panel-group panel-group-flush">
			<aui:input helpMessage='<%= LanguageUtil.format(request, "for-example-x", "http://www.liferay.com/liferay.png", false) %>' label='<%= LanguageUtil.get(resourceBundle, "image-url") %>' name="urlInput" placeholder="http://" />

			<aui:button disabled="<%= true %>" name="previewBtn" value='<%= LanguageUtil.get(resourceBundle, "enter") %>' />
		</div>
	</div>
</div>

<aui:script use="liferay-item-selector-url">
	new Liferay.ItemSelectorUrl({
		closeCaption: '<%= itemSelectorURLViewDisplayContext.getTitle(locale) %>',
		namespace: '<portlet:namespace />',
		on: {
			selectedItem: function(event) {
				Liferay.Util.getOpener().Liferay.fire(
					'<%= itemSelectorURLViewDisplayContext.getItemSelectedEventName() %>',
					event
				);
			}
		},
		rootNode: '#<portlet:namespace />itemSelectorUrlContainer'
	});
</aui:script>