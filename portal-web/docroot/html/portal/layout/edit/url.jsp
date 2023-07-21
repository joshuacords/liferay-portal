<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/portal/layout/edit/init.jsp" %>

<%
String url = StringPool.BLANK;

if (selLayout != null) {
	UnicodeProperties typeSettingsProperties = selLayout.getTypeSettingsProperties();

	url = typeSettingsProperties.getProperty("url", StringPool.BLANK);
}
%>

<aui:input cssClass="lfr-input-text-container" id="url" label="url" name="TypeSettingsProperties--url--" type="text" value="<%= url %>">
	<aui:validator errorMessage="please-enter-a-valid-url" name="required" />
</aui:input>

<aui:script use="liferay-form">
	var form = Liferay.Form.get('<portlet:namespace />addPageFm');

	if (!form) {
		form = Liferay.Form.get('<portlet:namespace />editLayoutFm');
	}

	if (form) {
		var rules = form.formValidator.get('rules');

		var fieldName = '<portlet:namespace />TypeSettingsProperties--url--';

		if (!(fieldName in rules)) {
			rules[fieldName] = {
				custom: false,
				required: true
			};
		}
	}
</aui:script>