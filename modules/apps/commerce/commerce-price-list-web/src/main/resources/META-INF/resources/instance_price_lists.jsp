<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CPInstanceCommercePriceEntryDisplayContext cpInstanceCommercePriceEntryDisplayContext = (CPInstanceCommercePriceEntryDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CPInstance cpInstance = cpInstanceCommercePriceEntryDisplayContext.getCPInstance();
long cpInstanceId = cpInstanceCommercePriceEntryDisplayContext.getCPInstanceId();
PortletURL portletURL = cpInstanceCommercePriceEntryDisplayContext.getPortletURL();

PortletURL productSkusURL = PortalUtil.getControlPanelPortletURL(request, CPPortletKeys.CP_DEFINITIONS, lifecycle);

productSkusURL.setParameter("mvcRenderCommandName", "editProductDefinition");
productSkusURL.setParameter("cpDefinitionId", String.valueOf(cpInstanceCommercePriceEntryDisplayContext.getCPDefinitionId()));
productSkusURL.setParameter("screenNavigationCategoryKey", "skus");
%>

<portlet:actionURL name="editCPInstanceCommercePriceEntry" var="addCommercePriceEntryURL" />

<aui:form action="<%= addCommercePriceEntryURL %>" cssClass="hide" name="addCommercePriceEntryFm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.ADD_MULTIPLE %>" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="cpInstanceId" type="hidden" value="<%= cpInstanceId %>" />
	<aui:input name="commercePriceListIds" type="hidden" value="" />
</aui:form>

<div id="<portlet:namespace />entriesContainer">
	<aui:form action="<%= portletURL.toString() %>" method="post" name="fm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" />
		<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
		<aui:input name="deleteCommercePriceEntryIds" type="hidden" />

		<liferay-ui:error exception="<%= DuplicateCommercePriceEntryException.class %>" message="one-or-more-selected-entries-already-exist" />

		<%
		Map<String, String> contextParams = new HashMap<>();

		contextParams.put("cpInstanceId", String.valueOf(cpInstanceId));
		%>

		<commerce-ui:dataset-display
			clayCreationMenu="<%= cpInstanceCommercePriceEntryDisplayContext.getClayCreationMenu() %>"
			contextParams="<%= contextParams %>"
			dataProviderKey="<%= CommercePriceListDataSetConstants.COMMERCE_DATA_SET_KEY_INSTANCE_PRICE_ENTRIES %>"
			formId="fm"
			id="<%= CommercePriceListDataSetConstants.COMMERCE_DATA_SET_KEY_INSTANCE_PRICE_ENTRIES %>"
			itemsPerPage="<%= 10 %>"
			namespace="<%= renderResponse.getNamespace() %>"
			pageNumber="<%= 1 %>"
			portletURL="<%= portletURL %>"
			style="stacked"
		/>
	</aui:form>
</div>

<aui:script use="liferay-item-selector-dialog">
	Liferay.on('<portlet:namespace />addCommercePriceEntry', function() {
		var itemSelectorDialog = new A.LiferayItemSelectorDialog({
			eventName: 'priceListsSelectItem',
			on: {
				selectedItemChange: function(event) {
					var selectedItems = event.newVal;

					if (selectedItems) {
						$('#<portlet:namespace />commercePriceListIds').val(
							selectedItems
						);

						var addCommercePriceEntryFm = $(
							'#<portlet:namespace />addCommercePriceEntryFm'
						);

						submitForm(addCommercePriceEntryFm);
					}
				}
			},
			title:
				'<liferay-ui:message arguments="<%= HtmlUtil.escape(cpInstance.getSku()) %>" key="add-x-to-price-list" />',
			url:
				'<%= cpInstanceCommercePriceEntryDisplayContext.getItemSelectorUrl() %>'
		});

		itemSelectorDialog.open();
	});
</aui:script>