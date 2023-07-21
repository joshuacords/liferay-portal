<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
OrganizationsDisplayContext organizationsDisplayContext = new OrganizationsDisplayContext(request, renderRequest, renderResponse);

OrganizationsManagementToolbarDisplayContext organizationsManagementToolbarDisplayContext = new OrganizationsManagementToolbarDisplayContext(liferayPortletRequest, liferayPortletResponse, request, organizationsDisplayContext);
%>

<clay:navigation-bar
	inverted="<%= true %>"
	navigationItems="<%= siteMembershipsDisplayContext.getViewNavigationItems() %>"
/>

<clay:management-toolbar
	displayContext="<%= organizationsManagementToolbarDisplayContext %>"
/>

<div class="closed container-fluid-1280 sidenav-container sidenav-right" id="<portlet:namespace />infoPanelId">
	<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="/organization/info_panel" var="sidebarPanelURL">
		<portlet:param name="groupId" value="<%= String.valueOf(siteMembershipsDisplayContext.getGroupId()) %>" />
	</liferay-portlet:resourceURL>

	<liferay-frontend:sidebar-panel
		resourceURL="<%= sidebarPanelURL %>"
		searchContainerId="organizations"
	>
		<liferay-util:include page="/organization_info_panel.jsp" servletContext="<%= application %>" />
	</liferay-frontend:sidebar-panel>

	<div class="sidenav-content">
		<portlet:actionURL name="deleteGroupOrganizations" var="deleteGroupOrganizationsURL">
			<portlet:param name="redirect" value="<%= currentURL %>" />
		</portlet:actionURL>

		<aui:form action="<%= deleteGroupOrganizationsURL %>" method="post" name="fm">
			<aui:input name="tabs1" type="hidden" value="organizations" />
			<aui:input name="groupId" type="hidden" value="<%= String.valueOf(siteMembershipsDisplayContext.getGroupId()) %>" />

			<liferay-ui:search-container
				id="organizations"
				searchContainer="<%= organizationsDisplayContext.getOrganizationSearchContainer() %>"
			>
				<liferay-ui:search-container-row
					className="com.liferay.portal.kernel.model.Organization"
					escapedModel="<%= true %>"
					keyProperty="organizationId"
					modelVar="organization"
				>

					<%
					String displayStyle = organizationsDisplayContext.getDisplayStyle();

					boolean selectOrganizations = false;
					%>

					<%@ include file="/organization_columns.jspf" %>
				</liferay-ui:search-container-row>

				<liferay-ui:search-iterator
					displayStyle="<%= organizationsDisplayContext.getDisplayStyle() %>"
					markupView="lexicon"
				/>
			</liferay-ui:search-container>
		</aui:form>
	</div>
</div>

<portlet:actionURL name="addGroupOrganizations" var="addGroupOrganizationsURL">
	<portlet:param name="redirect" value="<%= currentURL %>" />
</portlet:actionURL>

<aui:form action="<%= addGroupOrganizationsURL %>" cssClass="hide" name="addGroupOrganizationsFm">
	<aui:input name="tabs1" type="hidden" value="organizations" />
</aui:form>

<liferay-frontend:component
	componentId="<%= organizationsManagementToolbarDisplayContext.getDefaultEventHandler() %>"
	module="js/OrganizationsManagementToolbarDefaultEventHandler.es"
/>

<liferay-frontend:component
	componentId="<%= SiteMembershipWebKeys.ORGANIZATION_DROPDOWN_DEFAULT_EVENT_HANDLER %>"
	module="js/OrganizationDropdownDefaultEventHandler.es"
/>