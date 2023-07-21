<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

DDMStructure ddmStructure = (DDMStructure)row.getObject();
%>

<c:choose>
	<c:when test="<%= ArrayUtil.contains(journalDisplayContext.getAddMenuFavItems(), ddmStructure.getStructureKey()) %>">
		<portlet:actionURL name="/journal/remove_menu_fav_item" var="removeAddMenuFavItemURL">
			<portlet:param name="mvcPath" value="/view_more_menu_items.jsp" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="folderId" value="<%= String.valueOf(journalDisplayContext.getFolderId()) %>" />
			<portlet:param name="ddmStructureKey" value="<%= ddmStructure.getStructureKey() %>" />
		</portlet:actionURL>

		<liferay-ui:icon
			icon="star"
			linkCssClass="icon-monospaced text-default"
			markupView="lexicon"
			message="remove-favorite"
			url="<%= removeAddMenuFavItemURL %>"
		/>
	</c:when>
	<c:otherwise>
		<c:choose>
			<c:when test="<%= journalDisplayContext.getAddMenuFavItemsLength() < journalWebConfiguration.maxAddMenuItems() %>">
				<portlet:actionURL name="/journal/add_menu_fav_item" var="addAddMenuFavItemURL">
					<portlet:param name="mvcPath" value="/view_more_menu_items.jsp" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="folderId" value="<%= String.valueOf(journalDisplayContext.getFolderId()) %>" />
					<portlet:param name="ddmStructureKey" value="<%= ddmStructure.getStructureKey() %>" />
				</portlet:actionURL>

				<liferay-ui:icon
					icon="star-o"
					linkCssClass="icon-monospaced text-default"
					markupView="lexicon"
					message="add-favorite"
					url="<%= addAddMenuFavItemURL %>"
				/>
			</c:when>
			<c:otherwise>
				<liferay-ui:icon
					cssClass="icon-monospaced text-muted"
					icon="star-o"
					markupView="lexicon"
					message="add-favorite"
				/>
			</c:otherwise>
		</c:choose>
	</c:otherwise>
</c:choose>