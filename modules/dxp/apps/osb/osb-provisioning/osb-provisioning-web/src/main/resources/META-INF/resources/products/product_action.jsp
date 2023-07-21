<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

ProductDisplay productDisplay = (ProductDisplay)row.getObject();
%>

<liferay-ui:icon-menu
	direction="left-side"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
>
	<c:if test="<%= productDisplay.hasEditPermission() %>">
		<liferay-ui:icon-delete
			confirmation="are-you-sure-you-want-to-delete-this-product"
			icon="trash"
			label="<%= false %>"
			showIcon="<%= true %>"
			url="<%= productDisplay.getDeleteProductURL() %>"
		/>
	</c:if>
</liferay-ui:icon-menu>