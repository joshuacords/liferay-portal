<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
long accountId = ParamUtil.getLong(request, "accountId");

MailManager mailManager = MailManager.getInstance(request);
%>

<c:if test="<%= mailManager != null %>">
	<div class="controls-list well">
		<aui:nav cssClass="nav-list">
			<aui:nav-item cssClass="compose-message" data-messageId="0" data-messageType="new" data-replyMessageId="0" href="javascript:;" iconClass="icon-envelope" label="compose" />

			<aui:nav-item cssClass="divider" />

			<%
			Account mailAccount = AccountLocalServiceUtil.getAccount(accountId);

			List<Folder> folders = mailManager.getFolders(accountId, true, true);

			for (Folder folder : folders) {
				String folderIcon = "icon-folder-open";

				if (folder.getFolderId() == mailAccount.getInboxFolderId()) {
					folderIcon = "icon-inbox";
				}
				else if (folder.getFolderId() == mailAccount.getDraftFolderId()) {
					folderIcon = "icon-pencil";
				}
				else if (folder.getFolderId() == mailAccount.getSentFolderId()) {
					folderIcon = "icon-folder-close";
				}
				else if (folder.getFolderId() == mailAccount.getTrashFolderId()) {
					folderIcon = "icon-trash";
				}
			%>

				<aui:nav-item cssClass="messages-link" data-accountId="<%= accountId %>" data-folderId="<%= folder.getFolderId() %>" data-keywords="" data-orderByField="<%= MailConstants.ORDER_BY_SENT_DATE %>" data-orderByType="desc" data-pageNumber="1" href="javascript:;" iconClass="<%= folderIcon %>" label='<%= folder.getDisplayName() + " (" + MessageLocalServiceUtil.getFolderUnreadMessagesCount(folder.getFolderId()) + ")" %>' />

			<%
			}
			%>

			<aui:nav-item cssClass="divider" />

			<aui:nav-item cssClass="manage-folders" data-messageId="0" data-messageType="new" data-replyMessageId="0" href="javascript:;" iconClass="icon-cogs" label="manage-folders" />

			<aui:nav-item cssClass="edit-account" data-messageId="0" data-messageType="new" data-replyMessageId="0" href="javascript:;" iconClass="icon-cog" label="edit-account" />
		</aui:nav>
	</div>
</c:if>