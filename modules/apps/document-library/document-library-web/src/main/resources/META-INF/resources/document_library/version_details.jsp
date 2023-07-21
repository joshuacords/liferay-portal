<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/document_library/init.jsp" %>

<%
boolean checkedOut = GetterUtil.getBoolean(request.getAttribute("edit_file_entry.jsp-checkedOut"));
%>

<div id="<portlet:namespace />versionDetails" style="display: none;">
	<aui:fieldset>
		<h5 class="control-label"><liferay-ui:message key="select-whether-this-is-a-major-or-minor-version" /></h5>

		<aui:input checked="<%= checkedOut %>" label="major-version" name="versionDetailsVersionIncrease" onChange='<%= renderResponse.getNamespace() + "showVersionNotes(event);" %>' type="radio" value="<%= DLVersionNumberIncrease.MAJOR %>" />

		<aui:input checked="<%= !checkedOut %>" label="minor-version" name="versionDetailsVersionIncrease" onChange='<%= renderResponse.getNamespace() + "showVersionNotes(event);" %>' type="radio" value="<%= DLVersionNumberIncrease.MINOR %>" />

		<aui:input checked="<%= false %>" label="keep-current-version-number" name="versionDetailsVersionIncrease" onChange='<%= renderResponse.getNamespace() + "hideVersionNotes(event);" %>' type="radio" value="<%= DLVersionNumberIncrease.NONE %>" />

		<aui:input label="version-notes" maxLength="75" name="versionDetailsChangeLog" />
	</aui:fieldset>

	<aui:script>
		function <portlet:namespace />hideVersionNotes(event) {
			var fieldset = event.currentTarget.closest('fieldset');

			var versionNotes = fieldset.querySelector(
				'#<portlet:namespace />versionDetailsChangeLog'
			);

			if (versionNotes) {
				versionNotes.parentElement.classList.add('hide');
			}
		}

		function <portlet:namespace />showVersionNotes(event) {
			var fieldset = event.currentTarget.closest('fieldset');

			var versionNotes = fieldset.querySelector(
				'#<portlet:namespace />versionDetailsChangeLog'
			);

			if (versionNotes) {
				versionNotes.parentElement.classList.remove('hide');
			}
		}
	</aui:script>
</div>