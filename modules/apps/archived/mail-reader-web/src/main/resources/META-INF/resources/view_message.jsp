<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
MailManager mailManager = MailManager.getInstance(request);
%>

<c:if test="<%= mailManager != null %>">

	<%
	long folderId = ParamUtil.getLong(request, "folderId");
	int messageNumber = ParamUtil.getInteger(request, "messageNumber");
	int messagesPerPage = ParamUtil.getInteger(request, "messagesPerPage", 25);
	String orderByField = ParamUtil.getString(request, "orderByField");
	String orderByType = ParamUtil.getString(request, "orderByType");
	String keywords = ParamUtil.getString(request, "keywords");

	MessageDisplay messageDisplay = mailManager.getMessageDisplay(folderId, messageNumber, orderByField, orderByType, keywords);

	Message message = messageDisplay.getMessage();
	int messageCount = messageDisplay.getMessageCount();

	if (!message.hasFlag(MailConstants.FLAG_SEEN)) {
		mailManager.markAsRead(message.getAccountId(), folderId, message.getMessageId());
	}

	int pageNumber = (int)Math.ceil(messageNumber / (double)messagesPerPage);

	Folder folder = FolderLocalServiceUtil.getFolder(folderId);

	String folderName = folder.getDisplayName();

	if (Validator.isNotNull(keywords)) {
		folderName = LanguageUtil.get(request, "search-results");
	}
	%>

	<div class="row-fluid">
		<aui:nav-bar>
			<aui:nav>
				<aui:nav-item cssClass="messages-link" data-folderId="<%= folderId %>" data-keywords="<%= keywords %>" data-orderByField="<%= orderByField %>" data-orderByType="<%= orderByType %>" data-pageNumber="<%= pageNumber %>" href="javascript:;" iconClass="icon-arrow-left" label='<%= LanguageUtil.format(request, "back-to-x", folderName, false) %>' />

				<aui:nav-item cssClass="compose-message" data-messageType="reply" data-replyMessageId="<%= message.getMessageId() %>" iconClass="icon-reply" label="reply" />

				<aui:nav-item cssClass="compose-message" data-messageType="reply-all" data-replyMessageId="<%= message.getMessageId() %>" iconClass="icon-reply-all" label="reply-all" />

				<aui:nav-item cssClass="compose-message" data-messageType="forward" data-replyMessageId="<%= message.getMessageId() %>" iconClass="icon-share-alt" label="forward" />

				<aui:nav-item cssClass="delete-message" data-folderId="<%= folderId %>" data-keywords="<%= keywords %>" data-messageId="<%= message.getMessageId() %>" data-orderByField="<%= orderByField %>" data-orderByType="<%= orderByType %>" data-pageNumber="<%= pageNumber %>" iconClass="icon-trash" label="delete" />
			</aui:nav>

			<ul class="message-pager pager pull-right">
				<li class="<%= (messageNumber > 1) ? StringPool.BLANK : "disabled " %>previous">
					<aui:a cssClass="message-link" data-folderId="<%= folderId %>" data-keywords="<%= keywords %>" data-messageNumber="<%= messageNumber - 1 %>" data-orderByField="<%= orderByField %>" data-orderByType="<%= orderByType %>" href="javascript:;">&larr; <liferay-ui:message key="newer" /></aui:a>
				</li>
				<li class="message-count">
					<liferay-ui:message arguments="<%= new Object[] {messageNumber, messageCount} %>" key="x-of-x" translateArguments="<%= false %>" />
				</li>
				<li class="<%= (messageNumber < messageCount) ? StringPool.BLANK : "disabled " %>next">
					<aui:a cssClass="message-link" data-folderId="<%= folderId %>" data-keywords="<%= keywords %>" data-messageNumber="<%= messageNumber + 1 %>" data-orderByField="<%= orderByField %>" data-orderByType="<%= orderByType %>" href="javascript:;"><liferay-ui:message key="older" /> &rarr;</aui:a>
				</li>
			</ul>
		</aui:nav-bar>
	</div>

	<c:choose>
		<c:when test="<%= Validator.isNotNull(message.getSubject()) %>">
			<h3 class="subject"><%= HtmlUtil.escape(message.getSubject()) %></h3>
		</c:when>
		<c:otherwise>
			<h3 class="no-subject"><liferay-ui:message key="no-subject" /></h3>
		</c:otherwise>
	</c:choose>

	<div class="message-header">
		<dl class="dl-horizontal">
			<dt>
				<liferay-ui:message key="from" />
			</dt>
			<dd>
				<%= message.getSender() %>
			</dd>
			<dt>
				<liferay-ui:message key="to" />
			</dt>
			<dd>
				<%= message.getTo() %>
			</dd>

			<c:if test="<%= Validator.isNotNull(message.getCc()) %>">
				<dt>
					<liferay-ui:message key="cc" />
				</dt>
				<dd>
					<%= message.getCc() %>
				</dd>
				<dt></dt>
				<dd></dd>
			</c:if>

			<dt>
				<liferay-ui:message key="date" />
			</dt>
			<dd>
				<c:choose>
					<c:when test="<%= Validator.isNotNull(message.getSentDate()) %>">
						<%= dateFormatDateTime.format(message.getSentDate()) %>
					</c:when>
					<c:otherwise>
						<%= StringPool.DASH %>
					</c:otherwise>
				</c:choose>
			</dd>
		</dl>
	</div>

	<div id="messageContentContainer">
		<c:if test="<%= Validator.isNotNull(message.getBody()) %>">
			<liferay-util:include page="/view_message_content.jsp" servletContext="<%= application %>" />
		</c:if>
	</div>

	<aui:nav-bar>
		<aui:nav>
			<aui:nav-item cssClass="compose-message" data-messageType="reply" data-replyMessageId="<%= message.getMessageId() %>" iconClass="icon-reply" label="reply" />

			<aui:nav-item cssClass="compose-message" data-messageType="reply-all" data-replyMessageId="<%= message.getMessageId() %>" iconClass="icon-reply-all" label="reply-all" />

			<aui:nav-item cssClass="compose-message" data-messageType="forward" data-replyMessageId="<%= message.getMessageId() %>" iconClass="icon-share-alt" label="forward" />
		</aui:nav>
	</aui:nav-bar>

	<c:if test="<%= Validator.isNull(message.getBody()) %>">
		<aui:script use="aui-io-plugin-deprecated">
			A.one('#messageContentContainer').plug(A.Plugin.IO, {
				data: Liferay.Util.ns('<portlet:namespace />', {
					messageId: <%= message.getMessageId() %>
				}),
				method: 'POST',
				uri: themeDisplay.getLayoutURL() + '/-/mail/view_message_content'
			});
		</aui:script>
	</c:if>
</c:if>