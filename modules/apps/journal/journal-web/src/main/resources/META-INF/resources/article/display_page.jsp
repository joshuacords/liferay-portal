<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String layoutUuid = null;

JournalArticle article = journalDisplayContext.getArticle();

if (article != null) {
	layoutUuid = article.getLayoutUuid();
}

Layout articleLayout = null;

if (Validator.isNotNull(layoutUuid)) {
	articleLayout = LayoutLocalServiceUtil.fetchLayoutByUuidAndGroupId(layoutUuid, article.getGroupId(), false);

	if (articleLayout == null) {
		articleLayout = LayoutLocalServiceUtil.fetchLayoutByUuidAndGroupId(layoutUuid, article.getGroupId(), true);
	}
}

JournalEditArticleDisplayContext journalEditArticleDisplayContext = new JournalEditArticleDisplayContext(request, liferayPortletResponse, article);
%>

<p class="text-secondary"><liferay-ui:message key="changing-the-display-page-template-will-affect-all-web-content-article-versions-even-when-saving-it-as-a-draft" /></p>

<c:if test="<%= Validator.isNotNull(layoutUuid) && (articleLayout == null) %>">
	<div class="alert alert-warning">
		<liferay-ui:message arguments="<%= layoutUuid %>" key="this-article-is-configured-to-use-a-display-page-that-does-not-exist-on-the-current-site" />
	</div>
</c:if>

<liferay-asset:select-asset-display-page
	classNameId="<%= PortalUtil.getClassNameId(JournalArticle.class) %>"
	classPK="<%= (article != null) ? article.getResourcePrimKey() : 0 %>"
	classTypeId="<%= journalEditArticleDisplayContext.getDDMStructureId() %>"
	groupId="<%= journalEditArticleDisplayContext.getGroupId() %>"
	showPortletLayouts="<%= true %>"
	showViewInContextLink="<%= journalEditArticleDisplayContext.getClassNameId() == JournalArticleConstants.CLASSNAME_ID_DEFAULT %>"
/>