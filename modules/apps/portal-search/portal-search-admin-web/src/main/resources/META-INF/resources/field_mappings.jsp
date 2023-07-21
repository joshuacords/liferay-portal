<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ taglib uri="http://liferay.com/tld/soy" prefix="soy" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>

<%@ page import="com.liferay.portal.search.admin.web.internal.constants.SearchAdminWebKeys" %><%@
page import="com.liferay.portal.search.admin.web.internal.display.context.FieldMappingsDisplayContext" %>

<%@ page import="java.util.HashMap" %><%@
page import="java.util.Map" %>

<liferay-theme:defineObjects />

<%
FieldMappingsDisplayContext fieldMappingsDisplayContext = (FieldMappingsDisplayContext)request.getAttribute(SearchAdminWebKeys.FIELD_MAPPINGS_DISPLAY_CONTEXT);

Map<String, Object> context = new HashMap<>();

context.put("fieldMappingsJson", fieldMappingsDisplayContext.getFieldMappings());
context.put("indexList", fieldMappingsDisplayContext.getFieldMappingIndexDisplayContexts());
context.put("selectedIndexName", fieldMappingsDisplayContext.getSelectedIndexName());
context.put("spritemap", themeDisplay.getPathThemeImages() + "/lexicon/icons.svg");
%>

<soy:component-renderer
	context="<%= context %>"
	module="js/FieldMappings.es"
	templateNamespace="com.liferay.portal.search.admin.web.FieldMappings.render"
/>