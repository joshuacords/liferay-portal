<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/ddm" prefix="liferay-ddm" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>

<%@ page import="com.liferay.petra.string.StringPool" %><%@
page import="com.liferay.portal.kernel.util.GetterUtil" %><%@
page import="com.liferay.portal.osgi.web.portlet.container.test.TestPortlet" %>

<%@ page import="java.util.Collections" %><%@
page import="java.util.HashMap" %><%@
page import="java.util.Map" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
Map<String, Object> contextObjects = new HashMap<>();

contextObjects.put("testRuntimePortletId", "testRuntimePortletId");
%>

<liferay-ddm:template-renderer
	className="<%= TestPortlet.class.getName() %>"
	contextObjects="<%= contextObjects %>"
	displayStyle='<%= GetterUtil.getString(portletPreferences.getValue("displayStyle", StringPool.BLANK)) %>'
	displayStyleGroupId='<%= GetterUtil.getLong(portletPreferences.getValue("displayStyleGroupId", null), scopeGroupId) %>'
	entries="<%= Collections.emptyList() %>"
>
	This is the default content in case of failure.
</liferay-ddm:template-renderer>