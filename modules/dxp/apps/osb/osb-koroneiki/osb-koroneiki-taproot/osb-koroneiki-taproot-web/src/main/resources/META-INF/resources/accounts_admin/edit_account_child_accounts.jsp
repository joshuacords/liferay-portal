<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
Account koroneikiAccount = (Account)request.getAttribute(TaprootWebKeys.ACCOUNT);

renderResponse.setTitle(koroneikiAccount.getName());
%>

<liferay-util:include page="/accounts_admin/edit_account_tabs.jsp" servletContext="<%= application %>" />

<div class="main-content-body">

	<%
	List<Account> accounts = koroneikiAccount.getChildAccounts();
	%>

	<div class="container-fluid-1280">
		<liferay-ui:search-container
			emptyResultsMessage="no-child-accounts-were-found"
			headerNames="name,code,status"
			iteratorURL="<%= currentURLObj %>"
			total="<%= accounts.size() %>"
		>
			<liferay-ui:search-container-results
				results="<%= accounts %>"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.osb.koroneiki.taproot.model.Account"
				escapedModel="<%= true %>"
				keyProperty="accountId"
				modelVar="curAccount"
			>
				<portlet:renderURL var="rowURL">
					<portlet:param name="mvcRenderCommandName" value="/accounts_admin/edit_account" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="accountId" value="<%= String.valueOf(curAccount.getAccountId()) %>" />
				</portlet:renderURL>

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
					name="status"
					value="<%= curAccount.getStatus() %>"
				/>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator
				markupView="lexicon"
				paginate="<%= false %>"
			/>
		</liferay-ui:search-container>
	</div>
</div>