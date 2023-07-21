<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<liferay-portlet:renderURL varImpl="iteratorURL">
	<portlet:param name="mvcPath" value="/authorizations/view.jsp" />
</liferay-portlet:renderURL>

<liferay-ui:search-container
	emptyResultsMessage="no-oauth-users-were-found"
	iteratorURL="<%= iteratorURL %>"
	total="<%= OAuthUserLocalServiceUtil.getUserOAuthUsersCount(themeDisplay.getUserId()) %>"
>
	<liferay-ui:search-container-results
		results="<%= OAuthUserLocalServiceUtil.getUserOAuthUsers(themeDisplay.getUserId(), searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator()) %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.oauth.model.OAuthUser"
		modelVar="oAuthUser"
	>

		<%
		OAuthApplication oAuthApplication = OAuthApplicationLocalServiceUtil.getOAuthApplication(oAuthUser.getOAuthApplicationId());
		%>

		<liferay-ui:search-container-column-text
			name="application-name"
			value="<%= oAuthApplication.getName() %>"
		/>

		<liferay-ui:search-container-column-text
			name="access-token"
			property="accessToken"
		/>

		<liferay-ui:search-container-column-text
			name="access-secret"
			property="accessSecret"
		/>

		<liferay-ui:search-container-column-text
			name="access-type"
			translate="<%= true %>"
			value="<%= oAuthApplication.getAccessLevelLabel() %>"
		/>

		<c:if test="<%= OAuthUserPermission.contains(permissionChecker, oAuthUser, OAuthActionKeys.DELETE) %>">
			<liferay-portlet:actionURL name="deleteOAuthUser" var="revokeURL">
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="oAuthApplicationId" value="<%= String.valueOf(oAuthApplication.getOAuthApplicationId()) %>" />
			</liferay-portlet:actionURL>

			<liferay-ui:search-container-column-text
				href="<%= revokeURL %>"
				translate="<%= true %>"
				value="revoke"
			/>
		</c:if>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>