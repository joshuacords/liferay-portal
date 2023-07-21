<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
SelectOrganizationsDisplayContext selectOrganizationsDisplayContext = (SelectOrganizationsDisplayContext)request.getAttribute(SegmentsWebKeys.SELECT_ORGANIZATIONS_DISPLAY_CONTEXT);
%>

<clay:management-toolbar
	clearResultsURL="<%= selectOrganizationsDisplayContext.getClearResultsURL() %>"
	componentId="selectSegmentsEntryOrganizationsManagementToolbar"
	disabled="<%= selectOrganizationsDisplayContext.isDisabledManagementBar() %>"
	filterDropdownItems="<%= selectOrganizationsDisplayContext.getFilterDropdownItems() %>"
	itemsTotal="<%= selectOrganizationsDisplayContext.getTotalItems() %>"
	searchActionURL="<%= selectOrganizationsDisplayContext.getSearchActionURL() %>"
	searchContainerId="selectSegmentsEntryOrganizations"
	searchFormName="searchFm"
	showSearch="<%= selectOrganizationsDisplayContext.isShowSearch() %>"
	sortingOrder="<%= selectOrganizationsDisplayContext.getOrderByType() %>"
	sortingURL="<%= selectOrganizationsDisplayContext.getSortingURL() %>"
	viewTypeItems="<%= selectOrganizationsDisplayContext.getViewTypeItems() %>"
/>

<aui:form cssClass="container-fluid-1280" name="fm">
	<liferay-ui:search-container
		id="selectSegmentsEntryOrganizations"
		searchContainer="<%= selectOrganizationsDisplayContext.getOrganizationSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.portal.kernel.model.Organization"
			escapedModel="<%= true %>"
			keyProperty="organizationId"
			modelVar="organization"
		>

			<%
			Map<String, Object> data = new HashMap<>();

			data.put("id", organization.getOrganizationId());
			data.put("name", organization.getName());

			row.setData(data);
			%>

			<liferay-ui:search-container-column-text
				name="name"
				orderable="<%= true %>"
				property="name"
			/>

			<liferay-ui:search-container-column-text
				name="parent-organization"
				value="<%= HtmlUtil.escape(organization.getParentOrganizationName()) %>"
			/>

			<liferay-ui:search-container-column-text
				name="type"
				orderable="<%= true %>"
				value="<%= LanguageUtil.get(request, organization.getType()) %>"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			displayStyle="<%= selectOrganizationsDisplayContext.getDisplayStyle() %>"
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</aui:form>

<liferay-util:include page="/field/select_js.jsp" servletContext="<%= application %>">
	<liferay-util:param name="searchContainerId" value="selectSegmentsEntryOrganizations" />
	<liferay-util:param name="selectEventName" value="<%= selectOrganizationsDisplayContext.getEventName() %>" />
</liferay-util:include>