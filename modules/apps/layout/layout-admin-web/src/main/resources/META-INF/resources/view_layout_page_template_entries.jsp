<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
LayoutPageTemplateDisplayContext layoutPageTemplateDisplayContext = new LayoutPageTemplateDisplayContext(renderRequest, renderResponse, request);

LayoutPageTemplateManagementToolbarDisplayContext layoutPageTemplateManagementToolbarDisplayContext = new LayoutPageTemplateManagementToolbarDisplayContext(liferayPortletRequest, liferayPortletResponse, request, layoutPageTemplateDisplayContext);
%>

<clay:management-toolbar
	displayContext="<%= layoutPageTemplateManagementToolbarDisplayContext %>"
/>

<portlet:actionURL name="/layout/delete_layout_page_template_entry" var="deleteLayoutPageTemplateEntryURL">
	<portlet:param name="redirect" value="<%= currentURL %>" />
</portlet:actionURL>

<aui:form action="<%= deleteLayoutPageTemplateEntryURL %>" name="fm">
	<liferay-ui:error key="<%= PortalException.class.getName() %>" message="you-cannot-delete-page-templates-that-are-used-by-a-page" />

	<liferay-ui:search-container
		id="layoutPageTemplateEntries"
		searchContainer="<%= layoutPageTemplateDisplayContext.getLayoutPageTemplateEntriesSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.layout.page.template.model.LayoutPageTemplateEntry"
			keyProperty="layoutPageTemplateEntryId"
			modelVar="layoutPageTemplateEntry"
		>

			<%
			row.setCssClass("entry-card lfr-asset-item " + row.getCssClass());

			Map<String, Object> rowData = new HashMap<>();

			rowData.put("actions", layoutPageTemplateManagementToolbarDisplayContext.getAvailableActions(layoutPageTemplateEntry));

			row.setData(rowData);
			%>

			<liferay-ui:search-container-column-text>
				<clay:vertical-card
					verticalCard="<%= new LayoutPageTemplateEntryVerticalCard(layoutPageTemplateEntry, renderRequest, renderResponse, searchContainer.getRowChecker()) %>"
				/>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			displayStyle="icon"
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</aui:form>

<portlet:actionURL name="/layout/update_layout_page_template_entry_preview" var="updateLayoutPageTemplateEntryPreviewURL">
	<portlet:param name="redirect" value="<%= currentURL %>" />
</portlet:actionURL>

<aui:form action="<%= updateLayoutPageTemplateEntryPreviewURL %>" name="layoutPageTemplateEntryPreviewFm">
	<aui:input name="layoutPageTemplateEntryId" type="hidden" />
	<aui:input name="fileEntryId" type="hidden" />
</aui:form>

<liferay-frontend:component
	componentId="<%= LayoutAdminWebKeys.LAYOUT_PAGE_TEMPLATE_ENTRY_DROPDOWN_DEFAULT_EVENT_HANDLER %>"
	module="js/LayoutPageTemplateEntryDropdownDefaultEventHandler.es"
/>

<liferay-frontend:component
	componentId="<%= layoutPageTemplateManagementToolbarDisplayContext.getDefaultEventHandler() %>"
	module="js/LayoutPageTemplateEntryManagementToolbarDefaultEventHandler.es"
/>