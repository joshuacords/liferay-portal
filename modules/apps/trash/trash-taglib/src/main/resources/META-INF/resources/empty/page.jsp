<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String confirmMessage = (String)request.getAttribute("liferay-trash:empty:confirmMessage");
String emptyMessage = (String)request.getAttribute("liferay-trash:empty:emptyMessage");
String infoMessage = (String)request.getAttribute("liferay-trash:empty:infoMessage");
int totalEntries = GetterUtil.getInteger(request.getAttribute("liferay-trash:empty:totalEntries"));
%>

<c:if test="<%= totalEntries > 0 %>">
	<div class="alert alert-info taglib-trash-empty">
		<aui:form action='<%= (String)request.getAttribute("liferay-trash:empty:portletURL") %>' name="emptyForm">
			<c:if test="<%= Validator.isNotNull(infoMessage) %>">
				<liferay-ui:message key="<%= infoMessage %>" />
			</c:if>

			<aui:a cssClass="alert-link trash-empty-link" href="javascript:;" id="empty" label="<%= emptyMessage %>" />

			<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.EMPTY_TRASH %>" />
			<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />

			<aui:button cssClass="trash-empty-button" type="submit" value="<%= emptyMessage %>" />
		</aui:form>
	</div>
</c:if>

<aui:script>
	var <%= namespace %>empty = document.getElementById('<%= namespace %>empty');

	if (<%= namespace %>empty) {
		<%= namespace %>empty.addEventListener('click', function(event) {
			if (
				confirm('<%= UnicodeLanguageUtil.get(request, confirmMessage) %>')
			) {
				var form = document.getElementById(
					'<portlet:namespace />emptyForm'
				);

				if (form) {
					submitForm(form);
				}
			}
		});
	}
</aui:script>