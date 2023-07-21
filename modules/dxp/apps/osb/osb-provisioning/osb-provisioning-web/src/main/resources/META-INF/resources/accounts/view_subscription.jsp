<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<liferay-util:include page="/common/view_account_search_header.jsp" servletContext="<%= application %>" />

<%
ViewSubscriptionDisplayContext viewSubscriptionDisplayContext = ProvisioningWebComponentProvider.getViewSubscriptionDisplayContext(renderRequest, renderResponse, request);

viewSubscriptionDisplayContext.addPortletBreadcrumbEntries();

String tabs1 = ParamUtil.getString(request, "tabs1");
%>

<liferay-util:include page="/accounts/view_subscription_header.jsp" servletContext="<%= application %>" />

<div class="subscription" id="account">
	<div class="subscription-content">
		<liferay-ui:tabs
			names="subscription-terms,licenses"
			portletURL="<%= viewSubscriptionDisplayContext.getPortletURL() %>"
		/>

		<c:choose>
			<c:when test='<%= tabs1.equals("licenses") %>'>
				<liferay-util:include page="/accounts/view_account_license_keys.jsp" servletContext="<%= application %>" />
			</c:when>
			<c:otherwise>
				<liferay-util:include page="/accounts/view_subscription_terms.jsp" servletContext="<%= application %>" />
			</c:otherwise>
		</c:choose>
	</div>

	<div class="side-panel" id="<portlet:namespace />sidePanel">
		<react:component
			data="<%= viewSubscriptionDisplayContext.getPanelData() %>"
			module="js/apps/SidePanelApp"
		/>
	</div>
</div>