<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

boolean privateLayout = (boolean)row.getObject();
%>

<liferay-ui:icon-menu
	direction="left-side"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
	showWhenSingleIcon="<%= true %>"
>
	<c:if test="<%= layoutsAdminDisplayContext.isShowFirstColumnConfigureAction() %>">
		<liferay-ui:icon
			message="configure"
			url="<%= layoutsAdminDisplayContext.getFirstColumnConfigureLayoutURL(privateLayout) %>"
		/>
	</c:if>

	<c:if test="<%= layoutsAdminDisplayContext.isShowAddRootLayoutButton() %>">
		<liferay-ui:icon
			message="add-page"
			url="<%= layoutsAdminDisplayContext.getSelectLayoutPageTemplateEntryURL(privateLayout) %>"
		/>
	</c:if>
</liferay-ui:icon-menu>