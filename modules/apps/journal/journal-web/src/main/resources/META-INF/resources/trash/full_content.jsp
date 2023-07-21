<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
AssetRendererFactory<?> assetRendererFactory = (AssetRendererFactory<?>)request.getAttribute(WebKeys.ASSET_RENDERER_FACTORY);

JournalArticle article = (JournalArticle)request.getAttribute(WebKeys.JOURNAL_ARTICLE);
JournalArticleDisplay articleDisplay = (JournalArticleDisplay)request.getAttribute(WebKeys.JOURNAL_ARTICLE_DISPLAY);
%>

<div class="journal-content-article">
	<%= articleDisplay.getContent() %>
</div>

<c:if test="<%= articleDisplay.isPaginate() %>">

	<%
	PortletURL articlePageURL = renderResponse.createRenderURL();

	articlePageURL.setParameter("mvcPath", "/preview.jsp");
	articlePageURL.setParameter("classNameId", String.valueOf(assetRendererFactory.getClassNameId()));
	articlePageURL.setParameter("classPK", String.valueOf(JournalArticleAssetRenderer.getClassPK(article)));
	articlePageURL.setWindowState(LiferayWindowState.POP_UP);
	%>

	<br />

	<liferay-ui:page-iterator
		cur="<%= articleDisplay.getCurrentPage() %>"
		curParam="page"
		delta="<%= 1 %>"
		id="articleDisplayPages"
		maxPages="<%= 25 %>"
		portletURL="<%= articlePageURL %>"
		total="<%= articleDisplay.getNumberOfPages() %>"
		type="article"
	/>

	<br />
</c:if>