<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
MailManager mailManager = MailManager.getInstance(request);

if (mailManager == null) {
	return;
}

UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(liferayPortletRequest);

long accountId = ParamUtil.getLong(uploadPortletRequest, "accountId");
long messageId = ParamUtil.getLong(uploadPortletRequest, "messageId");
String to = ParamUtil.getString(uploadPortletRequest, "to");
String cc = ParamUtil.getString(uploadPortletRequest, "cc");
String bcc = ParamUtil.getString(uploadPortletRequest, "bcc");
String subject = ParamUtil.getString(uploadPortletRequest, "subject");
String body = ParamUtil.getString(uploadPortletRequest, "body");

int attachmentCount = ParamUtil.getInteger(uploadPortletRequest, "attachmentCount");

List<MailFile> mailFiles = new ArrayList<MailFile>();

try {
	for (int i = 1; i <= attachmentCount; i++) {
		File file = uploadPortletRequest.getFile("attachment" + i, true);

		String fileName = uploadPortletRequest.getFileName("attachment" + i);

		long size = uploadPortletRequest.getSize("attachment" + i);

		if (Validator.isNull(fileName)) {
			continue;
		}

		MailFile mailFile = new MailFile(file, fileName, size);

		mailFiles.add(mailFile);
	}
%>

	<%= mailManager.sendMessage(accountId, messageId, to, cc, bcc, subject, body, mailFiles) %>

<%
}
finally {
	for (MailFile mailFile : mailFiles) {
		mailFile.cleanUp();
	}
}
%>