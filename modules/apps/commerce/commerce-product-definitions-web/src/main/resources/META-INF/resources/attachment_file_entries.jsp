<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CPAttachmentFileEntriesDisplayContext cpAttachmentFileEntriesDisplayContext = (CPAttachmentFileEntriesDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

PortletURL portletURL = cpAttachmentFileEntriesDisplayContext.getPortletURL();

Map<String, String> contextParams = new HashMap<>();

contextParams.put("cpDefinitionId", String.valueOf(cpAttachmentFileEntriesDisplayContext.getCPDefinitionId()));
%>

<c:if test="<%= CommerceCatalogPermission.contains(permissionChecker, cpAttachmentFileEntriesDisplayContext.getCPDefinition(), ActionKeys.UPDATE) %>">
	<aui:form action="<%= portletURL.toString() %>" cssClass="pt-4" method="post" name="fm">
		<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />

		<liferay-ui:error exception="<%= NoSuchSkuContributorCPDefinitionOptionRelException.class %>" message="there-are-no-options-set-as-sku-contributor" />

		<commerce-ui:panel
			bodyClasses="p-0"
			title='<%= LanguageUtil.get(request, "images") %>'
		>
			<commerce-ui:dataset-display
				clayCreationMenu="<%= cpAttachmentFileEntriesDisplayContext.getClayCreationMenu(CPAttachmentFileEntryConstants.TYPE_IMAGE) %>"
				contextParams="<%= contextParams %>"
				dataProviderKey="<%= CommerceProductDataSetConstants.COMMERCE_DATA_SET_KEY_PRODUCT_IMAGES %>"
				formId="fm"
				id="<%= CommerceProductDataSetConstants.COMMERCE_DATA_SET_KEY_PRODUCT_IMAGES %>"
				itemsPerPage="<%= 10 %>"
				namespace="<%= renderResponse.getNamespace() %>"
				pageNumber="<%= 1 %>"
				portletURL="<%= portletURL %>"
			/>
		</commerce-ui:panel>

		<commerce-ui:panel
			bodyClasses="p-0"
			title='<%= LanguageUtil.get(request, "attachments") %>'
		>
			<commerce-ui:dataset-display
				clayCreationMenu="<%= cpAttachmentFileEntriesDisplayContext.getClayCreationMenu(CPAttachmentFileEntryConstants.TYPE_OTHER) %>"
				contextParams="<%= contextParams %>"
				dataProviderKey="<%= CommerceProductDataSetConstants.COMMERCE_DATA_SET_KEY_PRODUCT_ATTACHMENTS %>"
				formId="fm"
				id="<%= CommerceProductDataSetConstants.COMMERCE_DATA_SET_KEY_PRODUCT_ATTACHMENTS %>"
				itemsPerPage="<%= 10 %>"
				namespace="<%= renderResponse.getNamespace() %>"
				pageNumber="<%= 1 %>"
				portletURL="<%= portletURL %>"
			/>
		</commerce-ui:panel>
	</aui:form>
</c:if>