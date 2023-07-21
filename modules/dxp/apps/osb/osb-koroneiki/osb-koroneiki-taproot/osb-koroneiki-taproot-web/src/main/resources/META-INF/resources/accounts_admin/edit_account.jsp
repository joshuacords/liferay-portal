<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

Account koroneikiAccount = (Account)request.getAttribute(TaprootWebKeys.ACCOUNT);

long accountId = BeanParamUtil.getLong(koroneikiAccount, request, "accountId");

renderResponse.setTitle((koroneikiAccount == null) ? LanguageUtil.get(request, "new-account") : koroneikiAccount.getName());
%>

<liferay-util:include page="/accounts_admin/edit_account_tabs.jsp" servletContext="<%= application %>" />

<portlet:actionURL name="/accounts_admin/edit_account" var="editAccountURL" />

<aui:form action="<%= editAccountURL %>" cssClass="container-fluid-1280" method="post" name="fm">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="accountId" type="hidden" value="<%= accountId %>" />

	<liferay-ui:error exception="<%= AccountCodeException.MustNotBeDuplicate.class %>" message="please-enter-a-valid-code" />
	<liferay-ui:error exception="<%= AccountNameException.class %>" message="please-enter-a-valid-name" />
	<liferay-ui:error exception="<%= AccountParentException.MustNotBeDescendant.class %>" message="please-select-a-valid-parent" />
	<liferay-ui:error exception="<%= AccountParentException.MustNotBeSelf.class %>" message="please-select-a-valid-parent" />

	<aui:model-context bean="<%= koroneikiAccount %>" model="<%= Account.class %>" />

	<aui:fieldset-group>
		<aui:fieldset>
			<c:if test="<%= koroneikiAccount != null %>">
				<aui:input label="key" name="key" type="resource" value="<%= koroneikiAccount.getAccountKey() %>" />
			</c:if>

			<h5><liferay-ui:message key="parent-account" /></h5>

			<p>

				<%
				Account parentAccount = null;

				if (koroneikiAccount != null) {
					parentAccount = koroneikiAccount.getParentAccount();
				}
				%>

				<aui:input name="parentAccountId" type="hidden" value='<%= (parentAccount != null) ? parentAccount.getAccountId() : "" %>' />

				<span id="<portlet:namespace />parentAccountName">
					<c:if test="<%= parentAccount != null %>">
						<liferay-portlet:renderURL var="parentAccountURL">
							<portlet:param name="mvcRenderCommandName" value="/accounts_admin/edit_account" />
							<portlet:param name="accountId" value="<%= String.valueOf(parentAccount.getAccountId()) %>" />
						</liferay-portlet:renderURL>

						<a href="<%= parentAccountURL %>"><%= HtmlUtil.escape(parentAccount.getName()) %></a>
					</c:if>
				</span>

				<aui:button onClick='<%= renderResponse.getNamespace() + "openAccountSelector();" %>' value="select" />

				<aui:button onClick='<%= renderResponse.getNamespace() + "removeParentAccount();" %>' value="remove" />
			</p>

			<aui:input name="name" />

			<aui:input name="code" />

			<aui:input name="description" type="textarea" />

			<aui:input name="contactEmailAddress" />

			<aui:input name="profileEmailAddress" />

			<aui:input name="phoneNumber" />

			<aui:input name="faxNumber" />

			<aui:input name="website" />

			<aui:select name="tier">
				<aui:option value="" />

				<%
				for (com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Account.Tier tier : com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Account.Tier.values()) {
				%>

					<aui:option label="<%= tier %>" value="<%= tier %>" />

				<%
				}
				%>

			</aui:select>

			<aui:select name="region">
				<aui:option value="" />

				<%
				for (com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Account.Region region : com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Account.Region.values()) {
				%>

					<aui:option label="<%= region %>" value="<%= region %>" />

				<%
				}
				%>

			</aui:select>

			<aui:select name="dataRegion">
				<aui:option value="" />

				<%
				for (com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Account.DataRegion dataRegion : com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Account.DataRegion.values()) {
				%>

					<aui:option label="<%= dataRegion %>" value="<%= dataRegion %>" />

				<%
				}
				%>

			</aui:select>

			<aui:select name="language">
				<aui:option value="" />

				<%
				for (com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Account.Language language : com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Account.Language.values()) {
				%>

					<aui:option label="<%= language %>" value="<%= language %>" />

				<%
				}
				%>

			</aui:select>

			<aui:input checked="<%= (koroneikiAccount != null) && koroneikiAccount.isInternal() %>" name="internal" type="checkbox" />

			<aui:select name="status">

				<%
				for (com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Account.Status status : com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Account.Status.values()) {
				%>

					<aui:option label="<%= status %>" value="<%= status %>" />

				<%
				}
				%>

			</aui:select>
		</aui:fieldset>

		<div class="form-group">
			<h3 class="sheet-subtitle"><liferay-ui:message key="account-fields" /></h3>

			<aui:fieldset id='<%= renderResponse.getNamespace() + "accountFields" %>'>

				<%
				List<AccountField> accountFields = new ArrayList<>();

				if (koroneikiAccount != null) {
					accountFields.addAll(koroneikiAccount.getAccountFields());
				}

				if (accountFields.isEmpty()) {
					accountFields.add(AccountFieldLocalServiceUtil.createAccountField(0));
				}

				int[] accountFieldIndexes = new int[accountFields.size()];

				for (int i = 0; i < accountFields.size(); i++) {
					AccountField accountField = accountFields.get(i);

					accountFieldIndexes[i] = i;
				%>

					<div class="lfr-form-row lfr-form-row-inline">
						<div class="row-fields">
							<aui:row>
								<aui:col md="5">
									<aui:input label="name" name='<%= "accountFieldName_" + i %>' type="text" value="<%= accountField.getName() %>" />
								</aui:col>

								<aui:col md="5">
									<aui:input label="value" name='<%= "accountFieldValue_" + i %>' type="text" value="<%= accountField.getValue() %>" />
								</aui:col>
							</aui:row>
						</div>
					</div>

				<%
				}
				%>

				<aui:input name="accountFieldIndexes" type="hidden" value="<%= StringUtil.merge(accountFieldIndexes) %>" />
			</aui:fieldset>
		</div>
	</aui:fieldset-group>

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>

<aui:script use="aui-base,liferay-auto-fields">
	var autoFields = new Liferay.AutoFields(
		{
			contentBox: 'fieldset#<portlet:namespace />accountFields',
			fieldIndexes: '<portlet:namespace />accountFieldIndexes',
			namespace: '<portlet:namespace />'
		}
	).render();

	<portlet:namespace />openAccountSelector = function() {
		Liferay.Util.selectEntity(
			{
				dialog: {
					constrain: true,
					modal: true
				},
				eventName: 'selectAccount',
				title: '<%= UnicodeLanguageUtil.get(request, "accounts") %>',
				uri: '<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>"><portlet:param name="mvcPath" value="/accounts_admin/select_account.jsp" /><portlet:param name="accountId" value="<%= String.valueOf(accountId) %>" /></portlet:renderURL>'
			},
			function(event) {
				A.one('#<portlet:namespace />parentAccountName').html(event.accountname);
				A.one('#<portlet:namespace />parentAccountId').val(event.accountid);
			}
		);
	}

	<portlet:namespace />removeParentAccount = function() {
		A.one('#<portlet:namespace />parentAccountId').val('');
		A.one('#<portlet:namespace />parentAccountName').html('');
	}
</aui:script>