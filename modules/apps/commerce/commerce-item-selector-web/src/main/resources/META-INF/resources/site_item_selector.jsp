<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
SimpleSiteItemSelectorViewDisplayContext simpleSiteItemSelectorViewDisplayContext = (SimpleSiteItemSelectorViewDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

String itemSelectedEventName = simpleSiteItemSelectorViewDisplayContext.getItemSelectedEventName();
%>

<liferay-frontend:management-bar
	includeCheckBox="<%= false %>"
	searchContainerId="sites"
>
	<liferay-frontend:management-bar-filters>
		<liferay-frontend:management-bar-sort
			orderByCol="<%= simpleSiteItemSelectorViewDisplayContext.getOrderByCol() %>"
			orderByType="<%= simpleSiteItemSelectorViewDisplayContext.getOrderByType() %>"
			orderColumns='<%= new String[] {"name"} %>'
			portletURL="<%= simpleSiteItemSelectorViewDisplayContext.getPortletURL() %>"
		/>
	</liferay-frontend:management-bar-filters>

	<liferay-frontend:management-bar-buttons>
		<liferay-frontend:management-bar-display-buttons
			displayViews='<%= new String[] {"list"} %>'
			portletURL="<%= renderResponse.createRenderURL() %>"
			selectedDisplayStyle="list"
		/>
	</liferay-frontend:management-bar-buttons>
</liferay-frontend:management-bar>

<div class="container-fluid-1280" id="<portlet:namespace />siteSelectorWrapper">
	<liferay-ui:search-container
		id="sites"
		searchContainer="<%= simpleSiteItemSelectorViewDisplayContext.getSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.portal.kernel.model.Group"
			keyProperty="groupId"
			modelVar="group"
		>
			<liferay-ui:search-container-column-text
				cssClass="table-cell-content"
				name="name"
				value="<%= HtmlUtil.escape(group.getName(locale)) %>"
			/>

			<liferay-ui:search-container-column-text
				cssClass="table-cell-content"
				name="channel"
				value="<%= HtmlUtil.escape(simpleSiteItemSelectorViewDisplayContext.getChannelUsingSite(group.getGroupId())) %>"
			/>

			<%
			Map<String, Object> data = new HashMap<>();

			data.put("id", group.getGroupId());
			data.put("name", group.getName(locale));

			row.setData(data);
			%>

			<liferay-ui:search-container-column-text
				cssClass="table-cell-content"
			>
				<c:choose>
					<c:when test="<%= simpleSiteItemSelectorViewDisplayContext.isSiteAvailable(group.getGroupId()) %>">
						<aui:button cssClass="selector-button" value="choose" />
					</c:when>
					<c:otherwise>
						<liferay-ui:message key="that-site-is-already-associated-with-another-channel" />
					</c:otherwise>
				</c:choose>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>

<aui:script use="aui-base">
	A.one('#<portlet:namespace />sites').delegate(
		'click',
		function(event) {
			var row = this.ancestor('tr');

			var data = row.getDOM().dataset;

			Liferay.Util.getOpener().Liferay.fire(
				'<%= HtmlUtil.escapeJS(itemSelectedEventName) %>',
				{
					data: {id: data.id, name: data.name}
				}
			);

			var popupWindow = Liferay.Util.getWindow();

			if (popupWindow !== null) {
				Liferay.Util.getWindow().hide();
			}
		},
		'.selector-button'
	);
</aui:script>