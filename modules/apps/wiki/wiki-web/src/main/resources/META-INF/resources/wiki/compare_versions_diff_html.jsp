<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/wiki/init.jsp" %>

<%
String diffHtmlResults = (String)request.getAttribute(WebKeys.DIFF_HTML_RESULTS);
double diffVersion = GetterUtil.getDouble(request.getAttribute(WebKeys.DIFF_VERSION));

String infoMessage = StringPool.BLANK;

if (diffVersion > 0) {
	infoMessage = LanguageUtil.format(request, "unable-to-render-version-x", diffVersion);
}
%>

<liferay-ui:diff-html
	diffHtmlResults="<%= diffHtmlResults %>"
	infoMessage="<%= infoMessage %>"
/>