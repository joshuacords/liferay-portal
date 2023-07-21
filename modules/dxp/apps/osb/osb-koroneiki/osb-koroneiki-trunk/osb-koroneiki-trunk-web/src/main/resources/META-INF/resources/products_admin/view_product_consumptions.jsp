<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ProductConsumptionsDisplayContext productConsumptionsDisplayContext = new ProductConsumptionsDisplayContext(renderRequest, renderResponse, request);

ViewProductConsumptionsManagementToolbarDisplayContext viewProductConsumptionsManagementToolbarDisplayContext = new ViewProductConsumptionsManagementToolbarDisplayContext(liferayPortletRequest, liferayPortletResponse, request, productConsumptionsDisplayContext.getSearchContainer());
%>

<clay:navigation-bar
	inverted="<%= true %>"
	navigationItems="<%= viewProductConsumptionsManagementToolbarDisplayContext.getNavigationItems() %>"
/>

<clay:management-toolbar
	displayContext="<%= viewProductConsumptionsManagementToolbarDisplayContext %>"
/>

<div class="container-fluid-1280">
	<liferay-ui:search-container
		searchContainer="<%= productConsumptionsDisplayContext.getSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.osb.koroneiki.trunk.model.ProductConsumption"
			escapedModel="<%= true %>"
			keyProperty="productConsumptionId"
			modelVar="productConsumption"
		>

			<%
			Account koroneikiAccount = productConsumption.getAccount();
			ProductEntry productEntry = productConsumption.getProductEntry();
			%>

			<portlet:renderURL var="rowURL">
				<portlet:param name="mvcRenderCommandName" value="/products_admin/edit_product_consumption" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="productConsumptionId" value="<%= String.valueOf(productConsumption.getProductConsumptionId()) %>" />
			</portlet:renderURL>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="account"
			>
				<span class="lfr-portal-tooltip" data-title="<liferay-ui:message key="account" />">
					<aui:icon cssClass="icon-monospaced" image="users" markupView="lexicon" />
				</span>

				<%= HtmlUtil.escape(koroneikiAccount.getName()) %>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="product"
				value="<%= HtmlUtil.escape(productEntry.getName()) %>"
			/>

			<liferay-ui:search-container-column-jsp
				align="right"
				path="/products_admin/product_consumption_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>