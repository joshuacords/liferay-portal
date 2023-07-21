<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
int length = ParamUtil.getInteger(request, "length", 8);
boolean numbers = ParamUtil.getBoolean(request, "numbers");
boolean lowerCaseLetters = ParamUtil.getBoolean(request, "lowerCaseLetters");
boolean upperCaseLetters = ParamUtil.getBoolean(request, "upperCaseLetters");

String key = StringPool.BLANK;

if (numbers) {
	key += PwdGenerator.KEY1;
}

if (lowerCaseLetters) {
	key += PwdGenerator.KEY3;
}

if (upperCaseLetters) {
	key += PwdGenerator.KEY2;
}

String newPassword = StringPool.BLANK;

try {
	newPassword = PwdGenerator.getPassword(key, length);
}
catch (Exception e) {
}
%>

<liferay-portlet:renderURL var="generatePasswordURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>" />

<aui:form action="<%= generatePasswordURL %>" method="post" name="fm">
	<aui:fieldset>
		<aui:field-wrapper label="password-settings">
			<aui:input name="numbers" type="checkbox" value="<%= numbers %>" />

			<aui:input name="lowerCaseLetters" type="checkbox" value="<%= lowerCaseLetters %>" />

			<aui:input name="upperCaseLetters" type="checkbox" value="<%= upperCaseLetters %>" />

			<aui:select name="length">

				<%
				for (int i = 4; i <= 16; i++) {
				%>

					<aui:option label="<%= i %>" selected="<%= i == length %>" value="<%= i %>" />

				<%
				}
				%>

			</aui:select>

			<aui:input name="newPassword" type="resource" value="<%= newPassword %>" />
		</aui:field-wrapper>
	</aui:fieldset>

	<aui:button type="submit" value="generate" />
</aui:form>

<aui:script use="aui-parse-content">
	var form = A.one('#<portlet:namespace />fm');

	var parentNode = form.get('parentNode');

	parentNode.plug(A.Plugin.ParseContent);

	form.on('submit', function(event) {
		Liferay.Util.fetch(form.getAttribute('action'), {
			body: new FormData(form.getDOM()),
			method: 'POST'
		})
			.then(function(response) {
				return response.text();
			})
			.then(function(response) {
				parentNode.setContent(response);
			});

		event.halt();
	});
</aui:script>