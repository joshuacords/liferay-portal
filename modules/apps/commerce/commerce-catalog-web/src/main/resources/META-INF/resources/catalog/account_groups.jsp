<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceCatalogDisplayContext commerceCatalogDisplayContext = (CommerceCatalogDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceCatalog commerceCatalog = commerceCatalogDisplayContext.getCommerceCatalog();

Map<String, String> contextParams = new HashMap<>();

contextParams.put("commerceCatalogId", String.valueOf(commerceCatalog.getCommerceCatalogId()));
%>

<div class="row">
	<div class="col-12">
		<commerce-ui:dataset-display
			contextParams="<%= contextParams %>"
			dataProviderKey="<%= CommerceCatalogDataSetConstants.COMMERCE_DATA_SET_KEY_CATALOG_ACCOUNT_GROUPS %>"
			id="<%= CommerceCatalogDataSetConstants.COMMERCE_DATA_SET_KEY_CATALOG_ACCOUNT_GROUPS %>"
			itemsPerPage="<%= 10 %>"
			namespace="<%= renderResponse.getNamespace() %>"
			pageNumber="<%= 1 %>"
			portletURL="<%= commerceCatalogDisplayContext.getPortletURL() %>"
			style="fluid"
		/>
	</div>
</div>