<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceAccountGroupItemSelectorViewDisplayContext commerceAccountGroupItemSelectorViewDisplayContext = (CommerceAccountGroupItemSelectorViewDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

SearchContainer commerceAccountGroupSearchContainer = commerceAccountGroupItemSelectorViewDisplayContext.getSearchContainer();
String itemSelectedEventName = commerceAccountGroupItemSelectorViewDisplayContext.getItemSelectedEventName();
PortletURL portletURL = commerceAccountGroupItemSelectorViewDisplayContext.getPortletURL();
%>

<liferay-frontend:management-bar
	includeCheckBox="<%= true %>"
	searchContainerId="commerceAccountGroups"
>
	<liferay-frontend:management-bar-buttons>
		<liferay-frontend:management-bar-display-buttons
			displayViews='<%= new String[] {"list"} %>'
			portletURL="<%= portletURL %>"
			selectedDisplayStyle="list"
		/>
	</liferay-frontend:management-bar-buttons>

	<liferay-frontend:management-bar-filters>
		<liferay-frontend:management-bar-navigation
			navigationKeys='<%= new String[] {"all"} %>'
			portletURL="<%= portletURL %>"
		/>

		<liferay-frontend:management-bar-sort
			orderByCol="<%= commerceAccountGroupItemSelectorViewDisplayContext.getOrderByCol() %>"
			orderByType="<%= commerceAccountGroupItemSelectorViewDisplayContext.getOrderByType() %>"
			orderColumns="<%= new String[0] %>"
			portletURL="<%= portletURL %>"
		/>

		<li>
			<liferay-commerce:search-input
				actionURL="<%= portletURL %>"
				formName="searchFm"
			/>
		</li>
	</liferay-frontend:management-bar-filters>
</liferay-frontend:management-bar>

<div class="container-fluid-1280" id="<portlet:namespace />commerceAccountGroupSelectorWrapper">
	<liferay-ui:search-container
		id="commerceAccountGroups"
		searchContainer="<%= commerceAccountGroupSearchContainer %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.commerce.account.model.CommerceAccountGroup"
			cssClass="commerce-account-row"
			keyProperty="commerceAccountGroupId"
			modelVar="commerceAccountGroup"
		>

			<%
			Map<String, Object> data = new HashMap<>();

			data.put("commerce-account-group-id", commerceAccountGroup.getCommerceAccountGroupId());
			data.put("name", commerceAccountGroup.getName());

			row.setData(data);
			%>

			<liferay-ui:search-container-column-text
				cssClass="table-cell-content"
				property="name"
			/>

			<liferay-ui:search-container-column-date
				cssClass="table-cell-content"
				name="create-date"
				property="createDate"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
			searchContainer="<%= commerceAccountGroupSearchContainer %>"
		/>
	</liferay-ui:search-container>
</div>

<aui:script use="liferay-search-container">
	var searchContainer = Liferay.SearchContainer.get(
		'<portlet:namespace />commerceAccountGroups'
	);

	searchContainer.on('rowToggled', function(event) {
		var allSelectedElements = event.elements.allSelectedElements;
		var arr = [];

		allSelectedElements.each(function() {
			var row = this.ancestor('tr');

			var data = row.getDOM().dataset;

			arr.push({
				commerceAccountGroupId: data.commerceAccountGroupId,
				name: data.name
			});
		});

		Liferay.Util.getOpener().Liferay.fire(
			'<%= HtmlUtil.escapeJS(itemSelectedEventName) %>',
			{
				data: arr
			}
		);
	});
</aui:script>