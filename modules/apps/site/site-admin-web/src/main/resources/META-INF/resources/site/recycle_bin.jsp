<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
Group liveGroup = (Group)request.getAttribute("site.liveGroup");

UnicodeProperties groupTypeSettings = null;

if (liveGroup != null) {
	groupTypeSettings = liveGroup.getTypeSettingsProperties();
}
else {
	groupTypeSettings = new UnicodeProperties();
}

boolean groupTrashEnabled = PropertiesParamUtil.getBoolean(groupTypeSettings, request, "trashEnabled", true);

int trashEntriesMaxAge = PropertiesParamUtil.getInteger(groupTypeSettings, request, "trashEntriesMaxAge", PrefsPropsUtil.getInteger(company.getCompanyId(), PropsKeys.TRASH_ENTRIES_MAX_AGE));
%>

<aui:input id="trashEnabled" label="enable-recycle-bin" name="trashEnabled" type="toggle-switch" value="<%= groupTrashEnabled %>" />

<div class="trash-entries-max-age">
	<aui:input disabled="<%= !groupTrashEnabled %>" helpMessage="trash-entries-max-age-help" label="trash-entries-max-age" name="trashEntriesMaxAge" type="text" value="<%= ((trashEntriesMaxAge % 1) == 0) ? GetterUtil.getInteger(trashEntriesMaxAge) : String.valueOf(trashEntriesMaxAge) %>">
		<aui:validator name="min"><%= PropsValues.TRASH_ENTRY_CHECK_INTERVAL %></aui:validator>
	</aui:input>
</div>

<script>
	var trashEnabledCheckbox = document.getElementById(
		'<portlet:namespace />trashEnabled'
	);

	if (trashEnabledCheckbox) {
		var trashEnabledDefault = trashEnabledCheckbox.checked;

		trashEnabledCheckbox.addEventListener('change', function(event) {
			var trashEnabled = trashEnabledCheckbox.checked;

			if (!trashEnabled && trashEnabledDefault) {
				if (
					!confirm(
						'<%= HtmlUtil.escapeJS(LanguageUtil.get(request, "disabling-the-recycle-bin-prevents-the-restoring-of-content-that-has-been-moved-to-the-recycle-bin")) %>'
					)
				) {
					trashEnabledCheckbox.checked = true;

					trashEnabled = true;
				}
			}

			var trashEntriesMaxAge = document.getElementById(
				'<portlet:namespace />trashEntriesMaxAge'
			);

			if (trashEntriesMaxAge) {
				Liferay.Util.toggleDisabled(trashEntriesMaxAge, !trashEnabled);
			}
		});
	}
</script>