<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommercePriceEntryDisplayContext commercePriceEntryDisplayContext = (CommercePriceEntryDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

CommercePriceEntry commercePriceEntry = null;

if (row != null) {
	commercePriceEntry = (CommercePriceEntry)row.getObject();
}
else {
	commercePriceEntry = (CommercePriceEntry)request.getAttribute("info_panel.jsp-entry");
}
%>

<liferay-ui:icon-menu
	direction="left-side"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
	showWhenSingleIcon="<%= true %>"
>
	<c:if test="<%= commercePriceEntryDisplayContext.hasPermission(commercePriceEntry.getCommercePriceListId(), ActionKeys.UPDATE) %>">
		<portlet:renderURL var="editURL">
			<portlet:param name="mvcRenderCommandName" value="editCommercePriceEntry" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="commercePriceEntryId" value="<%= String.valueOf(commercePriceEntry.getCommercePriceEntryId()) %>" />
			<portlet:param name="commercePriceListId" value="<%= String.valueOf(commercePriceEntry.getCommercePriceListId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			message="edit"
			url="<%= editURL %>"
		/>

		<portlet:actionURL name="editCommercePriceEntry" var="deleteURL">
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="commercePriceEntryId" value="<%= String.valueOf(commercePriceEntry.getCommercePriceEntryId()) %>" />
			<portlet:param name="commercePriceListId" value="<%= String.valueOf(commercePriceEntry.getCommercePriceListId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			url="<%= deleteURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>