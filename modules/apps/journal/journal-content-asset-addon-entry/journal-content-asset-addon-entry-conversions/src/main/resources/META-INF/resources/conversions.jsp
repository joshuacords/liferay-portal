<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
JournalArticleDisplay articleDisplay = (JournalArticleDisplay)request.getAttribute(WebKeys.JOURNAL_ARTICLE_DISPLAY);

String extension = (String)request.getAttribute("extension");
String viewMode = ParamUtil.getString(request, "viewMode");
%>

<c:if test="<%= !viewMode.equals(Constants.PRINT) %>">
	<div class="autofit-col export-action user-tool-asset-addon-entry">
		<portlet:resourceURL id="exportArticle" var="exportArticleURL">
			<portlet:param name="groupId" value="<%= String.valueOf(articleDisplay.getGroupId()) %>" />
			<portlet:param name="articleId" value="<%= articleDisplay.getArticleId() %>" />
			<portlet:param name="targetExtension" value="<%= extension %>" />
		</portlet:resourceURL>

		<aui:a cssClass="btn btn-outline-borderless btn-outline-secondary btn-sm" href="<%= exportArticleURL.toString() %>" label='<%= LanguageUtil.format(resourceBundle, "x-download-x-as-x", new Object[] {"hide-accessible", HtmlUtil.escape(articleDisplay.getTitle()), StringUtil.toUpperCase(HtmlUtil.escape(extension))}) %>' />
	</div>
</c:if>