<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CPCompareContentHelper cpCompareContentHelper = (CPCompareContentHelper)request.getAttribute(CPContentWebKeys.CP_COMPARE_CONTENT_HELPER);

CPDataSourceResult cpDataSourceResult = (CPDataSourceResult)request.getAttribute(CPWebKeys.CP_DATA_SOURCE_RESULT);

List<CPCatalogEntry> cpCatalogEntries = cpDataSourceResult.getCPCatalogEntries();

CommerceContext commerceContext = (CommerceContext)request.getAttribute(CommerceWebKeys.COMMERCE_CONTEXT);

long commerceAccountId = 0;

CommerceAccount commerceAccount = commerceContext.getCommerceAccount();

if (commerceAccount != null) {
	commerceAccountId = GetterUtil.getLong(commerceAccount.getCommerceAccountId());
}

HttpServletRequest originalHttpServletRequest = PortalUtil.getOriginalServletRequest(request);

List<Long> cpDefinitionIds = CPCompareHelperUtil.getCPDefinitionIds(commerceContext.getCommerceChannelGroupId(), commerceAccountId, originalHttpServletRequest.getSession());
%>

<c:if test="<%= !cpCatalogEntries.isEmpty() %>">
	<div id="<portlet:namespace />compareProductsMiniContainer">
		<div class="compare-products-mini-header">
			<a class="collapse-icon lfr-compare-products-mini-header" href="javascript:;">
				<span class="component-title"><liferay-ui:message arguments="<%= new Object[] {cpCatalogEntries.size(), cpDefinitionIds.size()} %>" key="x-of-x-products-selected" translateArguments="<%= false %>" /></span>

				<span class="collapse-icon-open">
					<liferay-ui:icon
						icon="angle-down"
						markupView="lexicon"
					/>
				</span>
				<span class="collapse-icon-closed">
					<liferay-ui:icon
						icon="angle-up"
						markupView="lexicon"
					/>
				</span>
			</a>
		</div>

		<div class="lfr-compare-products-mini-content">
			<ul class="card-page">

				<%
				for (CPCatalogEntry cpCatalogEntry : cpCatalogEntries) {
					request.setAttribute("cpContentListRenderer-deleteCompareProductURL", cpCompareContentHelper.getDeleteCompareProductURL(cpCatalogEntry.getCPDefinitionId(), renderRequest, renderResponse));
				%>

					<li class="card-page-item">
						<liferay-commerce-product:product-list-entry-renderer
							CPCatalogEntry="<%= cpCatalogEntry %>"
						/>
					</li>

				<%
				}
				%>

				<li class="card-page-item card-page-item-shrink">
					<a class="btn btn-link" href="<%= HtmlUtil.escape(cpCompareContentHelper.getClearCompareProductsURL(renderRequest, renderResponse)) %>"><liferay-ui:message key="clear-all" /></a>
				</li>
				<li class="card-page-item card-page-item-shrink">
					<aui:button cssClass="btn-primary" href="<%= HtmlUtil.escape(cpCompareContentHelper.getCompareProductsURL(themeDisplay)) %>" value="compare" />
				</li>
			</ul>
		</div>
	</div>

	<aui:script use="aui-toggler">
		new A.Toggler({
			animated: true,
			content:
				'#<portlet:namespace />compareProductsMiniContainer .lfr-compare-products-mini-content',
			expanded: true,
			header:
				'#<portlet:namespace />compareProductsMiniContainer .lfr-compare-products-mini-header'
		});
	</aui:script>
</c:if>