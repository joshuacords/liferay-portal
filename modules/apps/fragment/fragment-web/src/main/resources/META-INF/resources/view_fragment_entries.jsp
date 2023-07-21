<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
FragmentManagementToolbarDisplayContextFactory fragmentManagementToolbarDisplayContextFactory = FragmentManagementToolbarDisplayContextFactory.getInstance();

FragmentManagementToolbarDisplayContext fragmentManagementToolbarDisplayContext = fragmentManagementToolbarDisplayContextFactory.getFragmentManagementToolbarDisplayContext(liferayPortletRequest, liferayPortletResponse, request, fragmentDisplayContext);
%>

<liferay-ui:error exception="<%= RequiredFragmentEntryException.class %>" message="the-fragment-entry-cannot-be-deleted-because-it-is-required-by-one-or-more-page-templates" />

<clay:management-toolbar
	displayContext="<%= fragmentManagementToolbarDisplayContext %>"
/>

<aui:form name="fm">
	<liferay-ui:search-container
		searchContainer="<%= fragmentDisplayContext.getFragmentEntriesSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.fragment.model.FragmentEntry"
			keyProperty="fragmentEntryId"
			modelVar="fragmentEntry"
		>

			<%
			row.setCssClass("card-page-item-asset " + row.getCssClass());

			FragmentEntryVerticalCardFactory fragmentEntryVerticalCardFactory = FragmentEntryVerticalCardFactory.getInstance();
			%>

			<liferay-ui:search-container-column-text>
				<clay:vertical-card
					verticalCard="<%= fragmentEntryVerticalCardFactory.getVerticalCard(fragmentEntry, renderRequest, renderResponse, searchContainer.getRowChecker(), fragmentDisplayContext.getFragmentType()) %>"
				/>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			displayStyle="icon"
			markupView="lexicon"
			resultRowSplitter="<%= fragmentDisplayContext.isSearch() ? null : new FragmentEntryResultRowSplitter() %>"
			searchResultCssClass="card-page"
		/>
	</liferay-ui:search-container>
</aui:form>

<portlet:actionURL name="/fragment/update_fragment_entry_preview" var="updateFragmentEntryPreviewURL">
	<portlet:param name="redirect" value="<%= currentURL %>" />
</portlet:actionURL>

<aui:form action="<%= updateFragmentEntryPreviewURL %>" name="fragmentEntryPreviewFm">
	<aui:input name="fragmentEntryId" type="hidden" />
	<aui:input name="fileEntryId" type="hidden" />
</aui:form>

<aui:form name="fragmentEntryFm">
	<aui:input name="fragmentEntryIds" type="hidden" />
	<aui:input name="fragmentCollectionId" type="hidden" />
</aui:form>

<liferay-frontend:component
	componentId="<%= FragmentWebKeys.FRAGMENT_ENTRY_DROPDOWN_DEFAULT_EVENT_HANDLER %>"
	module="js/FragmentEntryDropdownDefaultEventHandler.es"
/>

<liferay-frontend:component
	componentId="<%= fragmentManagementToolbarDisplayContext.getDefaultEventHandler() %>"
	context="<%= fragmentManagementToolbarDisplayContext.getComponentContext() %>"
	module="js/ManagementToolbarDefaultEventHandler.es"
/>