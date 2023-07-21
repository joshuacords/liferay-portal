<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/taglib/ui/table_iterator/init.jsp" %>

</td>

<c:if test="<%= ((listPos + 1) % rowLength) == 0 %>">
	</tr>

	<c:if test="<%= Validator.isNotNull(rowBreak) %>">
		<tr>
			<td colspan="<%= rowLength * 2 - 1 %>">
				<%= rowBreak %>
			</td>
		</tr>
	</c:if>

	<c:if test="<%= (listPos + 1) < list.size() %>">
		<tr>
	</c:if>
</c:if>

<c:if test="<%= (listPos + 1) < list.size() %>">
	<c:if test="<%= ((listPos % rowLength) + 1) < rowLength %>">
		<td style="padding-left: <%= rowPadding %>px;"></td>
	</c:if>

	<td valign="<%= rowValign %>">
</c:if>