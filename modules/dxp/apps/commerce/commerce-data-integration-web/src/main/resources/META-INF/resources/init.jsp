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

<%@ page import="com.liferay.commerce.data.integration.constants.CommerceDataIntegrationConstants" %><%@
page import="com.liferay.commerce.data.integration.constants.CommerceDataIntegrationWebKeys" %><%@
page import="com.liferay.commerce.data.integration.exception.NoSuchDataIntegrationProcessException" %><%@
page import="com.liferay.commerce.data.integration.exception.NoSuchDataIntegrationProcessLogException" %><%@
page import="com.liferay.commerce.data.integration.model.CommerceDataIntegrationProcess" %><%@
page import="com.liferay.commerce.data.integration.model.CommerceDataIntegrationProcessLog" %><%@
page import="com.liferay.commerce.data.integration.process.type.ProcessType" %><%@
page import="com.liferay.commerce.data.integration.web.internal.display.context.CommerceDataIntegrationProcessDisplayContext" %><%@
page import="com.liferay.commerce.data.integration.web.internal.display.context.CommerceDataIntegrationProcessLogDisplayContext" %><%@
page import="com.liferay.commerce.data.integration.web.internal.security.permisison.resource.CommerceDataintegrationProcessPermission" %><%@
page import="com.liferay.petra.string.StringPool" %><%@
page import="com.liferay.portal.kernel.backgroundtask.BackgroundTaskConstants" %><%@
page import="com.liferay.portal.kernel.dao.search.ResultRow" %><%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.security.permission.ActionKeys" %><%@
page import="com.liferay.portal.kernel.util.Constants" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.WebKeys" %>

<%@ page import="java.util.Date" %>

<%@ page import="javax.portlet.PortletURL" %>

<liferay-frontend:defineObjects />

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
String redirect = ParamUtil.getString(request, "redirect");

String backURL = ParamUtil.getString(request, "backURL", redirect);
%>