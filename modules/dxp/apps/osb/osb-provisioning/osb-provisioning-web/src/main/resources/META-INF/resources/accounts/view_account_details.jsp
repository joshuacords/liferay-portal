<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ViewAccountDisplayContext viewAccountDisplayContext = ProvisioningWebComponentProvider.getViewAccountDisplayContext(renderRequest, renderResponse, request);
%>

<liferay-ui:error exception="<%= AccountCodeException.class %>" message="please-enter-a-valid-code" />
<liferay-ui:error exception="<%= DuplicateAnalyticsCloudGroupIdException.class %>" message="analytics-cloud-group-id-must-be-unique" />
<liferay-ui:error exception="<%= DuplicateDossieraKeyException.class %>" message="dossiera-key-must-be-unique" />
<liferay-ui:error exception="<%= DuplicateDXPCloudProjectIdException.class %>" message="dxp-cloud-project-id-must-be-unique" />
<liferay-ui:error exception="<%= MultipleDossieraKeysException.class %>" message="an-account-can-only-have-one-dossiera-key" />

<liferay-ui:error exception="<%= Problem.ProblemException.class %>">

	<%
	Problem.ProblemException problemException = (Problem.ProblemException)errorException;
	%>

	<%= problemException.getMessage() %>
</liferay-ui:error>

<div class="account-details details-table" id="<portlet:namespace />accountDetails">
	<react:component
		data="<%= viewAccountDisplayContext.getAccountDetailsData() %>"
		module="js/apps/AccountDetailsApp"
	/>
</div>