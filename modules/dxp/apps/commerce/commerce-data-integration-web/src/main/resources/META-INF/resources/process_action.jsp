<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

CommerceDataIntegrationProcess commerceDataIntegrationProcess = (CommerceDataIntegrationProcess)row.getObject();
%>

<liferay-ui:icon-menu
	direction="left-side"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
	showWhenSingleIcon="<%= true %>"
>
	<c:if test="<%= CommerceDataintegrationProcessPermission.contains(permissionChecker, commerceDataIntegrationProcess, ActionKeys.UPDATE) %>">
		<portlet:renderURL var="editURL">
			<portlet:param name="mvcRenderCommandName" value="editCommerceDataIntegrationProcess" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="commerceDataIntegrationProcessId" value="<%= String.valueOf(commerceDataIntegrationProcess.getCommerceDataIntegrationProcessId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			message="edit"
			url="<%= editURL %>"
		/>
	</c:if>

	<c:if test="<%= CommerceDataintegrationProcessPermission.contains(permissionChecker, commerceDataIntegrationProcess, ActionKeys.DELETE) && !commerceDataIntegrationProcess.isSystem() %>">
		<portlet:actionURL name="editCommerceDataIntegrationProcess" var="deleteURL">
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="commerceDataIntegrationProcessId" value="<%= String.valueOf(commerceDataIntegrationProcess.getCommerceDataIntegrationProcessId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			url="<%= deleteURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>