<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>

<%@ page import="com.liferay.petra.string.StringPool" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.PortalUtil" %>

<%@ page import="java.util.HashMap" %><%@
page import="java.util.Map" %>

<liferay-theme:defineObjects />

<%
HashMap<String, String> cartViews = (HashMap<String, String>)request.getAttribute("liferay-commerce:cart:cartViews");
HashMap<String, String> labels = (HashMap<String, String>)request.getAttribute("liferay-commerce:cart:labels");
String checkoutURL = (String)request.getAttribute("liferay-commerce:cart:checkoutURL");
boolean displayDiscountLevels = (boolean)request.getAttribute("liferay-commerce:cart:displayDiscountLevels");
boolean displayTotalItemsQuantity = (boolean)request.getAttribute("liferay-commerce:cart:displayTotalItemsQuantity");
String orderDetailURL = (String)request.getAttribute("liferay-commerce:cart:orderDetailURL");
long orderId = (long)request.getAttribute("liferay-commerce:cart:orderId");
String spritemap = (String)request.getAttribute("liferay-commerce:cart:spritemap");
String randomNamespace = PortalUtil.generateRandomKey(request, "taglib") + StringPool.UNDERLINE;
boolean toggleable = (boolean)request.getAttribute("liferay-commerce:cart:toggleable");

String miniCartId = randomNamespace + "cart";
%>