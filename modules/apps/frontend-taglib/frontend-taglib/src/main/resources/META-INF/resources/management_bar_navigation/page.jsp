<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/management_bar_navigation/init.jsp" %>

<liferay-frontend:management-bar-filter
	disabled='<%= GetterUtil.getBoolean(request.getAttribute("liferay-frontend:management-bar-navigation:disabled")) %>'
	managementBarFilterItems='<%= (List<ManagementBarFilterItem>)request.getAttribute("liferay-frontend:management-bar-navigation:managementBarFilterItems") %>'
	value='<%= (String)request.getAttribute("liferay-frontend:management-bar-navigation:label") %>'
/>