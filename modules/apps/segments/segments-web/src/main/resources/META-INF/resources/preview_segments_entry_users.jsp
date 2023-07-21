<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
PreviewSegmentsEntryUsersDisplayContext previewSegmentsEntryUsersDisplayContext = (PreviewSegmentsEntryUsersDisplayContext)request.getAttribute(SegmentsWebKeys.PREVIEW_SEGMENTS_ENTRY_USERS_DISPLAY_CONTEXT);
%>

<div class="container-fluid-1280 main-content-body">
	<liferay-ui:search-container
		searchContainer="<%= previewSegmentsEntryUsersDisplayContext.getSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.portal.kernel.model.User"
			escapedModel="<%= true %>"
			keyProperty="userId"
			modelVar="user2"
			rowIdProperty="screenName"
		>
			<liferay-ui:search-container-column-text
				cssClass="table-cell-expand table-cell-minw-200 table-title"
				name="name"
				orderable="<%= true %>"
				property="fullName"
			/>

			<liferay-ui:search-container-column-text
				cssClass="table-cell-expand table-cell-minw-200"
				name="email-address"
				orderable="<%= true %>"
				property="emailAddress"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>