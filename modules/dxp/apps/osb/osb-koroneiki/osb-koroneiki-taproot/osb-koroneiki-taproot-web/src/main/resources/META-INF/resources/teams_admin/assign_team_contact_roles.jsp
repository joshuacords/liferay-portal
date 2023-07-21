<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

Team team = (Team)request.getAttribute(TaprootWebKeys.TEAM);
Contact koroneikiContact = (Contact)request.getAttribute(TaprootWebKeys.CONTACT);

renderResponse.setTitle(team.getName());
%>

<portlet:actionURL name="/teams_admin/assign_team_contact_roles" var="assignTeamContactRolesURL" />

<div class="main-content-body">
	<aui:form action="<%= assignTeamContactRolesURL %>" cssClass="container-fluid-1280" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "submitForm();" %>'>
		<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
		<aui:input name="teamId" type="hidden" value="<%= team.getTeamId() %>" />
		<aui:input name="contactId" type="hidden" value="<%= koroneikiContact.getContactId() %>" />
		<aui:input name="addContactRoleIds" type="hidden" />
		<aui:input name="deleteContactRoleIds" type="hidden" />

		<h2><liferay-ui:message arguments="<%= HtmlUtil.escape(koroneikiContact.getFullName()) %>" key="assign-contact-roles-for-x" /></h2>

		<%
		List<ContactRole> contactRoles = ContactRoleLocalServiceUtil.getContactTeamContactRoles(team.getTeamId(), koroneikiContact.getContactId(), new String[] {com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactRole.Type.TEAM.toString()}, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		%>

		<liferay-ui:search-container
			emptyResultsMessage="no-contact-roles-were-found"
			headerNames="name,description"
			iteratorURL="<%= currentURLObj %>"
			total="<%= ContactRoleLocalServiceUtil.getContactRolesCount(com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactRole.Type.TEAM.toString()) %>"
		>
			<liferay-ui:search-container-results
				results="<%= ContactRoleLocalServiceUtil.getContactRoles(com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactRole.Type.TEAM.toString(), searchContainer.getStart(), searchContainer.getEnd()) %>"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.osb.koroneiki.taproot.model.ContactRole"
				escapedModel="<%= true %>"
				keyProperty="contactRoleId"
				modelVar="contactRole"
			>
				<liferay-ui:search-container-column-text>
					<aui:input checked="<%= contactRoles.contains(contactRole) %>" label="" name="contactRoleIds" type="checkbox" value="<%= contactRole.getContactRoleId() %>" />
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					name="name"
					value="<%= contactRole.getName() %>"
				/>

				<liferay-ui:search-container-column-text
					name="description"
					value="<%= contactRole.getDescription() %>"
				/>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator
				markupView="lexicon"
			/>
		</liferay-ui:search-container>

		<aui:button-row>
			<aui:button type="submit" />

			<aui:button href="<%= redirect %>" type="cancel" />
		</aui:button-row>
	</aui:form>
</div>

<aui:script>
	function <portlet:namespace />submitForm() {
		var form = document.getElementById('<portlet:namespace />fm');

		var addContactRoleIdsInput = form.querySelector('#<portlet:namespace />addContactRoleIds');
		var deleteContactRoleIdsInput = form.querySelector('#<portlet:namespace />deleteContactRoleIds');

		addContactRoleIdsInput.setAttribute('value', Liferay.Util.listCheckedExcept(form));
		deleteContactRoleIdsInput.setAttribute('value', Liferay.Util.listUncheckedExcept(form));

		form.submit();
	}
</aui:script>