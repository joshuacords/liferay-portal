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