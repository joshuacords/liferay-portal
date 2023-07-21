<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
PortletURL portletURL = (PortletURL)request.getAttribute("view.jsp-portletURL");

UserGroupSearch userGroupSearch = new UserGroupSearch(renderRequest, portletURL);

UserGroupDisplayTerms searchTerms = (UserGroupDisplayTerms)userGroupSearch.getSearchTerms();

LinkedHashMap<String, Object> userGroupParams = new LinkedHashMap<String, Object>();

if (portletName.equals(PortletKeys.MY_SITES_DIRECTORY)) {
	LinkedHashMap<String, Object> groupParams = new LinkedHashMap<String, Object>();

	groupParams.put("inherit", Boolean.FALSE);
	groupParams.put("site", Boolean.TRUE);
	groupParams.put("usersGroups", user.getUserId());

	List<Group> groups = GroupLocalServiceUtil.search(user.getCompanyId(), groupParams, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

	userGroupParams.put(UserGroupFinderConstants.PARAM_KEY_USER_GROUPS_GROUPS, SitesUtil.filterGroups(groups, PropsValues.MY_SITES_DIRECTORY_SITE_EXCLUDES));
}
else if (portletName.equals(PortletKeys.SITE_MEMBERS_DIRECTORY)) {
	userGroupParams.put(UserGroupFinderConstants.PARAM_KEY_USER_GROUPS_GROUPS, Long.valueOf(themeDisplay.getScopeGroupId()));
}

String keywords = searchTerms.getKeywords();

if (Validator.isNotNull(keywords)) {
	userGroupParams.put("expandoAttributes", keywords);
}

int userGroupsCount = UserGroupServiceUtil.searchCount(company.getCompanyId(), keywords, userGroupParams);

userGroupSearch.setTotal(userGroupsCount);

List<UserGroup> userGroups = UserGroupServiceUtil.search(company.getCompanyId(), keywords, userGroupParams, userGroupSearch.getStart(), userGroupSearch.getEnd(), userGroupSearch.getOrderByComparator());

userGroupSearch.setResults(userGroups);
%>

<aui:input disabled="<%= true %>" name="userGroupsRedirect" type="hidden" value="<%= portletURL.toString() %>" />

<liferay-frontend:management-bar>
	<liferay-frontend:management-bar-buttons>
		<liferay-frontend:management-bar-display-buttons
			displayViews='<%= new String[] {"list"} %>'
			portletURL="<%= portletURL %>"
			selectedDisplayStyle="list"
		/>
	</liferay-frontend:management-bar-buttons>

	<liferay-frontend:management-bar-filters>
		<liferay-frontend:management-bar-navigation
			navigationKeys='<%= new String[] {"all"} %>'
			portletURL="<%= portletURL %>"
		/>

		<liferay-frontend:management-bar-sort
			orderByCol="<%= userGroupSearch.getOrderByCol() %>"
			orderByType="<%= userGroupSearch.getOrderByType() %>"
			orderColumns='<%= new String[] {"name"} %>'
			portletURL="<%= portletURL %>"
		/>

		<c:if test='<%= ParamUtil.getBoolean(request, "showSearch", true) %>'>
			<li>
				<liferay-ui:input-search
					markupView="lexicon"
				/>
			</li>
		</c:if>
	</liferay-frontend:management-bar-filters>
</liferay-frontend:management-bar>

<div class="container-fluid-1280">
	<liferay-ui:search-container
		searchContainer="<%= userGroupSearch %>"
	>
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

			<liferay-ui:search-container-column-jsp
				align="right"
				cssClass="entry-action"
				path="/user_group_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>