<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceApplicationAdminDisplayContext commerceApplicationAdminDisplayContext = (CommerceApplicationAdminDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

CommerceApplicationBrand commerceApplicationBrand = (CommerceApplicationBrand)row.getObject();
%>

<liferay-ui:icon-menu
	direction="left-side"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
	showWhenSingleIcon="<%= true %>"
>
	<c:if test="<%= commerceApplicationAdminDisplayContext.hasCommerceApplicationBrandPermissions(commerceApplicationBrand.getCommerceApplicationBrandId(), ActionKeys.UPDATE) %>">
		<portlet:renderURL var="editURL">
			<portlet:param name="mvcRenderCommandName" value="editCommerceApplicationBrand" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="commerceApplicationBrandId" value="<%= String.valueOf(commerceApplicationBrand.getCommerceApplicationBrandId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			message="edit"
			url="<%= editURL %>"
		/>
	</c:if>

	<c:if test="<%= commerceApplicationAdminDisplayContext.hasCommerceApplicationBrandPermissions(commerceApplicationBrand.getCommerceApplicationBrandId(), ActionKeys.DELETE) %>">
		<portlet:actionURL name="editCommerceApplicationBrand" var="deleteURL">
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="commerceApplicationBrandId" value="<%= String.valueOf(commerceApplicationBrand.getCommerceApplicationBrandId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			url="<%= deleteURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>