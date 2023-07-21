<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
Map<String, Object> context = new HashMap<>();

context.put("itemSelectorSaveEvent", HtmlUtil.escapeJS(assetCategoriesSelectorDisplayContext.getEventName()));
context.put("multiSelection", !assetCategoriesSelectorDisplayContext.isSingleSelect());
context.put("namespace", liferayPortletResponse.getNamespace());
context.put("nodes", assetCategoriesSelectorDisplayContext.getCategoriesJSONArray());
context.put("pathThemeImages", themeDisplay.getPathThemeImages());
context.put("viewType", "tree");
%>

<soy:component-renderer
	context="<%= context %>"
	module="js/SelectCategory.es"
	templateNamespace="com.liferay.asset.categories.selector.web.SelectCategory.render"
/>