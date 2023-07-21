<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/action/init.jsp" %>

<aui:input cssClass="lfr-input-text-container" name="url" type="text" value='<%= GetterUtil.getString(typeSettingsProperties.get("url")) %>'>
	<aui:validator name="required" />
</aui:input>

<c:if test='<%= GetterUtil.getBoolean(request.getParameter("ajax")) %>'>
	<aui:script use="liferay-form">
		var form = Liferay.Form.get('<portlet:namespace />fm');

		if (form) {
			var rules = form.formValidator.get('rules');

			var fieldName = '<portlet:namespace />url';

			if (!(fieldName in rules)) {
				rules[fieldName] = {
					custom: false,
					required: true
				};
			}
		}
	</aui:script>
</c:if>