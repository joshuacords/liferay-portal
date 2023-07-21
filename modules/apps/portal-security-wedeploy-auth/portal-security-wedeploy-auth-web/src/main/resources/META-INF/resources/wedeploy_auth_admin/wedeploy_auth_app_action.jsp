<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

WeDeployAuthApp weDeployAuthApp = (WeDeployAuthApp)row.getObject();

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcRenderCommandName", "/wedeploy_auth_admin/view");
%>

<liferay-ui:icon-menu
	direction="right"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
	showWhenSingleIcon="<%= true %>"
>
	<portlet:renderURL var="redirectURL">
		<portlet:param name="mvcRenderCommandName" value="/wedeploy_auth_admin/view" />
	</portlet:renderURL>

	<portlet:actionURL name="/wedeploy_auth_admin/edit_wedeploy_auth_app" var="deleteURL">
		<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
		<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
		<portlet:param name="weDeployAuthAppId" value="<%= String.valueOf(weDeployAuthApp.getWeDeployAuthAppId()) %>" />
	</portlet:actionURL>

	<c:if test="<%= WeDeployAuthAppPermission.contains(permissionChecker, weDeployAuthApp, ActionKeys.DELETE) %>">
		<liferay-ui:icon-delete
			url="<%= deleteURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>