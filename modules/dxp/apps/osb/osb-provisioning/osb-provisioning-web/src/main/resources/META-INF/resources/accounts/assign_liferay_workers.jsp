<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<liferay-util:include page="/common/view_account_search_header.jsp" servletContext="<%= application %>" />

<%
String redirect = ParamUtil.getString(request, "redirect");

ViewAccountLiferayWorkersDisplayContext viewAccountLiferayWorkersDisplayContext = ProvisioningWebComponentProvider.getViewAccountLiferayWorkersDisplayContext(renderRequest, renderResponse, request);

AccountDisplay accountDisplay = viewAccountLiferayWorkersDisplayContext.getAccountDisplay();
%>

<div class="add-items provisioning-accounts">
	<liferay-ui:header
		backURL="<%= redirect %>"
		cssClass="add-items-header"
		title="<%= viewAccountLiferayWorkersDisplayContext.getAssignLiferayWorkerTitle() %>"
	/>

	<liferay-ui:error exception="<%= ContactAlreadyAssignedException.class %>" message="the-contact-is-already-assigned-to-the-account" />
	<liferay-ui:error exception="<%= ContactEmailAddressException.class %>" message="please-enter-a-valid-email-address" />
	<liferay-ui:error exception="<%= ContactNameException.class %>" message="the-contact-could-not-be-found" />
	<liferay-ui:error exception="<%= DuplicateContactRoleException.class %>" message="primary-or-secondary-contact-is-already-assigned-to-another-user" />

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

	<aui:form action="<%= assignContactRolesURL %>" cssClass="container-fluid container-fluid-max-xl" method="post" name="assignLiferayWorkersFm">
		<aui:input name="contactRoleType" type="hidden" value="<%= ContactRole.Type.ACCOUNT_WORKER.toString() %>" />

		<div class="assign-contacts-sheet sheet">
			<react:component
				data="<%= viewAccountLiferayWorkersDisplayContext.getAssignLiferayWorkerData() %>"
				module="js/apps/AccountAddContactsApp"
			/>
		</div>
	</aui:form>
</div>