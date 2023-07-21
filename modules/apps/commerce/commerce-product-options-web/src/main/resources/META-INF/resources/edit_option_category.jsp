<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CPOptionCategory cpOptionCategory = (CPOptionCategory)request.getAttribute(CPWebKeys.CP_OPTION_CATEGORY);

long cpOptionCategoryId = BeanParamUtil.getLong(cpOptionCategory, request, "CPOptionCategoryId");

String title = LanguageUtil.get(request, "add-specification-group");

if (cpOptionCategory != null) {
	title = cpOptionCategory.getTitle(locale);
}

Map<String, Object> data = new HashMap<>();

data.put("direction-right", StringPool.TRUE);

PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, "specification-groups"), optionCategoriesURL, data);
PortalUtil.addPortletBreadcrumbEntry(request, title, StringPool.BLANK, data);

renderResponse.setTitle(LanguageUtil.get(request, "catalog"));
%>

<clay:navigation-bar
	inverted="<%= true %>"
	navigationItems="<%= CPNavigationItemRegistryUtil.getNavigationItems(renderRequest) %>"
/>

<%@ include file="/navbar_specifications.jspf" %>
<%@ include file="/breadcrumb.jspf" %>

<portlet:actionURL name="editProductOptionCategory" var="editProductOptionCategoryActionURL" />

<aui:form action="<%= editProductOptionCategoryActionURL %>" cssClass="container-fluid-1280" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= (cpOptionCategory == null) ? Constants.ADD : Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= optionCategoriesURL %>" />
	<aui:input name="cpOptionCategoryId" type="hidden" value="<%= String.valueOf(cpOptionCategoryId) %>" />

	<div class="lfr-form-content">
		<liferay-ui:form-navigator
			backURL="<%= optionCategoriesURL %>"
			formModelBean="<%= cpOptionCategory %>"
			id="<%= CPOptionCategoryFormNavigatorConstants.FORM_NAVIGATOR_ID_COMMERCE_PRODUCT_OPTION_CATEGORY %>"
			markupView="lexicon"
		/>
	</div>
</aui:form>