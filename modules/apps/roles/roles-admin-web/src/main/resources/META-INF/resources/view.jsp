<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String displayStyle = ParamUtil.getString(request, "displayStyle");

if (Validator.isNull(displayStyle)) {
	displayStyle = portalPreferences.getValue(RolesAdminPortletKeys.ROLES_ADMIN, "displayStyle", "descriptive");
}
else {
	portalPreferences.setValue(RolesAdminPortletKeys.ROLES_ADMIN, "displayStyle", displayStyle);

	request.setAttribute(WebKeys.SINGLE_PAGE_APPLICATION_CLEAR_CACHE, Boolean.TRUE);
}

ViewRolesManagementToolbarDisplayContext viewRolesManagementToolbarDisplayContext = new ViewRolesManagementToolbarDisplayContext(request, renderRequest, renderResponse, displayStyle);

SearchContainer searchContainer = viewRolesManagementToolbarDisplayContext.getSearchContainer();

PortletURL portletURL = viewRolesManagementToolbarDisplayContext.getPortletURL();
%>

<liferay-ui:error exception="<%= RequiredRoleException.class %>" message="you-cannot-delete-a-system-role" />

<clay:navigation-bar
	inverted="<%= true %>"
	navigationItems="<%= roleDisplayContext.getViewRoleNavigationItems(liferayPortletResponse, portletURL) %>"
/>

<clay:management-toolbar
	actionDropdownItems="<%= viewRolesManagementToolbarDisplayContext.getActionDropdownItems() %>"
	clearResultsURL="<%= viewRolesManagementToolbarDisplayContext.getClearResultsURL() %>"
	componentId="viewRolesManagementToolbar"
	creationMenu="<%= viewRolesManagementToolbarDisplayContext.getCreationMenu() %>"
	filterDropdownItems="<%= viewRolesManagementToolbarDisplayContext.getFilterDropdownItems() %>"
	itemsTotal="<%= searchContainer.getTotal() %>"
	searchActionURL="<%= viewRolesManagementToolbarDisplayContext.getSearchActionURL() %>"
	searchContainerId="roleSearch"
	searchFormName="searchFm"
	selectable="<%= true %>"
	showCreationMenu="<%= viewRolesManagementToolbarDisplayContext.showCreationMenu() %>"
	showSearch="<%= true %>"
	sortingOrder="<%= searchContainer.getOrderByType() %>"
	sortingURL="<%= viewRolesManagementToolbarDisplayContext.getSortingURL() %>"
	viewTypeItems="<%= viewRolesManagementToolbarDisplayContext.getViewTypeItems() %>"
/>

<aui:form action="<%= portletURL %>" cssClass="container-fluid container-fluid-max-xl container-form-view" method="get" name="fm">
	<aui:input name="deleteRoleIds" type="hidden" />

	<liferay-ui:search-container
		id="roleSearch"
		searchContainer="<%= searchContainer %>"
		var="roleSearchContainer"
	>
		<aui:input name="rolesRedirect" type="hidden" value="<%= portletURL.toString() %>" />

		<liferay-ui:search-container-row
			className="com.liferay.portal.kernel.model.Role"
			keyProperty="roleId"
			modelVar="role"
		>

			<%
			PortletURL rowURL = null;

			if (RolePermissionUtil.contains(permissionChecker, role.getRoleId(), ActionKeys.UPDATE)) {
				rowURL = renderResponse.createRenderURL();

				rowURL.setParameter("mvcPath", "/edit_role.jsp");
				rowURL.setParameter("tabs1", "details");

				PortletURL searchContainerPortletURL = roleSearchContainer.getIteratorURL();

				rowURL.setParameter("redirect", searchContainerPortletURL.toString());

				rowURL.setParameter("roleId", String.valueOf(role.getRoleId()));
			}
			%>

			<%@ include file="/search_columns.jspf" %>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			displayStyle="<%= displayStyle %>"
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</aui:form>

<aui:script sandbox="<%= true %>">
	var deleteRoles = function(deleteRoleIds) {
		var form = document.<portlet:namespace />fm;

		var p_p_lifecycle = form.p_p_lifecycle;

		if (p_p_lifecycle) {
			p_p_lifecycle.value = '1';
		}

		if (
			confirm(
				'<%= UnicodeLanguageUtil.get(request, "are-you-sure-you-want-to-delete-this-role") %>'
			)
		) {
			Liferay.Util.postForm(form, {
				data: {
					deleteRoleIds: deleteRoleIds
				},

				<portlet:actionURL name="deleteRoles" var="deleteRolesURL">
					<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
				</portlet:actionURL>

				url: '<%= deleteRolesURL %>'
			});
		}
	};

	var ACTIONS = {
		deleteRoles: deleteRoles
	};

	Liferay.componentReady('viewRolesManagementToolbar').then(function(
		managementToolbar
	) {
		managementToolbar.on('actionItemClicked', function(event) {
			var itemData = event.data.item.data;

			if (itemData && itemData.action && ACTIONS[itemData.action]) {
				ACTIONS[itemData.action](
					Liferay.Util.listCheckedExcept(
						document.<portlet:namespace />fm,
						'<portlet:namespace />allRowIds'
					)
				);
			}
		});
	});
</aui:script>