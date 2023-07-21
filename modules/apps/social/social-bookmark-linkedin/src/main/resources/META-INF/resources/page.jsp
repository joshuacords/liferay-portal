<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
SocialBookmark socialBookmark = (SocialBookmark)request.getAttribute("liferay-social-bookmarks:bookmark:socialBookmark");
String title = GetterUtil.getString((String)request.getAttribute("liferay-social-bookmarks:bookmark:title"));
String url = GetterUtil.getString((String)request.getAttribute("liferay-social-bookmarks:bookmark:url"));
%>

<clay:link
	buttonStyle="outline-secondary"
	data='<%= (HashMap)request.getAttribute("liferay-social-bookmarks:bookmark:data") %>'
	defaultEventHandler="openSocialBookmarkDefaultEventHandler"
	elementClasses="btn-monospaced btn-outline-borderless btn-sm lfr-portal-tooltip"
	href="<%= socialBookmark.getPostURL(title, url) %>"
	icon="social-linkedin"
	title="<%= socialBookmark.getName(request.getLocale()) %>"
/>

<liferay-frontend:component
	componentId="openSocialBookmarkDefaultEventHandler"
	module="js/OpenSocialBookmarkDefaultEventHandler"
/>