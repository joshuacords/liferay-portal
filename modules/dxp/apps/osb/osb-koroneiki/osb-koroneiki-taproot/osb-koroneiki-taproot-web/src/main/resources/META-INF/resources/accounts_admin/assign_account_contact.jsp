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

String contactRoleType = ParamUtil.getString(request, "contactRoleType");

renderResponse.setTitle(koroneikiAccount.getName());
%>

<portlet:actionURL name="/accounts_admin/assign_account_contact" var="assignAccountContactRoleURL" />

<div class="main-content-body">
	<aui:form action="<%= assignAccountContactRoleURL %>" cssClass="container-fluid-1280" method="post" name="fm">
		<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
		<aui:input name="accountId" type="hidden" value="<%= koroneikiAccount.getAccountId() %>" />

		<liferay-ui:error exception="<%= NoSuchContactException.class %>" message="the-contact-could-not-be-found" />

		<aui:fieldset-group>
			<aui:fieldset>
				<aui:input name="emailAddress" type="text" />

				<aui:select label="contact-role" name="contactRoleId">

					<%
					List<ContactRole> contactRoles = ContactRoleLocalServiceUtil.getContactRoles(contactRoleType, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

					for (ContactRole contactRole : contactRoles) {
					%>

						<aui:option label="<%= contactRole.getName() %>" value="<%= contactRole.getContactRoleId() %>" />

					<%
					}
					%>

				</aui:select>
			</aui:fieldset>
		</aui:fieldset-group>

		<aui:button-row>
			<aui:button type="submit" />

			<aui:button href="<%= redirect %>" type="cancel" />
		</aui:button-row>
	</aui:form>
</div>