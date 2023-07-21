<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceOrganizationDisplayContext commerceOrganizationDisplayContext = (CommerceOrganizationDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

PortletURL portletURL = currentURLObj;

portletURL.setParameter(PortletQName.PUBLIC_RENDER_PARAMETER_NAMESPACE + "backURL", backURL);

Map<String, String> contextParams = new HashMap<>();

contextParams.put("organizationId", String.valueOf(commerceOrganizationDisplayContext.getOrganizationId()));
%>

<commerce-ui:dataset-display
	contextParams="<%= contextParams %>"
	dataProviderKey="<%= CommerceOrganizationUserClayTableDataSetDisplayView.NAME %>"
	id="<%= CommerceOrganizationUserClayTableDataSetDisplayView.NAME %>"
	itemsPerPage="<%= 10 %>"
	namespace="<%= renderResponse.getNamespace() %>"
	pageNumber="<%= 1 %>"
	portletURL="<%= commerceOrganizationDisplayContext.getPortletURL() %>"
	showSearch="<%= false %>"
/>

<c:if test="<%= OrganizationPermissionUtil.contains(permissionChecker, commerceOrganizationDisplayContext.getOrganizationId(), ActionKeys.ASSIGN_MEMBERS) %>">
	<div class="commerce-cta is-visible">
		<aui:button cssClass="btn-lg btn-primary js-invite-user" onClick='<%= renderResponse.getNamespace() + "openUserInvitationModal();" %>' value="invite-user" />
	</div>

	<commerce-ui:user-invitation-modal
		componentId="userInvitationModal"
	/>

	<portlet:actionURL name="inviteUser" var="inviteUserActionURL" />

	<aui:form action="<%= inviteUserActionURL %>" method="post" name="inviteUserFm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.ASSIGN %>" />
		<aui:input name="redirect" type="hidden" value="<%= portletURL %>" />
		<aui:input name="organizationId" type="hidden" value="<%= commerceOrganizationDisplayContext.getOrganizationId() %>" />
		<aui:input name="userId" type="hidden" />
		<aui:input name="userIds" type="hidden" />
		<aui:input name="emailAddresses" type="hidden" />
	</aui:form>

	<aui:script>
		Liferay.provide(
			window,
			'<portlet:namespace />openUserInvitationModal',
			function() {
				var userInvitationModal = Liferay.component('userInvitationModal');
				userInvitationModal.open();
			}
		);

		Liferay.provide(window, 'deleteCommerceOrganizationUser', function(id) {
			document.querySelector('#<portlet:namespace /><%= Constants.CMD %>').value =
				'<%= Constants.REMOVE %>';
			document.querySelector('#<portlet:namespace />userId').value = id;

			submitForm(document.<portlet:namespace />inviteUserFm);
		});

		Liferay.componentReady('userInvitationModal').then(function(
			userInvitationModal
		) {
			userInvitationModal.on('inviteUserToAccount', function(users) {
				var existingUsersIds = users
					.filter(function(el) {
						return el.userId;
					})
					.map(function(usr) {
						return usr.userId;
					})
					.join(',');

				var newUsersEmails = users
					.filter(function(el) {
						return !el.userId;
					})
					.map(function(usr) {
						return usr.email;
					})
					.join(',');

				document.querySelector(
					'#<portlet:namespace />userIds'
				).value = existingUsersIds;

				document.querySelector(
					'#<portlet:namespace />emailAddresses'
				).value = newUsersEmails;

				userInvitationModal.close();

				submitForm(document.<portlet:namespace />inviteUserFm);
			});
		});
	</aui:script>
</c:if>