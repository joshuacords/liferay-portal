<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/taglib/ui/table_iterator/init.jsp" %>

<%
int mod = list.size() % rowLength;
%>

<c:if test="<%= mod > 0 %>">

	<%
	for (int i = mod; i < rowLength; i++) {
	%>

		<td></td>

	<%
	}
	%>

	</tr>
</c:if>

</table>