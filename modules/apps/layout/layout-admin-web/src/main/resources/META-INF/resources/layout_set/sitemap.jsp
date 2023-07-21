<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String sitemapUrl = PortalUtil.getPortalURL(request) + themeDisplay.getPathContext() + "/sitemap.xml";

LayoutSet layoutSet = layoutsAdminDisplayContext.getSelLayoutSet();

TreeMap<String, String> virtualHostnames = layoutSet.getVirtualHostnames();

if (!virtualHostnames.containsKey(PortalUtil.getHost(request))) {
	sitemapUrl += "?groupId=" + layoutsAdminDisplayContext.getLiveGroupId() + "&privateLayout=" + layoutsAdminDisplayContext.isPrivateLayout();
}
%>

<liferay-ui:error-marker
	key="<%= WebKeys.ERROR_SECTION %>"
	value="siteMap"
/>

<liferay-util:buffer
	var="linkContent"
>
	<aui:a href="http://www.sitemaps.org" target="_blank">http://www.sitemaps.org</aui:a>
</liferay-util:buffer>

<div class="text-muted">
	<liferay-ui:message key="the-sitemap-protocol-notifies-search-engines-of-the-structure-of-the-website" /> <liferay-ui:message arguments="<%= linkContent %>" key="see-x-for-more-information" translateArguments="<%= false %>" />

	<br /><br />

	<%= LanguageUtil.format(request, "send-sitemap-information-to-preview", new Object[] {"<a target=\"_blank\" href=\"" + HtmlUtil.escapeAttribute(sitemapUrl) + "\">", "</a>"}, false) %>

	<ul>
		<li>
			<aui:a href='<%= "http://www.google.com/webmasters/sitemaps/ping?sitemap=" + HtmlUtil.escapeURL(sitemapUrl) %>' target="_blank">Google</aui:a>
		</li>
		<li>
			<aui:a href='<%= "https://siteexplorer.search.yahoo.com/submit/ping?sitemap=" + HtmlUtil.escapeURL(sitemapUrl) %>' target="_blank">Yahoo!</aui:a> (<liferay-ui:message key="requires-log-in" />)
		</li>
	</ul>
</div>