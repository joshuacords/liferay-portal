<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String catalogNavigationItem = ParamUtil.getString(request, "catalogNavigationItem", "view-all-product-definitions");

CPDefinitionsDisplayContext cpDefinitionsDisplayContext = (CPDefinitionsDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

PortletURL portletURL = cpDefinitionsDisplayContext.getPortletURL();

request.setAttribute("view.jsp-portletURL", portletURL);
%>

<clay:navigation-bar
	inverted="<%= true %>"
	navigationItems="<%= CPNavigationItemRegistryUtil.getNavigationItems(renderRequest) %>"
/>

<%@ include file="/navbar_definitions.jspf" %>

<div class="pt-4" id="<portlet:namespace />productDefinitionsContainer">
	<aui:form action="<%= portletURL.toString() %>" cssClass="container-fluid-1280" method="post" name="fm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" />
		<aui:input name="redirect" type="hidden" value="<%= portletURL.toString() %>" />
		<aui:input name="deleteCPDefinitionIds" type="hidden" />

		<commerce-ui:headless-dataset-display
			apiUrl="/o/headless-commerce-admin-catalog/v1.0/products?nestedFields=skus,catalog"
			clayCreationMenu="<%= cpDefinitionsDisplayContext.getClayCreationMenu() %>"
			clayHeadlessDataSetActionTemplates="<%= cpDefinitionsDisplayContext.getClayHeadlessDataSetActionTemplates() %>"
			formId="fm"
			id="<%= CommerceProductDataSetConstants.COMMERCE_DATA_SET_KEY_PRODUCT_DEFINITIONS %>"
			itemsPerPage="<%= 10 %>"
			namespace="<%= renderResponse.getNamespace() %>"
			pageNumber="<%= 1 %>"
			portletURL="<%= portletURL %>"
			style="stacked"
		/>
	</aui:form>
</div>