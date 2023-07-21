<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
NPMResolver npmResolver = (NPMResolver)request.getAttribute("NPMResolver");

String cpOptionsEditorRequire = npmResolver.resolveModuleName("commerce-product-options-web/CPOptionsEditor.es") + " as CPOptionsEditor";

renderResponse.setTitle(LanguageUtil.get(request, "catalog"));
%>

<clay:navigation-bar
	inverted="<%= true %>"
	navigationItems="<%= CPNavigationItemRegistryUtil.getNavigationItems(renderRequest) %>"
/>

<%
NavigationItem navigationItem = new NavigationItem();

navigationItem.setActive(true);
navigationItem.setHref(currentURL);
navigationItem.setLabel(LanguageUtil.get(request, "option-templates"));
%>

<clay:navigation-bar
	navigationItems="<%= Collections.singletonList(navigationItem) %>"
/>

<div class="container-fluid-1280" id="<portlet:namespace />CPOptionsEditor">

</div>

<portlet:resourceURL id="cpOptions" var="cpOptionsURL">
</portlet:resourceURL>

<liferay-portlet:renderURL var="cpOptionURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="mvcRenderCommandName" value="cpOption" />
</liferay-portlet:renderURL>

<portlet:resourceURL id="cpOptionValues" var="cpOptionValuesURL">
</portlet:resourceURL>

<liferay-portlet:renderURL var="cpOptionValueURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="mvcRenderCommandName" value="editProductOptionValue" />
</liferay-portlet:renderURL>

<aui:script require="<%= cpOptionsEditorRequire %>">
	var cpOptionsEditor = new CPOptionsEditor.default(
		{
			hasEditPermission: <%= PortalPermissionUtil.contains(permissionChecker, CPActionKeys.ADD_COMMERCE_PRODUCT_OPTION) %>,
			namespace: '<portlet:namespace />',
			optionURL: '<%= cpOptionURL %>',
			optionValueURL: '<%= cpOptionValueURL %>',
			optionValuesURL: '<%= cpOptionValuesURL %>',
			optionsURL: '<%= cpOptionsURL %>',
			pathThemeImages: '<%= themeDisplay.getPathThemeImages() %>',
			successMessage:
				'<liferay-ui:message key="your-request-completed-successfully" />'
		},
		'#<portlet:namespace />CPOptionsEditor'
	);
</aui:script>