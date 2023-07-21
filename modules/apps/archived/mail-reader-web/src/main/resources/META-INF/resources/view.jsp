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

<c:choose>
	<c:when test="<%= mailManager != null %>">
		<div id="accountsContainer"></div>

		<div class="mail-status"></div>

		<div class="row-fluid" id="mailContainer">
			<div class="hide span3" id="controlContainer">
				<div id="foldersContainer"></div>
			</div>

			<div class="span9" id="contentContainer">
				<div class="hide" id="manageFoldersContainer"></div>

				<div id="messagesContainer"></div>

				<div class="hide" id="messageContainer"></div>

				<div class="hide" id="composeContainer"></div>
			</div>
		</div>

		<%
		Account initialAccount = mailManager.getInitialAccount();

		long initialAccountId = 0;
		long initialFolderId = 0;

		if (initialAccount != null) {
			initialAccountId = initialAccount.getAccountId();
			initialFolderId = initialAccount.getInboxFolderId();
		}
		%>

		<aui:script use="liferay-plugin-mail">
			Liferay.Mail.init({
				initialAccountId: <%= initialAccountId %>,
				initialFolderId: <%= initialFolderId %>,
				namespace: '<portlet:namespace />'
			});
		</aui:script>
	</c:when>
	<c:otherwise>
		<liferay-ui:message key="please-log-in-to-use-the-mail-widget" />
	</c:otherwise>
</c:choose>