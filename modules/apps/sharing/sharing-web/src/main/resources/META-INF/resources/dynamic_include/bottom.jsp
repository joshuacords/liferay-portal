<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/dynamic_include/init.jsp" %>

<%
PortletURL manageCollaboratorsURL = PortletProviderUtil.getPortletURL(request, SharingEntry.class.getName(), PortletProvider.Action.MANAGE);

manageCollaboratorsURL.setWindowState(LiferayWindowState.POP_UP);

PortletURL sharingURL = PortletProviderUtil.getPortletURL(request, SharingEntry.class.getName(), PortletProvider.Action.EDIT);

sharingURL.setWindowState(LiferayWindowState.POP_UP);
%>

<aui:script sandbox="<%= true %>">
	function showDialog(uri, title, namespace, refreshOnClose) {
		Liferay.Util.openWindow({
			dialog: {
				centered: true,
				constrain: true,
				cssClass: 'sharing-dialog',
				destroyOnHide: true,
				modal: true,
				height: 540,
				width: 600,
				on: {
					visibleChange: function(event) {
						if (refreshOnClose && !event.newVal) {
							Liferay.Portlet.refresh('#p_p_id' + namespace);
						}
					}
				}
			},
			id: 'sharingDialog',
			title: Liferay.Util.escapeHTML(title),
			uri: uri
		});
	}

	var Sharing = {};

	Liferay.provide(
		Sharing,
		'share',
		function(classNameId, classPK, title, namespace, refreshOnClose) {
			var sharingParameters = {
				classNameId: classNameId,
				classPK: classPK
			};

			var sharingURL = Liferay.Util.PortletURL.createPortletURL(
				'<%= sharingURL.toString() %>',
				sharingParameters
			);

			showDialog(sharingURL.toString(), title, namespace, refreshOnClose);
		},
		['liferay-util-window']
	);

	Liferay.provide(
		Sharing,
		'manageCollaborators',
		function(classNameId, classPK, namespace, refreshOnClose) {
			var manageCollaboratorsParameters = {
				classNameId: classNameId,
				classPK: classPK
			};

			var manageCollaboratorsURL = Liferay.Util.PortletURL.createPortletURL(
				'<%= manageCollaboratorsURL.toString() %>',
				manageCollaboratorsParameters
			);

			showDialog(
				manageCollaboratorsURL.toString(),
				'<%= LanguageUtil.get(resourceBundle, "manage-collaborators") %>',
				namespace,
				refreshOnClose
			);
		},
		['liferay-util-window']
	);

	Liferay.Sharing = Sharing;
</aui:script>