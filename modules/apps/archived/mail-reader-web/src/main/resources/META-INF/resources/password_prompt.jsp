<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
long accountId = ParamUtil.getLong(request, "accountId");
%>

<div class="mail-status"></div>

<aui:form name="dialogFm" onSubmit="event.preventDefault();">
	<aui:input name="accountId" type="hidden" value="<%= accountId %>" />

	<%
	Account mailAccount = AccountLocalServiceUtil.getAccount(accountId);
	%>

	<aui:input label='<%= LanguageUtil.format(request, "please-enter-your-password-for-x", mailAccount.getAddress(), false) %>' name="password" type="password" />

	<aui:button-row>
		<aui:button name="login" type="submit" value="login" />
	</aui:button-row>
</aui:form>

<aui:script use="aui-io-deprecated">
	var form = A.one('#<portlet:namespace />dialogFm');

	A.one('#<portlet:namespace />login').on('click', function(event) {
		event.preventDefault();

		A.io.request(themeDisplay.getLayoutURL() + '/-/mail/store_password', {
			dataType: 'JSON',
			form: {
				id: form.getDOMNode()
			},
			method: 'POST',
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
						Liferay.Mail.loadAccount(
							<%= accountId %>,
							<%= ParamUtil.getLong(request, "inboxFolderId") %>
						);
					}
				}
			}
		});
	});
</aui:script>