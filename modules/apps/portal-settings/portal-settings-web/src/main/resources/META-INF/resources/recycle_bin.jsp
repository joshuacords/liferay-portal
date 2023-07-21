<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<h3><liferay-ui:message key="recycle-bin" /></h3>

<aui:fieldset>
	<aui:input helpMessage="enable-recycle-bin-default" id="trashEnabled" label="enable-recycle-bin" name='<%= "settings--" + PropsKeys.TRASH_ENABLED + "--" %>' type="checkbox" value="<%= PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.TRASH_ENABLED) %>" />
</aui:fieldset>

<script>
	(function() {
		var trashEnabledCheckbox = document.getElementById(
			'<portlet:namespace />trashEnabled'
		);

		if (trashEnabledCheckbox) {
			var trashEnabledDefault = trashEnabledCheckbox.checked;

			trashEnabledCheckbox.addEventListener('change', function(event) {
				if (!trashEnabledCheckbox.checked && trashEnabledDefault) {
					if (
						!confirm(
							'<%= HtmlUtil.escapeJS(LanguageUtil.get(request, "disabling-the-recycle-bin-prevents-the-restoring-of-content-that-has-been-moved-to-the-recycle-bin")) %>'
						)
					) {
						trashEnabledCheckbox.checked = true;
					}
				}
			});
		}
	})();
</script>