<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>

<%@ page import="com.liferay.commerce.frontend.ClayCreationMenu" %><%@
page import="com.liferay.commerce.frontend.ClayMenuActionItem" %><%@
page import="com.liferay.commerce.frontend.clay.data.set.ClayHeadlessDataSetActionTemplate" %><%@
page import="com.liferay.commerce.frontend.taglib.internal.model.ClayPaginationEntry" %><%@
page import="com.liferay.petra.string.StringPool" %><%@
page import="com.liferay.portal.kernel.json.JSONFactoryUtil" %><%@
page import="com.liferay.portal.kernel.json.JSONSerializer" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.PortalUtil" %><%@
page import="com.liferay.portal.kernel.util.Validator" %>

<%@ page import="java.util.List" %>

<%@ page import="javax.portlet.PortletURL" %>

<liferay-frontend:defineObjects />

<liferay-theme:defineObjects />

<%
List<ClayMenuActionItem> bulkActions = (List<ClayMenuActionItem>)request.getAttribute("liferay-commerce:dataset-display:bulkActions");
Object clayDataSetDisplayViewsContext = request.getAttribute("liferay-commerce:dataset-display:clayDataSetDisplayViewsContext");
Object clayDataSetFiltersContext = request.getAttribute("liferay-commerce:dataset-display:clayDataSetFiltersContext");
List<ClayHeadlessDataSetActionTemplate> clayHeadlessDataSetActionTemplates = (List<ClayHeadlessDataSetActionTemplate>)request.getAttribute("liferay-commerce:dataset-display:clayHeadlessDataSetActionTemplates");
ClayCreationMenu clayCreationMenu = (ClayCreationMenu)request.getAttribute("liferay-commerce:dataset-display:clayCreationMenu");
String apiUrl = (String)request.getAttribute("liferay-commerce:dataset-display:apiUrl");
String formId = (String)request.getAttribute("liferay-commerce:dataset-display:formId");
String id = (String)request.getAttribute("liferay-commerce:dataset-display:id");
int itemsPerPage = (int)request.getAttribute("liferay-commerce:dataset-display:itemsPerPage");
String namespace = (String)request.getAttribute("liferay-commerce:dataset-display:namespace");
String nestedItemsKey = (String)request.getAttribute("liferay-commerce:dataset-display:nestedItemsKey");
String nestedItemsReferenceKey = (String)request.getAttribute("liferay-commerce:dataset-display:nestedItemsReferenceKey");
int pageNumber = (int)request.getAttribute("liferay-commerce:dataset-display:pageNumber");
List<ClayPaginationEntry> paginationEntries = (List<ClayPaginationEntry>)request.getAttribute("liferay-commerce:dataset-display:paginationEntries");
PortletURL portletURL = (PortletURL)request.getAttribute("liferay-commerce:dataset-display:portletURL");
List<String> selectedItems = (List<String>)request.getAttribute("liferay-commerce:dataset-display:selectedItems");
String selectedItemsKey = (String)request.getAttribute("liferay-commerce:dataset-display:selectedItemsKey");
String selectionType = (String)request.getAttribute("liferay-commerce:dataset-display:selectionType");
boolean showManagementBar = (boolean)request.getAttribute("liferay-commerce:dataset-display:showManagementBar");
boolean showPagination = (boolean)request.getAttribute("liferay-commerce:dataset-display:showPagination");
boolean showSearch = (boolean)request.getAttribute("liferay-commerce:dataset-display:showSearch");
String spritemap = (String)request.getAttribute("liferay-commerce:dataset-display:spritemap");
String style = (String)request.getAttribute("liferay-commerce:dataset-display:style");

JSONSerializer jsonSerializer = JSONFactoryUtil.createJSONSerializer();

String randomNamespace = PortalUtil.generateRandomKey(request, "taglib_step_tracker") + StringPool.UNDERLINE;

String containerId = randomNamespace + "table-id";
%>