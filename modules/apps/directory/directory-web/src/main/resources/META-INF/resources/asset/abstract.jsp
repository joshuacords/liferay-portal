<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
User selUser = (User)request.getAttribute(WebKeys.USER);

selUser = selUser.toEscapedModel();

Contact selContact = selUser.getContact();

List<Organization> organizations = OrganizationLocalServiceUtil.getUserOrganizations(selUser.getUserId());

request.setAttribute("addresses.className", Contact.class.getName());
request.setAttribute("addresses.classPK", selContact.getContactId());
request.setAttribute("emailAddresses.className", Contact.class.getName());
request.setAttribute("emailAddresses.classPK", selContact.getContactId());
request.setAttribute("phones.className", Contact.class.getName());
request.setAttribute("phones.classPK", selContact.getContactId());
request.setAttribute("user.organizations", organizations);
request.setAttribute("user.selContact", selContact);
request.setAttribute("user.selUser", selUser);
request.setAttribute("websites.className", Contact.class.getName());
request.setAttribute("websites.classPK", selContact.getContactId());
%>

<div class="user-information">
	<div class="entity-details section">
		<liferay-util:include page="/user/details.jsp" servletContext="<%= application %>" />
	</div>

	<div class="section">
		<liferay-util:include page="/user/addresses.jsp" servletContext="<%= application %>" />
	</div>

	<div class="section">
		<liferay-util:include page="/common/additional_email_addresses.jsp" servletContext="<%= application %>" />
	</div>

	<div class="section">
		<liferay-util:include page="/common/websites.jsp" servletContext="<%= application %>" />
	</div>

	<div class="entity-phones section">
		<liferay-util:include page="/user/phone_numbers.jsp" servletContext="<%= application %>" />
	</div>

	<div class="section">
		<liferay-util:include page="/user/instant_messenger.jsp" servletContext="<%= application %>" />
	</div>

	<div class="section">
		<liferay-util:include page="/user/social_network.jsp" servletContext="<%= application %>" />
	</div>

	<div class="section">
		<liferay-util:include page="/user/sms.jsp" servletContext="<%= application %>" />
	</div>

	<div class="section">
		<liferay-util:include page="/user/comments.jsp" servletContext="<%= application %>" />
	</div>
</div>