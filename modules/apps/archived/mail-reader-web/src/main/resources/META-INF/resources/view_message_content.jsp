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
	long messageId = ParamUtil.getLong(request, "messageId");
	int messageNumber = ParamUtil.getInteger(request, "messageNumber");
	String orderByField = ParamUtil.getString(request, "orderByField");
	String orderByType = ParamUtil.getString(request, "orderByType");
	String keywords = ParamUtil.getString(request, "keywords");

	MessageDisplay messageDisplay = null;

	if (messageId > 0) {
		messageDisplay = mailManager.getMessageDisplay(messageId);
	}
	else {
		messageDisplay = mailManager.getMessageDisplay(folderId, messageNumber, orderByField, orderByType, keywords);
	}

	Message message = messageDisplay.getMessage();
	%>

	<%= message.getBody() %>

	<%
	List<Attachment> attachments = messageDisplay.getAttachments();
	%>

	<c:if test="<%= !attachments.isEmpty() %>">
		<hr />

		<ul>

			<%
			for (Attachment attachment : attachments) {
			%>

				<liferay-portlet:resourceURL var="attachmentURL">
					<portlet:param name="mvcPath" value="/attachment.jsp" />
					<portlet:param name="attachmentId" value="<%= String.valueOf(attachment.getAttachmentId()) %>" />
				</liferay-portlet:resourceURL>

				<li>
					<a href="<%= attachmentURL %>"><%= attachment.getFileName() %> - <%= attachment.getSize() %></a>
				</li>

			<%
			}
			%>

		</ul>
	</c:if>
</c:if>