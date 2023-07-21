<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<div class="container-fluid-1280">
	<div class="card main-content-card">
		<div class="card-row card-row-padded">

			<%
			long repositoryId = themeDisplay.getScopeGroupId();

			Folder rootFolder = ShindigUtil.getGadgetEditorRootFolder(repositoryId);
			%>

			<div id="<portlet:namespace />editor"></div>

			<aui:script use="opensocial-editor">
				new Liferay.OpenSocial.Editor(
					{
						baseRenderURL: '<%= PortletURLFactoryUtil.create(request, portletDisplay.getId(), themeDisplay.getPlid(), PortletRequest.RENDER_PHASE) %>',

						<portlet:renderURL var="editorGadgetURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
							<portlet:param name="mvcPath" value="/admin/edit_gadget.jsp" />
							<portlet:param name="editorGadgetURL" value="editorGadgetURLPlaceholder" />
						</portlet:renderURL>

						editorGadgetURL: '<%= editorGadgetURL %>',
						gadgetPortletId: '<%= portletDisplay.getId() %>',
						gadgetServerBase: '<%= PortalUtil.getPathContext(renderRequest) %>/gadgets/',
						namespace: '<portlet:namespace />',
						publishGadgetPermission: <%= GadgetPermission.contains(themeDisplay.getPermissionChecker(), themeDisplay.getScopeGroupId(), ActionKeys.PUBLISH_GADGET) %>,
						repositoryId: '<%= repositoryId %>',
						resourceURL: '<portlet:resourceURL />',
						rootFolderId: '<%= rootFolder.getFolderId() %>'
					}
				).render('#<portlet:namespace />editor');
			</aui:script>
		</div>
	</div>
</div>