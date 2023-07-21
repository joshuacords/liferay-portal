<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<liferay-util:include page="/common/view_account_search_header.jsp" servletContext="<%= application %>" />

<%
ViewTeamDisplayContext viewTeamDisplayContext = ProvisioningWebComponentProvider.getViewTeamDisplayContext(renderRequest, renderResponse, request);

viewTeamDisplayContext.addPortletBreadcrumbEntries();

String tabs1 = ParamUtil.getString(request, "tabs1");
%>

<liferay-util:include page="/accounts/view_team_header.jsp" servletContext="<%= application %>" />

<div class="account team" id="team">
	<div class="account-content team-details">
		<liferay-ui:tabs
			names="team-members,partner-reseller-si-accounts,first-line-support-accounts"
			portletURL="<%= viewTeamDisplayContext.getPortletURL() %>"
		/>

		<div class="details-table member-details">
			<c:choose>
				<c:when test='<%= tabs1.equals("first-line-support-accounts") %>'>
					<liferay-util:include page="/accounts/view_team_first_line_support_accounts.jsp" servletContext="<%= application %>" />
				</c:when>
				<c:when test='<%= tabs1.equals("partner-reseller-si-accounts") %>'>
					<liferay-util:include page="/accounts/view_team_partner_reseller_si_accounts.jsp" servletContext="<%= application %>" />
				</c:when>
				<c:otherwise>
					<liferay-util:include page="/accounts/view_team_members.jsp" servletContext="<%= application %>" />
				</c:otherwise>
			</c:choose>
		</div>
	</div>

	<div class="side-panel" id="<portlet:namespace />sidePanel">
		<react:component
			data="<%= viewTeamDisplayContext.getPanelData() %>"
			module="js/apps/SidePanelApp"
		/>
	</div>
</div>