<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CPSpecificationOptionItemSelectorViewDisplayContext cpSpecificationOptionItemSelectorViewDisplayContext = (CPSpecificationOptionItemSelectorViewDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

SearchContainer cpSpecificationOptionSearchContainer = cpSpecificationOptionItemSelectorViewDisplayContext.getSearchContainer();

String displayStyle = cpSpecificationOptionItemSelectorViewDisplayContext.getDisplayStyle();

String itemSelectedEventName = cpSpecificationOptionItemSelectorViewDisplayContext.getItemSelectedEventName();

PortletURL portletURL = cpSpecificationOptionItemSelectorViewDisplayContext.getPortletURL();
%>

<liferay-frontend:management-bar
	includeCheckBox="<%= true %>"
	searchContainerId="cpSpecificationOptions"
>
	<liferay-frontend:management-bar-buttons>
		<liferay-frontend:management-bar-display-buttons
			displayViews='<%= new String[] {"list"} %>'
			portletURL="<%= portletURL %>"
			selectedDisplayStyle="<%= displayStyle %>"
		/>
	</liferay-frontend:management-bar-buttons>

	<liferay-frontend:management-bar-filters>
		<liferay-frontend:management-bar-sort
			orderByCol="<%= cpSpecificationOptionItemSelectorViewDisplayContext.getOrderByCol() %>"
			orderByType="<%= cpSpecificationOptionItemSelectorViewDisplayContext.getOrderByType() %>"
			orderColumns='<%= new String[] {"modified-date", "title"} %>'
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

<div class="container-fluid-1280" id="<portlet:namespace />cpSpecificationOptionSelectorWrapper">
	<liferay-ui:search-container
		id="cpSpecificationOptions"
		searchContainer="<%= cpSpecificationOptionSearchContainer %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.commerce.product.model.CPSpecificationOption"
			cssClass="commerce-product-option-row"
			keyProperty="CPSpecificationOptionId"
			modelVar="cpSpecificationOption"
		>
			<liferay-ui:search-container-column-text
				cssClass="table-cell-content"
				name="label"
			>
				<div class="commerce-product-option-title" data-id="<%= cpSpecificationOption.getCPSpecificationOptionId() %>">
					<%= HtmlUtil.escape(cpSpecificationOption.getTitle(themeDisplay.getLanguageId())) %>
				</div>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-date
				cssClass="table-cell-content"
				name="modified-date"
				property="modifiedDate"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			displayStyle="<%= displayStyle %>"
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>

<aui:script use="liferay-search-container">
	var cpSpecificationOptionSelectorWrapper = A.one(
		'#<portlet:namespace />cpSpecificationOptionSelectorWrapper'
	);

	var searchContainer = Liferay.SearchContainer.get(
		'<portlet:namespace />cpSpecificationOptions'
	);

	searchContainer.on('rowToggled', function(event) {
		Liferay.Util.getOpener().Liferay.fire(
			'<%= HtmlUtil.escapeJS(itemSelectedEventName) %>',
			{
				data: Liferay.Util.listCheckedExcept(
					cpSpecificationOptionSelectorWrapper,
					'<portlet:namespace />allRowIds'
				)
			}
		);
	});
</aui:script>