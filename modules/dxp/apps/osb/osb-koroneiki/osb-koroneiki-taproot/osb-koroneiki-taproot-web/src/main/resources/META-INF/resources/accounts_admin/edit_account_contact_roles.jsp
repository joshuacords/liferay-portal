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
Account koroneikiAccount = (Account)request.getAttribute(TaprootWebKeys.ACCOUNT);
String contactRoleType = (String)request.getAttribute(TaprootWebKeys.CONTACT_ROLE_TYPE);

renderResponse.setTitle(koroneikiAccount.getName());
%>

<liferay-util:include page="/accounts_admin/edit_account_tabs.jsp" servletContext="<%= application %>" />

<clay:management-toolbar
	componentId="contactsManagementToolbar"
	creationMenu='<%=
		new JSPCreationMenu(pageContext) {
			{
				addDropdownItem(
					dropdownItem -> {
						dropdownItem.setHref(renderResponse.createRenderURL(), "mvcRenderCommandName", "/accounts_admin/assign_account_contact", "redirect", PortalUtil.getCurrentURL(request), "accountId", koroneikiAccount.getAccountId(), "contactRoleType", contactRoleType);
						dropdownItem.setLabel(LanguageUtil.get(request, "assign-contact"));
					});
			}
		}
	%>'
	selectable="<%= false %>"
	showCreationMenu="<%= true %>"
	showSearch="<%= false %>"
/>

<div class="container-fluid-1280">
	<liferay-ui:search-container
		emptyResultsMessage="no-contacts-were-found"
		headerNames="first-name,last-name,email-address"
		iteratorURL="<%= currentURLObj %>"
		total="<%= ContactLocalServiceUtil.getAccountContactsCount(koroneikiAccount.getAccountId(), contactRoleType) %>"
	>
		<liferay-ui:search-container-results
			results="<%= ContactLocalServiceUtil.getAccountContacts(koroneikiAccount.getAccountId(), contactRoleType, searchContainer.getStart(), searchContainer.getEnd()) %>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.osb.koroneiki.taproot.model.Contact"
			escapedModel="<%= true %>"
			keyProperty="contactId"
			modelVar="koroneikiContact"
		>
			<liferay-portlet:renderURL portletName="<%= TaprootPortletKeys.CONTACTS_ADMIN %>" var="rowURL">
				<portlet:param name="mvcRenderCommandName" value="/contacts_admin/edit_contact" />
				<portlet:param name="contactId" value="<%= String.valueOf(koroneikiContact.getContactId()) %>" />
			</liferay-portlet:renderURL>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="name"
			>
				<span class="lfr-portal-tooltip" data-title="<liferay-ui:message key="user" />">
					<aui:icon cssClass="icon-monospaced" image="user" markupView="lexicon" />
				</span>

				<%= HtmlUtil.escape(koroneikiContact.getFullName()) %>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="email-address"
				value="<%= koroneikiContact.getEmailAddress() %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="roles"
			>
				<%= ListUtil.toString(koroneikiContact.getContactRoles(koroneikiAccount.getAccountId(), contactRoleType), ContactRole.NAME_ACCESSOR, StringPool.COMMA_AND_SPACE) %>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-jsp
				align="right"
				path="/accounts_admin/account_contact_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>

<aui:script require="metal-dom/src/all/dom as dom,frontend-js-web/liferay/modal/commands/OpenSimpleInputModal.es as modalCommands">
	function handleAddClick(event) {
		event.preventDefault();

		<portlet:actionURL name="/accounts_admin/assign_account_contact" var="assignAccountContactURL">
			<portlet:param name="accountId" value="<%= String.valueOf(koroneikiAccount.getAccountId()) %>" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
		</portlet:actionURL>

		modalCommands.openSimpleInputModal(
			{
				dialogTitle: '<liferay-ui:message key="assign-contact-to-this-account" />',
				formSubmitURL: '<%= assignAccountContactURL %>',
				mainFieldLabel: '<liferay-ui:message key="email-address" />',
				mainFieldName: 'emailAddress',
				mainFieldPlaceholder: '<liferay-ui:message key="email-address" />',
				namespace: '<portlet:namespace />',
				spritemap: '<%= themeDisplay.getPathThemeImages() %>/lexicon/icons.svg'
			}
		);
	}

	Liferay.componentReady('contactsManagementToolbar').then(
		function(managementToolbar) {
			managementToolbar.on('creationButtonClicked', handleAddClick);
		}
	);
</aui:script>