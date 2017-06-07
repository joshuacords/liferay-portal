<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/init.jsp" %>

<%
PortletConfigurationPermissionsDisplayContext portletConfigurationPermissionsDisplayContext = new PortletConfigurationPermissionsDisplayContext(request, renderRequest);

if (Validator.isNotNull(portletConfigurationPermissionsDisplayContext.getModelResource())) {
	PortalUtil.addPortletBreadcrumbEntry(request, HtmlUtil.unescape(portletConfigurationPermissionsDisplayContext.getSelResourceDescription()), null);
	PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, "permissions"), currentURL);
}

Resource resource = portletConfigurationPermissionsDisplayContext.getResource();

//String tabs1 = portletConfigurationPermissionsDisplayContext.getCurrentTab();
%>

<div class="edit-permissions portlet-configuration-edit-permissions">
	<div class="portlet-configuration-body-content">
		<aui:nav-bar cssClass="collapse-basic-search" markupView="lexicon">
			<aui:nav cssClass="navbar-nav">
				<aui:nav-item label="permissions" selected="true" />
			</aui:nav>
			<ul class="nav navbar-nav navbar-right">
				<aui:nav-bar-search>
					<aui:form action="<%= portletConfigurationPermissionsDisplayContext.getIteratorURL() %>" name="searchFm">
						<liferay-ui:input-search markupView="lexicon" />
					</aui:form>
				</aui:nav-bar-search>
			</ul>
		</aui:nav-bar>
		<%--<aui:nav-bar cssClass="collapse-basic-search" markupView="lexicon">--%>
		<%--<div class="management-bar management-bar-default">
			&lt;%&ndash;<nav class="navbar navbar-default">&ndash;%&gt;
			<div class="management-bar-header">
				<%
					PortletURL currentPermissionsURL = portletConfigurationPermissionsDisplayContext.getIteratorURL();
					PortletURL availablePermissionsURL = portletConfigurationPermissionsDisplayContext.getIteratorURL();
					currentPermissionsURL.setParameter("tabs1", "current");
					availablePermissionsURL.setParameter("tabs1", "available");
				%>

				<div aria-expanded="false" class="collapse navbar-collapse" id="navbar-collapse-0">
					<ul class="nav management-bar-nav">
						<li class="dropdown">
							<a class="dropdown-toggle" data-toggle="dropdown" href="#1">
								<span class="management-bar-item-title"><%= tabs1.equals("current") ? "current" : "available" %></span>
								<span class="icon-sort"></span>
							</a>
							<ul class="dropdown-menu">
								<li class="<%= tabs1.equals("available") ? "active" : "" %>"><a href="<%= availablePermissionsURL.toString() %>">All</a></li>
								<li class="<%= tabs1.equals("current") ? "active" : "" %>"><a href="<%= currentPermissionsURL.toString() %>">Current</a></li>
							</ul>
						</li>
						<li class="dropdown">
							<a class="dropdown-toggle" data-toggle="dropdown" href="#1">
								<span class="management-bar-item-title">Order By: Modified Date</span>
								<span class="icon-sort"></span>
							</a>
							<ul class="dropdown-menu">
								<li><a href="#1">Name</a></li>
								<li><a href="#1">Create Date</a></li>
								<li class="active"><a href="#1">Modified Date</a></li>
								<li><a href="#1">Downloads</a></li>
								<li><a href="#1">Size</a></li>
							</ul>
						</li>
						<li>
							<a class="btn hidden-xs" href="#1"><span class="icon-caret-up icon-monospaced"></span></a>
						</li>
						<li>
							<a class="btn hidden-xs" href="#1"><span class="icon-caret-down icon-monospaced"></span></a>
						</li>
					</ul>

				</div>
			</div>--%>
			<%--<aui:nav cssClass="navbar-nav">



				<aui:nav-item href="<%= currentPermissionsURL.toString() %>" label="current-A" selected='<%= tabs1.equals("current") %>' />
				<aui:nav-item href="<%= availablePermissionsURL.toString() %>" label="available" selected='<%= tabs1.equals("available") %>' />
			</aui:nav>

			<aui:nav-bar-search>
				<aui:form action="<%= portletConfigurationPermissionsDisplayContext.getIteratorURL() %>" name="searchFm">
					<liferay-ui:input-search markupView="lexicon" />
				</aui:form>
			</aui:nav-bar-search>--%>
		<%--</div>--%>

		<liferay-frontend:management-bar
			disabled="<%= portletConfigurationPermissionsDisplayContext.getRoleSearchContainer().getTotal() <= 0 %>"
		>
<%--			<liferay-frontend:management-bar-buttons>
				<liferay-frontend:management-bar-display-buttons
					displayViews='<%= new String[] {"icon", "descriptive", "list"} %>'
					portletURL="<%= portletConfigurationPermissionsDisplayContext.getIteratorURL() %>"
					selectedDisplayStyle="<%= "list" %>"
				/>
			</liferay-frontend:management-bar-buttons>--%>

			<liferay-frontend:management-bar-filters>
				<liferay-frontend:management-bar-navigation
					navigationKeys='<%= new String[] {"all", "current", "available"} %>'
					navigationParam="tabs1"
					portletURL="<%= portletConfigurationPermissionsDisplayContext.getIteratorURL() %>"
				/>

				<liferay-frontend:management-bar-sort
					orderByCol="<%= "title" %>"
					orderByType="<%= "asc" %>"
					orderColumns='<%= new String[] {"title", "display-date"} %>'
					portletURL="<%= portletConfigurationPermissionsDisplayContext.getIteratorURL() %>"
				/>
			</liferay-frontend:management-bar-filters>
		</liferay-frontend:management-bar>



		<aui:form action="<%= portletConfigurationPermissionsDisplayContext.getUpdateRolePermissionsURL() %>" cssClass="container-fluid-1280" method="post" name="fm">
			<aui:input name="resourceId" type="hidden" value="<%= resource.getResourceId() %>" />

			<liferay-ui:search-container
				searchContainer="<%= portletConfigurationPermissionsDisplayContext.getRoleSearchContainer() %>"
			>
				<liferay-ui:search-container-row
					className="com.liferay.portal.kernel.model.Role"
					escapedModel="<%= true %>"
					keyProperty="roleId"
					modelVar="role"
				>

					<%
					String definePermissionsHREF = null;

					String name = role.getName();

					if (!name.equals(RoleConstants.ADMINISTRATOR) && !name.equals(RoleConstants.ORGANIZATION_ADMINISTRATOR) && !name.equals(RoleConstants.ORGANIZATION_OWNER) && !name.equals(RoleConstants.OWNER) && !name.equals(RoleConstants.SITE_ADMINISTRATOR) && !name.equals(RoleConstants.SITE_OWNER) && !role.isTeam() && RolePermissionUtil.contains(permissionChecker, role.getRoleId(), ActionKeys.DEFINE_PERMISSIONS)) {
						PortletURL definePermissionsURL = portletConfigurationPermissionsDisplayContext.getDefinePermissionsURL();

						definePermissionsURL.setParameter("roleId", String.valueOf(role.getRoleId()));

						definePermissionsHREF = definePermissionsURL.toString();
					}
					%>

					<liferay-ui:search-container-column-text
						href="<%= definePermissionsHREF %>"
						name="role"
					>
						<%= role.getTitle(locale) %>

						<c:if test="<%= layout.isPrivateLayout() && name.equals(RoleConstants.GUEST) %>">
							<liferay-ui:icon-help message="under-the-current-configuration-all-users-automatically-inherit-permissions-from-the-guest-role" />
						</c:if>
					</liferay-ui:search-container-column-text>

					<%

					// Actions

					List<String> currentIndividualActions = new ArrayList<String>();
					List<String> currentGroupActions = new ArrayList<String>();
					List<String> currentGroupTemplateActions = new ArrayList<String>();
					List<String> currentCompanyActions = new ArrayList<String>();

					ResourcePermissionUtil.populateResourcePermissionActionIds(portletConfigurationPermissionsDisplayContext.getGroupId(), role, resource, portletConfigurationPermissionsDisplayContext.getActions(), currentIndividualActions, currentGroupActions, currentGroupTemplateActions, currentCompanyActions);

					for (String action : portletConfigurationPermissionsDisplayContext.getActions()) {
						if (action.equals(ActionKeys.ACCESS_IN_CONTROL_PANEL)) {
							continue;
						}

						boolean checked = false;

						if (currentIndividualActions.contains(action) || currentGroupActions.contains(action) || currentGroupTemplateActions.contains(action) || currentCompanyActions.contains(action)) {
							checked = true;
						}

						String preselectedMsg = StringPool.BLANK;

						if (currentGroupActions.contains(action) || currentGroupTemplateActions.contains(action)) {
							preselectedMsg = "x-is-allowed-to-do-action-x-in-all-items-of-type-x-in-x";
						}
						else if (currentCompanyActions.contains(action)) {
							preselectedMsg = "x-is-allowed-to-do-action-x-in-all-items-of-type-x-in-this-portal-instance";
						}

						List<String> guestUnsupportedActions = portletConfigurationPermissionsDisplayContext.getGuestUnsupportedActions();

						boolean disabled = false;

						if (name.equals(RoleConstants.GUEST) && guestUnsupportedActions.contains(action)) {
							disabled = true;
						}

						String dataMessage = StringPool.BLANK;

						if (Validator.isNotNull(preselectedMsg)) {
							String type = portletConfigurationPermissionsDisplayContext.getSelResourceDescription();

							if (Validator.isNull(type)) {
								type = ResourceActionsUtil.getModelResource(locale, resource.getName());
							}

							dataMessage = HtmlUtil.escapeAttribute(LanguageUtil.format(request, preselectedMsg, new Object[] {role.getTitle(locale), ResourceActionsUtil.getAction(request, action), type, HtmlUtil.escape(portletConfigurationPermissionsDisplayContext.getGroupDescriptiveName())}, false));
						}

						String actionSeparator = Validator.isNotNull(preselectedMsg) ? ActionUtil.PRESELECTED : ActionUtil.ACTION;
					%>

						<liferay-ui:search-container-column-text
							name="<%= ResourceActionsUtil.getAction(request, action) %>"
						>
							<c:if test="<%= disabled && checked %>">
								<input name="<%= renderResponse.getNamespace() + role.getRoleId() + actionSeparator + action %>" type="hidden" value="<%= true %>" />
							</c:if>

							<input <%= checked ? "checked" : StringPool.BLANK %> class="<%= Validator.isNotNull(preselectedMsg) ? "lfr-checkbox-preselected" : StringPool.BLANK %>" data-message="<%= dataMessage %>" <%= disabled ? "disabled" : StringPool.BLANK %> id="<%= FriendlyURLNormalizerUtil.normalize(role.getName()) + actionSeparator + action %>" name="<%= renderResponse.getNamespace() + role.getRoleId() + actionSeparator + action %>" onclick="<%= Validator.isNotNull(preselectedMsg) ? "return false;" : StringPool.BLANK %>" type="checkbox" />
						</liferay-ui:search-container-column-text>

					<%
					}
					%>

				</liferay-ui:search-container-row>

				<liferay-ui:search-iterator markupView="lexicon" />
			</liferay-ui:search-container>
		</aui:form>
	</div>

	<aui:button-row>
		<aui:button cssClass="btn-lg" name="saveButton" type="submit" />

		<aui:button cssClass="btn-lg" type="cancel" />
	</aui:button-row>
</div>

<aui:script sandbox="<%= true %>">
	var form = $(document.<portlet:namespace />fm);

	$('#<portlet:namespace />fm').on(
		'mouseover',
		'.lfr-checkbox-preselected',
		function(event) {
			var currentTarget = $(event.currentTarget);

			Liferay.Portal.ToolTip.show(currentTarget, currentTarget.data('message'));
		}
	);

	$('#<portlet:namespace />saveButton').on(
		'click',
		function(event) {
			event.preventDefault();

			if (<%= portletConfigurationPermissionsDisplayContext.getRoleSearchContainer().getTotal() != 0 %>) {
				submitForm(form);
			}
		}
	);
</aui:script>