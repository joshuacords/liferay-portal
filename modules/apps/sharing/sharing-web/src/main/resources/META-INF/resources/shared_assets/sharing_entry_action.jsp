<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/shared_assets/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

SharingEntry sharingEntry = (SharingEntry)row.getObject();

SharedAssetsViewDisplayContext sharedAssetsViewDisplayContext = (SharedAssetsViewDisplayContext)renderRequest.getAttribute(SharedAssetsViewDisplayContext.class.getName());
%>

<liferay-ui:menu
	menu="<%= sharedAssetsViewDisplayContext.getSharingEntryMenu(sharingEntry) %>"
/>