<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/admin/common/init.jsp" %>

<%
KBArticle kbArticle = (KBArticle)request.getAttribute(KBWebKeys.KNOWLEDGE_BASE_KB_ARTICLE);

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

KBComment kbComment = (KBComment)row.getObject();

int previousStatus = KBUtil.getPreviousStatus(kbComment.getStatus());
int nextStatus = KBUtil.getNextStatus(kbComment.getStatus());
%>

<c:if test="<%= KBArticlePermission.contains(permissionChecker, kbArticle, KBActionKeys.UPDATE) %>">
	<liferay-ui:icon-menu
		direction="left-side"
		icon="<%= StringPool.BLANK %>"
		markupView="lexicon"
		message="actions"
		showWhenSingleIcon="<%= true %>"
	>
		<c:if test="<%= previousStatus != KBCommentConstants.STATUS_NONE %>">
			<liferay-portlet:actionURL name="updateKBCommentStatus" varImpl="previousStatusURL">
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="kbCommentId" value="<%= String.valueOf(kbComment.getKbCommentId()) %>" />
				<portlet:param name="kbCommentStatus" value="<%= String.valueOf(previousStatus) %>" />
			</liferay-portlet:actionURL>

			<liferay-ui:icon
				message="<%= KBUtil.getStatusTransitionLabel(previousStatus) %>"
				url="<%= previousStatusURL.toString() %>"
			/>
		</c:if>

		<c:if test="<%= nextStatus != KBCommentConstants.STATUS_NONE %>">
			<liferay-portlet:actionURL name="updateKBCommentStatus" varImpl="nextStatusURL">
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="kbCommentId" value="<%= String.valueOf(kbComment.getKbCommentId()) %>" />
				<portlet:param name="kbCommentStatus" value="<%= String.valueOf(nextStatus) %>" />
			</liferay-portlet:actionURL>

			<liferay-ui:icon
				message="<%= KBUtil.getStatusTransitionLabel(nextStatus) %>"
				url="<%= nextStatusURL.toString() %>"
			/>
		</c:if>

		<c:if test="<%= KBCommentPermission.contains(permissionChecker, kbComment, KBActionKeys.DELETE) %>">
			<liferay-portlet:actionURL name="deleteKBComment" varImpl="deleteURL">
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="kbCommentId" value="<%= String.valueOf(kbComment.getKbCommentId()) %>" />
			</liferay-portlet:actionURL>

			<liferay-ui:icon-delete
				url="<%= deleteURL.toString() %>"
			/>
		</c:if>
	</liferay-ui:icon-menu>
</c:if>