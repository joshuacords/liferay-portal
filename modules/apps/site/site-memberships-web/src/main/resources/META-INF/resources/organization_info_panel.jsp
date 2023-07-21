<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
List<Organization> organizations = (List<Organization>)request.getAttribute(SiteMembershipWebKeys.ORGANIZATIONS);
%>

<c:choose>
	<c:when test="<%= ListUtil.isEmpty(organizations) %>">
		<div class="sidebar-header">
			<h4><liferay-ui:message key="organizations" /></h4>
		</div>

		<clay:navigation-bar
			navigationItems="<%= siteMembershipsDisplayContext.getInfoPanelNavigationItems() %>"
		/>

		<div class="sidebar-body">
			<h5><liferay-ui:message key="num-of-organizations" /></h5>

			<%
			LinkedHashMap<String, Object> organizationParams = new LinkedHashMap<String, Object>();

			organizationParams.put("groupOrganization", Long.valueOf(siteMembershipsDisplayContext.getGroupId()));
			organizationParams.put("organizationsGroups", Long.valueOf(siteMembershipsDisplayContext.getGroupId()));
			%>

			<p>
				<%= OrganizationLocalServiceUtil.searchCount(company.getCompanyId(), OrganizationConstants.ANY_PARENT_ORGANIZATION_ID, StringPool.BLANK, StringPool.BLANK, null, null, organizationParams) %>
			</p>
		</div>
	</c:when>
	<c:when test="<%= ListUtil.isNotEmpty(organizations) && (organizations.size() == 1) %>">

		<%
		Organization organization = organizations.get(0);
		%>

		<div class="sidebar-header">
			<h4>
				<%= organization.getName() %>
			</h4>

			<h6>
				<%= LanguageUtil.get(request, organization.getType()) %>
			</h6>

			<%
			Group group = siteMembershipsDisplayContext.getGroup();
			%>

			<c:if test="<%= group.getOrganizationId() == organization.getOrganizationId() %>">
				<p class="h6 text-muted">
					<liferay-ui:message arguments="<%= new String[] {organization.getName(), LanguageUtil.get(request, organization.getType())} %>" key="this-site-belongs-to-x-which-is-an-organization-of-type-x" translateArguments="<%= false %>" />
				</p>

				<p class="h6 text-muted">
					<liferay-ui:message arguments="<%= organization.getName() %>" key="all-users-of-x-are-automatically-members-of-the-site" translateArguments="<%= false %>" />
				</p>
			</c:if>
		</div>

		<clay:navigation-bar
			navigationItems="<%= siteMembershipsDisplayContext.getInfoPanelNavigationItems() %>"
		/>

		<div class="sidebar-body">
			<h5><liferay-ui:message key="num-of-users" /></h5>

			<p>
				<%= UserLocalServiceUtil.getOrganizationUsersCount(organization.getOrganizationId(), WorkflowConstants.STATUS_APPROVED) %>
			</p>

			<%
			Address address = organization.getAddress();

			String city = address.getCity();
			%>

			<c:if test="<%= Validator.isNotNull(city) %>">
				<h5><liferay-ui:message key="city" /></h5>

				<p>
					<%= HtmlUtil.escape(city) %>
				</p>
			</c:if>

			<%
			String region = UsersAdmin.ORGANIZATION_REGION_NAME_ACCESSOR.get(organization);
			%>

			<c:if test="<%= Validator.isNotNull(region) %>">
				<h5><liferay-ui:message key="region" /></h5>

				<p>
					<%= region %>
				</p>
			</c:if>

			<%
			String country = UsersAdmin.ORGANIZATION_COUNTRY_NAME_ACCESSOR.get(organization);
			%>

			<c:if test="<%= Validator.isNotNull(country) %>">
				<h5><liferay-ui:message key="country" /></h5>

				<p>
					<%= country %>
				</p>
			</c:if>
		</div>
	</c:when>
	<c:when test="<%= ListUtil.isNotEmpty(organizations) && (organizations.size() > 1) %>">
		<div class="sidebar-header">
			<h4><liferay-ui:message arguments="<%= organizations.size() %>" key="x-items-are-selected" /></h4>
		</div>

		<clay:navigation-bar
			navigationItems="<%= siteMembershipsDisplayContext.getInfoPanelNavigationItems() %>"
		/>

		<div class="sidebar-body">
			<h5><liferay-ui:message arguments="<%= organizations.size() %>" key="x-items-are-selected" /></h5>
		</div>
	</c:when>
</c:choose>