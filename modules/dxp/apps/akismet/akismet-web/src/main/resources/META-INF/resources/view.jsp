<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
portletURL.setWindowState(WindowState.MAXIMIZED);

request.setAttribute("view.jsp-portletURL", portletURL);
%>

<liferay-ui:error exception="<%= NoSuchMessageException.class %>" message="the-message-could-not-be-found" />
<liferay-ui:error exception="<%= PrincipalException.class %>" message="you-do-not-have-the-required-permissions" />
<liferay-ui:error exception="<%= RequiredMessageException.class %>" message="you-cannot-delete-a-root-message-that-has-more-than-one-immediate-reply" />

<%@ include file="/message_boards.jspf" %>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />deleteMBMessages',
		function(dicussion) {
			var deleteMBMessageIds = Liferay.Util.listCheckedExcept(
				document.<portlet:namespace />fm,
				'<portlet:namespace />allRowIds'
			);

			if (
				deleteMBMessageIds &&
				confirm(
					'<%= UnicodeLanguageUtil.get(portletConfig.getResourceBundle(locale), "are-you-sure-you-want-to-delete-the-selected-messages") %>'
				)
			) {
				document.<portlet:namespace />fm.<portlet:namespace />deleteMBMessageIds.value = deleteMBMessageIds;

				if (dicussion) {
					submitForm(
						document.<portlet:namespace />fm,
						'<portlet:actionURL name="deleteDiscussionMBMessages"><portlet:param name="redirect" value="<%= portletURL.toString() %>" /></portlet:actionURL>'
					);
				}
				else {
					submitForm(
						document.<portlet:namespace />fm,
						'<portlet:actionURL name="deleteMBMessages"><portlet:param name="redirect" value="<%= portletURL.toString() %>" /></portlet:actionURL>'
					);
				}
			}
		},
		['liferay-util-list-fields']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />notSpamMBMessages',
		function() {
			var notSpamMBMessageIds = Liferay.Util.listCheckedExcept(
				document.<portlet:namespace />fm,
				'<portlet:namespace />allRowIds'
			);

			if (
				notSpamMBMessageIds &&
				confirm(
					'<%= UnicodeLanguageUtil.get(portletConfig.getResourceBundle(locale), "are-you-sure-you-want-to-mark-the-selected-messages-as-not-spam") %>'
				)
			) {
				document.<portlet:namespace />fm.<portlet:namespace />notSpamMBMessageIds.value = notSpamMBMessageIds;

				submitForm(
					document.<portlet:namespace />fm,
					'<portlet:actionURL name="markNotSpamMBMessages"><portlet:param name="redirect" value="<%= portletURL.toString() %>" /></portlet:actionURL>'
				);
			}
		},
		['liferay-util-list-fields']
	);
</aui:script>