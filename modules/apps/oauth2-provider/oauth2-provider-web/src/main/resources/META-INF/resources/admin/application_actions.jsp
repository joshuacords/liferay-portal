<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/admin/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

OAuth2Application oAuth2Application = (OAuth2Application)row.getObject();

String oAuth2ApplicationId = String.valueOf(oAuth2Application.getOAuth2ApplicationId());
%>

<liferay-ui:icon-menu
	direction="left-side"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
	showWhenSingleIcon="<%= true %>"
>
	<c:if test="<%= oAuth2AdminPortletDisplayContext.hasUpdatePermission(oAuth2Application) %>">
		<portlet:renderURL var="editURL">
			<portlet:param name="mvcRenderCommandName" value="/admin/update_oauth2_application" />
			<portlet:param name="oAuth2ApplicationId" value="<%= oAuth2ApplicationId %>" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			message="edit"
			url="<%= editURL.toString() %>"
		/>
	</c:if>

	<c:if test="<%= oAuth2AdminPortletDisplayContext.hasPermissionsPermission(oAuth2Application) %>">
		<liferay-security:permissionsURL
			modelResource="<%= OAuth2Application.class.getName() %>"
			modelResourceDescription="<%= oAuth2Application.getName() %>"
			resourcePrimKey="<%= oAuth2ApplicationId %>"
			var="permissionsURL"
			windowState="<%= LiferayWindowState.POP_UP.toString() %>"
		/>

		<liferay-ui:icon
			message="permissions"
			method="get"
			url="<%= permissionsURL %>"
			useDialog="<%= true %>"
		/>
	</c:if>

	<c:if test="<%= oAuth2AdminPortletDisplayContext.hasDeletePermission(oAuth2Application) %>">
		<portlet:actionURL name="/admin/delete_oauth2_applications" var="deleteURL">
			<portlet:param name="oAuth2ApplicationIds" value="<%= oAuth2ApplicationId %>" />
		</portlet:actionURL>

		<%
		int authorizationsCount = oAuth2AdminPortletDisplayContext.getOAuth2AuthorizationsCount(oAuth2Application);

		String confirmation = LanguageUtil.get(request, "are-you-sure-you-want-to-delete-the-application-there-are-no-authorizations-or-associated-tokens");

		if (authorizationsCount == 1) {
			confirmation = LanguageUtil.get(request, "are-you-sure-you-want-to-delete-the-application-this-action-revokes-one-authorization-and-associated-tokens");
		}
		else if (authorizationsCount > 0) {
			confirmation = LanguageUtil.format(request, "are-you-sure-you-want-to-delete-the-application-this-action-revokes-x-authorizations-and-associated-tokens", new String[] {String.valueOf(authorizationsCount)});
		}
		%>

		<liferay-ui:icon-delete
			confirmation="<%= confirmation %>"
			message="delete"
			url="<%= deleteURL.toString() %>"
		/>
	</c:if>
</liferay-ui:icon-menu>