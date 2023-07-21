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
	<aui:nav-bar>
		<aui:nav>
			<aui:nav-item iconCssClass="icon-plus" label="add-mail-account" onClick="Liferay.Mail.addAccount();" />
		</aui:nav>
	</aui:nav-bar>

	<%
	List<Account> mailAccounts = mailManager.getAccounts();
	%>

	<c:if test="<%= !mailAccounts.isEmpty() %>">
		<ul class="nav nav-pills">

			<%
			for (Account mailAccount : mailAccounts) {
			%>

				<li class="tab <%= (mailAccount.getAccountId() == accountId) ? "active" : "" %>">
					<aui:a cssClass="folders-link" data-accountId="<%= mailAccount.getAccountId() %>" data-inboxFolderId="<%= mailAccount.getInboxFolderId() %>" href="javascript:;" label="<%= mailAccount.getAddress() %>" />
				</li>

			<%
			}
			%>

		</ul>
	</c:if>
</c:if>