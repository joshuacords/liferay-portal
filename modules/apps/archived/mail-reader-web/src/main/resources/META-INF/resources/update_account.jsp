<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
long accountId = ParamUtil.getLong(request, "accountId");
String address = ParamUtil.getString(request, "address");
String personalName = ParamUtil.getString(request, "personalName");
String protocol = ParamUtil.getString(request, "protocol");
String incomingHostName = ParamUtil.getString(request, "incomingHostName");
int incomingPort = ParamUtil.getInteger(request, "incomingPort");
boolean incomingSecure = ParamUtil.getBoolean(request, "incomingSecure");
String outgoingHostName = ParamUtil.getString(request, "outgoingHostName");
int outgoingPort = ParamUtil.getInteger(request, "outgoingPort");
boolean outgoingSecure = ParamUtil.getBoolean(request, "outgoingSecure");
String login = ParamUtil.getString(request, "login");
String password = ParamUtil.getString(request, "password");
boolean savePassword = ParamUtil.getBoolean(request, "savePassword");
String signature = ParamUtil.getString(request, "signature");
boolean useSignature = ParamUtil.getBoolean(request, "useSignature");
String folderPrefix = ParamUtil.getString(request, "folderPrefix");
boolean defaultSender = ParamUtil.getBoolean(request, "defaultSender");
boolean useLocalPartAsLogin = ParamUtil.getBoolean(request, "useLocalPartAsLogin");
long inboxFolderId = ParamUtil.getLong(request, "inboxFolderId");
long draftFolderId = ParamUtil.getLong(request, "draftFolderId");
long sentFolderId = ParamUtil.getLong(request, "sentFolderId");
long trashFolderId = ParamUtil.getLong(request, "trashFolderId");

if (useLocalPartAsLogin) {
	login = address.split("@")[0];
}

MailManager mailManager = MailManager.getInstance(request);
%>

<c:if test="<%= mailManager != null %>">
	<c:choose>
		<c:when test="<%= accountId == 0 %>">
			<%= mailManager.addAccount(address, personalName, protocol, incomingHostName, incomingPort, incomingSecure, outgoingHostName, outgoingPort, outgoingSecure, login, password, savePassword, signature, useSignature, folderPrefix, defaultSender) %>
		</c:when>
		<c:otherwise>
			<%= mailManager.updateAccount(accountId, personalName, password, savePassword, signature, useSignature, folderPrefix, defaultSender, inboxFolderId, draftFolderId, sentFolderId, trashFolderId) %>
		</c:otherwise>
	</c:choose>
</c:if>