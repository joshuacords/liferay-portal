<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CPDefinitionItemSelectorViewDisplayContext cpDefinitionItemSelectorViewDisplayContext = (CPDefinitionItemSelectorViewDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

SearchContainer cpDefinitionSearchContainer = cpDefinitionItemSelectorViewDisplayContext.getSearchContainer();

String displayStyle = cpDefinitionItemSelectorViewDisplayContext.getDisplayStyle();

String itemSelectedEventName = cpDefinitionItemSelectorViewDisplayContext.getItemSelectedEventName();

PortletURL portletURL = cpDefinitionItemSelectorViewDisplayContext.getPortletURL();
%>

<liferay-frontend:management-bar
	includeCheckBox="<%= !cpDefinitionItemSelectorViewDisplayContext.isSingleSelection() %>"
	searchContainerId="cpDefinitions"
>
	<liferay-frontend:management-bar-buttons>
		<liferay-frontend:management-bar-display-buttons
			displayViews='<%= new String[] {"list"} %>'
			portletURL="<%= portletURL %>"
			selectedDisplayStyle="<%= displayStyle %>"
		/>
	</liferay-frontend:management-bar-buttons>

	<liferay-frontend:management-bar-filters>
		<liferay-frontend:management-bar-navigation
			navigationKeys='<%= new String[] {"all"} %>'
			portletURL="<%= portletURL %>"
		/>

		<liferay-frontend:management-bar-sort
			orderByCol="<%= cpDefinitionItemSelectorViewDisplayContext.getOrderByCol() %>"
			orderByType="<%= cpDefinitionItemSelectorViewDisplayContext.getOrderByType() %>"
			orderColumns='<%= new String[] {"name", "modified-date", "display-date"} %>'
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

<div class="container-fluid-1280" id="<portlet:namespace />cpDefinitionSelectorWrapper">
	<liferay-ui:search-container
		id="cpDefinitions"
		searchContainer="<%= cpDefinitionSearchContainer %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.commerce.product.model.CPDefinition"
			cssClass="commerce-product-definition-row"
			keyProperty="CPDefinitionId"
			modelVar="cpDefinition"
		>

			<%
			Map<String, Object> data = new HashMap<>();

			data.put("cp-definition-id", cpDefinition.getCPDefinitionId());
			data.put("name", cpDefinition.getName(themeDisplay.getLanguageId()));

			row.setData(data);

			CPType cpType = cpDefinitionItemSelectorViewDisplayContext.getCPType(cpDefinition.getProductTypeName());

			String thumbnailSrc = cpDefinition.getDefaultImageThumbnailSrc();
			%>

			<c:choose>
				<c:when test="<%= Validator.isNotNull(thumbnailSrc) %>">
					<liferay-ui:search-container-column-image
						name="image"
						src="<%= thumbnailSrc %>"
					/>
				</c:when>
				<c:otherwise>
					<liferay-ui:search-container-column-icon
						icon="documents-and-media"
						name="image"
					/>
				</c:otherwise>
			</c:choose>

			<liferay-ui:search-container-column-text
				cssClass="table-cell-content"
				name="name"
			>
				<div class="commerce-product-definition-title" data-id="<%= cpDefinition.getCPDefinitionId() %>">
					<%= HtmlUtil.escape(cpDefinition.getName(themeDisplay.getLanguageId())) %>
				</div>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				cssClass="table-cell-content"
				name="type"
			>
				<%= HtmlUtil.escapeAttribute(cpType.getLabel(locale)) %>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				cssClass="table-cell-content"
				name="sku"
				value="<%= HtmlUtil.escape(cpDefinitionItemSelectorViewDisplayContext.getSku(cpDefinition, locale)) %>"
			/>

			<liferay-ui:search-container-column-date
				cssClass="table-cell-content"
				name="modified-date"
				property="modifiedDate"
			/>

			<liferay-ui:search-container-column-status
				cssClass="table-cell-content"
				name="status"
				status="<%= cpDefinition.getStatus() %>"
			/>

			<c:if test="<%= cpDefinitionItemSelectorViewDisplayContext.isSingleSelection() %>">
				<liferay-ui:search-container-column-text
					cssClass="table-cell-content"
				>
					<aui:button cssClass="selector-button" value="choose" />
				</liferay-ui:search-container-column-text>
			</c:if>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			displayStyle="<%= displayStyle %>"
			markupView="lexicon"
			searchContainer="<%= cpDefinitionSearchContainer %>"
		/>
	</liferay-ui:search-container>
</div>

<c:choose>
	<c:when test="<%= cpDefinitionItemSelectorViewDisplayContext.isSingleSelection() %>">
		<aui:script use="aui-base">
			A.one('#<portlet:namespace />cpDefinitions').delegate(
				'click',
				function(event) {
					var row = this.ancestor('tr');

					var data = row.getDOM().dataset;

					Liferay.Util.getOpener().Liferay.fire(
						'<%= HtmlUtil.escapeJS(itemSelectedEventName) %>',
						{
							data: {id: data.cpDefinitionId, name: data.name}
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
	</c:when>
	<c:otherwise>
		<aui:script use="liferay-search-container">
			var cpDefinitionSelectorWrapper = A.one(
				'#<portlet:namespace />cpDefinitionSelectorWrapper'
			);

			var searchContainer = Liferay.SearchContainer.get(
				'<portlet:namespace />cpDefinitions'
			);

			searchContainer.on('rowToggled', function(event) {
				Liferay.Util.getOpener().Liferay.fire(
					'<%= HtmlUtil.escapeJS(itemSelectedEventName) %>',
					{
						data: Liferay.Util.listCheckedExcept(
							cpDefinitionSelectorWrapper,
							'<portlet:namespace />allRowIds'
						)
					}
				);
			});
		</aui:script>
	</c:otherwise>
</c:choose>