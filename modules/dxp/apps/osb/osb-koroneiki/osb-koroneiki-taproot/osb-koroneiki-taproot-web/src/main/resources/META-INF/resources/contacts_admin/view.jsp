<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ContactsDisplayContext contactsDisplayContext = new ContactsDisplayContext(renderRequest, renderResponse, request);

ViewContactsManagementToolbarDisplayContext viewContactsManagementToolbarDisplayContext = new ViewContactsManagementToolbarDisplayContext(liferayPortletRequest, liferayPortletResponse, request, contactsDisplayContext.getSearchContainer());
%>

<clay:navigation-bar
	inverted="<%= true %>"
	navigationItems="<%= viewContactsManagementToolbarDisplayContext.getNavigationItems() %>"
/>

<clay:management-toolbar
	displayContext="<%= viewContactsManagementToolbarDisplayContext %>"
/>

<div class="container-fluid-1280">
	<liferay-ui:search-container
		searchContainer="<%= contactsDisplayContext.getSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.osb.koroneiki.taproot.model.Contact"
			escapedModel="<%= true %>"
			keyProperty="contactId"
			modelVar="koroneikiContact"
		>
			<portlet:renderURL var="rowURL">
				<portlet:param name="mvcRenderCommandName" value="/contacts_admin/edit_contact" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="contactId" value="<%= String.valueOf(koroneikiContact.getContactId()) %>" />
			</portlet:renderURL>

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
				name="language"
				value="<%= koroneikiContact.getLanguageId() %>"
			/>

			<liferay-ui:search-container-column-jsp
				align="right"
				path="/contacts_admin/contact_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>