<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/export_import_entity_management_bar_button/init.jsp" %>

<%
scopeGroup = themeDisplay.getScopeGroup();
%>

<c:if test="<%= GroupPermissionUtil.contains(permissionChecker, scopeGroup, ActionKeys.EXPORT_IMPORT_PORTLET_INFO) && (Objects.equals(cmd, Constants.EXPORT) || (Objects.equals(cmd, Constants.PUBLISH) && (scopeGroup.isStagingGroup() || scopeGroup.isStagedRemotely()) && scopeGroup.isStagedPortlet(portletDisplay.getId()))) %>">

	<%
	String taglibURL = "javascript:Liferay.fire('" + renderResponse.getNamespace() + cmd + "'); void(0);";
	%>

	<liferay-frontend:management-bar-button
		href="<%= taglibURL %>"
		icon="import-export"
		label="<%= cmd %>"
	/>

	<%
	PortletURL portletURL = PortletURLFactoryUtil.create(request, ChangesetPortletKeys.CHANGESET, PortletRequest.ACTION_PHASE);

	portletURL.setParameter(ActionRequest.ACTION_NAME, "exportImportEntity");
	portletURL.setParameter("mvcRenderCommandName", "exportImportEntity");
	portletURL.setParameter("cmd", cmd);
	portletURL.setParameter("backURL", themeDisplay.getURLCurrent());
	portletURL.setParameter("portletId", portletDisplay.getId());
	%>

	<aui:script use="liferay-export-import-management-bar-button">
		var exportImportManagementBarButton = new Liferay.ExportImportManagementBarButton(
			{
				actionNamespace:
					'<%= PortalUtil.getPortletNamespace(ChangesetPortletKeys.CHANGESET) %>',
				cmd: '<%= cmd %>',
				exportImportEntityUrl: '<%= portletURL.toString() %>',
				namespace: '<portlet:namespace />',
				searchContainerId: '<%= searchContainerId %>',
				searchContainerMappingId: '<%= searchContainerMappingId %>'
			}
		);
	</aui:script>
</c:if>