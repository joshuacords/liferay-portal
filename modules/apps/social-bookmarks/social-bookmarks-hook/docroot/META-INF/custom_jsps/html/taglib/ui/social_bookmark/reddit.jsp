<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/taglib/ui/social_bookmark/init.jsp" %>

<%
String redditDisplayStyle = "button1";

if (displayStyle.equals("vertical")) {
	redditDisplayStyle = "button2";
}
%>

<c:choose>
	<c:when test='<%= displayStyle.equals("simple") %>'>
		<a href="<%= HttpUtil.getProtocol(request) %>://www.reddit.com/submit" onclick="window.location = '<%= HttpUtil.getProtocol(request) %>://www.reddit.com/submit?url=' + encodeURIComponent('<%= url %>'); return false" title="<liferay-ui:message escapeAttribute="<%= true %>" key="submit-to-reddit" />"><img alt="<liferay-ui:message escapeAttribute="<%= true %>" key="submit-to-reddit" />" border="0" src="<%= HttpUtil.getProtocol(request) %>://www.reddit.com/static/spreddit7.gif" /></a>
	</c:when>
	<c:otherwise>
		<aui:script>
			reddit_newwindow = '1';
			reddit_title = '<%= HtmlUtil.escapeJS(title) %>';
			reddit_url = '<%= url %>';
		</aui:script>

		<script src="<%= HttpUtil.getProtocol(request) %>://www.reddit.com/static/button/<%= redditDisplayStyle %>.js?styled=off" type="text/javascript"></script>
	</c:otherwise>
</c:choose>