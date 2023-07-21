<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
portletDisplay.setShowBackIcon(true);

LiferayPortletURL usersAdminURL = liferayPortletResponse.createLiferayPortletURL(UsersAdminPortletKeys.USERS_ADMIN, PortletRequest.RENDER_PHASE);

portletDisplay.setURLBack(usersAdminURL.toString());

renderResponse.setTitle(StringBundler.concat(selectedUser.getFullName(), " - ", LanguageUtil.get(request, "personal-data-erasure")));
%>

<div class="container-fluid container-fluid-max-xl container-form-lg">
	<liferay-ui:empty-result-message
		message="you-have-successfully-anonymized-all-remaining-data"
	/>
</div>

<portlet:actionURL name="/delete_user" var="deleteUserURL">
	<portlet:param name="p_u_i_d" value="<%= String.valueOf(selectedUser.getUserId()) %>" />
</portlet:actionURL>

<aui:script>
	if (
		confirm(
			'<%= UnicodeLanguageUtil.get(request, "all-personal-data-associated-with-this-users-applications-has-been-deleted-or-anonymized") %>'
		)
	) {
		Liferay.Util.navigate('<%= deleteUserURL.toString() %>');
	}
</aui:script>