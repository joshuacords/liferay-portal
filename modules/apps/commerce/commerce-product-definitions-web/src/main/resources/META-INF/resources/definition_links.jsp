<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CPDefinitionLinkDisplayContext cpDefinitionLinkDisplayContext = (CPDefinitionLinkDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CPDefinition cpDefinition = cpDefinitionLinkDisplayContext.getCPDefinition();
PortletURL portletURL = cpDefinitionLinkDisplayContext.getPortletURL();
%>

<c:if test="<%= CommerceCatalogPermission.contains(permissionChecker, cpDefinition, ActionKeys.VIEW) %>">
	<portlet:actionURL name="editCPDefinitionLink" var="addCPDefinitionLinkURL" />

	<aui:form action="<%= addCPDefinitionLinkURL %>" cssClass="hide" name="addCPDefinitionLinkFm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.ADD %>" />
		<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
		<aui:input name="cpDefinitionId" type="hidden" value="<%= cpDefinitionLinkDisplayContext.getCPDefinitionId() %>" />
		<aui:input name="cpDefinitionIds" type="hidden" value="" />
		<aui:input name="type" type="hidden" value="" />
	</aui:form>

	<div class="pt-4" id="<portlet:namespace />productDefinitionLinksContainer">
		<aui:form action="<%= portletURL.toString() %>" method="post" name="fm">
			<aui:input name="<%= Constants.CMD %>" type="hidden" />
			<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />

			<%
			Map<String, String> contextParams = new HashMap<>();

			contextParams.put("cpDefinitionId", String.valueOf(cpDefinitionLinkDisplayContext.getCPDefinitionId()));
			%>

			<commerce-ui:dataset-display
				clayCreationMenu="<%= cpDefinitionLinkDisplayContext.getClayCreationMenu() %>"
				contextParams="<%= contextParams %>"
				dataProviderKey="<%= CommerceProductDataSetConstants.COMMERCE_DATA_SET_KEY_PRODUCT_LINKS %>"
				formId="fm"
				id="<%= CommerceProductDataSetConstants.COMMERCE_DATA_SET_KEY_PRODUCT_LINKS %>"
				itemsPerPage="<%= 10 %>"
				namespace="<%= renderResponse.getNamespace() %>"
				pageNumber="<%= 1 %>"
				portletURL="<%= portletURL %>"
				style="stacked"
			/>
		</aui:form>
	</div>

	<aui:script use="liferay-item-selector-dialog">

		<%
		for (String type : cpDefinitionLinkDisplayContext.getCPDefinitionLinkTypes()) {
		%>

			Liferay.on(
				'<portlet:namespace />addCommerceProductDefinitionLink<%= type %>',
				function() {
					var itemSelectorDialog = new A.LiferayItemSelectorDialog({
						eventName: 'productDefinitionsSelectItem',
						on: {
							selectedItemChange: function(event) {
								var selectedItems = event.newVal;

								if (selectedItems) {
									$('#<portlet:namespace />cpDefinitionIds').val(
										selectedItems
									);

									$('#<portlet:namespace />type').val('<%= type %>');

									var addCPDefinitionLinkFm = $(
										'#<portlet:namespace />addCPDefinitionLinkFm'
									);

									submitForm(addCPDefinitionLinkFm);
								}
							}
						},
						title:
							'<liferay-ui:message arguments="<%= HtmlUtil.escapeJS(cpDefinition.getName(languageId)) %>" key="add-new-product-to-x" />',
						url:
							'<%= cpDefinitionLinkDisplayContext.getItemSelectorUrl(type) %>'
					});

					itemSelectorDialog.open();
				}
			);

		<%
		}
		%>

	</aui:script>
</c:if>