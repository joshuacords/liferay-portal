<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<liferay-util:include page="/common/view_account_search_header.jsp" servletContext="<%= application %>" />

<%
String redirect = ParamUtil.getString(request, "redirect");
%>

<div class="add-items provisioning-accounts">
	<liferay-ui:header
		backURL="<%= redirect %>"
		cssClass="add-items-header"
		title="new-account"
	/>

	<liferay-ui:error exception="<%= Problem.ProblemException.class %>">

		<%
		Problem.ProblemException problemException = (Problem.ProblemException)errorException;
		%>

		<%= problemException.getMessage() %>
	</liferay-ui:error>

	<portlet:actionURL name="/accounts/edit_account" var="addAccountURL" />

	<aui:form action="<%= addAccountURL %>" cssClass="container-fluid container-fluid-max-xl" method="post" name="fm">
		<div class="add-items-sheet sheet sheet-lg">
			<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
			<aui:input inlineLabel="left" name="name" required="<%= true %>" />
			<aui:input inlineLabel="left" name="code" required="<%= true %>" />

			<aui:select inlineLabel="left" name="tier" required="<%= true %>">
				<aui:option value="" />

				<%
				for (Account.Tier tier : Account.Tier.values()) {
				%>

					<aui:option label="<%= tier %>" value="<%= tier %>" />

				<%
				}
				%>

			</aui:select>

			<aui:select inlineLabel="left" label="support-region" name="region" required="<%= true %>">
				<aui:option value="" />

				<%
				for (Account.Region region : Account.Region.values()) {
				%>

					<aui:option label="<%= region %>" value="<%= region %>" />

				<%
				}
				%>

			</aui:select>

			<aui:button-row>
				<aui:button type="submit" />

				<aui:button href="<%= redirect %>" type="cancel" />
			</aui:button-row>
		</div>
	</aui:form>
</div>