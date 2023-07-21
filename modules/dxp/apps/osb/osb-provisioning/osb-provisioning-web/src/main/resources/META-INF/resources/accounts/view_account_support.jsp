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

<div class="details-table support-information" id="<portlet:namespace />supportInformation">
	<react:component
		data="<%= viewAccountDisplayContext.getSupportData() %>"
		module="js/apps/SupportInformationApp"
	/>
</div>