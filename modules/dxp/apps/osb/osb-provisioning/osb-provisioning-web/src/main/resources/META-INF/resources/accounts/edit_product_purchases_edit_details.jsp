<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String backURL = ParamUtil.getString(request, "backURL");

EditProductPurchasesDisplayContext editProductPurchasesDisplayContext = ProvisioningWebComponentProvider.getEditProductPurchasesDisplayContext(renderRequest, renderResponse, request);

if (Validator.isNull(backURL)) {
	backURL = ParamUtil.getString(request, "redirect");

	if (Validator.isNull(backURL)) {
		backURL = editProductPurchasesDisplayContext.getRedirectURL();
	}
}
%>

<div class="add-items">
	<liferay-ui:header
		backURL="<%= backURL %>"
		cssClass="add-items-header"
		title="<%= editProductPurchasesDisplayContext.getTitle() %>"
	/>

	<liferay-ui:error exception="<%= Problem.ProblemException.class %>">

		<%
		Problem.ProblemException problemException = (Problem.ProblemException)errorException;
		%>

		<%= problemException.getMessage() %>
	</liferay-ui:error>

	<liferay-ui:error exception="<%= ProductPurchaseQuantityException.class %>" message="to-remove-a-subscription-change-the-status-to-cancelled-instead" />

	<div id="<portlet:namespace />editSubscriptions">
		<react:component
			data="<%= editProductPurchasesDisplayContext.getEditProductPurchasesData() %>"
			module="js/apps/EditSubscriptionsApp"
		/>
	</div>
</div>