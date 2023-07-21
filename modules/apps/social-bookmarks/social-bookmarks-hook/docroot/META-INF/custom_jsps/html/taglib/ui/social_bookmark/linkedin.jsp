<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/taglib/ui/social_bookmark/init.jsp" %>

<%
String linkedinDisplayStyle = StringPool.BLANK;

if (displayStyle.equals("horizontal")) {
	linkedinDisplayStyle = "data-counter=\"right\"";
}
else if (displayStyle.equals("vertical")) {
	linkedinDisplayStyle = "data-counter=\"top\"";
}
%>

<liferay-util:html-bottom
	outputKey="taglib_ui_social_bookmark_linkedin"
>
	<script data-senna-track="temporary" type="text/javascript">
		if (window.IN) {
			delete window.IN;
		}
	</script>

	<script async data-senna-track="temporary" src="<%= HttpUtil.getProtocol(request) %>://platform.linkedin.com/in.js" type="text/javascript" ></script>
</liferay-util:html-bottom>

<script <%= linkedinDisplayStyle %> data-url="<%= url %>" type="in/share"></script>