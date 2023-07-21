<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

TeamDisplay teamDisplay = (TeamDisplay)row.getObject();

ViewAccountTeamsDisplayContext viewAccountTeamsDisplayContext = ProvisioningWebComponentProvider.getViewAccountTeamsDisplayContext(renderRequest, renderResponse, request);
%>

<c:if test="<%= !teamDisplay.isSystem() && viewAccountTeamsDisplayContext.hasManageAccountsPermission() %>">
	<liferay-ui:icon-menu
		direction="left-side"
		icon="<%= StringPool.BLANK %>"
		markupView="lexicon"
		message="<%= StringPool.BLANK %>"
	>
		<liferay-ui:icon-delete
			confirmation="are-you-sure-you-want-to-delete-this-team"
			icon="trash"
			label="<%= false %>"
			showIcon="<%= true %>"
			url="<%= teamDisplay.getDeleteTeamURL() %>"
		/>
	</liferay-ui:icon-menu>
</c:if>