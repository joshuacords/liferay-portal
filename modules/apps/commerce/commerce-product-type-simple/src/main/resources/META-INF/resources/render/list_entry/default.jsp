<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CPContentHelper cpContentHelper = (CPContentHelper)request.getAttribute(CPContentWebKeys.CP_CONTENT_HELPER);

CPCatalogEntry cpCatalogEntry = cpContentHelper.getCPCatalogEntry(request);
%>

<div class="col-md-4">
	<div class="card">
		<a class="aspect-ratio" href="<%= cpContentHelper.getFriendlyURL(cpCatalogEntry, themeDisplay) %>">

			<%
			String img = cpCatalogEntry.getDefaultImageFileUrl();
			%>

			<c:if test="<%= Validator.isNotNull(img) %>">
				<img class="aspect-ratio-item-center-middle aspect-ratio-item-fluid" src="<%= img %>" />
			</c:if>
		</a>

		<div class="card-row card-row-padded card-row-valign-top">
			<div class="card-col-content">
				<a class="truncate-text" href="<%= cpContentHelper.getFriendlyURL(cpCatalogEntry, themeDisplay) %>">
					<%= HtmlUtil.escape(cpCatalogEntry.getName()) %>
				</a>
			</div>
		</div>
	</div>
</div>