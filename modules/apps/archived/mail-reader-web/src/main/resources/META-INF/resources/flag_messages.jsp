<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
long[] messageIds = ParamUtil.getLongValues(request, "messageIds");
int flag = ParamUtil.getInteger(request, "flag");
boolean value = ParamUtil.getBoolean(request, "value");

MailManager mailManager = MailManager.getInstance(request);
%>

<c:if test="<%= mailManager != null %>">
	<%= mailManager.flagMessages(messageIds, flag, value) %>
</c:if>