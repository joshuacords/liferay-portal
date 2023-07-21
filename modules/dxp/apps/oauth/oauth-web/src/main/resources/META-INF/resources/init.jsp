<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %><%@
taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.oauth.model.OAuthApplication" %><%@
page import="com.liferay.oauth.model.OAuthApplicationConstants" %><%@
page import="com.liferay.oauth.service.OAuthApplicationLocalServiceUtil" %><%@
page import="com.liferay.oauth.service.OAuthUserLocalServiceUtil" %><%@
page import="com.liferay.oauth.service.permission.OAuthApplicationPermission" %><%@
page import="com.liferay.oauth.service.permission.OAuthPermission" %><%@
page import="com.liferay.oauth.service.permission.OAuthUserPermission" %><%@
page import="com.liferay.oauth.util.OAuthAccessor" %><%@
page import="com.liferay.oauth.util.OAuthActionKeys" %><%@
page import="com.liferay.oauth.util.OAuthConsumer" %><%@
page import="com.liferay.oauth.util.OAuthUtil" %><%@
page import="com.liferay.oauth.util.OAuthWebKeys" %><%@
page import="com.liferay.oauth.web.internal.search.OAuthApplicationDisplayTerms" %><%@
page import="com.liferay.oauth.web.internal.search.OAuthApplicationSearch" %><%@
page import="com.liferay.petra.string.StringBundler" %><%@
page import="com.liferay.portal.kernel.exception.ImageTypeException" %><%@
page import="com.liferay.portal.kernel.exception.RequiredFieldException" %><%@
page import="com.liferay.portal.kernel.oauth.OAuthException" %><%@
page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %><%@
page import="com.liferay.portal.kernel.servlet.SessionErrors" %><%@
page import="com.liferay.portal.kernel.servlet.SessionMessages" %><%@
page import="com.liferay.portal.kernel.upload.UploadException" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.StringPool" %><%@
page import="com.liferay.portal.kernel.util.StringUtil" %><%@
page import="com.liferay.portal.kernel.util.Validator" %><%@
page import="com.liferay.portal.kernel.util.WebKeys" %><%@
page import="com.liferay.portal.kernel.webserver.WebServerServletTokenUtil" %><%@
page import="com.liferay.taglib.search.ResultRow" %>

<%@ page import="java.net.MalformedURLException" %>

<%@ page import="java.util.LinkedHashMap" %>

<%@ page import="javax.portlet.WindowState" %>

<liferay-frontend:defineObjects />

<liferay-theme:defineObjects />

<portlet:defineObjects />