<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

SegmentsEntry segmentsEntry = (SegmentsEntry)row.getObject();
%>

<liferay-ui:icon-menu
	direction="left-side"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
	showWhenSingleIcon="<%= true %>"
>
	<c:if test="<%= SegmentsEntryPermission.contains(permissionChecker, segmentsEntry, ActionKeys.UPDATE) %>">
		<portlet:renderURL var="editURL">
			<portlet:param name="mvcRenderCommandName" value="editSegmentsEntry" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="segmentsEntryId" value="<%= String.valueOf(segmentsEntry.getSegmentsEntryId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			message="edit"
			url="<%= editURL %>"
		/>
	</c:if>

	<c:if test="<%= SegmentsEntryPermission.contains(permissionChecker, segmentsEntry, ActionKeys.VIEW) %>">
		<portlet:renderURL var="previewMembersURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
			<portlet:param name="mvcRenderCommandName" value="previewSegmentsEntryUsers" />
			<portlet:param name="segmentsEntryId" value="<%= String.valueOf(segmentsEntry.getSegmentsEntryId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			message="view-members"
			url="<%= previewMembersURL %>"
			useDialog="<%= true %>"
		/>
	</c:if>

	<c:if test="<%= SegmentsEntryPermission.contains(permissionChecker, segmentsEntry, ActionKeys.PERMISSIONS) %>">
		<liferay-security:permissionsURL
			modelResource="<%= SegmentsEntry.class.getName() %>"
			modelResourceDescription="<%= segmentsEntry.getName(locale) %>"
			resourcePrimKey="<%= String.valueOf(segmentsEntry.getSegmentsEntryId()) %>"
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

	<c:if test="<%= SegmentsEntryPermission.contains(permissionChecker, segmentsEntry, ActionKeys.DELETE) %>">
		<portlet:actionURL name="deleteSegmentsEntry" var="deleteURL">
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="segmentsEntryId" value="<%= String.valueOf(segmentsEntry.getSegmentsEntryId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			url="<%= deleteURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>