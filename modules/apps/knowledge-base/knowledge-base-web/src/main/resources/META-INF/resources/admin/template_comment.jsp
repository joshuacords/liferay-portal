<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/admin/init.jsp" %>

<%
KBComment kbComment = (KBComment)request.getAttribute("template_comment.jsp-kb_comment");
%>

<div class="kb-template-comment">
	<table class="lfr-table" width="100%">
		<tr>
			<td align="center" valign="top">
				<liferay-ui:user-display
					displayStyle="2"
					userId="<%= kbComment.getUserId() %>"
					userName="<%= kbComment.getUserName() %>"
				/>
			</td>
			<td valign="top" width="90%">
				<div>
					<strong class="kb-question"><liferay-ui:message key="was-this-information-helpful" /></strong>

					<c:choose>
						<c:when test="<%= kbComment.getUserRating() == KBCommentConstants.USER_RATING_LIKE %>">
							<strong class="kb-yes"><liferay-ui:message key="yes" /></strong>
						</c:when>
						<c:when test="<%= kbComment.getUserRating() == KBCommentConstants.USER_RATING_DISLIKE %>">
							<strong class="kb-no"><liferay-ui:message key="no" /></strong>
						</c:when>
					</c:choose>
				</div>

				<br />

				<div>
					<%= HtmlUtil.escape(kbComment.getContent()) %>
				</div>

				<br />

				<div>
					<%= LanguageUtil.format(request, "posted-on-x", dateFormatDateTime.format(kbComment.getModifiedDate()), false) %>
				</div>

				<c:if test="<%= KBCommentPermission.contains(permissionChecker, kbComment, KBActionKeys.DELETE) %>">
					<br />

					<liferay-ui:icon-delete
						label="<%= true %>"
						url='<%= "javascript:" + renderResponse.getNamespace() + "deleteKBComment(" + kbComment.getKbCommentId() + ");" %>'
					/>
				</c:if>
			</td>
		</tr>
	</table>

	<div class="separator"><!-- --></div>
</div>