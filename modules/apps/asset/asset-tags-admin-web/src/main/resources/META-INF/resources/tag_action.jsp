<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

AssetTag tag = (AssetTag)row.getObject();
%>

<liferay-ui:icon-menu
	direction="left-side"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
	showWhenSingleIcon="<%= true %>"
>
	<c:if test="<%= assetTagsDisplayContext.isShowTagsActions() %>">
		<portlet:renderURL var="editURL">
			<portlet:param name="mvcPath" value="/edit_tag.jsp" />
			<portlet:param name="tagId" value="<%= String.valueOf(tag.getTagId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			label="<%= true %>"
			message="edit"
			url="<%= editURL %>"
		/>

		<portlet:renderURL var="mergeURL">
			<portlet:param name="mvcPath" value="/merge_tag.jsp" />
			<portlet:param name="mergeTagIds" value="<%= String.valueOf(tag.getTagId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			label="<%= true %>"
			message="merge"
			url="<%= mergeURL %>"
		/>
	</c:if>

	<portlet:actionURL name="deleteTag" var="deleteURL">
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="tagId" value="<%= String.valueOf(tag.getTagId()) %>" />
	</portlet:actionURL>

	<liferay-ui:icon-delete
		url="<%= deleteURL %>"
	/>
</liferay-ui:icon-menu>