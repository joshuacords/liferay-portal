<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceWishListDisplayContext commerceWishListDisplayContext = (CommerceWishListDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);
%>

<div class="container-fluid-1280 py-3">
	<h3 class="align-middle d-inline"><liferay-ui:message key="wish-lists" /></h3>

	<div class="d-inline float-right">
		<portlet:actionURL name="editCommerceWishList" var="addCommerceWishListActionURL">
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.SAVE %>" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
		</portlet:actionURL>

		<liferay-frontend:add-menu
			inline="<%= true %>"
		>
			<liferay-frontend:add-menu-item
				title='<%= LanguageUtil.get(request, "add-wish-list") %>'
				url="<%= addCommerceWishListActionURL %>"
			/>
		</liferay-frontend:add-menu>
	</div>
</div>

<div class="container-fluid-1280">
	<portlet:actionURL name="editCommerceWishList" var="editCommerceWishListActionURL" />

	<aui:form action="<%= editCommerceWishListActionURL %>" method="post" name="fm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" />
		<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
		<aui:input name="deleteCommerceWishListIds" type="hidden" />

		<liferay-ui:search-container
			id="commerceWishLists"
			searchContainer="<%= commerceWishListDisplayContext.getSearchContainer() %>"
		>
			<liferay-ui:search-container-row
				className="com.liferay.commerce.wish.list.model.CommerceWishList"
				keyProperty="commerceWishListId"
				modelVar="commerceWishList"
			>
				<liferay-ui:search-container-column-text
					cssClass="important table-cell-content"
					href="<%= commerceWishListDisplayContext.getRowURL(commerceWishList.getCommerceWishListId()) %>"
					property="name"
				/>

				<liferay-ui:search-container-column-jsp
					cssClass="entry-action-column"
					path="/wish_list_action.jsp"
				/>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator
				markupView="lexicon"
			/>
		</liferay-ui:search-container>
	</aui:form>
</div>