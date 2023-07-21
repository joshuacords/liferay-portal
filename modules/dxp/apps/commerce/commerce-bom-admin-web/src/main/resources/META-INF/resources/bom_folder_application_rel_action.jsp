<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceBOMAdminDisplayContext commerceBOMAdminDisplayContext = (CommerceBOMAdminDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

CommerceBOMFolderApplicationRel commerceBOMFolderApplicationRel = (CommerceBOMFolderApplicationRel)row.getObject();
%>

<liferay-ui:icon-menu
	direction="left-side"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
	showWhenSingleIcon="<%= true %>"
>
	<c:if test="<%= commerceBOMAdminDisplayContext.hasCommerceBOMFolderPermissions(commerceBOMFolderApplicationRel.getCommerceBOMFolderId(), ActionKeys.UPDATE) %>">
		<portlet:actionURL name="editCommerceBOMFolderApplicationRel" var="deleteURL">
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="commerceBOMFolderApplicationRelId" value="<%= String.valueOf(commerceBOMFolderApplicationRel.getCommerceBOMFolderApplicationRelId()) %>" />
			<portlet:param name="commerceBOMFolderId" value="<%= String.valueOf(commerceBOMFolderApplicationRel.getCommerceBOMFolderId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			url="<%= deleteURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>