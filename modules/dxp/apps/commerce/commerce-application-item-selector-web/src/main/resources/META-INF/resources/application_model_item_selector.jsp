<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceApplicationModelItemSelectorViewDisplayContext commerceApplicationModelItemSelectorViewDisplayContext = (CommerceApplicationModelItemSelectorViewDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

String itemSelectedEventName = commerceApplicationModelItemSelectorViewDisplayContext.getItemSelectedEventName();

PortletURL portletURL = commerceApplicationModelItemSelectorViewDisplayContext.getPortletURL();
%>

<liferay-frontend:management-bar
	includeCheckBox="<%= true %>"
	searchContainerId="commerceApplicationModels"
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
			orderByCol="<%= commerceApplicationModelItemSelectorViewDisplayContext.getOrderByCol() %>"
			orderByType="<%= commerceApplicationModelItemSelectorViewDisplayContext.getOrderByType() %>"
			orderColumns='<%= new String[] {"name"} %>'
			portletURL="<%= portletURL %>"
		/>
	</liferay-frontend:management-bar-filters>
</liferay-frontend:management-bar>

<div class="container-fluid-1280" id="<portlet:namespace />commerceApplicationModelSelectorWrapper">
	<liferay-ui:search-container
		id="commerceApplicationModels"
		searchContainer="<%= commerceApplicationModelItemSelectorViewDisplayContext.getSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.commerce.application.model.CommerceApplicationModel"
			cssClass="commerce-application-model-row"
			keyProperty="commerceApplicationModelId"
			modelVar="commerceApplicationModel"
		>

			<%
			Map<String, Object> data = new HashMap<>();

			data.put("commerce-application-model-id", commerceApplicationModel.getCommerceApplicationModelId());
			data.put("name", commerceApplicationModel.getName());

			row.setData(data);
			%>

			<liferay-ui:search-container-column-text
				cssClass="table-cell-content"
				name="id"
				property="commerceApplicationModelId"
			/>

			<liferay-ui:search-container-column-text
				cssClass="table-cell-content"
				property="name"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			displayStyle="list"
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>

<aui:script use="liferay-search-container">
	var commerceApplicationModelSelectorWrapper = A.one(
		'#<portlet:namespace />commerceApplicationModelSelectorWrapper'
	);

	var searchContainer = Liferay.SearchContainer.get(
		'<portlet:namespace />commerceApplicationModels'
	);

	searchContainer.on('rowToggled', function(event) {
		var allSelectedElements = event.elements.allSelectedElements;
		var arr = [];

		allSelectedElements.each(function() {
			var row = this.ancestor('tr');

			var data = row.getDOM().dataset;

			arr.push({
				commerceApplicationModelId: data.commerceApplicationModelId,
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