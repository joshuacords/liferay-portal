<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/portal/init.jsp" %>

<%
String title = ParamUtil.getString(request, "title");

int height = ParamUtil.getInteger(request, "height", 768);
int width = ParamUtil.getInteger(request, "width", 1024);

String movie = ParamUtil.getString(request, "movie");

// LPS-72916

movie = HtmlUtil.escapeHREF(movie);
%>

<html>
	<head>
		<title><%= HtmlUtil.escape(title) %></title>

		<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />

		<script src="<%= themeDisplay.getCDNHost() + themeDisplay.getPathJavaScript() %>/misc/swfobject.js" type="text/javascript"></script>
	</head>

	<body leftmargin="0" marginheight="0" marginwidth="0" rightmargin="0" topmargin="0">
		<center>
			<c:if test="<%= Validator.isNotNull(movie) %>">
				<div id="flashMovie"></div>

				<script type="text/javascript">
					var so = new SWFObject('<%= HtmlUtil.escapeJS(movie) %>', 'flashMovie', '<%= width %>', '<%= height %>', '6', '#FFFFFF');

					so.write('flashMovie');
				</script>
			</c:if>
		</center>
	</body>
</html>