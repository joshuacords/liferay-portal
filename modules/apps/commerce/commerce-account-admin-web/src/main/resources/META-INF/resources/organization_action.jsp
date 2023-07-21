<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceAccountOrganizationRelAdminDisplayContext commerceAccountOrganizationRelAdminDisplayContext = (CommerceAccountOrganizationRelAdminDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

CommerceAccountOrganizationRel commerceAccountOrganizationRel = (CommerceAccountOrganizationRel)row.getObject();
%>

<liferay-ui:icon-menu
	direction="left-side"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
	showWhenSingleIcon="<%= true %>"
>
	<c:if test="<%= commerceAccountOrganizationRelAdminDisplayContext.hasPermission(commerceAccountOrganizationRel.getCommerceAccountId(), ActionKeys.UPDATE) %>">
		<liferay-ui:icon
			message="edit"
			url="<%= commerceAccountOrganizationRelAdminDisplayContext.getEditOrganizationURL(commerceAccountOrganizationRel.getOrganizationId()) %>"
		/>
	</c:if>

	<c:if test="<%= commerceAccountOrganizationRelAdminDisplayContext.hasPermission(commerceAccountOrganizationRel.getCommerceAccountId(), ActionKeys.DELETE) %>">
		<portlet:actionURL name="editCommerceAccountOrganizationRel" var="deleteURL">
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="commerceAccountId" value="<%= String.valueOf(commerceAccountOrganizationRel.getCommerceAccountId()) %>" />
			<portlet:param name="organizationId" value="<%= String.valueOf(commerceAccountOrganizationRel.getOrganizationId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			message="remove"
			url="<%= deleteURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>