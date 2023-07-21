<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/panel_category_body/init.jsp" %>

<c:if test="<%= !panelApps.isEmpty() %>">
	<ul aria-labelledby="<%= id %>" class="nav nav-equal-height nav-stacked" role="menu">

		<%
		for (PanelApp panelApp : panelApps) {
		%>

			<liferay-application-list:panel-app
				panelApp="<%= panelApp %>"
			/>

		<%
		}
		%>

	</ul>
</c:if>

<liferay-application-list:panel
	panelCategory="<%= panelCategory %>"
/>