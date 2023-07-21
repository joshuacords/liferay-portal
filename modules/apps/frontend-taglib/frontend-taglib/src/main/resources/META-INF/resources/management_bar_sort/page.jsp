<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/management_bar_sort/init.jsp" %>

<%
boolean disabled = GetterUtil.getBoolean(request.getAttribute("liferay-frontend:management-bar-sort:disabled"));
String orderByCol = (String)request.getAttribute("liferay-frontend:management-bar-sort:orderByCol");
String orderByType = (String)request.getAttribute("liferay-frontend:management-bar-sort:orderByType");
PortletURL portletURL = (PortletURL)request.getAttribute("liferay-frontend:management-bar-sort:portletURL");
%>

<liferay-frontend:management-bar-filter
	disabled="<%= disabled %>"
	label="order-by"
	managementBarFilterItems='<%= (List<ManagementBarFilterItem>)request.getAttribute("liferay-frontend:management-bar-sort:managementBarFilterItems") %>'
	value='<%= (String)request.getAttribute("liferay-frontend:management-bar-sort:orderByColLabel") %>'
/>

<%
PortletURL orderByColAscURL = PortletURLUtil.clone(portletURL, liferayPortletResponse);

orderByColAscURL.setParameter("orderByCol", orderByCol);
orderByColAscURL.setParameter("orderByType", "asc");
%>

<li>
	<liferay-frontend:management-bar-button
		active='<%= Validator.isNotNull(orderByType) && orderByType.equals("asc") %>'
		cssClass="hidden-xs"
		disabled="<%= disabled %>"
		href="<%= orderByColAscURL.toString() %>"
		icon="caret-top"
		label="ascending"
	/>
</li>

<%
PortletURL orderByColDescURL = PortletURLUtil.clone(portletURL, liferayPortletResponse);

orderByColDescURL.setParameter("orderByCol", orderByCol);
orderByColDescURL.setParameter("orderByType", "desc");
%>

<li>
	<liferay-frontend:management-bar-button
		active='<%= Validator.isNotNull(orderByType) && orderByType.equals("desc") %>'
		cssClass="hidden-xs"
		disabled="<%= disabled %>"
		href="<%= orderByColDescURL.toString() %>"
		icon="caret-bottom"
		label="descending"
	/>
</li>