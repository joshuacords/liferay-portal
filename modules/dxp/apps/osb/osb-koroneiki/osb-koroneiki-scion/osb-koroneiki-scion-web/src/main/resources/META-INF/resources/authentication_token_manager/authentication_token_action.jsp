<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

AuthenticationToken authenticationToken = (AuthenticationToken)row.getObject();
%>

<liferay-ui:icon-menu
	direction="left-side"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
	showWhenSingleIcon="<%= true %>"
>
	<portlet:renderURL var="editURL">
		<portlet:param name="mvcRenderCommandName" value="/authentication_token_manager/edit_authentication_token" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="authenticationTokenId" value="<%= String.valueOf(authenticationToken.getAuthenticationTokenId()) %>" />
	</portlet:renderURL>

	<liferay-ui:icon
		message="edit"
		url="<%= editURL %>"
	/>

	<c:choose>
		<c:when test="<%= authenticationToken.getStatus() == WorkflowConstants.STATUS_APPROVED %>">
			<portlet:actionURL name="/authentication_token_manager/edit_authentication_token" var="deactivateURL">
				<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DEACTIVATE %>" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="authenticationTokenId" value="<%= String.valueOf(authenticationToken.getAuthenticationTokenId()) %>" />
			</portlet:actionURL>

			<liferay-ui:icon-delete
				confirmation="are-you-sure-you-want-to-deactivate-this-authentication-token"
				message="deactivate"
				url="<%= deactivateURL %>"
			/>
		</c:when>
		<c:otherwise>
			<portlet:actionURL name="/authentication_token_manager/edit_authentication_token" var="activateURL">
				<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.RESTORE %>" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="authenticationTokenId" value="<%= String.valueOf(authenticationToken.getAuthenticationTokenId()) %>" />
			</portlet:actionURL>

			<liferay-ui:icon-delete
				confirmation="are-you-sure-you-want-to-activate-this-authentication-token"
				message="activate"
				url="<%= activateURL %>"
			/>

			<portlet:actionURL name="/authentication_token_manager/edit_authentication_token" var="deleteURL">
				<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="authenticationTokenId" value="<%= String.valueOf(authenticationToken.getAuthenticationTokenId()) %>" />
			</portlet:actionURL>

			<liferay-ui:icon-delete
				confirmation="are-you-sure-you-want-to-delete-this-authentication-token"
				url="<%= deleteURL %>"
			/>
		</c:otherwise>
	</c:choose>
</liferay-ui:icon-menu>