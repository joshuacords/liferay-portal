<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

Team team = (Team)request.getAttribute(TaprootWebKeys.TEAM);

long teamId = BeanParamUtil.getLong(team, request, "teamId");

long accountId = ParamUtil.getLong(request, "accountId");
%>

<liferay-util:include page="/teams_admin/edit_team_tabs.jsp" servletContext="<%= application %>" />

<portlet:actionURL name="/teams_admin/edit_team" var="editTeamURL" />

<aui:form action="<%= editTeamURL %>" cssClass="container-fluid-1280" method="post" name="fm">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="teamId" type="hidden" value="<%= teamId %>" />

	<liferay-ui:error exception="<%= NoSuchAccountException.class %>" message="please-select-an-account" />
	<liferay-ui:error exception="<%= TeamNameException.class %>" message="please-enter-a-valid-name" />

	<aui:model-context bean="<%= team %>" model="<%= Team.class %>" />

	<aui:fieldset-group>
		<aui:fieldset>
			<c:if test="<%= team != null %>">
				<aui:input label="key" name="key" type="resource" value="<%= team.getTeamKey() %>" />
			</c:if>

			<aui:input name="name" />

			<h5><liferay-ui:message key="account" /></h5>

			<p>
				<c:choose>
					<c:when test="<%= team == null %>">
						<aui:input name="accountId" type="hidden" value="<%= accountId %>" />

						<span id="<portlet:namespace />accountName">
							<c:if test="<%= accountId > 0 %>">

								<%
								Account koroneikiAccount = AccountLocalServiceUtil.getAccount(accountId);
								%>

								<%= HtmlUtil.escape(koroneikiAccount.getName()) %>
							</c:if>
						</span>

						<aui:button onClick='<%= renderResponse.getNamespace() + "openAccountSelector();" %>' value="select" />
					</c:when>
					<c:otherwise>

						<%
						Account koroneikiAccount = team.getAccount();
						%>

						<liferay-portlet:renderURL portletName="<%= TaprootPortletKeys.ACCOUNTS_ADMIN %>" var="accountURL">
							<portlet:param name="mvcRenderCommandName" value="/accounts_admin/edit_account" />
							<portlet:param name="accountId" value="<%= String.valueOf(koroneikiAccount.getAccountId()) %>" />
						</liferay-portlet:renderURL>

						<a href="<%= accountURL %>"><%= HtmlUtil.escape(koroneikiAccount.getName()) %></a>
					</c:otherwise>
				</c:choose>
			</p>
		</aui:fieldset>
	</aui:fieldset-group>

	<aui:button-row>
		<c:if test="<%= (team == null) || !team.isSystem() %>">
			<aui:button type="submit" />
		</c:if>

		<aui:button href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>

<aui:script use="aui-base">
	<portlet:namespace />openAccountSelector = function() {
		Liferay.Util.selectEntity(
			{
				dialog: {
					constrain: true,
					modal: true
				},
				eventName: 'selectAccount',
				title: '<%= UnicodeLanguageUtil.get(request, "accounts") %>',
				uri: '<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>"><portlet:param name="mvcPath" value="/accounts_admin/select_account.jsp" /></portlet:renderURL>'
			},
			function(event) {
				A.one('#<portlet:namespace />accountName').html(event.accountname);
				A.one('#<portlet:namespace />accountId').val(event.accountid);
			}
		);
	}
</aui:script>