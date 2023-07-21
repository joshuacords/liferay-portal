<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
JournalArticle article = journalDisplayContext.getArticle();

JournalEditArticleDisplayContext journalEditArticleDisplayContext = new JournalEditArticleDisplayContext(request, liferayPortletResponse, article);
%>

<p class="text-secondary"><liferay-ui:message key="changing-the-friendly-url-will-affect-all-web-content-article-versions-even-when-saving-it-as-a-draft" /></p>

<p class="mb-2 text-secondary">
	<%= journalEditArticleDisplayContext.getFriendlyURLBase() %>
</p>

<liferay-ui:input-localized
	availableLocales="<%= journalEditArticleDisplayContext.getAvailableLocales() %>"
	defaultLanguageId="<%= journalEditArticleDisplayContext.getDefaultArticleLanguageId() %>"
	maxLength='<%= String.valueOf(ModelHintsUtil.getMaxLength(JournalArticle.class.getName(), "urlTitle")) %>'
	name="friendlyURL"
	selectedLanguageId="<%= journalEditArticleDisplayContext.getSelectedLanguageId() %>"
	xml="<%= (article != null) ? HttpUtil.decodeURL(article.getFriendlyURLsXML()) : StringPool.BLANK %>"
/>