<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/portal/init.jsp" %>

<%
String[][] emoticons = BBCodeTranslatorUtil.getEmoticons();

for (int i = 0; i < emoticons.length; i++) {
%>

	<a class="emoticon lfr-button" emoticonCode="<%= emoticons[i][1] %>"><%= StringUtil.replace(emoticons[i][0], ThemeConstants.TOKEN_THEME_IMAGES_PATH, themeDisplay.getPathThemeImages()) %></a>

<%
}
%>