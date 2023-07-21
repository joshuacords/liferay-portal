<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceOrganizationDisplayContext commerceOrganizationDisplayContext = (CommerceOrganizationDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);
%>

<div class="commerce-organization-container" id="<portlet:namespace />entriesContainer">

	<%
	Map<String, String> contextParams = new HashMap<>();

	contextParams.put("organizationId", String.valueOf(commerceOrganizationDisplayContext.getOrganizationId()));
	%>

	<commerce-ui:dataset-display
		contextParams="<%= contextParams %>"
		dataProviderKey="<%= CommerceOrganizationAccountClayTableDataSetDisplayView.NAME %>"
		id="<%= CommerceOrganizationAccountClayTableDataSetDisplayView.NAME %>"
		itemsPerPage="<%= 10 %>"
		namespace="<%= renderResponse.getNamespace() %>"
		pageNumber="<%= 1 %>"
		portletURL="<%= commerceOrganizationDisplayContext.getPortletURL() %>"
		showSearch="<%= false %>"
	/>
</div>

<c:if test="<%= OrganizationPermissionUtil.contains(permissionChecker, commerceOrganizationDisplayContext.getOrganizationId(), ActionKeys.UPDATE) %>">
	<portlet:actionURL name="editCommerceAccountOrganizationRel" var="editCommerceAccountOrganizationRelActionURL" />

	<aui:form action="<%= editCommerceAccountOrganizationRelActionURL %>" method="post" name="commerceAccountOrganizationRelFm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" />
		<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
		<aui:input name="organizationId" type="hidden" />
	</aui:form>

	<aui:script>
		Liferay.provide(window, 'deleteCommerceOrganizationAccount', function(id) {
			document.querySelector('#<portlet:namespace /><%= Constants.CMD %>').value =
				'<%= Constants.REMOVE %>';
			document.querySelector('#<portlet:namespace />organizationId').value = id;

			submitForm(document.<portlet:namespace />commerceAccountOrganizationRelFm);
		});
	</aui:script>
</c:if>