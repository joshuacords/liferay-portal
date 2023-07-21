<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
long accountId = ParamUtil.getLong(request, "accountId");

AccountsDisplayContext accountsDisplayContext = new AccountsDisplayContext(renderRequest, renderResponse, request);
%>

<clay:management-toolbar
	displayContext="<%= new ViewAccountsManagementToolbarDisplayContext(liferayPortletRequest, liferayPortletResponse, request, accountsDisplayContext.getSearchContainer()) %>"
/>

<aui:form cssClass="container-fluid-1280" name="selectAccountFm">
	<liferay-ui:search-container
		searchContainer="<%= accountsDisplayContext.getSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.osb.koroneiki.taproot.model.Account"
			escapedModel="<%= true %>"
			keyProperty="accountId"
			modelVar="koroneikiAccount"
		>
			<liferay-ui:search-container-column-text
				name="name"
			>
				<span class="lfr-portal-tooltip" data-title="<liferay-ui:message key="account" />">
					<aui:icon cssClass="icon-monospaced" image="users" markupView="lexicon" />
				</span>

				<%= koroneikiAccount.getName() %>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="code"
				value="<%= koroneikiAccount.getCode() %>"
			/>

			<liferay-ui:search-container-column-text
				name="description"
				value="<%= koroneikiAccount.getDescription() %>"
			/>

			<liferay-ui:search-container-column-text
				name="status"
				value="<%= koroneikiAccount.getStatus() %>"
			/>

			<liferay-ui:search-container-column-text>
				<c:if test="<%= accountId != koroneikiAccount.getAccountId() %>">

					<%
					Map<String, Object> data = new HashMap<String, Object>();

					data.put("accountid", koroneikiAccount.getAccountId());
					data.put("accountname", koroneikiAccount.getName());
					%>

					<aui:button cssClass="selector-button" data="<%= data %>" value="select" />
				</c:if>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</aui:form>

<aui:script>
	Liferay.Util.selectEntityHandler('#<portlet:namespace />selectAccountFm', 'selectAccount');
</aui:script>