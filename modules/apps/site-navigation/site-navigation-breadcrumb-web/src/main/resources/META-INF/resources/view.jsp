<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<liferay-ui:breadcrumb
	ddmTemplateGroupId="<%= siteNavigationBreadcrumbDisplayContext.getDisplayStyleGroupId() %>"
	ddmTemplateKey="<%= siteNavigationBreadcrumbDisplayContext.getDDMTemplateKey() %>"
	showCurrentGroup="<%= siteNavigationBreadcrumbDisplayContext.isShowCurrentGroup() %>"
	showGuestGroup="<%= siteNavigationBreadcrumbDisplayContext.isShowGuestGroup() %>"
	showLayout="<%= siteNavigationBreadcrumbDisplayContext.isShowLayout() %>"
	showParentGroups="<%= siteNavigationBreadcrumbDisplayContext.isShowParentGroups() %>"
	showPortletBreadcrumb="<%= siteNavigationBreadcrumbDisplayContext.isShowPortletBreadcrumb() %>"
/>