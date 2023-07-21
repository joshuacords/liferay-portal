<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceCatalogDisplayContext commerceCatalogDisplayContext = (CommerceCatalogDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);
%>

<liferay-ui:error embed="<%= false %>" exception="<%= CommerceCatalogProductsException.class %>" message="you-cannot-delete-catalogs-that-have-products" />

<div class="row">
	<div class="col-12">
		<commerce-ui:dataset-display
			clayCreationMenu="<%= commerceCatalogDisplayContext.getClayCreationMenu() %>"
			dataProviderKey="<%= CommerceCatalogDataSetConstants.COMMERCE_DATA_SET_KEY_CATALOGS %>"
			id="<%= CommerceCatalogDataSetConstants.COMMERCE_DATA_SET_KEY_CATALOGS %>"
			itemsPerPage="<%= 10 %>"
			namespace="<%= renderResponse.getNamespace() %>"
			pageNumber="<%= 1 %>"
			portletURL="<%= commerceCatalogDisplayContext.getPortletURL() %>"
			style="fluid"
		/>
	</div>
</div>