<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ViewAccountContactsDisplayContext viewAccountContactsDisplayContext = ProvisioningWebComponentProvider.getViewAccountContactsDisplayContext(renderRequest, renderResponse, request);
%>

<div class="details-table table-striped">
	<liferay-ui:error exception="<%= ContactRequiredException.class %>" message="please-reassign-all-of-the-contacts-zendesk-tickets-before-unassigning" />
	<liferay-ui:error exception="<%= RequiredContactRoleException.class %>" message="cannot-remove-the-last-team-member-with-this-role.assign-this-role-to-another-team-member-then-try-again" />

	<liferay-ui:search-container
		id="contacts"
		searchContainer="<%= viewAccountContactsDisplayContext.getSearchContainer() %>"
	>
		<clay:management-toolbar
			clearResultsURL="<%= viewAccountContactsDisplayContext.getClearResultsURL() %>"
			creationMenu="<%= viewAccountContactsDisplayContext.getCreationMenu() %>"
			elementClasses="full-width"
			filterDropdownItems="<%= viewAccountContactsDisplayContext.getFilterCustomerRoleDropdownItems() %>"
			filterLabelItems="<%= viewAccountContactsDisplayContext.getFilterCustomerRoleLabelItems() %>"
			itemsTotal="<%= searchContainer.getTotal() %>"
			searchActionURL="<%= viewAccountContactsDisplayContext.getCurrentURL() %>"
			searchContainerId="contacts"
			selectable="<%= false %>"
			showSearch="<%= true %>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.osb.provisioning.web.internal.display.context.ContactDisplay"
			modelVar="contactDisplay"
		>
			<liferay-portlet:renderURL portletName="<%= ProvisioningPortletKeys.USERS %>" var="rowURL">
				<portlet:param name="mvcRenderCommandName" value="/users/view_contact" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="contactEmailAddress" value="<%= contactDisplay.getEmailAddress() %>" />
			</liferay-portlet:renderURL>

			<liferay-ui:search-container-column-text
				name="name-email"
			>
				<a href="<%= rowURL %>"><%= HtmlUtil.escape(contactDisplay.getFullName()) %></a>

				<div class="secondary-information">
					<span><%= contactDisplay.getEmailAddress() %></span>

					<button class="btn btn-unstyled copy-btn">
						<liferay-ui:icon
							icon="paste"
							markupView="lexicon"
							message="copy"
						/>
					</button>
				</div>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="role"
			>
				<div class="card-row">
					<span class="autofit-col">
						<%= StringUtil.merge(contactDisplay.getContactRoleNames(), "<br />") %>
					</span>

					<button class="autofit-col btn btn-unstyled copy-btn">
						<liferay-ui:icon
							icon="paste"
							markupView="lexicon"
							message="copy"
						/>
					</button>
				</div>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="user-status"
			>
				<span class="label <%= contactDisplay.getStatusStyle() %>"><%= contactDisplay.getStatus() %></span>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-jsp
				align="right"
				path="/accounts/account_customer_contact_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>

<aui:script>
	var copyBtn = document.querySelectorAll('.copy-btn');

	copyBtn.forEach(function(btn) {
		btn.addEventListener('click', function() {
			var target = btn.previousElementSibling;

			if (target) {
				navigator.clipboard.writeText(target.innerText);
			}
		});
	});
</aui:script>