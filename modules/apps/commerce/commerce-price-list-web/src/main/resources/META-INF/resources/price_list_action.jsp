<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommercePriceListDisplayContext commercePriceListDisplayContext = (CommercePriceListDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

CommercePriceList commercePriceList = null;

if (row != null) {
	commercePriceList = (CommercePriceList)row.getObject();
}
else {
	commercePriceList = (CommercePriceList)request.getAttribute("info_panel.jsp-entry");
}
%>

<liferay-ui:icon-menu
	direction="left-side"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
	showWhenSingleIcon="<%= true %>"
>
	<c:if test="<%= commercePriceListDisplayContext.hasPermission(commercePriceList.getCommercePriceListId(), ActionKeys.UPDATE) %>">
		<portlet:renderURL var="editURL">
			<portlet:param name="mvcRenderCommandName" value="editCommercePriceList" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="commercePriceListId" value="<%= String.valueOf(commercePriceList.getCommercePriceListId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			message="edit"
			url="<%= editURL %>"
		/>
	</c:if>

	<c:if test="<%= commercePriceListDisplayContext.hasPermission(commercePriceList.getCommercePriceListId(), ActionKeys.DELETE) %>">
		<portlet:actionURL name="editCommercePriceList" var="deleteURL">
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="commercePriceListId" value="<%= String.valueOf(commercePriceList.getCommercePriceListId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			url="<%= deleteURL %>"
		/>
	</c:if>

	<c:if test="<%= commercePriceListDisplayContext.hasPermission(commercePriceList.getCommercePriceListId(), ActionKeys.PERMISSIONS) %>">
		<liferay-security:permissionsURL
			modelResource="<%= CommercePriceList.class.getName() %>"
			modelResourceDescription="<%= commercePriceList.getName() %>"
			resourcePrimKey="<%= String.valueOf(commercePriceList.getCommercePriceListId()) %>"
			var="permissionsPriceListURL"
			windowState="<%= LiferayWindowState.POP_UP.toString() %>"
		/>

		<liferay-ui:icon
			message="permissions"
			method="get"
			url="<%= permissionsPriceListURL %>"
			useDialog="<%= true %>"
		/>
	</c:if>
</liferay-ui:icon-menu>