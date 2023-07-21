<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceOrganizationDisplayContext commerceOrganizationDisplayContext = (CommerceOrganizationDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

Organization organization = commerceOrganizationDisplayContext.getOrganization();

PortletURL portletURL = commerceOrganizationDisplayContext.getPortletURL();

portletURL.setParameter("mvcRenderCommandName", "viewCommerceOrganization");
%>

<portlet:renderURL var="editCommerceOrganizationURL">
	<portlet:param name="mvcRenderCommandName" value="editCommerceOrganization" />
	<portlet:param name="organizationId" value="<%= String.valueOf(organization.getOrganizationId()) %>" />
	<portlet:param name='<%= PortletQName.PUBLIC_RENDER_PARAMETER_NAMESPACE + "backURL" %>' value="<%= portletURL.toString() %>" />
</portlet:renderURL>

<div class="account-management">
	<div class="row">
		<div class="col-auto">
			<img alt="avatar" class="account-management__thumbnail img-fluid rounded-circle" src="<%= commerceOrganizationDisplayContext.getLogo(organization) %>" />
		</div>

		<div class="col d-flex flex-col justify-content-center">
			<span class="account-management__name">
				<%= HtmlUtil.escape(organization.getName()) %>
			</span>
		</div>

		<c:if test="<%= OrganizationPermissionUtil.contains(permissionChecker, organization.getOrganizationId(), ActionKeys.UPDATE) %>">
			<div class="align-items-center col-auto d-flex">
				<div class="account-management__action">
					<aui:button cssClass="btn-lg btn-secondary" href="<%= editCommerceOrganizationURL %>" value='<%= LanguageUtil.get(request, "edit-organization") %>' />
				</div>
			</div>
		</c:if>
	</div>
</div>

<liferay-frontend:screen-navigation
	context="<%= organization %>"
	key="<%= CommerceOrganizationScreenNavigationConstants.SCREEN_NAVIGATION_KEY %>"
	portletURL="<%= portletURL %>"
/>