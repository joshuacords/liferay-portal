<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
JournalViewMoreMenuItemsDisplayContext journalViewMoreMenuItemsDisplayContext = new JournalViewMoreMenuItemsDisplayContext(renderRequest, renderResponse, journalDisplayContext.getFolderId(), journalDisplayContext.getRestrictionType());
%>

<c:if test="<%= journalDisplayContext.getAddMenuFavItemsLength() == 0 %>">
	<clay:stripe
		destroyOnHide="<%= true %>"
		message='<%= LanguageUtil.format(resourceBundle, "you-can-add-as-many-as-x-favorites-in-your-quick-menu", journalWebConfiguration.maxAddMenuItems()) %>'
		title='<%= LanguageUtil.get(resourceBundle, "info") + ":" %>'
	/>
</c:if>

<liferay-ui:error exception="<%= MaxAddMenuFavItemsException.class %>" message='<%= LanguageUtil.format(resourceBundle, "you-cannot-add-more-than-x-favorites", journalWebConfiguration.maxAddMenuItems()) %>' />

<c:if test="<%= journalDisplayContext.getAddMenuFavItemsLength() >= journalWebConfiguration.maxAddMenuItems() %>">
	<clay:stripe
		message='<%= LanguageUtil.get(resourceBundle, "right-now-your-quick-menu-is-full-of-favorites-if-you-want-to-add-another-one-please-remove-at-least-one-of-them") %>'
		style="warning"
		title='<%= LanguageUtil.get(resourceBundle, "warning") + ":" %>'
	/>
</c:if>

<clay:navigation-bar
	navigationItems="<%= journalViewMoreMenuItemsDisplayContext.getNavigationItems() %>"
/>

<clay:management-toolbar
	displayContext="<%= new JournalViewMoreMenuItemsManagementToolbarDisplayContext(liferayPortletRequest, liferayPortletResponse, request, journalViewMoreMenuItemsDisplayContext) %>"
/>

<aui:form cssClass="container-fluid-1280" name="addMenuItemFm">
	<liferay-ui:search-container
		searchContainer="<%= journalViewMoreMenuItemsDisplayContext.getDDMStructuresSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.dynamic.data.mapping.model.DDMStructure"
			cssClass="selectable"
			escapedModel="<%= true %>"
			modelVar="ddmStructure"
		>

			<%
			Map<String, Object> data = new HashMap<>();

			data.put("ddmStructureKey", ddmStructure.getStructureKey());
			%>

			<liferay-ui:search-container-column-text
				name="menu-item-name"
			>
				<aui:a cssClass="selector-button" data="<%= data %>" href="javascript:;">
					<%= ddmStructure.getUnambiguousName(journalViewMoreMenuItemsDisplayContext.getDDMStructures(), themeDisplay.getScopeGroupId(), locale) %>
				</aui:a>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="user"
				property="userName"
			/>

			<liferay-ui:search-container-column-date
				name="modified-date"
				property="modifiedDate"
			/>

			<liferay-ui:search-container-column-jsp
				align="center"
				name='<%= LanguageUtil.format(request, "add-to-favorites-x", String.valueOf(journalDisplayContext.getAddMenuFavItemsLength())) %>'
				path="/view_more_menu_items_actions.jsp"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			displayStyle="list"
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</aui:form>

<aui:script require="metal-dom/src/all/dom as dom">
	var Util = Liferay.Util;

	var addMenuItemFm = document.getElementById(
		'<portlet:namespace />addMenuItemFm'
	);

	dom.delegate(addMenuItemFm, 'click', '.selector-button', function(event) {
		Util.getOpener().Liferay.fire(
			'<%= HtmlUtil.escapeJS(journalViewMoreMenuItemsDisplayContext.getEventName()) %>',
			{
				ddmStructureKey: event.delegateTarget.getAttribute(
					'data-ddmStructureKey'
				)
			}
		);

		Util.getWindow().destroy();
	});
</aui:script>