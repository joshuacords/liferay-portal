<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CPDefinitionsDisplayContext cpDefinitionsDisplayContext = (CPDefinitionsDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CPDefinition cpDefinition = cpDefinitionsDisplayContext.getCPDefinition();
CProduct cProduct = cpDefinitionsDisplayContext.getCProduct();
PortletURL portletURL = cpDefinitionsDisplayContext.getEditProductDefinitionURL();

String headerTitle = LanguageUtil.get(request, "add-product");

if (cpDefinition != null) {
	headerTitle = cpDefinition.getName(languageId);
}

request.setAttribute("view.jsp-cpDefinition", cpDefinition);
request.setAttribute("view.jsp-cpType", cpDefinitionsDisplayContext.getCPType());
request.setAttribute("view.jsp-portletURL", portletURL);
request.setAttribute("view.jsp-showSearch", false);
%>

<liferay-portlet:renderURL var="editCProductExternalReferenceCodeURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
	<portlet:param name="mvcRenderCommandName" value="editCProductExternalReferenceCode" />
	<portlet:param name="cpDefinitionId" value="<%= String.valueOf(cpDefinitionsDisplayContext.getCPDefinitionId()) %>" />
</liferay-portlet:renderURL>

<commerce-ui:header
	actions="<%= cpDefinitionsDisplayContext.getHeaderActionModels() %>"
	bean="<%= cpDefinition %>"
	beanIdLabel="id"
	dropdownItems="<%= cpDefinitionsDisplayContext.getClayMenuActionItems() %>"
	externalReferenceCode="<%= (cProduct == null) ? StringPool.BLANK : cProduct.getExternalReferenceCode() %>"
	externalReferenceCodeEditUrl="<%= (cProduct == null) ? StringPool.BLANK : editCProductExternalReferenceCodeURL %>"
	model="<%= CPDefinition.class %>"
	thumbnailUrl="<%= cpDefinitionsDisplayContext.getCPDefinitionThumbnailURL() %>"
	title="<%= headerTitle %>"
	version="<%= (cpDefinition == null) ? StringPool.BLANK : String.valueOf(cpDefinition.getVersion()) %>"
	wrapperCssClasses="side-panel-top-anchor"
/>

<liferay-frontend:screen-navigation
	containerCssClass="col-md-10"
	key="<%= CPDefinitionScreenNavigationConstants.SCREEN_NAVIGATION_KEY_CP_DEFINITION_GENERAL %>"
	modelBean="<%= cpDefinition %>"
	navCssClass="col-md-2"
	portletURL="<%= currentURLObj %>"
/>

<aui:script>
	document
		.getElementById('<portlet:namespace />publishButton')
		.addEventListener('click', function(e) {
			e.preventDefault();

			var form = document.getElementById('<portlet:namespace />fm');

			if (!form) {
				throw new Error('Form with id: <portlet:namespace />fm not found!');
			}

			var workflowActionInput = document.getElementById(
				'<portlet:namespace />workflowAction'
			);

			if (workflowActionInput) {
				workflowActionInput.value =
					'<%= WorkflowConstants.ACTION_PUBLISH %>';
			}

			submitForm(form);
		});
</aui:script>