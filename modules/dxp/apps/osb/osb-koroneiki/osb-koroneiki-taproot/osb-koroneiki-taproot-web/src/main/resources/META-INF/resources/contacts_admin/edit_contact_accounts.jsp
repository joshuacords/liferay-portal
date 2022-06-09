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
Contact koroneikiContact = (Contact)request.getAttribute(TaprootWebKeys.CONTACT);

renderResponse.setTitle(koroneikiContact.getFullName());
%>

<liferay-util:include page="/contacts_admin/edit_contact_tabs.jsp" servletContext="<%= application %>" />

<div class="main-content-body">
	<div class="container-fluid-1280">
		<liferay-ui:search-container
			emptyResultsMessage="no-accounts-were-found"
			headerNames="name,code,description,roles"
			iteratorURL="<%= currentURLObj %>"
			total="<%= AccountLocalServiceUtil.getContactAccountsCount(koroneikiContact.getContactId()) %>"
		>
			<liferay-ui:search-container-results
				results="<%= AccountLocalServiceUtil.getContactAccounts(koroneikiContact.getContactId(), searchContainer.getStart(), searchContainer.getEnd()) %>"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.osb.koroneiki.taproot.model.Account"
				escapedModel="<%= true %>"
				keyProperty="accountId"
				modelVar="curAccount"
			>
				<liferay-portlet:renderURL portletName="<%= TaprootPortletKeys.ACCOUNTS_ADMIN %>" var="rowURL">
					<portlet:param name="mvcRenderCommandName" value="/accounts_admin/edit_account" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="accountId" value="<%= String.valueOf(curAccount.getAccountId()) %>" />
				</liferay-portlet:renderURL>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="name"
				>
					<span class="lfr-portal-tooltip" data-title="<liferay-ui:message key="account" />">
						<aui:icon cssClass="icon-monospaced" image="users" markupView="lexicon" />
					</span>

					<%= curAccount.getName() %>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="code"
					value="<%= curAccount.getCode() %>"
				/>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="description"
					value="<%= curAccount.getDescription() %>"
				/>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="customer-roles"
				>
					<%= ListUtil.toString(koroneikiContact.getContactRoles(curAccount.getAccountId(), com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactRole.Type.ACCOUNT_CUSTOMER.toString()), ContactRole.NAME_ACCESSOR, StringPool.COMMA_AND_SPACE) %>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="worker-roles"
				>
					<%= ListUtil.toString(koroneikiContact.getContactRoles(curAccount.getAccountId(), com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactRole.Type.ACCOUNT_WORKER.toString()), ContactRole.NAME_ACCESSOR, StringPool.COMMA_AND_SPACE) %>
				</liferay-ui:search-container-column-text>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator
				markupView="lexicon"
			/>
		</liferay-ui:search-container>
	</div>
</div>