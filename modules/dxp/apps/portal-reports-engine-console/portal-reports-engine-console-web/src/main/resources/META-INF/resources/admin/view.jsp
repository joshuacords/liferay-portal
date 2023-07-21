<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<liferay-util:include page="/admin/toolbar.jsp" servletContext="<%= application %>" />

<div class="container-fluid-1280 main-content-body">
	<c:choose>
		<c:when test="<%= hasAddSourcePermission && reportsEngineDisplayContext.isSourcesTabSelected() %>">
			<liferay-util:include page="/admin/data_source/sources.jsp" servletContext="<%= application %>" />
		</c:when>
		<c:when test="<%= hasAddDefinitionPermission && reportsEngineDisplayContext.isDefinitionsTabSelected() %>">
			<liferay-util:include page="/admin/definition/definitions.jsp" servletContext="<%= application %>" />
		</c:when>
		<c:otherwise>
			<liferay-util:include page="/admin/report/entries.jsp" servletContext="<%= application %>" />
		</c:otherwise>
	</c:choose>
</div>