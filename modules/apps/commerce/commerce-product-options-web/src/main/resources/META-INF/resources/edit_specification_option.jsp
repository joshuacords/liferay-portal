<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CPSpecificationOption cpSpecificationOption = (CPSpecificationOption)request.getAttribute(CPWebKeys.CP_SPECIFICATION_OPTION);

long cpSpecificationOptionId = BeanParamUtil.getLong(cpSpecificationOption, request, "CPSpecificationOptionId");

String title = LanguageUtil.get(request, "add-specification-label");

if (cpSpecificationOption != null) {
	title = cpSpecificationOption.getTitle(locale);
}

Map<String, Object> data = new HashMap<>();

data.put("direction-right", StringPool.TRUE);

PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, "specification-labels"), specificationOptionsURL, data);
PortalUtil.addPortletBreadcrumbEntry(request, title, StringPool.BLANK, data);

renderResponse.setTitle(LanguageUtil.get(request, "catalog"));
%>

<clay:navigation-bar
	inverted="<%= true %>"
	navigationItems="<%= CPNavigationItemRegistryUtil.getNavigationItems(renderRequest) %>"
/>

<%@ include file="/navbar_specifications.jspf" %>
<%@ include file="/breadcrumb.jspf" %>

<portlet:actionURL name="editProductSpecificationOption" var="editProductSpecificationOptionActionURL" />

<aui:form action="<%= editProductSpecificationOptionActionURL %>" cssClass="container-fluid-1280" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= (cpSpecificationOption == null) ? Constants.ADD : Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= specificationOptionsURL %>" />
	<aui:input name="cpSpecificationOptionId" type="hidden" value="<%= String.valueOf(cpSpecificationOptionId) %>" />

	<div class="lfr-form-content">
		<liferay-ui:form-navigator
			backURL="<%= specificationOptionsURL %>"
			formModelBean="<%= cpSpecificationOption %>"
			id="<%= CPSpecificationOptionFormNavigatorConstants.FORM_NAVIGATOR_ID_COMMERCE_PRODUCT_SPECIFICATION_OPTION %>"
			markupView="lexicon"
		/>
	</div>
</aui:form>