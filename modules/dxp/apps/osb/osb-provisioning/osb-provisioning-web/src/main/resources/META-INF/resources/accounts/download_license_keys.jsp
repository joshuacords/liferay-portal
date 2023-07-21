<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<liferay-util:include page="/common/view_account_search_header.jsp" servletContext="<%= application %>" />

<%
String redirect = ParamUtil.getString(request, "redirect");

DownloadLicenseKeysDisplayContext downloadLicenseKeysDisplayContext = new DownloadLicenseKeysDisplayContext(renderRequest, renderResponse, request);
%>

<div class="add-items">
	<liferay-ui:header
		backURL="<%= redirect %>"
		cssClass="add-items-header"
		title='<%= LanguageUtil.get(request, "download-licenses") %>'
	/>

	<div id="<portlet:namespace />downloadLicenses">
		<react:component
			data="<%= downloadLicenseKeysDisplayContext.getDownloadLicenseKeysData() %>"
			module="js/apps/DownloadLicensesApp"
		/>
	</div>
</div>