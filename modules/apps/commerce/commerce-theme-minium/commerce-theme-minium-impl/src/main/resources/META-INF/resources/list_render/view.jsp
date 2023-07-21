<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CPDataSourceResult cpDataSourceResult = (CPDataSourceResult)request.getAttribute(CPWebKeys.CP_DATA_SOURCE_RESULT);

List<CPCatalogEntry> cpCatalogEntries = cpDataSourceResult.getCPCatalogEntries();
%>

<c:choose>
	<c:when test="<%= !cpCatalogEntries.isEmpty() %>">
		<div class="minium-product-tiles">

			<%
			for (CPCatalogEntry cpCatalogEntry : cpCatalogEntries) {
			%>

				<div class="minium-product-tiles__item">
					<liferay-commerce-product:product-list-entry-renderer
						CPCatalogEntry="<%= cpCatalogEntry %>"
					/>
				</div>

			<%
			}
			%>

		</div>
	</c:when>
	<c:otherwise>
		<div class="alert alert-info">
			<liferay-ui:message key="no-products-were-found" />
		</div>
	</c:otherwise>
</c:choose>