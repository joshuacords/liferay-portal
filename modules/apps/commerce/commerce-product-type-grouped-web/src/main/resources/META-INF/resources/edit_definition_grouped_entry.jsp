<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CPDefinitionGroupedEntriesDisplayContext cpDefinitionGroupedEntriesDisplayContext = (CPDefinitionGroupedEntriesDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CPDefinitionGroupedEntry cpDefinitionGroupedEntry = cpDefinitionGroupedEntriesDisplayContext.getCPDefinitionGroupedEntry();

CPDefinition cpDefinition = cpDefinitionGroupedEntry.getCPDefinition();

CProduct cProduct = cpDefinitionGroupedEntry.getEntryCProduct();

CPDefinition cProductCPDefinition = CPDefinitionLocalServiceUtil.getCPDefinition(cProduct.getPublishedCPDefinitionId());

PortletURL groupedProductsURL = renderResponse.createRenderURL();

groupedProductsURL.setParameter("mvcRenderCommandName", "editProductDefinition");
groupedProductsURL.setParameter("cpDefinitionId", String.valueOf(cpDefinition.getCPDefinitionId()));
groupedProductsURL.setParameter("screenNavigationCategoryKey", cpDefinitionGroupedEntriesDisplayContext.getScreenNavigationCategoryKey());

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(groupedProductsURL.toString());

renderResponse.setTitle(cpDefinition.getName(themeDisplay.getLanguageId()) + " - " + cProductCPDefinition.getName(themeDisplay.getLanguageId()));
%>

<portlet:actionURL name="editCPDefinitionGroupedEntry" var="editCPDefinitionGroupedEntryActionURL">
	<portlet:param name="mvcRenderCommandName" value="editCPDefinitionGroupedEntry" />
</portlet:actionURL>

<aui:form action="<%= editCPDefinitionGroupedEntryActionURL %>" cssClass="container-fluid-1280" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "saveCPDefinitionGroupedEntry();" %>'>
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= groupedProductsURL %>" />
	<aui:input name="cpDefinitionGroupedEntryId" type="hidden" value="<%= cpDefinitionGroupedEntry.getCPDefinitionGroupedEntryId() %>" />

	<aui:model-context bean="<%= cpDefinitionGroupedEntry %>" model="<%= CPDefinitionGroupedEntry.class %>" />

	<div class="lfr-form-content">
		<liferay-ui:error exception="<%= CPDefinitionGroupedEntryQuantityException.class %>" message="please-enter-a-valid-quantity" />

		<aui:fieldset-group markupView="lexicon">
			<aui:fieldset>
				<aui:input name="priority" />

				<aui:input name="quantity" />
			</aui:fieldset>
		</aui:fieldset-group>
	</div>

	<aui:button-row>
		<aui:button cssClass="btn-lg" type="submit" />

		<aui:button cssClass="btn-lg" href="<%= groupedProductsURL.toString() %>" type="cancel" />
	</aui:button-row>
</aui:form>

<aui:script>
	function <portlet:namespace />saveCPDefinitionGroupedEntry() {
		submitForm(document.<portlet:namespace />fm);
	}
</aui:script>