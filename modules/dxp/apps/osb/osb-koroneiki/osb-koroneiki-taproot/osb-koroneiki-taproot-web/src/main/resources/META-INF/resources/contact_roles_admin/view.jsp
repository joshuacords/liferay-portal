<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ContactRolesDisplayContext contactRolesDisplayContext = new ContactRolesDisplayContext(renderRequest, renderResponse, request);

ViewContactRolesManagementToolbarDisplayContext viewContactRolesManagementToolbarDisplayContext = new ViewContactRolesManagementToolbarDisplayContext(liferayPortletRequest, liferayPortletResponse, request, contactRolesDisplayContext.getSearchContainer());
%>

<clay:navigation-bar
	inverted="<%= true %>"
	navigationItems="<%= viewContactRolesManagementToolbarDisplayContext.getNavigationItems() %>"
/>

<clay:management-toolbar
	displayContext="<%= viewContactRolesManagementToolbarDisplayContext %>"
/>

<liferay-ui:error exception="<%= RequiredContactRoleException.MustNotDeleteContactRoleReferencedByContact.class %>" message="the-contact-role-cannot-be-deleted-because-it-is-assigned-to-one-or-more-contacts" />

<div class="container-fluid-1280">
	<liferay-ui:search-container
		searchContainer="<%= contactRolesDisplayContext.getSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.osb.koroneiki.taproot.model.ContactRole"
			escapedModel="<%= true %>"
			keyProperty="contactRoleId"
			modelVar="contactRole"
		>
			<portlet:renderURL var="rowURL">
				<portlet:param name="mvcRenderCommandName" value="/contact_roles_admin/edit_contact_role" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="contactRoleId" value="<%= String.valueOf(contactRole.getContactRoleId()) %>" />
			</portlet:renderURL>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="name"
				value="<%= contactRole.getName() %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="description"
				value="<%= contactRole.getDescription() %>"
			/>

			<liferay-ui:search-container-column-jsp
				align="right"
				path="/contact_roles_admin/contact_role_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>