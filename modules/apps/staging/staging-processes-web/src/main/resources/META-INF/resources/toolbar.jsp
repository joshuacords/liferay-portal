<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String mvcRenderCommandName = ParamUtil.getString(request, "mvcRenderCommandName");

String tabs1 = ParamUtil.getString(request, "tabs1");

String navigation = ParamUtil.getString(request, "navigation", "all");
String orderByCol = ParamUtil.getString(request, "orderByCol");
String orderByType = ParamUtil.getString(request, "orderByType");
String searchContainerId = ParamUtil.getString(request, "searchContainerId");

boolean hasPublishStagingPermission = GroupPermissionUtil.contains(permissionChecker, scopeGroupId, ActionKeys.PUBLISH_STAGING);

PortletURL portletURL = liferayPortletResponse.createRenderURL();

portletURL.setParameter("mvcRenderCommandName", mvcRenderCommandName);
portletURL.setParameter("tabs1", tabs1);
portletURL.setParameter("displayStyle", stagingProcessesWebToolbarDisplayContext.getDisplayStyle());
portletURL.setParameter("navigation", navigation);
portletURL.setParameter("orderByCol", orderByCol);
portletURL.setParameter("orderByType", orderByType);
portletURL.setParameter("searchContainerId", String.valueOf(searchContainerId));
%>

<clay:management-toolbar
	actionDropdownItems="<%= stagingProcessesWebToolbarDisplayContext.getActionDropdownItems(hasPublishStagingPermission) %>"
	creationMenu="<%= stagingProcessesWebToolbarDisplayContext.getCreationMenu(hasPublishStagingPermission) %>"
	filterDropdownItems="<%= stagingProcessesWebToolbarDisplayContext.getFilterDropdownItems() %>"
	searchContainerId="<%= searchContainerId %>"
	selectable="<%= hasPublishStagingPermission %>"
	showCreationMenu='<%= tabs1.equals("processes") && hasPublishStagingPermission %>'
	showSearch="<%= false %>"
	sortingOrder="<%= stagingProcessesWebToolbarDisplayContext.getSortingOrder() %>"
	sortingURL="<%= stagingProcessesWebToolbarDisplayContext.getSortingURL() %>"
	viewTypeItems="<%= stagingProcessesWebToolbarDisplayContext.getViewTypeItems() %>"
/>