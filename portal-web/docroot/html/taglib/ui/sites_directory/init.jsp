<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/taglib/init.jsp" %>

<%@ page import="com.liferay.portal.kernel.util.comparator.GroupNameComparator" %><%@
page import="com.liferay.taglib.ui.SitesDirectoryTag" %>

<%
String displayStyle = (String)request.getAttribute("liferay-ui:sites-directory:displayStyle");
String sites = (String)request.getAttribute("liferay-ui:sites-directory:sites");

Group group = GroupLocalServiceUtil.getGroup(scopeGroupId);
%>