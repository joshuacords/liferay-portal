<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

Gadget gadget = (Gadget)row.getObject();

Map<String, OAuthService> oAuthServices = null;

try {
	oAuthServices = ShindigUtil.getOAuthServices(gadget.getUrl());
}
catch (Exception e) {
	row.setRestricted(true);
}
%>

<liferay-ui:icon-menu
	icon="<%= StringPool.BLANK %>"
	message="<%= StringPool.BLANK %>"
>
	<c:if test="<%= GadgetPermission.contains(permissionChecker, themeDisplay.getScopeGroupId(), gadget.getGadgetId(), ActionKeys.UPDATE) %>">
		<portlet:renderURL var="updateGadgetURL">
			<portlet:param name="mvcPath" value="/admin/edit_gadget.jsp" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="gadgetId" value="<%= String.valueOf(gadget.getGadgetId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			iconCssClass="icon-edit"
			message="edit"
			url="<%= updateGadgetURL %>"
		/>

		<c:if test="<%= (oAuthServices != null) && !oAuthServices.isEmpty() %>">
			<portlet:renderURL var="configureOAuthURL">
				<portlet:param name="mvcPath" value="/admin/edit_oauth_consumers.jsp" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="gadgetId" value="<%= String.valueOf(gadget.getGadgetId()) %>" />
			</portlet:renderURL>

			<liferay-ui:icon
				iconCssClass="icon-list-alt"
				message="manage-oauth"
				url="<%= configureOAuthURL %>"
			/>
		</c:if>

		<portlet:actionURL name="refreshGadgets" var="refreshGadgetsURL">
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="gadgetId" value="<%= String.valueOf(gadget.getGadgetId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon
			iconCssClass="icon-refresh"
			message="refresh"
			url="<%= refreshGadgetsURL %>"
		/>
	</c:if>

	<c:if test="<%= GadgetPermission.contains(permissionChecker, themeDisplay.getScopeGroupId(), gadget.getGadgetId(), ActionKeys.PERMISSIONS) %>">
		<liferay-security:permissionsURL
			modelResource="<%= Gadget.class.getName() %>"
			modelResourceDescription="<%= gadget.getName() %>"
			resourcePrimKey="<%= String.valueOf(gadget.getGadgetId()) %>"
			var="permissionsURL"
			windowState="<%= LiferayWindowState.POP_UP.toString() %>"
		/>

		<liferay-ui:icon
			iconCssClass="icon-lock"
			message="permissions"
			method="get"
			url="<%= permissionsURL %>"
			useDialog="<%= true %>"
		/>
	</c:if>

	<c:if test="<%= GadgetPermission.contains(permissionChecker, themeDisplay.getScopeGroupId(), gadget.getGadgetId(), ActionKeys.DELETE) %>">
		<portlet:actionURL name="deleteGadget" var="deleteURL">
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="gadgetId" value="<%= String.valueOf(gadget.getGadgetId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			url="<%= deleteURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>