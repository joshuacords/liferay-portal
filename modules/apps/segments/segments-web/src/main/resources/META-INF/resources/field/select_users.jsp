<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
SelectUsersDisplayContext selectUsersDisplayContext = (SelectUsersDisplayContext)request.getAttribute(SegmentsWebKeys.SELECT_USERS_DISPLAY_CONTEXT);
%>

<clay:management-toolbar
	clearResultsURL="<%= selectUsersDisplayContext.getClearResultsURL() %>"
	componentId="selectSegmentsEntryUsersManagementToolbar"
	disabled="<%= selectUsersDisplayContext.isDisabledManagementBar() %>"
	filterDropdownItems="<%= selectUsersDisplayContext.getFilterDropdownItems() %>"
	itemsTotal="<%= selectUsersDisplayContext.getTotalItems() %>"
	searchActionURL="<%= selectUsersDisplayContext.getSearchActionURL() %>"
	searchContainerId="selectSegmentsEntryUsers"
	searchFormName="searchFm"
	showSearch="<%= selectUsersDisplayContext.isShowSearch() %>"
	sortingOrder="<%= selectUsersDisplayContext.getOrderByType() %>"
	sortingURL="<%= selectUsersDisplayContext.getSortingURL() %>"
	viewTypeItems="<%= selectUsersDisplayContext.getViewTypeItems() %>"
/>

<aui:form cssClass="container-fluid-1280" name="fm">
	<liferay-ui:search-container
		id="selectSegmentsEntryUsers"
		searchContainer="<%= selectUsersDisplayContext.getUserSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.portal.kernel.model.User"
			escapedModel="<%= true %>"
			keyProperty="userId"
			modelVar="user2"
			rowIdProperty="screenName"
		>

			<%
			Map<String, Object> data = new HashMap<>();

			data.put("id", user2.getUserId());
			data.put("name", user2.getFullName());

			row.setData(data);
			%>

			<liferay-ui:search-container-column-text
				cssClass="table-cell-expand table-cell-minw-200 table-title"
				name="name"
				value="<%= user2.getFullName() %>"
			/>

			<liferay-ui:search-container-column-text
				cssClass="table-cell-expand table-cell-minw-200"
				name="screen-name"
				orderable="<%= true %>"
				property="screenName"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			displayStyle="<%= selectUsersDisplayContext.getDisplayStyle() %>"
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</aui:form>

<liferay-util:include page="/field/select_js.jsp" servletContext="<%= application %>">
	<liferay-util:param name="searchContainerId" value="selectSegmentsEntryUsers" />
	<liferay-util:param name="selectEventName" value="<%= selectUsersDisplayContext.getEventName() %>" />
</liferay-util:include>