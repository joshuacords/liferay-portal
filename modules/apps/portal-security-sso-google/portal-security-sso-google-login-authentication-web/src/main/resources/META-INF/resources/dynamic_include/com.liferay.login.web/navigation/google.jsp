<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/dynamic_include/init.jsp" %>

<%
String googleAuthURL = PortalUtil.getPathContext() + "/c/portal/google_login?cmd=login";

String taglibOpenGoogleLoginWindow = "javascript:var googleLoginWindow = window.open('" + googleAuthURL + "', 'google', 'align=center,directories=no,height=560,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=1000'); void(''); googleLoginWindow.focus();";
%>

<liferay-ui:icon
	message="google"
	url="<%= taglibOpenGoogleLoginWindow %>"
/>