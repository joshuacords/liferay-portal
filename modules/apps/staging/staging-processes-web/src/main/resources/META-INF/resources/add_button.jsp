<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
int configurationType = ExportImportConfigurationConstants.TYPE_PUBLISH_LAYOUT_LOCAL;
boolean localPublishing = true;

if (stagingGroup.isStagedRemotely()) {
	configurationType = ExportImportConfigurationConstants.TYPE_PUBLISH_LAYOUT_REMOTE;
	localPublishing = false;
}

List<ExportImportConfiguration> exportImportConfigurations = ExportImportConfigurationLocalServiceUtil.getExportImportConfigurations(stagingGroupId, configurationType);
%>

<c:if test="<%= GroupPermissionUtil.contains(permissionChecker, stagingGroupId, ActionKeys.PUBLISH_STAGING) %>">
	<liferay-frontend:add-menu
		inline="<%= true %>"
	>

		<%
		for (ExportImportConfiguration exportImportConfiguration : exportImportConfigurations) {
		%>

			<portlet:renderURL var="addNewProcessURL">
				<portlet:param name="mvcRenderCommandName" value="publishLayouts" />
				<portlet:param name="<%= Constants.CMD %>" value="<%= localPublishing ? Constants.PUBLISH_TO_LIVE : Constants.PUBLISH_TO_REMOTE %>" />
				<portlet:param name="exportImportConfigurationId" value="<%= String.valueOf(exportImportConfiguration.getExportImportConfigurationId()) %>" />
				<portlet:param name="groupId" value="<%= String.valueOf(stagingGroupId) %>" />
			</portlet:renderURL>

			<liferay-frontend:add-menu-item
				title="<%= exportImportConfiguration.getName() %>"
				url="<%= addNewProcessURL %>"
			/>

		<%
		}
		%>

		<portlet:renderURL var="addNewCustomProcessURL">
			<portlet:param name="mvcRenderCommandName" value="publishLayouts" />
			<portlet:param name="<%= Constants.CMD %>" value="<%= localPublishing ? Constants.PUBLISH_TO_LIVE : Constants.PUBLISH_TO_REMOTE %>" />
			<portlet:param name="groupId" value="<%= String.valueOf(stagingGroupId) %>" />
			<portlet:param name="privateLayout" value="<%= Boolean.FALSE.toString() %>" />
		</portlet:renderURL>

		<liferay-frontend:add-menu-item
			title='<%= LanguageUtil.get(request, "custom-publication") %>'
			url="<%= addNewCustomProcessURL %>"
		/>
	</liferay-frontend:add-menu>
</c:if>