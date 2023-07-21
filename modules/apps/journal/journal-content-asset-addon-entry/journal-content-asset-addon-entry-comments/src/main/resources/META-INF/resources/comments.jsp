<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommentsContentMetadataAssetAddonEntry commentsContentMetadataAssetAddonEntry = (CommentsContentMetadataAssetAddonEntry)request.getAttribute(WebKeys.ASSET_ADDON_ENTRY);
JournalArticleDisplay articleDisplay = (JournalArticleDisplay)request.getAttribute(WebKeys.JOURNAL_ARTICLE_DISPLAY);

String viewMode = ParamUtil.getString(request, "viewMode");
%>

<div class="content-metadata-asset-addon-entry content-metadata-comments">
	<liferay-comment:discussion
		className="<%= JournalArticle.class.getName() %>"
		classPK="<%= articleDisplay.getResourcePrimKey() %>"
		hideControls="<%= viewMode.equals(Constants.PRINT) %>"
		ratingsEnabled="<%= commentsContentMetadataAssetAddonEntry.isCommentsRatingsSelected(request) && !viewMode.equals(Constants.PRINT) %>"
		redirect="<%= currentURLObj.toString() %>"
		userId="<%= articleDisplay.getUserId() %>"
	/>
</div>