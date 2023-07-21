<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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