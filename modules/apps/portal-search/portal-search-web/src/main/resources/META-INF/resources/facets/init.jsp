<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>
<%@ page import="com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil" %><%@
page import="com.liferay.portal.search.web.internal.facet.display.builder.AssetCategoriesSearchFacetDisplayBuilder" %><%@
page import="com.liferay.portal.search.web.internal.facet.display.builder.AssetCategoryPermissionCheckerImpl" %><%@
page import="com.liferay.portal.search.web.internal.facet.display.builder.AssetEntriesSearchFacetDisplayBuilder" %><%@
page import="com.liferay.portal.search.web.internal.facet.display.builder.AssetTagsSearchFacetDisplayBuilder" %><%@
page import="com.liferay.portal.search.web.internal.facet.display.builder.FolderSearchFacetDisplayBuilder" %><%@
page import="com.liferay.portal.search.web.internal.facet.display.builder.ScopeSearchFacetDisplayBuilder" %><%@
page import="com.liferay.portal.search.web.internal.facet.display.builder.UserSearchFacetDisplayBuilder" %><%@
page import="com.liferay.portal.search.web.internal.facet.display.context.AssetCategoriesSearchFacetDisplayContext" %><%@
page import="com.liferay.portal.search.web.internal.facet.display.context.AssetCategoriesSearchFacetTermDisplayContext" %><%@
page import="com.liferay.portal.search.web.internal.facet.display.context.AssetEntriesSearchFacetDisplayContext" %><%@
page import="com.liferay.portal.search.web.internal.facet.display.context.AssetEntriesSearchFacetTermDisplayContext" %><%@
page import="com.liferay.portal.search.web.internal.facet.display.context.AssetTagsSearchFacetDisplayContext" %><%@
page import="com.liferay.portal.search.web.internal.facet.display.context.AssetTagsSearchFacetTermDisplayContext" %><%@
page import="com.liferay.portal.search.web.internal.facet.display.context.FolderSearchFacetDisplayContext" %><%@
page import="com.liferay.portal.search.web.internal.facet.display.context.FolderSearchFacetTermDisplayContext" %><%@
page import="com.liferay.portal.search.web.internal.facet.display.context.FolderSearcher" %><%@
page import="com.liferay.portal.search.web.internal.facet.display.context.FolderTitleLookupImpl" %><%@
page import="com.liferay.portal.search.web.internal.facet.display.context.ScopeSearchFacetDisplayContext" %><%@
page import="com.liferay.portal.search.web.internal.facet.display.context.ScopeSearchFacetTermDisplayContext" %><%@
page import="com.liferay.portal.search.web.internal.facet.display.context.UserSearchFacetDisplayContext" %><%@
page import="com.liferay.portal.search.web.internal.facet.display.context.UserSearchFacetTermDisplayContext" %>

<%
String randomNamespace = PortalUtil.generateRandomKey(request, _RANDOM_KEY_INPUT) + StringPool.UNDERLINE;

Facet facet = (Facet)request.getAttribute("search.jsp-facet");

String fieldParam = ParamUtil.getString(request, facet.getFieldId());

FacetConfiguration facetConfiguration = facet.getFacetConfiguration();

JSONObject dataJSONObject = facetConfiguration.getData();

FacetCollector facetCollector = facet.getFacetCollector();

String cssClass = "search-facet search-";
%>

<%!
private static final String _RANDOM_KEY_INPUT = "portlet_search_facets_" + StringUtil.randomString();
%>