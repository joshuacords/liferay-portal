<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/taglib/ui/social_bookmark/init.jsp" %>

<a class="addthis_button" href="http://www.addthis.com/bookmark.php?v=300">
	<img alt="<liferay-ui:message escapeAttribute="<%= true %>" key="bookmark-and-share" />" height="16" src="http://s7.addthis.com/static/btn/v2/lg-share-en.gif" style="border: 0;" width="125" />
</a>

<liferay-util:html-bottom
	outputKey="addthis"
>
	<script src="<%= HttpUtil.getProtocol(request) %>://s7.addthis.com/js/300/addthis_widget.js" type="text/javascript"></script>
</liferay-util:html-bottom>

<aui:script>
	var addthis_share = {
		title: '<%= HtmlUtil.escapeJS(title) %>',
		url: '<%= url %>'
	}
</aui:script>