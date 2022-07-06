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

<liferay-util:include page="/common/view_account_search_header.jsp" servletContext="<%= application %>" />

<%
String redirect = ParamUtil.getString(request, "redirect");

ViewAccountContactsDisplayContext viewAccountContactsDisplayContext = ProvisioningWebComponentProvider.getViewAccountContactsDisplayContext(renderRequest, renderResponse, request);

AccountDisplay accountDisplay = viewAccountContactsDisplayContext.getAccountDisplay();
%>

<div class="add-items provisioning-accounts">
	<liferay-ui:header
		backURL="<%= redirect %>"
		cssClass="add-items-header"
		title="<%= viewAccountContactsDisplayContext.getAssignContactTitle() %>"
	/>

	<liferay-ui:error exception="<%= ContactAlreadyAssignedException.class %>" message="the-contact-is-already-assigned-to-the-account" />
	<liferay-ui:error exception="<%= ContactEmailAddressException.class %>" message="please-enter-a-valid-email-address" />

	<c:if test="<%= SessionErrors.contains(renderRequest, ContactNameException.class.getName()) %>">
		<div class="portlet-msg-info">
			<liferay-ui:message key="the-contact-could-not-be-found.enter-the-contact's-first-and-last-name-to-create-one" />
		</div>
	</c:if>

	<liferay-ui:error exception="<%= NoSuchContactException.class %>" message="the-contact-could-not-be-found.the-user-must-create-his-own-okta-account" />

	<liferay-ui:error exception="<%= Problem.ProblemException.class %>">

		<%
		Problem.ProblemException problemException = (Problem.ProblemException)errorException;
		%>

		<%= problemException.getMessage() %>
	</liferay-ui:error>

	<liferay-ui:error exception="<%= RequiredContactRoleException.class %>" message="cannot-remove-the-last-team-member-with-this-role.assign-this-role-to-another-team-member-then-try-again" />

	<portlet:actionURL name="/accounts/assign_contact_roles" var="assignContactRolesURL">
		<portlet:param name="redirect" value="<%= redirect %>" />
		<portlet:param name="accountKey" value="<%= accountDisplay.getKey() %>" />
	</portlet:actionURL>

	<aui:form action="<%= assignContactRolesURL %>" cssClass="container-fluid container-fluid-max-xl" method="post" name="assignContactFm">
		<aui:input name="contactRoleType" type="hidden" value="<%= ContactRole.Type.ACCOUNT_CUSTOMER.toString() %>" />

		<div class="assign-contacts-sheet sheet">
			<react:component
				data="<%= viewAccountContactsDisplayContext.getAssignContactData() %>"
				module="js/apps/AccountAddContactsApp"
			/>
		</div>
	</aui:form>
</div>