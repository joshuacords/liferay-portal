<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
SelectAssetListDisplayContext selectAssetListDisplayContext = new SelectAssetListDisplayContext(request, renderResponse);
%>

<clay:management-toolbar
	displayContext="<%= new SelectAssetListManagementToolbarDisplayContext(liferayPortletRequest, liferayPortletResponse, request, selectAssetListDisplayContext) %>"
/>

<div class="container-fluid-1280" id="<portlet:namespace />assetLists">
	<liferay-ui:search-container
		emptyResultsMessage="there-are-no-content-sets"
		id="assetListEntries"
		searchContainer="<%= selectAssetListDisplayContext.getSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.asset.list.model.AssetListEntry"
			cssClass="asset-list-entry entry-display-style"
			keyProperty="assetListEntryId"
			modelVar="assetListEntry"
		>
			<liferay-ui:search-container-column-icon
				icon="list"
			/>

			<liferay-ui:search-container-column-text
				colspan="<%= 2 %>"
			>
				<h5>
					<c:choose>
						<c:when test="<%= assetListEntry.getAssetListEntryId() != selectAssetListDisplayContext.getSelectedAssetListEntryId() %>">
							<aui:a
								cssClass="selector-button"
								data='<%=
									HashMapBuilder.<String, Object>put(
										"assetListEntryId", assetListEntry.getAssetListEntryId()
									).put(
										"assetListEntryTitle", assetListEntry.getTitle()
									).build()
								%>'
								href="javascript:;"
							>
								<%= HtmlUtil.escape(assetListEntry.getTitle()) %>
							</aui:a>
						</c:when>
						<c:otherwise>
							<%= HtmlUtil.escape(assetListEntry.getTitle()) %>
						</c:otherwise>
					</c:choose>
				</h5>

				<h6 class="text-default">
					<strong><liferay-ui:message key="<%= HtmlUtil.escape(assetListEntry.getTypeLabel()) %>" /></strong>
				</h6>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			displayStyle="descriptive"
			markupView="lexicon"
			searchContainer="<%= selectAssetListDisplayContext.getSearchContainer() %>"
		/>
	</liferay-ui:search-container>
</div>

<aui:script>
	Liferay.Util.selectEntityHandler(
		'#<portlet:namespace />assetLists',
		'<%= HtmlUtil.escapeJS(selectAssetListDisplayContext.getEventName()) %>'
	);
</aui:script>