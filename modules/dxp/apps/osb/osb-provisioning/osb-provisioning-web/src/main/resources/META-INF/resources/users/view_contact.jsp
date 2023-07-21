<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<liferay-util:include page="/common/view_account_search_header.jsp" servletContext="<%= application %>" />

<%
ViewContactDisplayContext viewContactDisplayContext = ProvisioningWebComponentProvider.getViewContactDisplayContext(renderRequest, renderResponse, request);

viewContactDisplayContext.addPortletBreadcrumbEntries();

String tabs1 = ParamUtil.getString(request, "tabs1");

String tabNames = "accounts,entitlements";

if (viewContactDisplayContext.hasManageContactsPermission()) {
	tabNames = "accounts,general,entitlements";
}
%>

<liferay-util:include page="/users/view_contact_header.jsp" servletContext="<%= application %>" />

<div class="contact" id="contact">
	<div class="contact-content">
		<liferay-ui:tabs
			names="<%= tabNames %>"
			portletURL="<%= viewContactDisplayContext.getPortletURL() %>"
		/>

		<c:choose>
			<c:when test='<%= tabs1.equals("entitlements") %>'>
				<liferay-util:include page="/users/view_contact_entitlements.jsp" servletContext="<%= application %>" />
			</c:when>
			<c:when test='<%= tabs1.equals("general") %>'>
				<liferay-util:include page="/users/edit_contact.jsp" servletContext="<%= application %>" />
			</c:when>
			<c:otherwise>
				<liferay-util:include page="/users/view_contact_accounts.jsp" servletContext="<%= application %>" />
			</c:otherwise>
		</c:choose>
	</div>
</div>