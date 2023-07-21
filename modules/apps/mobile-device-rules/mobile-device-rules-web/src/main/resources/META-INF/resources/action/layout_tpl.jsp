<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/action/init.jsp" %>

<liferay-ui:error-marker
	key="<%= WebKeys.ERROR_SECTION %>"
	value="layout"
/>

<h5><liferay-ui:message key="layout-template" /></h5>

<liferay-ui:layout-templates-list
	layoutTemplateId='<%= GetterUtil.getString(typeSettingsProperties.getProperty("layoutTemplateId"), PropsValues.DEFAULT_LAYOUT_TEMPLATE_ID) %>'
	layoutTemplates="<%= LayoutTemplateLocalServiceUtil.getLayoutTemplates() %>"
/>