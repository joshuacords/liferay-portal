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

	<liferay-ui:error exception="<%= Problem.ProblemException.class %>">

		<%
		Problem.ProblemException problemException = (Problem.ProblemException)errorException;
		%>

		<%= problemException.getMessage() %>
	</liferay-ui:error>

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