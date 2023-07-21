<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/dynamic_section/init.jsp" %>

<div class="autofit-row manage-collaborators sidebar-panel">
	<div class="autofit-col manage-collaborators-owner">

		<%
		FileEntry fileEntry = (FileEntry)request.getAttribute("info_panel.jsp-fileEntry");

		User owner = UserLocalServiceUtil.fetchUser(fileEntry.getUserId());
		%>

		<div class="lfr-portal-tooltip" data-title="<%= LanguageUtil.format(resourceBundle, "x-is-the-owner", owner.getFullName()) %>">
			<liferay-ui:user-portrait
				user="<%= owner %>"
			/>
		</div>
	</div>

	<div class="autofit-col autofit-col-expand">
		<div class="autofit-row">

			<%
			List<User> sharingEntryToUsers = (List<User>)request.getAttribute("info_panel_file_entry.jsp-sharingEntryToUsers");

			for (User sharingEntryToUser : sharingEntryToUsers) {
			%>

				<div class="autofit-col manage-collaborators-collaborator">
					<div class="lfr-portal-tooltip" data-title="<%= sharingEntryToUser.getFullName() %>">
						<liferay-ui:user-portrait
							user="<%= sharingEntryToUser %>"
						/>
					</div>
				</div>

			<%
			}

			int sharingEntriesCount = GetterUtil.getInteger(request.getAttribute("info_panel_file_entry.jsp-sharingEntriesCount"));
			%>

			<c:if test="<%= sharingEntriesCount > 4 %>">

				<%
				int moreCollaboratorsCount = sharingEntriesCount - 4;
				%>

				<div class="autofit-col manage-collaborators-collaborator">
					<div class="lfr-portal-tooltip" data-title="<%= LanguageUtil.format(resourceBundle, (moreCollaboratorsCount == 1) ? "x-more-collaborator" : "x-more-collaborators", moreCollaboratorsCount) %>">
						<clay:sticker
							elementClasses="user-icon-color-0"
							icon="users"
							shape="circle"
						/>
					</div>
				</div>
			</c:if>
		</div>
	</div>
</div>

<c:if test='<%= GetterUtil.getBoolean(request.getAttribute("info_panel_file_entry.jsp-showManageCollaborators")) %>'>
	<div class="autofit-row sidebar-panel">
		<clay:button
			elementClasses="btn-link manage-collaborators-btn"
			id='<%= liferayPortletResponse.getNamespace() + "manageCollaboratorsButton" %>'
			label='<%= LanguageUtil.get(resourceBundle, "manage-collaborators") %>'
			size="sm"
			style="link"
		/>
	</div>

	<%
	PortletURL manageCollaboratorsRenderURL = PortletProviderUtil.getPortletURL(request, SharingEntry.class.getName(), PortletProvider.Action.MANAGE);

	manageCollaboratorsRenderURL.setParameter("classNameId", String.valueOf(ClassNameLocalServiceUtil.getClassNameId(DLFileEntry.class.getName())));
	manageCollaboratorsRenderURL.setParameter("classPK", String.valueOf(fileEntry.getFileEntryId()));
	manageCollaboratorsRenderURL.setParameter("dialogId", liferayPortletResponse.getNamespace() + "manageCollaboratorsDialog");
	manageCollaboratorsRenderURL.setWindowState(LiferayWindowState.POP_UP);
	%>

	<aui:script>
		var button = document.getElementById(
			'<portlet:namespace />manageCollaboratorsButton'
		);

		button.addEventListener('click', function() {
			Liferay.Util.openWindow({
				dialog: {
					destroyOnHide: true,
					height: 470,
					width: 600,
					on: {
						visibleChange: function(event) {
							if (!event.newVal) {
								Liferay.Util.getOpener().Liferay.fire(
									'refreshInfoPanel'
								);
							}
						}
					}
				},
				id: '<portlet:namespace />manageCollaboratorsDialog',
				title: '<%= LanguageUtil.get(resourceBundle, "collaborators") %>',
				uri: '<%= manageCollaboratorsRenderURL.toString() %>'
			});
		});
	</aui:script>
</c:if>