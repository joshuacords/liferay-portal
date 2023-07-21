<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CPCompareContentHelper cpCompareContentHelper = (CPCompareContentHelper)request.getAttribute(CPContentWebKeys.CP_COMPARE_CONTENT_HELPER);

CPContentHelper cpContentHelper = (CPContentHelper)request.getAttribute(CPContentWebKeys.CP_CONTENT_HELPER);

CPCatalogEntry cpCatalogEntry = cpContentHelper.getCPCatalogEntry(request);
%>

<div class="autofit-row autofit-row-end">
	<div class="autofit-col">
		<div class="card">
			<div class="sticker sticker-top-right">
				<liferay-ui:icon
					icon="times"
					markupView="lexicon"
					message="remove"
					url="<%= cpCompareContentHelper.getDeleteCompareProductURL(cpCatalogEntry.getCPDefinitionId(), renderRequest, renderResponse) %>"
				/>
			</div>

			<a class="aspect-ratio" href="<%= cpContentHelper.getFriendlyURL(cpCatalogEntry, themeDisplay) %>">

				<%
				String img = cpCatalogEntry.getDefaultImageFileUrl();
				%>

				<c:if test="<%= Validator.isNotNull(img) %>">
					<img class="aspect-ratio-item-center-middle aspect-ratio-item-fluid" src="<%= img %>" />
				</c:if>
			</a>
		</div>
	</div>

	<div class="autofit-col autofit-col-expand">
		<a class="compare-link" href="<%= cpContentHelper.getFriendlyURL(cpCatalogEntry, themeDisplay) %>">
			<%= HtmlUtil.escape(cpCatalogEntry.getName()) %>
		</a>
	</div>
</div>