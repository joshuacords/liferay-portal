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

CPSku cpSku = cpContentHelper.getDefaultCPSku(cpCatalogEntry);
%>

<div class="card">
	<div class="autofit-row">
		<div class="autofit-col autofit-col-expand">
			<liferay-ui:icon
				cssClass="compare-remove-item link-monospaced"
				icon="times"
				markupView="lexicon"
				url="<%= cpCompareContentHelper.getDeleteCompareProductURL(cpCatalogEntry.getCPDefinitionId(), renderRequest, renderResponse) %>"
			/>
		</div>
	</div>

	<a class="product-image-container" href="<%= cpContentHelper.getFriendlyURL(cpCatalogEntry, themeDisplay) %>">

		<%
		String img = cpCatalogEntry.getDefaultImageFileUrl();
		%>

		<c:if test="<%= Validator.isNotNull(img) %>">
			<img class="img-responsive product-image" src="<%= img %>" />
		</c:if>
	</a>

	<div class="card-section-expand">
		<div class="card-title">
			<a href="<%= cpContentHelper.getFriendlyURL(cpCatalogEntry, themeDisplay) %>">
				<%= HtmlUtil.escape(cpCatalogEntry.getName()) %>
			</a>
		</div>

		<c:if test="<%= cpSku != null %>">
			<div class="card-subtitle">
				<liferay-ui:message arguments="<%= HtmlUtil.escape(cpSku.getSku()) %>" key="sku-x" />
			</div>
		</c:if>
	</div>

	<div class="autofit-float autofit-row autofit-row-end product-price-section">
		<div class="autofit-col">
			<span class="product-price">
				<liferay-commerce:price
					CPDefinitionId="<%= cpCatalogEntry.getCPDefinitionId() %>"
				/>
			</span>
		</div>
	</div>

	<c:if test="<%= cpSku != null %>">
		<div class="autofit-float autofit-row autofit-row-end product-subscription-info-section">
			<div class="autofit-col">
				<span class="product-subscription-info">
					<commerce-ui:product-subscription-info
						CPInstanceId="<%= cpSku.getCPInstanceId() %>"
					/>
				</span>
			</div>
		</div>
	</c:if>

	<%
	String quantityInputId = cpCatalogEntry.getCPDefinitionId() + "_quantity";
	%>

	<div class="product-footer">
		<c:if test="<%= (cpSku != null) && !cpContentHelper.hasChildCPDefinitions(cpCatalogEntry.getCPDefinitionId()) %>">
			<div class="autofit-row product-actions">
				<div class="autofit-col autofit-col-expand">
					<liferay-commerce:quantity-input
						CPDefinitionId="<%= cpCatalogEntry.getCPDefinitionId() %>"
						name="<%= quantityInputId %>"
						useSelect="<%= false %>"
					/>

					<liferay-commerce-cart:add-to-cart
						CPDefinitionId="<%= cpCatalogEntry.getCPDefinitionId() %>"
						CPInstanceId="<%= cpSku.getCPInstanceId() %>"
						elementClasses="btn-block btn-primary text-truncate"
						taglibQuantityInputId="<%= renderResponse.getNamespace() + quantityInputId %>"
					/>
				</div>
			</div>
		</c:if>
	</div>
</div>