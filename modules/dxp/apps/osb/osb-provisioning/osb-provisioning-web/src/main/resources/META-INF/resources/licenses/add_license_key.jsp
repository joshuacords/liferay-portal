<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<liferay-util:include page="/common/view_account_search_header.jsp" servletContext="<%= application %>" />

<%
AddLicenseKeyDisplayContext addLicenseKeyDisplayContext = ProvisioningWebComponentProvider.getAddLicenseKeyDisplayContext(renderRequest, renderResponse, request);
%>

<div class="add-items">
	<liferay-ui:header
		backURL='<%= ParamUtil.getString(request, "redirect") %>'
		cssClass="add-items-header"
		title='<%= LanguageUtil.get(request, "generate-license") %>'
	/>

	<div id="generateLicense">
		<react:component
			data="<%= addLicenseKeyDisplayContext.getAddLicenseKeyData() %>"
			module="js/apps/GenerateLicenseApp"
		/>
	</div>
</div>