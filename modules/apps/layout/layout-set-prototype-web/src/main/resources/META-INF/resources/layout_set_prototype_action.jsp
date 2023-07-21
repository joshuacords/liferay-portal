<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

LayoutSetPrototype layoutSetPrototype = (LayoutSetPrototype)row.getObject();

long layoutSetPrototypeId = layoutSetPrototype.getLayoutSetPrototypeId();

Group group = layoutSetPrototype.getGroup();
%>

<liferay-ui:icon-menu
	direction="left-side"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
	showWhenSingleIcon="<%= true %>"
>
	<c:if test="<%= LayoutSetPrototypePermissionUtil.contains(permissionChecker, layoutSetPrototypeId, ActionKeys.UPDATE) %>">

		<%
		PortletURL siteAdministrationURL = null;

		PanelCategoryHelper panelCategoryHelper = (PanelCategoryHelper)request.getAttribute(ApplicationListWebKeys.PANEL_CATEGORY_HELPER);

		String portletId = panelCategoryHelper.getFirstPortletId(PanelCategoryKeys.SITE_ADMINISTRATION, permissionChecker, group);

		if (Validator.isNotNull(portletId)) {
			siteAdministrationURL = PortalUtil.getControlPanelPortletURL(request, group, portletId, 0, 0, PortletRequest.RENDER_PHASE);

			siteAdministrationURL.setParameter("redirect", currentURL);
		}
		%>

		<c:if test="<%= siteAdministrationURL != null %>">
			<liferay-ui:icon
				message="manage"
				method="get"
				url="<%= siteAdministrationURL.toString() %>"
			/>
		</c:if>

		<c:choose>
			<c:when test="<%= layoutSetPrototype.isActive() && !group.isGuest() %>">
				<portlet:actionURL name="activateDeactivateLayoutSetPrototype" var="deactivateURL">
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="layoutSetPrototypeId" value="<%= String.valueOf(layoutSetPrototypeId) %>" />
					<portlet:param name="active" value="<%= Boolean.FALSE.toString() %>" />
				</portlet:actionURL>

				<liferay-ui:icon-deactivate
					url="<%= deactivateURL %>"
				/>
			</c:when>
			<c:when test="<%= !layoutSetPrototype.isActive() %>">
				<portlet:actionURL name="activateDeactivateLayoutSetPrototype" var="activateURL">
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="layoutSetPrototypeId" value="<%= String.valueOf(layoutSetPrototypeId) %>" />
					<portlet:param name="active" value="<%= Boolean.TRUE.toString() %>" />
				</portlet:actionURL>

				<liferay-ui:icon
					message="activate"
					url="<%= activateURL %>"
				/>
			</c:when>
		</c:choose>
	</c:if>

	<c:if test="<%= LayoutSetPrototypePermissionUtil.contains(permissionChecker, layoutSetPrototypeId, ActionKeys.PERMISSIONS) %>">
		<liferay-security:permissionsURL
			modelResource="<%= LayoutSetPrototype.class.getName() %>"
			modelResourceDescription="<%= layoutSetPrototype.getName(locale) %>"
			resourcePrimKey="<%= String.valueOf(layoutSetPrototypeId) %>"
			var="permissionsURL"
			windowState="<%= LiferayWindowState.POP_UP.toString() %>"
		/>

		<liferay-ui:icon
			message="permissions"
			method="get"
			url="<%= permissionsURL %>"
			useDialog="<%= true %>"
		/>
	</c:if>

	<c:if test="<%= LayoutSetPrototypePermissionUtil.contains(permissionChecker, layoutSetPrototypeId, ActionKeys.DELETE) %>">
		<portlet:actionURL name="deleteLayoutSetPrototypes" var="deleteLayoutSetPrototypesURL">
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="layoutSetPrototypeId" value="<%= String.valueOf(layoutSetPrototypeId) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			url="<%= deleteLayoutSetPrototypesURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>