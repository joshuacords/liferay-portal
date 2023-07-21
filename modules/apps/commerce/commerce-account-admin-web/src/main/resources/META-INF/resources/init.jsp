<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/commerce" prefix="liferay-commerce" %><%@
taglib uri="http://liferay.com/tld/expando" prefix="liferay-expando" %><%@
taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.commerce.account.admin.web.internal.display.context.CommerceAccountAddressAdminDisplayContext" %><%@
page import="com.liferay.commerce.account.admin.web.internal.display.context.CommerceAccountAdminDisplayContext" %><%@
page import="com.liferay.commerce.account.admin.web.internal.display.context.CommerceAccountOrganizationRelAdminDisplayContext" %><%@
page import="com.liferay.commerce.account.admin.web.internal.display.context.CommerceAccountUserRelAdminDisplayContext" %><%@
page import="com.liferay.commerce.account.admin.web.internal.servlet.taglib.ui.CommerceAccountScreenNavigationConstants" %><%@
page import="com.liferay.commerce.account.constants.CommerceAccountActionKeys" %><%@
page import="com.liferay.commerce.account.constants.CommerceAccountConstants" %><%@
page import="com.liferay.commerce.account.exception.CommerceAccountNameException" %><%@
page import="com.liferay.commerce.account.exception.CommerceAccountOrdersException" %><%@
page import="com.liferay.commerce.account.exception.NoSuchAccountException" %><%@
page import="com.liferay.commerce.account.model.CommerceAccount" %><%@
page import="com.liferay.commerce.account.model.CommerceAccountOrganizationRel" %><%@
page import="com.liferay.commerce.account.model.CommerceAccountUserRel" %><%@
page import="com.liferay.commerce.constants.CommerceAddressConstants" %><%@
page import="com.liferay.commerce.exception.CommerceAddressCityException" %><%@
page import="com.liferay.commerce.exception.CommerceAddressCountryException" %><%@
page import="com.liferay.commerce.exception.CommerceAddressStreetException" %><%@
page import="com.liferay.commerce.exception.CommerceAddressZipException" %><%@
page import="com.liferay.commerce.exception.NoSuchAddressException" %><%@
page import="com.liferay.commerce.model.CommerceAddress" %><%@
page import="com.liferay.commerce.model.CommerceCountry" %><%@
page import="com.liferay.commerce.model.CommerceRegion" %><%@
page import="com.liferay.petra.string.StringPool" %><%@
page import="com.liferay.portal.kernel.dao.search.ResultRow" %><%@
page import="com.liferay.portal.kernel.dao.search.SearchContainer" %><%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.model.Organization" %><%@
page import="com.liferay.portal.kernel.model.User" %><%@
page import="com.liferay.portal.kernel.security.permission.ActionKeys" %><%@
page import="com.liferay.portal.kernel.util.Constants" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.WebKeys" %><%@
page import="com.liferay.portal.kernel.webserver.WebServerServletTokenUtil" %><%@
page import="com.liferay.users.admin.configuration.UserFileUploadsConfiguration" %>

<%@ page import="java.util.List" %>

<%@ page import="javax.portlet.PortletURL" %>

<liferay-frontend:defineObjects />

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
String redirect = ParamUtil.getString(request, "redirect");

String backURL = ParamUtil.getString(request, "backURL", redirect);
%>