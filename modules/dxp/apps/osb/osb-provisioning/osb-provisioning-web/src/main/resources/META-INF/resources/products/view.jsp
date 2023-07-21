<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<liferay-util:include page="/common/view_account_search_header.jsp" servletContext="<%= application %>" />

<%
ProductSearchDisplayContext productSearchDisplayContext = ProvisioningWebComponentProvider.getProductSearchDisplayContext(renderRequest, renderResponse, request);
%>

<portlet:renderURL var="editProductURL">
	<portlet:param name="mvcRenderCommandName" value="/products/edit_product" />
	<portlet:param name="redirect" value="<%= currentURL %>" />
</portlet:renderURL>

<div class="title-bar">
	<h3><liferay-ui:message key="products" /></h3>

	<c:if test="<%= productSearchDisplayContext.hasManageProductsPermission() %>">
		<a aria-label="<%= LanguageUtil.get(request, "new-product") %>" class="btn btn-primary nav-btn nav-btn-monospaced" href="<%= editProductURL %>" title="<%= LanguageUtil.get(request, "new-product") %>">
			<svg class="lexicon-icon lexicon-icon-plus" focusable="false" role="presentation">
				<use xlink:href="#plus" />
			</svg>
		</a>
	</c:if>
</div>

<div class="container-fluid home">
	<liferay-ui:error exception="<%= Problem.ProblemException.class %>">

		<%
		Problem.ProblemException problemException = (Problem.ProblemException)errorException;
		%>

		<%= problemException.getMessage() %>
	</liferay-ui:error>

	<liferay-ui:error key="<%= RequiredProductException.class.getName() %>" message="please-remove-the-product-from-all-product-bundles-before-deleting" />

	<portlet:actionURL name="/search" var="searchURL" />

	<clay:management-toolbar
		displayContext="<%= new ViewProductsManagementToolbarDisplayContext(liferayPortletRequest, liferayPortletResponse, request, productSearchDisplayContext.getSearchContainer()) %>"
		elementClasses="full-width"
	/>

	<liferay-ui:search-container
		cssClass="table-hover"
		searchContainer="<%= productSearchDisplayContext.getSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.osb.provisioning.web.internal.display.context.ProductDisplay"
			keyProperty="productKey"
			modelVar="productDisplay"
		>
			<portlet:renderURL var="rowURL">
				<portlet:param name="mvcRenderCommandName" value="/products/edit_product" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="productKey" value="<%= productDisplay.getKey() %>" />
			</portlet:renderURL>

			<liferay-ui:search-container-column-text
				href="<%= productDisplay.hasEditPermission() ? rowURL : StringPool.BLANK %>"
				name="name"
				value="<%= HtmlUtil.escape(productDisplay.getName()) %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= productDisplay.hasEditPermission() ? rowURL : StringPool.BLANK %>"
				name="type"
				value="<%= productDisplay.getType() %>"
			/>

			<liferay-ui:search-container-column-jsp
				align="right"
				path="/products/product_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>