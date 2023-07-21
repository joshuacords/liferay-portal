<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.petra.string.StringPool" %><%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %><%@
page import="com.liferay.portal.kernel.util.Constants" %><%@
page import="com.liferay.portal.kernel.util.WebKeys" %>

<%@ page import="javax.portlet.WindowState" %>

<liferay-frontend:defineObjects />

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
String initUrl = portletPreferences.getValue("initUrl", StringPool.BLANK);
String proxyAuthentication = portletPreferences.getValue("proxyAuthentication", StringPool.BLANK);
String proxyAuthenticationDomain = portletPreferences.getValue("proxyAuthenticationDomain", StringPool.BLANK);
String proxyAuthenticationHost = portletPreferences.getValue("proxyAuthenticationHost", StringPool.BLANK);
String proxyAuthenticationPassword = portletPreferences.getValue("proxyAuthenticationPassword", StringPool.BLANK);
String proxyAuthenticationUsername = portletPreferences.getValue("proxyAuthenticationUsername", StringPool.BLANK);
String proxyHost = portletPreferences.getValue("proxyHost", StringPool.BLANK);
String proxyPort = portletPreferences.getValue("proxyPort", StringPool.BLANK);
String scope = portletPreferences.getValue("scope", StringPool.BLANK);
String stylesheet = portletPreferences.getValue("stylesheet", StringPool.BLANK);
%>

<%@ include file="/init-ext.jsp" %>