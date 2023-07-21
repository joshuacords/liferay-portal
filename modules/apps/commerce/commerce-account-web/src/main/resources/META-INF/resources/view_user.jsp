<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceAccountDisplayContext commerceAccountDisplayContext = (CommerceAccountDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceAccount commerceAccount = commerceAccountDisplayContext.getCurrentCommerceAccount();
User selectedUser = commerceAccountDisplayContext.getSelectedUser();
PortletURL portletURL = commerceAccountDisplayContext.getPortletURL();

Map<String, String> contextParams = new HashMap<>();

contextParams.put("commerceAccountId", String.valueOf(commerceAccountDisplayContext.getCurrentCommerceAccountId()));
contextParams.put("userId", String.valueOf(selectedUser.getUserId()));

portletURL.setParameter("mvcRenderCommandName", "viewCommerceAccountUser");
portletURL.setParameter("userId", String.valueOf(selectedUser.getUserId()));
%>

<portlet:renderURL var="editCommerceAccountURL">
	<portlet:param name="mvcRenderCommandName" value="editCommerceAccountUser" />
	<portlet:param name="commerceAccountId" value="<%= (commerceAccount != null) ? String.valueOf(commerceAccount.getCommerceAccountId()) : StringPool.BLANK %>" />
	<portlet:param name="userId" value="<%= String.valueOf(selectedUser.getUserId()) %>" />
	<portlet:param name='<%= PortletQName.PUBLIC_RENDER_PARAMETER_NAMESPACE + "backURL" %>' value="<%= portletURL.toString() %>" />
</portlet:renderURL>

<div class="account-management">
	<section class="panel panel-secondary">
		<div class="panel-body">
			<div class="row">
				<div class="col-auto">
					<img alt="avatar" class="account-management__thumbnail img-fluid rounded-circle" src="<%= selectedUser.getPortraitURL(themeDisplay) %>" />
				</div>

				<div class="col d-flex flex-col justify-content-center">
					<span class="account-management__name">
						<%= selectedUser.getFullName() %>
					</span>
					<span class="account-management__email">
						<%= selectedUser.getEmailAddress() %>
					</span>
				</div>

				<div class="align-items-center col-auto d-flex">
					<div class="account-management__action">
						<c:if test="<%= (selectedUser.getUserId() == user.getUserId()) || ((commerceAccount != null) && commerceAccountDisplayContext.hasCommerceAccountModelPermissions(commerceAccount.getCommerceAccountId(), CommerceAccountActionKeys.MANAGE_MEMBERS)) %>">
							<aui:button cssClass="btn btn-lg btn-secondary" href="<%= editCommerceAccountURL %>" value='<%= LanguageUtil.get(request, "edit-user") %>' />
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</section>
</div>

<c:if test="<%= commerceAccount != null %>">
	<div class="commerce-cta is-visible">
		<c:if test="<%= (selectedUser.getUserId() != user.getUserId()) && commerceAccountDisplayContext.hasCommerceAccountModelPermissions(commerceAccount.getCommerceAccountId(), CommerceAccountActionKeys.MANAGE_MEMBERS) %>">
			<aui:button cssClass="btn-lg btn-primary js-invite-user" onClick='<%= renderResponse.getNamespace() + "openUserRolesModal();" %>' value="roles" />
		</c:if>
	</div>

	<div class="commerce-account-container">
		<commerce-ui:dataset-display
			contextParams="<%= contextParams %>"
			dataProviderKey="<%= CommerceAccountUserRolesClayTableDataSetDisplayView.NAME %>"
			id="<%= CommerceAccountUserRolesClayTableDataSetDisplayView.NAME %>"
			itemsPerPage="<%= 10 %>"
			namespace="<%= renderResponse.getNamespace() %>"
			pageNumber="<%= 1 %>"
			portletURL="<%= portletURL %>"
			style="stacked"
		/>
	</div>

	<portlet:actionURL name="editCommerceAccountUser" var="editCommerceAccountUserURL" />

	<aui:form action="<%= editCommerceAccountUserURL %>" method="post" name="editCommerceAccountUserFm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" value="EDIT_ROLES" />
		<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
		<aui:input name="commerceAccountId" type="hidden" value="<%= commerceAccount.getCommerceAccountId() %>" />
		<aui:input name="userId" type="hidden" value="<%= selectedUser.getUserId() %>" />
		<aui:input name="selectedRoleIds" type="hidden" />
	</aui:form>

	<c:if test="<%= (selectedUser.getUserId() != user.getUserId()) && commerceAccountDisplayContext.hasCommerceAccountModelPermissions(ActionKeys.UPDATE) %>">
		<commerce-ui:user-roles-modal
			commerceAccountId="<%= commerceAccount.getCommerceAccountId() %>"
			componentId="userRolesModal"
			userId="<%= selectedUser.getUserId() %>"
		/>

		<aui:script>
			Liferay.provide(window, '<portlet:namespace />openUserRolesModal', function(
				evt
			) {
				var userRolesModal = Liferay.component('userRolesModal');
				userRolesModal.open();
			});

			Liferay.componentReady('userRolesModal').then(function(userRolesModal) {
				userRolesModal.on('updateRoles', function(selectedRoles) {
					var selectedRoleIds = selectedRoles
						.map(function(role) {
							return role.id;
						})
						.join(',');

					document.querySelector(
						'#<portlet:namespace />selectedRoleIds'
					).value = selectedRoleIds;

					userRolesModal.close();

					submitForm(document.<portlet:namespace />editCommerceAccountUserFm);
				});
			});
		</aui:script>
	</c:if>
</c:if>