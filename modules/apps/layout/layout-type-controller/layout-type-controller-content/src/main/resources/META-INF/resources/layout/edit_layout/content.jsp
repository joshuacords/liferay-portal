<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/layout/edit_layout/init.jsp" %>

<liferay-ui:success key="layoutAdded" message="the-page-was-created-succesfully" />

<liferay-ui:success key="layoutPageTemplateAdded" message="the-page-template-was-created-succesfully" />

<liferay-ui:success key="layoutPublished" message="the-page-was-published-succesfully" />

<div class="layout-content portlet-layout" id="main-content" role="main">
	<liferay-portlet:runtime
		portletName="<%= ContentPageEditorPortletKeys.CONTENT_PAGE_EDITOR_PORTLET %>"
	/>
</div>

<liferay-ui:layout-common />