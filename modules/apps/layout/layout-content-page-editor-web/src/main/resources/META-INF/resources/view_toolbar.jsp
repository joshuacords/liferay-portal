<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ContentPageEditorDisplayContext contentPageEditorDisplayContext = (ContentPageEditorDisplayContext)request.getAttribute(ContentPageEditorWebKeys.LIFERAY_SHARED_CONTENT_PAGE_EDITOR_DISPLAY_CONTEXT);
%>

<soy:component-renderer
	componentId='<%= PortalUtil.getPortletNamespace(ContentPageEditorPortletKeys.CONTENT_PAGE_EDITOR_PORTLET) + "toolbar" %>'
	context="<%= contentPageEditorDisplayContext.getFragmentsEditorToolbarSoyContext() %>"
	module="js/components/toolbar/FragmentsEditorToolbar.es"
	templateNamespace="com.liferay.layout.content.page.editor.web.FragmentsEditorToolbar.render"
/>