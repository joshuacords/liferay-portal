<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceAccountDisplayContext commerceAccountDisplayContext = (CommerceAccountDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

request.setAttribute("view.jsp-filterPerAccount", false);
%>

<liferay-ui:error exception="<%= UserEmailAddressException.MustValidate.class %>" message="please-enter-a-valid-email-address" />

<div class="commerce-account-container" id="<portlet:namespace />entriesContainer">
	<commerce-ui:dataset-display
		dataProviderKey="<%= CommerceAccountClayDataSetDataSetDisplayView.NAME %>"
		id="<%= CommerceAccountClayDataSetDataSetDisplayView.NAME %>"
		itemsPerPage="<%= 10 %>"
		namespace="<%= renderResponse.getNamespace() %>"
		pageNumber="<%= 1 %>"
		portletURL="<%= commerceAccountDisplayContext.getPortletURL() %>"
		style="stacked"
	/>
</div>

<c:if test="<%= commerceAccountDisplayContext.hasAddAccountPermissions() %>">
	<div class="commerce-cta is-visible">
		<aui:button cssClass="btn-lg btn-primary js-invite-user" onClick='<%= renderResponse.getNamespace() + "openAddAccountModal();" %>' value="add-account" />
	</div>

	<portlet:actionURL name="editCommerceAccount" var="editCommerceAccountActionURL" />

	<aui:form action="<%= editCommerceAccountActionURL %>" method="post" name="commerceAccountFm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.ADD %>" />
		<aui:input name="active" type="hidden" value="<%= true %>" />
		<aui:input name="emailAddresses" type="hidden" />
		<aui:input name="name" type="hidden" />
		<aui:input name="userIds" type="hidden" />
		<aui:input name="commerceAccountId" type="hidden" />
	</aui:form>

	<commerce-ui:add-account-modal
		componentId="addAccountModal"
	/>

	<aui:script>
		Liferay.provide(window, '<portlet:namespace />openAddAccountModal', function(
			evt
		) {
			var addAccountModal = Liferay.component('addAccountModal');

			addAccountModal.open();
		});

		Liferay.provide(window, 'setCurrentAccount', function(id) {
			document.querySelector('#<portlet:namespace /><%= Constants.CMD %>').value =
				'setCurrentAccount';
			document.querySelector(
				'#<portlet:namespace />commerceAccountId'
			).value = id;

			submitForm(document.<portlet:namespace />commerceAccountFm);
		});

		Liferay.provide(window, 'toggleActiveCommerceAccount', function(id) {
			document.querySelector('#<portlet:namespace /><%= Constants.CMD %>').value =
				'setActive';
			document.querySelector(
				'#<portlet:namespace />commerceAccountId'
			).value = id;

			submitForm(document.<portlet:namespace />commerceAccountFm);
		});

		Liferay.componentReady('addAccountModal').then(function(addAccountModal) {
			addAccountModal.on('AddAccountModalSave', function(event) {
				var existingUserIds = event.administratorsEmail
					.filter(function(el) {
						return el.userId;
					})
					.map(function(usr) {
						return usr.userId;
					})
					.join(',');

				var newUserEmails = event.administratorsEmail
					.filter(function(el) {
						return !el.userId;
					})
					.map(function(usr) {
						return usr.email;
					})
					.join(',');

				document.querySelector(
					'#<portlet:namespace />emailAddresses'
				).value = newUserEmails;
				document.querySelector('#<portlet:namespace />name').value =
					event.accountName;
				document.querySelector(
					'#<portlet:namespace />userIds'
				).value = existingUserIds;

				addAccountModal.close();

				submitForm(document.<portlet:namespace />commerceAccountFm);
			});
		});
	</aui:script>
</c:if>