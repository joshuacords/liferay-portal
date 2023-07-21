<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ taglib uri="http://liferay.com/tld/clay" prefix="clay" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %><%@
taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.frontend.taglib.clay.sample.web.constants.ClaySamplePortletKeys" %><%@
page import="com.liferay.frontend.taglib.clay.sample.web.internal.display.context.CardsDisplayContext" %><%@
page import="com.liferay.frontend.taglib.clay.sample.web.internal.display.context.DropdownsDisplayContext" %><%@
page import="com.liferay.frontend.taglib.clay.sample.web.internal.display.context.ManagementToolbarsDisplayContext" %><%@
page import="com.liferay.frontend.taglib.clay.sample.web.internal.display.context.NavigationBarsDisplayContext" %><%@
page import="com.liferay.frontend.taglib.clay.servlet.taglib.util.SelectOption" %>

<%@ page import="java.util.ArrayList" %><%@
page import="java.util.HashMap" %><%@
page import="java.util.List" %><%@
page import="java.util.Map" %>

<liferay-theme:defineObjects />

<%@ include file="/init-ext.jsp" %>

<%
CardsDisplayContext cardsDisplayContext = (CardsDisplayContext)request.getAttribute(ClaySamplePortletKeys.CARDS_DISPLAY_CONTEXT);
DropdownsDisplayContext dropdownsDisplayContext = (DropdownsDisplayContext)request.getAttribute(ClaySamplePortletKeys.DROPDOWNS_DISPLAY_CONTEXT);
ManagementToolbarsDisplayContext managementToolbarsDisplayContext = (ManagementToolbarsDisplayContext)request.getAttribute(ClaySamplePortletKeys.MANAGEMENT_TOOLBARS_DISPLAY_CONTEXT);
NavigationBarsDisplayContext navigationBarsDisplayContext = (NavigationBarsDisplayContext)request.getAttribute(ClaySamplePortletKeys.NAVIGATION_BARS_DISPLAY_CONTEXT);
%>