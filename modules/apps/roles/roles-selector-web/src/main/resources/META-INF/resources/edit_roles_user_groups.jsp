<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String tabs1 = (String)request.getAttribute("edit_roles.jsp-tabs1");

Group group = (Group)request.getAttribute("edit_roles.jsp-group");
Role role = (Role)request.getAttribute("edit_roles.jsp-role");
long roleId = (Long)request.getAttribute("edit_roles.jsp-roleId");

PortletURL portletURL = (PortletURL)request.getAttribute("edit_roles.jsp-portletURL");
%>

<aui:input name="addUserGroupIds" type="hidden" />
<aui:input name="removeUserGroupIds" type="hidden" />

<liferay-ui:search-container
	rowChecker="<%= new UserGroupGroupRoleUserGroupChecker(renderResponse, group, role) %>"
	searchContainer="<%= new UserGroupSearch(renderRequest, portletURL) %>"
>

	<%
	UserGroupDisplayTerms searchTerms = (UserGroupDisplayTerms)searchContainer.getSearchTerms();

	LinkedHashMap<String, Object> userGroupParams = new LinkedHashMap<String, Object>();

	if (group.isSite()) {
		userGroupParams.put(UserGroupFinderConstants.PARAM_KEY_USER_GROUPS_GROUPS, Long.valueOf(group.getGroupId()));
	}

	if (tabs1.equals("current")) {
		userGroupParams.put(UserGroupFinderConstants.PARAM_KEY_USER_GROUP_GROUP_ROLE, new Long[] {Long.valueOf(roleId), Long.valueOf(group.getGroupId())});
	}
	%>

	<liferay-ui:user-group-search-container-results
		searchTerms="<%= searchTerms %>"
		userGroupParams="<%= userGroupParams %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.portal.kernel.model.UserGroup"
		escapedModel="<%= true %>"
		keyProperty="userGroupId"
		modelVar="userGroup"
	>
		<liferay-ui:search-container-column-text
			name="name"
			orderable="<%= true %>"
			property="name"
		/>

		<liferay-ui:search-container-column-text
			name="description"
			orderable="<%= true %>"
			property="description"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator
		markupView="lexicon"
	/>
</liferay-ui:search-container>