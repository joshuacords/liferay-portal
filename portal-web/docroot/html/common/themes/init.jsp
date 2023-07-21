<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/common/init.jsp" %>

<%@ page import="com.liferay.layout.seo.kernel.LayoutSEOLink" %><%@
page import="com.liferay.layout.seo.kernel.LayoutSEOLinkManagerUtil" %><%@
page import="com.liferay.portal.kernel.model.Portlet" %><%@
page import="com.liferay.portal.kernel.model.portlet.PortletDependency" %><%@
page import="com.liferay.portal.kernel.servlet.BrowserMetadata" %><%@
page import="com.liferay.portal.kernel.upload.UploadServletRequestConfigurationHelperUtil" %><%@
page import="com.liferay.portal.servlet.ComboServletStaticURLGenerator" %><%@
page import="com.liferay.portal.util.LayoutTypeAccessPolicyTracker" %><%@
page import="com.liferay.portlet.PortletResourceAccessor" %><%@
page import="com.liferay.portlet.PortletResourceStaticURLGenerator" %><%@
page import="com.liferay.portlet.internal.RenderStateUtil" %><%@
page import="com.liferay.taglib.aui.ScriptTag" %>

<%@ page import="java.util.Iterator" %><%@
page import="java.util.concurrent.ConcurrentHashMap" %><%@
page import="java.util.function.Predicate" %>