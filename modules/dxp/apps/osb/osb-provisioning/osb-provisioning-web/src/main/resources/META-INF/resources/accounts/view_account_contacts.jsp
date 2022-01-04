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
ViewAccountContactsDisplayContext viewAccountContactsDisplayContext = ProvisioningWebComponentProvider.getViewAccountContactsDisplayContext(renderRequest, renderResponse, request);
%>

<div class="details-table table-striped">
	<liferay-ui:error exception="<%= ContactRequiredException.class %>" message="please-reassign-all-of-the-contacts-zendesk-tickets-before-unassigning" />
	<liferay-ui:error exception="<%= NoSuchContactException.class %>" message="contact-does-not-exist" />

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