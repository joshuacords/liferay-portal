<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>

<%@ page import="com.liferay.fragment.internal.constants.PortletFragmentEntryProcessorWebKeys" %><%@
page import="com.liferay.fragment.internal.display.context.PortletFragmentEntryProcessorDisplayContext" %>

<%
PortletFragmentEntryProcessorDisplayContext portletFragmentEntryProcessorDisplayContext = (PortletFragmentEntryProcessorDisplayContext)request.getAttribute(PortletFragmentEntryProcessorWebKeys.PORTLET_FRAGMENT_ENTRY_PROCESSOR_DISPLAY_CONTEXT);
%>

<%@ include file="/init-ext.jsp" %>