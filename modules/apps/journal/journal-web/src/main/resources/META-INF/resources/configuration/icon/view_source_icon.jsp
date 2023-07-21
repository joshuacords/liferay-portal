<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<liferay-ui:icon
	id="viewResourcesButton"
	message="view-source"
	url="javascript:;"
/>

<%
JournalArticle article = (JournalArticle)request.getAttribute(WebKeys.JOURNAL_ARTICLE);
%>

<script>
	var viewResourcesButton = document.getElementById(
		'<portlet:namespace />viewResourcesButton'
	);

	if (viewResourcesButton) {
		viewResourcesButton.addEventListener('click', function(event) {
			Liferay.Util.openWindow({
				dialog: {
					destroyOnHide: true,
					modal: true
				},
				dialogIframe: {
					bodyCssClass: 'dialog-with-footer'
				},
				title:
					'<%= HtmlUtil.escapeJS(HtmlUtil.escape(article.getTitle(themeDisplay.getLocale()))) %>',
				uri:
					'<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>"><portlet:param name="mvcPath" value="/configuration/icon/view_source.jsp" /><portlet:param name="redirect" value="<%= currentURL %>" /><portlet:param name="groupId" value="<%= String.valueOf(article.getGroupId()) %>" /><portlet:param name="articleId" value="<%= article.getArticleId() %>" /><portlet:param name="status" value="<%= String.valueOf(article.getStatus()) %>" /></portlet:renderURL>'
			});
		});
	}
</script>