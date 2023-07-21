<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.portal.kernel.log.Log" %><%@
page import="com.liferay.portal.kernel.log.LogFactoryUtil" %><%@
page import="com.liferay.portal.kernel.util.Constants" %><%@
page import="com.liferay.portal.kernel.util.Validator" %><%@
page import="com.liferay.xsl.content.web.internal.configuration.XSLContentConfiguration" %><%@
page import="com.liferay.xsl.content.web.internal.configuration.XSLContentPortletInstanceConfiguration" %><%@
page import="com.liferay.xsl.content.web.internal.display.context.XSLContentDisplayContext" %>

<liferay-theme:defineObjects />

<%
XSLContentConfiguration xslContentConfiguration = (XSLContentConfiguration)request.getAttribute(XSLContentConfiguration.class.getName());

XSLContentDisplayContext xslContentDisplayContext = new XSLContentDisplayContext(request, xslContentConfiguration);

XSLContentPortletInstanceConfiguration xslContentPortletInstanceConfiguration = xslContentDisplayContext.getXSLContentPortletInstanceConfiguration();
%>

<%@ include file="/init-ext.jsp" %>