<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
List<DLFileRank> fileRanks = DLFileRankLocalServiceUtil.getFileRanks(scopeGroupId, user.getUserId());
%>

<c:choose>
	<c:when test="<%= fileRanks.isEmpty() %>">
		<liferay-ui:message key="there-are-no-recent-downloads" />
	</c:when>
	<c:otherwise>
		<ul class="recent-documents">

			<%
			for (DLFileRank fileRank : fileRanks) {
				try {
					FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(fileRank.getFileEntryId());

					fileEntry = fileEntry.toEscapedModel();
			%>

					<li>
						<liferay-ui:icon
							iconCssClass="<%= fileEntry.getIconCssClass() %>"
							label="<%= true %>"
							message="<%= fileEntry.getTitle() %>"
							url="<%= DLURLHelperUtil.getPreviewURL(fileEntry, fileEntry.getFileVersion(), themeDisplay, StringPool.BLANK, false, true) %>"
						/>
					</li>

			<%
				}
				catch (Exception e) {
				}
			}
			%>

		</ul>
	</c:otherwise>
</c:choose>