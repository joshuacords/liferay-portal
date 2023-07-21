<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
long accountId = ParamUtil.getLong(request, "accountId");
long messageId = ParamUtil.getLong(request, "messageId");
String to = ParamUtil.getString(request, "to");
String cc = ParamUtil.getString(request, "cc");
String bcc = ParamUtil.getString(request, "bcc");
String subject = ParamUtil.getString(request, "subject");
String body = ParamUtil.getString(request, "body");

MailManager mailManager = MailManager.getInstance(request);
%>

<c:if test="<%= mailManager != null %>">
	<%= mailManager.saveDraft(accountId, messageId, to, cc, bcc, subject, body, null) %>
</c:if>