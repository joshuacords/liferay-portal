<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/shared_assets/init.jsp" %>

<%
Portlet selPortlet = PortletLocalServiceUtil.getPortletById(company.getCompanyId(), portletDisplay.getId());
%>

<aui:script>
	Liferay.fire('closeWindow', {
		id: '<portlet:namespace />editAsset',
		portletAjaxable: <%= selPortlet.isAjaxable() %>,
		refresh: '<%= portletDisplay.getId() %>'
	});
</aui:script>