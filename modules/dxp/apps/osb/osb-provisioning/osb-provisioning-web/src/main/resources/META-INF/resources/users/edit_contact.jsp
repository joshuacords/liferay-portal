<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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

			<aui:input checked="<%= koroneikiContact.getEmailAddressVerified() %>" disabled="<%= true %>" name="emailAddressVerified" type="checkbox" />
		</aui:fieldset>
	</aui:fieldset-group>
</div>