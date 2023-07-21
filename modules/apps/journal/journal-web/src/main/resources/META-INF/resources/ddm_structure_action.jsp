<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

DDMStructure ddmStructure = (DDMStructure)row.getObject();
%>

<liferay-ui:icon-menu
	direction="left-side"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
	showWhenSingleIcon="<%= true %>"
>
	<c:if test="<%= DDMStructurePermission.contains(permissionChecker, ddmStructure, ActionKeys.UPDATE) %>">
		<portlet:renderURL var="ediDDMStructuretURL">
			<portlet:param name="mvcPath" value="/edit_ddm_structure.jsp" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="ddmStructureId" value="<%= String.valueOf(ddmStructure.getStructureId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			message="edit"
			url="<%= ediDDMStructuretURL %>"
		/>
	</c:if>

	<portlet:renderURL var="editDDMStructureDefaultValuesURL">
		<portlet:param name="mvcPath" value="/edit_article.jsp" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="groupId" value="<%= String.valueOf(ddmStructure.getGroupId()) %>" />
		<portlet:param name="classNameId" value="<%= String.valueOf(PortalUtil.getClassNameId(DDMStructure.class)) %>" />
		<portlet:param name="classPK" value="<%= String.valueOf(ddmStructure.getStructureId()) %>" />
		<portlet:param name="ddmStructureKey" value="<%= ddmStructure.getStructureKey() %>" />
	</portlet:renderURL>

	<c:if test="<%= DDMStructurePermission.contains(permissionChecker, ddmStructure, ActionKeys.UPDATE) %>">
		<liferay-ui:icon
			message="edit-default-values"
			url="<%= editDDMStructureDefaultValuesURL %>"
		/>
	</c:if>

	<c:if test="<%= DDMStructurePermission.contains(permissionChecker, ddmStructure, ActionKeys.VIEW) %>">
		<portlet:renderURL var="manageViewURL">
			<portlet:param name="mvcPath" value="/view_ddm_templates.jsp" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="classPK" value="<%= String.valueOf(ddmStructure.getStructureId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			message="manage-templates"
			url="<%= manageViewURL %>"
		/>
	</c:if>

	<c:if test="<%= DDMStructurePermission.contains(permissionChecker, ddmStructure, ActionKeys.PERMISSIONS) %>">
		<liferay-security:permissionsURL
			modelResource="<%= DDMStructurePermission.getStructureModelResourceName(ddmStructure.getClassNameId()) %>"
			modelResourceDescription="<%= ddmStructure.getName(locale) %>"
			resourcePrimKey="<%= String.valueOf(ddmStructure.getStructureId()) %>"
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

	<%
	Group scopeGroup = themeDisplay.getScopeGroup();
	%>

	<c:if test="<%= (!scopeGroup.hasLocalOrRemoteStagingGroup() || scopeGroup.isStagingGroup()) && DDMStructurePermission.containsAddDDMStructurePermission(permissionChecker, scopeGroupId, ddmStructure.getClassNameId()) %>">
		<portlet:renderURL var="copyDDMStructureURL">
			<portlet:param name="mvcPath" value="/copy_ddm_structure.jsp" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="ddmStructureId" value="<%= String.valueOf(ddmStructure.getStructureId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			message="copy"
			url="<%= copyDDMStructureURL %>"
		/>
	</c:if>

	<c:if test="<%= DDMStructurePermission.contains(permissionChecker, ddmStructure, ActionKeys.DELETE) %>">
		<portlet:actionURL copyCurrentRenderParameters="<%= true %>" name="/journal/delete_ddm_structure" var="deleteURL">
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="ddmStructureId" value="<%= String.valueOf(ddmStructure.getStructureId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			url="<%= deleteURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>