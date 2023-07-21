<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
long folderId = ParamUtil.getLong(request, "folderId");

Folder folder = FolderLocalServiceUtil.getFolder(folderId);
%>

<div class="mail-status"></div>

<aui:form name="dialogFm" onSubmit="event.preventDefault();">
	<aui:input name="folderId" type="hidden" value="<%= folderId %>" />

	<aui:input name="displayName" value="<%= folder.getDisplayName() %>" />

	<aui:button-row>
		<aui:button name="updateFolder" type="submit" value="update-folder" />
	</aui:button-row>
</aui:form>

<aui:script use="aui-io-deprecated">
	var A = AUI();

	var form = A.one('#<portlet:namespace />dialogFm');

	form.on('submit', function(event) {
		event.preventDefault();

		Liferay.Mail.setStatus(
			'info',
			'<liferay-ui:message key="updating-folder" />',
			true
		);

		A.io.request(themeDisplay.getLayoutURL() + '/-/mail/rename_folder', {
			dataType: 'JSON',
			form: {
				id: form.getDOMNode()
			},
			on: {
				failure: function(event, id, obj) {
					Liferay.Mail.setStatus(
						'error',
						'<liferay-ui:message key="unable-to-connect-with-mail-server" />'
					);
				},
				success: function(event, id, obj) {
					var responseData = this.get('responseData');

					Liferay.Mail.setStatus(
						responseData.status,
						responseData.message
					);

					if (responseData.status == 'success') {
						Liferay.Mail.loadFolders(Liferay.Mail.accountId);
					}
				}
			}
		});
	});
</aui:script>