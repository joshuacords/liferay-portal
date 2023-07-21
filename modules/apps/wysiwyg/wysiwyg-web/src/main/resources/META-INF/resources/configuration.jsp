<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />

<aui:form action="<%= configurationActionURL %>" cssClass="container-fluid-1280" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "saveMessage();" %>'>
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

	<aui:fieldset-group>
		<aui:fieldset>
			<liferay-ui:input-editor
				contents="<%= message %>"
			/>
		</aui:fieldset>
	</aui:fieldset-group>

	<aui:input name="preferences--message--" type="hidden" />

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button type="cancel" />
	</aui:button-row>
</aui:form>

<aui:script>
	function <portlet:namespace />saveMessage() {
		var editorVal = window.<portlet:namespace />editor.getHTML();

		var form = document.getElementById('<portlet:namespace />fm');

		if (form) {
			var message = form.querySelector('#<portlet:namespace />message');

			if (message) {
				message.value = editorVal;
			}

			submitForm(form);
		}
	}
</aui:script>