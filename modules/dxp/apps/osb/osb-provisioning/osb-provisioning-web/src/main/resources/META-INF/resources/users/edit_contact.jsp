<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
Contact koroneikiContact = (Contact)renderRequest.getAttribute(ProvisioningWebKeys.CONTACT);
%>

<liferay-ui:error exception="<%= Problem.ProblemException.class %>">

	<%
	Problem.ProblemException problemException = (Problem.ProblemException)errorException;
	%>

	<%= problemException.getMessage() %>
</liferay-ui:error>

<portlet:actionURL name="/users/edit_contact" var="editContactURL">
	<portlet:param name="redirect" value="<%= currentURL %>" />
	<portlet:param name="emailAddress" value="<%= koroneikiContact.getEmailAddress() %>" />
</portlet:actionURL>

<div class="container-fluid-1280">
	<aui:fieldset-group>
		<aui:fieldset>
			<aui:input name="key" type="resource" value="<%= koroneikiContact.getKey() %>" />

			<aui:input name="emailAddress" type="resource" value="<%= koroneikiContact.getEmailAddress() %>" />

			<aui:input name="languageId" type="resource" value="<%= koroneikiContact.getLanguageId() %>" />

			<aui:input name="uuid" type="resource" value="<%= koroneikiContact.getUuid() %>" />

			<aui:input name="firstName" type="resource" value="<%= koroneikiContact.getFirstName() %>" />

			<aui:input name="middleName" type="resource" value="<%= koroneikiContact.getMiddleName() %>" />

			<aui:input name="lastName" type="resource" value="<%= koroneikiContact.getLastName() %>" />
		</aui:fieldset>
	</aui:fieldset-group>
</div>