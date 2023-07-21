<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/panel_category_content/init.jsp" %>

<liferay-application-list:panel-category
	panelCategory='<%= (PanelCategory)request.getAttribute("liferay-application-list:panel-category-content:panelCategory") %>'
	showOpen='<%= GetterUtil.getBoolean(request.getAttribute("liferay-application-list:panel-category-content:showOpen")) %>'
/>