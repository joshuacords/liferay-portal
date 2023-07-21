<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
long organizationId = ParamUtil.getLong(request, "organizationId");

Organization organization = OrganizationServiceUtil.fetchOrganization(organizationId);

request.setAttribute(WebKeys.ORGANIZATION, organization);

request.setAttribute("addresses.className", Organization.class.getName());
request.setAttribute("addresses.classPK", organizationId);
request.setAttribute("emailAddresses.className", Organization.class.getName());
request.setAttribute("emailAddresses.classPK", organizationId);
request.setAttribute("phones.className", Organization.class.getName());
request.setAttribute("phones.classPK", organizationId);
request.setAttribute("websites.className", Organization.class.getName());
request.setAttribute("websites.classPK", organizationId);
%>

<liferay-util:include page="/tabs1.jsp" servletContext="<%= application %>">
	<liferay-util:param name="showSearch" value="<%= Boolean.FALSE.toString() %>" />
</liferay-util:include>

<div class="organization-information">
	<div class="entity-details section">
		<liferay-util:include page="/organization/details.jsp" servletContext="<%= application %>" />
	</div>

	<div class="section">
		<liferay-util:include page="/common/additional_email_addresses.jsp" servletContext="<%= application %>" />
	</div>

	<div class="section">
		<liferay-util:include page="/common/websites.jsp" servletContext="<%= application %>" />
	</div>

	<div class="section">
		<liferay-util:include page="/organization/addresses.jsp" servletContext="<%= application %>" />
	</div>

	<div class="section">
		<liferay-util:include page="/organization/phone_numbers.jsp" servletContext="<%= application %>" />
	</div>

	<div class="section">
		<liferay-util:include page="/organization/services.jsp" servletContext="<%= application %>" />
	</div>

	<div class="section">
		<liferay-util:include page="/organization/comments.jsp" servletContext="<%= application %>" />
	</div>
</div>